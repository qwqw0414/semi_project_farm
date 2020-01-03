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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값에 한글이 있을 경우 인코딩처리해야함.
				//void javax.servlet.ServletRequest.setCharacterEncoding(String arg0) throws UnsupportedEncodingException
				request.setCharacterEncoding("UTF-8");//대소문자 상관없음. 요청한 view단의 charset값과 동일해야 한다.
				
				//2.전송값 꺼내서 변수에 기록하기.
				//String javax.servlet.ServletRequest.getParameter(String arg0)
				String memberId = request.getParameter("memberId");
				String memberName = request.getParameter("memberName");
				String birth = request.getParameter("birth");
				String phone = request.getParameter("phone");
				String zipcode = request.getParameter("zipcode");
				String address = request.getParameter("address");
				String detail = request.getParameter("detail");
				
				Member m = new Member();

				m.setMemberId(memberId);
				m.setMemberName(memberName);
				m.setBirth(birth);
				m.setPhone(phone);
				m.setZipcode(zipcode);
				m.setAddress(address);
				m.setDetail(detail);
				
				//System.out.println("Member@MemberUpdateServlet="+m);
				
				int result = new MemberService().memberUpdate(m);
				
				//System.out.println("result@MemberUpdateServlet="+result);
				
				String view = "/WEB-INF/views/common/msg.jsp";
				String msg = "";
				String loc = "/member/memberView?memberId="+m.getMemberId();
				

				if(result > 0) {
					msg = "성공적으로 정보를 수정했습니다.";
				}
				else {
					msg = "회원정보 수정에 실패했습니다.";
				}
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
				reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
