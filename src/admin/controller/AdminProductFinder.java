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
		final int numPerPage = new BaseData().getPagenum();
		
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

			if ("V".equals(searchKeyword) || "채소".equals(searchKeyword)) {
				searchKeyword = "채소";
			}
			else if("F".equals(searchKeyword) || "과일".equals(searchKeyword)) {
				searchKeyword = "과일";
			}
			else if("M".equals(searchKeyword) || "버섯".equals(searchKeyword)) {
				searchKeyword = "버섯";
			}
			else if("S".equals(searchKeyword) || "해산물".equals(searchKeyword)) {
				searchKeyword = "해산물";
			}
			else if("G".equals(searchKeyword) || "육류".equals(searchKeyword)) {
				searchKeyword = "육류";
			}
			list = adminService.selectProductByCategory(searchKeyword, cPage, numPerPage);break;
		}

		//페이징바 영역
		int totalContent = 0;
		switch(searchType) {
		case "pName": totalContent= new AdminService().selectTotalContentBypName(searchKeyword); break;
		case "Category":totalContent = new AdminService().selectTotalContentByCategory(searchKeyword);break;
		}
		
		
		//totalPage구하기
				int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		
				//페이지바 html코드
				String pageBar = "";	
				//페이지바 길이
				int pageBarSize = new BaseData().getPAGEBARSIZE();
				//(공식3)시작페이지 번호 세팅
				int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
				//종료페이지 번호 세팅
				int pageEnd = pageStart+pageBarSize-1;
				int pageNo = pageStart;		

				//이전 section
				if(pageNo == 1 ){

				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"'>이전</a></li> ";
				}
				// pageNo section
				while(pageNo<=pageEnd && pageNo<=totalPage){
					if(cPage ==  pageNo ){
						pageBar += "<li class ='page-item active'><a class='page-link'>"+pageNo+"</a></li>\n";
					} 
					else {
						pageBar += "<li class='page-item'> <a class='page-link' href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a></li> ";
					}
					pageNo++;
				}
				
				//[다음]
				if(pageNo > totalPage){
					
				} else {
					
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>다음</a></li>";
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
