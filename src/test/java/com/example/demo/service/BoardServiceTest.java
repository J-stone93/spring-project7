package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {

	// 단위테스트의 목적:
	// 전체테스트는 문제가 발생했을 때, 어떤 구간에서 문제가 발생했는지 확인이 어려워서
	// 단위테스트를 통해 미리 구간별 문제를 확인해야 오류 지점을 찾기 수월함
	
	@Autowired // 스프링부트테스트로 생성한 임시 컨테이너에서 빈(객체)을 꺼내옴 
	BoardService service;
	
	@Test
	public void 게시물등록() {
		
		BoardDTO dto = BoardDTO.builder()
								.title("2번글")
								.content("내용입니다")
								.writer("또치")
								.build();
		
		int no = service.register(dto);
		
		System.out.println("새로운 게시물 번호: " + no);
	}
	
	@Test
	public void 게시물목록조회() {
		
		List<BoardDTO> list = service.getList();
		
		for (BoardDTO boardDTO : list) {
			System.out.println(boardDTO);
		}
	}
	
	@Test
	public void 게시물단건조회() {
		
		BoardDTO dto = service.read(1);
		
		System.out.println(dto);
		
	}
	
	@Test
	public void 게시물수정() {
		
		BoardDTO dto = service.read(1);
		dto.setContent("내용이 수정되었습니다!!!");
		service.modify(dto);
		
	}
	
	@Test
	public void 게시물삭제() {
		
		service.remove(13);
	}
}













