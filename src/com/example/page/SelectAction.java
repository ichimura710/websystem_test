package com.rhizome.example.page;

import java.util.List;

import com.rhizome.example.entity.Employee;
import com.rhizome.example.entity.EmployeeInfo;
import com.rhizome.example.page.base.BaseServlet;
import com.rhizome.example.service.SearchService;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "select", urlPatterns = { "/select" })
public class SelectAction extends BaseServlet {

	@Override
	protected String getPageName() {
		return "select";
	}

	@Override
	protected String doAction() throws Exception {
		String[] pageParam = super.getInputParameter(
				"empId" // 0
				, "empNm" // 1
				, "empKn" // 2
				, "mail" // 3
				, "depId" // 4
		);

		// 入力チェック
		/*
		 * IDが数値でない場合は検索しなくても0件
		 */
		if (pageParam[0].trim().length() > 0) {
			try {
				Integer.parseInt(pageParam[0]);
			} catch (NumberFormatException e) {
				throw new Exception("入力された条件で情報が見つかりませんでした");
			}
		}

		// 画面入力値 -> DTOへ
		Employee employee = new Employee();
		if (!"".equals(pageParam[0])) {
			employee.setIdEmployee(Integer.parseInt(pageParam[0]));
		}
		employee.setNmEmployee(pageParam[1]);
		employee.setKnEmployee(pageParam[2]);
		employee.setMailAddress(pageParam[3]);
		if (!"".equals(pageParam[4])) {
			employee.setIdDepartment(Integer.parseInt(pageParam[4]));
		}

		// 検索を行う
		SearchService service = new SearchService();
		List<EmployeeInfo> empInfoList = service.searchEmployeeInfo(employee);

		// 検索結果の判定
		try {
			if (empInfoList.size() == 0) {
			}
		} catch (NullPointerException e) {

			throw new Exception("入力された条件で情報が見つかりませんでした");
		}

		// 取得した結果をセット
		super.request.setAttribute("empInfoList", empInfoList);
		return "selectResult";
	}

}
