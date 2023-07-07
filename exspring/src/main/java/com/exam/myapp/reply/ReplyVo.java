package com.exam.myapp.reply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVo {
	private int repNo;
	private String repContent;
	private String repWriter;
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "Asia/Seoul")
	
	private Date repRegDate;
	private int repBbsNo;
	
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public String getRepWriter() {
		return repWriter;
	}
	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}
	public Date getRepRegDate() {
		return repRegDate;
	}
	public void setRepRegDate(Date repRegDate) {
		this.repRegDate = repRegDate;
	}
	public int getRepBbsNo() {
		return repBbsNo;
	}
	public void setRepBbsNo(int repBbsNo) {
		this.repBbsNo = repBbsNo;
	}
}
