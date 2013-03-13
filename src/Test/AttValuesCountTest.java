package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class AttValuesCountTest {
	public static void main(String args[]) throws IOException {
		HashMap<String, HashMap<String, Integer>> attValues = new HashMap<String, HashMap<String, Integer>>();
		File file = new File("product.txt");
		File file2 = new File("productAttCount.txt");
		FileReader fileReader = new FileReader(file);
		FileWriter fileWriter = new FileWriter(file2);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		String row = "";
		while ((row = bufferedReader.readLine()) != null) {
			String[] strings = row.split("\\|");
			//System.out.println(strings[0] + "|" + strings[1]);
			AttValuesCount(attValues, strings[0], strings[1]);
		}
		print(attValues, bufferedWriter);
		
		bufferedReader.close();
		bufferedWriter.close();
	}

	public static void AttValuesCount(
			HashMap<String, HashMap<String, Integer>> attValues, String att,
			String value) {
		if (!attValues.containsKey(att)) {
			HashMap<String, Integer> innerHs = new HashMap<String, Integer>();
			innerHs.put(value, 1);
			attValues.put(att, innerHs);
		} else {
			HashMap<String, Integer> innerHs = new HashMap<String, Integer>();
			innerHs = attValues.get(att);
			if (innerHs.containsKey(value)) {
				innerHs.put(value, innerHs.get(value) + 1);
			} else {
				innerHs.put(value, 1);
			}
		}
	}

	public static void print(
			HashMap<String, HashMap<String, Integer>> attValues,
			BufferedWriter bufferedWriter) throws IOException {
		int totalCount = 0;
		
		Iterator<String> iterator = attValues.keySet().iterator();
		
		System.out.println("att"+"\t"+"value"+"|"+"count");
		
		while (iterator.hasNext()) {
			String att = iterator.next();
			System.out.println(att);
			
			HashMap<String, Integer> valueCount = attValues.get(att);
			Iterator<String> iterator2 = valueCount.keySet().iterator();
			
			while (iterator2.hasNext()) {
				String value = "";
				value = iterator2.next();
				Integer count = valueCount.get(value);
				
				System.out.println("\t" + value + "|" + count);
				
				bufferedWriter.write(att+"|" + value + "|" + count);
				bufferedWriter.newLine();
				
				totalCount+=count;
			}
			bufferedWriter.write(att+"|" + "allValues" + "|" + totalCount);
			bufferedWriter.newLine();
			
			System.out.println("totalCount"+"\t"+totalCount);
			
			System.out.println("---------------------------");
			totalCount=0;
		}
	}
}
