import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONTokener;

// 更新資料庫
public class Update {

	// 資料表欄位
	private static String[] cols = { "id", "title", "catalogs_id", "address", "phone",
			"business_hrs", "price", "Description", "route", "tianmama",
			"ImageUrl", "sc_id", "post_id", "link", "x", "y", "create_date",
			"modify_date" };
	private static String[] cols2 = { "id", "title", "catalogs_id", "address",
			"phone", "business_hrs", "price", "Description", "route",
			"ImageUrl", "sc_id", "post_id", "link", "x", "y", "create_date",
			"modify_date" };

	// JSON來源網址
	private static String[] urls = {
			"http://data.coa.gov.tw:8080/od/data/api/eir01/?$format=json",
			"http://data.coa.gov.tw:8080/od/data/api/eir04/?$format=json",
			"http://data.coa.gov.tw:8080/od/data/api/eir02/?$format=json" };

	// 檔案，資料表名稱
	private static String[] files = { "food", "site", "live" };

	// Parse資料並存進資料庫
	private static void update(Database db) {

		int i = 0;

		// 標記是否要更新
		boolean NeedtoUpdate = false;

		// 設定 smtp server
		Jmail sm = new Jmail("smtp.gmail.com");
		sm.props.put("mail.smtp.starttls.enable", "true");

		// 寄信者的信箱帳密
		sm.setNamePass(" largeteam8@gmail.com", "largeeight");

		// 主旨(可中文)
		sm.setSubject("LAO report");

		// 寄信人
		sm.setFrom("largeteam8@gmail.com");

		// 收件人
		sm.setTo("kevinx6000@gmail.com");
		String content = new String();

		// 嘗試
		try {

			// 美食、景點、住宿
			for (int seq = 0; seq < 3; seq++) {
				
				// 將資料寫入txt檔
				System.out.print("取得" + files[seq] + "資料中...");
				getFilefromUrl(urls[seq], files[seq] + ".txt");
				System.out.println("完成");

				// 讀檔
				File f = new File(files[seq] + ".txt");
				FileInputStream file = new FileInputStream(f);
				InputStreamReader input = new InputStreamReader(file, "UTF-8");
				f.delete();

				// SQL
				String sql = "", tmp;
				Statement st = db.con.createStatement();

				// JSON Parser
				JSONTokener jt = new JSONTokener(input);
				JSONArray item = new JSONArray(jt);

				// 取得第一筆Parse的ID、第一筆資料庫內的ID
				String id_net = item.getJSONObject(0).getString("id");
				String id_dat = db.first(files[seq]);

				// 資料不變
				if (id_net.equals(id_dat)) {
					System.out.println("資料未變動，不更新");
					continue;
				}
				// 需要更新
				NeedtoUpdate = true;
				content += files[i] + "資料已更新<br>";

				// 刪除、建立資料表
				System.out.println("資料需更新!");
				db.delete_tables(files[seq]);
				db.create_tables(files[seq], seq);

				// 每筆資料
				System.out.print("插入" + files[seq] + "至資料庫...");
				for (i = 0; i < item.length(); i++) {
					
					// 所有欄位
					sql = "INSERT INTO " + files[seq] + " VALUES(";

					// 美食、景點
					if (seq < 2)
					{
						for (int j = 0; j < cols.length; j++)
						{
							// 取得該欄位的資料
							tmp = item.getJSONObject(i).getString(cols[j]);
							tmp = tmp.replaceAll("\'", "\"");
							sql += "\'" + tmp + "\'";
							if (j != cols.length - 1)
								sql += ",";
						}
					}
					// 住宿
					else
					{
						for (int j = 0; j < cols2.length; j++)
						{
							// 取得該欄位的資料
							tmp = item.getJSONObject(i).getString(cols2[j]);
							tmp = tmp.replaceAll("\'", "\"");
							sql += "\'" + tmp + "\'";
							if (j != cols2.length - 1)
								sql += ",";
						}
					}
					sql += ");";
					st.execute(sql);
				}
				st.close();

				// 提示完成訊息
				System.out.println("完成");
			}
			
			// 提示完成訊息
			System.out.println("所有資料更新完畢");
			if (!NeedtoUpdate) content += "無資料更新<br>";

			// 設定信件內容
			sm.setBody(content);
			sm.setNeedAuth(true);

			// 寄信
			System.out.print("正在嘗試發送Email...");
			boolean b = sm.setOut();
			if (b)
				System.out.println("發送成功!");
			else
				System.out.println("發送失敗!");

			// 例外處理
		} catch (Exception ee) {
			
			// 提示訊息
			System.out.println("更新錯誤");
			System.out.println(ee.getMessage());
		}
	}

	// 取得某網址的檔案
	private static void getFilefromUrl(String urlstring, String objfile){
		
		// 嘗試
		try {
			// 新檔案
			File desFile = new File(objfile);
	
			// 如果檔案已存在，刪除原來的檔案
			if (desFile.exists()) desFile.delete();
	
			// 取得URL
			URL url = new URL(urlstring);
			URLConnection connection = url.openConnection();

			// buffer
			byte[] data = new byte[1];

			// 設定接收資料流來源 ,就是要下載的網址
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					connection.getInputStream());

			// 設定　儲存 要下載檔案的位置.
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(desFile));
			while (bufferedInputStream.read(data) != -1) {
				bufferedOutputStream.write(data);
			}

			// 將緩衝區中的資料全部寫出
			bufferedOutputStream.flush();

			// 關閉資料流
			bufferedInputStream.close();
			bufferedOutputStream.close();
			
		} catch (Exception ee) {

			// 提示訊息
			System.out.println("取得網路內容失敗");
			System.out.println(ee.getMessage());
		}
	}

	// 程式進入點
	public static void main(String[] args)
	{
		// Simple Factory
		SimpleFactory sf = new SimpleFactory();

		// 資料庫
		Database db = sf.getDatabase();
		db.start_link();

		// 檢查更新，並Parse資料、存進資料庫
		update(db);

		// 結束連線
		db.end_link();
	}

}
