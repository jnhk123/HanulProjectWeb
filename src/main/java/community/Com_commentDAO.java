package community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Com_commentDAO {

	@Autowired SqlSession sql;
	
	public List<Com_commentVO> list(int comu_no) {
		return sql.selectList("com_comment-mapper.list", comu_no);
	}
	
	public void update(Com_commentVO vo) {
		sql.update("com_comment-mapper.update",vo);
	}
	
	public void delete(int no) {
		sql.update("com_comment-mapper.delete",no);
	}
	
	public void insert(Com_commentVO vo) {
		sql.insert("com_comment-mapper.insert",vo);
	}
}
