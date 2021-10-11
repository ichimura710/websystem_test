package com.rhizome.example.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rhizome.example.entity.base.BaseEntity;

public abstract class BaseDao<T extends BaseEntity> {

	protected Connection con;
	protected PreparedStatement stmt;

	protected BaseDao(Connection con) {
		this.con = con;
	}

	// ============================================ 汎用SQL系
	/**
	 * 全件検索メソッド
	 * @return 検索結果（List）
	 */
	public List<T> findAll() throws SQLException {
		List<T> list = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM " + this.getTableName());

		this.stmt = this.con.prepareStatement(sql.toString());

		ResultSet rs = this.stmt.executeQuery();

		while (rs.next()) {
			list.add(this.rowMapping(rs));
		}

		this.colseStatement();
		return list;
	}

	/**
	 * 主キーで検索を行う
	 * @param pkList
	 * @return 検索結果DTO
	 * @throws SQLException
	 */
	public T findByPramaryKey(Object...pkValue) throws SQLException {
		T entity = null;
		StringBuilder sql = new StringBuilder();
		String[] pkList = this.getPrimaryKey();

		// エラーチェック
		if (pkList.length == 0) {
			throw new SQLException("主キーが設定されていません");
		}
		if (pkList.length != pkValue.length) {
			throw new SQLException("主キーに設定されているカラムと条件に指定する値の数が一致しません");
		}

		// SQLを生成
		sql.append(" SELECT * FROM " + this.getTableName());
		sql.append(" WHERE ");
		sql.append(" 'X' = 'X' ");	// 汎用的な条件設定のため指定
		for (String column : pkList) {
			sql.append(" AND " + column + " = ?");
		}

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(pkValue);

		ResultSet rs = stmt.executeQuery();

		// 主キー検索のため、0件、または1件と想定
		if (rs.next()) {
			entity = rowMapping(rs);
		}

		// 見つからなかった
		this.colseStatement();
		return entity;
	}

	/**
	 * 全項目指定の登録を行う
	 * @param insertValue
	 * @return
	 * @throws SQLException
	 */
	public int insert(List<Object> insertValue) throws SQLException {
		int count = -1;
		StringBuilder sql = new StringBuilder();
		String[] columnsList = this.getColumnsList();

		// エラーチェック
		if (columnsList.length != insertValue.size()) {
			throw new SQLException("登録する値とカラムの値が一致していません");
		}

		// SQLを生成
		sql.append(" INSERT INTO " + this.getTableName());
		sql.append(" VALUES (");
		for (int i = 0; i < columnsList.length; i++) {
			sql.append("?, ");
		}
		sql.delete(sql.length() - 2, sql.length()); // 末尾のカンマを除去する
		sql.append(")");

		// Statementの生成および条件の設定
		this.stmt = con.prepareStatement(sql.toString());
		setParameter(insertValue.toArray());

		// 登録の実行
		count = this.stmt.executeUpdate();

		this.colseStatement();
		return count;
	}

	/**
	 * 主キー削除を行う
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public int deleteByPrimaryKey(Object... pkValue) throws SQLException {
		int count = -1;
		StringBuilder sql = new StringBuilder();
		String[] pkList = this.getPrimaryKey();

		// エラーチェック
		if (pkList.length == 0) {
			throw new SQLException("主キーが設定されていません");
		}
		if (pkList.length != pkValue.length) {
			throw new SQLException("主キーに設定されているカラムと条件に指定する値の数が一致しません");
		}

		// SQLを生成
		sql.append(" DELETE FROM " + this.getTableName());
		sql.append(" WHERE");
		sql.append(" 'X' = 'X'");	// 汎用的な条件設定のため指定
		for (String column : pkList) {
			sql.append(" AND " + column + " = ?");
		}

		// Statementの生成および条件の設定
		this.stmt = con.prepareStatement(sql.toString());
		setParameter(pkValue);

		// 登録の実行
		count = this.stmt.executeUpdate();

		this.colseStatement();
		return count;
	}

	/**
	 * 主キー指定の更新を行う。
	 * 更新項目は全カラムが対象
	 * @param updateValue
	 * @param pkValue
	 * @return
	 * @throws SQLException
	 */
	public int updateByPrimaryKey(List<Object> updateValue, Object... pkValue) throws SQLException {
		int count = -1;
		StringBuilder sql = new StringBuilder();
		String[] columnsList = this.getColumnsList();
		String[] pkList = this.getPrimaryKey();

		// エラーチェック
		if (pkList.length == 0) {
			throw new SQLException("主キーが設定されていません");
		}
		if (pkList.length != pkValue.length) {
			throw new SQLException("主キーに設定されているカラムと条件に指定する値の数が一致しません");
		}
		if (columnsList.length != updateValue.size()) {
			throw new SQLException("カラムの数と引数に指定された更新値の数が一致しません");
		}

		// SQLを生成
		sql.append(" UPDATE " + this.getTableName());
		sql.append(" SET ");
		for (String column : columnsList) {
			sql.append(column + " = ?, ");
		}
		sql.delete(sql.length() - 2, sql.length()); // 末尾のカンマを除去する
		sql.append(" WHERE");
		sql.append(" 'X' = 'X'");	// 汎用的な条件設定のため指定
		for (String column : pkList) {
			sql.append(" AND " + column + " = ?");
		}

		// 更新値と主キー値をひとまとめにする
		Object[] paramList = new Object[updateValue.size() + pkValue.length];
		int index = 0;
		for (int i = 0; i < updateValue.size(); i++, index++) {
			paramList[index] = updateValue.get(i);
		}
		for (int i = 0; i < pkValue.length; i++, index++) {
			paramList[index] = pkValue[i];
		}

		// Statementの生成および条件の設定
		this.stmt = con.prepareStatement(sql.toString());
		setParameter(paramList);

		// 登録の実行
		count = this.stmt.executeUpdate();

		this.colseStatement();
		return count;
	}

	// ============================================ Utilメソッド
	/**
	 * 値の設定を行う
	 * @param valueList
	 * @return
	 */
	protected void setParameter(Object... paramValue) throws SQLException {
		for (int i = 0; i < paramValue.length; i++) {
			/*
			 * 今回のテーブルは、intとvarcharのみのため判定は２種とする
			 */
			if (paramValue[i] instanceof String) {
				this.stmt.setString(i + 1, (String) paramValue[i]);
			} else if (paramValue[i] instanceof Integer) {
				this.stmt.setInt(i + 1, (Integer) paramValue[i]);
			} else {
				throw new SQLException("登録できない値が含まれています");
			}
		}
	}

	/**
	 * Statementを閉じる
	 */
	protected void colseStatement() {
		try {
			if (this.stmt != null) {
				this.stmt.close();
			}
		} catch (SQLException e) {
			/*
			 * Statementが閉じれない場合は
			 * 何かしらの理由でStatmentが空いていない可能性がある
			 * それならば特に問題はないので何もしない
			 */
		}
		stmt = null;
	}

	// ============================================ 抽象メソッド
	/**
	 * テーブル物理名を取得する
	 *
	 * @return tableName
	 */
	protected abstract String getTableName();

	/**
	 * 取得したデータのマッピング
	 *
	 * @param rs
	 * @return ArrayList<BaseEntity>
	 */
	protected abstract T rowMapping(ResultSet rs) throws SQLException;

	/**
	 * テーブルに存在するカラムの一覧を取得する
	 * @return
	 */
	protected abstract String[] getColumnsList();

	/**
	 * テーブルの主キーを取得する
	 *
	 * @return
	 */
	protected abstract String[] getPrimaryKey();
}
