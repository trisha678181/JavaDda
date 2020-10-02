package web.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import web.dao.face.BoardDao;
import web.dao.face.CoupleDao;
import web.dao.face.CouponDao;
import web.dao.face.ReviewDao;
import web.dao.face.UserDao;
import web.dao.impl.BoardDaoImpl;
import web.dao.impl.CoupleDaoImpl;
import web.dao.impl.CouponDaoImpl;
import web.dao.impl.ReviewDaoImpl;
import web.dao.impl.UserDaoImpl;
import web.dto.Board;
import web.dto.Couple;
import web.dto.Coupon;
import web.dto.Place;
import web.dto.Review;
import web.dto.User;
import web.service.face.UserService;
import web.util.MailAuth;


public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	private CouponDao couponDao = new CouponDaoImpl();
	private CoupleDao coupleDao = new CoupleDaoImpl();
	private ReviewDao reviewDao = new ReviewDaoImpl();
	private BoardDao boardDao = new BoardDaoImpl();

	public void SendMail(List<User> list) {
		
		Couple couple = new Couple();

        Properties prop = System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587");
        
        Authenticator auth = new MailAuth();
        
        Session session = Session.getDefaultInstance(prop, auth);
        
        MimeMessage msg = new MimeMessage(session);
        
        UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성
		String u = uuid.toString().split("-")[0];// 8자리 uid
    
        try {
            msg.setSentDate(new Date());
            
            msg.setFrom(new InternetAddress("haemichuu@gmail.com", "내손을 자바"));
            InternetAddress to = new InternetAddress("haemichuu@gmail.com");         
            //InternetAddress to = new InternetAddress(list.get(1).getMail());   
            msg.setRecipient(Message.RecipientType.TO, to);            
            msg.setSubject("제목", "UTF-8");
            msg.setText(list.get(0).getName() + "님이 커플 신청을 하셨습니다." + "\r\n" + 
            "커플을 맺으시려면 아래 인증번호를 복사해주세요~" + "\r\n" + 
            		"인증번호 : " + u + "\r\n" +
            		"URL : " + "http://localhost:8083/couple/valid?couplevalid=" + u, "UTF-8");   
            
            coupleDao.setValidNum(u, list);
            
            Transport.send(msg);
            
            System.out.println("보내따뀨!");
            
        } catch(AddressException ae) {            
            System.out.println("AddressException : " + ae.getMessage());           
        } catch(MessagingException me) {            
            System.out.println("MessagingException : " + me.getMessage());
        } catch(UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException : " + e.getMessage());			
        }
	}

	@Override
	public void UpdateUserInfo(User user) {

		userDao.UpdateUserOne(user); // 영향받은 행의 수 return

	}

	@Override
	public User show(User uno) {
		// 게시글 조회
		User user = userDao.selectUserByUno(uno);
		return user;
	}

	@Override
	public Map<String, Object> coupleshow(User uno) {
		return userDao.selectCoupleByUno(uno);
	}

	@Override
	public User getUserNo(HttpServletRequest req) {

		User user = new User();

		String param = req.getParameter("uno");
		if (param != null && !"".equals(param)) {

			user.setUno(Integer.parseInt(param));
		}
		// 결과 객체 반환
		return user;
	}

	@Override
	public List<Board> SelectBoardUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> SelectCouponUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> SelectReviewUno(int Uno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User GetUserInfo(int uno) {
		return userDao.GetUserInfo(uno);
	}

	@Override
	public boolean checkPW(User user) {
		boolean bl = true;
		if (userDao.checkPW(user) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkOppoID(String ooppoID) {
		if (userDao.checkOppoID(ooppoID) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void SetUserInfo(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		HttpSession session = req.getSession();
		int uno = (int) session.getAttribute("uno");

		User user = userDao.GetUserInfo(uno);

		// 1. 파일 업로드 형태의 테이터가 맞는 지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		// 1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
		if (!isMultipart) {
			System.out.println("[error] enctype이 multipart/form-data가 아닙니다");

			return; // fileupload() 매소드 실행 중지
		}

		// 디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 메모리 처리 사이즈 지정
		factory.setSizeThreshold(1 * 1024 * 1024);

		// 임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp")); // 임시 저장 폴더
		factory.setRepository(repository);

		// 파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 용량 제한
		upload.setFileSizeMax(10 * 1024 * 1024);

		// 전달 데이터 파싱
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		// 요청정보 처리
		while (iter.hasNext()) {
			FileItem item = iter.next();

			// 빈 파일 처리
			if (item.getSize() <= 0)
				continue;

			// 빈 파일이 아닐 경우
			if (item.isFormField()) {
				try {
					if ("nick".equals(item.getFieldName())) {
						user.setNick(item.getString("utf-8"));
					}
					if ("phone".equals(item.getFieldName())) {
						user.setPhone(item.getString());
					}
					if ("mail".equals(item.getFieldName())) {
						user.setMail(item.getString());
					}
					if ("hot".equals(item.getFieldName())) {
						user.setHot(Integer.parseInt(item.getString()));
					}
					if ("cold".equals(item.getFieldName())) {
						user.setCold(Integer.parseInt(item.getString()));
					}
					if ("rain".equals(item.getFieldName())) {
						user.setRain(Integer.parseInt(item.getString()));
					}
					if ("snow".equals(item.getFieldName())) {
						user.setSnow(Integer.parseInt(item.getString()));
					}
					if ("newpw".equals(item.getFieldName())) {
						user.setPw(item.getString());
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			}
			// 3) 파일 처리
			if (!item.isFormField()) {

				// ---- UUID 생성 ------
				UUID uuid = UUID.randomUUID(); // 랜덤 UID 생성

				String u = uuid.toString().split("-")[0];// 8자리 uid
				// -------------------

				// ------ 로컬 저장소의 파일 객체 생성 ----------------
				File up = new File(req.getServletContext().getRealPath("upload") // 업로드될 폴더 경로
						, item.getName() + "_" + u // 원본파일명_uid
				);
				// -----------------------------------------

				if ("profileimgupload".equals(item.getFieldName())) {
					user.setUprofileimg(item.getName() + "_" + u);
				}
				if ("bgimgupload".equals(item.getFieldName())) {
					user.setUbgimg(item.getName() + "_" + u);
				}

				// ---------------------

				// --- 처리 ㅇ완료된 파일 업로드 하기 ------------
				try {
					item.write(up); // 실제 업로드
					item.delete(); // 임시 파일 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
				// -----------------------------

			} // 파일 처리 end
		}

		userDao.setNewInfo(user);

	}

	@Override
	public int Boardcount(User uno) {
		return userDao.Boardcount(uno);
	}

	@Override
	public int Reviewcount(User uno) {
		return userDao.Reviewcount(uno);
	}

	@Override
	public int Like_placecount(User uno) {
		return userDao.Like_placecount(uno);
	}

	@Override
	public int Have_couponcount(User uno) {
		return userDao.Have_couponcount(uno);
	}

	@Override
	public User GetUserById(String userid) {
		return userDao.GetUserById(userid);
	}
	
	@Override
	public List<Map<String, Object>> GetCouponList(User user) {
		return userDao.GetCouponList(user);
	}
	
	@Override
	public List<Map<String, Object>> GetBoardListByUser(User user) {
		return userDao.GetBoardListByUser(user);
	}
	
	@Override
	public List<Map<String, Object>> GetReviewListByUser(User user) {
		return userDao.GetReviewListByUser(user);
	}
	
	@Override
	public User GetOppoInfo(User user) {
		return userDao.GetOppoInfo(user);
	}
	
	@Override
	public boolean MakeCouple(String couplevalid, User user) {
		int res = userDao.MakeCouple(couplevalid, user);
		if(res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void SetLikePlace(User user, Place place) {
		userDao.SetLikePlace(user, place);
	}
	
	@Override
	public int[] PersonalLikePlace(List<Integer> likeplace, List<Integer> top3) {
		
		int[] contain = new int[6];
		
		for(int i = 0; i < 6; i++) {
			if(likeplace.contains(top3.get(i))) {
				contain[i] = 1;
			} else {
				contain[i] = 0;
			}
		}
		
		return contain;
	}

}