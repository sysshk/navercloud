package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor  // 기본생성자는 반드시 존재해야함.
@RequiredArgsConstructor //nonnull이 걸린 필드만 초기화
@AllArgsConstructor // 모든 필드를 초기화하는 생성자
@Data // getter/setter 등 기본 메소드 생성(Lombok)
public class Member {
	// 계층간 데이터 전송을 위해 사용되는 객체
	// DTO(Data Transfer Object)
	// 여러 데이터를 담을 바구니
	
	@NonNull   // lombok꺼로 만들기
	private String email;
	private String pw;
	private String tel;
	private String address ;
	
	
	
	
}
