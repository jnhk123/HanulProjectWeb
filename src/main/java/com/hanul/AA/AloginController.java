package com.hanul.AA;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import android.AndroidServiceImpl;
import client.ClientServiceImpl;
import client.ClientVO;
import status.Snowpiercer;

@Controller
public class AloginController {
	@Autowired private ClientServiceImpl Cservice;

	
	//아이디 중복체크
	@RequestMapping(value="/AloginCheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginCheck(@RequestParam String id, Model model) {
		System.out.println("AloginCheck");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		boolean check = Cservice.ID_check(map);
		if(check) {
			ClientVO vo = Cservice.snsloginRequest(map);
			model.addAttribute("vo",vo);
			return "main/android/loginRequest";
		}else {
			return "main/android/loginFail";
		}
		
	}
	//비밀번호 찾기
	@RequestMapping(value="/AFindPw",method= {RequestMethod.GET, RequestMethod.POST})
	public String findPw(@RequestParam String id, @RequestParam String name, @RequestParam String email,  Model model) {
		System.out.println("AFindPw");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		boolean check = Cservice.Find_PW(map);
		if(check) {
			ClientVO vo= Cservice.loginRequest(map);
			model.addAttribute("vo", vo);
			return "main/android/loginRequest";
		}else {
			return "main/android/loginFail";
		}
	}
	
	
	//아이디 찾기
	@RequestMapping(value="/AFindId",method= {RequestMethod.GET, RequestMethod.POST})
	public String findId(@RequestParam String name, @RequestParam String email,  Model model) {
		System.out.println("AFindId");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		boolean check = Cservice.Find_ID(map);
		if(check) {
			ClientVO vo= Cservice.loginRequest(map);
			model.addAttribute("vo", vo);
			return "main/android/loginRequest";
		}else {
			return "main/android/loginFail";
		}
	}
	
	//로그인
	@RequestMapping(value="/AloginRequest", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginRequest(@RequestParam String id, @RequestParam String pw, Model model) {
		System.out.println("AloginRequest"); //실행여부
		
		//Snowpiercer.snowpiercerStop = true;
		
		
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		boolean check = Cservice.ID_PW_check(map);
		if(check) {
			ClientVO vo = Cservice.loginRequest(map);
			model.addAttribute("vo",vo);
			return "main/android/loginRequest";
		}else {
			return "main/android/loginFail";
		}
	}
	
	
	@RequestMapping(value="/uinsert", method= {RequestMethod.GET, RequestMethod.POST})
	public String Cinsert(HttpServletRequest req, Model model) {
		System.out.println("uinsert"); //실행여부
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		String name = req.getParameter("name");
		String id= req.getParameter("id");
		String pw= req.getParameter("pw");
		String email= req.getParameter("email");
		
	
		ClientVO vo=new ClientVO();
		vo.setName(name);
		vo.setId(id);
		vo.setPw(pw);
		vo.setEmail(email);
		
		System.out.println(id);
		System.out.println(name);
		System.out.println(pw);
		System.out.println(email);
		
		Cservice.ainsert(vo);
		
		return "main/android/nothing";
	}
	
	@RequestMapping("/uupdate")
	public String update(HttpServletRequest req) {
		System.out.println("uupdate");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String uploadtype = (String) req.getParameter("uploadType");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String fileName= (String) req.getParameter("fileName");
		System.out.println(dbImgPath);
		
		
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		if(uploadtype != null && uploadtype.equals("image")) {
			file = multi.getFile("image");	
			
			if(file != null) {
				//fileName = file.getOriginalFilename();
				System.out.println(fileName);
				
				// 디렉토리 존재하지 않으면 생성
				makeDir(req);	
				
				if(file.getSize() > 0){			
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/profile/");
					
					System.out.println( fileName + " : " + realImgPath);
					System.out.println( "fileSize : " + file.getSize());	
					
					
					// 이미지파일 저장				
				 	try {
						file.transferTo(new File(realImgPath, fileName));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
										
				}else{
					fileName = "FileFail.jpg";
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/profile/" + fileName);
					System.out.println(fileName + " : " + realImgPath);
				}
			}
		}
		
		if(uploadtype != null && uploadtype.equals("delete")) {
			uploadtype = "image";
			fileName = "";
			dbImgPath = "";
		}
		
		ClientVO vo = new ClientVO();
		vo.setId(id);
		vo.setName(name);
		vo.setPw(pw);
		vo.setProfile(dbImgPath);
		vo.setProfileName(fileName);
		vo.setUploadType(uploadtype);
		
		Cservice.androidUpdate(vo);
		
		return "main/android/nothing";
	}
	
	
	public void makeDir(HttpServletRequest req){
		File f = new File(req.getSession().getServletContext()
				.getRealPath("/resources"));
		if(!f.isDirectory()){
		f.mkdir();
		}
		
		File f1 = new File(req.getSession().getServletContext()
				.getRealPath("/resources/images"));
		if(!f1.isDirectory()){			
		f1.mkdir();
		}		
				
		File f2 = new File(req.getSession().getServletContext()
			.getRealPath("/resources/images/upload"));
		if(!f2.isDirectory()){
		f2.mkdir();
		}	
	}
}
