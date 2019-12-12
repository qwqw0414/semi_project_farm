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
		String pwd_new = request.getParameter("pwd_new");
		

		Member m = new MemberService().selectByPassword(memberId, birth);

		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(m!=null) {
			int result = new MemberService().updatePassword(m, pwd_new);
			if(result > 0) {
				msg = "패스워드가 변경되었습니다.";
				loc = "/member/memberLogin";
			} else {
				
			}
		
		} else {
				
			msg = "아이디와 생년월일을 다시입력해주세요. 입력한 아이디: "+memberId+" 입력한 생년월일: "+birth;
			loc = "/member/updatePassword";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}













