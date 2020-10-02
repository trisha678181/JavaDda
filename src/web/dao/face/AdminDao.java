package web.dao.face;

import java.util.List;

import web.dto.User;
import web.util.Paging;

public interface AdminDao {
	
	// 회원리스트 출력
	public List<User> SelectUserAll(Paging paging);
	
	//종 게시글 조회
	public int selectCntAll();
	
	/**
	 * 검색어를 이용한 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(String search);

}
