package product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductIO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ioId;
	private int pId;
	private String memberId;
	private String status;
	private int amount;
	private Date ioDate;

	public ProductIO() {

	}

	public ProductIO(int ioId, int pId, String memberId, String status, int amount, Date ioDate) {
		super();
		this.ioId = ioId;
		this.pId = pId;
		this.memberId = memberId;
		this.status = status;
		this.amount = amount;
		this.ioDate = ioDate;
	}

	public int getIoId() {
		return ioId;
	}

	public void setIoId(int ioId) {
		this.ioId = ioId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
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

	@Override
	public String toString() {
		return "ProductIO [ioId=" + ioId + ", pId=" + pId + ", memberId=" + memberId + ", status=" + status
				+ ", amount=" + amount + ", ioDate=" + ioDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ioId;
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
		ProductIO other = (ProductIO) obj;
		if (ioId != other.ioId)
			return false;
		return true;
	}

}
