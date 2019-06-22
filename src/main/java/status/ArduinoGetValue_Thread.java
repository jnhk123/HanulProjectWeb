package status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.CommonVO;

public class ArduinoGetValue_Thread extends Thread {
	
	private String id;
	
	public ArduinoGetValue_Thread(String id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				URL url = new URL(CommonVO.arduino_ip);
		        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
		        urlconnection.setRequestMethod("GET");
		        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		        String result = "";
		        String line;
		        while ((line = br.readLine()) != null) {
		           result = result + line + "\n";
		        }
		        System.out.println(result);
		        
		        JSONParser jsonParser = new JSONParser();
		        Object obj = jsonParser.parse(result);
		        JSONObject jsonObject = (JSONObject) obj;
		        
		        String door = jsonObject.get("door").toString();
		        door = door.equals("1") ? "N" : "Y";
		        
		        HashMap<String, String> map = new HashMap<String, String>();
		        map.put("door", door);
		        map.put("id", id);
		        excuteUpdate(map);
		        break;
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void excuteUpdate(HashMap<String, String> map) {
		try {
			URL url = new URL(CommonVO.serverIP+ "/AA/curStatusUpdate?id="+map.get("id")+"&door="+map.get("door"));
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
