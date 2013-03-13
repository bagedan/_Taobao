package DB;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

public class AttValuesCount {
	HashMap<String, HashMap<String, Integer>> attValuesCount = null;

	public AttValuesCount(HashMap<String, HashMap<String, Integer>> attValues) {
		this.attValuesCount = attValues;
	}

	public HashMap<String, HashMap<String, Integer>> getAttValuesCount() {
		return attValuesCount;
	}

	public void setAttValuesCount(HashMap<String, HashMap<String, Integer>> attValues) {
		this.attValuesCount = attValues;
	}

	public void attValuesCount(String att, String value) {
		HashMap<String, Integer> innerHs = new HashMap<String, Integer>();
		if (!attValuesCount.containsKey(att)) {
			innerHs.put(value, 1);
			attValuesCount.put(att, innerHs);
		} else {
			innerHs = attValuesCount.get(att);
			if (innerHs.containsKey(value)) {
				innerHs.put(value, innerHs.get(value) + 1);
			} else {
				innerHs.put(value, 1);
			}
		}
	}

	public void storeToDB(String tableString) throws IOException, SQLException {
		int totalCount = 0;
		Connection connWrite = new ConnectionInstance().getConn();
		Statement statement = connWrite.createStatement(); // statement用来执行SQL语句
		ResultSet rs = connWrite.getMetaData().getTables(null, null,
				tableString, null);
		if (rs.next()) //查看数据库中是否有这张表,如果有则删除
			statement.executeUpdate("drop table " + tableString);
		//新创建表
		statement.executeUpdate("create table " + tableString
				+ "(att varchar(100),value varchar(100),cout varchar(20));");
		Iterator<String> iterator = attValuesCount.keySet().iterator();

		String sqlString = "insert into " + tableString + " values('";
		while (iterator.hasNext()) {
			String att = iterator.next();
			HashMap<String, Integer> valueCount = attValuesCount.get(att);
			Iterator<String> iterator2 = valueCount.keySet().iterator();

			while (iterator2.hasNext()) {
				att = att.replaceAll("'", "");//去掉其中的"'",否则会使得字段划分错误
				String value = "";
				value = iterator2.next();
				Integer count = valueCount.get(value);
				sqlString += att + "','" + value.replaceAll("'", "") + "','"
						+ count + "');";
				//System.out.println(sqlString);
				statement.executeUpdate(sqlString);
				totalCount += count;
				sqlString = "insert into " + tableString + " values('";
			}
			sqlString += att + "','" + "allValues" + "','" + totalCount + "');";
			sqlString = "insert into " + tableString + " values('";
			totalCount = 0;
		}
		connWrite.close();
		if (connWrite.isClosed()) {
			System.out
					.println("Succeeding closing the connection of writing data to DB ");
		}
	}

	public void printToFile(BufferedWriter bufferedWriter) throws IOException {
		//BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("attValuesCout.txt"));
		int totalCount = 0;
		Iterator<String> iterator = attValuesCount.keySet().iterator();
		while (iterator.hasNext()) {
			String att = iterator.next();
			HashMap<String, Integer> valueCount = attValuesCount.get(att);
			Iterator<String> iterator2 = valueCount.keySet().iterator();

			while (iterator2.hasNext()) {
				String value = "";
				value = iterator2.next();
				Integer count = valueCount.get(value);
				bufferedWriter.write(att + "|" + value + "|" + count);
				bufferedWriter.newLine();
				totalCount += count;
			}
			bufferedWriter.write(att + "|" + "allValues" + "|" + totalCount);
			bufferedWriter.newLine();
			totalCount = 0;
		}
	}
}
