package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.Business;
import web.util.Paging;

public interface BusinessDao {
	
	// 회원리스트 출력
	public List<Map<String, Object>> SelectBusinessAll(Paging paging);
	
	//종 게시글 조회
	public int selectCntAll();
	
	public Map<String, Object> selectBusinessnoByBno(Business businessno);


	/**
	 * 검색어를 이용한 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(String search);


}
