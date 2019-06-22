package com.hanul.AA;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import community.CommunityServiceImpl;
import community.CommunityVO;
import community.Community_commentVO;

@Controller
public class AcommunityController {
	@Autowired private CommunityServiceImpl cmService;
	//커뮤니티 파일경로는 마지막 파일이 community
	
	
	//안드로이드 커뮤니티 댓글 업데이트
	@RequestMapping("/cm_cmt_update")
	public String cm_cmt_update(HttpServletRequest req) {
		System.out.println("cm_cmt_update");
		Community_commentVO vo = new Community_commentVO();
		vo.setNo(Integer.parseInt(req.getParameter("no")));
		vo.setContent(req.getParameter("content"));
		cmService.cm_cmt_update(vo);
		return "main/android/nothing";
	}
	
	//안드로이드 커뮤니티 댓글 삭제
	@RequestMapping("/cm_cmt_delete")
	public String cm_cmt_delete(HttpServletRequest req) {
		System.out.println("Community_commentDelete");
		int no = (Integer.parseInt(req.getParameter("no")));
		cmService.cm_cmt_delete(no);
		return "main/android/nothing";
	}
	
	//안드로이드 커뮤니티 댓글 입력
	@RequestMapping("/cm_cmt_insert")
	public String cm_cmt_insert(HttpServletRequest req) {
		System.out.println("cm_cmt_insert");
		Community_commentVO vo = new Community_commentVO();
		vo.setComu_no(Integer.parseInt(req.getParameter("comu_no")));
		vo.setContent(req.getParameter("content"));
		vo.setWriter(req.getParameter("writer"));
		
		cmService.cm_cmt_insert(vo);
		
		return "main/android/nothing";
	}
	
	//안드로이드 커뮤니티 댓글 리스트
	@RequestMapping("/cmmcSelect")
	public String cmmcSelect(Model model,@RequestParam int no) {
		System.out.println("cmmcSelect");
		model.addAttribute("cmmcList",cmService.cmmcSelect(no));
		return "main/android/cmmcSelect";
	}
	
	//안드로이드 커뮤니티글 삭제
	@RequestMapping(value="/cmdelete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(HttpServletRequest req) {
		System.out.println("cmdelete");
		int no = Integer.parseInt(req.getParameter("no"));
		cmService.delete(no);
		return "main/android/nothing";
	}
	
	//안드로이드 커뮤니티 상세정보
	@RequestMapping(value="/cmdetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String detail(Model model, @RequestParam int no) {
		System.out.println("cmdetail");
		model.addAttribute("vo", cmService.detail(no));
		return "main/android/communityDetail";
	}
	
	//안드로이드 커뮤니티 수정 업데이트
	@RequestMapping(value="/cmupdate", method= {RequestMethod.GET, RequestMethod.POST})
	public String communityUpdate(HttpServletRequest req) {
		System.out.println("cmupdate");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}
		int no = Integer.parseInt(req.getParameter("no"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String uploadtype = (String) req.getParameter("uploadType");
		String dbImgPath = (String) req.getParameter("dbImgPath");
		String fileName= (String) req.getParameter("fileName");
		System.out.println(dbImgPath);
		
		MultipartRequest multi = (MultipartRequest)req;
		MultipartFile file = null;
		
		System.out.println("업로드 타입 "+uploadtype);
		
		if(uploadtype != null && uploadtype.equals("image")) {
			file = multi.getFile("image");	
			
			if(file != null) {
				//fileName = file.getOriginalFilename();
				System.out.println(fileName);
				
				// 디렉토리 존재하지 않으면 생성
				makeDir(req);	
				
				if(file.getSize() > 0){			
					String realImgPath = req.getSession().getServletContext()
							.getRealPath("/resources/images/community/");
					
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
							.getRealPath("/resources/images/community/" + fileName);
					System.out.println(fileName + " : " + realImgPath);
				}
			}
		}
		
		if(uploadtype != null && uploadtype.equals("delete")) {
			uploadtype = "image";
			fileName = "";
			dbImgPath = "";
		}
		
		CommunityVO vo = new CommunityVO();
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setFileName(fileName);
		vo.setFilePath(dbImgPath);
		vo.setUploadType(uploadtype);
		
		cmService.update(vo);
		
		return "main/android/nothing";
	}
	
	//안드로이드 커뮤니티 리스트
	@RequestMapping(value="/cmselect", method= {RequestMethod.GET, RequestMethod.POST})
	public String communitySelect(Model model) {
		System.out.println("cmselect"); // 실행여부
		
		model.addAttribute("list", cmService.readCommunity());
		return "main/android/communitySelectList";
	}
	
	//안드로이드 커뮤니티 글 입력
	@RequestMapping(value="/cminsert", method = {RequestMethod.GET, RequestMethod.POST} )
	public String communityInsert(HttpServletRequest req, Model model) {
		System.out.println("cminsert"); // 실행여부
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.getMessage();
		}

		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
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
							.getRealPath("/resources/images/community/");
					
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
							.getRealPath("/resources/images/community/" + fileName);
					System.out.println(fileName + " : " + realImgPath);
				}
			}
		}
		CommunityVO vo = new CommunityVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		vo.setFileName(fileName);
		vo.setFilePath(dbImgPath);
		
		cmService.insert(vo);
		
		return "main/android/nothing";
	}
	
	//파일경로 만들어주는 메소드
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
			.getRealPath("/resources/images/community"));
		if(!f2.isDirectory()){
		f2.mkdir();
		}	
	}
	
}
