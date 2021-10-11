/*
 * Department.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.entity;

import com.rhizome.example.entity.base.BaseEntity;

/**
 * DepartmentテーブルのEntity
 * @author IT-College
 *
 */
public class Department implements BaseEntity {
	public static final String TITLE_ID_DEPARTMENT = "部署ID";
	public static final String TITLE_NM_DEPARTMENT = "部署名";

	/** 部署ID */
	private Integer idDepartment;
	/** 部署名 */
	private String nmDepartment;

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
	/**
	 * 部署名を取得します
	 * @return 部署名
	 */
	public String getNmDepartment() {
		return nmDepartment;
	}
	/**
	 * 部署名を設定します
	 * @param nmDepartment 部署名
	 */
	public void setNmDepartment(String nmDepartment) {
		this.nmDepartment = nmDepartment;
	}
}
