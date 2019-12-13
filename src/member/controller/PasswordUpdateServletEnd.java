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


@WebServlet(urlPatterns="/member/PasswordUpdateEnd",
			name ="PasswordUpdateServletEnd")
public class PasswordUpdateServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		String pwd_new = request.getParameter("pwd_new");
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(pwd_new);
		
		int result = new MemberService().updatePassword(m);
		
		
	
		String view = "";
		if(result > 0) {
			
			request.setAttribute("str", "<script>alert('비밀번호가 변경되었습니다.');</script>");
			view = "/WEB-INF/views/member/memberLogin.jsp";
		}
		
		
		System.out.println(m+"11212312");
		

		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}






















