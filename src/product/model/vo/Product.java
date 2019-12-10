package product.model.vo;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Product implements Serializable, HttpSessionBindingListener {

	private static final long serialVersionUID = 1L;

	private String pId;
	private String category;
	private String pName;
	private String pInfo;
	private int price;
	private double discount;
	private int stock;
	
	public Product() {
		
	}
	
	public Product(String pId, String category, String pName, String pInfo, int price, double discount, int stock) {
		this.pId = pId;
		this.category = category;
		this.pName = pName;
		this.pInfo = pInfo;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpInfo() {
		return pInfo;
	}

	public void setpInfo(String pInfo) {
		this.pInfo = pInfo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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
		return "Product [pId=" + pId + ", category=" + category + ", pName=" + pName + ", pInfo=" + pInfo + ", price="
				+ price + ", discount=" + discount + ", stock=" + stock + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
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
		Product other = (Product) obj;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		return true;
	}
	
	
}
