package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServletEnd
 */
@WebServlet(urlPatterns="/member/memberLoginEnd",
					name="MemberLoginEndServlet")
public class MemberLoginServletEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 핸들링
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.println(memberId);
		System.out.println(password);
		//2. 업무로직
		MemberService ms = new MemberService();
		Member m = ms.selectOne(memberId);
		//처리결과에 따라 msg.jsp로 포워딩처리
		String msg ="";
		String loc ="/";
		RequestDispatcher reqDispatcher 
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		if(m==null) {
			//아이디가 존재하지 않아 로그인에 실패한 경우
			msg = "존재하지 않는 아이디입니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			reqDispatcher.forward(request, response);
		} else {
			//비밀번호가 틀려서 로그인에 실패한 경우
			if(!m.getPassword().equals(password)) {
				msg = "비밀번호가 틀렸습니다.";
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				reqDispatcher.forward(request, response);
			} else {
				//로그인에 성공한 경우
				//로그인한 회원 객체 저장
				HttpSession session = request.getSession(true);
				session.setAttribute("memberLoggedIn", m);
				//session유효시간 설정(30분)
				session.setMaxInactiveInterval(60*30);
				//아이디저장
				String saveId = request.getParameter("saveId");
				
				//체크한경우
				if(saveId != null) {
					Cookie c = new Cookie("saveId", memberId);
					c.setMaxAge(7*24*60*60);//7일후 소멸
					c.setPath("/");//쿠키사용디렉토리. 도메인 전역에서 사용함.
					response.addCookie(c);
				}
				//체크하지 않은 경우
				else {
					Cookie c = new Cookie("saveId", memberId);
					c.setMaxAge(0);//삭제를 위해 유효기간을 0으로 설정
					c.setPath("/");
					response.addCookie(c);
				}

				//리다이렉트 처리
				response.sendRedirect(request.getContextPath());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
