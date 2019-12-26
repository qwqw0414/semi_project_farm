package product.model.vo;

public class OrderListProduct extends OrderList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pName;
	private String photo;
	
	public OrderListProduct() {
		
	}

	public OrderListProduct(String pName, String photo) {
		super();
		this.pName = pName;
		this.photo = photo;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
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

	@Override
	public String toString() {
		return "OrderListProduct [pName=" + pName + ", photo=" + photo + "]";
	}
	
	

}
