package web.service.face;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import web.dto.QnA;
import web.util.Paging;

public interface QnAService {
   
   /**
    * 페이징 객체 생성
    * 
    * 요청정보를 활용하여 curPage를 구하고
    * Board 테이블과 curPage 값을 이용한 Paging 객체를 생성하여 반환한다
    * 
    * @param req - curPage정보를 담고 있는 요청정보 객체
    * @return Paging - 페이징 계산이 완료된 결과 객체
    */
   public Paging getPaging(HttpServletRequest req);
    
   /**
    * 페이징 처리하여 보여질 게시글 목록만 조회하기
    * 
    * @param paging - 페이징 정보 객체
    * @return List<QnA> - 조회된 게시글 목록
    */
   public List<Map<String, Object>> getList(Paging paging);

   /**
    * 게시물번호 파라미터 얻어오기
    * 
    * @param req - 요청정보 객체
    * @return QnA - 전달파라미터 qnano를 포함한 객체
    */
   public QnA getQnANo(HttpServletRequest req);

   
   
   //*************************************************************
   
   /**
    * 주어진 qnano를 이용하여 게시글을 조회한다
    * 
    * @param qnano - qnano를 가지고 있는 객체
    * @return QnA - 조회된 게시글
    */
   public QnA show(QnA qnano);//예지쇼
   
   public Map<String, Object> listshow(QnA qnano);
   /**
    * QnA 객체의 id를 이용한 닉네임 조회
    * 
    * @param showQna - 조회할 게시글 정보
    * @return String - 게시글 작성자의 닉네임
    */
   public String getName(QnA showQna);

   /**
    * 글 작성자인지 판단하기
    * 
    * @param req - 요청 정보 객체
    * @return boolean - true : 로그인한 사람이 글 작성자
    */
   public boolean checkId(HttpServletRequest req);
   
    /** 게시글 작성
    *    입력한 게시글 내용을 DB에 저장
    * 
    * @param req - 요청정보 객체(게시글내용)
    * 
    */
   public void write(HttpServletRequest req);
   /**
    * 게시글 수정
    * 
    * @param req - 요청 정보 객체
    */
   public void UpdateQnA(HttpServletRequest req);

   /**
    * 게시글 삭제
    * 
    * @param qna - 요정정보객체
    */
   public void DeleteQnA(QnA qna);

   
   
   
   
}