package Main;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import HelpFunc.G_main;

public class HeartBeat_Server extends Thread {
	private int heartBeatCount = 1;
	private Socket heartBeatSocket = null;
	private int serialNo = 0;

	public HeartBeat_Server(Socket client, int No) {
		this.heartBeatSocket = client;
		this.serialNo = No;

		// set variable
		try {
			this.heartBeatSocket.setSoTimeout(30 * 1000);
			this.heartBeatSocket.setKeepAlive(true);
			this.heartBeatSocket.setOOBInline(true);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
		}

	}

	@Override
	public void run() {
		while (true) {

			try {
				this.heartBeatSocket.sendUrgentData(0xFF);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				G_main.logger.error(e1.getMessage());

				try {
					// 關閉socket
					if (this.heartBeatSocket != null)
						this.heartBeatSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					G_main.logger.error(e.getMessage());
				}
				break;
			}

			//G_main.logger.info("Client SerialNo:" + this.serialNo + ",heartBeat:" + this.heartBeatCount++);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
			}
			if (heartBeatCount >= 9999)
				heartBeatCount = 1;
		}

	}
}
