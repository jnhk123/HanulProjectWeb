package community;

import java.util.List;

public interface CommunityService {
	public List<CommunityVO> readCommunity();
	public void insert(CommunityVO vo);
	public void update(CommunityVO vo);
	public CommunityVO detail(int no);
	public void delete(int no);
	public CommunityPage readCommunity(CommunityPage page);
	void insertData(CommunityVO vo);

	List<Community_commentVO> cmmcSelect(int no);
	void cm_cmt_insert(Community_commentVO vo);
	void cm_cmt_update(Community_commentVO vo);
	void cm_cmt_delete(int no);
	
	CommunityVO communityDetail(int no);
	void count(int no);
	public LikeVO communityLike(LikeVO vo);
	LikeVO communityLikeCancel(LikeVO vo);
	//LikeVO communityLikeCheck(int no);
	public void updateFile(CommunityVO vo);
	LikeVO communityLikeCheck(LikeVO vo);
	
	List<LikeVO> likeList(String id);
	CommunityPage communityLikeListCheck(CommunityPage page, String id);
	
}
