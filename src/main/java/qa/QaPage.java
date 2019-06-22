package qa;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class QaPage extends PageVO {
	private List<QaVO> list;
	
	public List<QaVO> getList(){
		return list;
	}
	
	public void setList(List<QaVO> list) {
		this.list = list;
	}
}
