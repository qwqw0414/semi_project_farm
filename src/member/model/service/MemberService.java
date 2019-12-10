package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {

	public int memberLogin(String memberId, String password) {
		Connection conn = getConnection();
		int result = new MemberDAO().memberLogin(conn, memberId, password);
		close(conn);
		return result;
	}

	public int memberEnroll(Member m) {
		
		
		
		return 0;
	}

	
	
}
