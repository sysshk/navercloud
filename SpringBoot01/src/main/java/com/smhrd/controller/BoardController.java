package com.smhrd.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list")
	public String list(Model model) {
		// 1. 데이터수집
		// 2. 기능
		List<Board> list = mapper.list();
		
		// 리스트가 리턴되지 않아서 출력되지 않음.
		// Scope : 정보 저장(유지_ 4개의 내장객체 
		// page : 하나의 jsp 안에서만 사용 가능 
		// request*** : 한번의 요청 - 응답동안만 유지  <<< 요거사용, 알아서 사라지기때문에 바로 사라지기때문에
		// 		model 객체 >> spring에서나 사용, 다이어트한 request, 저장기능외에 다른 기능을 제거한 request
		// session : 하나의 브라우저 내에서
		// application : 서버 종료시까지, 공용공간
		model.addAttribute("list", list);
		// 3. view 이동
		return "boardMain"; //forward 이동
	}
	
	@RequestMapping("/writerBoard")
	public void writerBoard() {
		// 1. 데이터 수집
		// 2. 기능 실행
		// 3. View 선택
		// return "WriterBoard";
		// 리턴 타입이 void인 경우 >> urlMapping을 jsp이름이라 간주하고 forward함
	}
	
	
	// @Value를 이용해서 변수 값을 채울 수 있음.
	// 어노테이션 안에서 ${프로퍼티 이름}을 사용해서 application.properties에 정의해둔 데이터를 가져올 수 있음.
	@Value("${save.path}")
	private String savePath;
	// 배포를 하기전에 경로를 생각해야함.
	
	@RequestMapping("/write")
	public String write(MultipartFile file, Board board) {
		// 매개변수로 제목, 작성자 내용을 가져옴
		// file 수집하는 방법
		// (1) 전송된 파일을 수집할 때, MultipartFile 자료형으로 선언
		// (2) 파일을 저장할 폴더(경로에 한글X)
		// (3) 폴더 경로 저장
		if(file==null) {
			board.setImg("none");
			System.out.println("출력");
		} else {
			// 에러가 발생할 수있으므로 try-catch 사용하자
			try {
				// 1) 앞에 랜덤한 문자열을 붙여서, 파일 이름 중복 방지
				// UUID : 16자리로 랜덤해줌
				String uuid = UUID.randomUUID().toString();
				String filename = uuid + file.getOriginalFilename();
				
				// 2) 전체 경로(폴더 경로 + 파일 이름)
				Path path = Paths.get(savePath + filename);
				
				System.out.println(path);
						
				// 3) 저장
				file.transferTo(path);
				
				board.setImg(filename);
				
			} catch (Exception e) {
				e.printStackTrace(); // 오류메세지 출력코드
			}
			
		}
		
		// 2. 기능 실행
		mapper.write(board);
		
		// 3. view 이동
		return "redirect:/list";
		
	}
	
	// uri는 작성하지 않아도 됨.
	@RequestMapping("/view")
	public String view(int idx, Model model) {
		// 1개의 데이터는 변수로 가져올 수있다.
		Board board = mapper.view(idx);
		
		model.addAttribute("board", board);
		
		return "viewBoard";
	}
	
			// ~~~~~/delete/3 <3을 idx로 보겠다
	@RequestMapping("/delete/{idx}")
	public String delete(@PathVariable("idx") int idx) {
		// 1. 데이터 수집
		// 2. 기능 실행
		
		mapper.delete(idx);
		// 3. View 이동
		return "redirect:/list";
	}
	
	// 여기부터 axios
	
	@RequestMapping("/axios")
	public void axios() {};
	
	
	
}
