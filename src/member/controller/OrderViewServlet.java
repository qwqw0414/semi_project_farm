package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import product.model.vo.OrderList;

@WebServlet("/member/orderView")
public class OrderViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int cPage =1; //초기값 설정
		final int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
//		System.out.println("cPage@list="+cPage);
		//페이징바 영역처리
		int totalContent = new MemberService().orderTotalContent();
		int totalPage = (int) Math.ceil(totalContent/numPerPage);

		String pageBar = "";
		int pageBarSize = 5;
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		
		int pageNo = pageStart;
		//1.이전
			if(pageNo!=1) {
				pageBar = "<a href='"+request.getContextPath()+"/member/orderView?cPage="+(pageNo-1)+"'[이전]</a>\n";
			}
		//2.pageNo
			while(pageNo<=pageEnd&&pageNo<=totalPage) {
				//현재페이지인 경우
				if(cPage==pageNo) {
					pageBar += "<span class='cPage'>"+pageNo+"</span>";
				} else {
					pageBar += "<a href='"+request.getContextPath()+"/member/orderView?cPage="+pageNo+"'>"+pageNo+"</a>\n";
				}
			}
		//3. 다음
			if(pageNo<totalPage) {
				pageBar += "<a href='"+request.getContextPath()+"/member/orderView?cPage="+pageNo+"'>[다음]</a>\n";
			}
		
		String memberId = request.getParameter("memberId");
		List<OrderList> list = new MemberService().selectOrderList(memberId,cPage,numPerPage);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/member/orderView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
