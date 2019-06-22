package client;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import status.AddrVO;

@Repository
public class ClientDAO {
	@Autowired private SqlSession sql;

	//CRUD
	
	public void ainsert(ClientVO vo) {
		sql.insert("client-mapper.insert", vo);
		
		sql.insert("status-mapper.insert", vo);
	}
	
	public void insert(ClientVO vo) {
		sql.insert("client-mapper.insert", vo);
		
		sql.insert("client-mapper.insert_addr", vo);
		
		sql.insert("status-mapper.insert", vo);
	}
	
	public List<ClientVO> list(){
		return sql.selectList("client-mapper.list");
	}
	
	public ClientVO detail(String id) {
		return sql.selectOne("client-mapper.detail", id);
	}
	
	public void update(ClientVO vo) {
		sql.update("client-mapper.update", vo);
	}
	
	public void delete(String id) {
		sql.delete("client-mapper.delete", id);
	}

	public boolean id_check(String id) {
		return (Integer)sql.selectOne("client-mapper.id_check", id) == 0 ? true : false;
	}
	//로그인 처리
	public ClientVO loginRequest(HashMap<String, String> map) {
		return sql.selectOne("client-mapper.loginRequest", map);
	}
	
	//로그인 처리
	public boolean ID_PW_check(HashMap<String, String> map) {
		ClientVO vo = sql.selectOne("client-mapper.ID_PW_check", map);
		if(vo == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	//아이디 찾기
	public boolean Find_ID(HashMap<String, String> map) {
		ClientVO vo = sql.selectOne("client-mapper.Find_ID", map);
		if(vo == null) {
			return false;
		}else {
			return true;
		}
	}
	public boolean Find_PW(HashMap<String, String> map) {
		ClientVO vo = sql.selectOne("client-mapper.Find_PW", map);
		if(vo == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void androidUpdate(ClientVO vo) {
		sql.update("client-mapper.androidUpdate", vo);
	}
	

	public void insertAddr(AddrVO vo) {
		sql.insert("client-mapper.insert_addr", vo);
		sql.insert("status-mapper.insert", vo);
		//sql.update("client-mapper.update_addr", vo);
	}
	

	public boolean ID_check(HashMap<String, String> map) {
		ClientVO vo=sql.selectOne("client-mapper.ID_check_android", map);
		if(vo == null) {
			return false;
		}else {
			return true;
		}
	}

	public ClientVO snsloginRequest(HashMap<String, String> map) {
		return sql.selectOne("client-mapper.snsloginRequest", map);
	}

	public String findId(ClientVO vo) {
		String tmp = sql.selectOne("client-mapper.findId", vo);
		if(tmp == null) {
			return null;
		}else {
		return sql.selectOne("client-mapper.findId", vo);
		}
	}

	public String findPW(ClientVO vo) {
		String tmp = sql.selectOne("client-mapper.findPw", vo);
		if(tmp == null) {
			return null;
		}else {
		return sql.selectOne("client-mapper.findPw", vo);
		}
	}

}
