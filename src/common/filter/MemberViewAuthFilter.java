package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class MemberViewAuthFilter
 */
@WebFilter(servletNames = { "MemberViewServlet" })
public class MemberViewAuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MemberViewAuthFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		//로그인한 사용자의 아이디
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");

		String memberId = request.getParameter("memberId");
		if(memberLoggedIn==null||!(memberLoggedIn.getMemberId().equals(memberId)||memberLoggedIn.isAdmin()==true)) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
