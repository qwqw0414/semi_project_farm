package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("memberdeleteServelt 실행");
		
		//1.encoding
		request.setCharacterEncoding("utf-8");
		
		//2.parameter handling
		String memberId = request.getParameter("memberId");
		System.out.println("memberId@MemberDeleteServlet="+memberId);		
		
		//3.business logic
		int result = 0;
		result = new MemberService().deleteMember(memberId);
		
		
		//4.view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		//String loc = "/member/memberView";
		String loc = "/";
		
		
		if(result > 0) {
			msg = "회원정보를 성공적으로 삭제했습니다.";
			loc = "/member/memberLogout";
		}
		else {
			msg = "회원정보를 삭제하는데 실패했습니다.";
			loc = "/member/memberView?memberId="+memberId;
		}
		System.out.println(msg);
		
		System.out.println("처리확인");
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
			   .forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
