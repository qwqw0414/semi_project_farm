package admin.model.service;

import product.model.vo.ProductIO;
import static common.JDBCTemplate.*;

import java.sql.Connection;

import admin.model.dao.AdminDAO;

public class AdminService {

	public int ProductInput(ProductIO pIO) {
		Connection conn = getConnection();
		System.out.println("service");
		int result = new AdminDAO().ProductInput(conn, pIO);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
