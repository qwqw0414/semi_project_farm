package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import common.BaseData;
import product.model.service.ProductService;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductCategoryViewServlet
 */
@WebServlet("/product/productCategory")
public class ProductCategoryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터
		String category = request.getParameter("category");
		int cPage = 1;
		int totalContent = 0;
		int numPerPage = new BaseData().getIndexpagenum();
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		
		totalContent = new ProductService().selectProductTotalContent(category);
		List<Product> list = new ProductService().selectByCategory(category, cPage, numPerPage);
	
		
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();
		int pageStart = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		int pageNo = pageStart;
		
		//1.이전
		if (pageNo == 1) {

		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productCategory?category=" + category + "&cPage="
					+ (pageNo - pageBarSize) + "'>이전</a></li>";
		}

		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (cPage == pageNo) {
				pageBar += "<li class='page-item active'><a class='page-link'>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productCategory?category=" + category
						+ "&cPage=" + pageNo + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}

		if (pageNo > totalPage) {

		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productCategory?category=" + category + "&cPage="
					+ pageNo + "'>다음</a></li>";
		}
		

		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/WEB-INF/views/product/productCategoryView.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}





















