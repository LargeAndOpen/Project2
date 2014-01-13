import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// 住宿資訊
public class Liveinfo {
	
	// 印出前10個地址
	public void printtop10fromCity(String City, Database db)
	{
		try{
			// 取得該資料庫的連線
			Connection con = db.con;
			
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

	// 印出符合條件的搜尋結果
	public void printResult(String City, Database db)
	{
		try{
			// 取得該資料庫的連線
			Connection con = db.con;
			
			// 敘述子
			Statement st = con.createStatement();
			
			// 符合該縣市的項目
			String sql = "SELECT * FROM live WHERE address LIKE '%"+City+"%'";
			
			// 紀錄偏好
			String prefer="";
			
			// Reading buffer
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			
			// 不斷讀入偏好
			System.out.println("住宿搜尋: 請輸入偏好，一行一個，輸入'.'離開");
			while(true)
			{
				// 偏好
				prefer = buf.readLine();
				
				// 停止讀入
				if(prefer.equals(".")) break;
				
				// 增加偏好搜尋
				sql += " and Description LIKE '%"+prefer+"%'";
			}
			sql += ";";
			
			// 取得Response
			ResultSet rs = st.executeQuery(sql);
			
			// 列出所有符合項目(最多前10個)
			int i=0;
			System.out.println("<住宿資訊>");
			while (rs.next()&&i<10) {
				
				// 印出名稱及地址
				System.out.println("("+(i+1)+")--------------");
				System.out.println("名稱: "+rs.getString("title"));
				System.out.println("地址: "+rs.getString("address"));
				
				// 遞增
				i++;
			}
			System.out.println("");
			st.close();
			
		}catch(Exception ee){
			
			// 錯誤訊息
			System.out.println("取得住宿資訊失敗");
			System.out.println(ee.getMessage());
		}
	}
	
}
