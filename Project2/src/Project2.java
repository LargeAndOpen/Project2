
// 主類別
public class Project2 {
	
	// 程式進入點
	public static void main(String[] args)
	{
		// Simple Factory
		SimpleFactory sf = new SimpleFactory();
		
		// 美食資訊
		Foodinfo fi = sf.getFoodinfo();
		
		// 景點資訊
		Siteinfo si = sf.getSiteinfo();
		
		// 住宿資訊
		Liveinfo li = sf.getLiveinfo();
		
		// 資料庫
		Database db = new Database();
		
		// 起始連線
		db.start_link();
		
		// 結束連線
		db.end_link();
	}
}
