package research.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import research.model.dao.ResearchDAO;
import research.model.vo.Research;

public class ResearchService {

	public Research selectTotalPriceByMonth(int year, int month) {
		//월간 총 수익
		Connection conn = getConnection();
		Research r = new ResearchDAO().selectTotalPriceByMonth(conn, year, month);
		close(conn);
		return r;
	}

	public Research selectTotalPriceByYear(int year) {
		//년간 총 수익
		Connection conn = getConnection();
		Research r = new ResearchDAO().selectTotalPriceByYear(conn, year);
		close(conn);
		return r;
	}

	public List<Research> selectDataListByYear(int year) {
		//월간 수익 그래프
		Connection conn = getConnection();
		List<Research> list = new ResearchDAO().selectDataListByYear(conn, year);
		close(conn);
		return list;
	}

	public List<Research> selectDataListByDay(int year, int month) {
		//일간 수익 그래프
		Connection conn = getConnection();
		List<Research> list = new ResearchDAO().selectDataListByDay(conn, year, month);
		close(conn);
		return list;
	}

	public List<Research> selectDataListByYearSetCate(int year) {
		//카테고리별 년간 수익 그래프
		Connection conn = getConnection();
		List<Research> list = new ResearchDAO().selectDataListByYearSetCate(conn, year);
		close(conn);
		return list;
	}

	public List<Research> selectDataListByMonthSetCate(int year, int month) {
		//카테고리별 월간 수익 그래프
		Connection conn = getConnection();
		List<Research> list = new ResearchDAO().selectDataListByMonthSetCate(conn, year, month);
		close(conn);
		return list;
	}

}
