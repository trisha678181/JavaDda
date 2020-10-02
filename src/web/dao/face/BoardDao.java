package web.dao.face;

import java.util.List;
import java.util.Map;

import web.dto.BRImg;
import web.dto.Board;
import web.util.Paging;

public interface BoardDao {
	
	/**
	 * 총 게시글 수 조회
	 * @return
	 */
	public int selectCntAll();
	
	/**
	 * 페이징 대상 게시글 목록 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Board> - 조회된 게시글 목록
	 */
	public List<Map<String, Object>> selectAll(Paging paging);
	
	/**
	 * 다음 게시글 번호 반환
	 *  게시글 테이블과 첨부파일 테이블에 입력될 게시글번호를
	 * 시퀀스를 통해 추출한다
	 * 
	 * @return - 다음 게시글 번호
	 */
	public int selectBoardbrno();
	
	
	/**
	 * 조회된 게시글의 조회수 증가시키기
	 * 
	 * @param brno - 조호된 게시글 번호를 가진 객체
	 */
	public void updateBRviewcnt(Board brno);
	
	/**
	 * 특정 게시글 조회
	 * 
	 * @param boardno - 조회할 brno를 가진 객체
	 * @return Boarad - 조호된 결과 객체
	 */
	public Board selectBoardByBoardno(Board brno);
	
	/**
	 * id를 이용해 nick을 조회한다
	 * 
	 * @param showBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNickByUserid(Board showBoard);
	/**
	 * 첨부파일 입력
	 * 
	 * @param boardFile - 업로드 된 첨부파일 정보 객체
	 */
	public void insertFile(BRImg brImg);
	
	/**
	 * 게시글 입력
	 * 
	 * @param board - 삽입될 게시글 내용
	 */
	public void InsertBoard(Board board);
	
	/**
	 * 게시글 수정
	 * 
	 * @param board - 수정할 내용을 담은 객체
	 */
	public void update(Board board);
	
	/**
	 * 게시글 첨부된 파일 기록 삭제
	 * 
	 * @param board - 삭제할 게시글 번호를 담은 객체
	 */
	public void deleteFile(Board board);
	
	/**
	 * 게시글 삭제
	 * 
	 * @param board - 삭제할 게시글번호를 담은 객체
	 */
	public void delete(Board board);
	
	
	/**
	 * 특정 게시글 좋아요
	 * @param boardno
	 */
	public void upadateLikeCount(int brno);
	
	/**
	 * 디비에서 좋아요 수 가져오는 함수
	 * @param brno
	 * @return
	 */
	public int selectLike(int brno);

	
	
	/**
	 * 첨부파일 조회
	 * 
	 * @param showBoard - 첨부파일을 조회할 게시글 객체
	 * @return List<BRImg> - 조회된 첨부파일
	 */
	public List<BRImg> selectFile(Board showBoard);

	/**
	 * 선택적으로 첨부파일 삭제
	 * @param next - 삭제될 첨부파일 번호
	 */
	public void deleteitemFile(String next);
	
	/**
	 * id를 이용해 uid을 조회한다
	 * 
	 * @param showBoard - 조회할 id를 가진 객체
	 * @return String - 작성자 닉네임
	 */
	public String selectNameByUserid(Board board);
	
	
}