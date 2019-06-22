package com.hanul.AA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import android.AndroidServiceImpl;
import android.TokenVO;
import common.CommonService;
import community.PushVO;
import status.StatusService;

@Controller
public class ApushMessageController {

	@Autowired
	AndroidServiceImpl service;
	@Autowired
	CommonService common_service;
	@Autowired
	StatusService status_service;

	// 문열림 메세지 알림
	@RequestMapping("/openDoor.status")
	@ResponseBody
	public void openDoor(Model model, HttpServletRequest request, HttpSession session, String id,
			@RequestParam String doorCheck) throws Exception {
		if (doorCheck.equals("push")) {
			System.out.println("PushComment");
			String deviceToken = service.getToken(id);
			if (deviceToken != null) {
				// System.out.println(deviceToken);
				final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
				URL url = new URL("https://fcm.googleapis.com/fcm/send");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Authorization", "key=" + apiKey);

				conn.setDoOutput(true);

				String userId = (String) request.getSession().getAttribute("ssUserId");

				// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
				// String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" :
				// \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";

				// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
				String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \" 현관문이 열렸습니다. \", \"id\" : \""
						+ id + "\" }, \"to\":\"" + deviceToken + "\"}";

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
			} else {
				System.out.println("알림 안보냄");
			}
		}
		// 문열림 상태로 데이터베이스 업데이트
		status_service.doorOpen(id);

	}

	// 문닫힘 메세지 알림
	@RequestMapping("/closeDoor.status")
	@ResponseBody
	public void closeDoor(Model model, HttpServletRequest request, HttpSession session, String id,
			@RequestParam String doorCheck) throws Exception {
		if (doorCheck.equals("push")) {
			System.out.println("PushComment");
			String deviceToken = service.getToken(id);
			if (deviceToken != null) {
				// System.out.println(deviceToken);
				final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
				URL url = new URL("https://fcm.googleapis.com/fcm/send");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setRequestProperty("Authorization", "key=" + apiKey);

				conn.setDoOutput(true);

				String userId = (String) request.getSession().getAttribute("ssUserId");

				// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
				// String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" :
				// \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";

				// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
				String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \" 현관문이 닫혔습니다. \", \"id\" : \""
						+ id + "\" }, \"to\":\"" + deviceToken + "\"}";

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
			} else {
				System.out.println("알림 안보냄");
			}
		}
		// 문닫힘 상태로 데이터베이스 업데이트
		status_service.doorClose(id);

	}

	// 전체알림을 위한 페이지로 이동
	@RequestMapping("/allPush.push.android")
	public String messagePage() {
		return "notice/all_message_page";
	}

