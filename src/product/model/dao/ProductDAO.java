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

import product.model.vo.Product;
import product.model.vo.WishList;

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
				p.setDiscount(rset.getInt("DISCOUNT"));
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

	public List<WishList> selectWishListByMemberId(Connection conn, String memberId) {
		List<WishList> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectWishListByMemberId");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				WishList w = new WishList();
				w.setListId(rset.getInt("listid"));
				w.setMemberId(rset.getString("memberId"));
				w.setpId(rset.getInt("pid"));
				w.setAmount(rset.getInt("amount"));

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
}
