package admin.model.service;

import product.model.vo.OrderList;
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

	public List<Member> selectMemberByMemberPhone(String phone, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByMemberPhone(conn, phone, cPage, numPerPage);
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

	public List<ProductIO> selectAllProductIO(int cPage, int numPerPage, String byStatus) {
		Connection conn = getConnection();
		List<ProductIO> list = new AdminDAO().selectAllProductIO(conn, cPage, numPerPage, byStatus);
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
	
	public int selectProductIOCountByStatus(String byStatus) {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectProductIOCountByStatus(conn, byStatus);
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

	

	public Product selectProductByPName(String pName) {
		// TODO Auto-generated method stub
		Product p = null;
		Connection conn = getConnection();
		p = new AdminDAO().selectProductByPName(pName, conn);
		close(conn);
		
		return p;
	}

	public int updateProduct(Product p) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();

		int result = new AdminDAO().updateProduct(p, conn);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	public int deleteProduct(Product p) {
		
		Connection conn = getConnection();

		int result = new AdminDAO().deleteProduct(p, conn);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	public Product selectProductByPId(int pId) {
		Connection conn = getConnection();
		Product p = new AdminDAO().selectProductByPId(conn, pId);
		close(conn);
		return p;
	}

	public List<OrderList> selectAllOrderList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<OrderList> list = new AdminDAO().selectAllOrderList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectOrderListCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDAO().selectOrderListCount(conn);
		close(conn);
		return totalContent;
	}

}








