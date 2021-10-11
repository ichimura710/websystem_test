/*
 * DeleteAction.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.page;

import com.rhizome.example.page.base.BaseServlet;
import com.rhizome.example.service.UpdateService;

import jakarta.servlet.annotation.WebServlet;

/**
 * 社員情報削除を行う画面に対応したページクラスです
 * @author IT-College
 *
 */
@WebServlet(name="delete", urlPatterns={"/delete"})
public class DeleteAction extends BaseServlet {
	@Override
	protected String getPageName() {
		return "deleteConfirm";
	}

	@Override
	protected String doAction() throws Exception {

		String[] pageParam = super.getInputParameter(
				 "empId"		// 0
		);

		UpdateService service = new UpdateService();
		service.deleteEmployee(Integer.parseInt(pageParam[0]));

		return "deleteResult";
	}

}
