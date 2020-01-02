package research.model.dao;

import static common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import research.model.vo.Research;


public class ResearchDAO {
	private Properties prop = new Properties();

	public ResearchDAO() {
		try {
			String fileName = ResearchDAO.class.getResource("/research/research-query.properties").getPath();
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Research selectTotalPriceByMonth(Connection conn, int year, int month) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Research r = null;
		String query = prop.getProperty("selectTotalPriceByMonth");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setString(3, "Y");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Research();
				r.setPrice(rset.getInt("total"));
				r.setDate(year+"|"+month);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public Research selectTotalPriceByYear(Connection conn, int year) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Research r = null;
		String query = prop.getProperty("selectTotalPriceByYear");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setString(2, "Y");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				r = new Research();
				r.setPrice(rset.getInt("total"));
				r.setDate(""+year);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return r;
	}

	public List<Research> selectDataListByYear(Connection conn, int year) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Research> list = new ArrayList<>();
		Research r = null;
		String query = prop.getProperty("selectDataListByYear");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setString(2, "Y");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new Research();
				r.setPrice(rset.getInt("total"));
				r.setDate(rset.getString("date_"));
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Research> selectDataListByDay(Connection conn, int year, int month) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Research> list = new ArrayList<>();
		Research r = null;
		String query = prop.getProperty("selectDataListByDay");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setString(3, "Y");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new Research();
				r.setPrice(rset.getInt("total"));
				r.setDate(rset.getString("date_"));
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Research> selectDataListByYearSetCate(Connection conn, int year) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Research> list = new ArrayList<>();
		Research r = null;
		String query = prop.getProperty("selectDataListByYearSetCate");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, year);
			pstmt.setString(2, "Y");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				r = new Research();
				r.setPrice(rset.getInt("total"));
				r.setDate(rset.getString("month_"));
				r.setCategory(rset.getString("category"));
				list.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
