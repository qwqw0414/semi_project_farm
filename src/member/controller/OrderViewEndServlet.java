package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BaseData;
import member.model.service.MemberService;
import product.model.vo.OrderList;

/**
 * Servlet implementation class OrderViewEndServlet
 */
@WebServlet("/member/orderViewEnd")
public class OrderViewEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		String memberId = request.getParameter("memberId");
		
		int cPage =1; //초기값 설정
		final int numPerPage = new BaseData().getPagenum();
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			
		}
		
		List<OrderList> list = new MemberService().selectOrderByDate(memberId, year, month, cPage, numPerPage);
		
		//페이징바 영역처리
		int totalContent = new MemberService().orderTotalContent(memberId);
		int totalPage = (int) Math.ceil((double)totalContent/numPerPage);

		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();
		
		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		
		int pageNo = pageStart;
		//1.이전
			if(pageNo!=1) {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/member/orderViewEnd?memberId="+memberId+"&cPage="+(pageNo-1)+"'≪</a></li>";
				
			}
		//2.pageNo
			System.out.println("cPage="+cPage);
			System.out.println("pageNo= "+pageNo);
			while(pageNo<=pageEnd&&pageNo<=totalPage) {
				//현재페이지인 경우
				if(cPage==pageNo) {
					pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li>";
				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/member/orderViewEnd?memberId="+memberId+"&cPage="+pageNo+"'>"+pageNo+"</a></li>";
				}
				pageNo++;
			}
		//3. 다음
			if(pageNo<totalPage) {
				pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/member/orderViewEnd?memberId="+memberId+"&cPage="+pageNo+"'>≫</a></li>";
			}
			
			
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/member/orderView.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
