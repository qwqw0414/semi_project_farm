package admin.model.service;

import product.model.vo.ProductIO;
import static common.JDBCTemplate.*;

import java.sql.Connection;

import admin.model.dao.AdminDAO;

public class AdminService {

	public int productInput(ProductIO pIO) {
		Connection conn = getConnection();
		int result = new AdminDAO().productInput(conn, pIO);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int productOutput(ProductIO pIO) {
		Connection conn = getConnection();
		int result = new AdminDAO().productOutput(conn, pIO);
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
