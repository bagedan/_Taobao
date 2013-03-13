package Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class HashMapTraversePerformance {
	public static void main(String args[]) {
		System.out
				.println("---------------------顺序会影响到执行时间----------------------");

		System.out
				.println("-----------------------entrySet()---------------------");
		listHashMap_entrySet();
		System.out
				.println("---------------listHashMap_keySet_iterator---------------------");
		listHashMap_keySet_iterator();
		System.out
				.println("------------------listHashMap_keySet_for---------------------");
		listHashMap_keySet_for();
		System.out
				.println("-----------------------entrySet()---------------------");
		listHashMap_entrySet();
	}

	public static void listHashMap_keySet_iterator() {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap.put(Integer.toString(i), "thanks");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Iterator<String> iterator = hashmap.keySet().iterator();
		String string;
		while (iterator.hasNext()) {
			string = iterator.next();
			System.out.print(string);//  
			System.out.print("|" + hashmap.get(string) + "\t");// 
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
	}

	public static void listHashMap_keySet_for() {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap.put(Integer.toString(i), "thanas");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Set<String> keys = hashmap.keySet();
		for (String key : keys) {
			System.out.print(key + "|" + hashmap.get(key) + "\t");
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
	}

	public static void listHashMap_entrySet() {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap.put(Integer.toString(i), "thancs");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Iterator<Entry<String, String>> iter = hashmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			String val = (String) entry.getValue();
			System.out.print(key + "|" + val + "\t");
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
	}
}
