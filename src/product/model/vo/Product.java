package product.model.vo;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	private String pId;
	private String category;
	private String pName;
	private String pInfo;
	private int price;
	private double discount;
	private int stock;
	private String photo;
	
	public Product() {
		
	}

	public Product(String pId, String category, String pName, String pInfo, int price, double discount, int stock,
			String photo) {
		this.pId = pId;
		this.category = category;
		this.pName = pName;
		this.pInfo = pInfo;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", category=" + category + ", pName=" + pName + ", pInfo=" + pInfo + ", price="
				+ price + ", discount=" + discount + ", stock=" + stock + ", photo=" + photo + "]";
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
