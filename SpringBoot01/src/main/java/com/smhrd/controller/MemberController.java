package com.smhrd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.Member;
import com.smhrd.mapper.MemberMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller // Spring에게 POJO임을 알려주기위한 어노테이션(필수)
public class MemberController {

	// xml, interface --> SqlSessionFactory --> DAO
	// MyBatis가 interface를 구현해서 서버 메모리에 등록시켜 놓음
	
	@Autowired // 메모리에서 넣을 수 있는거 가져와서 넣어라
	private MemberMapper mapper;
	
	
	// Spring
	// FrontController 기반
	// Servlet이 늘어날 수록 서버 메모리 부하 증가 --> Servlet을 단 하나만 쓰자
	// 일반 Class에 어떤 요청이 들어 왔을 때 무슨 일을 할지를 정의
	// FrontController에서 정의된 내용을 설정

	// Spring에서는 Servlet을 기본으로 제공, 클래스만 만들면 된다.
	// POJO(plain old java object)라고도 부른다.

	// 어떤 요청이 들어 왔을 때 무슨 일을 할지를 정의 --> 메소드

	@RequestMapping("/goMain") // goMain요청이 들어오면 goMain()을 실행해라
	public String goMain() {
		// 1. 데이터 수집
		// 2. 기능 실행
		// 3. View 이동
		// forward 이동시, 이동하고 싶은 "jsp파일의 이름"만 적으면 리턴하면 됨.
		return "main";

	}

//	@RequestMapping("/join")
//	public String join(HttpServletRequest request) {
//		// 필요한 객체 생겼다!
//		// --> 매개 변수에 변수를 선언해두면, Spring이 알아서 객체를 집어넣어줌.
//		
//		// 1. 데이터 수집
//		String email = request.getParameter("email");
//		String pw = request.getParameter("pw");
//		String tel = request.getParameter("tel");
//		String address = request.getParameter("address");
//
//		// >> 하나로 묶는 과정 dto
//		Member member = new Member();
//		member.setEmail(email);
//		member.setPw(pw);
//		member.setTel(tel);
//		member.setAdd(address);
//		
//		// 2. 기능 구현
//		
//		return ="";
//	}

	@RequestMapping("/join")
	public String join(Member member) {
		// 필요한 객체 생겼다!
		// --> 매개 변수에 변수를 선언해두면, Spring이 알아서 객체를 집어넣어줌.

		// 1. 데이터 수집
		// 메소드의 매개변수 잘에 변수를 선언해두면 자동으로 수집
		// 변수명 == name
		// 형변환도 자동으로 진행
		
		// DTO를 이용해서 한번에 수집도 가능
		// DTO에 선언된 필드 변수명 == name
		
		// 2. 기능 실행
		// >> DB에 회원정보 저장하기
		int cnt = mapper.join(member);
		
		if(cnt >0) {
			System.out.println("회원가입 성공");
		}
		
		// 3. View 이동
		// redirect
		return "redirect:/goMain";
		
	}

	@RequestMapping("/Login")
	public String login(Member member, HttpSession session) {
		// 1. 데이터 수집
		// 2. 특정 기능 실행
		
		Member result = mapper.login(member);
		
		if(result==null) {
			System.out.println("login f");
		} else {
			System.out.println("login s");
			session.setAttribute("user", result);
			
		}
		// 3. view 이동
		return "redirect:/goMain";
	}
	
	@RequestMapping("/goUpdate")
	public String goUpdate() {
		// 1. 데이터 수집
		// 2. 기능 실행
		// 3. View로 이동
		return "update";
	}
	
	

	@RequestMapping("/update")
	public String update(Member member, HttpSession session) {
		
		// 1. 데이터 수집
		Member user =(Member)session.getAttribute("user");
		String email = user.getEmail();
		
		member.setEmail(email);
		
		int cnt = mapper.update(member);
		
		if(cnt>0) {
			System.out.println("update s");
			session.setAttribute("user", member);
		} else {
			System.out.println("update f");
		}
		
		// 3. veiws
		return "redirect:/goMain";
	}
	
	// void는 포워드 전송이 장되서 mapping이라 같은 곳으로 이동함.
	@RequestMapping("/socket")
	public void socket () {}
	
	
	
}
