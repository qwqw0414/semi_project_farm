package product.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import admin.model.dao.AdminDAO;
import product.model.dao.ProductDAO;
import product.model.vo.Comments;
import product.model.vo.OrderList;
import product.model.vo.Product;
import product.model.vo.WishList;
import product.model.vo.WishListProduct;

public class ProductService {
	
	public List<Product> selectProduct(String keyWord, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Product> list = null;
		list = new ProductDAO().selectProduct(conn, keyWord, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Product> selectAll(){
		Connection conn = getConnection();
		List<Product> list = null;
				
		list = new ProductDAO().selectAll(conn);
		
		close(conn);
		return list;
	}

	public List<WishListProduct> selectWishListByMemberId(String memberId) {
		Connection conn = getConnection();
		List<WishListProduct> list = null;
		
		list = new ProductDAO().selectWishListByMemberId(conn, memberId);
		
		close(conn);
		return list;
	}

	public int insertWishList(WishList w) {
		Connection conn = getConnection();
		int result = 0;
		
		result = new ProductDAO().insertWishList(conn,w);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		return result;
	}

	public int deleteWishList(int listId) {
		Connection conn = getConnection();
		int result = 0;
		
		result = new ProductDAO().deleteWishList(conn,listId);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int countProductByName(String keyWord) {
		Connection conn = getConnection();
		int total = new ProductDAO().countProductByName(conn, keyWord);
		close(conn);
		return total;
	}

	public Product selectOneByPId(int pId) {
		Connection conn = getConnection();
		Product p = new ProductDAO().selectOneByPId(conn, pId);
		close(conn);
		return p;
	}

	public int orderAll(List<OrderList> orderList,List<Integer> listId) {
		Connection conn = getConnection();
		int result = 0;
		ProductDAO pd = new ProductDAO();
		
		result = pd.orderAll(conn, orderList);
		
		if(result > 0) {
			//장바구니 제거
			if(listId != null && listId.size()>0) {
				for(int id : listId) {
					pd.deleteWishList(conn, id);
				}
			}
			commit(conn);
		}
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int insertOrderList(OrderList list) {
		Connection conn = getConnection();
		int result = 0;
		result = new ProductDAO().insertOrderList(conn, list);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int productInsertComment(int pId, String memberId, String commentContent) {
		Connection conn = getConnection();
		int result = new ProductDAO().productInsertComment(conn, pId, memberId, commentContent);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<Comments> selectCommentsByPId(int pId) {
		Connection conn = getConnection();
		List<Comments> list = new ProductDAO().selectCommentsByPId(conn, pId);
		close(conn);
		return list;
	}

	public int deleteComment(int commentId) {
		Connection conn = getConnection();
		int result = new ProductDAO().deleteComment(conn, commentId);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

}
