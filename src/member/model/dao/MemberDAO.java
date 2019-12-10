package member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDAO {

	private Properties prop = new Properties();
	
	public MemberDAO() {
		try {
            String fileName = MemberDAO.class.getResource("/member/member-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				m = new Member();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	public int memberEnroll(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("memberEnroll");
		int result = 0;
		
		try {
			int cnt = 0;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++cnt, m.getMemberId());
			pstmt.setString(++cnt, m.getPassword());
			pstmt.setString(++cnt, m.getMemberName());
			pstmt.setString(++cnt, m.getBirth());
			pstmt.setString(++cnt, m.getPhone());
			pstmt.setString(++cnt, m.getZipcode());
			pstmt.setString(++cnt, m.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
