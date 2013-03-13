package Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import DB.ConnectionInstance;

public class ConnectionTest {
	public static void main(String args[]) {
		Connection conn = new ConnectionInstance().getConn();
		Statement statement;
		String sql = "select * from productsimiMore1 limit 10"; // Ҫִ�е�SQL���
		ResultSet rs = null;
		try {
			statement = conn.createStatement(); // statement����ִ��SQL���
			rs = statement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();// ��ȡԪ����
			int count = rsmd.getColumnCount();
			System.out.println("ִ�н��������ʾ:");
			System.out
					.println("----------------title-------------------------");
			String sepa = "\t\t";
			for (int i = 1; i <= count; i++) {
				System.out.print(rsmd.getColumnLabel(i));
				System.out.print(sepa);
			}
			System.out.println();
			System.out.println("------------------data-----------------------");
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + sepa);
				}
				System.out.println();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("�ر�connection�쳣");
			e.printStackTrace();
		}
	}
}