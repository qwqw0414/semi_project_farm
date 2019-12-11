package zipcode.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zipcode.model.service.ZipcodeService;
import zipcode.model.vo.Zipcode;

@WebServlet("/zipcode/search")
public class ZipcodeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String keyWord = request.getParameter("keyWord");
		
		if(keyWord != null) {
			
			List<Zipcode> list = new ZipcodeService().search(searchType,keyWord);
			
			request.setAttribute("list", list);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/member/zipcode.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
