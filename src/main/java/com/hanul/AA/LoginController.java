package com.hanul.AA;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import client.ClientVO;
import login.LoginServiceImpl;
import login.LoginVO;

@Controller @SessionAttributes({"category"})
public class LoginController {
	
	@Autowired private LoginServiceImpl service;
	public static ClientVO login;
	
	//웹 페이스북 로그인
	@RequestMapping("/facebook_login") @ResponseBody
	public String facebook_login(LoginVO vo, HttpSession session) {
		login = service.facebook_login(vo);
		session.setAttribute("login",login);
		return null;
	}
	
	//웹 카카오톡 로그인
	@RequestMapping("/kakao_login") @ResponseBody
	public String kakao_login(LoginVO vo, HttpSession session,
			Model model) {
		login = service.kakaoLogin(vo);
		session.setAttribute("login",login);
		return null;
	}
	
	// 로그인 화면 요청
	@RequestMapping("/login.cs")
	public String login(Model model) {
		model.addAttribute("category", "");
		return "main/login/login";
	}
	
	//로그아웃처리 요청
	@ResponseBody @RequestMapping("/logout")
	public void logout(HttpSession session) {
		//세션에 있는 로그인정보를 삭제한 후
		session.removeAttribute("login");
		login = null;
		//요청한 곳으로 돌아간다.
	}
	
	//로그인 처리 요청
	@ResponseBody @RequestMapping("/login")
	public boolean login(@RequestParam String id, @RequestParam String pw, HttpSession session,
			Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		login = service.login(map);
		
		session.setAttribute("login", login);
//		if (vo != null)
//			session.setAttribute("login", vo);
		return (login != null ? true : false);
	}
}
