package member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static common.JDBCTemplate.close;
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
				m.setMemberId(rset.getString("memberId"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				m.setAdmin(Boolean.parseBoolean(rset.getString("admin_yn")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
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


















