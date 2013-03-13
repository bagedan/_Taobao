package Test;

import java.util.HashMap;

import algorithm.SimilarityUtils;

public class SimilarityUtilsTest {
	public static void main(String[] args) {
		String src = "hello world!";
		String tar = "hello";
		System.out.println("edit distance:" + SimilarityUtils.ld(src, tar));
		System.out.println("sim=" + SimilarityUtils.similarity(src, tar));
		HashMap<String, String> hm = new HashMap<String,String>();
		
	}
}
