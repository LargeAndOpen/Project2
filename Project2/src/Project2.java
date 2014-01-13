import java.io.BufferedReader;
import java.io.InputStreamReader;


// 主類別
public class Project2 {
	
	// 程式進入點
	public static void main(String[] args)
	{
		// Simple Factory
		SimpleFactory sf = new SimpleFactory();
		
		// 資料庫初始化
		Database db = sf.getDatabase();
		db.start_link();
		
		// 目的地縣市
		String city = "";
		System.out.print("請輸入城市:");
		try{
			// 讀取使用者輸入的縣市
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			city = buf.readLine();
		}catch(Exception ee){
			// 提示訊息
			System.out.println("取得縣市失敗");
			System.out.println(ee.getMessage());
		}
		
		// 美食資訊
		Foodinfo fi = sf.getFoodinfo();
		fi.printResult(city, db);
		
		// 景點資訊
		Siteinfo si = sf.getSiteinfo();
		si.printResult(city, db);
		
		// 住宿資訊
		Liveinfo li = sf.getLiveinfo();
		li.printResult(city, db);
		
		// 資料庫結束
		db.end_link();
	}
}
