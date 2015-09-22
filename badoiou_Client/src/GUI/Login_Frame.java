package GUI;

import HelpFunc.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Login_Frame extends JFrame {

	private static final long serialVersionUID = 847651487361554900L;

	// variable
	// 系統螢幕大小
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int frame_width;
	private int frame_height;
	private int frame_posX;
	private int frame_posY;
	// UI物件
	private String frame_title;
	private JFormattedTextField txt_IpInput;
	private JFormattedTextField txt_PortInput;
	private JTextField txt_Name;
	private JButton btn_Enter;
	// 連線參數
	private String ip = "";
	private String userName = "";
	private int port = 0;
	// 其他
	private final String FONT_TYPE = "SimHei";

	// 建構子
	public Login_Frame() {

		// 配置大小
		frame_width = (int) (screenSize.width / 3);
		frame_height = (int) (screenSize.height / 3);
		frame_posX = (int) (screenSize.width / 2 - frame_width / 2);
		frame_posY = (int) (screenSize.height / 2 - frame_height / 2);

		// 配置元件、標題
		frame_title = "使用者登入畫面";
		txt_Name = new JTextField();
		txt_IpInput = new JFormattedTextField();
		txt_PortInput = new JFormattedTextField();
		btn_Enter = new JButton();

		init_UI();

	}

	// 初始化界面
	private void init_UI() {
		// 設置寬、高
		this.setSize(frame_width, frame_height);
		// 按了X 做甚麼事件
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// 設置出現位置
		this.setLocation(frame_posX, frame_posY);
		// 配置layout方式
		// this.getContentPane().setLayout(new GridLayout(4,3));
		// 設置標題
		this.setTitle(frame_title);
		// 設置內容
		init_content(this.getContentPane());
		// 可顯現
		this.setVisible(true);
	}

	// 設置內容
	private void init_content(Container cp) {

		Font font = new Font(FONT_TYPE, Font.PLAIN, 30);
		JPanel p1 = new JPanel(new SpringLayout());
		int iLayPad = 25;

		// label -> name
		JLabel lbl_Name = new JLabel("請輸入您的名稱:", JLabel.TRAILING);
		lbl_Name.setFont(font);

		// text -> name
		txt_Name.setColumns(20);
		txt_Name.setFont(font);

		// Panel 加入obj
		p1.add(lbl_Name);
		lbl_Name.setLabelFor(txt_Name);
		p1.add(txt_Name);

		// label -> ip
		JLabel lbl_Ip = new JLabel("請輸入Server IP:", JLabel.TRAILING);
		lbl_Ip.setFont(font);

		// formatted text -> ip
		txt_IpInput.setColumns(20);
		txt_IpInput.setFont(font);

		// Panel 加入obj
		p1.add(lbl_Ip);
		lbl_Ip.setLabelFor(txt_IpInput);
		p1.add(txt_IpInput);

		// label -> port
		JLabel lbl_Port = new JLabel("請輸入指定 Port:", JLabel.TRAILING);
		lbl_Port.setFont(font);

		// formatted text -> port
		txt_PortInput.setColumns(20);
		txt_PortInput.setFont(font);

		// Panel 加入obj
		p1.add(lbl_Port);
		lbl_Port.setLabelFor(txt_PortInput);
		p1.add(txt_PortInput);

		// label -> port
		JLabel lbl_Empty = new JLabel("", JLabel.TRAILING);
		lbl_Empty.setFont(font);

		// btn -> enter
		btn_Enter.setText("確定");
		btn_Enter.setFont(font);
		set_Button_Action(btn_Enter);

		// Panel 加入obj
		p1.add(lbl_Empty);
		lbl_Empty.setLabelFor(btn_Enter);
		p1.add(btn_Enter);

		// 載入JAVA 提供的 spring layout 排版
		SpringUtilities.makeCompactGrid(p1, 4, 2, iLayPad, iLayPad, iLayPad, iLayPad);

		cp.add(p1);
	}

	// 製作button的動作內容
	private void set_Button_Action(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ip = txt_IpInput.getText();
					userName = txt_Name.getText();
					port = Integer.parseInt(txt_PortInput.getText());

					//System.out.println("在login JFrame裡 name:" + userName + "\tIP:" + ip + "\tport:" + port);

					//Component c = (Component) e.getSource();
					// 找到Jframe
					//c = c.getParent().getParent().getParent().getParent().getParent();

					// if (c instanceof JFrame) {
					// ((JFrame) c).dispose();
					// } else {
					// System.out.println(c.toString());
					// }

				} catch (NumberFormatException NE) {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Port 請輸入數字");
				}
			}
		});
	}

	// 回傳數值
	public Object[] getObj() {
		Object[] obj = { this.userName, this.ip, this.port };
		return obj;
	}
	//初始化參數
	public void initObj(){
		  this.ip = "";
		  this.userName = "";
		  this.port = 0;
	}
}
