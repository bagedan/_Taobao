package Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ForEachIteratorForPerformanceTest {
	public static void main(String[] args) {
		int loop_count = 100000;
		String test;
		List<String> list = new ArrayList<String>();
		System.out.println("消耗的时间比较：");
		for (int i = 0; i < loop_count; i++) {
			list.add(String.valueOf(i));
		}

		long start = System.nanoTime();
		for (String s : list) {
			test = s;
		}
		System.out.println("ForEach\t"+(System.nanoTime() - start));

		start = System.nanoTime();

		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			test = it.next();

		}
		System.out.println("iterator\t"+(System.nanoTime() - start));

		start = System.nanoTime();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			test = list.get(i);

		}
		System.out.println("for\t"+(System.nanoTime() - start));
	}
}
