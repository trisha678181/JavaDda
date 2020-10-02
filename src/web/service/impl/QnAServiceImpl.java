package web.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dao.face.QnADao;
import web.dao.impl.QnADaoImpl;
import web.dto.Board;
import web.dto.QnA;
import web.service.face.QnAService;
import web.util.Paging;

public class QnAServiceImpl implements QnAService {
   
   private QnADao qnaDao = new QnADaoImpl();
   
   @Override
   public Paging getPaging(HttpServletRequest req) {

      //전달파라미터 curPage를 파싱한다
      String param = req.getParameter("curPage");
      int curPage = 0;
      if( param!=null && !"".equals(param) ) {
         curPage = Integer.parseInt(param);
      }

      //검색어 
      String search = (String)req.getParameter("search");
      
      //Board 테이블의 총 게시글 수를 조회한다
      int totalCount = qnaDao.selectCntAll();

      //Paging 객체 생성 - 현재 페이지(curPage), 총 게시글 수(totalCount) 활용
      Paging paging = new Paging(totalCount, curPage);

      //검색어
      paging.setSearch(search);
      
      //Paging 객체 반환
      return paging;
   }
   
   @Override
   public List<Map<String, Object>> getList(Paging paging) {
      return qnaDao.SelectQnAAll(paging);
   }
   
   @Override
   public QnA show(QnA qnano) {
      //게시글 조회
      QnA qna = qnaDao.selectQnabyQnano(qnano);
      
      return qna;
   }
   
   @Override
   public String getName(QnA showQna) {
      return qnaDao.selectNameByBusinessid(showQna);
   }
   
   @Override
   public boolean checkId(HttpServletRequest req) {
      
      //사용자 세션 ID 얻기
      req.getSession().setAttribute("bno", 3);
      int businessId = (int)req.getSession().getAttribute("bno");
      
      //작성자 게시글 번호 얻기
      QnA qna = getQnANo(req);
      
      //게시글 얻어오기
      qna = qnaDao.selectQnabyQnano(qna);
      
      //게시글의 작성자와 사업자아이디 비교
      if( !(businessId == qna.getBno())) {
         return false;
      }
   
      return true;
      
   }
   
   @Override
   public void write(HttpServletRequest req) {
   
      
      QnA qna = new QnA();
      
      qna.setTitle(req.getParameter("title"));
      qna.setQcontent(req.getParameter("qcontent"));
      
      //사업자 id처리
      req.getSession().setAttribute("bno", 1);
      qna.setBno((int) req.getSession().getAttribute("bno"));
      
      if(qna.getTitle() == null || "".equals(qna.getTitle())) {
         qna.setTitle("(제목없음)");
      }
      
      qnaDao.InsertQnA(qna);
   }
   
   @Override
   public void DeleteQnA(QnA qna) {
      qnaDao.delete(qna);
      
   }
   
   @Override
   public QnA getQnANo(HttpServletRequest req) {
      
      //qnano를 저장할 객체 생성
      QnA qnano = new QnA();
      
      //qnano 전달파라미터 검증 - null, ""
      String param = req.getParameter("qnano");
      
      if(param != null && !"".equals(param)) {
         
         //qnano 전달파라미터 추출
         qnano.setQnano(Integer.parseInt(param));
         
      }
      
      //결과 객체 반환
      return qnano;
   }
   
   @Override
   public void UpdateQnA(HttpServletRequest req) {
      QnA qna = new QnA();
      
      qna.setQnano(Integer.parseInt(req.getParameter("qnano")));
      qna.setTitle(req.getParameter("title"));
      qna.setQcontent(req.getParameter("qcontent"));
      
      if(qna != null) {
         qnaDao.update(qna);
      }
      
      
      
   }

   @Override
   public Map<String, Object> listshow(QnA qnano) {
      return qnaDao.selectQnAByQnAno(qnano);
   }
}
