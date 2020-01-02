package research.model.vo;

import java.io.Serializable;

public class Research implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String category;
	private long price;
	private String date;
	
	public Research() {
		
	}

	public Research(String category, long price, String date) {
		super();
		this.category = category;
		this.price = price;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Research [category=" + category + ", price=" + price + ", date=" + date + ", getCategory()="
				+ getCategory() + ", getPrice()=" + getPrice() + ", getDate()=" + getDate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
