package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import admin.model.service.AdminService;
import common.BaseData;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage =1; //초기값 설정
		final int numPerPage = new BaseData().getPagenum();
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
			
		} catch(NumberFormatException e) {
			//cPage 입력값이 없거나, 부정입력한 경우 기본값으로 처리된다
		}
		//페이징바 영역처리
		int totalContent = new AdminService().selectMemberTotalContent();
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
		String pageBar = "";
		int pageBarSize = new BaseData().getPAGEBARSIZE();

		int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageStart+pageBarSize-1;
		//증감변수 pageNo
		int pageNo = pageStart;
		//1. 이전
				if(pageNo!=1) {
					pageBar = "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberList?cPage="+(pageNo-1)+"'>≪</a></li>";
				}
				//2. pageNo
				while(pageNo<=pageEnd&&pageNo<=totalPage) {
					//현재페이지인 경우
					if(cPage==pageNo) {
						pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li> ";
					} else {
						pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"'>"+pageNo+"</a></li>";
					}
					pageNo++;
				}
				//3. 다음
				if(pageNo<totalPage) {
					pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"'>≫</a></li>";
				}

		
		List<Member> list = new AdminService().selectAllByPaging(cPage, numPerPage);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
