package community;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class CommunityPage extends PageVO {
	
	
	private String id;
	private List<CommunityVO> list;

	public List<CommunityVO> getList() {
		return list;
	}

	public void setList(List<CommunityVO> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
}
