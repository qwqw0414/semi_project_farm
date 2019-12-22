package product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class OrderList implements Serializable{

	private static final long serialVersionUID = 1L;

	private int orderId;
	private String memberId;
	private int pId;
	private int price;
	private int amount;
	private String zipcode;
	private String address;
	private Date orderDate;
	private Date checkDate;
	private String status;
	
	public OrderList() {
		
	}

	public OrderList(int orderId, String memberId, int pId, int price, int amount, String zipcode, String address,
			Date orderDate, Date checkDate, String status) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.pId = pId;
		this.price = price;
		this.amount = amount;
		this.zipcode = zipcode;
		this.address = address;
		this.orderDate = orderDate;
		this.checkDate = checkDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderList [orderId=" + orderId + ", memberId=" + memberId + ", pId=" + pId + ", price=" + price
				+ ", amount=" + amount + ", zipcode=" + zipcode + ", address=" + address + ", orderDate=" + orderDate
				+ ", checkDate=" + checkDate + ", status=" + status + ", getOrderId()=" + getOrderId()
				+ ", getMemberId()=" + getMemberId() + ", getpId()=" + getpId() + ", getPrice()=" + getPrice()
				+ ", getAmount()=" + getAmount() + ", getZipcode()=" + getZipcode() + ", getAddress()=" + getAddress()
				+ ", getOrderDate()=" + getOrderDate() + ", getCheckDate()=" + getCheckDate() + ", getStatus()="
				+ getStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
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
		OrderList other = (OrderList) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
