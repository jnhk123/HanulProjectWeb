package com.hanul.AA;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import client.ClientServiceImpl;
import client.ClientVO;
import status.StatusServiceImpl;

@Controller
public class AclientController {
	@Autowired
	private ClientServiceImpl uService;
	@Autowired
	private StatusServiceImpl sService;

	/*
	@RequestMapping("/Uupdate")
	public String Uupdate(HttpServletRequest req) {
		System.out.println("Uupdate");
		try {
			req.setCharacterEncoding("UTF-8");
		}catch(Exception e) {
			e.getMessage();
		}
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String email=req.getParameter("email");
		String addr=req.getParameter("addr");
		
		ClientVO vo = new ClientVO();
		vo.setId(id);
		vo.setName(name);
		vo.setEmail(email);
		vo.setPw(pw);
		vo.setAddr(addr);
		
		return "main/android/noting";
	}
*/

	@RequestMapping(value = "/hselect", method = { RequestMethod.GET, RequestMethod.POST })
	public String myHomeList(Model model, @RequestParam String id) {
		System.out.println("hselect");
		System.out.println(id);
		model.addAttribute("hlist", sService.getAddrs(id));
		
		return "main/android/myhomeSelectList";
	}
	@RequestMapping(value="/udelete", method= {RequestMethod.GET, RequestMethod.POST})
		public String delete(HttpServletRequest req) {
		System.out.println("udelete");
		String id=req.getParameter("id");
		uService.delete(id);
			return "main/android/nothing";
	}
	@RequestMapping(value="/udetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String detail(@RequestParam String id, Model model) {
		System.out.println("udetail");
		model.addAttribute("vo", uService.detail(id));
		return "main/android/userDetail";
	}
	
	@RequestMapping(value = "/uselect", method = { RequestMethod.GET, RequestMethod.POST })
	public String userSelect(Model model) {
		System.out.println("uselect");//실행시 출력
		
		model.addAttribute("list", uService.list());
		return "main/android/userSelectList";
	}

}
