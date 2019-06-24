package client;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import status.AddrVO;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired private ClientDAO dao;
	
	@Override
	public void ainsert(ClientVO vo) {
		dao.ainsert(vo);
	}
	
	@Override
	public void insert(ClientVO vo) {
		dao.insert(vo);
	}
	@Override
	public List<ClientVO> list() {
		return dao.list();
	}
	@Override
	public ClientVO detail(String id) {
		return dao.detail(id);
	}
	@Override
	public void update(ClientVO vo) {
		dao.update(vo);
	}
	@Override
	public void delete(String id) {
		dao.delete(id);
	}
	@Override
	public boolean id_check(String id) {
		return dao.id_check(id);
	}
	@Override
	public ClientVO loginRequest(HashMap<String, String> map) {
		return dao.loginRequest(map);
	}
	@Override
	public boolean ID_PW_check(HashMap<String, String> map) {
		return dao.ID_PW_check(map);
	}
	
	@Override
	public void androidUpdate(ClientVO vo) {
		dao.androidUpdate(vo);
	}

	public void insertAddr(AddrVO vo) {
		dao.insertAddr(vo);
	}

	@Override
	public boolean ID_check(HashMap<String, String> map) {
		return dao.ID_check(map);
	}
	
	@Override
	public boolean Find_ID(HashMap<String, String> map) {
		return dao.Find_ID(map);
	}
	@Override
	public boolean Find_PW(HashMap<String, String> map) {
		return dao.Find_PW(map);
	}
	@Override
	public ClientVO snsloginRequest(HashMap<String, String> map) {
		return dao.snsloginRequest(map);
	}
	
	@Override
	public String findId(ClientVO vo) {
		return dao.findId(vo);
	}
	
	@Override
	public String findPw(ClientVO vo) {
		return dao.findPW(vo);
	}

	@Override
	public void newPw(String id, String pw) {
		dao.newPw(id, pw);
	}
	
}
