/*
 * TargetSearchAction.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.page;

import com.rhizome.example.entity.Employee;
import com.rhizome.example.page.base.BaseServlet;
import com.rhizome.example.service.SearchService;

import jakarta.servlet.annotation.WebServlet;

/**
 * 社員IDから情報を取得して、次の画面に値を渡すためのクラスです。
 * 今回の対象であれば、「削除→削除対象確認」や「更新→更新内容入力」などです。
 * @author IT-College
 *
 */
@WebServlet(name="targetSearch", urlPatterns={"/targetSearch"})
public class TargetSearchAction extends BaseServlet {
	@Override
	protected String getPageName() {
		String page = super.getInputParameter("page")[0];
		return "update".equals(page) ? "update" : "delete";
	}
	@Override
	protected String doAction() throws Exception {
		String[] pageParam = super.getInputParameter(
				 "empId"		// 0
				,"page"			// 1
		);

		int empId = -1;
		try {
			empId = Integer.parseInt(pageParam[0]);
		} catch (NumberFormatException e) {
			throw new Exception("入力された社員IDで社員情報が見つかりませんでした");
		}

		SearchService service = new SearchService();
		Employee employee = service.searchEmployeeByPkey(empId);

		if (employee == null) {
			throw new Exception("入力された社員IDで社員情報が見つかりませんでした");
		}
		super.request.setAttribute("empId", employee.getIdEmployee());
		super.request.setAttribute("empNm", employee.getNmEmployee());
		super.request.setAttribute("empKn", employee.getKnEmployee());
		super.request.setAttribute("mail", employee.getMailAddress());
		super.request.setAttribute("passOld", employee.getPassword());
		super.request.setAttribute("depId", employee.getIdDepartment());

		return "update".equals(pageParam[1]) ? "updateInput" : "deleteConfirm";
	}
}
