package android;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AndroidServiceImpl implements AndroidService {
	
	@Autowired AndroidDAO dao;

	@Override
	public void token(HashMap<String, String> map) {
		dao.token(map);
	}

	@Override
	public List<TokenVO> list() {
		return dao.list();
	}

	@Override
	public String getToken(String id) {
		System.out.println(id);
		return dao.getToken(id);
	}

	@Override
	public void tokenLogout(String id) {
		dao.tokenLogout(id);
	}

	@Override
	public void tokenSetting(String id) {
		dao.tokenSetting(id);
	}

	@Override
	public String checkCommentPushState(String id) {
		return dao.checkCommentPushState(id);
	}

	@Override
	public String checkALLPushState(String id) {
		return dao.checkALLPushState(id);
	}

	@Override
	public void tokenSettingAll(String id) {
		dao.tokenSettingAll(id);
	}
}
