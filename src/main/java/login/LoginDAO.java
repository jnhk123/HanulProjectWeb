package login;

import java.util.HashMap;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import client.ClientVO;

@Repository
public class LoginDAO {
	@Autowired
	private SqlSession sql;
	/*
	 * public boolean id_check(String id) {
	 * 
	 * return (Integer)sql.selectOne( "login-mapper.id_check", id) == 0 ? true :
	 * false;
	 * 
	 * }
	 */
	public ClientVO login(HashMap<String, String> map) {
		// String id = map.get("id");
		// String pw = map.get("pw");
		ClientVO vo = sql.selectOne("login-mapper.login", map);
		return vo;
	}

	public ClientVO kakaoLogin(LoginVO vo) {
		ClientVO tmp = sql.selectOne("login-mapper.kakaoLogin", vo);
		sql.update("login-mapper.profileUpdate", vo);
		if(tmp == null) {
			vo.setPw(getRandomPassword(4));
			sql.insert("login-mapper.kakaoInsert",vo);
//			System.out.println("카카오아이디 입력");
			return sql.selectOne("login-mapper.kakaoLogin", vo);
		}else {
//			System.out.println("이미 있음");
			return tmp;
		}
	}
	
	public ClientVO facebook_login(LoginVO vo) {
		System.out.println(vo.getId());
		System.out.println(vo.getName());
		vo.setEmail(vo.getId());
		ClientVO tmp = sql.selectOne("login-mapper.kakaoLogin",vo);
		if(tmp == null) {
			vo.setPw(getRandomPassword(4));
			sql.insert("login-mapper.facebookInsert",vo);
//			System.out.println("카카오아이디 입력");
			return sql.selectOne("login-mapper.kakaoLogin", vo);
		}else {
//			System.out.println("이미 있음");
			return tmp;
		}
	}	
	
	
	public static String getRandomPassword(int length) {
        String[] passwords={"0","1","2","3","4","5","6","7","8","9", "a", "b", "c", "d", "e", "f", "g" ,"A", "B"};
        StringBuilder builder = new StringBuilder("");

        Random random = new Random();
        for (int i = 0; i<length; i++){
            builder.append(passwords[random.nextInt(passwords.length)]);
        }
        return builder.toString();
    }
}
