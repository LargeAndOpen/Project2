import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// Junit Test
public class Project2Test {

	private SimpleFactory sf;
	private Foodinfo foodinfo;
	private Siteinfo siteinfo;
	private Liveinfo liveinfo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		 sf = new SimpleFactory();
		 foodinfo = sf.getFoodinfo();
		 siteinfo = sf.getSiteinfo();
		 liveinfo = sf.getLiveinfo();
	}
	
	@Test
	public void testGetFirstFoodinfofromCity() {
		
		//  嘗試
		try{
			// 預期的字串
			String expected = "一日均衡手抓飯便當";
			
			// 實際的結果
			String result = foodinfo.getFirstFromCity("台北");
			
			// 比較
			assertEquals(expected, result);
		}
		catch(Exception ee)	{
			
			// 提示訊息
			System.out.println("Junit: 美食錯誤");
			System.out.println(ee.getMessage());
		}
	}

	@Test
	public void testGetFirstSiteinfofromCity() {
		
		//  嘗試
		try{
			// 預期的字串
			String expected = "迪化街";
			
			// 實際的結果
			String result = siteinfo.getFirstFromCity("台北");
			
			// 比較
			assertEquals(expected, result);
		}
		catch(Exception ee)	{
			
			// 提示訊息
			System.out.println("Junit: 景點錯誤");
			System.out.println(ee.getMessage());
		}
	}
	
	@Test
	public void testGetFirstLiveinfofromCity() {
		
		//  嘗試
		try{
			// 預期的字串
			String expected = "陽明山菁山溫泉";
			
			// 實際的結果
			String result = liveinfo.getFirstFromCity("台北");
			
			// 比較
			assertEquals(expected, result);
		}
		catch(Exception ee)	{
			
			// 提示訊息
			System.out.println("Junit: 住宿錯誤");
			System.out.println(ee.getMessage());
		}
	}
	
}
