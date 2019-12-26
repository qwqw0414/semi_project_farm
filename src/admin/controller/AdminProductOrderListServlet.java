package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import common.BaseData;
import product.model.vo.OrderList;

/**
 * Servlet implementation class AdminProductOrderListServlet
 */
@WebServlet("/admin/productOrderList")
public class AdminProductOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		final int numPerPage = new BaseData().getPagenum();
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}
		AdminService as = new AdminService();
		List<OrderList> list = as.selectAllOrderList(cPage, numPerPage);
		int totalContent = as.selectOrderListCount();
		int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE(); 
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		//페이징바 처리
		//[이전] section
		if(pageNo == 1 ){
			//pageBar += "<span>[이전]</span>"; 
		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+(pageNo-1)+"'>[이전]</a> ";
		}
		
		// pageNo section
		while(pageNo<=pageEnd && pageNo<=totalPage){
			
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+pageNo+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//[다음] section
		if(pageNo > totalPage){
			
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/productOrderList?cPage="+pageNo+"'>[다음]</a>";
		}
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/orderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
