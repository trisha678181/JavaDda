package web.service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import web.dto.User;
import web.util.Paging;

public interface AdminService {

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
	public List<User> getList(Paging paging);

}
