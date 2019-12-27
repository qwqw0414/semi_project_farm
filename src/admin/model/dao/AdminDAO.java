package admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.vo.Member;
import product.model.vo.OrderList;
import product.model.vo.OrderListProduct;
import product.model.vo.Product;
import product.model.vo.ProductIO;
import product.model.vo.ProductIOProduct;

import static common.JDBCTemplate.close;

public class AdminDAO {

	private Properties prop = new Properties();

	public AdminDAO() {
		try {
			String fileName = AdminDAO.class.getResource("/admin/admin-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int productInput(Connection conn, ProductIO pIO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ProductIOUpdate");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pIO.getpId());
			pstmt.setString(2, pIO.getMemberId());
			pstmt.setString(3, pIO.getStatus());
			pstmt.setInt(4, pIO.getAmount());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productOutput(Connection conn, ProductIO pIO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ProductIOUpdate");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, pIO.getpId());
			pstmt.setString(2, pIO.getMemberId());
			pstmt.setString(3, pIO.getStatus());
			pstmt.setInt(4, pIO.getAmount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int productReg(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("productReg");

		try {
			int cnt = 0;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++cnt, p.getCategory());
			pstmt.setString(++cnt, p.getpName());
			pstmt.setString(++cnt, p.getpInfo());
			pstmt.setInt(++cnt, p.getPrice());
			pstmt.setString(++cnt, p.getPhoto());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<Product> selectProductList(Connection conn, int cPage, int numPerPage) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectProductListByPaging");

		try {
			pstmt = conn.prepareStatement(query);
			
			 pstmt.setInt(1, (cPage-1)*numPerPage+1);
	            pstmt.setInt(2, cPage*numPerPage);
			
			
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

	public List<Member> selectAllByPaging(Connection conn, int cPage, int numPerPage ) {
		Member m = null;
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllByPaging");
		list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cPage*10-9);
			pstmt.setInt(2, numPerPage*cPage);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberId"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				
				list.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public List<Member> selectMemberByMemberId(Connection conn, String memberId, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		List<Member> list = null;
		Member m = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberIdByPaging");
		list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberId+"%");
			pstmt.setInt(2, cPage*10-9);
			pstmt.setInt(3, numPerPage*cPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberId"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				list.add(m);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	public List<Member> selectMemberByMemberName(Connection conn, String memberName, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		List<Member> list = null;
		Member m = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberNameByPaging");
		list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberName+"%");
			pstmt.setInt(2, cPage*10-9);
			pstmt.setInt(3, numPerPage*cPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberId"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				list.add(m);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public List<Member> selectMemberByMemberPhone(Connection conn, String phone, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		List<Member> list = null;
		ResultSet rset = null;
		Member m = null;
		String query = prop.getProperty("selectMemberByPhonByPaging");
		list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+phone+"%");
			pstmt.setInt(2, cPage*10-9);
			pstmt.setInt(3, numPerPage*cPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberId"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public List<Product> selectProductBypName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = null;
		String query = prop.getProperty("selectProductBypNameByPaging");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + searchKeyword + "%");
			
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();

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

	public List<Product> selectProductByCategory(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Product> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductByCategoryByPaging");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2,(cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(3, cPage*numPerPage);//end rownum
			rset = pstmt.executeQuery();
			list = new ArrayList<>();

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

	public int selectMemberTotalContent(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberTotalContent");
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
			

	public List<ProductIO> selectAllProductIO(Connection conn, int cPage, int numPerPage, String byStatus) {
		List<ProductIO> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "";
		if(byStatus==null||"All".equals(byStatus)) {
			query = prop.getProperty("selectAllProductIO");			
		} else {
			query = prop.getProperty("selectProductIOByCategory");
		}

		
		try {
			if(byStatus==null||"All".equals(byStatus)) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, (cPage-1)*numPerPage+1);//start rownum
				pstmt.setInt(2, cPage*numPerPage);//end rownum
			} else {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, byStatus);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);//start rownum
				pstmt.setInt(3, cPage*numPerPage);//end rownum
			}
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				ProductIOProduct pIO = new ProductIOProduct();
				pIO.setIoId(rset.getInt("ioid"));
				pIO.setpId(rset.getInt("pId"));
				pIO.setMemberId(rset.getString("memberId"));
				pIO.setStatus(rset.getString("status"));
				pIO.setAmount(rset.getInt("amount"));
				pIO.setIoDate(rset.getDate("iodate"));
				pIO.setpName(rset.getString("pname"));
				pIO.setpName(rset.getString("pName"));
				list.add(pIO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		

		return totalContent;
	}

	public int selectTotalContentByMemberId(Connection conn, String memberId) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberId+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

		
	
	public int selectProductIOCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductIOCount");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	public int selectProductIOCountByStatus(Connection conn, String byStatus) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductIOCountByStatus");
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, byStatus);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectTotalContentByMemberName(Connection conn, String memberName) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByMemberName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+memberName+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		return totalContent;
	}

	public int selectTotalContentBypName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentBypName");
		int totalContent = 0;

	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
				totalContent = rset.getInt("cnt");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	
	}

	public int selectTotalContentByCategory(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContentByCategory");
		int totalContent = 0;
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
	
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContent;
	}




	public List<Product> selectAllProductList(Connection conn) {
		List<Product> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllProductList");
		list = new ArrayList<>();
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
	
	


	public Product selectProductByPName(String pName, Connection conn) {
		// TODO Auto-generated method stub
		
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByPname");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pName);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
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
	
	
	
	public int updateProduct(Product p, Connection conn) {
		// TODO Auto-generated method stub
		int result = 0;
		List<Product> list = null;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateProduct");
		//System.out.println("query="+query);
		//updateProduct=update product set category=?, pname=?, pinfo=?, price=?, photo=? where pid=?
		list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getCategory());
			pstmt.setString(2, p.getpName());
			pstmt.setString(3, p.getpInfo());
			pstmt.setInt(4, p.getPrice());
			pstmt.setDouble(5, p.getDiscount());			
			pstmt.setString(6, p.getPhoto());
			pstmt.setInt(7, p.getpId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteProduct(Product p, Connection conn) {
		
		int result = 0;		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteProduct");
		//System.out.println("query="+query);
		//deleteProduct=DELETE FROM PRODUCT WHERE PID=?
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p.getpId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Product selectProductByPId(Connection conn, int pId) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductByPId");
		
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

	public List<OrderList> selectAllOrderList(Connection conn, int cPage, int numPerPage) {
		List<OrderList> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllOrderList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);//start rownum
			pstmt.setInt(2, cPage*numPerPage);//end rownum
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				OrderListProduct o = new OrderListProduct();
				o.setOrderId(rset.getInt("orderid"));
				o.setMemberId(rset.getString("memberid"));
				o.setpId(rset.getInt("pid"));
				o.setPrice(rset.getInt("price"));
				o.setAmount(rset.getInt("amount"));
				o.setZipcode(rset.getString("zipcode"));
				o.setAddress(rset.getString("address"));
				o.setOrderDate(rset.getDate("orderDate"));
				o.setCheckDate(rset.getDate("checkdate"));
				o.setStatus(rset.getString("status"));
				o.setpName(rset.getString("pname"));
				o.setPhoto(rset.getString("photo"));
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int selectOrderListCount(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int changeOrderStatus(Connection conn, int orderId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("changeOrderStatus");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public OrderList selectOrderListByOrderId(Connection conn, int orderId) {
		OrderList o = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOrderListByOrderId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				o = new OrderListProduct();
				o.setOrderId(rset.getInt("orderid"));
				o.setMemberId(rset.getString("memberid"));
				o.setpId(rset.getInt("pid"));
				o.setPrice(rset.getInt("price"));
				o.setAmount(rset.getInt("amount"));
				o.setZipcode(rset.getString("zipcode"));
				o.setAddress(rset.getString("address"));
				o.setOrderDate(rset.getDate("orderDate"));
				o.setCheckDate(rset.getDate("checkdate"));
				o.setStatus(rset.getString("status"));
				((OrderListProduct) o).setpName(rset.getString("pname"));
				((OrderListProduct) o).setPhoto(rset.getString("photo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
				
		return o;
	}

}
