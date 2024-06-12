package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service // Service 어노테이션으로 구현부로 쓸 클래스에 붙여 바꿀 수 있다
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository repository;
	
	@Override
	public int register(BoardDTO dto) {

		Board entity = dtoToEntity(dto); // dto -> entity 변환
		
		repository.save(entity); // 변환한 엔티티를 저장
		
		int newNo = entity.getNo();
		
		return newNo; // 새로운 글의 번호 반환
	}

	@Override
	public List<BoardDTO> getList() {
		
		List<Board> entityList = repository.findAll(); // 게시물 목록 조회
		
		// Entity 리스트 -> DTO 리스트 변환
		List<BoardDTO> dtoList = new ArrayList<>();
		
		dtoList = entityList.stream()
						.map(entity -> entityToDto(entity))
						.collect(Collectors.toList());
		
		return dtoList; // DTO 리스트 반환
		
	}

	@Override
	public BoardDTO read(int no) {
		
		Optional<Board> result = repository.findById(no);
		
		if(result.isPresent()) {
			Board board = result.get();
			BoardDTO boardDTO = entityToDto(board); // entity -> dto
			return boardDTO;
		} else {
			return null;			
		}
		
	}

	@Override
	public void modify(BoardDTO dto) {
		
		Optional<Board> result = repository.findById(dto.getNo());
		// 확인하고 저장해야하는 이유: 같은 화면을 공유 중인 두 사용자가 한명은 삭제 한명은 수정 할때 오류 발생을 잡기 위해
		if(result.isPresent()) {
			// 제목과 내용만 변경(사용자가 바꿀 수 있는 데이터)
			Board entity = result.get();
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			// 게시물 교체하기
			repository.save(entity);
		}
		
	}

}








