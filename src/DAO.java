import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@211.238.142.205:1521:XE";
	
	public DAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("DAO():");
			e.printStackTrace();
		}
	}
	
	public void getConnection(){
		try {
			conn = DriverManager.getConnection(URL, "hr2", "happy");
		} catch (Exception e) {
			System.out.println("DAO:getConnection():");
			e.printStackTrace();
		}
	}
	
	public void disConnection(){
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			System.out.println("DAO:disConnection():");
			e.printStackTrace();
		}
	}
	
	public void ingredientInsert(IngredientVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO ingredients VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getMname());
			ps.setString(3, vo.getImgSrc());
			ps.setString(4, vo.getSeason());
			ps.setString(5, vo.getTemprt());
			ps.setString(6, vo.getCalUnit());
			ps.setString(7, vo.getUnitCal());
			ps.setString(8, vo.getFit());
			ps.setString(9, vo.getEffects());
			ps.setString(10, vo.getGuide());
			ps.setString(11, vo.getTrim());
			ps.setString(12, vo.getCookery());
			ps.setString(13, vo.getStorage());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("DAO:ingredientInsert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	
	public List<String> getRecipeLinkList() {
		List<String> list = new ArrayList<String>();
		
		try {
			getConnection();
			String sql = "SELECT link FROM recipe where rownum < 10";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int cnt = 1;
			while(rs.next()) {
				list.add(rs.getString(1));
				System.out.println("add "+cnt+"'s recipeLink to list");
				cnt++;
			}
			rs.close();
			System.out.println("list up end...");
		} catch (Exception e) {
			System.out.println("DAO:getRecipeLinkList():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	public List<SearchKeywordVO> getKeywordList() {
		List<SearchKeywordVO> list = new ArrayList<SearchKeywordVO>();
		
		try {
			getConnection();
			String sql = "SELECT * FROM search_keyword";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SearchKeywordVO vo = new SearchKeywordVO();
				vo.setCodeNo(rs.getInt(1));
				vo.setKeyword(rs.getString(2));
				list.add(vo);
				System.out.println("add "+vo.getCodeNo()+" "+vo.getKeyword()+" to list");
			}
			rs.close();
			System.out.println("list up end...");
		} catch (Exception e) {
			System.out.println("DAO:getKeywordList():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
		
		return list;
	}
	
	public void lotterMartInsert(LotteOnProductVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO lottemart VALUES(?,?,?,?,?,?,?,?,?,?,0,SYSDATE,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getProductCode());
			ps.setInt(2, vo.getCodeNo());
			ps.setString(3, vo.getName());
			ps.setDouble(4, vo.getPrice());
			ps.setString(5, vo.getUnitPrice());
			ps.setString(6, vo.getDiscountRate());
			ps.setString(7, vo.getBrand());
			ps.setInt(8, vo.getmPurchase());
			ps.setDouble(9, vo.getRate());
			ps.setInt(10, vo.getReviewCount());
			ps.setInt(11, vo.getRank());
			ps.setString(12, vo.getImg());
			ps.setInt(13, vo.getOriginPrice());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("DAO:lotterMartInsert():");
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}

}







































