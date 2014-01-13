
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
		String city = "高雄";
		
		// 美食資訊
		Foodinfo fi = sf.getFoodinfo();
		fi.printtop10fromCity(city, db);
		
		// 景點資訊
		Siteinfo si = sf.getSiteinfo();
		si.printtop10fromCity(city, db);
		
		// 住宿資訊
		Liveinfo li = sf.getLiveinfo();
		li.printtop10fromCity(city, db);
		
		// 資料庫結束
		db.end_link();
	}
}
