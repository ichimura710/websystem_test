package com.rhizome.example.service.base;

import java.sql.Connection;

import com.rhizome.example.util.DbUtil;

public abstract class BaseService {
	protected Connection con;

	/**
	 * コンストラクタでDBコネクションを生成。
	 * 検索系はfalse、更新系はtrueを引数で指定する
	 * @param autoCommitFlg
	 * @throws Exception
	 */
	protected BaseService(boolean autoCommitFlg) throws Exception {
		try {
			this.con = DbUtil.getConnection(autoCommitFlg);
		} catch(Exception e) {
			throw new Exception("データベースに接続できません");
		}
	}
}
