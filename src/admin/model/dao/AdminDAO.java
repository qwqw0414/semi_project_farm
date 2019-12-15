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
import product.model.vo.Product;
import product.model.vo.ProductIO;
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

	public List<Product> selectProdcutList(Connection conn) {
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("selectProductList");

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
		System.out.println(list+"dao33333333333");
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
	

	public List<Product> selectProductBypName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = null;
		String query = prop.getProperty("selectMemberByPname");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + searchKeyword + "%");
			rset = pstmt.executeQuery();
			list = new ArrayList<>();

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

	public List<Product> selectProductByCategory(Connection conn, String searchKeyword) {
		List<Product> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductByCategory");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();

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
			
			System.out.println("왜 아무것도 없냐고 dao"+list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectTotalContent(Connection conn) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalContent");
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





}
