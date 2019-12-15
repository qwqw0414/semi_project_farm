package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BaseData;
import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.WishList;

/**
 * Servlet implementation class WishListInsert
 */
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
		
		int numPerPage = new BaseData().getIndexpagenum();
		int cPage = 1;
		int totalContent = 0;

		String keyWord = request.getParameter("keyWord");
		if(keyWord == null) keyWord = "";
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}
		// 2.업무로직
		List<Product> list = null;
		ProductService ps = new ProductService();
		list = ps.selectProduct(keyWord ,cPage, numPerPage);

		//총 수
		totalContent = ps.countProductByName(keyWord);
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		
		// 페이지바 html 코드 생성
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();
		int pageStart = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageStart + pageBarSize - 1;
		int pageNo = pageStart;

		if (pageNo == 1) {

		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productView?keyWord=" + keyWord + "&cPage="
					+ (pageNo - pageBarSize) + "'>이전</a></li>";
		}

		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (cPage == pageNo) {
				pageBar += "<li class='page-item active'><a class='page-link'>" + pageNo + "</a></li>";
			} else {
				pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productView?keyWord=" + keyWord
						+ "&cPage=" + pageNo + "'>" + pageNo + "</a></li>";
			}
			pageNo++;
		}

		if (pageNo > totalPage) {

		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='" + request.getContextPath() + "/product/productView?keyWord=" + keyWord + "&cPage="
					+ pageNo + "'>다음</a></li>";
		}
		//3.view단 처리
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.getRequestDispatcher("/WEB-INF/views/product/productView.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
