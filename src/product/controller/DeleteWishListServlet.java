package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import product.model.service.ProductService;
import product.model.vo.WishListProduct;

@WebServlet("/product/deleteWishList")
public class DeleteWishListServlet extends HttpServlet {
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

		//json 처리
		list = new ProductService().selectWishListByMemberId(memberId);
		JSONArray jsonArray = new JSONArray();
		
		for(WishListProduct wp : list) {
			JSONObject jsonWishList = new JSONObject();
			jsonWishList.put("pName", wp.getpName());
			jsonWishList.put("amount", wp.getAmount());
			jsonWishList.put("pId", wp.getpId());
			jsonWishList.put("photo", wp.getPhoto());
			jsonWishList.put("listId", wp.getListId());
			jsonWishList.put("price", wp.getPrice());
			jsonArray.add(jsonWishList);
		}

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
