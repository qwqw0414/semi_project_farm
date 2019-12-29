package product.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.vo.Comments;
import product.model.vo.OrderList;
import product.model.vo.Product;
import product.model.vo.WishList;
import product.model.vo.WishListProduct;

public class ProductDAO {

	private Properties prop = new Properties();

	public ProductDAO() {
		try {
			String fileName = ProductDAO.class.getResource("/product/product-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> selectProduct(Connection conn, String keyWord, int cPage, int numPerPage) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProduct");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyWord+"%");
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				Product p = new Product();
				p.setpId(rset.getInt("PID"));
				p.setCategory(rset.getString("CATEGORY"));
				p.setpName(rset.getString("PNAME"));
				p.setpInfo(rset.getString("PINFO"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDiscount(rset.getDouble("DISCOUNT"));
				p.setStock(rset.getInt("STOCK"));
				p.setPhoto(rset.getString("PHOTO"));
				list.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Product> selectAll(Connection conn) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectAll");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				p.setpId(rset.getInt("PID"));
				p.setCategory(rset.getString("CATEGORY"));
				p.setpName(rset.getString("PNAME"));
				p.setpInfo(rset.getString("PINFO"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDiscount(rset.getDouble("DISCOUNT"));
				p.setStock(rset.getInt("STOCK"));
				p.setPhoto(rset.getString("PHOTO"));

				list.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<WishListProduct> selectWishListByMemberId(Connection conn, String memberId) {
		List<WishListProduct> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectWishListByMemberId");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				WishListProduct w = new WishListProduct();
				w.setListId(rset.getInt("listid"));
				w.setMemberId(rset.getString("memberId"));
				w.setpId(rset.getInt("pid"));
				w.setAmount(rset.getInt("amount"));
				w.setpName(rset.getString("pname"));
				w.setPrice(rset.getInt("price"));
				w.setStock(rset.getInt("stock"));
				w.setDiscount(rset.getDouble("discount"));
				w.setPhoto(rset.getString("photo"));
				list.add(w);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertWishList(Connection conn, WishList w) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertWishList");
		int result = 0;
		
		try {
			int cnt = 0;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++cnt, w.getMemberId());
			pstmt.setInt(++cnt, w.getpId());
			pstmt.setInt(++cnt, w.getAmount());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteWishList(Connection conn, int listId) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteWishList");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, listId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countProductByName(Connection conn, String keyWord) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("countProductByName");
		int total = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyWord+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				total = rset.getInt("cnt");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return total;
	}

	public Product selectOneByPId(Connection conn, int pId) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOneByPId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				p = new Product();
				p.setpId(rset.getInt("PID"));
				p.setCategory(rset.getString("CATEGORY"));
				p.setpName(rset.getString("PNAME"));
				p.setpInfo(rset.getString("PINFO"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDiscount(rset.getDouble("DISCOUNT"));
				p.setStock(rset.getInt("STOCK"));
				p.setPhoto(rset.getString("PHOTO"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return p;
	}

	public int orderAll(Connection conn, List<OrderList> orderList) {
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("orderAll");
		int result = 0;
		
		try {
			for(OrderList ol : orderList) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, ol.getMemberId());
				pstmt.setInt(2, ol.getpId());
				pstmt.setInt(3, ol.getPrice());
				pstmt.setInt(4, ol.getAmount());
				pstmt.setString(5, ol.getMemberId());
				pstmt.setString(6, ol.getMemberId());
				result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertOrderList(Connection conn, OrderList list) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertOrderList");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, list.getMemberId());
			pstmt.setInt(2, list.getpId());
			pstmt.setInt(3, list.getPrice());
			pstmt.setInt(4, list.getAmount());
			pstmt.setString(5, list.getMemberId());
			pstmt.setString(6, list.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int productInsertComment(Connection conn, int pId, String memberId, String commentContent) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("productInsertComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, pId);
			pstmt.setString(3, commentContent);
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Comments> selectCommentsByPId(Connection conn, int pId) {
		Comments c = null;
		List<Comments> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentsByPId");
		
		try {
			list = new ArrayList<>();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				c = new Comments();
				c.setCommentId(rset.getInt("commentid"));
				c.setMemberId(rset.getString("memberid"));
				c.setpId(rset.getInt("pid"));
				c.setCommentContent(rset.getString("comments"));
				c.setCommentDate(rset.getDate("comment_date"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}
