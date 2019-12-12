package admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
	public int ProductInput(Connection conn, ProductIO pIO) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ProductInput");
		System.out.println(33333);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pIO.getIoId());
			pstmt.setString(2, pIO.getpId());
			pstmt.setString(3, pIO.getMemberId());
			pstmt.setString(4, pIO.getStatus());
			pstmt.setInt(5, pIO.getAmount());
			result = pstmt.executeUpdate();
			System.out.println("@adminDAO: "+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println(44444);
		return result;
	}
	public int productReg(Connection conn, Product p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("productReg");
		
		try {
			int cnt = 0;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++cnt, p.getpId());
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

}
