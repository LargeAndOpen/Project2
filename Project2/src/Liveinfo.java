import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Liveinfo {

	// 連線
	private Connection con;
	
	// 印出前10個城市
	public void printtop10fromCity(String City, Database db)
	{
		try{
			// 取得該資料庫的連線
			con = db.con;
			
			// 敘述子
			Statement st = con.createStatement();
			
			// 符合該縣市的項目
			String sql = "SELECT * FROM live WHERE address LIKE '%"+City+"%';";
			
			// 取得Response
			ResultSet rs = st.executeQuery(sql);
			
			// 列出所有符合項目(最多前10個)
			int i=0;
			while (rs.next()&&i<10) {
				
				// 印出
				System.out.println(rs.getString("title"));
				
				// 遞增
				i++;
			}
			st.close();
			
		}catch(Exception ee){
			
			// 提示訊息
			System.out.println("取得前10個地址失敗");
			System.out.println(ee.getMessage());
		}
	}
}
