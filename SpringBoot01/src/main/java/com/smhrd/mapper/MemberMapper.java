package com.smhrd.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Member;

@Mapper
public interface MemberMapper {

	// 1. 연결
	// jdbc : 기존방식 --> 기능을 실행시마다 connection생성 --> DB부하가 커짐
	// >> DataBase Connection Pool(DataSource)
	//    미리 만들어두고, 빌려만 주자.
	
	// 2. 기능 구현(MyBatis Framework)
	// java <--(mapping)--> xml
	// (실행코드)	           (sql)
	// insert/delete/update int리턴
	// sql문의 id==메소드 이름
	public int join(Member member);
	
	
	public Member login(Member member);
	// 리스트인경우 자동으로 List<Member> 로 작성 여러가지
	
	public int update(Member member);
	
	public Member check(String email);
	
}
