package status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.CommonVO;

public class Snowpiercer extends Thread {

	public static int snowpiercerStop = 3;
	public static boolean autoWindow = true;
	private String id;
	private static String check_point = "3";
	public static boolean stay = false;
	
	public Snowpiercer(String id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		super.run();
		boolean is_window_open = true;
		boolean is_window_close = true;
		while(true) {
			System.out.println("쓰레드 while 체크");
			if(snowpiercerStop == 2) {
	        	System.out.println("아두이노 서버 멈춤");
	        	stay = false;
		        break;
	        }
			try {
				while(stay) {
					
				}
				stay = true;
				Thread.sleep(1000);
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
		        String moi = jsonObject.get("moisture").toString();
//		        String temper = jsonObject.get("temperature").toString();
		        int moisture = Integer.parseInt(moi);
//		        int temperature = Integer.parseInt(temper);
		        String door = jsonObject.get("door").toString();
		        door = door.equals("1") ? "Y" : "N";
		        String boiler = jsonObject.get("boiler").toString();
		        boiler = boiler.equals("0") ? "N" : "Y";
		        System.out.println("문상태 : "+door);
		        System.out.println("습도 : "+moi);
//		        System.out.println("온도 : "+temperature);
		        String doorCheck = "noPush";
		        if(!check_point.equals(door)) {
		        	doorCheck = "push";
		        }
		        System.out.println("푸쉬알림 알려주기 : " + doorCheck);
		        System.out.println("보일러 : "+boiler);
		        //temperature(temperature);
		        boiler(boiler, id);
		        doorStatus(door, doorCheck);
		        check_point = door;
//		        if(autoWindow) {
//			        if(moisture > 400) {
//			        	if(is_window_close) {
//			        		is_window_close = false;
//			        		is_window_open = true;
//			        		URL url2 = new URL(CommonVO.serverIP + "/AA/controll_windowClose?id="+id );
//					        HttpURLConnection urlconnection2 = (HttpURLConnection) url2.openConnection();
//					        urlconnection2.setRequestMethod("GET");
//					        BufferedReader br2 = new BufferedReader(new InputStreamReader(urlconnection2.getInputStream(), "UTF-8"));
//					        while ((line = br2.readLine()) != null) {
//					            result = result + line + "\n";
//					         }
//					         System.out.println("문닫히고 불러온 결과: "+result);
//			        	}
//			        }else {
//			        	if(is_window_open) {
//			        		is_window_open = false;
//			        		is_window_close = true;
//			        		URL url2 = new URL(CommonVO.serverIP + "/AA/controll_windowOpen?id="+id );
//					        HttpURLConnection urlconnection2 = (HttpURLConnection) url2.openConnection();
//					        urlconnection2.setRequestMethod("GET");
//					        BufferedReader br2 = new BufferedReader(new InputStreamReader(urlconnection2.getInputStream(), "UTF-8"));
//					        while ((line = br2.readLine()) != null) {
//					            result = result + line + "\n";
//					         }
//					         System.out.println("문열리고 불러온 결과: "+result);
//			        	}
//			        }
//		        }
		        stay = false;
		        Thread.sleep(2000);
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
				stay = false;
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				stay = false;
				System.out.println("아두이노 서버 예외발생!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
			}finally {
				
			}
		}
		
	}
	
	private void temperature(int tem) {
		try {
			URL url = new URL(CommonVO.serverIP + "/AA/updateTemperature?id="+id+"&temper="+tem);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine()) != null) {
	           result = result + line + "\n";
	        }
	        System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void doorStatus(String door, String doorCheck) throws Exception {
		if(door.equals("Y")) {
			
			//문 열림
			URL url = new URL(CommonVO.serverIP + "/AA/closeDoor.status?id="+id+"&doorCheck="+doorCheck);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine()) != null) {
	           result = result + line + "\n";
	        }
	        System.out.println(result);
	        
		}else if(door.equals("N")) {
			
			//문 닫힘
			URL url = new URL(CommonVO.serverIP + "/AA/openDoor.status?id="+id+"&doorCheck="+doorCheck);
	        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
	        urlconnection.setRequestMethod("GET");
	        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	        String result = "";
	        String line;
	        while ((line = br.readLine()) != null) {
	           result = result + line + "\n";
	        }
	        System.out.println(result);
	        
		}
	}
	
	private void boiler(String status, String id) throws Exception{
		URL url = new URL(CommonVO.serverIP + "/AA/boilerUpdate?status="+status+"&id="+id);
        HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
        urlconnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
	}
	
}
