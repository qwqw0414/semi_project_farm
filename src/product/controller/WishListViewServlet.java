package product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import product.model.service.ProductService;
import product.model.vo.WishList;
import product.model.vo.WishListProduct;

@WebServlet("/product/wishListView")
public class WishListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<WishListProduct> list = null;
		String memberId = request.getParameter("memberId");

		//json 처리
		list = new ProductService().selectWishListByMemberId(memberId);
		JSONArray jsonArray = new JSONArray();
		
		for(WishListProduct wp : list) {
			JSONObject jsonWishList = new JSONObject();
			jsonWishList.put("pName", wp.getpName());
			jsonWishList.put("amount", wp.getAmount());
			jsonWishList.put("pId", wp.getpId());
			jsonWishList.put("listId", wp.getListId());
			jsonWishList.put("photo", wp.getPhoto());
			jsonWishList.put("listId", wp.getListId());
			jsonWishList.put("price", wp.getPrice());
			jsonWishList.put("discount", wp.getDiscount());
			jsonArray.add(jsonWishList);
		}

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonArray.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
