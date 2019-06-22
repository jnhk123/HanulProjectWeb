package qa;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QaDAO {
	@Autowired
	SqlSession sql;

	public List<QaVO> readQa() {
		return sql.selectList("question-mapper.list");
	}

	public QaPage readQa(QaPage page) {
		page.setTotalList((Integer)sql.selectOne("question-mapper.total", page));
		List<QaVO> list = sql.selectList("question-mapper.searchList", page);
		page.setList(list);
		return page;
	}
	
	public QaVO detail(int no) {
		return sql.selectOne("question-mapper.detail", no);
	}

	public void insert(QaVO vo) {
		sql.insert("question-mapper.insert", vo);
	}

	public void update(QaVO vo) {
		sql.update("question-mapper.update", vo);
	}

	public void delete(int no) {
		sql.update("question-mapper.delete", no);
	}

	public void updateFile(QaVO vo) {
		sql.update("question-mapper.updateFile", vo);
	} 

}
