package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;

/**
 * Servlet implementation class AdminProductFinder
 */
@WebServlet("/admin/productFinder")
public class AdminProductFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//페이징바 파라미터 핸들링
		int cPage = 1;
		final int numPerPage = 10;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}
		
		
		
		
		
		//1.파라미터
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
	
		//2.업무로직
		List<Product>list = null;
		AdminService adminService = new AdminService();
		switch(searchType) {
		case "pName": list = adminService.selectProductBypName(searchKeyword, cPage, numPerPage); break;
		case "Category":
			if ("V".equals(searchKeyword)) {
				searchKeyword = "채소";
			}
			else {
				searchKeyword = "과일";
			}
			list = adminService.selectProductByCategory(searchKeyword, cPage, numPerPage);break;
		}
		
		
		
		
		////////////////////////////////////////////////////////////////////////////
		//노답
//		//페이징바 영역
//		int totalContent = 0;
//		switch(searchType) {
//		case "pName": list = adminService.selectTotalContentByBypName(searchKeyword); break;
//		case "Category":
//			if ("V".equals(searchKeyword)) {
//				searchKeyword = "채소";
//			}
//			else {
//				searchKeyword = "과일";
//			}
//			list = adminService.selectTotalContentByCategory(searchKeyword);break;
//		}
	///////////////////////////////////////////////////////////////////////////////////	
		
		
		
		
		
		
		
		
		
		
		
		//totalPage구하기
				int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
				System.out.println("totalMember="+totalContent+", totalPage="+totalPage);
		
				//페이지바 html코드
				String pageBar = "";	
				//페이지바 길이
				int pageBarSize = 5;
				//(공식3)시작페이지 번호 세팅
				int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
				//종료페이지 번호 세팅
				int pageEnd = pageStart+pageBarSize-1;
				System.out.println("pageStart["+pageStart+"] ~ pageEnd["+pageEnd+"]");
				int pageNo = pageStart;		

				//이전 section
				if(pageNo == 1 ){

				}
				else {
					pageBar += "<a href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"'>[이전]</a> ";
				}
				// pageNo section
				while(pageNo<=pageEnd && pageNo<=totalPage){
					if(cPage ==  pageNo ){
						pageBar += "<span class='cPage'>"+pageNo+"</span> ";
					} 
					else {
						pageBar += "<a href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a> ";
					}
					pageNo++;
				}
				
				//[다음]
				if(pageNo > totalPage){
					
				} else {
					
					pageBar += "<a href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>[다음]</a>";
				}
				
		
		
		
		
		
		//3.뷰단
		request.setAttribute("list", list);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("cPage",cPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/productFinder.jsp")
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
