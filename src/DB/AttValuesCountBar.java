package DB;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;


import Main.attValuesCountDBBarMain;
import Product.Count;

public class AttValuesCountBar {
	HashMap<String, HashMap<String, Integer>> attValuesCount = null;

	public AttValuesCountBar(HashMap<String, HashMap<String, Integer>> attValues) {
		this.attValuesCount = attValues;
	}

	public HashMap<String, HashMap<String, Integer>> getAttValuesCount() {
		return attValuesCount;
	}

	public void setAttValuesCount(
			HashMap<String, HashMap<String, Integer>> attValues) {
		this.attValuesCount = attValues;
	}

	public void attValuescount(String att, String value) {
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
		if (rs.next()) {
			statement.executeUpdate("drop table " + tableString);
			statement
					.executeUpdate("create table "
							+ tableString
							+ "(att varchar(100),value varchar(100),cout varchar(20));");
			Iterator<String> iterator = attValuesCount.keySet().iterator();
			String sqlString = "insert into " + tableString + " values('";

			while (iterator.hasNext()) {
				String att = iterator.next();
				HashMap<String, Integer> valueCount = attValuesCount.get(att);
				Iterator<String> iterator2 = valueCount.keySet().iterator();

				while (iterator2.hasNext()) {
					att = att.replaceAll("'", "");
					String value = "";
					value = iterator2.next();
					Integer count = valueCount.get(value);
					sqlString += att + "','" + value.replaceAll("'", "")
							+ "','" + count + "');";
					System.out.println(sqlString);
					statement.executeUpdate(sqlString);
					totalCount += count;
					sqlString = "insert into " + tableString + " values('";
					Count.COUNT++;
					attValuesCountDBBarMain.jpb.setValue(Count.COUNT);
				}
				sqlString += att + "','" + "allValues" + "','" + totalCount
						+ "');";
				Count.COUNT++;
				statement.executeUpdate(sqlString);
				sqlString = "insert into " + tableString + " values('";
				totalCount = 0;
			}
			Count.COUNT = attValuesCountDBBarMain.rowCount * 2;//最后显示完成100%
			statement.executeUpdate(sqlString);
			connWrite.close();
			if (connWrite.isClosed())
				System.out
						.println("Succeeding closing the connection of writing data to DB ");
		}
	}

	public void printToFile(BufferedWriter bufferedWriter) throws IOException {
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
