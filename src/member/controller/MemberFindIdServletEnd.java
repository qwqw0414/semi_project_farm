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
//		try {
//			m = new MemberService().memberFindId(memberName, birth, phone);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//3.
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
		
		System.out.println(m+"find");
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
