package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import product.model.vo.Product;
import product.model.vo.ProductIO;

/**
 * Servlet implementation class inputProductIOServlet
 */
@WebServlet(urlPatterns="/admin/productIO", name ="ProductIOServelt")
public class ProductIOServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		int pId = Integer.parseInt(request.getParameter("pName"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String status = request.getParameter("selectIO");
		String memberId = request.getParameter("memberId");
		System.out.println("입출고@pId="+pId);
		System.out.println("입출고@amount="+amount);
		System.out.println("입출고@status="+status);
		System.out.println("입출고@memberId="+memberId);
		ProductIO pIO = new ProductIO(0,pId,memberId,status,amount,null);
		
		//2. 업무로직
		int result;
		String msg = "";
		String loc ="/";

		//2-1. 입고일 경우
		if("I".equals(status)) {
			result = new AdminService().productInput(pIO);
			if(result>0) {
				msg ="입고 성공";
				loc = "/admin/productIOList";
				
			} else {
				msg ="입고 실패";
				loc = "/admin/productIOList";
			}
		} else {
		//2-2. 출고일 경우	
			//출고할 상품 재고 확인
			Product p = new AdminService().selectProductByPId(pId);
			if(amount>p.getStock()) {
				//재고보다 출고량이 많을 경우
				msg = "재고보다 출고량이 많습니다";
				loc = "/admin/productIOList";
			} else {
				//재고가 충분할 경우(정상진행)
				result = new AdminService().productOutput(pIO);
				if(result>0) {
					msg ="출고 성공";
					loc = "/admin/productIOList";
				} else {
					msg ="출고 실패";
					loc = "/admin/productIOList";
				}
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
