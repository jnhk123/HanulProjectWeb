package login;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import client.ClientVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired private LoginDAO dao;
	
	@Override
	public ClientVO login(HashMap<String, String> map) {
		return dao.login(map);
	}

	@Override
	public ClientVO kakaoLogin(LoginVO vo) {
		return dao.kakaoLogin(vo);
	}

	@Override
	public ClientVO facebook_login(LoginVO vo) {
		return dao.facebook_login(vo);
	}	
	
/*	@Override
	public boolean id_check(String id) {
		// TODO Auto-generated method stub
		return dao.id_check(id);
	}*/

}
