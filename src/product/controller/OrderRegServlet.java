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
		List<Integer> listId = new ArrayList<>();
		List<OrderList> orderList = new ArrayList<>();
		
		try {
			orderArr = (JSONArray)parser.parse(request.getParameter("orderList"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//배열 -> 오브젝트 분리 -> order 객체 생성
		
		OrderList o = null;
		for(int i = 0; i < orderArr.size(); i++) {
			
			JSONObject obj = (JSONObject)orderArr.get(i);
			o = new OrderList();
			o.setpId(Integer.parseInt((String)obj.get("pId")));
			o.setPrice(Integer.parseInt((String)obj.get("price")));
			o.setAmount(Integer.parseInt((String)obj.get("amount")));
			o.setMemberId(memberId);
			
			orderList.add(o);
			
			if(obj.get("listId")!=null)
				listId.add(Integer.parseInt((String)obj.get("listId")));
			
		}
		
//		비즈니스로직
		int result = new ProductService().orderAll(orderList,listId);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
