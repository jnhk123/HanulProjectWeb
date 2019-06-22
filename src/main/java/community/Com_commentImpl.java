package community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Com_commentImpl implements Com_commentService {

	@Autowired Com_commentDAO dao;

	@Override
	public List<Com_commentVO> list(int comu_no) {
		return dao.list(comu_no);
	}

	@Override
	public void update(Com_commentVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public void insert(Com_commentVO vo) {
		dao.insert(vo);
	}
}
