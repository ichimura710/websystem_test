/*
 * DbUtil.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベースの接続・切断を行うクラスです。
 * @author IT-College
 *
 */
public class DbUtil {
	// 定数定義（共通）
	private static final String DB_HOST = "127.0.0.1";
	private static final String DB_NAME = "rezodb";
	private static final String DB_USER = "rezouser";
	private static final String DB_PASS = "Rezo_0000";

	// 定数定義（PostgreSQL用）
//	private static final String DBMS = "postgresql";
//	private static final String DB_DRIVER = "org." + DBMS + ".Driver";
//	private static final String DB_PORT = "5432";
//	private static final String DB_URL = "jdbc:" + DBMS + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

	// 定数定義（MySQL用）
	private static final String DBMS ="mysql";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_PORT = "3306";
	private static final String DB_URL = "jdbc:" + DBMS + "://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=JST";

	// インスタンス化の禁止
	private DbUtil() {}

	// Connectionを単一のインスタンスとする
	private static Connection con;

	/**
	 * DBのコネクションを取得
	 * @return DBコネクション
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection(boolean autoCommitFlg) throws ClassNotFoundException, SQLException {
		if (con == null || con.isClosed()) {
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			con.setAutoCommit(false);
		}
		return con;
	}

	/**
	 * DBとのコネクションを切断
	 * @param con DBコネクション
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException {
		if (con != null && con.isClosed() != false) { con.close(); }
	}
}
