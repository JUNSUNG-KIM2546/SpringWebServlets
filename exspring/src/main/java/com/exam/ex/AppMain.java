package com.exam.ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	
	public static void main(String[] args) {
		//MyApp, MyServiceKo를 사용하여, 콘솔에 "안녕" 이 출력되도록 구현
			
//		MyApp app = new MyApp();
//		MyServiceKo my = new MyServiceKo();
//		MyServiceEn myy = new MyServiceEn();
//		
//		app.setMyService(my);
//		app.say();
//		app.setMyService(myy);
//		app.say();
		// 프레임워크(남이 만들어 놓은거에 내꺼는 추가해서 사용 
		// == 
		// 라이브러리(내가 만들어 놓은거에 남이 짠걸 추가)
		// 스프링 == 객체컨테이너 == BeanFactory == ApplicationContext  객체들을 만들어주는공장
		
		// 클래스패스 상의 XML 파일로부터 설정을 읽어서, 스프링 객체 컨테이너를 생성
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("/com/exam/ex/context.xml");
		
		// JAVA 클래스로부터 설정을 읽어서, 스프링 객체 컨테이너를 생성
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		
		// 스프링에 "ma"라는 이름으로 등록되어 있는 객체를 가져오기
		MyApp app = (MyApp) ctx.getBean("ma");
		
		app.say();
		
		
	}

}
