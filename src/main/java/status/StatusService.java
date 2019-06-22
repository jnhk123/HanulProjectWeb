package status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface StatusService {
	
	//보일러 업데이트
	void statusBoiler(String status, String id);
	
	void statusWindowAuto(String id);
	//창문 자동 온오프 기능
	String autoWindowSetting(String id);
	
	//문열림
	void doorOpen(String id);
	
	//문닫힘
	void doorClose(String id);
	
	//가스 오프
	void gas_off(String id);
	
	//보일러 온오프
	void boiler_on_off(String id);
	
	//온도 업데이트
	void updateTem(String id, int temper);
	
	//전등 온오프
	StatusVO light_on_off(String id);
	
	//status select
	StatusVO select(String id);
	
	//주소 가져오기
	List<AddrVO> getAddrs(String id);
	AddrVO getAddr(String id);
	
	//로그인 정보  받아오기
	public GetUserVO start(String addr);
	
	//아이디를 통해 집상태 받기
	public StatusVO sinfo(String id);
	
	//물 무게 받아서 상태 보여주기
	public StatusVO water_info_print(HashMap<String, String> map);
	
	//전등 상황 받아서 보여주기
	public StatusVO light_ctrl(HashMap<String, String> map);
	
	//보안설정 on/off 보여주기
	public StatusVO secure_ctrl(HashMap<String, String> map);
	
	//타이머 설정 (시간은 1초당 1000)
	public void timer_ctrl(int set_timer);
	
	//기온 정보 출력
	public void temp_info_print(int temp_info);
	
	//미세먼지 넣기
	public ArrayList<StatusDTO> dust_info_put();
	
	//미세먼지/초미세먼지 정보 출력
	public void dust_info_print(int dust_info, int smaller_dust_info);
	
	//날씨 정보 출력
	public void weather_info_print(String weather);

	void waterVal(String id);
	
	void windowOpen(String id);
	
	void windowClose(String id);
	
	//아두이노 값 받아오기
	void getArduinoValue(String id);

	void curStatusUpdate(HashMap<String, String> map);
	
	
}
