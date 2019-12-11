package zipcode.model.vo;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Zipcode implements Serializable, HttpSessionBindingListener {

	private static final long serialVersionUID = 1L;

	private String code;
	private String sido;
	private String sigungu;
	private String myun;
	private String ro;
	private String name;

	public Zipcode() {

	}

	public Zipcode(String code, String sido, String sigungu, String myun, String ro, String name) {
		this.code = code;
		this.sido = sido;
		this.sigungu = sigungu;
		this.myun = myun;
		this.ro = ro;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	public String getMyun() {
		return myun;
	}

	public void setMyun(String myun) {
		this.myun = myun;
	}

	public String getRo() {
		return ro;
	}

	public void setRo(String ro) {
		this.ro = ro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Zipcode [code=" + code + ", sido=" + sido + ", sigungu=" + sigungu + ", myun=" + myun + ", ro=" + ro
				+ ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zipcode other = (Zipcode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
	}
}
