/**
 * 项目名：student
 * 修改历史：
 * 作者： MZ
 * 创建时间： 2016年1月6日-上午10:04:37
 */
package com.up.student.base;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.up.student.DAO;
import com.up.student.dao.AdminDAO;
import com.up.student.dao.StudentDAO;
import com.up.student.util.DBUtil;

/**
 * 模块说明： DAO基类
 * 
 */
public abstract class BaseDAO {
	protected final DBUtil db = DBUtil.getDBUtil();
	protected ResultSet rs;
	private static BaseDAO baseDAO;

	public BaseDAO() {
		init();
	}

	private void init() {
		// buildAbilityDAO();
	}

	// protected abstract void buildAbilityDAO();

	public static synchronized BaseDAO getAbilityDAO(DAO dao) {
		switch (dao) {
		case AdminDAO:
			if (baseDAO == null || baseDAO.getClass() != AdminDAO.class) {
				baseDAO = AdminDAO.getInstance();
			}
			break;
		case StudentDAO:
			if (baseDAO == null || baseDAO.getClass() != StudentDAO.class) {
				baseDAO = StudentDAO.getInstance();
			}
			break;
		default:
			break;
		}
		return baseDAO;
	}

	protected void destroy() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
