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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import product.model.dao.ProductDAO;
import product.model.service.ProductService;
import product.model.vo.OrderList;

@WebServlet("/product/orderReg")
public class OrderRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		파라미터핸들링
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
//		배열 파싱처리
		JSONParser parser = new JSONParser();
		JSONArray orderArr = null;
		List<OrderList> orderList = new ArrayList<>();
		int result = 0;
		int listId = 0;
		
		try {
			orderArr = (JSONArray)parser.parse(request.getParameter("orderList"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//배열 -> 오브젝트 분리 -> order 객체 생성
		
		OrderList order = null;
		for(int i = 0; i < orderArr.size(); i++) {
			
			JSONObject obj = (JSONObject)orderArr.get(i);
			order = new OrderList();
			order.setpId(Integer.parseInt((String)obj.get("pId")));
			order.setPrice(Integer.parseInt((String)obj.get("price")));
			order.setAmount(Integer.parseInt((String)obj.get("amount")));
			order.setMemberId(memberId);
			
//			비즈니스로직
			result = new ProductService().insertOrderList(order);
			
			//장바구니 제거
			if(result > 0 && obj.get("listId")!=null) {
				listId = Integer.parseInt((String)obj.get("listId"));
				new ProductService().deleteWishList(listId);
			}else {
				//재고 부족 리턴
				
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
