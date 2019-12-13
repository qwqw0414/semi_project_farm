package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberFindIdServletEnd
 */
@WebServlet(urlPatterns="/member/memberFindIdEnd", name="MemberFindIdEndServlet")
public class MemberFindIdServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		String memberName = request.getParameter("memberName");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		//2. 업무로직
		Member m = null;
		
		m = new MemberService().memberFindId(memberName, birth, phone);
	
		//3.

		String view = "";
		String msg = "";
		String loc = "/";
		
		if(m != null) {
			view = "/WEB-INF/views/member/memberFindId.jsp";		
			
		}
		else {
			view = "/WEB-INF/views/common/msg.jsp";
			msg = "아이디를 찾을 수 없습니다.";
			//loc = "/WEB-INF/views/member/memberFindId.jsp";
			//loc = "/member/memberFindId.jsp";
			loc = "/member/memberFindId";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("member", m);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
