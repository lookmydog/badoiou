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

		G_main.logger.info("Create Client,ip:" + this.client.getInetAddress() + "\tport:" + this.client.getPort()
				+ "\tserialNo:" + this.serialNo);
	}

	// override
	public void run() {
		new HeartBeat_Server(this.client, this.serialNo).start();
		//new ReceiveFromClient(this.client).start();
	}
}
