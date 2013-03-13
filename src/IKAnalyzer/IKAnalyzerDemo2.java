package IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

import javax.security.auth.login.Configuration;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IKAnalyzerDemo2 {
	public static void main(String[] args) throws IOException {
		String str = "我早已经不是坏人了";
		cutWords(str,true);
		cutWords(str,false);
	}

	public static void cutWords(String str,boolean b) throws IOException {
		StringReader reader = new StringReader(str);
		IKSegmenter ik = new IKSegmenter(reader, b);
		//非智能分词：细粒度输出所有可能的切分结果
		//智能分词： 合并数词和量词，对分词结果进行歧义判断
		Lexeme lexeme = null;
		while ((lexeme = ik.next()) != null){
			System.out.print(lexeme.getLexemeText());
			System.out.print(lexeme.getBegin()+"*");
			System.out.print(lexeme.getEndPosition()+"*"+lexeme.getLength()+"|");
		}
			
		System.out.println();
	}
}
