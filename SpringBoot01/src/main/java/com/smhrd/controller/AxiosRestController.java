package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;

@RestController  // 데이터를 응답하는 Controller에 RestController 어노테이션을 붙임.
// >> @ResponseBody를 붙이지 않아도 됨.
public class AxiosRestController {
	
	// REST : 요청이 발생했을 떄, 응답으로 데이터를 돌려주는 구조
	
	// REST API : 요청에 따라 정보를 제공하는 api
	
	
	// 리턴 문자열이 == 응답할 데이터 
	// 문자열만 응답되도록 responseBody를 붙임.
	@ResponseBody
	@RequestMapping("/axios01")
	public String axios01() {
		// 1. 데이터 수집
		// 2. 기능 실행
		// 3. 데이터 응답
		System.out.println("request");
		
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/axios02")
	public String axios02(String text) {
		// 1.데이터 수집
		// 쿼리스트링 이름이 text이니 text로 매개변수를 가져옴
		System.out.println(text);
		// 2. 기능 실행
		// 3. 데이터 응답
		return "GET";
	}
	
	@Autowired
	private BoardMapper mapper;
	
	// RequestMapping 속성값
	// value : URL mapping
	// produces : 응답 데이터 형식을 지정
	@ResponseBody
	@RequestMapping(value="/axios03", produces = "text/plain; charset=UTF-8")
	public List<Board> axios03(@RequestBody String data) {
		// 1. 데이터 수집 // post로 보내면 json형태가 됨.
		// post 방식으로 데이터를 받아올 때, 만약 이름값이 정해지지 않았다면
		// --> 수집할 변수 앞에 @RequestBody 어노테이션을 붙임
		System.out.println(data);
		
		// JSON 데이터는 String
		// GSON : JSON(문자열) <--> java object
		Gson gson = new Gson();
		
		// JSON --> java object
		// fromJson("json", 옴겨 담을 클래스 정보)
		// json의 key == DTO의 필드 변수명
		
		Board board = gson.fromJson(data, Board.class);
		System.out.println(board.getTitle());
		
		// 2. 기능실행
		List<Board> list = mapper.list();
		
		// 3. 데이터 응답
		// CSV, XML, JSON
//		String json = gson.toJson(list); 이것도할필요 없어
//		return json;
		
		// {} 객체 형태 [] json 어레이
		
		// jackson databind 응답되기 전에 문자열 데이터로 변환해줌
		// : java 객체 리턴시, json으로 변환이 일어남
		
		return list;
		
	}
	
	// @RequestBody 포스트방식인경우 만 사용
	
}
