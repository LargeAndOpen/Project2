import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;


// 檔案相關處理
public class FileManager {

	// 取得某網址的檔案
	public void getFilefromUrl(String urlstring, String objfile){
		
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

}
