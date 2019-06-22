package status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.CommonVO;

public class Boiler_on_off_status_Thread extends Thread {
	
	String now;
	
	public Boiler_on_off_status_Thread(String now) {
		this.now = now;
	}
	@Override
	public void run() {
		super.run();
		while(Snowpiercer.stay) {
			
		}
		Snowpiercer.stay = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//보일러가 켜져있으면
		if(now.equals("Y")) {
			try {
				URL url = new URL(CommonVO.arduino_ip + "/BY");
				HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
				urlconnection.setRequestMethod("GET");
				BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//				String result = "";
//				String line;
//				while ((line = br.readLine()) != null) {
//					result = result + line + "\n";
//				}
				System.out.println("보일러끄기");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				URL url = new URL(CommonVO.arduino_ip + "/BN");
				HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
				urlconnection.setRequestMethod("GET");
				BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//				String result = "";
//				String line;
//				while ((line = br.readLine()) != null) {
//					result = result + line + "\n";
//				}
				System.out.println("보일러켜기");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Snowpiercer.stay = false;
	}
}
