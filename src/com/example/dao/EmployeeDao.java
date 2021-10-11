package com.rhizome.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rhizome.example.dao.base.BaseDao;
import com.rhizome.example.entity.Employee;
import com.rhizome.example.entity.EmployeeInfo;
import com.rhizome.example.util.Util;

public class EmployeeDao extends BaseDao<Employee> {

	public EmployeeDao(Connection con) {
		super(con);
	}

	/**
	 * ログインで入力された情報で検索を行う
	 * @param mail
	 * @param pass
	 * @return ログイン情報に合致した社員情報
	 */
	public Employee selectLoginInfo(String mail, String pass) throws SQLException {
		Employee entity = null;
		StringBuilder sql = new StringBuilder();

		// SQLの生成
		sql.append(" SELECT * FROM " + this.getTableName());
		sql.append(" WHERE mail_address = ? AND password = ?");

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(mail, pass);

		ResultSet rs = stmt.executeQuery();

		// 主キー検索のため、0件、または1件と想定
		if (rs.next()) {
			entity = rowMapping(rs);
		}

		// 見つからなかった
		this.colseStatement();
		return entity;
	}

	/**
	 * メールアドレスでの検索を行う
	 * @param mail 検索するメールアドレス
	 * @return 検索結果
	 */
	public Employee findByMail(String mail) throws SQLException {
		Employee entity = null;
		StringBuilder sql = new StringBuilder();

		// SQLの生成
		sql.append(" SELECT * FROM " + this.getTableName());
		sql.append(" WHERE mail_address = ?");

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(mail);

		ResultSet rs = stmt.executeQuery();

		// 検索結果の取得
		if (rs.next()) {
			entity = rowMapping(rs);
		}

		this.colseStatement();
		return entity;
	}

	/**
	 * 条件検索を行う
	 * @param emp 検索条件
	 * @return 検索結果
	 */
	public List<EmployeeInfo> findByParam(Employee emp) throws SQLException {
		StringBuilder sql = new StringBuilder();

		// SQLの生成
		sql.append(" SELECT");
		sql.append("      e.id_employee");
		sql.append("     ,e.nm_employee");
		sql.append("     ,e.kn_employee");
		sql.append("     ,e.mail_address");
		sql.append("     ,e.password");
		sql.append("     ,e.id_department");
		sql.append("     ,d.nm_department");
		sql.append(" FROM");
		sql.append("     employee e INNER JOIN department d");
		sql.append("         ON e.id_department = d.id_department");

		String keyword = " WHERE ";
		List<Object> paramList = new ArrayList<>();

		if (emp.getIdEmployee() != null) {
			sql.append(keyword + " e.id_employee = ?");
			paramList.add(emp.getIdEmployee());
			keyword = " AND ";
		}

		if (!(Util.isStringEmpty(emp.getNmEmployee()))) {
			sql.append(keyword + " e.nm_employee LIKE ?");
			paramList.add("%" + emp.getNmEmployee() + "%");
			keyword = " AND ";
		}

		if (!(Util.isStringEmpty(emp.getKnEmployee()))) {
			sql.append(keyword + " e.kn_employee LIKE ?");
			paramList.add("%" + emp.getKnEmployee() + "%");
			keyword = " AND ";
		}

		if (!(Util.isStringEmpty(emp.getMailAddress()))) {
			sql.append(keyword + " e.mail_address LIKE ?");
			paramList.add("%" + emp.getMailAddress() + "%");
			keyword = " AND ";
		}

		if (emp.getIdDepartment() != null) {
			sql.append(keyword + " e.id_department = ?");
			paramList.add(emp.getIdDepartment());
			keyword = " AND ";
		}

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(paramList.toArray());

		ResultSet rs = stmt.executeQuery();

		// 検索結果の取得
		List<EmployeeInfo> empList = new ArrayList<>();
		while (rs.next()) {
			EmployeeInfo empInfo = new EmployeeInfo(rowMapping(rs));
			empInfo.setNmDepartment(rs.getString("nm_department"));
			empList.add(empInfo);
		}

		// 見つからなかった
		this.colseStatement();
		return empList;
	}

	/**
	 * 社員情報の登録を行う
	 * @param emp
	 * @return
	 */
	public int insertEmployee(Employee emp) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO " + this.getTableName());
		sql.append("(");
		sql.append("     nm_employee");
		sql.append("    ,kn_employee");
		sql.append("    ,mail_address");
		sql.append("    ,password");
		sql.append("    ,id_department");
		sql.append(") VALUES ( ?, ?, ?, ? ,? )");

		// Statementの生成および条件の設定
		this.stmt = this.con.prepareStatement(sql.toString());
		setParameter(
				 emp.getNmEmployee()
				,emp.getKnEmployee()
				,emp.getMailAddress()
				,emp.getPassword()
				,emp.getIdDepartment()
		);

		int count = this.stmt.executeUpdate();

		this.colseStatement();
		return count;
	}

	@Override
	protected String getTableName() {
		return "employee";
	}

	@Override
	protected Employee rowMapping(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setIdEmployee(rs.getInt("id_employee"));
		employee.setNmEmployee(rs.getString("nm_employee"));
		employee.setKnEmployee(rs.getString("kn_employee"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setPassword(rs.getString("password"));
		employee.setIdDepartment(rs.getInt("id_department"));
		return employee;
	}

	@Override
	protected String[] getColumnsList() {
		return new String[]{
				 "id_employee"
				,"nm_employee"
				,"kn_employee"
				,"mail_address"
				,"password"
				,"id_department"
		};
	}

	@Override
	protected String[] getPrimaryKey() {
		return new String[]{
				"id_employee"
		};
	}
}
