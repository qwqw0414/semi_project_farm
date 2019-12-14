package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.WishList;

/**
 * Servlet implementation class WishListInsert
 */
@WebServlet("/product/wishListInsert")
public class WishListInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int pId = Integer.parseInt(request.getParameter("pId"));
		int amount = Integer.parseInt(request.getParameter("pNum"));
		
		WishList w = new WishList();
		w.setMemberId(memberId);
		w.setpId(pId);
		w.setAmount(amount);
		
		int result = 0;
		
		if("".equals(memberId)) {
			
		}
		else {
			
			result = new ProductService().insertWishList(w);
			
		}
		
		request.getRequestDispatcher("/").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
