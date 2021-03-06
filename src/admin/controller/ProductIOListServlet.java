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
import product.model.vo.ProductIO;

/**
 * Servlet implementation class ProductIOListServlet
 */
@WebServlet(urlPatterns="/admin/productIOList", name ="ProductIOListServlet")
public class ProductIOListServlet extends HttpServlet {
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
		String byStatus = request.getParameter("byStatus");
		
		AdminService as = new AdminService();
		List<ProductIO> productIOList = as.selectAllProductIO(cPage, numPerPage, byStatus);
		List<Product> productList = as.selectAllProductList();
		//2. 업무로직
		int totalContent = 0;
		if(byStatus==null||"All".equals(byStatus)) {
			totalContent = as.selectProductIOCount();
		} else {
			totalContent = as.selectProductIOCountByStatus(byStatus);
		}
		int totalPage =  (int)Math.ceil((double)totalContent/numPerPage);
		
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		int pageNo = pageStart;
		if(byStatus==null||"All".equals(byStatus)) {
			//입출고내역 전체조회일 경우 페이징바 처리
			//[이전] section
			if(pageNo == 1 ){
				//pageBar += "<span>[이전]</span>"; 
			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?cPage="+(pageNo-1)+"'>≪</a></li> ";
			}
			
			// pageNo section
			while(pageNo<=pageEnd && pageNo<=totalPage){
				
				if(cPage == pageNo ){
					pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li> ";
				} 
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?cPage="+pageNo+"'>"+pageNo+"</a></li> ";
				}
				pageNo++;
			}
			
			//[다음] section
			if(pageNo > totalPage){
				
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?cPage="+pageNo+"'>≫</a></li>";
			}
		} else {
			//입출고내역 입고, 출고로 조회할 경우 페이징바 처리
			if(pageNo == 1 ){
				//pageBar += "<span>[이전]</span>"; 
			}
			else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?byStatus="+byStatus+"&cPage="+(pageNo-1)+"'>≪</a></li> ";
			}
			
			// pageNo section
			while(pageNo<=pageEnd && pageNo<=totalPage){
				
				if(cPage == pageNo ){
					pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li> ";
				} 
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?byStatus="+byStatus+"&cPage="+pageNo+"'>"+pageNo+"</a></li> ";
				}
				pageNo++;
			}
			
			//[다음] section
			if(pageNo > totalPage){
				
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/productIOList?byStatus="+byStatus+"&cPage="+pageNo+"'>≫</a></li>";
			}
			request.setAttribute("byStatus", byStatus);
		}
		
		//3. view단 처리
		request.setAttribute("productIOList", productIOList);
		request.setAttribute("productList", productList);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/productIO.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
