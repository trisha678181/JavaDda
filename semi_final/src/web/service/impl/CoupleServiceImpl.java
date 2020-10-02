package web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import web.dao.face.CoupleDao;
import web.dao.impl.CoupleDaoImpl;
import web.dto.Anniversary;
import web.dto.Place;
import web.dto.User;
import web.service.face.CoupleService;
import web.util.MiniComparator;

public class CoupleServiceImpl implements CoupleService {
	
	private CoupleDao coupleDao = new CoupleDaoImpl();
	
	@Override
	public int SelectCoupleFirstDay(User user) {
		
		String firstday = coupleDao.SelectCoupleFirstDay(user);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		
		Calendar baseCal = new GregorianCalendar(Integer.parseInt(firstday.substring(0, 4)), Integer.parseInt(firstday.substring(5, 7)), Integer.parseInt(firstday.substring(8, 10)));
		Calendar targetCal = new GregorianCalendar(Integer.parseInt(strToday.substring(0, 4)), Integer.parseInt(strToday.substring(4, 6)), Integer.parseInt(strToday.substring(6,8)));
	
		long diffSec = (targetCal.getTimeInMillis() - baseCal.getTimeInMillis()) / 1000;
		int diffDays= (int)(diffSec / (24*60*60)) + 1;
		
		return diffDays;
		
	}
	
	@Override
	public String[] SelectCoupleName(User user) {
		return coupleDao.SelectCoupleName(user);
	}
	
	@Override
	public List<Anniversary> CalcDday(String[] names, String[] births, User user) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime()); 
		
		List<Anniversary> list = new ArrayList<>();
		
		for(int i = 0; i < 2; i++) {
			
			Anniversary anniversary = new Anniversary();
			
			anniversary.setTitle(names[i] + " 생일");
			
			String strBirthday = births[i].substring(5,7) + births[i].substring(8,10);
			
			if(Integer.parseInt(strToday.substring(4,8)) > Integer.parseInt(strBirthday)) {// 생일이 지났으면,
				
				Calendar today = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4)), Integer.parseInt(strToday.substring(4,6)), Integer.parseInt(strToday.substring(6,8)));
				Calendar birth = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4))+1, Integer.parseInt(strBirthday.substring(0, 2)), Integer.parseInt(strBirthday.substring(2,4)));
			
				long diffSec = (birth.getTimeInMillis() - today.getTimeInMillis()) / 1000;
				int diffDays= (int)(diffSec / (24*60*60)) + 2;
				
				anniversary.setAv_date(String.valueOf(Integer.parseInt(strToday.substring(0,4))+1)+births[i].substring(4,10));
				anniversary.setRest_day(Math.abs(diffDays));
				
			} else {				
				Calendar today = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4)), Integer.parseInt(strToday.substring(4,6)), Integer.parseInt(strToday.substring(6,8)));
				Calendar birth = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4)), Integer.parseInt(strBirthday.substring(0, 2)), Integer.parseInt(strBirthday.substring(2,4)));
			
				long diffSec = (today.getTimeInMillis() - birth.getTimeInMillis()) / 1000;
				int diffDays= (int)(diffSec / (24*60*60)) + 2;
				
				anniversary.setAv_date(strToday.substring(0,4)+births[i].substring(4,10));
				anniversary.setRest_day(Math.abs(diffDays));
			}
			
			list.add(anniversary);
		}
		// 생일 추가됨
		
		String firstday = coupleDao.SelectCoupleFirstDay(user); // 2013-12-08
		int joy = SelectCoupleFirstDay(user); // 2383
		
		strToday = sdf.format(Calendar.getInstance().getTime()); 
		
		Calendar today = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4)), Integer.parseInt(strToday.substring(4,6)), Integer.parseInt(strToday.substring(6,8)));
		Calendar today2 = new GregorianCalendar(Integer.parseInt(strToday.substring(0,4))+1, Integer.parseInt(strToday.substring(4,6))-1, Integer.parseInt(strToday.substring(6,8)));
		
		for(int i = 0; i < 4; i++) {
			
			Anniversary anniversary = new Anniversary();
			
			int future = (((int)joy/100)*100); // 2400
			
			future += (100 * (i+1));
			
			Calendar first = new GregorianCalendar(Integer.parseInt(firstday.substring(0,4)), Integer.parseInt(firstday.substring(5, 7)), Integer.parseInt(firstday.substring(8,10)));

			first.add(Calendar.DAY_OF_MONTH, future);

			if(Integer.parseInt(sdf.format(first.getTime())) < Integer.parseInt(sdf.format(today2.getTime()))) {
			
				anniversary.setTitle(future + "일");
				anniversary.setAv_date(sdf2.format(first.getTime()));
				
				long diffSec = (today.getTimeInMillis() - first.getTimeInMillis()) / 1000;
				int diffDays= (int)(diffSec / (24*60*60)) + 1;
				
				anniversary.setRest_day(Math.abs(diffDays));
				
			}
			
			list.add(anniversary);
			
		}
		
		MiniComparator comp = new MiniComparator();
		Collections.sort(list, comp);
		
		return list;
	}
	
	@Override
	public String[] SelectCoupleBirth() {
		return coupleDao.SelectCoupleBirth();
	}
	
	@Override
	public void MakeCouple(User user) {
		coupleDao.MakeCouple(user);
	}
	
	@Override
	public int AreYouCouple(User user) {
		return coupleDao.AreYouCouple(user);
	}
	
	@Override
	public void MakeCoupleFirstday(String firstday, User user) {
		
		coupleDao.MakeCoupleFirstday(firstday, user);
		
	}
	
	@Override
	public int RequiredCouple(int uno) {
		return coupleDao.RequiredCouple(uno);
	}
	
	@Override
	public void DeleteCoupleValid(String couplevalid) {
		coupleDao.DeleteCoupleValid(couplevalid);
		
	}
	
	@Override
	public List<Map<String, Object>> GetLikePlaceList(User user) {
		return coupleDao.GetLikePlaceList(user);
	}
	
	@Override
	public List<Integer> GetLikePlaceListNo(User user) {
		return coupleDao.GetLikePlaceListNo(user);
	}
	@Override
	public List<Integer> GetTop3ListNo() {
		return coupleDao.GetTop3ListNo();
	}

	@Override
	public void SetLikePlaceTogether(User user) {
		
		List<Integer> list = coupleDao.GetLikePlace(user);
		
		List<Integer> newlist = new ArrayList<>();
		
		for(int i = 0; i < list.size()-1; i++) {
			if(list.get(i) == list.get(i+1)) {
				coupleDao.SetLikePlaceTogether(list.get(i));
				coupleDao.DeleteLikePlaceTogether(list.get(i), user);
			}
		}
		
	}
	
	@Override
	public List<Anniversary> GetAnni(User user) {
		return coupleDao.GetAnni(user);
	}
	
	@Override
	public void ExitCouple(User user) {
		coupleDao.ExitCouple(user);
	}
	
	@Override
	public boolean AddAnni(String annititle, String annidate, User user) {
		if(coupleDao.AddAnni(annititle, annidate, user) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean EditAnni(String annititle, String annidate, String old_annititle, User user) {
		if(coupleDao.EditAnni(annititle, annidate, old_annititle, user) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean DeleteAnni(String annititle, String annidate, User user) {
		if(coupleDao.DeleteAnni(annititle, annidate, user) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void DeleteLikePlace(Place place, User user) {
		coupleDao.DeleteLikePlace(place, user);
	}

}
