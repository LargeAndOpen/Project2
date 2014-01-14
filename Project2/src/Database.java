import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// 建立資料庫
public class Database {
	
	// 連線
	public Connection con;
	
	// Database資訊
	private String db_driver = "org.postgresql.Driver"; 
	private String db_url = "jdbc:postgresql://210.61.10.89:9999/Team8";
	private String db_user = "Team8";
	private String db_passwd = "LAO2013";

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
	
	// 刪除舊有的Table
	public void delete_tables(String table){
		
		// 嘗試
		try{
			// 敘述子
			Statement st = con.createStatement();
			
			// 美食、景點、住宿
			String sql;
			sql = "DROP TABLE "+table+";";
			st.execute(sql);
			st.close();
			
		// 例外處理
		}catch(Exception ee){
			
			// 提示訊息
			System.out.println("刪除Table錯誤");
			System.out.println(ee.getMessage());
		}
	}

	// 建立新的Table
	public void create_tables(String table, int i){
		
		// 嘗試
		try{
			// 敘述子
			Statement st = con.createStatement();
			String sql;
			
			// 美食、景點
			if(i<2)
			{
				sql = "CREATE TABLE "+table+"(" +
					  "id varchar(50) NOT NULL," +
				      "title varchar(50) NOT NULL," +
					  "catalogs_id varchar(30) NOT NULL," +
					  "address varchar(100) NOT NULL," +
					  "phone varchar(100) NOT NULL," +
					  "business_hrs varchar(120) NOT NULL," +
					  "price varchar(400) NOT NULL," +
					  "Description varchar(1200) NOT NULL," +
					  "route varchar(300) NOT NULL," +
					  "tianmama varchar(10) NOT NULL," +
					  "ImageUrl varchar(150) NOT NULL," +
					  "sc_id varchar(2) NOT NULL," +
					  "post_id varchar(5) NOT NULL," +
					  "link varchar(150) NOT NULL," +
					  "x varchar(60) NOT NULL," +
					  "y varchar(60) NOT NULL," +
					  "create_date varchar(30) NOT NULL," +
					  "modify_date varchar(30) NOT NULL);";
					  st.execute(sql);
			}
			else
			{
				//住宿
				sql = "CREATE TABLE "+table+"(" +
				      "id varchar(50) NOT NULL," +
				      "title varchar(50) NOT NULL," +
					  "catalogs_id varchar(30) NOT NULL," +
					  "address varchar(100) NOT NULL," +
					  "phone varchar(100) NOT NULL," +
					  "business_hrs varchar(120) NOT NULL," +
					  "price varchar(400) NOT NULL," +
					  "Description varchar(1200) NOT NULL," +
					  "route varchar(300) NOT NULL," +
					  "ImageUrl varchar(150) NOT NULL," +
					  "sc_id varchar(2) NOT NULL," +
					  "post_id varchar(5) NOT NULL," +
					  "link varchar(150) NOT NULL," +
					  "x varchar(60) NOT NULL," +
					  "y varchar(60) NOT NULL," +
					  "create_date varchar(30) NOT NULL," +
					  "modify_date varchar(30) NOT NULL);";
					  st.execute(sql);
			}
			st.close();

		// 例外處理
		}catch(Exception ee){
			
			// 提示訊息
			System.out.println("建立Table錯誤");
			System.out.println(ee.getMessage());
		}
	}
	
	// 取得該table的第一筆ID
	public String first(String table){
		
		// 嘗試
		try{
			// 敘述子
			Statement st = con.createStatement();
			
			// 取出第一筆資料
			String sql = "SELECT * FROM "+table+" LIMIT 1;";
			
			// 取得Response
			ResultSet rs = st.executeQuery(sql);
			
			// 回傳第一筆的id
			rs.next();
			return rs.getString("id");
			
		}catch(Exception ee){
			
			// 提示訊息
			System.out.println("取得第一筆id錯誤");
			System.out.println(ee.getMessage());
		}
		
		// Error
		return "QQ";
	}

}
