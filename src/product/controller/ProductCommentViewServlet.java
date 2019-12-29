package product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import product.model.service.ProductService;
import product.model.vo.Comments;



/**
 * Servlet implementation class ProductCommentViewServlet
 */
@WebServlet("/product/productCommentView")
public class ProductCommentViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터
		int pId = Integer.parseInt(request.getParameter("pId"));
		//2. 업무로직
		List<Comments> list = new ProductService().selectCommentsByPId(pId);
		//JSON처리
		JSONArray jsonCommentList = new JSONArray();
		JSONObject jsonComments;
		for(Comments c:list) {
			jsonComments = new JSONObject();
			jsonComments.put("commentId", c.getCommentId());
			jsonComments.put("memberId", c.getMemberId());
			jsonComments.put("pId", c.getpId());
			jsonComments.put("commentContent", c.getCommentContent());
			jsonComments.put("commentDate", c.getCommentDate().toString());
			
			jsonCommentList.add(jsonComments);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonCommentList.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
