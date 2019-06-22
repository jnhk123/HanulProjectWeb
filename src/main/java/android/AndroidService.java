package android;

import java.util.HashMap;
import java.util.List;

public interface AndroidService  {
	
	void token(HashMap<String, String> map);
	List<TokenVO> list();
	String getToken(String id);
	void tokenLogout(String id);
	void tokenSetting(String id);
	void tokenSettingAll(String id);
	String checkCommentPushState(String id);
	String checkALLPushState(String id);
	
}
