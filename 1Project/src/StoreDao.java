import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StoreDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1524:orc";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "jspid";
	String pw = "jsppw";
	Connection conn = null;
	
	StoreDao(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public StoreBean checkUser(String id, String pw) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		StoreBean sb = new StoreBean();
		
		try {
			String sql = "select * from book_user where id = ? and pw = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				sb.setNo(rs.getInt("no"));
				sb.setName(rs.getString("name"));
				sb.setId(rs.getString("id"));
				sb.setPw(rs.getString("pw"));
				sb.setAge(rs.getInt("age"));
				sb.setGender(rs.getString("gender"));
				sb.setAddress(rs.getString("address"));
				sb.setEmail(rs.getString("email"));
				sb.setSign_up(String.valueOf(rs.getDate("sign_up")));
				sb.setRank(rs.getInt("rank"));
				sb.setRental_cnt(rs.getString("rental_cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

	public ArrayList<StoreBean> getAllBookList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		try {
			String sql = "select * from bookstore order by no asc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(rs.getInt("no"));
				sb.setTitle(rs.getString("title"));
				sb.setAuthor(rs.getString("author"));
				sb.setPrice(rs.getInt("price"));
				sb.setPublisher(rs.getString("publisher"));
				sb.setPub_date(String.valueOf(rs.getDate("pub_date")));
				sb.setRental(rs.getInt("rental"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<StoreBean> getAllUserList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		try {
			String sql = "select * from book_user order by no asc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(rs.getInt("no"));
				sb.setName(rs.getString("name"));
				sb.setId(rs.getString("id"));
				sb.setPw(rs.getString("pw"));
				sb.setAge(rs.getInt("age"));
				sb.setGender(rs.getString("gender"));
				sb.setAddress(rs.getString("address"));
				sb.setEmail(rs.getString("email"));
				sb.setSign_up(String.valueOf(rs.getDate("sign_up")));
				sb.setRank(rs.getInt("rank"));
				sb.setRental_cnt(rs.getString("rental_cnt"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int insertBook(StoreBean vo) {
		PreparedStatement ps = null;
		int cnt = 0;
		String pub = "";
		try {
			if(vo.getPub_date().equals("")) {
				pub = "sysdate";
			}else {
				pub = "'"+vo.getPub_date()+"'";
			}
			String sql = "insert into bookstore values(bookseq.nextval,?,?,?,?,"+pub+",0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getPublisher());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt ;
	}

	public int deleteBook(int bookno) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			String sql = "delete from bookstore where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookno);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt ;
	}

	public ArrayList<StoreBean> selectBookUser(String[] sqlStr) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		String sql = "select * from book_user";

		try {
			if(!sqlStr[3].equals("")) {
				sql+=" where "+sqlStr[2]+" like '%"+sqlStr[3]+"%' ";
			}
		}catch(NullPointerException nullE) {}
		try {
			if(!sqlStr[0].equals("")) {
				sql+=" order by "+sqlStr[1]+" "+sqlStr[0];
			}
		}catch(NullPointerException nullE) {
			sql+=" order by no asc";
		}
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(rs.getInt("no"));
				sb.setName(rs.getString("name"));
				sb.setId(rs.getString("id"));
				sb.setPw(rs.getString("pw"));
				sb.setAge(rs.getInt("age"));
				sb.setGender(rs.getString("gender"));
				sb.setAddress(rs.getString("address"));
				sb.setEmail(rs.getString("email"));
				sb.setSign_up(String.valueOf(rs.getDate("sign_up")));
				sb.setRank(rs.getInt("rank"));
				sb.setRental_cnt(rs.getString("rental_cnt"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public HashMap<Integer, String> getRankList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		try {
			String sql = "select * from book_rank order by rankno asc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("rankno"), rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public int deleteUserData(int no) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			String sql = "delete from book_user where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt ;
	}

	public int updateUserData(StoreBean vo) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			String add = "";
			String email = "";
			if(vo.getAddress().equals("")) {
				add = ",address=null";
			}else {
				add = ",address='"+vo.getAddress()+"'";
			}
			
			if(vo.getEmail().equals("")) {
				email = ",email=null";
			}else {
				email = ",email='"+vo.getEmail()+"'";
			}
			String sql = "update book_user "
						+ "set name=?,id=?,pw=?,age=?,gender=?"+add+email+",sign_up=?,rank=? "
						+ "where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getPw());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getGender());
			ps.setString(6, vo.getSign_up());
			ps.setInt(7, vo.getRank());
			ps.setInt(8, vo.getNo());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt ;
	}

	public ArrayList<StoreBean> selectBookData(String[] sqlStr) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		String sql = "select * from bookstore";

		try {
			if(!sqlStr[3].equals("")) {
				sql+=" where "+sqlStr[2]+" like '%"+sqlStr[3]+"%' ";
			}
		}catch(NullPointerException nullE) {}
		try {
			if(!sqlStr[0].equals("")) {
				sql+=" order by "+sqlStr[1]+" "+sqlStr[0];
			}
		}catch(NullPointerException nullE) {
			sql+=" order by no asc";
		}
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(rs.getInt("no"));
				sb.setTitle(rs.getString("title"));
				sb.setAuthor(rs.getString("author"));
				sb.setPrice(rs.getInt("price"));
				sb.setPublisher(rs.getString("publisher"));
				sb.setPub_date(rs.getString("pub_date"));
				sb.setRental(rs.getInt("rental"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String RemoveUserRentalStr(int bookNo,int userNo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select rental_cnt from book_user where no=?";
		String renStr = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				renStr = rs.getString("rental_cnt");
			}
			String[] rentals = renStr.split(",");
			renStr = "";
			for(int i=0;i<rentals.length;i++) {
				if(Integer.parseInt(rentals[i]) != bookNo) {
					if(renStr.equals("")) {
						renStr+=rentals[i];
					}else {
						renStr+=","+rentals[i];
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return renStr;
	}

	public int updateUserRent(String renStr, int userNo) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			//저장 형식 4,1,43
			String sql = "update book_user set rental_cnt =? where no=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, renStr);
			ps.setInt(2, userNo);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int updateRemoveBookRent(int bookNo) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			//저장 형식 4,1,43
			String sql = "update bookstore set rental=0 where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			ps.executeUpdate();
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int getRentUserNo(int bookNo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userNo = 0; 
		
		try {
			String sql = "select rental from bookstore where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				userNo = rs.getInt("rental");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userNo;
	}

	public boolean checkRentalCnt(int no) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] strLsit = null;
		try {
			String sql = "select rental_cnt from book_user where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			String str = "";
			while(rs.next()) {
				str = rs.getString("rental_cnt");
			}
			try {
				strLsit = str.split(",");
			}catch(NullPointerException e) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return strLsit.length<3?true:false;
	}

	public String addUserRentalStr(int bookNo, int userNo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select rental_cnt from book_user where no=?";
		String renStr = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				renStr = rs.getString("rental_cnt");
			}
			if(renStr == null) {
				renStr = bookNo+""; 
			}else {
				renStr +="," +bookNo; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return renStr;
	}

	public int updateAddBookRent(int bookNo,int userNo) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			//저장 형식 4,1,43
			String sql = "update bookstore set rental=? where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, bookNo);
			ps.executeUpdate();
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public boolean getRentalUserNo(int bookNo, int userNo) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select rental from bookstore where no=?";
		int rentalNo = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				rentalNo = rs.getInt("rental");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rentalNo==userNo;
	}

	public int updateInfoData(StoreBean vo) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			//저장 형식 4,1,43
			String sql = "update book_user set pw=?,age=?,gender=?,address=?,email=? where no=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,vo.getPw());
			ps.setInt(2,vo.getAge());
			ps.setString(3,vo.getGender());
			ps.setString(4,vo.getAddress());
			ps.setString(5,vo.getEmail());
			ps.setInt(6,vo.getNo());
			
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public int insertApplayData(StoreBean vo) {
		PreparedStatement ps = null;
	int cnt = 0;
	try {
		String sql = "insert into book_applay values(bookapplayseq.nextval,?,?,?,?,?,sysdate,0,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1,vo.getTitle());
		ps.setString(2,vo.getGenre());
		ps.setString(3,vo.getAuthor());
		ps.setString(4,vo.getPublisher());
		ps.setString(5,vo.getContent());
		ps.setInt(6,vo.getNo());
		
		cnt = ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return cnt;
	}

	public ArrayList<StoreBean> getAllApplayList() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		String sql = "select * from book_applay order by no asc";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(Integer.parseInt(rs.getString("no")));
				sb.setTitle(rs.getString("title"));
				sb.setGenre(rs.getString("genre"));
				sb.setAuthor(rs.getString("author"));
				sb.setPublisher(rs.getString("publisher"));
				sb.setContent(rs.getString("content"));
				sb.setInputdate(String.valueOf(rs.getDate("inputdate")));
				sb.setYn(rs.getInt("yn"));
				sb.setUserno(rs.getInt("userno"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public ArrayList<StoreBean> selectApplayData(String[] sqlStr) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StoreBean> list = new ArrayList<StoreBean>();
		String sql = "select * from book_applay where 1=1 ";
		String ynIn ="";
		String order = "order by ";
		boolean inchk = false;
		for(int i=4;i<7;i++) {
			if(!sqlStr[i].isEmpty()) {
				ynIn +="'"+sqlStr[i]+"',";
				inchk = true;
			}
		}
		if(inchk) {
			ynIn = "and yn in("+ynIn;
			ynIn = ynIn.substring(0,ynIn.length()-1);
			ynIn += ") ";
			sql +=ynIn;
		}
		if(!sqlStr[3].isEmpty()) {
			sql+="and "+sqlStr[2]+" like '%"+sqlStr[3]+"%' ";
		}
		sql+=order+sqlStr[1]+" "+sqlStr[0];
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StoreBean sb = new StoreBean();
				sb.setNo(Integer.parseInt(rs.getString("no")));
				sb.setTitle(rs.getString("title"));
				sb.setGenre(rs.getString("genre"));
				sb.setAuthor(rs.getString("author"));
				sb.setPublisher(rs.getString("publisher"));
				sb.setContent(rs.getString("content"));
				sb.setInputdate(String.valueOf(rs.getDate("inputdate")));
				sb.setYn(rs.getInt("yn"));
				sb.setUserno(rs.getInt("userno"));
				
				list.add(sb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int userApplayChange(int applayNo,int yn) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			//저장 형식 4,1,43
			String sql = "update book_applay set yn=? where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, yn);
			ps.setInt(2, applayNo);
			ps.executeUpdate();
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	public boolean checkId(String newId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "select count(*) cnt from book_user where id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt != 0;
	}

	public int InsertUser(String[] sqlStr) {
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			String sql = "insert into book_user values(bookseq.nextval,?,?,?,?,?,?,?,sysdate,0,null)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, sqlStr[2]);
			ps.setString(2, sqlStr[0]);
			ps.setString(3, sqlStr[1]);
			ps.setInt(4, Integer.parseInt(sqlStr[3]));
			ps.setString(5, sqlStr[4]);
			ps.setString(6, sqlStr[5]);
			ps.setString(7, sqlStr[6]);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
}
