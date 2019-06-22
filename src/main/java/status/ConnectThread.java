package status;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectThread{
	private String serverIP;
    private int serverPort;
    
    private Socket mSocket = null;
    private String mServerIP = null;
    private PrintWriter mOut;
    private BufferedReader mIn;

    ConnectThread(String ip, int port) {
        serverIP = ip;
        serverPort = port;
    }

	public void connect() {
		try {

            mSocket = new Socket(serverIP, serverPort);
            mServerIP = mSocket.getRemoteSocketAddress().toString();

        } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void sender(String msg) {
		mOut.println(msg);
        mOut.flush();
	}
	
	
//	class SenderThread implements Runnable {
//
//        private String msg;
//
//        SenderThread(String msg) {
//            this.msg = msg;
//        }
//
//        @Override
//        public void run() {
//
//            mOut.println(this.msg);
//            mOut.flush();
//            
//        }
//    }
}
