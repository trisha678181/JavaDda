package web.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import web.service.face.WeatherService;

public class WeatherServiceImpl implements WeatherService {

	public String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	@Override // 날씨 정보 가져오기
	public Map<String, String> GetWeatherInfo() { // ==========================================================================================================

		String address = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		String serviceKey = "LXKaDPtPasP127oeQxRevh4LUn1zpGfRbsY4YuY0OAtuXGtm3EAKIeFZl4u0u6MtZS2MOFRX5Gk2aA9wH%2FfsXA%3D%3D";

		Map<String, String> map = new HashMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("kk");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		int Today2 = Integer.parseInt(sdf2.format(c1.getTime()));
		String strToday2 = "";
		
		if(Today2 >= 2 && Today2 < 5) { // 2시부터 5시
			strToday2 = "0200";
		} else if(Today2 >= 5 && Today2 <8) { // 5시부터 8시
			strToday2 = "0500";
		} else if(Today2 >= 2 && Today2 < 11) { //8시부터 11시
			strToday2 = "0800";
		} else if(Today2 >= 11 && Today2 < 14) { // 11시부터 14시
			strToday2 = "1100";
		} else if(Today2 >= 14 && Today2 < 17) { // 14시부터 17시
			strToday2 = "1400";
		} else if(Today2 >= 17 && Today2 < 20) { // 17시부터 20시
			strToday2 = "1700";
		} else if(Today2 >= 20 && Today2 < 23) { // 2시부터 5시
			strToday2 = "2000";
		} else if(Today2 == 23) {
			strToday2 = "2300";
		} else if(Today2 == 24 || Today2 == 1) {
			strToday2 = "2300";
			c1.add(Calendar.DAY_OF_MONTH, -1);
			strToday = sdf.format(c1.getTime());
		}
		
		String url = address;
		url += "?serviceKey=" + serviceKey;
		url += "&numOfRows=" + "10";
		url += "&pageNo=" + "1";
		url += "&base_date=" + strToday;
		url += "&base_time=" + strToday2;
		url += "&nx=" + "62";
		url += "&ny=" + "126";
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		BufferedReader br =null;
		try {
			
			URL urlstr = new URL(url);
			HttpURLConnection urlconnection = (HttpURLConnection) urlstr.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			
			String result = "";
			String line;
			
			while((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			
			dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					map.put(getTagValue("category", eElement), getTagValue("fcstValue", eElement));
					
				} // for end
			} // if end

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;

	} // ========================================================================================================================================

	@Override // 미세먼지 정보 가져오기
	public int GetAirInfo() { // ==========================================================================================================

		String address = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";
		String serviceKey = "LXKaDPtPasP127oeQxRevh4LUn1zpGfRbsY4YuY0OAtuXGtm3EAKIeFZl4u0u6MtZS2MOFRX5Gk2aA9wH%2FfsXA%3D%3D";

		int air = 0;
		
		String url = address;
		url += "?stationName=종로구";
		url += "&dataTerm=" + "month";
		url += "&pageNo=" + "1";
		url += "&numOfRows=" + "10";
		url += "&ServiceKey=" + serviceKey;
		url += "&ver=" + "1.3";
		
		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		BufferedReader br =null;
		try {
			
			URL urlstr = new URL(url);
			HttpURLConnection urlconnection = (HttpURLConnection) urlstr.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			
			String result = "";
			String line;
			
			while((line = br.readLine()) != null) {
				result = result + line + "\n";
			}
			
			dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			for (int i = 0; i < 1; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					air = Integer.parseInt(getTagValue("pm10Grade1h", eElement));
					
				} else {
					air = 2;// for end 
				}
			} // if end

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return air;

	} // ========================================================================================================================================

	
	@Override
	public int recommend() {

		// 날씨 정보 조회

		// 회원 정보 조회

		return 0;
	}
}
