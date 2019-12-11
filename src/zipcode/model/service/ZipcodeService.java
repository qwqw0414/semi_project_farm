package zipcode.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import zipcode.model.dao.ZipcodeDAO;
import zipcode.model.vo.Zipcode;

public class ZipcodeService {

	public List<Zipcode> search(String searchType, String keyWord) {
		Connection conn = getConnection();
		List<Zipcode> list = new ZipcodeDAO().search(conn,searchType,keyWord);
		
		close(conn);
		
		return list;
	}

}
