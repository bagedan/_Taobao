package Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import DB.AttValuesCount;
import DB.ConnectionInstance;

public class AttValuesCountDBMain {
	static int Timestamp = 40;

	public static void main(String args[]) throws IOException {
		int k;
		for (k = Timestamp - 1; k >= 39; k--) {
			Connection conn = new ConnectionInstance().getConn();
			Statement statement;
			String sql = "select * from productsimiMore1 where productID % "
					+ Timestamp + "=" + k + " limit 1000"; // 要执行的SQL语句
			// \\ 反斜线(‘\’)字符; \' 单引号(‘'’) ; and att like '%\\'%'
			ResultSet rs = null;
			HashMap<String, HashMap<String, Integer>> hsHashMap = new HashMap<String, HashMap<String, Integer>>();
			AttValuesCount attValuesCount = new AttValuesCount(hsHashMap);
			try {
				statement = conn.createStatement(); // statement用来执行SQL语句
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					attValuesCount.attValuesCount(rs.getString(2), rs.getString(3));
				}
				conn.close();
				if (conn.isClosed()) {
					System.out
							.println("Succeed closing the connection of attValuesCount");
				}
				attValuesCount.storeToDB("attValuesCout" + k);

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("关闭connection异常");
				e.printStackTrace();
			}
		}
	}
}
