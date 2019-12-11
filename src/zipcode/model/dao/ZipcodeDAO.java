package zipcode.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import zipcode.model.vo.Zipcode;

public class ZipcodeDAO {

	private Properties prop = new Properties();

	public ZipcodeDAO() {
		try {
			String fileName = ZipcodeDAO.class.getResource("/zipcode/zipcode-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Zipcode> search(Connection conn, String searchType, String keyWord) {
		List<Zipcode> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		
		switch (searchType) {
		case "1": query = prop.getProperty("searchsido"); break;
		case "2": query = prop.getProperty("searchsigungu"); break;
		case "3": query = prop.getProperty("searchmyun"); break;
		case "4": query = prop.getProperty("searchroad"); break;

		}
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyWord+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Zipcode z = new Zipcode();
				z.setCode(rset.getString("code"));
				z.setSido(rset.getString("sido"));
				z.setSigungu(rset.getString("sigungu"));
				z.setMyun(rset.getString("myun"));
				z.setRo(rset.getString("road"));
				z.setName(rset.getString("name"));
				list.add(z);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
            close(rset);
            close(pstmt);
        }
		
		
		return list;
	}

}
