package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;

// 단위테스트의 목적: 리파지토리가 가지고 있는 기능을 단독으로 테스트하기 위함
@SpringBootTest // 컨테이너를 임시로 생성
public class BoardRepositoryTest {

	@Autowired // 빈(객체)을 가져오기
	BoardRepository repository;
	
	@Test
	void 빈을가지고왔는지확인() {
		System.out.println("repository의 주소: " + repository);
	}
	
	@Test
	void 게시물등록() {
		// 게시물번호와 시간은 시스템에서 입력해주는 부분이라 입력 필요 없음
		Board board = Board.builder()
								.title("1번글")
								.content("내용입니다")
								.writer("둘리")
								.build();
		
		System.out.println("등록전:" + board.toString());
		
		repository.save(board);
		
		System.out.println("등록후:" + board.toString());
	}
	
	@Test
	void 게시물목록조회() {
		
		List<Board> list = repository.findAll();
		
		for (Board board : list) {
			System.out.println(board);
		}
		
	}
	
	@Test
	void 게시물단건조회() {
		
		Optional<Board> result = repository.findById(1);
		
		if (result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}
	
	@Test
	void 게시물수정() {
		Optional<Board> result = repository.findById(1);
		
		Board board = result.get();
		board.setContent("수정되었습니다");
		repository.save(board);
	}
	
	@Test
	void 게시물삭제() {
		repository.deleteById(2);
	}
	
	@Test
	void 게시물전체삭제() {
		repository.deleteAll();
	}
}
















