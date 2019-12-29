package product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Comments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int commentId;
	private String memberId;
	private int pId;
	private String commentContent;
	private Date commentDate;

	public Comments() {
		
	}

	public Comments(int commentId, String memberId, int pId, String commentContent, Date commentDate) {
		super();
		this.commentId = commentId;
		this.memberId = memberId;
		this.pId = pId;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", memberId=" + memberId + ", pId=" + pId + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + "]";
	}
	
	
}
