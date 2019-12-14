package product.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ProductDAO;
import product.model.vo.Product;
import product.model.vo.WishList;
import product.model.vo.WishListProduct;

public class ProductService {
	
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
	
}
