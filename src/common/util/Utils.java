package common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {

	public static String getSha512(String password) {
		String encPwd = null;
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("sha-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = null;
		try {
			 bytes = password.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		md.update(bytes);
		
		byte[] encBytes = md.digest();
		
		encPwd = Base64.getEncoder().encodeToString(encBytes);
		
		return encPwd;
	}
}







