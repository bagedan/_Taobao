package Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class HashTableTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hashtable<String,Integer>  hashTable = new Hashtable<String,Integer> ();
		hashTable.put("hello", Integer.valueOf(1));
		Set<String> keys = hashTable.keySet();
	    for(String key: keys) {
	    System.out.println(key+"--"+hashTable.get(key));
	    }
	    System.out.println("HashTable过滤重复，覆盖之后");
	    
		hashTable.put("hello", hashTable.get("hello").intValue()+1);
		keys = hashTable.keySet();
	    for(String key: keys) {
	    System.out.println(key+"--"+hashTable.get(key));
	    }
	}
}
