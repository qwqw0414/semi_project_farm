package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet("/member/memberView")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		//파라미터
		String memberId = request.getParameter("memberId");
		
		//업무로직
		Member m = new MemberService().selectOne(memberId);
		
		//뷰단 처리
		String view = "";
		
		//조회성공
		if(m != null) {
			view = "/WEB-INF/views/member/memberView.jsp";
			request.setAttribute("member", m);
		}
		//조회실패
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			
			String loc = "/";
			String msg = "해당회원이 없습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		}
		
		RequestDispatcher reqDispatcher 
			= request.getRequestDispatcher(view);
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
