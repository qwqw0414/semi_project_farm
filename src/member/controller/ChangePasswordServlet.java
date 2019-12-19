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
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet(urlPatterns="/member/ChangePasswordEnd",
			name ="ChangeMemberPasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String pwd_new	= request.getParameter("pwd_new");
		
		System.out.println(memberId+"123123123");
		System.out.println(password+"123123123");
		
		Member m = new MemberService().changeByPassword(memberId, password);
		System.out.println(m+"23123123123");
		String msg = "";
		String loc = "";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(m != null) {
			int result = new MemberService().changeUpdate(m,pwd_new);
			if(result > 0) {
				msg = "비밀번호가 변경되었습니다.";
				loc = "/product/productView";
			}
			
		}else {
			msg = "비밀번호를 다시 확인해주세요.";
			loc = "/member/ChangePassword";
		}
		
		
	
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}












