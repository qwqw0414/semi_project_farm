package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.WishListProduct;

@WebServlet("/product/deleteWishListBar")
public class DeleteWishListBarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("listId"));
		int result = 0;

//		장바구니 제거
		result = new ProductService().deleteWishList(listId);

//		제거후 리스트 출력
		String memberId = request.getParameter("memberId");
		List<WishListProduct> list = null;

		list = new ProductService().selectWishListByMemberId(memberId);
		request.setAttribute("list", list);

		request.getRequestDispatcher("/product/productView").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
