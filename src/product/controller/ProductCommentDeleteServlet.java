package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;

/**
 * Servlet implementation class ProductCommentDeleteServlet
 */
@WebServlet("/product/deleteComment")
public class ProductCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int pId = Integer.parseInt(request.getParameter("pId"));
		System.out.println(commentId);
		
		ProductService ps = new ProductService();
		int result = ps.deleteComment(commentId);
		
		String msg = "";
		if(result>0) {
			msg = "리뷰 삭제 성공";
		} else {
			msg = "리뷰 삭제 실패";
		}
		String loc = "/product/productInfo?pId="+pId;
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
