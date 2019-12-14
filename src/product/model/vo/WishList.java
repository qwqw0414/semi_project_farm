package product.model.vo;

import java.io.Serializable;

public class WishList implements Serializable{

	private static final long serialVersionUID = 1L;

	private int listId;
	private String memberId;
	private int pId;
	private int amount;
	
	public WishList() {
		
	}
	
	public WishList(int listId, String memberId, int pId, int amount) {
		super();
		this.listId = listId;
		this.memberId = memberId;
		this.pId = pId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "wishList [listId=" + listId + ", memberId=" + memberId + ", pId=" + pId + ", amount=" + amount + "]";
	}

	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
