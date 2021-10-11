package com.rhizome.example.page;

import java.util.Map;

import com.rhizome.example.page.base.BaseServlet;
import com.rhizome.example.service.SearchService;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name="login", urlPatterns={"/login"})
public class LoginAction extends BaseServlet {


	@Override
	protected String getPageName() {
		return "login";
	}

	@Override
	protected String doAction() throws Exception {
		// 画面入力項目の取得
		String[] param = super.getInputParameter("mail", "pass");

		// 入力チェック
		/*
		 * 必須チェックはHTML側で済みのため、Java側は特になし
		 */

		// ログイン処理
		SearchService service = new SearchService();
		Map<String, Object> loginData = service.doLogin(param[0], param[1]);

		// 結果の取得（検索結果0件）
		if (loginData == null || loginData.get("EMP") == null) {
			throw new Exception("IDまたはパスワードが間違っています");
		}

		// 取得した情報をセッションへ格納
		super.session.setAttribute("LOGIN_EMP", loginData.get("EMP"));
		super.session.setAttribute("DEP_LIST", loginData.get("DEP_LIST"));
		return "menu";
	}
}
