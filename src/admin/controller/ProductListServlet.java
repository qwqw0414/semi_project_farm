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
import product.model.vo.Product;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/admin/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//페이징바 처리
		int cPage = 1;//초기값 설정
		final int numPerPage = new BaseData().getPagenum(); 
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		} catch(NumberFormatException e) {
			
		}
		
		int totalContent = new AdminService().selectTotalContent();
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		System.out.printf("totalContent=%s, totalPage=%s%n", totalContent, totalPage);
		
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();   //테스트 페이징바에 2개만 표시  
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize + 1;
		int pageEnd = pageStart+pageBarSize-1;
		
		int pageNo = pageStart;
		
		
		
		//1.이전
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link'href='"+request.getContextPath()+"/admin/productList?cPage="+(pageNo-1)+"'>≪</a></li>\n";
		}
		
		//2.pageNo
		while(pageNo<=pageEnd && pageNo<=totalPage) {
			//현재페이지인 경우
			if(cPage == pageNo) {
				pageBar += "<li class ='page-item active'><a class='page-link'>"+pageNo+"</a></li>\n";
			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productList?cPage="+pageNo+"'>"+pageNo+"</a></li>\n";				
			}
			
			pageNo++;
		}
		
		//3.다음
		if(pageNo <= totalPage) {
			pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productList?cPage="+pageNo+"'>≫</a></li>\n";							
		}
		
		
		
			
		List<Product>list = new AdminService().selectProductList(cPage, numPerPage);
		request.setAttribute("list", list);
		
		request.setAttribute("pageBar", pageBar);
	
		request.getRequestDispatcher("/WEB-INF/views/admin/productList.jsp")
	   .forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
