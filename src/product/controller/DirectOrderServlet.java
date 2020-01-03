package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.OrderList;

@WebServlet("/product/directOrder")
public class DirectOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int pId = Integer.parseInt(request.getParameter("pId"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		int price = Integer.parseInt(request.getParameter("price"));
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String detail = request.getParameter("detail");
		
		System.out.println(detail);
		
		OrderList order = new OrderList();
		order.setMemberId(memberId);
		order.setpId(pId);
		order.setAmount(amount);
		order.setPrice(price);
		order.setZipcode(zipcode);
		order.setAddress(address + " " +detail);
		
		int result = new ProductService().insertOrderListDir(order);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
