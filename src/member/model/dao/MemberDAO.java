package member.model.dao;

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

import common.CommonException;
import member.model.vo.Member;
import product.model.vo.OrderList;
import product.model.vo.OrderListProduct;
import product.model.vo.Product;

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
				m.setDetail(rset.getString("detail"));
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
			pstmt.setString(++cnt, m.getDetail());
			
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
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");
		System.out.println(query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			//System.out.println("result@dao="+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Member changeByPassword(Connection conn, String memberId, String password) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = prop.getProperty("changeByPassword");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, password);
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

	public int changeUpdate(Connection conn, Member m, String pwd_new) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("changeUpdate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd_new);
			pstmt.setString(2, m.getMemberId());
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int memberUpdate(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("memberUpdate");
		int result = 0;
		
		try {
			int cnt = 0;
			pstmt = conn.prepareStatement(query);
			pstmt.setString(++cnt, m.getMemberName());
			pstmt.setString(++cnt, m.getBirth());
			pstmt.setString(++cnt, m.getPhone());
			pstmt.setString(++cnt, m.getZipcode());
			pstmt.setString(++cnt, m.getAddress());
			pstmt.setString(++cnt, m.getDetail());
			pstmt.setString(++cnt, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<OrderList> selectOrderList(Connection conn, String memberId, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		List<OrderList> list = new ArrayList<>();
		String query = prop.getProperty("selectOrderList");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, cPage*10-9);
			pstmt.setInt(3, numPerPage*cPage);
			rset = pstmt.executeQuery();

			
			while(rset.next()) {
				OrderListProduct o = new OrderListProduct();
				o.setOrderId(rset.getInt("ORDERID"));
				o.setMemberId(rset.getString("MEMBERID"));
				o.setpId(rset.getInt("PID"));
				o.setPrice(rset.getInt("PRICE"));
				o.setAmount(rset.getInt("AMOUNT"));
				o.setZipcode(rset.getString("ZIPCODE"));
				o.setAddress(rset.getString("ADDRESS"));
				o.setOrderDate(rset.getDate("ORDERDATE"));
				o.setCheckDate(rset.getDate("CHECKDATE"));
				o.setStatus(rset.getString("STATUS"));
				o.setpName(rset.getString("PNAME"));
				o.setPhoto(rset.getString("PHOTO"));
				
				list.add(o);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	public int orderTotalContent(Connection conn, String memberId) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("orderTotalContent");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(totalContent+"dao11");

		return totalContent;
	}

	
	
	
	public int memberIdCheck(Connection conn, String memberId) {
		int result=0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("memberIdCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
					result=rset.getInt("cnt");
			}
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public List<OrderList> selectOrderByDate(Connection conn, String memberId, int year, int month, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		List<OrderList> list = new ArrayList<>();
		String query = prop.getProperty("selectOrderByDate");
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
			pstmt.setInt(4, cPage*10-9);
			pstmt.setInt(5, numPerPage*cPage);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				OrderListProduct o = new OrderListProduct();
				o.setOrderId(rset.getInt("ORDERID"));
				o.setMemberId(rset.getString("MEMBERID"));
				o.setpId(rset.getInt("PID"));
				o.setPrice(rset.getInt("PRICE"));
				o.setAmount(rset.getInt("AMOUNT"));
				o.setZipcode(rset.getString("ZIPCODE"));
				o.setAddress(rset.getString("ADDRESS"));
				o.setOrderDate(rset.getDate("ORDERDATE"));
				o.setCheckDate(rset.getDate("CHECKDATE"));
				o.setStatus(rset.getString("STATUS"));
				o.setpName(rset.getString("PNAME"));
				o.setPhoto(rset.getString("PHOTO"));
				
				list.add(o);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int ordertotalContentByDate(Connection conn, String memberId, int year, int month) {
		int totalContent = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("ordertotalContentByDate");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
			
			rset = pstmt.executeQuery();

			if(rset.next()) {
				totalContent = rset.getInt("cnt");
				System.out.println(totalContent+"daodaodao");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(totalContent+"dao11");

		return totalContent;
	}


	
	
	
	
	
	
	
	
	
	
	
}


















