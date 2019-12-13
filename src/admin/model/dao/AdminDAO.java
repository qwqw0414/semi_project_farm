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
		System.out.println(p);
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
		List<Product>list = new ArrayList<>();
		PreparedStatement pstmt = null;
        ResultSet rset = null;
        
		String query=prop.getProperty("selectProductList");
		
		 try {
			pstmt = conn.prepareStatement(query);
			 rset = pstmt.executeQuery();
			 
			 while(rset.next()) {
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
			 System.out.println(list);
			 
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
            close(rset);
            close(pstmt);
        }
        
        
        return list;
	}
	public List<Member> selectAll(Connection conn) {
		Member m = null;
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAll");
		list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(query);
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
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

}








