package client;

import java.util.HashMap;
import java.util.List;

import status.AddrVO;

public interface ClientService {
	
	//CRUD: 저장 create, 조회 read, 변경 update, 삭제 delete
		//저장
		void ainsert(ClientVO vo);
		void insert(ClientVO vo);
		//조회
		public List<ClientVO> list();
		
		ClientVO detail(String id);
		//변경
		void update(ClientVO vo);
		//삭제
		void delete(String id);
		//아이디체크
		boolean id_check(String id);
		
		ClientVO loginRequest(HashMap<String, String> map);
		
		boolean ID_PW_check(HashMap<String, String> map);
		//안드로이드 아이디 체크
		boolean ID_check(HashMap<String, String> map);
		
		//안드로이드 아이디 찾기
		boolean Find_ID(HashMap<String, String> map);
		
		boolean Find_PW(HashMap<String, String> map);
		
		void androidUpdate(ClientVO vo);
		
		public void insertAddr(AddrVO vo);
		
		ClientVO snsloginRequest(HashMap<String, String> map);
		
		String findId(ClientVO vo);
		
		String findPw(ClientVO vo);
		
}
