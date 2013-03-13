package Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class byteCharDiff {

	public static void main(String args[]) {

		try {
			bytePrint();
			System.out.println("----------------");
			charPrint();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void bytePrint() throws IOException {
		URL url = new URL("http://www.baidu.com");
		InputStream in = url.openStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		int n;
		StringBuffer buffer = new StringBuffer();

		while ((n = bis.read()) != -1) {
			buffer.append((char) n);
		}
		String response = new String(buffer.toString().getBytes("iso-8859-1"),
				"utf-8");
		System.out.println(response);
	}

	public static void charPrint() throws IOException {
		URL url = new URL("http://www.baidu.com");
		InputStream in = url.openStream();
		StringBuffer buffer1 = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String string;
		while ((string = reader.readLine()) != null) {
			buffer1.append(string);
		}

		System.out.println(buffer1.toString());
	}
}
