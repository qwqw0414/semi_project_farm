package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/memberinfo")
public class AjaxMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		
		Member m = new MemberService().selectOne(memberId);
		
		JSONObject jsonMember = new JSONObject();
		jsonMember.put("memberId", m.getMemberId());
		jsonMember.put("memberName", m.getMemberName());
		jsonMember.put("address", m.getAddress());
		jsonMember.put("zipcode", m.getZipcode());
		jsonMember.put("detail", m.getDetail());
		jsonMember.put("phone", m.getPhone());
		jsonMember.put("birth", m.getBirth());
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonMember.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
