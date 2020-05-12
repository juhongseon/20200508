import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

}
