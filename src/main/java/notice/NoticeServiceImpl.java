package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired private NoticeDAO dao;
	
	@Override
	public void insert(NoticeVO vo) {
		dao.insert(vo);	
	}
	
	@Override
	public List<NoticeVO> readNotice() {
		return dao.readNotice();
	}
	
	@Override
	public NoticePage readNotice(NoticePage page) {
		return dao.readNotice(page);
	}
	
	@Override
	public NoticeVO noticeDetail(int no) {
		return dao.noticeDetail(no);
	}

	@Override
	public void update(NoticeVO vo) {
		dao.update(vo);
	}

	@Override
	public void count(int no) {
		dao.count(no);
	}

	@Override
	public void insertData(NoticeVO vo) {
		dao.insert(vo);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

}
