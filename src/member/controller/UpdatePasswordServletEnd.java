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

@WebServlet(urlPatterns="/member/UpdatePasswordEnd",
			name ="UpdatePasswordServletEnd")
public class UpdatePasswordServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String memberId = request.getParameter("memberId");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
//		String pwd_new = request.getParameter("pwd_new");
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setBirth(birth);
		m.setPhone(phone);
		
		Member member = new MemberService().selectByPassword(m);

		request.setAttribute("m", member);
		
		if(member == null) {
			request.setAttribute("str", "<script>alert('잘못된 입력');</script>");
		}
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}













