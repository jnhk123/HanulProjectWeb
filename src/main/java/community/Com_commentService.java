package community;

import java.util.List;

public interface Com_commentService {
	List<Com_commentVO> list(int comu_no);
	void update(Com_commentVO vo);
	void delete(int no);
	void insert(Com_commentVO vo);
}
