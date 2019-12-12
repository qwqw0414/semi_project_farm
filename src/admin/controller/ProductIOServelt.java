package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.ProductIO;

/**
 * Servlet implementation class inputProductIOServlet
 */
@WebServlet("/admin/productIO")
public class ProductIOServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		String pId = request.getParameter("pId");
		int amount = Integer.parseInt(request.getParameter("amount"));
		String status = request.getParameter("selectIO");
		String memberId = request.getParameter("memberId");
		//임시 ioid
		int num = (int) (Math.random()*10000+1);
		String ioid = pId+status+num;
		
		ProductIO pIO = new ProductIO(ioid,pId,memberId,status,amount,null);
		System.out.println(pIO);
		//2. 업무로직
		int result;
		String msg = "";
		String loc ="/";

		//2-1. 입고일 경우
		if("I".equals(status)) {
			result = new AdminService().productInput(pIO);
			if(result>0) {
				msg ="입고 성공";
				loc = "/admin/productIOView";
				
			} else {
				msg ="입고 실패";
				loc = "/admin/productIOView";
			}
		} else {
		//2-2. 출고일 경우	
			result = new AdminService().productOutput(pIO);
			if(result>0) {
				msg ="출고 성공";
				loc = "/admin/productIOView";
				
			} else {
				msg ="출고 실패";
				loc = "/admin/productIOView";
			}
		}
		//3. view단처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
