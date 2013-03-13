package Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;


import DB.AttValuesCountBar;
import DB.ConnectionInstance;
import Product.Count;

public class attValuesCountDBBarMain {
	public static JProgressBar jpb = null;
	static JButton jb = null;
	public static int rowCount = 0;

	public static void main(String args[]) {
		JFrame frm = new JFrame();
		Container contentPane = frm.getContentPane();
		jpb = new JProgressBar();
		jpb.setOrientation(JProgressBar.HORIZONTAL);
		jpb.setMaximum(100);
		jpb.setMinimum(0);
		jpb.setValue(0);
		jpb.setStringPainted(true);
		jpb.setPreferredSize(new Dimension(400, 50));
		contentPane.add(jpb, BorderLayout.CENTER);
		jb = new JButton("Start");
		contentPane.add(jb, BorderLayout.SOUTH);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
		jb.addActionListener(new AttValuesDB());
	}

	//�ڲ���
	static class AttValuesDB implements ActionListener {
		static int Times = 40;//ִ�еĴ���

		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable() {
				public void run() {
					int k;
					String dbName = "";
					for (k = Times - 1; k >= 39; k--) {
						dbName = "attValuesCout" + k;
						Connection connRow = new ConnectionInstance().getConn();
						Statement statementRow = null;
						try {
							statementRow = connRow.createStatement();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						String sql = "select * from productsimiMore1 where productID % "
								+ Times + "=" + k; // Ҫִ�е�SQL���: \\ ��б��(��\��)�ַ�; \' ������(��'��) ; and att like '%\\'%'
						//�趨bar����ֵ
						ResultSet resultSet = null;
						try {
							resultSet = statementRow.executeQuery(sql);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						try {
							while (resultSet.next()) {
								rowCount++;
							}
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						jpb.setMaximum((int) (rowCount * 1.5));//������ΪrowCount��1.5��

						try {
							connRow.close();
							if (connRow.isClosed()) {
								System.out
										.println("Succeeding closing the connection of counting the rows!");
							}
						} catch (SQLException e2) {
							e2.printStackTrace();
						}

						Connection conn = new ConnectionInstance().getConn();
						Statement statement = null;
						ResultSet rs = null;
						HashMap<String, HashMap<String, Integer>> hsHashMap = new HashMap<String, HashMap<String, Integer>>();
						AttValuesCountBar attValuesCountBar = new AttValuesCountBar(
								hsHashMap);
						try {
							statement = conn.createStatement(); // statement����ִ��SQL���
							rs = statement.executeQuery(sql);
							//ͳ����attValues��
							while (rs.next()) {
								attValuesCountBar.attValuescount(
										rs.getString(2), rs.getString(3));
								Count.COUNT++;
								jpb.setValue(Count.COUNT);
							}
							try {
								rs.close();
								conn.close();
								if (conn.isClosed()) {
									System.out
											.println("Succeed closing the connection of attValuesCount");
								}
							} catch (SQLException e) {
								System.out.println("�ر�connection�쳣");
								e.printStackTrace();
							}
							attValuesCountBar.storeToDB(dbName);
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
}