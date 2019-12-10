package member.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.dao.MemberDAO;

public class MemberService {

	public int memberLogin(String memberId, String password) {
		Connection conn = getConnection();
		int result = new MemberDAO().memberLogin(conn, memberId, password);
		close(conn);
		return result;
	}

	
	
}
