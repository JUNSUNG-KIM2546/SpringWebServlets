package com.exam.myapp.member;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Vo : 밸류 오브젝트 약자	private : 캡슐화, 직접 접근을 막음

//Bean Validation 2.0부터 사용가능
// @NotEmpty, @NotBlank, @Email, @Positive, @PositiveOrZero, @Negative, @NegativeOrZero
public class MemberVo {
	@NotNull @Size(max = 10, min = 4) 	//최대 10자리 , 최소 4자리 @email
	private String memId;
	@NotNull @Size(max = 10, min = 3)	//최대 10자리 , 최소 3자리
	private String memPass;
	@NotNull @Size(max = 10, min = 1) 	//최대 10자리 , 최소 1자리
	private String memName;
	@Digits(integer = 10, fraction = 0)	//fraction = 소수점 자리수 , integer = 정수자리수
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
