package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter{

	private FilterConfig fConfig;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.fConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain)
			throws IOException, ServletException {
		String encodeType = fConfig.getInitParameter("encodeType");
		
		request.setCharacterEncoding(encodeType);
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		
	}

}
