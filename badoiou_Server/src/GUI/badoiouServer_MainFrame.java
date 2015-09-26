package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Component;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import java.awt.Canvas;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class badoiouServer_MainFrame extends JFrame {

	private JPanel contentPane;
	private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					badoiouServer_MainFrame frame = new badoiouServer_MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public badoiouServer_MainFrame() {
		setTitle("Badoiou Server \u7BA1\u7406\u7CFB\u7D71");
		setFont(new Font("SimHei", Font.BOLD, 20));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int) screenSize.getWidth() - 50, (int) screenSize.getHeight() - 50);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u7576\u524D\u9023\u7DDA", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(table, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("\u7CFB\u7D71");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u7576\u524D\u9023\u7DDA");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_1 = new JMenu("\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("\u5546\u5BB6\u7BA1\u7406");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isMake = true;
				JPanel storesPanel = new JPanel();
				
				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					
					if (tabbedPane.getTitleAt(i).equals("\u5546\u5BB6\u7BA1\u7406")) {
						isMake = false;
						break;
					}
				}

				if (isMake)
					tabbedPane.addTab("\u5546\u5BB6\u7BA1\u7406", null, storesPanel, null);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u7522\u54C1\u7BA1\u7406");
		mnNewMenu_1.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_2 = new JMenu("\u8AAA\u660E");
		menuBar.add(mnNewMenu_2);

	}
}
