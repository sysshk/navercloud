package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.smhrd.entity.Board;

@Mapper
public interface BoardMapper {

	public List<Board> list();
	// 함수명은 xml과 동일하게 되어야 가져다 쓸 수 있어\\
	
	public int write(Board board);
	
	public Board view(int idx);
	
	@Delete("""
			delete from Board 
			where idx =#{idx}
			""")
	public int delete(int idx);
	
	public List<Board> search(String text);	
	
	
	// chart만들기
	public List<Board> chart();
	
	
}
