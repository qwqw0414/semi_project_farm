package product.model.vo;

import java.io.Serializable;

public class ProductIOProduct extends ProductIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pName;
	
	public ProductIOProduct() {
		
	}
	
	public ProductIOProduct(String pName) {
		super();
		this.pName = pName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ProductIOProduct [pName=" + pName + "]";
	}

	
	
}
