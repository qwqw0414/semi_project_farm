package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;

/**
 * Servlet implementation class AdminProductFinder
 */
@WebServlet("/admin/productFinder")
public class AdminProductFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.파라미터
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
	
		//2.업무로직
		List<Product>list = null;
		AdminService adminService = new AdminService();
	
		switch(searchType) {
		case "pName": list = adminService.selectProductBypName(searchKeyword); break;
//		case "Category": list = adminService.selectMemberByCategory(searchKeyword); break;
		}
		
		//3.뷰단
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/productFinder.jsp")
		   .forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
