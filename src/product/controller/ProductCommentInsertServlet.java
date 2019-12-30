package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.ProductService;
import product.model.vo.OrderList;
import product.model.vo.Product;

/**
 * Servlet implementation class ProductCommentInsertServlet
 */
@WebServlet("/product/productCommentInsert")
public class ProductCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int pId = Integer.parseInt(request.getParameter("pid"));
		String memberId = request.getParameter("memberid");
		String commentContent = request.getParameter("comments");
		//XSS공격대비 &문자변환
		commentContent = commentContent.replaceAll("<", "&lt;")
											.replaceAll(">", "&gt;")
											.replaceAll("\\n", "<br/>");//개행문자 처리
		//2. 업무로직
		ProductService ps = new ProductService();
		//리뷰 작성자 해당 물품 구매여부 확인
		OrderList checkPurchase = ps.selectProductOrderByMemberId(pId, memberId);
		if(checkPurchase==null) {
			//구매하지 않았을 경우 경고메시지 후 리턴
			String msg = "해당 물품 구매자만 리뷰를 등록할 수 있습니다.";
			String loc = "/product/productInfo?pId="+pId;
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			
		} else {
			int result = ps.productInsertComment(pId, memberId, commentContent);
			//3. view단 처리
			String msg = "";
			if(result>0) {
				msg = "리뷰 등록 성공";
			} else {
				msg = "리뷰 등록 실패";
			}
			String loc = "/product/productInfo?pId="+pId;
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
