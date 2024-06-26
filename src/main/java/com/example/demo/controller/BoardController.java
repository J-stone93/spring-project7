package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired //서비스에서 만든 메소드 사용하기 때문에
	BoardService service;
	
	// void: url 경로가 파일의 경로 String: 주소를 직접 입력
	// 메인 화면을 반환하는 메소드
	@GetMapping("/main")
	public void main() {
		
	}
	
	// 목록화면
	@GetMapping("/list")
	public void list(Model model) {
		
		// 서비스를 사용해서 게시물 목록 조회
		List<BoardDTO> list = service.getList();
		
		// 화면에 게시물 목록 전달
		model.addAttribute("list", list);
	}
	
	// 등록화면을 반환하는 메소드
	@GetMapping("/register")
	public void register() {
		System.out.println();
	}
	
	// 1.dto: 폼에서 전달 받은 게시물 정보
	// 2.RedirectAttributes: 다른 주소를 리다이렉트할때 화면에 데이터를 전달하는 객체(Model과 비슷)
	// 등록을 처리하는 메소드
	@PostMapping("/register")
	public String registerPost(BoardDTO dto, 
									RedirectAttributes redirectAttributes) {
		
		// 게시물 등록하고 새로운 게시물 번호 반환
		int no = service.register(dto);
		
		// 리다이렉트된 페이지(목록화면)에 새로운 게시물 번호 전달
		redirectAttributes.addFlashAttribute("msg", no);
		
		// 게시물 목록 화면으로 리다이렉트 하기
		// 리다이렉트? 새로운 URL을 다시 호출하는 것
		return "redirect:/board/list"; // HTML파일X URL주소O
	}
	
	// 게시물 상세보기하는 메소드
	// localhost:8080?no=1
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		
		BoardDTO dto = service.read(no);
		
		model.addAttribute("dto", dto);
		
	}
	
	// localhost:8080/board/modify?no=1
	// 게시물 수정 화면을 반환하는 메소드
	@GetMapping("/modify")
    public void modify(@RequestParam(name = "no") int no, Model model) {
		
        BoardDTO dto = service.read(no); // 게시물 번호로 조회
        
        model.addAttribute("dto", dto); // 화면에 게시물 정보 전달
    }
	
	// 게시물 수정하는 메소드
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {
		
		service.modify(dto); // 게시물 수정
		
		
		// addFlashAttribute(): 리다이렉트 할 화면에 데이터를 보내는 함수
		// addAttribute(): 리다이렉트 주소에 파라미터 추가하는 함수 (/board/read?no=1)
		redirectAttributes.addAttribute("no", dto.getNo());
		
		// 상세화면으로 이동
		return "redirect:/board/read";
	}
	
	// 게시물 삭제하는 메소드
	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		
		service.remove(no);
		
		return "redirect:/board/list";
	}
}










