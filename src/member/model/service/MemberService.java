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

	public Member selectByPassword(Member m) {
		Connection conn = getConnection();
		Member member = null;
		member = new MemberDAO().selectByPassword(conn, m);
		
		if(m!=null) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return member;
	}

	public int updatePassword(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().updatePassword(conn, m);
	
		if(result>0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public Member memberFindId(String memberName, String birth, String phone) {
		Connection conn = getConnection();
		
		Member m = new MemberDAO().findMemberId(conn, memberName, birth, phone);
		
		close(conn);
		
		return m;
	}

	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = new MemberDAO().deleteMember(conn, memberId);
		
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		
		return result;
	}

	public Member changeByPassword(String memberId, String password) {
		Connection conn = getConnection();
		Member m = new MemberDAO().changeByPassword(conn, memberId, password);
		if(m != null) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		return m;
	}

	public int changeUpdate(Member m, String pwd_new) {
		Connection conn = getConnection();
		int result = new MemberDAO().changeUpdate(conn,m, pwd_new);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		
		return result;

	}



	
	
	
	
}












