package com.tp.tools;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;
public class IKAnalyzerSearch {
	@SuppressWarnings("resource")
	public static List<String> iKAnalyzerSearch(String queryString) throws IOException {
		List<String> list = new ArrayList<String>();
		// 基于Lucene实现
		Analyzer analyzer = new IKAnalyzer(true);// true智能切分
		StringReader reader = new StringReader(queryString);
		TokenStream ts = analyzer.tokenStream("", reader);
		CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
		while (ts.incrementToken()) {
			System.out.println(term.toString());
			list.add(term.toString());
		}
		reader.close();
		return list;
	}
	public static void main(String[] args) throws Exception {
		IKAnalyzerSearch.iKAnalyzerSearch("IKAnalyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。"
				+ "从2006年12月推出1.0版本开始，IKAnalyzer已经推出了3个大版本。");
	}
}
