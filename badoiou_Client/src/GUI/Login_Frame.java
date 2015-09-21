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
	// �t�οù��j�p
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private int frame_width;
	private int frame_height;
	private int frame_posX;
	private int frame_posY;
	// UI����
	private String frame_title;
	private JFormattedTextField txt_IpInput;
	private JFormattedTextField txt_PortInput;
	private JTextField txt_Name;
	private JButton btn_Enter;
	// �s�u�Ѽ�
	private String ip = "";
	private String userName = "";
	private int port = 0;
	// ��L
	private final String FONT_TYPE = "SimHei";

	// �غc�l
	public Login_Frame() {

		// �t�m�j�p
		frame_width = (int) (screenSize.width / 3);
		frame_height = (int) (screenSize.height / 3);
		frame_posX = (int) (screenSize.width / 2 - frame_width / 2);
		frame_posY = (int) (screenSize.height / 2 - frame_height / 2);

		// �t�m����B���D
		frame_title = "�ϥΪ̵n�J�e��";
		txt_Name = new JTextField();
		txt_IpInput = new JFormattedTextField();
		txt_PortInput = new JFormattedTextField();
		btn_Enter = new JButton();

		init_UI();

	}

	// ��l�Ƭɭ�
	private void init_UI() {
		// �]�m�e�B��
		this.setSize(frame_width, frame_height);
		// ���FX ���ƻ�ƥ�
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		// �]�m�X�{��m
		this.setLocation(frame_posX, frame_posY);
		// �t�mlayout�覡
		// this.getContentPane().setLayout(new GridLayout(4,3));
		// �]�m���D
		this.setTitle(frame_title);
		// �]�m���e
		init_content(this.getContentPane());
		// �i��{
		this.setVisible(true);
	}

	// �]�m���e
	private void init_content(Container cp) {

		Font font = new Font(FONT_TYPE, Font.PLAIN, 30);
		JPanel p1 = new JPanel(new SpringLayout());
		int iLayPad = 25;

		// label -> name
		JLabel lbl_Name = new JLabel("�п�J�z���W��:", JLabel.TRAILING);
		lbl_Name.setFont(font);

		// text -> name
		txt_Name.setColumns(20);
		txt_Name.setFont(font);

		// Panel �[�Jobj
		p1.add(lbl_Name);
		lbl_Name.setLabelFor(txt_Name);
		p1.add(txt_Name);

		// label -> ip
		JLabel lbl_Ip = new JLabel("�п�JServer IP:", JLabel.TRAILING);
		lbl_Ip.setFont(font);

		// formatted text -> ip
		txt_IpInput.setColumns(20);
		txt_IpInput.setFont(font);

		// Panel �[�Jobj
		p1.add(lbl_Ip);
		lbl_Ip.setLabelFor(txt_IpInput);
		p1.add(txt_IpInput);

		// label -> port
		JLabel lbl_Port = new JLabel("�п�J���w Port:", JLabel.TRAILING);
		lbl_Port.setFont(font);

		// formatted text -> port
		txt_PortInput.setColumns(20);
		txt_PortInput.setFont(font);

		// Panel �[�Jobj
		p1.add(lbl_Port);
		lbl_Port.setLabelFor(txt_PortInput);
		p1.add(txt_PortInput);

		// label -> port
		JLabel lbl_Empty = new JLabel("", JLabel.TRAILING);
		lbl_Empty.setFont(font);

		// btn -> enter
		btn_Enter.setText("�T�w");
		btn_Enter.setFont(font);
		set_Button_Action(btn_Enter);

		// Panel �[�Jobj
		p1.add(lbl_Empty);
		lbl_Empty.setLabelFor(btn_Enter);
		p1.add(btn_Enter);

		// ���JJAVA ���Ѫ� spring layout �ƪ�
		SpringUtilities.makeCompactGrid(p1, 4, 2, iLayPad, iLayPad, iLayPad, iLayPad);

		cp.add(p1);
	}

	// �s�@button���ʧ@���e
	private void set_Button_Action(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					ip = txt_IpInput.getText();
					userName = txt_Name.getText();
					port = Integer.parseInt(txt_PortInput.getText());

					//System.out.println("�blogin JFrame�� name:" + userName + "\tIP:" + ip + "\tport:" + port);

					//Component c = (Component) e.getSource();
					// ���Jframe
					//c = c.getParent().getParent().getParent().getParent().getParent();

					// if (c instanceof JFrame) {
					// ((JFrame) c).dispose();
					// } else {
					// System.out.println(c.toString());
					// }

				} catch (NumberFormatException NE) {
					JOptionPane.showMessageDialog((Component) e.getSource(), "Port �п�J�Ʀr");
				}
			}
		});
	}

	// �^�Ǽƭ�
	public Object[] getObj() {
		Object[] obj = { this.userName, this.ip, this.port };
		return obj;
	}
	//��l�ưѼ�
	public void initObj(){
		  this.ip = "";
		  this.userName = "";
		  this.port = 0;
	}
}
