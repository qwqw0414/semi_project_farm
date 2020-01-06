package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.OrderListProduct;

@WebServlet(urlPatterns="/admin/productResearch", name ="ProductResearchServlet")
public class ProductResearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<OrderListProduct> list = new ArrayList<>();
		list = new AdminService().selectAllOrderAndProduct();
		
//		일별 수익
//		List<OrderListProduct> dateIncom = new AdminService().researchDateIncom();
		
//		System.out.println(dateIncom);
		
		request.setAttribute("list",list);
//		request.setAttribute("dateIncom",dateIncom);
		request.getRequestDispatcher("/WEB-INF/views/admin/productResearch.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
