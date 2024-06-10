package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;

@Service // Service 어노테이션으로 구현부로 쓸 클래스에 붙여 바꿀 수 있다
public class BoardServiceImpl implements BoardService {

	@Override
	public int register(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
