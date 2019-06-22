package status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.CommonVO;

@Repository
public class StatusDAO {

	@Autowired
	private SqlSession sql;
	
	public void statusBoiler(String status, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("id",id);
		sql.update("status-mapper.statusBoiler", map);
	}
	
	//창문 자동 온오프 기능
	public void statusWindowAuto(String id) {
		sql.update("status-mapper.statusWindowAuto", id);
	}
	
	//창문 자동 온오프 체크
	public String autoWindowSetting(String id) {
		return sql.selectOne("status-mapper.autoWindowSetting", id);
	}
	
	//문열림 데이터베이스 업데이트
	public void doorOpen(String id) {
		sql.update("status-mapper.doorOpen",id);
	}
	
	//문닫힘 데이터베이스 업데이트
	public void doorClose(String id) {
		sql.update("status-mapper.doorClose",id);
	}
	
	//가스 오프
	public void gas_off(String id) {
		sql.update("status-mapper.gas_off", id);
	}
	
	//보일러 온오프
	public void boiler_on_off(String id) {
		sql.update("status-mapper.boiler_on_off", id);
		String now = sql.selectOne("status-mapper.now_boiler", id);
		Boiler_on_off_status_Thread thread = new Boiler_on_off_status_Thread(now);
		thread.start();
	}
	
	//온도 업데이트
	public void updateTem(String id, int temper) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("temper", temper);
		sql.update("status-mapper.updateTem", map);
	}
	
	//창문열기
	public String windowOpen(String id) {
		sql.update("status-mapper.windowOpen",id);
		return null;
	}

	//창문닫기
	public String windowClose(String id) {
		sql.update("status-mapper.windowClose",id);
		return null;
	}
	
	//아두이노 값 읽어오기
	public void getArduinoValue(String id) {
		Snowpiercer snowpiercer = new Snowpiercer(id);
		snowpiercer.start();
	}
	
	// 전등 온오프 기능
	public StatusVO light_on_off(String id) {
		StatusVO vo = sql.selectOne("status-mapper.select", id);
		sql.update("status-mapper.light_on_off", id);
		return vo;
	}

	// status 상태 가져오기
	public StatusVO select(String id) {
		return sql.selectOne("status-mapper.select", id);
	}

	// 주소 받아오기
	public List<AddrVO> getAddrs(String id) {
		return sql.selectList("status-mapper.getAddrs", id);
	}
	
	//이 메소드 쓰지마세요===================
	public AddrVO getAddr(String id) {
		return sql.selectOne("status-mapper.getAddr", id);
	}
	//===============사용 ㄴㄴ

	// 집정보 받아오기
	public GetUserVO start(String addr) {
		return sql.selectOne("status-mapper.info", addr);
	}

	// 아이디를 통해 집상태 받기
	public StatusVO sinfo(String id) {
		return sql.selectOne("status-mapper.sinfo", id);
	};

	// 물 현재 정보 받아오기
	public StatusVO water_info_print(HashMap<String, String> map) {
		sql.update("status-mapper.water_ctrl", map);
		return null;
	}

	// 전등 현재 상태 받아오기
	public StatusVO light_ctrl(HashMap<String, String> map) {
		sql.update("status-mapper.light_ctrl", map);
		return null;
	}

	// 보안설정 on/off 상태 보기
	public StatusVO secure_ctrl(HashMap<String, String> map) {
		sql.update("status-mapper.secure_ctrl", map);
		return null;
	}

	// 미세먼지
	public ArrayList<StatusDTO> dust_info() {
		BufferedReader br = null;
		String result = "";
		String pm10Value = "";
		String pm25Value = "";
		String cityName = "";
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = new JSONObject();
		JSONObject obj = new JSONObject();
		JSONArray listArray = new JSONArray();
		StatusDTO ddto = null;
		ArrayList<StatusDTO> list = new ArrayList<StatusDTO>();

		try {
			String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst"
					+ "?serviceKey=EJmGtsKFK8swoD%2FB3ayPRQswYDu25icg76t6HqcxEF1fX%2B5A4nek3ruj2fJTqImB9Ok6z3TVUcK5AVFVZjdT0Q%3D%3D"
					+ "&numOfRows=6&pageNo=1" + "&sidoName=%EA%B4%91%EC%A3%BC" + "&searchCondition=DAILY" + "&ver=1.3"
					+ "&_returnType=json";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "utf-8"));
			result = "";
			/*
			 * HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			 * urlconnection.setRequestMethod("GET"); br = new BufferedReader(new
			 * InputStreamReader(urlconnection.getInputStream(), "UTF-8")); result = "";
			 */
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			// System.out.println(result);

			jsonObj = (JSONObject) jsonParse.parse(result);
			listArray = (JSONArray) jsonObj.get("list");

			for (int i = 0; i < listArray.size(); i++) {
				obj = (JSONObject) listArray.get(i);
				cityName = (String) obj.get("cityName");
				pm10Value = (String) obj.get("pm10Value");
				pm25Value = (String) obj.get("pm25Value");

				ddto = new StatusDTO(cityName, pm10Value, pm25Value);
				list.add(ddto);
				// System.out.println(list);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return list;
	}

	public void waterVal(String id) {
		HttpURLConnection urlconnection = null;
		
		try {
			URL url = new URL(CommonVO.arduino_ip);
	        urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine()) != null) {
	           result = result + line + "\n";
	        }
	        System.out.println(result);
	        
	        JSONParser jsonParser = new JSONParser();
	        Object obj = jsonParser.parse(result);
	        JSONObject jsonObject = (JSONObject) obj;
	        String light = jsonObject.get("light").toString();
	        String door = jsonObject.get("door").toString();
	        String boiler = jsonObject.get("boiler").toString();
	        updateStatus(light, door, boiler, id);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateStatus(String light, String door, String boiler, String id) throws Exception {
		URL url = new URL(CommonVO.serverIP + "/AA/curStatusUpdate?light="+light+"&door="+door+"&boiler="+boiler+"&id="+id);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	}
	
	public void curStatusUpdate(HashMap<String, String> map) {
		sql.update("status-mapper.curStatusUpdate", map);
	}
}
