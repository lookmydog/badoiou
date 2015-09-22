package Main;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import HelpFunc.G_main;

public class Handle_Client_Process extends Thread {

	private String ip = "";
	private int port = 0;
	private int serialNo = 0;
	private Socket client = null;

	// 建構子
	public Handle_Client_Process(Socket socket, int No) {

		this.client = socket;
		this.serialNo = No;
		this.port = socket.getPort();
		this.ip = socket.getInetAddress().getHostAddress();

		try {
			this.client.setSoTimeout(30*1000);
			this.client.setKeepAlive(true);
			this.client.setOOBInline(true);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		G_main.logger.info("Create Client,ip:" + this.client.getInetAddress() + "\tport:" + this.client.getPort()
				+ "\tserialNo:" + this.serialNo);
	}

	// override
	public void run() {
		try {
			while (true) {
				
				//斷線處理
				this.client.sendUrgentData(0xFF);
				
				G_main.logger.info("heart beat,serialNo:" + this.serialNo);
				Thread.currentThread().sleep(1000);
			}
		}catch(SocketException se){
			try {
				G_main.logger.error(se.getMessage());
				this.client.close();
				this.interrupt();
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				G_main.logger.error(ioe.getMessage());
			}
		}
		catch (Exception e) {
			G_main.logger.error(e.getMessage());
		}
	}
}
