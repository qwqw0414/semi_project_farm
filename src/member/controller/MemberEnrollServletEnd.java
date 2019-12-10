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
 * 회원 가입
 */
@WebServlet(name="MemberEnrollEndServlet", urlPatterns="/member/memberEnrollEnd")
public class MemberEnrollServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		
		Member m = new Member(memberId, password, memberName, birth, phone, zipcode, address, null, false);
		
		int result = new MemberService().memberEnroll(m);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";
		
		if(result > 0) {
			msg = "성공적으로 회원등록했습니다.";
		}
		else {
			msg = "회원등록에 실패했습니다.";
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
