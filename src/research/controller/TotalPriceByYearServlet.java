package research.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import research.model.service.ResearchService;
import research.model.vo.Research;

@WebServlet("/research/totalPriceByYear")
public class TotalPriceByYearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("year"));
		Research r = new Research();
		
		r = new ResearchService().selectTotalPriceByYear(year);
		
		JSONObject jsonResearch = new JSONObject();
		jsonResearch.put("price", r.getPrice());
		jsonResearch.put("date", r.getDate());
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonResearch.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
