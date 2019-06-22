package community;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import notice.NoticeVO;

@Repository
public class CommunityDAO {
	@Autowired SqlSession sql;

	public void cm_cmt_update(Community_commentVO vo) {
		sql.update("community-mapper.cm_cmt_update", vo);
	}

	public void insert(CommunityVO vo) {
		sql.insert("community-mapper.insert", vo);
	}

	public List<CommunityVO> readCommunity() {
		return sql.selectList("community-mapper.list");
	}

	public void update(CommunityVO vo) {
		sql.update("community-mapper.update", vo);
	}
	
	public void updateFile(CommunityVO vo) {
		sql.update("community-mapper.updateFile", vo);
	}

	public CommunityVO detail(int no) {
		return sql.selectOne("community-mapper.detail", no);
	}

	public void delete(int no) {
		sql.update("community-mapper.delete", no);
	}

	public CommunityPage readCommunity(CommunityPage page) {
		page.setTotalList((Integer) sql.selectOne("community-mapper.total", page));
		List<CommunityVO> list = sql.selectList("community-mapper.searchList", page);
		page.setList(list);
		return page;
	}
	
	public CommunityPage likeListCheck(CommunityPage page, String id) {
		page.setTotalList((Integer) sql.selectOne("community-mapper.likeTotal", id));
		page.setId(id);
		List<CommunityVO> list = sql.selectList("community-mapper.likeLists", page);
		page.setList(list);
		return page;
	}

	public void insertData(CommunityVO vo) {
		sql.insert("community-mapper.insert", vo);
		//sql.insert("community-mapper.insertLike", vo);
	}

	public void count(int no) {
		sql.update("community-mapper.count", no);
	}

	public CommunityVO communityDetail(int no) {
		return sql.selectOne("community-mapper.detail", no);
	}

	public List<Community_commentVO> cmmcSelect(int no) {
		return sql.selectList("community-mapper.cmmcSelect", no);
	}

	public void cm_cmt_insert(Community_commentVO vo) {
		sql.insert("community-mapper.cm_cmt_insert", vo);
	}

	public LikeVO communityLike(LikeVO vo) {
		return sql.selectOne("community-mapper.like", vo);
	}

	public LikeVO communityLikeCancel(LikeVO vo) {
		return sql.selectOne("community-mapper.like_cancel", vo);
	}
	
	public LikeVO communityLikeCheck(LikeVO vo) {
		return sql.selectOne("community-mapper.like_check", vo);
	}
	
	public void cm_cmt_delete(int no) {
		sql.update("community-mapper.cm_cmt_delete", no);
	}
	
	public List<LikeVO> likeList(String id) {
		return sql.selectList("community-mapper.likeList",id);
	}

	


}
