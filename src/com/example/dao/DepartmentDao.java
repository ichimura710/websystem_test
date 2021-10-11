package com.rhizome.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rhizome.example.dao.base.BaseDao;
import com.rhizome.example.entity.Department;

public class DepartmentDao extends BaseDao<Department> {

	public DepartmentDao(Connection con) {
		super(con);
	}

	@Override
	protected String getTableName() {
		return "department";
	}

	@Override
	protected Department rowMapping(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setIdDepartment((Integer) rs.getInt("id_department"));
		department.setNmDepartment((String) rs.getString("nm_department"));
		return department;
	}

	@Override
	protected String[] getColumnsList() {
		return new String[] {
				 "id_department"
				,"nm_department"
		};
	}

	@Override
	protected String[] getPrimaryKey() {
		return new String[] {
				 "id_department"
		};
	}

}
