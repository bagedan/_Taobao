package DB;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionInstance {
	String driver = "com.mysql.jdbc.Driver"; // mysql����������
	String databaseName = "datapreprocess";
	String user = "root"; // MySQL����ʱ���û���
	String password = "123456"; // MySQL����ʱ������
	Connection conn = null;

	public ConnectionInstance() {
		if (databaseName == null | user == null | password == null) {
			System.out.println("There is no url or user or password");
		}
		try {
			Class.forName(driver); // ������������
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ databaseName, user, password); // �������ݿ�
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!"); // ��֤�Ƿ����ӳɹ�
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Can't connect mysql");
		}
	}

	public ConnectionInstance(String databaseName, String user, String password) {
		this.databaseName = databaseName;
		this.user = user;
		this.password = password;
		if (databaseName == null | user == null | password == null) {
			System.out.println("There is no url or user or password");
		}
		try {
			Class.forName(driver); // ������������
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ databaseName, user, password); // �������ݿ�
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!"); // ��֤�Ƿ����ӳɹ�
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Can't connect mysql");
		}
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
