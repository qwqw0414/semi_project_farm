package member.model.dao;

import static common.JDBCTemplate.close;

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
				m.setMemberId(rset.getString("memberId"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				if("Y".equals(rset.getString("admin_yn"))) {
					m.setAdmin(true);		
				} else {
					m.setAdmin(false);
				}
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

	public Member selectByPassword(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = prop.getProperty("selectByPassword");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getBirth());
			pstmt.setString(3, m.getPhone());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("memberId"));
				member.setPassword(rset.getString("password"));
				member.setMemberName(rset.getString("memberName"));
				member.setBirth(rset.getString("birth"));
				member.setPhone(rset.getString("phone"));
				member.setZipcode(rset.getString("zipcode"));
				member.setAddress(rset.getString("address"));
				member.setEnrolldate(rset.getDate("enrollDate"));
				if("Y".equals(rset.getString("admin_yn"))) {
					member.setAdmin(true);		
				} else {
					member.setAdmin(false);
				}


			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
//		System.out.println(m+"MemberDAO 333");
		return member;
	}

	public int updatePassword(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Member findMemberId(Connection conn, String memberName, String birth, String phone) {
		Member m = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("findMemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			
			System.out.println("memberName="+memberName);
			System.out.println("birth="+birth);
			System.out.println("phone="+phone);
			rset = pstmt.executeQuery();
		
			if(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberId"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("memberName"));
				m.setBirth(rset.getString("birth"));
				m.setPhone(rset.getString("phone"));
				m.setZipcode(rset.getString("zipcode"));
				m.setAddress(rset.getString("address"));
				m.setEnrolldate(rset.getDate("enrollDate"));
				m.setAdmin("Y".equals(rset.getString("admin_yn"))?true:false);
				//System.out.println("isAdmin="+rset.getString("admin_yn"));
				
			}
			System.out.println("Member@dao="+m);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return m;
	}

	public int deleteMember(Connection conn, String memberId) {
		// TODO Auto-generated method stub
		System.out.println("dao시작");
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");
		System.out.println(query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


}


















