package status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.CommonVO;

public class WindowOpenThread extends Thread {
	@Override
	public void run() {
		super.run();
//		while(Snowpiercer.stay) {
//			
//		}
//		Snowpiercer.stay = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String tmp = CommonVO.arduino_ip + "/O";
		
		try {
			URL url = new URL(tmp);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//			String result = "";
//			String line;
//			while ((line = br.readLine()) != null) {
//				result = result + line + "\n";
//			}
//			System.out.println(result);
			System.out.println("창문 열기");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Snowpiercer.stay = false;
	}
}
