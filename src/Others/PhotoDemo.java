package Others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhotoDemo {
	Connection conn = null;

	public static void main(String[] args) {
		PhotoDemo p = new PhotoDemo();
		if (p.createConn() == -1)
			return;
		if (p.Insert() == -1)
			return;
		p.Read();
	}

	public PhotoDemo() {

	}

	public int createConn() {
		/*	sql 2000:
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		URL = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=Demo";

		sql 2005：
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		URL = "jdbc:sqlserver://localhost:1433;DatabaseName=Demo";
		如果版本不对,会提示没有要加载的驱动
		*/
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=temp";//mysql 3306
		String user = "sa";//连接的用户有没有相应的权限
		String password = "123456";
		//驱动加载
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
					.newInstance();
		} catch (Exception ex) {
			System.out.println("驱动加载失败");
			ex.printStackTrace();
			return -1;
		}
		System.out.println("驱动加载成功");
		//进行连接
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("connect fail!");
			e.printStackTrace();
			return -1;
		}
		System.out.println("Succeed link to the db");
		return 0;
	}

	public int Insert() {
		try {
			String sql = "insert into Pictures(pictureID,picture) values(?,?)";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(sql);
			File f = new File("D:\\1.jpg");
			FileInputStream input = new FileInputStream(f);
			ps.setString(1, "1");
			ps.setBinaryStream(2, input, (int) f.length());
			ps.executeUpdate();
			ps.close();
			input.close();
		} catch (Exception e) {
			System.out.println("insert image fail!");
			e.printStackTrace();
			return -1;
		}
		System.out.println("Succeed inserting image ");
		return 0;
	}

	public int Read() {
		try {
			String sql = "select picture from pictures where pictureID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "1");
			ResultSet rs = ps.executeQuery();
			byte[] b = new byte[10240 * 8];

			while (rs.next()) {
				InputStream in = rs.getBinaryStream("picture");
				in.read(b);
				File f = new File("D:\\2.jpg");
				FileOutputStream out = new FileOutputStream(f);
				out.write(b, 0, b.length);
				out.close();
			}
		} catch (Exception e) {
			System.out.println("read image fail!");
			System.out.println(e.getMessage());
			return -1;
		}
		System.out.println("Succeed read image.");
		return 0;
	}

}
