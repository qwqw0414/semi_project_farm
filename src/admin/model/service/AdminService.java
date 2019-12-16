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

	public List<Product> selectProductList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Product> list= new AdminDAO().selectProductList(conn,cPage, numPerPage);
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

	public List<Product> selectProductBypName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Product> list = null;
		list = new AdminDAO().selectProductBypName(conn, searchKeyword,cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Product> selectProductByCategory(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Product> list = new AdminDAO().selectProductByCategory(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<ProductIO> selectAllProductIO(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<ProductIO> list = new AdminDAO().selectAllProductIO(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContent() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContent(conn);
		close(conn);
		return totalContent;
	}
	public int selectMemberTotalContent() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectMemberTotalContent(conn);
		close(conn);
		return totalContent;
	
	}

	public int selectTotalContentByMemberId(String memberId) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberId(conn, memberId);
		close(conn);
		return totalContent;
	}
	public int selectProductIOCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectProductIOCount(conn);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByMemberName(String memberName) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByMemberName(conn, memberName);
		close(conn);
		return totalContent;
	}
	public int selectTotalContentBypName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentBypName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectTotalContentByCategory(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectTotalContentByCategory(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public List<Product> selectAllProductList() {
		Connection conn = getConnection();
		List<Product> list= new AdminDAO().selectAllProductList(conn);
		close(conn);
        return list;
	}
	
}








