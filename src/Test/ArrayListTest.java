package Test;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
	public static void main(String args[]) {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("hello");
		arrayList.add("hello");

		for (Iterator<String> it = arrayList.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
		System.out.println();
	}
}
