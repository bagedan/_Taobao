package Others;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnectionSqlServer {
	Connection dbConn = null;

	public void getConnectionSqlServer() {

		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=tempdb";
		String user = "sa";
		String password = "123456";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();//��������
		} catch (Exception ex) {
			System.out.println("��������ʧ��");
			ex.printStackTrace();
		}

		try {
			dbConn = DriverManager.getConnection(url, user, password);
			System.out.println("�ɹ��������ݿ⣡");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GetConnectionSqlServer getConn = new GetConnectionSqlServer();
		getConn.getConnectionSqlServer();
	}
}