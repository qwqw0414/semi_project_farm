package research.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import research.model.service.ResearchService;
import research.model.vo.Research;

@WebServlet("/research/dataListByAll")
public class DataListByAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("year"));
		
		List<Research> list = new ArrayList<>();
		list = new ResearchService().selectDataListByYear(year);
		
		JSONArray jsonArr = new JSONArray();
		
		for(Research r : list) {
			JSONObject jsonResearch = new JSONObject();
			jsonResearch.put("price", r.getPrice());
			jsonResearch.put("date", r.getDate());
			
			jsonArr.add(jsonResearch);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonArr.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
