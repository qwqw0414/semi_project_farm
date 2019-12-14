package product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.WishList;

@WebServlet("/product/wishListView")
public class WishListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		
		List<WishList> list = null;
		
		request.setAttribute("list", list);
		
		if(memberId == null) {
			
		}else {
			list = new ProductService().selectWishListByMemberId(memberId);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/product/wishListView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
