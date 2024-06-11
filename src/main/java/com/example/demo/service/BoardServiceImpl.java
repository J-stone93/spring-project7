package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
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

}








