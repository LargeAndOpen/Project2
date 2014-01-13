import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// 建立資料庫
public class Database {
	
	// 連線
	public Connection con;
	
	// Database資訊
	public static String db_driver = "org.postgresql.Driver"; 
	public static String db_url = "jdbc:postgresql://210.61.10.89:9999/Team8";
	public static String db_user = "Team8";
	public static String db_passwd = "LAO2013";
	
	// 建立連線
	public void start_link(){
		
		// 嘗試
		try{
			// 新的instance
			Class.forName(db_driver).newInstance();
			
			// 建立連線
			con = DriverManager.getConnection(db_url,db_user,db_passwd);
		}
		// 例外處理
		catch(Exception ee){
			
			// 提示訊息 
			System.out.println("資料庫建立連線失敗");
			System.out.println(ee.getMessage());
		}
	}
	
	// 結束連線
	public void end_link(){
		
		// 嘗試
		try{
			// 連線結束				
			con.close();
			
		// 例外處理
		}catch(Exception ee){
			
			// 提示訊息 
			System.out.println("資料庫結束失敗");
			System.out.println(ee.getMessage());
		}
	}
	
}
