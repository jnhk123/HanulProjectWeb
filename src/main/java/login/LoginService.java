package login;

import java.util.HashMap;

import client.ClientVO;

public interface LoginService {

	ClientVO login(HashMap<String, String> map);
	/*boolean id_check(String id);*/
	ClientVO kakaoLogin(LoginVO vo);
	ClientVO facebook_login(LoginVO vo);
}
