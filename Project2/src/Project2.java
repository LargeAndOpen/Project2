
// 主類別
public class Project2 {
	
	// 程式進入點
	public static void main(String[] args)
	{
		// 資料庫
		Database db = new Database();
		
		// 起始連線
		db.start_link();
		
		// 測試Query
		db.test_query();
		
		// 結束連線
		db.end_link();
	}
}
