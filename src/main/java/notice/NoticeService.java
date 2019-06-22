package notice;

import java.util.List;

public interface NoticeService {
	List<NoticeVO> readNotice();
	NoticePage readNotice(NoticePage page);
	NoticeVO noticeDetail(int id);
	void insert(NoticeVO vo);
	void update(NoticeVO vo);
	void count(int no);
	void insertData(NoticeVO vo);
	void delete(int no);
}
