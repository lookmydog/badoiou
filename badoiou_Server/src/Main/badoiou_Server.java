package Main;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import HelpFunc.G_main;
import Logic.TestDataSocket;

public class badoiou_Server {

	// server
	private static ServerSocket server = null;
	// serialNo
	private static int serialNo = 0;
	// map for <serialNo,socket>
	private static HashMap<String, Socket> ipMap = null;
	// server accept port
	private static int port = 1480;

	public static void main(String[] args) {
		// 建立log 資料夾
		File directory = new File(".\\log");
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("Directory log is made.");
			}
		}
		// 建構g_main
		new G_main();

		G_main.logger.info("The Server is running.");

		try {
			server = new ServerSocket(port);
			ipMap = new HashMap<>();

			while (true) {
				Socket client;
				synchronized (server) {
					 client = server.accept();
				}
				int No = serialNo++;

				// put in hashmap
				ipMap.put(String.valueOf(No), client);

				//test
				DataOutputStream dos = new DataOutputStream(client.getOutputStream());
				dos.writeUTF(String.valueOf(No));
			
				// make link
				new Handle_Client_Process(client, No).start();

				//test datasocket
				new TestDataSocket(No).start();
			}
		} catch (Exception e) {
			G_main.logger.error(e.getMessage());
			// 關閉server
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
