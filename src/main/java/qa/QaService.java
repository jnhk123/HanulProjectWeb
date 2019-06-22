package qa;

import java.util.List;

public interface QaService {
	List<QaVO> readQa();
	QaPage readQa(QaPage page);
	QaVO detail(int no);
	void insert(QaVO vo);
	void update(QaVO vo);
	void delete(int no);
	void updateFile(QaVO vo);
}