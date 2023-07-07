package com.exam.myapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//웹브라우저에서 /member/addform.do 로 요청을 보내면
//웹브라우저 화면에 회원정보를 입력하는 폼을 출력하도록
//MemAddFormServlet을 변경하세요.
//@WebServlet("/member/addform.do")
public class MemAddFormServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	

	
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		PrintWriter out = resp.getWriter();
		out.print("<!DOCTYPE html>"         	);
		out.print("<html>"                		);
		out.print("<head>"                  	);
		out.print("<meta charset='UTF-8'>"		);
		out.print("<title>회원 가입</title>"		);
		out.print("</head>"                 	);
		out.print("<body>"                  	);
		out.print("<h1>회원추가</h1>"				);
		out.print("<form action='" + req.getContextPath() + "/member/add2.do' method='post'>"					);
		out.print("		아이디 : <input type='text' name='memId' value=''><br>"			);
		out.print("		비밀번호 : <input type='password' name='memPass' value='' /><br>"	);
		out.print("		이름 : <input type='text' name='memName' value='' /><br>"			);
		out.print("		뽀인트 : <input type='number' name='memPoint' value='0' /><br>"	);
		out.print("		<input type='submit'/>"	);
		out.print("</form>"						);
		out.print("	<h2>=================================================================</h2> ");
		out.print("	<h2>회원목록으로 가기</h2> ");
		out.print("<a href='" + req.getContextPath() + "/member/list2.do' method='post'>");
		out.print("		<button type='button'> 회원 목록 </button>");
		out.print("</body>"						);
		out.print("</html>"                 	);
		System.out.println("접속 하였습니다."		);
	}
	
}
