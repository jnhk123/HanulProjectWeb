package notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {
	@Autowired
	private SqlSession sql;

	public List<NoticeVO> readNotice() {
		return sql.selectList("notice-mapper.list");
	}
	
	public NoticePage readNotice(NoticePage page) {
		page.setTotalList((Integer) sql.selectOne("notice-mapper.total", page));
		List<NoticeVO> list = sql.selectList("notice-mapper.searchList", page);
		page.setList(list);
		return page;
	}

	public void insert(NoticeVO vo) {
		sql.insert("notice-mapper.insert", vo);
		System.out.println("저장완료");
	}
	
	public NoticeVO noticeDetail(int no) {
		return sql.selectOne("notice-mapper.detail", no);
	}

	public void update(NoticeVO vo) {
		sql.update("notice-mapper.update", vo);
	}

	public void count(int no) {
		sql.update("notice-mapper.count", no);
	}

	public void delete(int no) {
		sql.update("notice-mapper.delete", no);
	}
}
