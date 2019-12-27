package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.model.service.AdminService;
import common.BaseData;
import member.model.vo.Member;
import product.model.vo.OrderList;
import product.model.vo.OrderListProduct;

/**
 * Servlet implementation class AdminChangeOrderStatus
 */
@WebServlet("/admin/changeOrderStatus")
public class AdminChangeOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
//		int cPage = 1;
//		try {
//			cPage = Integer.parseInt(request.getParameter("cPage"));			
//		}catch(NumberFormatException e) {
//			
//		}
//		int numPerPage = new BaseData().getPagenum();
//		System.out.println("Change@="+orderId);
		AdminService as = new AdminService();
		int result = as.chageOrderStatus(orderId);
		OrderList o = as.selectOrderListByOrderId(orderId);
//		System.out.println(result+"=Change");
//		List<OrderList> list = as.selectAllOrderList(cPage, numPerPage);
//		int totalContent = as.selectOrderListCount();
//		int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);
//		String pageBar = "";
//		int pageBarSize = new BaseData().getPAGEBARSIZE(); 
//		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
//		int pageEnd = pageStart+pageBarSize-1;
//		int pageNo = pageStart;
		//페이징바 처리
		//[이전] section
//		if(pageNo == 1 ){
//			//pageBar += "<span>[이전]</span>"; 
//		}
//		else {
//			pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+(pageNo-1)+"'>[이전]</a> ";
//		}
//		
//		// pageNo section
//		while(pageNo<=pageEnd && pageNo<=totalPage){
//			
//			if(cPage == pageNo ){
//				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
//			} 
//			else {
//				pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+pageNo+"'>"+pageNo+"</a> ";
//			}
//			pageNo++;
//		}
//		
//		//[다음] section
//		if(pageNo > totalPage){
//			
//		} else {
//			pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+pageNo+"'>[다음]</a>";
//		}
		//JSON처리
		JSONArray jsonArray = new JSONArray();

		JSONObject jsonOrderList = new JSONObject();
		jsonOrderList.put("orderId", o.getOrderId());
		jsonOrderList.put("memberId", o.getMemberId());
		jsonOrderList.put("pId", o.getpId());
		jsonOrderList.put("price", o.getPrice());
		jsonOrderList.put("amount", o.getAmount());
		jsonOrderList.put("zipcode", o.getZipcode());
		jsonOrderList.put("address", o.getAddress());
		jsonOrderList.put("orderDate", o.getOrderDate().toString());
		if(o.getCheckDate()!=null) {
			jsonOrderList.put("checkDate", o.getCheckDate().toString());
			
		}else {
			jsonOrderList.put("checkDate", null);
		}
		jsonOrderList.put("status", o.getStatus());
		jsonOrderList.put("pName", ((OrderListProduct) o).getpName());
		jsonArray.add(jsonOrderList);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonArray.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
