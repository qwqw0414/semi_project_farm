package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import product.model.service.ProductService;

@WebServlet("/product/wishListCount")
public class WishListCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		int result = new ProductService().countWishList(memberId);
		
		JSONObject jsonResult = new JSONObject();
		
		jsonResult.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonResult.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
