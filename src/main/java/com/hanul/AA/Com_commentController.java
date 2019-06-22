package com.hanul.AA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import android.AndroidServiceImpl;
import community.Com_commentImpl;
import community.Com_commentVO;
import community.CommunityServiceImpl;
import community.CommunityVO;

@Controller
public class Com_commentController {
	
	@Autowired Com_commentImpl service;
	@Autowired CommunityServiceImpl community_service;
	@Autowired AndroidServiceImpl and_service;

	//커뮤니티 댓글 글 수정 업데이트
	@RequestMapping("/com_update")
	public String update(Com_commentVO vo) {
		service.update(vo);
		
		return "redirect:detail.co?no="+vo.getComu_no();
	}
	
	//커뮤니티 댓글 삭제
	@RequestMapping("/com_delete")
	public String delete(@RequestParam int no, @RequestParam int comu_no) {
		service.delete(no);
		return "redirect:detail.co?no="+comu_no;
	}
	
	//커뮤니티 댓글 글 입력
	@RequestMapping("/com_insert")
	public String insert(Com_commentVO vo, Model model, HttpSession session, HttpServletRequest request) { 
		CommunityVO com_vo = community_service.detail(vo.getComu_no());
		System.out.println(com_vo.getWriter());
		//푸쉬알림을 위한 메소드 
		try {
			sendOne(model, request, session, com_vo.getWriter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.insert(vo);
		return "redirect:detail.co?no="+vo.getComu_no()+"&id="+LoginController.login.getId();
	}
	
	//웹용 개인 푸쉬알림
	public void sendOne(Model model, HttpServletRequest request, HttpSession session,
			@RequestParam String id)
			throws Exception {
		System.out.println("PushComment");
		String deviceToken = and_service.getToken(id);
		if(deviceToken != null) {
		//System.out.println(deviceToken);
		final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key=" + apiKey);

		conn.setDoOutput(true);

		String userId = (String) request.getSession().getAttribute("ssUserId");

		// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
		String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \" 댓글이 입력되었습니다. \", \"id\" : \""+id+"\" }, \"to\":\""+ deviceToken +"\"}";

		OutputStream os = conn.getOutputStream();

		// 서버에서 날려서 한글 깨지는 사람은 아래처럼 UTF-8로 인코딩해서 날려주자
		os.write(input.getBytes("UTF-8"));
		os.flush();
		os.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + input);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());
		}else {
			System.out.println("알림 안보냄");
		}
//		return "main/android/jsonView";
	}
	
	
}
