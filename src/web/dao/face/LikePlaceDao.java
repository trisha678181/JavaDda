package web.dao.face;

public interface LikePlaceDao {
	
	/**
	 * Pno 장소가 찜된 상태인지 아닌지 조회
	 * @param Pno
	 * @return 찜되있으면 1, 아니면 0
	 */
	public int SelectLikePlacePno(int Pno);
	
	/**
	 * Pno 장소를 내 찜으로 추가
	 * @param Pno
	 */
	public void UpdateLikePlacePno(int Pno);
	
	/**
	 * Pno 장소를 내 찜에서 삭제
	 * @param Pno
	 */
	public void DeleteLikePlacePno(int Pno);

}
