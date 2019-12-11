package common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import common.util.Utils;

public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String key) {
		String value = "";
		
		if("password".equals(key) || "pwd_new".equals(key)) {
			value = Utils.getSha512(super.getParameter(key));
		}
		else {
			value = super.getParameter(key);
		}
		
		return value;
	}

}






