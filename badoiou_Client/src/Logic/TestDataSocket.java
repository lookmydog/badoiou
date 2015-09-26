package Logic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONObject;

import HelpFunc.G_main;

public class TestDataSocket extends Thread {
	private int SerialNo;
	private int port = 1480;
	private Socket dataSocket = null;
	private String ip = null;
	private DataInputStream dis = null;

	public TestDataSocket(String ip, int SerialNo) {
		this.SerialNo = SerialNo;
		this.ip = ip;
	}

	public void run() {
		int newPort = this.port + (this.SerialNo * 2) + 1;

		try {
			// 等待
			try {
				G_main.logger.info("Wait 5 Sec");
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
			}

			dataSocket = new Socket(this.ip, newPort);
			System.out.println("datasocket ok");

			dis = new DataInputStream(dataSocket.getInputStream());
			// BufferedReader br = new BufferedReader(dis);

			String strTmp = "";
			// test json

			while (true) {
				try {
					strTmp = dis.readUTF();
					JSONObject json = new JSONObject(strTmp);
					String func = (String) json.get("function");
					String storeName = (String) json.get("storename");

					// if (!strTmp.equals("") && strTmp != null)
					// System.out.println(strTmp);

					if (func != null) {
						System.out.println("功能" + func + ",名稱" + storeName);
					}
				} catch (Exception e) {
					G_main.logger.error(e.getMessage());
					break;
				}

				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					G_main.logger.error(e.getMessage());
				}
			}

			// dataSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
		} finally {

			try {
				dis.close();
				dataSocket.close();
				Thread.currentThread().interrupt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
			}

		}

	}
}
