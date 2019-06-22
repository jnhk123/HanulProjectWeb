package status;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import common.CommonVO;

public class Light_on_off_thread extends Thread {
	
	private StatusVO vo;
	
	public Light_on_off_thread(StatusVO vo) {
		this.vo = vo;
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
		try {
			String tmp;
			if (vo.getLight().equals("Y")) {
				tmp = CommonVO.arduino_ip + "/L";
				System.out.println(tmp);
			} else {
				tmp = CommonVO.arduino_ip + "/H";
				System.out.println(tmp);
			}

			URL url = new URL(tmp);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//			String result = "";
//			String line;
//			while ((line = br.readLine()) != null) {
//				result = result + line + "\n";
//			}
			System.out.println("Àüµî²ô±â");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Snowpiercer.stay = false;
	}
}
