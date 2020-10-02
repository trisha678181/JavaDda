package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.NTCommentDao;
import web.dao.face.NoticeDao;
import web.dao.impl.NTCommentDaoImpl;
import web.dao.impl.NoticeDaoImpl;
import web.dto.Notice;
import web.service.face.NoticeService;
import web.util.Paging;

public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDao noticeDao = new NoticeDaoImpl();
	private NTCommentDao ntCommentDao = new NTCommentDaoImpl();

	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 를 파싱한다
        String  param = req.getParameter("curPage");
        int curPage = 0;
        if(param != null && ! "".equals(param)) {
           curPage = Integer.parseInt(param);
        }
        
        //검색어 
        String search = (String)req.getParameter("search");
        
        //Board테이블의 총 게시글 수를 조회한다
        int totalCount = noticeDao.selectCntAll();

        //Paging 객체 생성 - 현재 페이지 ,총 게시글  활용
        Paging paging = new Paging(totalCount, curPage);
        
        //검색어
        paging.setSearch(search);

        //paging 리턴
        return paging;
	}
	
	//예지리스트
	@Override
	public List<Map<String, Object>> getList(Paging paging) {
		return noticeDao.SelectAll(paging);
	} 
	
	//혜연언니 리스트
	@Override
	public List<Notice> getNoticeList(Paging paging) {
		return noticeDao.SelectNoticeAll(paging);
	} 

	@Override
	public Notice getNoticeNo(HttpServletRequest req) {
		//noticeno 를 저장할 객체 생성
		Notice noticeno = new Notice();

		//noticeno  전달파라미터 검증 - null, ""
		String param = req.getParameter("noticeno");

		if(param != null && !"".equals(param)) {

			//noticeno  전달파라미터 추출
			noticeno.setNoticeno (Integer.parseInt(param));

		}

		//결과 객체 반환
		return noticeno;
	}
	
	@Override
	public Notice show(Notice Noticeno) {
		//게시글 조회
	      Notice notice = noticeDao.selectNoticeByNoticeno(Noticeno);
	      if(notice !=null) {
	         noticeDao.updateHit(Noticeno);
	      }
	      return notice;
	}
	
	//***************************혜연**********************************

	@Override
	public void UpdateNoticeNo(HttpServletRequest req) {
		Notice notice = new Notice();
		 
		notice.setNoticeno(Integer.parseInt(req.getParameter("noticeno")));
		notice.setTitle(req.getParameter("title"));
		notice.setNtcontent(req.getParameter("ntcontent"));
		if(notice != null) {
		noticeDao.UpdateNotice(notice);	
		}
	}
	@Override
	public void DeleteNotice(Notice notice) {
		noticeDao.delete(notice);
		
	}

	@Override
	public void write(HttpServletRequest req) {
		Notice notice = new Notice();
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		notice.setTitle( req.getParameter("title") );
		notice.setNtcontent( req.getParameter("ntcontent") );

		//작성자id 처리
		
		req.getSession().setAttribute("ano", 1);
		notice.setAno((int)req.getSession().getAttribute("ano")); 

		if(notice.getTitle()==null || "".equals(notice.getTitle())) {
			notice.setTitle("(제목없음)");
		}

		noticeDao.insertBoard(notice);


	}
	
	@Override
	public int replyCountComment(Notice showNotice) {
		return noticeDao.replyCount(showNotice);
	}

}
