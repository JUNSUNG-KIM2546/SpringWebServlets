package com.exam.ex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller

//이 클래스의 객체를 생성하여 "ma"라는 이름으로 스프링에 등록	@Component(value = "ma") == @Component("ma")
@Component("ma")	
public class MyApp {
	// @Autowired, @Inject, @Resource : 스프링에 등록된 객체를 이 변수(속성)에 주입(저장)
	@Autowired
	private MyService myService;	//필드
	
	public void say() {
		
		System.out.println(myService.getMessage() );
	}

	public MyService getMyService() {	//속성
		return myService;
	}

	public void setMyService(MyService myService) {	//속성
		this.myService = myService;
	}
	
}
