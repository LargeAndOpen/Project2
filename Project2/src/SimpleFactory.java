

// Simple Factory
public class SimpleFactory {
	
	// 取得資料庫的 Instance
	public Database getDatabase()
	{
		Database database = new Database();
		return database;
	}
	
	// 取得檔案管理員的 Instance
	public FileManager getFileManager()
	{
		FileManager fileManager = new FileManager();
		return fileManager;
	}

	// 取得美食的 Instance
	public Foodinfo getFoodinfo()
	{
		Foodinfo foodinfo = new Foodinfo();
		return foodinfo;
	}
	
	// 取得景膽的 Instance
	public Siteinfo getSiteinfo()
	{
		Siteinfo siteinfo = new Siteinfo();
		return siteinfo;
	}
	
	// 取得住宿的 Instance
	public Liveinfo getLiveinfo()
	{
		Liveinfo liveinfo = new Liveinfo();
		return liveinfo;
	}
}
