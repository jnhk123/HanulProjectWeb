package com.hanul.AA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import common.CommonVO;
import getweather.DustInfoVO;
import getweather.GetDust;
import getweather.GetWeather;
import getweather.TranslateXY;
import getweather.TranslatexyVO;
import getweather.WeatherInfoVO;
import getweather.WeatherVO;
import status.AddrVO;
import status.ArduinoGetValue_Thread;
import status.GetUserVO;
import status.Light_on_off_thread;
import status.Snowpiercer;
import status.StatusService;
import status.StatusVO;
import status.WindowCloseThread;
import status.WindowOpenThread;

@Controller
@SessionAttributes({ "category" })
public class StatusController {

	@Autowired
	private StatusService service;
	
	//어플에서 select시 문상태 업데이트
	@RequestMapping("/curStatusUpdate") @ResponseBody
	public String curStatusUpdate(@RequestParam String id, @RequestParam String door) {
		System.out.println("curStatusUpdate");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("door", door);
		service.curStatusUpdate(map);
		return null;
	}
	
	//보일러 상태 업데이트
	@RequestMapping("/boilerUpdate") @ResponseBody
	public String statusBoiler(@RequestParam String status, @RequestParam String id) {
		System.out.println("boilerUpdate");
		service.statusBoiler(status, id);
		return null;
	}
	
	//창문 자동 온오프 기능
	@RequestMapping("/statusWindowAuto") @ResponseBody
	public String statusWindowAuto(@RequestParam String id) {
		System.out.println("statusWindowAuto");
		if(Snowpiercer.autoWindow) {
			Snowpiercer.autoWindow = false;
		}else {
			Snowpiercer.autoWindow = true;
		}
		service.statusWindowAuto(id);
		return null;
	}
	
	
	//창문 자동 온오프 기능 체크
	@RequestMapping("/autoWindowSetting") @ResponseBody
	public String autoWindowSetting(@RequestParam String id) {
		System.out.println("autoWindowSetting");
		if(service.autoWindowSetting(id).equals("Y")) {
			System.out.println("Y");
			return "{\"result\":\"Y\"}";
		}else {
			System.out.println("N");
			return "{\"result\":\"N\"}";
		}
	}

	//가스 오프 ============================안씀
	@RequestMapping("/Gas_off.status") @ResponseBody
	public String gas_off(@RequestParam String id) {
		System.out.println("gas_off");
		service.gas_off(id);
		return "gas_off";
	}
	
	
	//보일러 온오프
	@RequestMapping("/boiler_on_off.status") @ResponseBody
	public String boiler_on_off(@RequestParam String id) {
		System.out.println("boiler_on_off");
		service.boiler_on_off(id);
		return "boiler_on_off";
	}
	
	//온도 업데이트
	@RequestMapping("/updateTemperature") @ResponseBody
	public String updateTem(@RequestParam String id, @RequestParam int temper) {
		service.updateTem(id, temper);
		return "updateTem";
	}
	
	// 창문열기
	@RequestMapping("/controll_windowOpen") @ResponseBody
	public String windowOpen(@RequestParam String id) {
		System.out.println("controll_windowOpen");
		service.windowOpen(id);
		WindowOpenThread windowOpenThread = new WindowOpenThread();
		windowOpenThread.start();
		//return "redirect:window_open_message?id=" + id;
		return null;
	}

	// 창문닫기
	@RequestMapping("/controll_windowClose") @ResponseBody
	public String windowClose(@RequestParam String id) {
		
		System.out.println("controll_windowClose");
		service.windowClose(id);
		WindowCloseThread windowCloseThread = new WindowCloseThread();
		windowCloseThread.start();
		//return "redirect:window_close_message?id=" + id;
		return null;
	}

	//안드로이드 값 받아오기 끄기와 다시 실행
	@RequestMapping("/controll_train")
	@ResponseBody
	public String gogo(@RequestParam String id) {
		if (Snowpiercer.snowpiercerStop == 1) {
			Snowpiercer.snowpiercerStop = 2;
			System.out.println("스위치 꺼짐");
		} else if(Snowpiercer.snowpiercerStop == 2) {
			Snowpiercer.snowpiercerStop = 1;
			System.out.println("스위치 켜짐");
			service.getArduinoValue(id);
		} else if(Snowpiercer.snowpiercerStop == 3) {
			service.getArduinoValue(id);
			Snowpiercer.snowpiercerStop = 1;
		}
		service.statusWindowAuto(id);
		return "CLEAR";
	}

	// 아두이노와 연결해서 값 받아오기
	@RequestMapping("/startServer")
	@ResponseBody
	public void water(@RequestParam String id) {
		service.getArduinoValue(id);
		if(service.autoWindowSetting(id).equals("Y")) {
			Snowpiercer.autoWindow = true;
		}else {
			Snowpiercer.autoWindow = false;
		}
	}

