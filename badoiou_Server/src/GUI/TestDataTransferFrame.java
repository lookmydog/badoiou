package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import HelpFunc.G_main;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class TestDataTransferFrame extends JFrame {

	private static final long serialVersionUID = 68899978771105121L;
	private Socket DatSocket = null;
	private JTextField txt_Send;
	private JTextField txt_Receive;
	private DataOutputStream dos = null;

	public TestDataTransferFrame() {
		super();

		setTitle("Test Server");
		setSize(474, 340);
		setLocation(200, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane, BorderLayout.CENTER);
		layeredPane.setLayout(new MigLayout("", "[91px][91px][91px][91px][91px]", "[97px][97px][97px]"));

		JLabel lblNewLabel = new JLabel("Send Msg");
		layeredPane.add(lblNewLabel, "cell 0 0,grow");
				
						txt_Send = new JTextField();
						layeredPane.add(txt_Send, "cell 1 0 3 1,grow");
						txt_Send.setColumns(10);
		
				JLabel lblNewLabel_1 = new JLabel("rec Msg");
				layeredPane.add(lblNewLabel_1, "cell 0 1,grow");
		
				txt_Receive = new JTextField();
				layeredPane.add(txt_Receive, "cell 1 1 3 1,grow");
				txt_Receive.setColumns(10);
				
						JButton btn_SendMsg = new JButton("Send String");
						layeredPane.add(btn_SendMsg, "cell 2 2 2 1,grow");
		btn_SendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SendMsg2Client()) {
					G_main.logger.info("Send Msg To Client is success");
				} else {
					G_main.logger.error("Send Msg To Client is fail");
				}
			}
		});
	}

	public TestDataTransferFrame(Socket socket) {
		this();
		this.DatSocket = socket;
		this.setVisible(true);
	}

	private boolean SendMsg2Client() {
		boolean isConnect = false;
		boolean isSend = false;

		// judge is close or not
		if (!this.DatSocket.isClosed()) {
			isConnect = true;
		} else {
			G_main.logger.error("Data Socket is disconnect");
			return isConnect;
		}

		// get socket outputstream
		try {
			dos = new DataOutputStream(this.DatSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
			return false;
		}

		// send msg to client
		try {
			G_main.logger.info("What I send: " + txt_Send.getText().trim());
			// test
			String strJson = "{\"function\":\"order\",\"storename\": \"池上便當\",\"type\": \"lunch\",\"choose\": [{\"name\": \"鰻魚便當\",\"price\": 80,\"amount\": 1}]}";
			dos.writeUTF(strJson);
			// dos.writeUTF(txt_Send.getText().trim());
			dos.flush();
			isSend = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
			return false;
		}

		// close dos
		// try {
		// dos.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// G_main.logger.error(e.getMessage());
		// }

		return isConnect && isSend;
	}
}
