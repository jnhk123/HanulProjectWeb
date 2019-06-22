package community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired private CommunityDAO dao;
	
	@Override
	public void insert(CommunityVO vo) {
		dao.insert(vo);
	}
	
	@Override
	public List<CommunityVO> readCommunity() {
		return dao.readCommunity();
	}
	
	@Override
	public CommunityPage readCommunity(CommunityPage page) {
		return dao.readCommunity(page);
	}

	@Override
	public void update(CommunityVO vo) {
		dao.update(vo);
	}
	
	@Override
	public void updateFile(CommunityVO vo) {
		dao.updateFile(vo);
	}

	@Override
	public CommunityVO detail(int no) {
		return dao.detail(no);
	}

	@Override
	public void delete(int no) {
		dao.delete(no);
	}

	@Override
	public void insertData(CommunityVO vo) {
		dao.insertData(vo);
	}

	@Override
	public void count(int no) {
		dao.count(no);
	}
	
	@Override
	public CommunityVO communityDetail(int no) {
		return dao.communityDetail(no);
	}
	
	@Override
	public List<Community_commentVO> cmmcSelect(int no) {
		return dao.cmmcSelect(no);
	}

	@Override
	public void cm_cmt_insert(Community_commentVO vo) {
		dao.cm_cmt_insert(vo);
	}

	@Override
	public LikeVO communityLike(LikeVO vo) {
		return dao.communityLike(vo);
	}
	
	@Override
	public LikeVO communityLikeCancel(LikeVO vo) {
		return dao.communityLikeCancel(vo);
	}
	
	@Override
	public LikeVO communityLikeCheck(LikeVO vo) {
		return dao.communityLikeCheck(vo);
	}
	
	@Override
	public void cm_cmt_update(Community_commentVO vo) {
		dao.cm_cmt_update(vo);
	}

	@Override
	public void cm_cmt_delete(int no) {
		dao.cm_cmt_delete(no);
	}

	
	//좋아요 리스트에 체크
	@Override
	public List<LikeVO> likeList(String id) {
		return dao.likeList(id);
	}

	//즐겨찾기 리스트 체크
	@Override
	public CommunityPage communityLikeListCheck(CommunityPage page, String id) {
		return dao.likeListCheck(page, id);
	}

	

}
