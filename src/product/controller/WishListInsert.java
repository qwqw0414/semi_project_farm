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

import common.BaseData;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.WishList;
import product.model.vo.WishListProduct;

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
		JSONArray jsonArray = new JSONArray();
		
		List<WishListProduct> wishList = new ProductService().selectWishListByMemberId(memberId);
		
		for(WishListProduct wp : wishList) {
			JSONObject jsonWishList = new JSONObject();
			jsonWishList.put("pName", wp.getpName());
			jsonWishList.put("amount", wp.getAmount());
			jsonWishList.put("pId", wp.getpId());
			jsonWishList.put("photo", wp.getPhoto());
			jsonArray.add(jsonWishList);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonArray.toString());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
