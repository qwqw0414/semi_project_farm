package product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductIO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ioId;
	private String pId;
	private String memberId;
	private String status;
	private int amount;
	private Date ioDate;

	public ProductIO() {

	}

	public ProductIO(String ioId, String pId, String memberId, String status, int amount, Date ioDate) {
		this.ioId = ioId;
		this.pId = pId;
		this.memberId = memberId;
		this.status = status;
		this.amount = amount;
		this.ioDate = ioDate;
	}

	@Override
	public String toString() {
		return "ProductIO [ioId=" + ioId + ", pId=" + pId + ", memberId=" + memberId + ", status=" + status
				+ ", amount=" + amount + ", ioDate=" + ioDate + "]";
	}

	public String getIoId() {
		return ioId;
	}

	public void setIoId(String ioId) {
		this.ioId = ioId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getIoDate() {
		return ioDate;
	}

	public void setIoDate(Date ioDate) {
		this.ioDate = ioDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
