package com.tp.tools.lucene;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * 拼写检查测试
 * 
 * @author Lanxiaowei
 * 
 */
public class SpellCheck {
	private Directory directory = new RAMDirectory();  
	private Document document;
	private IndexWriter indexWriter;
	/** 拼写检查器 */
	private SpellChecker spellchecker;
	// private IndexReader indexReader;
	// private IndexSearcher indexSearcher;

	/**
	 * 创建测试索引
	 * 
	 * @param content
	 * @throws IOException
	 */
	public void createIndex(String content) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,new IKAnalyzer());  
        indexWriter = new IndexWriter(directory, config);  
        document = new Document();  
        document.add(new TextField("content", content,Store.YES)); 
		try {
			indexWriter.addDocument(document);
			indexWriter.commit();
			indexWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void search(String word, int numSug) {
		//directory = new RAMDirectory();
		try {
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,
					new IKAnalyzer());
			spellchecker = new SpellChecker(directory);
			// 初始化字典目录
			// 最后一个fullMerge参数表示拼写检查索引是否需要全部合并
			spellchecker.indexDictionary(new PlainTextDictionary(new File(
					Constant.dicpath)), config, true);
			spellchecker.setAccuracy(0.8f);
			// 这里的参数numSug表示返回的建议个数
			String[] suggests = spellchecker.suggestSimilar(word, numSug);
			if (suggests != null && suggests.length > 0) {
				for (String suggest : suggests) {
					System.out.println("您是不是想要找：" + suggest);
				}
			} else {
				System.out.println("suggests.length=" + suggests.length);
				System.out.println("this is null");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		SpellCheck spellCheckTest = new SpellCheck();
		spellCheckTest.createIndex("屌丝男士 不是传统意义上的情景喜剧，有固定时长和单一场景，以及简单的生活细节。");
		String word = "java开发工程事";
		spellCheckTest.search(word, 10);
	}
}
