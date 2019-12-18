package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.service.MemberService;
import member.model.vo.Member;
import product.model.vo.Product;

/**
 * Servlet implementation class AdminProductUpdateServlet
 */
@WebServlet("/admin/productUpdate")
public class AdminProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("AdminProductUpdateServlet");
		
		//1.전송값에 한글이 있을 경우 인코딩처리해야함.		
		request.setCharacterEncoding("UTF-8");
		
		//2.전송값 꺼내서 변수에 기록하기.		
		String pName = request.getParameter("pName");	
		System.out.println("pName: "+pName);
		
		//3.서비스로직호출
		Product p = new AdminService().selectProductByPName(pName);
		//int result = new AdminService().updateProduct(pName);
		//System.out.println(p);
		
		
		//4. 받은 결과에 따라 뷰페이지 내보내기
//		String view = "/WEB-INF/views/common/msg.jsp";
//		String msg = "";
//		String loc = "/member/memberView?memberId="+memberId;
//
//		if(result>0){
//			msg = "성공적으로 회원정보를 수정했습니다.";
//			
//			Member memberLoggedIn
//				= (Member)request.getSession().getAttribute("memberLoggedIn");
//			if(!"admin".equals(memberLoggedIn.getMemberId()))
//				//현재 session에 저장된 memberLoggedIn객체 반영
//				request.getSession().setAttribute("memberLoggedIn", member);
//		}
//		else 
//			msg = "회원정보수정에 실패했습니다.";	

		
		String view = "/WEB-INF/views/admin/productView.jsp";
	
		request.setAttribute("product", p);
		
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);


		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
