package web.service.face;

import java.util.Map;

import org.w3c.dom.Element;

public interface WeatherService {
	
	public String getTagValue(String tag, Element eElement);
	
	/**
	 * 날씨 정보 조회
	 */
	public Map<String, String> GetWeatherInfo();
	
	/**
	 * 미세먼지 정보 조회
	 */
	public int GetAirInfo();
	
	/**
	 * 날씨 정보를 가지고 추천 알고리즘 돌린다.
	 */
	public int recommend();

}
