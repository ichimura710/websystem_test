/*
 * MenuAction.java
 *   作成	IT-College	2016
 *------------------------------------------------------------
 * Copyright(c) Rhizome Inc. All Rights Reserved.
 */
package com.rhizome.example.page;

import com.rhizome.example.page.base.BaseServlet;

import jakarta.servlet.annotation.WebServlet;

/**
 * メニュー（トップ）画面から何かしらの処理を行うためのクラスです。
 * 今回の対象システムでは不要ですが、SPAなどを作成する場合はまとめましょう
 * @author IT-College
 *
 */
@WebServlet(name="menu", urlPatterns={"/menu"})
public class MenuAction extends BaseServlet {
	@Override
	protected String getPageName() {
		return "menu";
	}

	@Override
	protected String doAction() throws Exception {
		return this.getPageName();
	}
}
