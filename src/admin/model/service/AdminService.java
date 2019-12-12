package admin.model.service;

import product.model.vo.Product;
import product.model.vo.ProductIO;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import admin.model.dao.AdminDAO;
import member.model.vo.Member;

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

	public int productReg(Product p) {
		Connection conn = getConnection();
		int result = 0;
		
		result = new AdminDAO().productReg(conn,p);
		
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public List<Product> selectMemberList() {
		Connection conn = getConnection();
		List<Product> list= new AdminDAO().selectProdcutList(conn);
		close(conn);
        return list;
	
	}

}
