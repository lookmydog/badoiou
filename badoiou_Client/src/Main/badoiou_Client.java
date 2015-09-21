package Main;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

import GUI.*;
import HelpFunc.G_main;

import org.apache.log4j.*;

public class badoiou_Client {

	private static Object[] obj = null;
	private static String ip = "";
	private static String name = "";
	private static int port = 0;

	// socket
	private static Socket client = null;

	public static void main(String[] args) {
		// �إ�log��Ƨ�
		File directory = new File(".\\log");
		if (!directory.exists()) {
			if (directory.mkdirs()) {
				System.out.println("make log directory OK ");
			}
		}
		// �غcG_main
		new G_main();

		Login_Frame login = new Login_Frame();

		G_main.logger.info("Start Login JFrame");

		while (login.isShowing()) {

			obj = login.getObj();
			// username ip port
			if (!((String) obj[0]).equals("") && !((String) obj[1]).equals("") && (int) obj[2] != 0) {
				name = (String) obj[0];
				ip = (String) obj[1];
				port = (int) obj[2];
				// ���ճs�u
				if (checkLinkServer(ip, port)) {
					// �s�u���\
					// �]�߰Ѽ�
					// ��������
					login.dispose();
					G_main.logger.info("Login JFrame is dispose");
				} else {
					// �s�u����
					obj = null;
					// ��l�ưѼ�
					login.initObj();
					G_main.logger.info("Initialize Obj variables");

					JOptionPane.showMessageDialog(login, "�L�k�s�Wserver,�Э��s��J");
				}

			}
			// 0.2�� �]�@���j��
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
			}
		}
		// �C�X�Ѽ�
		G_main.logger
				.info("End Login JFrame. name:" + name + " ip:" + ip+ " port:" + port);

		// �p�G�٬O�S���s�u�A���O�����n�J����
		if (client == null) {
			G_main.logger.info("Exit Client App");
			System.exit(0);
		} else {
			// �i�J�ϥΪ̤���

			// ����heart beat

			try {
				client.setSoTimeout(30*1000);
				client.setKeepAlive(true);
				client.setOOBInline(true);
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//test
			int count =1;
			while (true) {

				// �_�u�B�z�ݭn����
				try {
					client.sendUrgentData(0xFF);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					G_main.logger.error(e1.getMessage());
					
					try {
						client.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				
				G_main.logger.info("test server heart "+count++);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					G_main.logger.error(e.getMessage());
				}
			}
		}
		G_main.logger.info("Exit Client App");
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
