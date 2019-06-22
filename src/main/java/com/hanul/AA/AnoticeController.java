package com.hanul.AA;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import notice.NoticeServiceImpl;
import notice.NoticeVO;

@Controller
public class AnoticeController {
	@Autowired private NoticeServiceImpl Nservice;
	
	//안드로이드 공지사항 상세정보
	@RequestMapping(value="/ndetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String detail(@RequestParam int no, Model model) {
		System.out.println("ndetail");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NoticeVO vo = Nservice.noticeDetail(no);
		vo.setDate(sdf.format(vo.getWriteDate()));
		model.addAttribute("vo", vo);
		return "main/android/NoticeDetail";
	}
	//안드로이드 공지사항 리스트
	@RequestMapping(value="/nselect", method= {RequestMethod.GET, RequestMethod.POST})
	public String select(Model model) {
		System.out.println("nselect"); //실행여부
		
		List<NoticeVO> list = Nservice.readNotice();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(NoticeVO vo : list) {
			vo.setDate(sdf.format(vo.getWriteDate()));
		}
		model.addAttribute("list",list);
		return "main/android/NoticeSelectList";
	}
	//안드로이드 공지사항 글 입력
	@RequestMapping(value="/ninsert", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String Ainsert(HttpServletRequest req, Model model) {
		System.out.println("ninsert"); //실행여부
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setTitle(title);
		vo.setContent(content);
		
		Nservice.insert(vo);

		return "main/android/nothing";
	}
	//안드로이드 공지사항 글 수정 업데이트
	@RequestMapping(value="/nupdate", method = {RequestMethod.GET, RequestMethod.POST})
	public String nUpdate(HttpServletRequest req, Model model) {
		System.out.println("nupdate"); //실행여부
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		int no = Integer.parseInt(req.getParameter("no"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		NoticeVO vo = new NoticeVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		
		Nservice.update(vo);
		
		return "main/android/nothing";
	}
}
