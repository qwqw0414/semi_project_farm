package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Member implements Serializable, HttpSessionBindingListener {

	private static final long serialVersionUID = 1L;

	private String memberId;
	private String password;
	private String memberName;
	private String birth;
	private String phone;
	private String zipcode;
	private String address;
	private Date enrolldate;
	private boolean isAdmin;

	public Member() {

	}

	public Member(String memberId, String password, String memberName, String birth, String phone, String zipcode,
			String address, Date enrolldate, boolean isAdmin) {
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.birth = birth;
		this.phone = phone;
		this.zipcode = zipcode;
		this.address = address;
		this.enrolldate = enrolldate;
		this.isAdmin = isAdmin;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {

	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", birth="
				+ birth + ", phone=" + phone + ", zipcode=" + zipcode + ", address=" + address + ", enrolldate="
				+ enrolldate + ", isAdmin=" + isAdmin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
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
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}

}
