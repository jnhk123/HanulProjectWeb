package com.hanul.AA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import client.ClientServiceImpl;
import client.ClientVO;
import status.AddrVO;

@Controller
@SessionAttributes({ "category" })
public class ClientController {
	@Autowired
	private ClientServiceImpl service;

	//비번 변경
	@RequestMapping(value="/new.pw", method= {RequestMethod.POST})
	public String newPw(@RequestParam String id, @RequestParam String pw) {
		service.newPw(id, pw);
		return "redirect:home";
	}
	
	
	//비번 변경 화면 연결
	@RequestMapping(value= "/change.pw", method= {RequestMethod.POST})
	public String changePW(@RequestParam String id, Model model) {
		model.addAttribute("id", id);
		return "client/change_pw";
	}
	
	// 아이디 찾기
	@ResponseBody @RequestMapping("/findId")
	public String findId(@RequestParam String name, @RequestParam String email) {
		ClientVO vo = new ClientVO();
		vo.setName(name);
		vo.setEmail(email);
		return service.findId(vo);
	}

	// 비번 찾기
	@ResponseBody @RequestMapping("/findPw")
	public String findPw(@RequestParam String name, @RequestParam String email, @RequestParam String id) {
		ClientVO vo = new ClientVO();
		vo.setName(name);
		vo.setEmail(email);
		vo.setId(id);
		return service.findPw(vo);
	}

	// 회원 아이디 찾는 화면 요청
	@RequestMapping("/findId.cs")
	public String findIdPage() {
		return "client/findId";
	}

	// 회원 비번 찾는 화면 요청
	@RequestMapping("/findPw.cs")
	public String findPwPage() {
		return "client/findPw";
	}

	// 새로운 주소 추가
	@RequestMapping("/insertAddr.cs")
	public String insertAddr(AddrVO vo) {
		service.insertAddr(vo);
		return "status/status";
	}

	// 새로운 주소 추가 화면 요청
	@RequestMapping("/newAddr")
	public String newAddr() {
		return "client/newAddr";
	}

	// 회원가입화면 요청
	@RequestMapping("/client.cs")
	public String Client(Model model) {
		model.addAttribute("category", "");
		return "client/join";
	}

	// 신규고객정보 저장처리 요청
	@RequestMapping("/insert.cs")
	public String insert(ClientVO vo) {
		// 화면에서 입력한 정보를 수집해 DB에 저장한 후,
		service.insert(vo);

		// 목록화면으로 연결
		return "redirect:home";
	}

	// 신규고객등록화면 요청
	@RequestMapping("/new.cs")
	public String customer() {
		return "customer/new";
	}

	// 아이디 중복 확인 요청 (이 자체가 요청)
	@ResponseBody
	@RequestMapping("/id_check")
	public String id_check(@RequestParam String id) {
		// 화면에서 입력한 아이디가 DB에 있는지를 조회한 후
		// 사용가능 여부를 반환한다.
		return String.valueOf(service.id_check(id));

	}

}
