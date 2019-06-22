package status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Service
public class StatusServiceImpl implements StatusService{

	@Autowired private StatusDAO dao;
	
	//창문 자동 온오프 기능
	@Override
	public void statusWindowAuto(String id) {
		dao.statusWindowAuto(id);
	}
		
	//창문 자동 온오프 기능 체크
	@Override
	public String autoWindowSetting(String id) {
		return dao.autoWindowSetting(id);
	}
	
	//집정보 받아오기
	@Override
	public GetUserVO start(String addr) {
		return dao.start(addr);
	}

	//아이디를 통해 집상태 받기
	@Override
	public StatusVO sinfo(String id) {
		return dao.sinfo(id);
	};
	
	//물 현재 정보 받아오기
	@Override
	public StatusVO water_info_print(HashMap<String, String> map) {
		return dao.water_info_print(map);
	}

	//전등 현재 상태 받아오기
	@Override
	public StatusVO light_ctrl(HashMap<String, String> map) {
		return dao.light_ctrl(map);
	}

	//보안설정 on/off 상태 보기
	@Override
	public StatusVO secure_ctrl(HashMap<String, String> map) {
		return dao.secure_ctrl(map);
	}

	//타이머 설정
	@Override
	public void timer_ctrl(int set_timer) {
	}

	//기온 정보 출력
	@Override
	public void temp_info_print(int temp_info) {
	}
	
	//미세먼지 넣기
	@Override
	public ArrayList<StatusDTO> dust_info_put() {
		return dao.dust_info();
	}

	//미세먼지/초미세먼지 정보 받아오기
	@Override
	public void dust_info_print(int dust_info, int smaller_dust_info) {
		
	}

	//날씨 정보 받아오기
	@Override
	public void weather_info_print(String weather) {
	}
	
	//주소 받아오기
	@Override
	public List<AddrVO> getAddrs(String id) {
		return dao.getAddrs(id);
	}

	//이메소드 사용 ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ======
	@Override
	public AddrVO getAddr(String id) {
		// TODO Auto-generated method stub
		return dao.getAddr(id);
	}
	//사용 ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ=========
	
	
	//status select
	@Override
	public StatusVO select(String id) {
		return dao.select(id);
	}

	//전등 온오프
	@Override
	public StatusVO light_on_off(String id) {
		return dao.light_on_off(id);
	}

	@Override
	public void getArduinoValue(String id) {
		dao.getArduinoValue(id);
	}

	@Override
	public void waterVal(String id) {
		// TODO Auto-generated method stub
		dao.waterVal(id);
	}

	@Override
	public void windowOpen(String id) {
		dao.windowOpen(id);
		
	}

	@Override
	public void windowClose(String id) {
		dao.windowClose(id);
		
	}

	@Override
	public void updateTem(String id, int temper) {
		dao.updateTem(id,temper);
	}
	
	//보일러 온 오프
	@Override
	public void boiler_on_off(String id) {
		dao.boiler_on_off(id);
	}

	//가스 끄기
	@Override
	public void gas_off(String id) {
		dao.gas_off(id);
	}

	//문열림 데이터베이스 업데이트
	@Override
	public void doorOpen(String id) {
		dao.doorOpen(id);
		
	}

	//문닫힘 데이터베이스 업데이트
	@Override
	public void doorClose(String id) {
		dao.doorClose(id);
		
	}
	//보일러 업데이트
	@Override
	public void statusBoiler(String status, String id) {
		dao.statusBoiler(status, id);
	}

	@Override
	public void curStatusUpdate(HashMap<String, String> map) {
		dao.curStatusUpdate(map);
	}

	
}
