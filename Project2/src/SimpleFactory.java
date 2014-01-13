

// Simple Factory
public class SimpleFactory {

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
