/*
 * EmployeeInfo.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.entity;

import com.rhizome.example.entity.base.BaseEntity;

public class EmployeeInfo implements BaseEntity {
	public static final String TITLE_NM_DEPARTMENT = "部署名";

	/** 社員情報 */
	private Employee employee;
	/** 部署名 */
	private String nmDepartment;

	public EmployeeInfo() {}
	public EmployeeInfo(Employee emp) {
		this.employee = emp;
	}

	/**
	 * 社員情報を取得します
	 * @return 社員情報
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * 社員情報を設定します
	 * @param employee 社員情報
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	/**
	 * 部署名を取得します
	 * @return
	 */
	public String getNmDepartment() {
		return nmDepartment;
	}
	/**
	 * 部署名を設定します
	 * @param nmDepartment
	 */
	public void setNmDepartment(String nmDepartment) {
		this.nmDepartment = nmDepartment;
	}
}
