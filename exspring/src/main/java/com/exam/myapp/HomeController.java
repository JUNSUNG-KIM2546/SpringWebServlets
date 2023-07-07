package com.exam.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller	// 컨트롤러(요청을 받았을때 실행되는 객체)로서 스프링(DispatcherServlet)에 등록
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
																//"com.exam.myapp.HomeController"										
	// "/"  경로로 GET 방식 요청이 오면 실행
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public String home(Locale locale, Model model, Map map, ModelMap modelmap) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// 모델: 응답/화면(JSP) 출력시 포함할 데이터
		// 모델에 데이터를 추가(저장)하기 위해서는, 인자로 받은 Model, Map, ModelMap 객체에 모델이름-값 형식으로 데이터를 저장
		// JSP에서는 ${모델이름} EL 으로 값을 꺼내서 사용가능
		model.addAttribute("a", formattedDate ); // ("모델이름", 값);
		map.put("b", formattedDate ); // Map은 자바에 있는 메소드 ("모델이름", 값);
		modelmap.addAttribute("c", formattedDate ); // ("모델이름", 값);
		
		return "home";	// 컨트롤러가 문자열을 반환하면 스프링은 문자열을 뷰이름으로 인식
		// servlet-context.xml의 InternalResourceViewResolver 설정대로
		// 문자열 앞에 "/WEB-INF/views/" 를 추가하고,
		// 문자열 뒤에 ".jsp" 를 추가한 주소(경로)로 이동(forward)
		// 즉, "/WEB-INF/views/home.jsp" 파일을 실행
	}
	
	// 브라우저에서 "http://localhost:8000/myapp/test" 로 접속하면,
	// test()메서드와 test.jsp가 순서대로 실행되어 
	// test.jsp의 h1 태그 내용에 변수 s값("JSP에서 출력할 문자열")이 출력되도록 구현
	@RequestMapping(path = "/test", method = RequestMethod.GET)	// value = 와 path = 같다, method = RequestMethod.GET 이걸 넣어주면 겟방식만 요청 받겠다
	// 스프링4.3버전이상에서만 @GetMapping(path = "/test") //, @PostMapping, @PutMapping, @PatchMapping 요청방식별 @RequestMapping 애노테이션도 사용가능
	public String test (@RequestParam(name = "x") String xx	// 이름이 x인 요청파라미터 값을 변수에 저장
						, int y	//변수이름이 파라미터이름과 동일하면 @RequestParam 생략 가능, 스프링에서는 파라미터값을 형변환 안해줘도 선언된거에 따라간다
						
						//사용자가 정의한 객체를 인자로 받는 경우, 객체의 속성명과 동일한 이름의 파라미터 값을 객체의 속성에 자동 저장
						//@ModelAttribute(모델명) 을 적용하여 매개변수 값을 지정한 이름으로 모델레 저장(추가) 가능
						,@ModelAttribute(value = "mv") MyVo vo
						
						//파라미터를 받기 위해서 배치한 매개변수는 자동으로 모델에 추가, 
						//@ModelAttribute를 생략한 경우, 모델이름은 타입명의 첫글자를 소문자로 변환하여 저장
						, MyVo v 
						
						, Model mod) {
		
		logger.info("TEST 할꼬얌 xx : {}, yy : {}", xx, y);	// {} 중갈호 안에 변수값이 출력된다
		logger.info("TEST 할꼬얌 vo.x : {}, vo.y : {}", vo.getX(), vo.getY());	// {} 중갈호 안에 변수값이 출력된다
		String s = "JSP에서 출력할 문자열";
		
		mod.addAttribute("vox",vo.getX());
		mod.addAttribute("voy",vo.getY());
//		mod.addAttribute("mv",vo);
		mod.addAttribute("s",s);	//출력할 값을 Model에 넣어줘야 한다
		return "test";
		
		
	}
	
	
}







