/*
 * Employee.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.entity;

import com.rhizome.example.entity.base.BaseEntity;

/**
 * EmployeeテーブルのEntity
 * @author IT-College
 *
 */
public class Employee implements BaseEntity {
	public static final String TITLE_ID_EMPLOYEE = "社員ID";
	public static final String TITLE_NM_EMPLOYEE = "社員名";
	public static final String TITLE_KN_EMPLOYEE = "フリガナ";
	public static final String TITLE_MAIL = "メールアドレス";
	public static final String TITLE_PASSWORD = "パスワード";
	public static final String TITLE_ID_DEPARTMENT = "部署ID";

	/** 社員ID */
	private Integer idEmployee;
	/** 社員名 */
	private String nmEmployee;
	/** 社員名カナ */
	private String knEmployee;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/** 部署ID */
	private Integer idDepartment;

	/**
	 * 社員IDを取得します
	 * @return 社員ID
	 */
	public Integer getIdEmployee() {
		return idEmployee;
	}
	/**
	 * 社員IDを設定します
	 * @param idEmployee 社員ID
	 */
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	/**
	 * 社員名を取得します
	 * @return 社員名
	 */
	public String getNmEmployee() {
		return nmEmployee;
	}
	/**
	 * 社員名を設定します
	 * @param nmEmployee 社員名
	 */
	public void setNmEmployee(String nmEmployee) {
		this.nmEmployee = nmEmployee;
	}
	/**
	 * 社員名カナを取得します
	 * @return 社員名カナ
	 */
	public String getKnEmployee() {
		return knEmployee;
	}
	/**
	 * 社員名カナを設定します
	 * @param knEmployee 社員名カナ
	 */
	public void setKnEmployee(String knEmployee) {
		this.knEmployee = knEmployee;
	}
	/**
	 * メールアドレスを取得します
	 * @return メールアドレス
	 */
	public String getMailAddress() {
		return mailAddress;
	}
	/**
	 * メールアドレスを設定します
	 * @param mailAddress メールアドレス
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	/**
	 * パスワードを取得します
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * パスワードを設定します
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 部署IDを取得します
	 * @return 部署ID
	 */
	public Integer getIdDepartment() {
		return idDepartment;
	}
	/**
	 * 部署IDを設定します
	 * @param idDepartment 部署ID
	 */
	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}
}
