/*
 * InsertAction.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.page;

import com.rhizome.example.entity.Employee;
import com.rhizome.example.page.base.BaseServlet;
import com.rhizome.example.service.UpdateService;
import com.rhizome.example.service.UpdateService.UPDATE_MODE;

import jakarta.servlet.annotation.WebServlet;

/**
 * 社員情報の登録を行う画面に対応したクラスです
 * 入力されたパスワードと確認パスワードが一致しているか判定します。
 * @author IT-College
 *
 */
@WebServlet(name = "insert", urlPatterns = { "/insert" })
public class InsertAction extends BaseServlet {
	@Override
	protected String getPageName() {
		return "insertConfirm";
	}

	@Override
	protected String doAction() throws Exception {

		String[] pageParam = super.getInputParameter(
				"empNm" // 0
				, "empKn" // 1
				, "mail" // 2
				, "pass" // 3
				, "passConfirm" // 4
				, "depId" // 5
				, "flg" // 6
		);
		if ("1".equals(pageParam[6])) {
			super.request.setAttribute("empNm", pageParam[0]);
			super.request.setAttribute("empKn", pageParam[1]);
			super.request.setAttribute("mail", pageParam[2]);
			super.request.setAttribute("pass", pageParam[3]);
			super.request.setAttribute("depId", pageParam[4]);
			return "insert";
		} else {

			if (!pageParam[3].equals(pageParam[4])) {
				throw new Exception("パスワードが一致していません");
			}

			Employee emp = new Employee();
			emp.setNmEmployee(pageParam[0]);
			emp.setKnEmployee(pageParam[1]);
			emp.setMailAddress(pageParam[2]);
			emp.setPassword(pageParam[3]);
			emp.setIdDepartment(Integer.parseInt(pageParam[5]));

			UpdateService service = new UpdateService();
			service.registEmployee(emp, UPDATE_MODE.INSERT);

			return "insertResult";
		}
	}
}
