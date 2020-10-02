package web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.DBUtil.JDBCTemplate;
import web.dao.face.PlaceDao;
import web.dto.Business;
import web.dto.Coupon;
import web.dto.HaveCoupon;
import web.dto.Location;
import web.dto.PImg;
import web.dto.Place;
import web.dto.RVImg;
import web.dto.Report;
import web.dto.Review;
import web.util.Paging;

public class PlaceDaoImpl implements PlaceDao {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//**문정****************************************************************************//
	
		@Override
		public List<Map<String, Object>> removePno(int pno) {
			
			conn = JDBCTemplate.getConnection();

			//sql로 8개로 가지고오기
			String sql = "";
			sql += "select * from place p, p_img pi";
			sql += "  where p.pno = pi.pno and p.pno not in(?)";
			sql += "  and pi.pcode=3 and rownum<=8";
			sql += "  order by DBMS_RANDOM.RANDOM()";

			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

			try {
				ps = conn.prepareStatement(sql); //sql수행객체
				ps.setInt(1, pno); //받아온 번호 적용

				rs = ps.executeQuery();

				while(rs.next()) {
					
					Map<String, Object> map = new HashMap<>();
					
					Place place = new Place();
					PImg img = new PImg();

					place.setPno(rs.getInt("pno"));
					place.setLno(rs.getInt("lno"));
					place.setPcode(rs.getInt("pcode"));
					place.setPname(rs.getString("pname"));
					place.setPcontent(rs.getString("pcontent"));
					place.setPphone(rs.getString("pphone"));
					place.setPloc(rs.getString("ploc"));
					place.setPmenu_txt(rs.getString("pmenu_txt"));
					place.setPoperate(rs.getString("poperate"));
					place.setPparking(rs.getString("pparking"));
					place.setPlike(rs.getInt("plike"));
					place.setPpick(rs.getString("ppick"));
					place.setPsubtitle(rs.getString("psubtitle"));
					
					img.setPno(rs.getInt("pno"));
					img.setPino(rs.getInt("pino"));
					img.setPcode(rs.getInt("pcode"));
					img.setOriginName(rs.getString("originName"));
					img.setStoredName(rs.getString("storedName"));
					img.setFilesize(rs.getInt("filesize"));
					
					map.put("place", place);
					map.put("img", img);
					
					list.add(map);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}

			return list; //결과반환
		}
		
		@Override 
		public Place SetPlaceNo(int pno) { 
			
			conn = JDBCTemplate.getConnection();
			
			// sql문으로 SELECT문
			String sql = "";
			sql += "select * from place";
			sql += " where pno = ?";
			
			// Place place = new Place()
			Place place = null;
			
			try {
				ps = conn.prepareStatement(sql); //sql수행객체
				ps.setInt(1, pno); //받아온 번호 적용
				
				rs = ps.executeQuery();

				while(rs.next()) {
					place = new Place(); //결과값
					
					place.setPno(rs.getInt("pno"));
					place.setLno(rs.getInt("lno"));
					place.setPcode(rs.getInt("pcode"));
					place.setPname(rs.getString("pname"));
					place.setPcontent(rs.getString("pcontent"));
					place.setPphone(rs.getString("pphone"));
					place.setPloc(rs.getString("ploc"));
					place.setPmenu_txt(rs.getString("pmenu_txt"));
					place.setPoperate(rs.getString("poperate"));
					place.setPparking(rs.getString("pparking"));
					place.setPlike(rs.getInt("plike"));
					place.setPpick(rs.getString("ppick"));
					place.setPsubtitle(rs.getString("psubtitle"));
					place.setPx(rs.getFloat("px"));
					place.setPy(rs.getFloat("py"));
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			
//			System.out.println(place); //테스트
			return place; //결과반환
		}
		
		
		@Override
		public PImg SetPlaceImg(int pno) {
			conn = JDBCTemplate.getConnection();
			
			// sql문으로 SELECT문
			String sql = "";
			sql += "select * from p_img";
			sql += " where pno = ? and pcode = 2";
			
			// Place place = new Place()
			PImg pimg = null;
			
			try {
				ps = conn.prepareStatement(sql); //sql수행객체
				ps.setInt(1, pno); //받아온 번호 적용
				
				rs = ps.executeQuery();

				while(rs.next()) {
					pimg = new PImg(); //결과값
					
					pimg.setPno(rs.getInt("pno"));
					pimg.setPino(rs.getInt("pino"));
					pimg.setPcode(rs.getInt("pcode"));
					pimg.setOriginName(rs.getString("originName"));
					pimg.setStoredName(rs.getString("storedName"));
					pimg.setFilesize(rs.getInt("filesize"));
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			
//			System.out.println("placeDaoImpl - "+pimg); //테스트
			return pimg; //결과반환
			
		}
		
		
		@Override
		public List<PImg> SelectPimgslideList(int pno) {
			conn = JDBCTemplate.getConnection();
			
			// sql문으로 SELECT문
			String sql = "";
			sql += "select * from p_img";
			sql += " where pno = ? and (pcode = 1 or pcode=3)";
			
			List<PImg> pimglist = new ArrayList<>();
			
			try {
				ps = conn.prepareStatement(sql); //sql수행객체
				ps.setInt(1, pno); //받아온 번호 적용
				
				rs = ps.executeQuery();

				while(rs.next()) {
					PImg p = new PImg(); //결과값
					
					p.setPno(rs.getInt("pno"));
					p.setPino(rs.getInt("pino"));
					p.setPcode(rs.getInt("pcode"));
					p.setOriginName(rs.getString("originName"));
					p.setStoredName(rs.getString("storedName"));
					p.setFilesize(rs.getInt("filesize"));
					
					pimglist.add(p);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
//			System.out.println("placeDaoImpl - "+pimglist); //테스트
			return pimglist; //결과반환
		}
		
		@Override
		public List<Coupon> SetCouponImg(int pno) {
			// sql문으로 SELECT문
					String sql = "";
					sql += "select * from coupon";
					sql += " where pno = ?";
					
					List<Coupon> cimglist = new ArrayList<>();
					
					try {
						ps = conn.prepareStatement(sql); //sql수행객체
						ps.setInt(1, pno); //받아온 번호 적용
						
						rs = ps.executeQuery();

						while(rs.next()) {
							Coupon c = new Coupon(); 
							
							c.setCpno(rs.getInt("cpno"));
							c.setPno(rs.getInt("pno"));
							c.setOriginname(rs.getString("originname"));
							c.setStoredname(rs.getString("storedname"));
							c.setExpdate(rs.getString("expdate"));
							c.setFilesize(rs.getInt("filesize"));
							
							cimglist.add(c);
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						JDBCTemplate.close(rs);
						JDBCTemplate.close(ps);
					}
					
//					System.out.println("placeDaoImpl - "+cimglist); //테스트
					return cimglist; //결과반환
		}
		
		@Override
		public Location selectJoinLoc(int pno) {
			conn = JDBCTemplate.getConnection();
			
			// sql문
			String sql = "";
			sql += "select * from place, location";
			sql += " where place.lno = location.lno" ;
			sql += " AND pno= ?";
			
			Location loc = null;
			
			try {
				ps = conn.prepareStatement(sql); //sql수행객체
				ps.setInt(1, pno); //받아온 번호 적용
				
				rs = ps.executeQuery();

				while(rs.next()) {
					loc = new Location();
					
					loc.setLno(rs.getInt("lno"));
					loc.setName(rs.getString("name"));
					loc.setX(rs.getFloat("x"));
					loc.setY(rs.getFloat("y"));
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			
//			System.out.println(loc); //테스트
			return loc; //결과반환
		}
		
		@Override
	      public List<Map<String, Object>> selectReviewOne(int pno) {
	         //리뷰 가지고오기
	         conn = JDBCTemplate.getConnection();
	         
	         // sql문
	         String sql = "";
	         sql += "select * from review r, rv_img ri";
	         sql += " where r.rno = ri.rno and r.pno = ?";
	         sql += " order by r.rvstar desc, r.rvdate desc"; //별점 높은순, 최신 리뷰 가지고오기
	         
	         List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	         
	         try {
	            
	        	ps = conn.prepareStatement(sql);
	        	
	            ps.setInt(1, pno);
	            rs = ps.executeQuery();
	             
	            while(rs.next()) {
	            	Map<String, Object> map = new HashMap<String, Object>();
	            	
	            	Review rv = new Review();
	            	RVImg ri = new RVImg();
	            	
	            	rv.setRno(rs.getInt("rno"));
	                rv.setPno(rs.getInt("pno"));
	                rv.setUno(rs.getInt("uno"));
	                rv.setRvcontent(rs.getString("rvcontent"));
	                rv.setRvdate(rs.getString("rvdate"));
	                rv.setRvstar(rs.getInt("rvstar"));
	                
	                ri.setRino(rs.getInt("rino"));
					ri.setRno(rs.getInt("rno"));
					ri.setOriginname(rs.getString("originname"));
					ri.setStoredname(rs.getString("storedname"));
	                
					map.put("review", rv);
					map.put("rvimg", ri);
					
	                list.add(map);
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	        	 JDBCTemplate.close(rs);
	             JDBCTemplate.close(ps);
	         }
	         
	         return list; //반환
	      }
		
		//--------------추가 ------------------------
	      @Override
	      public int selectResCoupon(HaveCoupon havecoupon) {
	         
	         conn = JDBCTemplate.getConnection();
	         
	         String sql ="";
	         sql += "SELECT count(*) from have_coupon";
	         sql += " where uno=? and cpno=? ";
	         
	         int res = 0; 
	         
	         try {
	            ps = conn.prepareStatement(sql);
	            ps.setInt(1, havecoupon.getUno());
	            ps.setInt(2, havecoupon.getCpno());
	            
	            rs = ps.executeQuery();
	            
	            while(rs.next()) {
	               res = rs.getInt(1); //첫번째줄 있는지 확인
	            }
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            JDBCTemplate.close(rs);
	            JDBCTemplate.close(ps);
	         }
	         
	         return res;
	      }
	      
	      @Override
	      public void insertHaveCoupon(HaveCoupon havecoupon) {
	         conn = JDBCTemplate.getConnection(); //DB연결
	         
	         String sql="";
	         sql += "insert into have_coupon(hcpno, cpno, uno)";
	         sql += " values (HAVE_COUPON_SEQ.nextval, ?, ?)";

	         try {
	            ps = conn.prepareStatement(sql);
	            
	            ps.setInt(1, havecoupon.getCpno());
	            ps.setInt(2, havecoupon.getUno());
	            
	            //sql 수행
	            ps.executeUpdate();
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            JDBCTemplate.close(rs);
	            JDBCTemplate.close(ps);
	         }
	         
	         System.out.println("PlaceDao - insert성공");
	      }
	      
	      
	      @Override
	      public void UpdatePlaceLike(int Pno) { //-1
	         
	      }
	      
	      @Override
	      public void UpdatePlaceUnLike(int Pno) { //+1
	         
	      }
	      
	      
	      @Override
	      public Report insertReport(int targetpno) { //리뷰인서트
	         conn = JDBCTemplate.getConnection(); //DB연결
	         
	         String sql="";
	         sql += "insert into report_tb(rpno, uno, targetpno, report_content, report_date)";
	         sql += " values (HAVE_COUPON_SEQ.nextval, ?, ?, ?, ?)";

	         try {
	            ps = conn.prepareStatement(sql);
	            
	            
	            
	            //sql 수행
	            ps.executeUpdate();
	            
	         } catch (SQLException e) {
	            e.printStackTrace();
	         } finally {
	            JDBCTemplate.close(rs);
	            JDBCTemplate.close(ps);
	         }
	         
	         System.out.println("PlaceDao - insert성공");
	         
	         return null;
	      }
	      
	      
	
	//**해미****************************************************************************//
	
	@Override
	public List<Map<String, Object>> SelectActTop3() {
		
		int res = 0;

		conn = JDBCTemplate.getConnection();
		
		List<Map<String, Object>> list = new ArrayList<>();

		String sql = "";
		sql += "select * from (select * from place join p_img on place.pno = p_img.pno where place.pcode=4 or place.pcode=5 order by plike desc)"; 
		sql += " where rownum <= 3";

		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<>();
				
				Place place = new Place();
				PImg pimg = new PImg();
				
				//장소명, subtitle, 장소, like, 대표사진
				place.setPname(rs.getString("pname"));
				place.setPsubtitle(rs.getString("psubtitle"));
				place.setPlike(rs.getInt("plike"));
				place.setPloc(rs.getString("ploc"));
				
				pimg.setStoredName(rs.getString("storedname"));
	
				map.put("place", place);
				map.put("pimg", pimg);
				
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		
		System.out.println();

		// 영향받은 행의 수 return
		return list;
		
	}
	
	@Override
	public List<Map<String, Object>> SelectCafeTop3() {

		conn = JDBCTemplate.getConnection();
		
		List<Map<String, Object>> list = new ArrayList<>();

		String sql = "";
		sql += "select * from (select * from place join p_img on place.pno = p_img.pno where place.pcode=1 order by plike desc)"; 
		sql += " where rownum <= 3";

		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<>();
				
				Place place = new Place();
				PImg pimg = new PImg();
				
				//장소명, subtitle, 장소, like, 대표사진
				place.setPno(rs.getInt("pno"));
				place.setPname(rs.getString("pname"));
				place.setPsubtitle(rs.getString("psubtitle"));
				place.setPlike(rs.getInt("plike"));
				place.setPloc(rs.getString("ploc"));
				
				pimg.setStoredName(rs.getString("storedname"));
	
				map.put("place", place);
				map.put("pimg", pimg);
				
				list.add(map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		// 영향받은 행의 수 return
		return list;
	}
	
	public List<Map<String, Object>> SelectPick() {

		conn = JDBCTemplate.getConnection();
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

		String sql = "";
		sql += "select p_img.storedname, place.pno from place join p_img on place.pno = p_img.pno where place.ppick='O'"; 

		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				Place place= new Place();
				PImg pimg = new PImg();
				
				place.setPno(rs.getInt("pno"));
				pimg.setStoredName(rs.getString("storedname"));
				
				map.put("place", place);
				map.put("pimg", pimg);
				
				list.add(map);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		// 영향받은 행의 수 return
		return list;
	}

	//********************************************************************
	
	@Override
	public List<Map<String, Object>> SelectPlaceAll(Paging paging) {
		conn = JDBCTemplate.getConnection();

		String sql="";
		sql += "SELECT * FROM(";
		sql += " SELECT ROWNUM rnum, R.* FROM(";
		sql += " 	SELECT p.pno, p.pcode, p.lno, p.pname, b.name ";
		sql += " 		FROM place p join business b on p.pno = b.pno ";
		sql += "		WHERE pname LIKE '%'||?||'%'"; 
		sql	+= "ORDER BY pno DESC )";
		sql += " 	R)";
		sql += " Place WHERE rnum BETWEEN ? AND ?";

		List<Map<String, Object>> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, paging.getSearch());
			ps.setInt(2, paging.getStartNo()); 
			ps.setInt(3, paging.getEndNo());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회결과 처리
			while (rs.next()) {
				
				System.out.println("들어옴");
				
				Place place = new Place(); //결과값 저장 객체
				Business business = new Business();

				//결과값 한 행 처리
				place.setPno( rs.getInt("pno") );
				place.setPcode( rs.getInt("pcode") );
				place.setLno( rs.getInt("lno") );
				place.setPname( rs.getString("pname") );

				business.setName( rs.getString("name"));

				Map<String, Object> m = new HashMap<>();
				
				m.put("place", place);
				m.put("business", business);
				
				list.add(m);

				//리스트에 결과값 저장
				//	            map.put(business.getBsnum(), place);
				//	            map.put(place.getPno(), business);
				//	            System.out.println("map : " + map);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("list : " + list.size());
		return list;

	}
	
	@Override
	public int selectCntAll() {
		conn = JDBCTemplate.getConnection();
		String sql="";
		sql +="SELECT count(*) FROM place";

		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// SQL 

			//조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public Map<String, Object> selectPlaceByPlaceno(Place placeno) {
		
		conn =JDBCTemplate.getConnection();//DB연결

		//SQL 작성
		String sql = "";
		sql += "SELECT p.*,  b.name ";
		sql += " FROM place p join business b on p.pno = b.pno";
		sql += " where p.pno = ?";

		Map<String, Object> map = new HashMap<String, Object>();

		//결과 저장할 Board객체
		try {
			//SQL 수행 객체
			ps=conn.prepareStatement(sql);
			//조죄할 게스글 번호 적용
			ps.setInt(1, placeno.getPno()); 
			//SQL
			rs= ps.executeQuery();
			Place placeview = new Place();
			Business business = new Business();

			while(rs.next()) {
				placeview.setPno(rs.getInt("pno"));
				placeview.setLno(rs.getInt("lno"));
				placeview.setPname(rs.getString("pname"));
				placeview.setPcode( rs.getInt("pcode"));
				placeview.setPloc(rs.getString("ploc"));
				placeview.setPphone(rs.getString("pphone"));
				placeview.setPoperate(rs.getString("poperate"));
				placeview.setPparking(rs.getString("pparking"));
				placeview.setPmenu_txt(rs.getString("pmenu_txt"));
				placeview.setPcontent(rs.getString("pcontent"));
				placeview.setPpick(rs.getString("ppick"));

				business.setName(rs.getString("name"));

				map.put("place", placeview);
				map.put("business",business);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public void insertPlace(Place place) {
		conn = JDBCTemplate.getConnection(); //DB 연결

		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO place(PNO, LNO, PCODE, PNAME, PCONTENT, PPHONE ,PLOC ,PMENU_TXT, ";
		sql += " POPERATE, PPARKING, PLIKE )";
		sql += "  VALUES (place_seq.nextval, ?, ?, ?, ?,?,?,?,?,?,0)";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, place.getLno());
			ps.setInt(2, place.getPcode());
			ps.setString(3, place.getPname());
			ps.setString(4, place.getPcontent());
			ps.setString(5, place.getPphone());
			ps.setString(6, place.getPloc());
			ps.setString(7, place.getPmenu_txt());
			ps.setString(8, place.getPoperate());
			ps.setString(9, place.getPparking());
		
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void DeletePlace(Place place) {
		conn = JDBCTemplate.getConnection();

		String sql="";
		sql +="DELETE PLACE";
		sql +=" WHERE pno = ?";

		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, place.getPno());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}  


	}


	@Override
	public int selectCntAll(String search) {
		conn = JDBCTemplate.getConnection();
		//수행할 SQL
		String sql = "";
		sql += "SELECT ";
		sql += "	count(*)";
		sql += " FROM place";
		sql += " WHERE pname LIKE '%'||?||'%'";


		int cnt = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// SQL 

			//조회결과 처리
			while (rs.next()) {
				cnt = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {//
			try {
				if(rs != null)	rs.close();
				if(ps != null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}



	@Override
	public int selectPlacePlaceno() {
		//DB연결 객체
	      conn = JDBCTemplate.getConnection();
	      
	    //SQL 작성
	      String sql = "";
	      sql += "SELECT place_seq.nextval FROM dual";
	      
	    //결과 저장할 변수
	      int pno = 0;
	      try {
	          ps = conn.prepareStatement(sql); //SQL수행 객체
	          rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

	          //조회 결과 처리
	          while(rs.next()) {
	        	  pno = rs.getInt(1);
	          }
	      }catch (SQLException e) {
				e.printStackTrace();
			}finally {//
				try {
					if(rs != null)	rs.close();
					if(ps != null)	ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return pno;
	}



	@Override
	public void insertFile(PImg p) {
		 conn = JDBCTemplate.getConnection(); //DB 연결
		  //다음 게시글 번호 조회 쿼리
	      String sql = "";
	      sql += "INSERT INTO P_IMG(pino, pno, originname, storedname, filesize)";
	      sql += " VALUES (P_IMG_SEQ.nextval, ?, ?, ?, ?)";

	      try {
	          //DB작업
	          ps = conn.prepareStatement(sql);
	          
	          ps.setInt(1, p.getPno());
	          ps.setString(2, p.getOriginName());
	          ps.setString(3, p.getStoredName());
	          ps.setInt(4, p.getFilesize());

	          ps.executeUpdate();
	          
	       } catch (SQLException e) {
	          e.printStackTrace();
	       }finally {//
				try {
					if(ps != null)	ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

	}
	
	@Override
	public void PlacePickList(String names) {
	conn = JDBCTemplate.getConnection();
	String sql = "";
	sql += " update place set ppick = 'O' where pno IN("+names+")";

	try {
		ps = conn.prepareStatement(sql);

		ps.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(ps != null)	ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	@Override
	public void UpdatePlace(Place place) {
		conn =JDBCTemplate.getConnection();//DB연결
		//SQL 작성
		String sql = "";
		sql += "UPDATE place SET pcontent=?, pphone=?, ploc=?, pmenu_txt=?, poperate=?, pparking=?";
		sql += " where pno = ?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, place.getPcontent());
			ps.setString(2, place.getPphone());
			ps.setString(3, place.getPloc());
			ps.setString(4, place.getPmenu_txt());
			ps.setString(5, place.getPoperate());
			ps.setString(6, place.getPparking());

			ps.setInt(7, place.getPno());

			ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs !=null) 	rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	//관리자 픽 삭제
	@Override
	public void deletePlacePick(String names) {

		conn = JDBCTemplate.getConnection(); //DB 연결

		String sql = " update place set ppick = '' where pno IN("+names+")";

		try {
			ps = conn.prepareStatement(sql);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
/***********************************************문정 ,지원******************************************/
	 @Override
   public int selectResCoupon(int uno, int cpno) {
      System.out.println("8 dao/uno,cpno >> " + uno+","+cpno);
      conn = JDBCTemplate.getConnection();
      
      String sql ="";
      sql += "SELECT count(*) from have_coupon";
      sql += " where uno=? and cpno=? ";
      
      int res = 0; 
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setInt(1, uno);
         ps.setInt(2, cpno);
         
         rs = ps.executeQuery();
         
         while(rs.next()) {
            res = rs.getInt(1); //첫번째줄 있는지 확인
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
      System.out.println("8 dao/존재 여부 cnt >> "+res);
      return res;
   }

   @Override
   public void insertHaveCoupon(int uno, int cpno) {
      
      System.out.println("12 insertHaveCouponDao uno, cpno >> "+uno+","+cpno);
      conn = JDBCTemplate.getConnection(); //DB연결
      
      String sql="";
      sql += "insert into have_coupon(hcpno, cpno, uno)";
      sql += " values (HAVE_COUPON_SEQ.nextval, ?, ?)";

      try {
         ps = conn.prepareStatement(sql);
         
         ps.setInt(1, cpno);
         ps.setInt(2, uno);
         
         //sql 수행
         ps.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         JDBCTemplate.close(rs);
         JDBCTemplate.close(ps);
      }
      
      System.out.println("13 PlaceDao - insert성공");
   }

@Override
public List<Map<String, Object>> selectPlaceOfLocInfo(int lno) {
	 
    conn = JDBCTemplate.getConnection();

    //sql로 8개로 가지고오기
    String sql = "";

    sql+="SELECT";
	sql+="    P.pno, P.lno, P.pcode p_pcode, P.pname, P.pcontent, P.pphone, P.ploc, P.pmenu_txt";
	sql+="    , P.poperate, P.pparking, P.plike, P.ppick, P.psubtitle, P.px, P.py";
	sql+="    , PI.pino, PI.pno, PI.pcode pi_pcode, PI.originname, PI.storedname, PI.filesize";
	sql+="    , ( ";
	sql+="        SELECT round(NVL(avg(rvstar), 0),2)";
	sql+="        FROM review R";
	sql+="        WHERE P.pno = R.pno";
	sql+="    ) avg_rvstar";
	sql+=" FROM place P, p_img PI";
	sql+=" WHERE P.pno = PI.pno";
	sql+=" AND PI.pcode = 3";
	sql+=" AND p.lno = ?";
	sql+=" ORDER BY DBMS_RANDOM.RANDOM()";
    
//    sql += "select * from place p, p_img pi";
//    sql += "	where p.pno = pi.pno";
//    sql += " 	and lno = 1";
//    sql += "  order by DBMS_RANDOM.RANDOM()";
    

    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//    	System.out.println("Dao lno >> " + lno);
    try {
    	
    	ps = conn.prepareStatement(sql); //sql수행객체
    	ps.setInt(1, lno); //받아온 번호 적용
    	rs = ps.executeQuery();

    	while(rs.next()) {
          
          Map<String, Object> map = new HashMap<>();
          
          Place place = new Place();
          PImg img = new PImg();

          place.setPno(rs.getInt("pno"));//장소 정보
          place.setLno(rs.getInt("lno"));//지역코드 >> 1
          place.setPcode(rs.getInt("p_pcode"));//음식종류
          place.setPname(rs.getString("pname"));//장소이름
          place.setPparking(rs.getString("pparking"));//주차가능여부
          place.setPlike(rs.getInt("plike"));//추천수
          place.setPsubtitle(rs.getString("psubtitle"));//한줄소개
          
          img.setPno(rs.getInt("pno"));
          img.setPino(rs.getInt("pino"));
          img.setPcode(rs.getInt("pi_pcode"));
          img.setOriginName(rs.getString("originName"));
          img.setStoredName(rs.getString("storedName"));
          img.setFilesize(rs.getInt("filesize"));
          
          map.put("place", place);
          map.put("img", img);
          map.put("avg_rvstar", rs.getDouble("avg_rvstar"));
          
          list.add(map);
       }

    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       JDBCTemplate.close(rs);
       JDBCTemplate.close(ps);
    }

    return list; //결과반환
}

@Override
public List<Map<String, Object>> selectPlaceOfLocInfoFilter(int lno, int ppcode, String search) {
	conn = JDBCTemplate.getConnection();
	
	System.out.println("lno : " + lno);
	System.out.println("ppcode : " + ppcode);

    //sql로 8개로 가지고오기
    String sql = "";

    sql+="SELECT";
	sql+="    P.pno, P.lno, P.pcode p_pcode, P.pname, P.pcontent, P.pphone, P.ploc, P.pmenu_txt";
	sql+="    , P.poperate, P.pparking, P.plike, P.ppick, P.psubtitle, P.px, P.py";
	sql+="    , PI.pino, PI.pno, PI.pcode pi_pcode, PI.originname, PI.storedname, PI.filesize";
	sql+="    , ( ";
	sql+="        SELECT round(NVL(avg(rvstar), 0),2)";
	sql+="        FROM review R";
	sql+="        WHERE P.pno = R.pno";
	sql+="    ) avg_rvstar";
	sql+=" FROM place P, p_img PI";
	sql+=" WHERE P.pno = PI.pno";
	sql+=" AND PI.pcode = 3";
	sql+=" AND p.lno = ?";
	if( ppcode != 0 ) {
	sql+=" AND p.pcode = ?";
	}
	if( search != "" || search != null) {
	sql+=" AND P.pname LIKE ?";
	}
	sql+=" ORDER BY DBMS_RANDOM.RANDOM()";
    

    
	int index = 1;
    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//    	System.out.println("Dao lno >> " + lno);
    try {
    	ps = conn.prepareStatement(sql); //sql수행객체
    	
    	ps.setInt(index++, lno); //받아온  lno
    	
    	if( ppcode != 0 ) {
    		ps.setInt(index++, ppcode); //받아온  place 번호
    	}
    	
    	if( search != "" || search != null) {
        	ps.setString(index++, "%"+search+"%"); //받아온  글자열       
        }

    	rs = ps.executeQuery();

        while(rs.next()) {
          
          Map<String, Object> map = new HashMap<>();
          
          Place place = new Place();
          PImg img = new PImg();

          place.setPno(rs.getInt("pno"));//장소 정보
          place.setLno(rs.getInt("lno"));//지역코드 >> 1
          place.setPcode(rs.getInt("p_pcode"));//음식종류
          place.setPname(rs.getString("pname"));//장소이름
          place.setPparking(rs.getString("pparking"));//주차가능여부
          place.setPlike(rs.getInt("plike"));//추천수
          place.setPsubtitle(rs.getString("psubtitle"));//한줄소개
          
          img.setPno(rs.getInt("pno"));
          img.setPino(rs.getInt("pino"));
          img.setPcode(rs.getInt("pi_pcode"));
          img.setOriginName(rs.getString("originName"));
          img.setStoredName(rs.getString("storedName"));
          img.setFilesize(rs.getInt("filesize"));
          
          map.put("place", place);
          map.put("img", img);
          map.put("avg_rvstar", rs.getDouble("avg_rvstar"));
          
          list.add(map);
       }

    } catch (SQLException e) {
       e.printStackTrace();
    } finally {
       JDBCTemplate.close(rs);
       JDBCTemplate.close(ps);
    }

    return list; //결과반환
}


@Override
public Place GetPlaceByName(String pname) {
	conn = JDBCTemplate.getConnection(); //DB 연결

	String sql = "select * from place where pname=?";
	
	Place place = new Place();

	try {
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, pname);

		rs = ps.executeQuery();
		
		while(rs.next()) {
			place.setPno(rs.getInt("pno"));
			place.setLno(rs.getInt("lno"));
			place.setPcode(rs.getInt("pcode"));
			place.setPname(rs.getString("pname"));
			place.setPcontent(rs.getString("pcontent"));
			place.setPphone(rs.getString("pphone"));
			place.setPloc(rs.getString("ploc"));
			place.setPmenu_txt(rs.getString("pmenu_txt"));
			place.setPoperate(rs.getString("poperate"));
			place.setPparking(rs.getString("pparking"));
			place.setPlike(rs.getInt("plike"));
			place.setPpick(rs.getString("ppick"));
			place.setPsubtitle(rs.getString("psubtitle"));
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(ps!=null)	ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return place;
}

@Override
public int likeplacebl(int pno,int uno) {
	conn = JDBCTemplate.getConnection(); //DB 연결

	String sql = "select count(*) from like_place where uno = ? and pno= ?";
	
	int res = 0;

	try {
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, uno);
		ps.setInt(2, pno);

		rs = ps.executeQuery();
		
		while(rs.next()) {
            res = rs.getInt(1); //첫번째줄 있는지 확인
         }

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(ps!=null)	ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return res;
}

	
}
