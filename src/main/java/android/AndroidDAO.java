package android;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AndroidDAO {

	@Autowired
	private SqlSession sql;

	public void token(HashMap<String, String> map) {
		sql.update("android-mapper.token", map);
	}

	public List<TokenVO> list() {
		return sql.selectList("android-mapper.list");
	}

	public String getToken(String id) {
		System.out.println(id);
		return sql.selectOne("android-mapper.getToken",id);
	}
	
	public void tokenLogout(String id) {
		sql.update("android-mapper.tokenLogout",id);
	}
	
	public void tokenSetting(String id) {
		sql.update("android-mapper.tokenSetting",id);
	}
	
	public void tokenSettingAll(String id) {
		sql.update("android-mapper.tokenSettingAll",id);
	}
	
	public String checkCommentPushState(String id) {
		return sql.selectOne("android-mapper.checkCommentPushState", id);
	}
	
	public String checkALLPushState(String id) {
		return sql.selectOne("android-mapper.checkALLPushState", id);
	}

}
