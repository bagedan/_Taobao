package Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class printTest {

	public static void print1() {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap.put(Integer.toString(i), "thanks");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Iterator<String> iterator = hashmap.keySet().iterator();
		String string;
		while (iterator.hasNext()) {
			string = iterator.next();
			System.out.print(string);// �ڴˣ��������
			System.out.print("|" + hashmap.get(string) + "\t");// ֻ�������\t����println��ʱ����ܴ�ӡ����
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
		System.out.println("------------------------------------");
	}

	public static void print2() {
		HashMap<String, String> hashmap2 = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap2.put(Integer.toString(i), "thanks");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Iterator<String> iterator = hashmap2.keySet().iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "|"
					+ hashmap2.get(iterator.next()) + "\t");
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
		System.out.println("------------------------------------");
	}

	public static void print3() {
		HashMap<String, String> hashmap2 = new HashMap<String, String>();
		for (int i = 0; i < 1000; i++) {
			hashmap2.put(Integer.toString(i), "thanks");
		}
		long bs = Calendar.getInstance().getTimeInMillis();
		Iterator<String> iterator = hashmap2.keySet().iterator();
		while (iterator.hasNext()) {
			System.out.print(hashmap2.get(iterator.next()));// ֻ�������"\t"
															// "|"������println��ʱ����ܴ�ӡ����
		}
		System.out.println();
		System.out.println(Calendar.getInstance().getTimeInMillis() - bs);
		System.out.println("------------------------------------");
	}

	public static void main(String args[]) {
		print1();
		print2();
		print3();
	}
}
