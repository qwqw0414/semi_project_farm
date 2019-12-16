package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	int cPage = 1;//현재페이지번호
	final int numPerPage = 10;//페이지당 게시물 수
	try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
	} catch(Exception e) {
		
	}
		
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
		
	List<Member> list = null;
	AdminService as = new AdminService();
	switch(searchType) {
		case "memberId" : list = as.selectMemberByMemberId(searchKeyword, cPage, numPerPage);break;
		case "memberName" : list = as.selectMemberByMemberName(searchKeyword,cPage, numPerPage); break;
	}
	
	//페이징바 영역
	int totalContent = 0;
	switch(searchType) {
		case "memberId": totalContent= as.selectTotalContentByMemberId(searchKeyword); break;
		case "memberName": totalContent= as.selectTotalContentByMemberName(searchKeyword); break;

			}
	
	
	int totalPage = (int) Math.ceil((double)totalContent/numPerPage);//총페이지수
	String pageBar = "";
	int pageBarSize = 5;
	int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
	int pageEnd = pageStart+pageBarSize-1;
	int pageNo = pageStart;//증감변수

	//1. 이전
	if(pageNo!=1) {
		pageBar = "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+(pageNo-1)+"'>[이전]</a>\n";
	}
	//2. pageNo
	while(pageNo<=pageEnd&&pageNo<=totalPage) {
		//현재페이지인 경우
		if(cPage==pageNo) {
			pageBar += "<span class='cPage'>"+pageNo+"</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"'>"+pageNo+"</a>\n";
		}
		pageNo++;
	}
	//3. 다음
	if(pageNo<totalPage) {
		pageBar += "<a href='"+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"'>[다음]</a>\n";
	}
	


	
	
	System.out.println(pageBar+"servlet1212");
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














