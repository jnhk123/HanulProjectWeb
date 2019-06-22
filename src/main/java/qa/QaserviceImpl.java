package qa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QaserviceImpl implements QaService {

	@Autowired private QaDAO dao;
	
	@Override
	public List<QaVO> readQa() {
		return dao.readQa();
	}
	
	@Override
	public QaPage readQa(QaPage page) {
		return dao.readQa(page);
	}

	@Override
	public QaVO detail(int no) {
		return dao.detail(no);
	}

	@Override
	public void insert(QaVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(QaVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}
	
	@Override
	public void updateFile(QaVO vo) {
		dao.updateFile(vo);
	}


}
