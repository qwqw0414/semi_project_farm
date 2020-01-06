package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import common.BaseData;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet(urlPatterns="/admin/memberFinder", name ="AdminMemberFinderServlet")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
		
	int cPage = 1;//현재페이지번호
	final int numPerPage = new BaseData().getPagenum();
	try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
	} catch(Exception e) {
		
	}

	List<Member> list = null;
	AdminService as = new AdminService();
	switch(searchType) {
		case "memberName" : list = as.selectMemberByMemberName(searchKeyword,cPage, numPerPage); break;
		case "memberId" : list = as.selectMemberByMemberId(searchKeyword, cPage, numPerPage);break;
		case "phone" : list = as.selectMemberByMemberPhone(searchKeyword, cPage, numPerPage);break;
	}
	
	//페이징바 영역
	int totalContent = 0;
	switch(searchType) {
		case "memberId": totalContent= as.selectTotalContentByMemberId(searchKeyword); break;
		case "memberName": totalContent= as.selectTotalContentByMemberName(searchKeyword); break;

			}
	
	
	int totalPage = (int) Math.ceil((double)totalContent/numPerPage);//총페이지수
	String pageBar = "";
	int pageBarSize = new BaseData().getPAGEBARSIZE();
	int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
	int pageEnd = pageStart+pageBarSize-1;
	int pageNo = pageStart;//증감변수

	//1. 이전
	if(pageNo!=1) {
		pageBar = "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-1)+"'>≪</a></li>";

	}
	//2. pageNo
	while(pageNo<=pageEnd&&pageNo<=totalPage) {
		//현재페이지인 경우
		if(cPage==pageNo) {
			pageBar += "<li class='page-item active'><a class='page-link'>"+pageNo+"</a></li> ";
		} else {
			pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>"+pageNo+"</a></li>";
		}
		pageNo++;
	}
	//3. 다음
	if(pageNo<totalPage) {
		pageBar += "<li class='page-item'><a class='page-link' href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"'>≫</a></li>";
	}

	request.setAttribute("pageBar", pageBar);
	request.setAttribute("list", list);
	//3. 뷰단처리
	request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp").forward(request, response);

	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}














