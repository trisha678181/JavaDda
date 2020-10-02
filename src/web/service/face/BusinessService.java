package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.Business;
import web.util.Paging;

public interface BusinessService {
	
	/**
	 * 페이징 객체 생성
	 * @param req
	 * @return
	 */
	public Paging getPaging(HttpServletRequest req);
	
	
	
	/**
	 * 게시물 목록
	 * @param paging
	 * @return
	 */
	public List<Map<String, Object>> getList(Paging paging);

	//사업자넘버가져오기
	public Business getBusinessNo(HttpServletRequest req);

	//VIEW
	public Map<String, Object> show(Business businessno);

}
