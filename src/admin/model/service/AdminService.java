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

	public List<Product> selectProductList() {
		Connection conn = getConnection();
		List<Product> list= new AdminDAO().selectProdcutList(conn);
		close(conn);
        return list;
	
	}

	public List<Member> selectAllByPaging(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectAllByPaging(conn, cPage, numPerPage);
		
		close(conn);
		return list;
		
	}
	public List<Member> selectMemberByMemberId(String memberId, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberId(conn, memberId, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberName(String memberName, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberName(conn, memberName, cPage, numPerPage);
		close(conn);
		return list;
		
	}

	

	public List<Product> selectProductBypName(String searchKeyword) {
		List<Product> list = null;
		Connection conn = getConnection();
		list = new AdminDAO().selectProductBypName(conn, searchKeyword);
		close(conn);
		return list;
	}

	public List<Product> selectProductByCategory(String searchKeyword) {
		Connection conn = getConnection();
		List<Product> list = new AdminDAO().selectProductByCategory(conn, searchKeyword);
		close(conn);
		return list;
	}

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberId(String memberId) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberId(conn, memberId);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberName(String memberName) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberName(conn, memberName);
		close(conn);
		return totalContent;
	}


}






