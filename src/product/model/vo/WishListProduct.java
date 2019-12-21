package product.model.vo;

import java.io.Serializable;

public class WishListProduct extends WishList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String pName;
	private int price;
	private double discount;
	private String photo;
	
	public WishListProduct() {
	}

	public WishListProduct(int listId, String memberId, int pId, int amount, String pName, int price, double discount,
			String photo) {
		super(listId, memberId, pId, amount);
		this.pName = pName;
		this.price = price;
		this.discount = discount;
		this.photo = photo;
	}


	@Override
	public String toString() {
		return "WishListProduct [pName=" + pName + ", price=" + price + ", discount=" + discount + ", photo=" + photo
				+ "]";
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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
