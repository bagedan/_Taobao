package Test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class encodebyteString {
	@Test
	public void encodeChineseTest() throws UnsupportedEncodingException{
		String string = "ÄãºÃ";
		
		byte[] bytes = string.getBytes("UTF-8");
		string = new String(bytes,"UTF-8");
		System.out.println(string);
		
		string = new String(bytes,"GBK");
		System.out.println(string);
		encodeEnglishTest();
	}
	
	@Test
	public void encodeEnglishTest() throws UnsupportedEncodingException{
		String string = "hello";
		
		byte[] bytes = string.getBytes("UTF-8");
		string = new String(bytes,"UTF-8");
		System.out.println(string);
		
		string = new String(bytes,"GBK");
		System.out.println(string);
	}
	
}
