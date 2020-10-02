package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.QnA;
import web.util.Paging;

public interface QnADao {
   
   /**
    * 페이징 대상 게시글 목록 조회
    * 
    * @param paging - 페이징 정보 객체
    * @return List<QnA> - 조회되 게시글 목록
    */
   public List<Map<String, Object>> SelectQnAAll(Paging paging);
   
   
   
   //***********************************************************
   
   /**
    * id를 이용해 name을 조회한다
    * 
    * @param showQna - 조회할 id를 가진 객체
    * @return String - 사업자 이름
    */
   public String selectNameByBusinessid(QnA showQna);

   /**
    * 게시물 등록
    * 
    * @param QnA - 삽입될 게시글 내용
    */
   public void InsertQnA(QnA Qna);
   
   /**
    * 특정 게시글 조회
    * 
    * @param qnano - 조회할 qnano를 가진 객체
    * @return QnA - 조회된 결과 객체
    */
   public QnA selectQnabyQnano(QnA qnano);
   /**
    * 총 게시글 수 조회
    * @return
    */
   public int selectCntAll();
   
   
   
   /**
    * 게시글 수정
    * 
    * @param qna - 수정할 내용을 담은 객체
    */
   public void update(QnA qna);
   
   /**
    * 게시글 삭제
    * 
    * @param board - 삭제할 게시글번호를 담은 객체
    */
   public void delete(QnA qna);



   public Map<String, Object> selectQnAByQnAno(QnA qnano); 
   
   
   



}