	// 창문닫힘
	@RequestMapping("/window_close_message")
	@ResponseBody
	public void windowClose(Model model, HttpServletRequest request, HttpSession session, @RequestParam String id)
			throws Exception {
		System.out.println("PushComment");
		String deviceToken = service.getToken(id);
		if (deviceToken != null) {
			// System.out.println(deviceToken);
			final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + apiKey);

			conn.setDoOutput(true);

			String userId = (String) request.getSession().getAttribute("ssUserId");

			// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
			// String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" :
			// \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";

			// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
			String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \" 창문이 닫혔습니다. \", \"id\" : \"" + id
					+ "\" }, \"to\":\"" + deviceToken + "\"}";

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
		} else {
			System.out.println("알림 안보냄");
		}
		// return "main/android/jsonView";
	}

	// 창문열림
	@RequestMapping("/window_open_message")
	@ResponseBody
	public void windowOpen(Model model, HttpServletRequest request, HttpSession session, @RequestParam String id)
			throws Exception {
		System.out.println("PushComment");
		String deviceToken = service.getToken(id);
		if (deviceToken != null) {
			// System.out.println(deviceToken);
			final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + apiKey);

			conn.setDoOutput(true);

			String userId = (String) request.getSession().getAttribute("ssUserId");

			// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
			// String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" :
			// \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";

			// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
			String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \"창문이 열렸습니다. \", \"id\" : \"" + id
					+ "\" }, \"to\":\"" + deviceToken + "\"}";

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
		} else {
			System.out.println("알림 안보냄");
		}
		// return "main/android/jsonView";
	}

	// 개인 알림
	@RequestMapping("/PushComment")
	@ResponseBody
	public void sendOne(Model model, HttpServletRequest request, HttpSession session, @RequestParam String id)
			throws Exception {
		System.out.println("PushComment");
		String deviceToken = service.getToken(id);
		if (deviceToken != null) {
			// System.out.println(deviceToken);
			final String apiKey = "AAAAzXriQRU:APA91bHGaW_VIqlWYkd7e0znmEQnG9GGBEh-lzHHHzgWiW7ezqLo_ICZKSu2NxRQQRGeo3rt0c1dIvuLFKCXpw4zqJk-fr3Cb0jpCBS22lgH-6bU8x0iSuuAruaBh911h98PG0aCw4TQ";
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + apiKey);

			conn.setDoOutput(true);

			String userId = (String) request.getSession().getAttribute("ssUserId");

			// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
			// String input = "{\"notification\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" :
			// \"여기다 내용 넣기\"}, \"to\":\"/topics/ALL\"}";

			// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
			String input = "{\"notification\" : {\"title\" : \" 알림 \", \"body\" : \" 댓글이 입력되었습니다. \", \"id\" : \"" + id
					+ "\" }, \"to\":\"" + deviceToken + "\"}";

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
		} else {
			System.out.println("알림 안보냄");
		}
		// return "main/android/jsonView";
	}

	// 디바이스 토큰값 입력
	@RequestMapping("/pushMessege")
	public String pushMessege(@RequestParam String token, @RequestParam String id) {
		System.out.println("pushMessege");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("token", token);
		map.put("id", id);
		service.token(map);

		return "main/android/nothing";
	}

	// 전체 푸시 알림
	@RequestMapping("/sendALL")
	public String sendAll(Model model, HttpServletRequest request, HttpSession session, PushVO vo) throws Exception {

		List<TokenVO> list = service.list();
		String title = vo.getTitle();
		String body = vo.getBody();

		for (TokenVO tokenVO : list) {
			String deviceToken = tokenVO.getToken();

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
			String input = "{\"notification\" : {\"title\" : \"" + title + "\", \"body\" : \"" + body
					+ "\", \"id\" : \"\"}, \"to\":\"" + deviceToken + "\"}";

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
		}

		return "redirect:allPush.push.android";
	}

	// 디바이스 토큰 값 삭제
	@RequestMapping("/tokenLogout")
	public String tokenLogout(@RequestParam String id) {
		System.out.println("tokenLogout");
		service.tokenLogout(id);
		return "main/android/nothing";
	}

	// 디바이스 토큰 is_token 끔 // 'N'이 꺼진상태 'Y'가 켜진상태
	@RequestMapping("/tokenSetting")
	public String tokenSetting(@RequestParam String id) {
		System.out.println("tokenSetting");
		service.tokenSetting(id);
		return "main/android/nothing";
	}

	// 전체 알림 온 오프 기능
	@RequestMapping("/tokenSettingAll")
	public String tokenSettingAll(@RequestParam String id) {
		System.out.println("tokenSettingAll");
		service.tokenSettingAll(id);
		return "main/android/nothing";
	}

	// 안드로이드 뎃글 알림기능 온오프 체크
	@RequestMapping("/CheckCommentPushState")
	@ResponseBody
	public String CheckCommentPushState(@RequestParam String id) {
		System.out.println("checkCommentPushState");
		if (service.checkCommentPushState(id).equals("Y")) {
			System.out.println("Y");
			return "{\"result\":\"Y\"}";
		} else {
			System.out.println("N");
			return "{\"result\":\"N\"}";
		}
	}

	// 안드로이드 전체 알림 기능 온오프 체크
	@RequestMapping("/CheckALLPushState")
	@ResponseBody
	public String checkALLPushState(@RequestParam String id) {
		System.out.println("checkALLPushState");
		if (service.checkALLPushState(id).equals("Y")) {
			System.out.println("Y");
			return "{\"result\":\"Y\"}";
		} else {
			System.out.println("N");
			return "{\"result\":\"N\"}";
		}
	}
}
