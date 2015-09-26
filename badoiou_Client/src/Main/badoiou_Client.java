﻿package Main;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

import GUI.*;
import HelpFunc.G_main;
import Logic.TestDataSocket;

import org.apache.log4j.*;

public class badoiou_Client {

	private static Object[] obj = null;
	private static String ip = "";
	private static String name = "";
	private static int port = 0;

	// socket
	private static Socket client = null;

	public static void main(String[] args) {
		// 建立log資料夾
		File directory = new File(".\\log");
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("make log directory OK ");
			}
		}
		// 建構G_main
		if (args.length != 0) {
			new G_main(args[0]);
		} else {
		new G_main();
		}

		Login_Frame login = new Login_Frame();

		G_main.logger.info("Start Login JFrame");

		while (login.isShowing()) {

			obj = login.getObj();
			// username ip port
			if (!((String) obj[0]).equals("") && !((String) obj[1]).equals("") && (int) obj[2] != 0) {
				name = (String) obj[0];
				ip = (String) obj[1];
				port = (int) obj[2];
				// 嘗試連線
				if (checkLinkServer(ip, port)) {
					// 連線成功
					// 設立參數
					// 關閉視窗
					login.dispose();
					G_main.logger.info("Login JFrame is dispose");
				} else {
					// 連線失敗
					obj = null;
					// 初始化參數
					login.initObj();
					G_main.logger.info("Initialize Obj variables");

					JOptionPane.showMessageDialog(login, "無法連上server,請重新輸入");
				}

			}
			// 0.2秒 跑一次迴圈
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
			}
		}
		// 列出參數
		G_main.logger.info("End Login JFrame. name:" + name + " ip:" + ip + " port:" + port);

		// 如果還是沒有連線，但是關閉登入視窗
		if (client == null) {
			G_main.logger.info("Exit Client App");
			System.exit(0);
		} else {
			// 進入使用者介面
			String str = "-1";
					try {
				DataInputStream dis = new DataInputStream(client.getInputStream());
				str = dis.readUTF();
				System.out.println(str);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			// 測試heart beat
			new HeartBeat_Client(client, 0).start();
			// new SendToServer(client).start();

			// test
			new TestDataSocket(ip, Integer.valueOf(str)).start();
			}
		G_main.logger.info("Exit Client Main Thread");
	}

	private static boolean checkLinkServer(String ip, int port) {
		try {
			client = new Socket(ip, port);
			if (client.isConnected()) {
				G_main.logger.info("server connect success,ip:" + ip + "\tport:" + port);
			}
			return true;
		} catch (Exception e) {
			client = null;
			G_main.logger.error(e.getMessage());
			return false;
		}
	}
}
