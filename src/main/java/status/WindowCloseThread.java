package status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.CommonVO;

public class WindowCloseThread extends Thread{
	@Override
	public void run() {
		super.run();
		try {
//			while(Snowpiercer.stay) {
//				
//			}
//			Snowpiercer.stay = true;
			Thread.sleep(1000);
			
			
			String tmp = CommonVO.arduino_ip + "/C";
			URL url = new URL(tmp);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//			String result = "";
//			String line;
//			while ((line = br.readLine()) != null) {
//				result = result + line + "\n";
//			}
			System.out.println("Ã¢¹®´Ý±â ³¡");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Snowpiercer.stay = false;
	}
}
