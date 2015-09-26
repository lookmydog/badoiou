package Logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.TestDataTransferFrame;

public class TestDataSocket extends Thread{
	private int SerialNo;
	private int port = 1480;
	private ServerSocket dataSocket = null;
	private Socket client = null;
	private String ip = null;
	
	public TestDataSocket(int SerialNo) {
		this.SerialNo = SerialNo;
	}

	public void run() {
		int newPort = this.port+(this.SerialNo*2)+1;
		
		try {
			dataSocket = new ServerSocket(newPort);
			
			client = dataSocket.accept();
			System.out.println("datasocket ok");
			
			new TestDataTransferFrame(client);;
//			client.close();
//			dataSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
