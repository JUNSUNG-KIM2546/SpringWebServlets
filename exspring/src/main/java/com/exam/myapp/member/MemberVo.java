package com.exam.myapp.member;

// Vo : 밸류 오브젝트 약자	private : 캡슐화, 직접 접근을 막음
public class MemberVo {

	private String memId;
	private String memPass;
	private String memName;
	private int    memPoint;
	
	
	
	public String getMemId() {
		return memId;
	}



	public void setMemId(String memId) {
		this.memId = memId;
	}



	public String getMemPass() {
		return memPass;
	}



	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}



	public String getMemName() {
		return memName;
	}



	public void setMemName(String memName) {
		this.memName = memName;
	}



	public int getMemPoint() {
		return memPoint;
	}



	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}


}
