package com.hanul.AA;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import common.CommonServiceImpl;
import common.CommonVO;
import qa.QaPage;
import qa.QaVO;
import qa.QaserviceImpl;
 
@Controller @SessionAttributes({"category", "title"})
public class QaController {
	@Autowired private QaserviceImpl service;
	@Autowired private CommonServiceImpl common;
	
	//문의사항 다운로드
	@RequestMapping("/cmDownload.qu") @ResponseBody
	public File download(@RequestParam int no, HttpSession session, HttpServletResponse response) {
		QaVO vo = service.detail(no);
		
		return common.download(vo.getFileRealPath(), vo.getFileName(), session, response);
	}
	//문의사항 글 삭제
	@RequestMapping("/delete.qu")
	public String delete(@RequestParam int no) {
		service.delete(no);
		return "redirect:list.qu";
	}
	
	//문의사항 글쓰고 데이터베이스 저장
	@RequestMapping("/insertData.qu")
	public String insertData(QaVO vo, HttpSession session, @RequestParam MultipartFile attach_file) {
		
		if(attach_file.getSize()>0) {
			HashMap<String, String> map = common.upload("upload", attach_file, session);
			vo.setFileRealPath(map.get("fileRealPath"));
			vo.setFileName(map.get("fileName"));
			vo.setFilePath(CommonVO.ip + "/AA/resources/images/upload/"+map.get("fileName"));
			System.out.println(map.get("fileRealPath"));
			System.out.println(map.get("fileName"));
			System.out.println(CommonVO.ip + "/AA/resources/images/upload/"+map.get("fileName"));
		}
		service.insert(vo);
		return "redirect:list.qu";
	}
	//문의사항 글 수정 업데이트
	@RequestMapping("/update.qu")
	public String update(QaVO vo, HttpSession session, @RequestParam MultipartFile attach_file){
		System.out.println("uploadType = "+vo.getUploadType());
		if(attach_file.getSize()>0) {
			HashMap<String, String> map = common.upload("upload", attach_file, session);
			vo.setFileRealPath(map.get("fileRealPath"));
			vo.setFileName(map.get("fileName"));
			vo.setFilePath(CommonVO.ip + "/AA/resources/images/upload/"+map.get("fileName"));
			System.out.println(map.get("fileRealPath"));
			System.out.println(map.get("fileName"));
			System.out.println(CommonVO.ip + "/AA/resources/images/upload/"+map.get("fileName"));

		}else if(vo.getUploadType() == null){
			vo.setUploadType("");
			System.out.println("널로 변경");
		}
		service.updateFile(vo);
		return "redirect:detail.qu?no="+vo.getNo();
	}
	//문의사항 수정페이지 요청
	@RequestMapping("/modifyPage.qu")
	public String modifyPage(@RequestParam int no, Model model) {
		model.addAttribute("vo", service.detail(no));
		return  "question/modifyPage_qu";
	}
	
	//문의사항 목록 요청
	@Autowired private QaPage page;
	@RequestMapping("/list.qu")
	public String readQa(Model model, 
		@RequestParam(defaultValue="1") int curPage,
		@RequestParam(defaultValue="all")String search, 
		@RequestParam(defaultValue="")String keyword
		, HttpSession session) {
	
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		session.removeAttribute("status_info");
		session.removeAttribute("myHomeExistense");
		model.addAttribute("category", "qu");
		model.addAttribute("title", "문의사항");
		model.addAttribute("page", service.readQa(page));
		return "question/list_qu";
	}
		
	//문의사항 세부내용 요청
	@RequestMapping("/detail.qu")
	public String questionDetail(@RequestParam int no, Model model) {
		QaVO tmp = service.detail(no);
		model.addAttribute("vo", service.detail(no));
		return "question/detail_qu";
	}
	
	//문의사항 글쓰기 화면 요청
	@RequestMapping("/insert.qu")
	public String insertForm() {
		return "question/insertForm_qu";
	}
	
}