	// 일회용 아두이노 연결
	@RequestMapping("/readerCurValue")
	@ResponseBody
	public void waterVal(@RequestParam String id) {
		while(Snowpiercer.stay) {
			
		}
		Snowpiercer.stay = true;
		service.waterVal(id);
		Snowpiercer.stay = false;
	}

	//물 상태 측정
	@RequestMapping("/warter.status") @ResponseBody
	public void warter_status(@RequestParam int num) {
		System.out.println("warter.status");
		
	}
	
	// 전등 온오프 기능
	@RequestMapping("/light_on_off.status")
	@ResponseBody
	public String light_on_off(@RequestParam String id, Model model) {
		System.out.println("light_on_off");
		StatusVO vo = service.light_on_off(id);
		Light_on_off_thread thread = new Light_on_off_thread(vo);
		thread.start();
		return null;
	}

	// 안드로이드 status select 구문
	@RequestMapping("/select.status")
	public String select(@RequestParam String id, Model model) {
		System.out.println("select.status");
//		ArduinoGetValue_Thread thread = new ArduinoGetValue_Thread(id);
//		thread.setPriority(Thread.MAX_PRIORITY);
//		thread.start();
		StatusVO vo = service.select(id);
		model.addAttribute("vo", vo);
		return "main/android/status_select";
	}

	@ResponseBody
	@RequestMapping("/getAddr")
	public List<AddrVO> getAddr(@RequestParam String id, Model model) {
		List<AddrVO> list = service.getAddrs(id);
		model.addAttribute("AddrList",list);
		return list;
	}

	@RequestMapping("/statusList")
	public String StatusList() {
		return "weather.st, dust";
	}

	@ResponseBody @RequestMapping("/get_items")
	public String get_info(@RequestParam String id, HttpSession session) {
		//System.out.println(id);

		StatusVO svo = service.sinfo(id);
		AddrVO vo = service.getAddr(id);
		//GetDust gd = new GetDust(Double.parseDouble(vo.getNy()), Double.parseDouble(vo.getNx()));
		try {
			WeatherInfoVO wvo = new WeatherInfoVO();
			TranslateXY txy = new TranslateXY();
			TranslatexyVO tvo = txy.getTransXY(Double.parseDouble(vo.getNy()), Double.parseDouble(vo.getNx()));
			GetWeather gw = new GetWeather(tvo.getX(), tvo.getY());
			//ArrayList<DustInfoVO> dustlist = gd.SearchDustInfo();
			String weather_result = gw.start();
			System.out.println(weather_result);
			//System.out.println(dustlist.get(2).getPm10Grade());
//			GetUserVO vo = service.start(addr);
//			session.setAttribute("my_info", vo);

			int fcstValue = 0;
	        JSONArray jsonArray = new JSONArray(weather_result);
	        for (int i = 0; i < jsonArray.length(); i++) {
	            JSONObject row = jsonArray.getJSONObject(i);
	            String category = (String) row.getString("category");

	            if (category.equals("SKY")) {
	                fcstValue = row.getInt("fcstValue");
	                wvo.setSky(String.valueOf(fcstValue));
	            } else if (category.equals("T3H")) {
	                fcstValue = row.getInt("fcstValue");
	                wvo.setT3h(String.valueOf(fcstValue));
	            } else if (category.equals("POP")) {
	                fcstValue = row.getInt("fcstValue");
	                wvo.setPop(String.valueOf(fcstValue));
	            } else if (category.equals("PTY")) {
	                fcstValue = row.getInt("fcstValue");
	                wvo.setPty(String.valueOf(fcstValue));
	            }
	        }
			
			session.setAttribute("status_info", svo);
			session.setAttribute("weather_info", wvo);
			session.setAttribute("myHomeExistense", "Y");
			return "Y";
		} catch (NullPointerException e) {
			System.out.println("null Pointer Exception 집상태확인");
			session.setAttribute("myHomeExistense", "N");
			return "N";
		}
		
	}

	@RequestMapping("/home.st")
	public String home(Model model, HttpSession session) {
		model.addAttribute("category", "st");
		return "status/status";
	}

	/*
	 * @ResponseBody @RequestMapping("/dust") public StatusDTO dust(@RequestParam
	 * String gu ) { ArrayList<StatusDTO> list = service.dust_info_put(); StatusDTO
	 * reDTO = null; for(StatusDTO dto : list) { if(dto.getCityName().equals(gu)) {
	 * reDTO = dto; } } System.out.println(gu); return reDTO; }
	 */

}
