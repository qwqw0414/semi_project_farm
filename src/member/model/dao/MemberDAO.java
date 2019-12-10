package member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class MemberDAO {

	private Properties prop = new Properties();
	
	public MemberDAO() {
		try {
            //클래스객체 위치찾기 : 절대경로를 반환한다. 
            String fileName = MemberDAO.class.getResource("/member/member-query.properties").getPath();
            prop.load(new FileReader(fileName));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public int memberLogin(Connection conn, String memberId, String password) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		return 0;
	}

}
