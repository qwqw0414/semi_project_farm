package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import member.model.vo.Member;
import product.model.service.ProductService;
import product.model.vo.OrderList;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductInfoServlet
 */
@WebServlet("/product/productInfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int pId = Integer.parseInt(request.getParameter("pId"));
		HttpSession session = ((HttpServletRequest)request).getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		OrderList checkPurchase;
		ProductService ps = new ProductService();
		Product p = ps.selectOneByPId(pId);
		if(memberLoggedIn==null) {
			checkPurchase = null;
		} else {
			String memberId = memberLoggedIn.getMemberId();
			//2. 업무 로직
			checkPurchase = ps.selectProductOrderByMemberId(pId, memberId);
		}
		//3. view단 처리
		
		request.setAttribute("product", p);
		request.setAttribute("checkPurchase", checkPurchase);
		request.getRequestDispatcher("/WEB-INF/views/product/productInfo.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
