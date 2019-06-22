package common;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonServiceImpl implements CommonService {

	//파일 업로드 메소드
	@Override
	public HashMap<String, String> upload(String category, MultipartFile file, HttpSession session) {
		//업로드할 물리적 위치
		//D:\Study_Spring\.metadata\.plugins\o..webapps\iot\resources
		String resources = session.getServletContext().getRealPath("resources");
//		D:\Study_Spring\.metadat..webapps\iot\resources\images\_upload
		String upload = resources + File.separator + "images" + File.separator + category ;
		
		//D:\Study_Spring\..iot\resources\\images\\upload\\fileName

		Date now = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append(new SimpleDateFormat("yyyy").format(now));
		sb.append(new SimpleDateFormat("MM").format(now));
		sb.append(new SimpleDateFormat("dd").format(now));
		
		
		String uuid = sb.toString() + file.getOriginalFilename();
		try {
			file.transferTo( new File(upload, uuid) );
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("fileRealPath", upload.substring(resources.length()) 
				+ File.separator + uuid);
		map.put("fileName",uuid);
		return map;
	}

	//파일 다운로드하는 메소드
	@Override
	public File download(String filepath, String filename,
			HttpSession session, HttpServletResponse response) {

		//다운로드해올 파일이 있는 물리적 위치
		File file = new File( session.getServletContext()
					.getRealPath("resources") + filepath );
		String mime 
		= session.getServletContext().getMimeType(filename);
		if( mime == null )	mime = "application/octet-stream";
		response.setContentType(mime);
		
		try {
			//한글파일명처리
			filename = URLEncoder.encode(filename, "utf-8");
			
			response.setHeader(
				"content-disposition", 
				"attachment; filename=" + filename);
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
		}catch(Exception e) {
		}
		return file;
	}

	
	/////////////////////////////////밑으로는 구현 아직 안함 이메일 기능
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void emailSend(String email, String name) {
		//이메일수신할 이메일주소, 수신자성명
		basicEmail(email, name);
		
	}
	
	@Override
	public void emailSend(String email, String name, HttpSession session) {
//		attachEmail(email, name, session);	
		htmlEmail(email, name, session);	
	}
	
	
	private void htmlEmail(String email, String name, HttpSession session) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		mail.setAuthentication("ojink2", "");
		mail.setSSLOnConnect(true);
		
		try {
		mail.setFrom("ojink2@naver.com", "관리자");
		mail.addTo(email, name);
		
		mail.setSubject("Html 태그 형태로 보내기");
		StringBuilder sb = new StringBuilder();
		sb.append( "<html>" );
		sb.append( "<body>" );
		sb.append( "<a href='http://hanuledu.co.kr'>한울 홈 연결</a>" );
		sb.append( "<img src='https://mvnrepository.com/assets/images/392dffac024b9632664e6f2c0cac6fe5-logo.png'/>" );
		sb.append("<h3>환영합니다</h3>");
		sb.append("내용작성하기<br>");
		sb.append("완성!!");		
		sb.append( "</body>" );		
		sb.append( "</html>" );
		mail.setHtmlMsg( sb.toString() );
		
		EmailAttachment attach = new EmailAttachment();
		attach.setPath( session.getServletContext()
				.getRealPath("resources") 
				+ File.separator + "img"
				+ File.separator + "hanul.logo.png");
		mail.attach(attach);
	
		mail.send();
		
		}catch(Exception e) {
		}
		
	}
	
	private void attachEmail(
		String email, String name, HttpSession session) {
		
		MultiPartEmail mail = new MultiPartEmail();
		
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		mail.setAuthentication("ojink2", "");
		mail.setSSLOnConnect(true);
		
		try {
		mail.setFrom("ojink2@naver.com", "관리자");
		mail.addTo(email, name);
		
		mail.setSubject("파일첨부되었습니다. 확인요망");
		mail.setMsg("첨부된 파일 잘 수신되었나 확인하세요~");
		
		//첨부파일객체 
		EmailAttachment attach = new EmailAttachment();
		attach.setPath( session.getServletContext()
				.getRealPath("resources") + File.separator + "img"
				+ File.separator + "hanul.logo.png");
		mail.attach(attach);
		
		attach = new EmailAttachment();
		attach.setURL( new URL("https://mvnrepository.com/assets/images/6a606fcf7b8526f25d1fc9b3fe8f39ad-growth.png") );
		mail.attach(attach);
		mail.send();
		}catch(Exception e) {}
	
	}
	
	
	private void basicEmail(String email, String name) {
		SimpleEmail mail = new SimpleEmail();
		
		//이메일 송신 서버 지정
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//메일서버에 로그인하기 위한 아이디/비번 지정
		mail.setAuthentication("ojink2", "");
		mail.setSSLOnConnect(true);
		
		try {
		mail.setFrom("ojink2@naver.com", "관리자");
		//수신인 지정
		mail.addTo(email, name); //가입회원의 정보
		
		mail.setSubject("한울 IoT 과정 입교 축하");
		mail.setMsg("IoT 과정의 훈련을 축하합니다!!");
		
		//메일전송
		mail.send();
		
		}catch(Exception e) {
			
		}
	}
	

}










