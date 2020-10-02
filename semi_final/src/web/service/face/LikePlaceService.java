package web.service.face;

public interface LikePlaceService {
	
	/**
	 * Pno 장소가 찜된 상태인지 아닌지 조회
	 * @param Pno
	 * @return 찜되있으면 1, 아니면 0 -> 하트 비우고 채우고는 css에서
	 */
	public int SelectLikePlacePno(int Pno);

}
