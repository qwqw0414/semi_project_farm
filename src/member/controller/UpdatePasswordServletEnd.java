package member.controller;

import java.io.IOException;

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
		String pwd_new = request.getParameter("pwd_new");
		

		Member m = new MemberService().selectByPassword(memberId, birth);
//		System.out.println(m+"servlet+11212121121");
		
		
		if(m!=null) {
			int result = new MemberService().updatePassword(m, pwd_new);
			
			System.out.println(result+"servlet+1123123123");
			System.out.println(pwd_new+"servlet pwdnew");
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}













