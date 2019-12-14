package product.model.vo;

import java.io.Serializable;

public class WishListProduct extends WishList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String pName;
	private int price;
	
	public WishListProduct() {
	}

	public WishListProduct(int listId, String memberId, int pId, int amount, String pName, int price) {
		super(listId, memberId, pId, amount);
		this.pName = pName;
		this.price = price;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "WishListProduct [pName=" + pName + ", price=" + price + "]";
	}
	
	
}
