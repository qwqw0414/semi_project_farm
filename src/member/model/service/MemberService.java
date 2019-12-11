package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, memberId);
		close(conn);
		return m;
	}

	public int memberEnroll(Member m) {
		Connection conn = getConnection();
		
		int result =  new MemberDAO().memberEnroll(conn,m);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Member selectByPassword(String memberId, String birth) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectByPassword(conn, memberId,birth);
		if(m!=null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return m;
	}

	public int updatePassword(Member m, String pwd_new) {
		Connection conn = getConnection();
		int result = new MemberDAO().updatePassword(conn, m, pwd_new);
	
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}



	
	
	
	
}












