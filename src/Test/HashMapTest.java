package Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> hsHashMap = new HashMap<String, Integer>();
		hsHashMap.put("hello", Integer.valueOf(1));
		Set<String> keys = hsHashMap.keySet();
		for (String key : keys) {
			System.out.println(key + "--" + hsHashMap.get(key));
		}
		System.out.println("HashMap�����ظ�������֮��");
		hsHashMap.put("hello", hsHashMap.get("hello") + 1);// ���԰�Integer��int�������������ǲ���++
		keys = hsHashMap.keySet();
		for (String key : keys) {
			System.out.println(key + "--" + hsHashMap.get(key));
		}
	}

}
