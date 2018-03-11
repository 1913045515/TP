package com.tp.tools.lucene;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 * 拼音搜索测试
 * 
 * @author Lanxiaowei
 * 
 */
public class PinyinSearchTest {
	public static void main(String[] args) throws Exception {
		String fieldName = "content";
		String fieldTitle = "title";
		String queryString = "林";
		String queryString2 = "赵";
		Directory directory = new RAMDirectory();
		Analyzer analyzer = new PinyinAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,
				analyzer);
		IndexWriter writer = new IndexWriter(directory, config);

		/**************** 创建测试索引begin ********************/
		Document doc1 = new Document();
		doc1.add(new TextField(fieldName, "高等数学-林志强著", Store.YES));
		doc1.add(new TextField(fieldTitle, "1", Store.YES));
		writer.addDocument(doc1);

		Document doc2 = new Document();
		doc2.add(new TextField(fieldName, "高等数学-王福龙著", Store.YES));
		doc2.add(new TextField(fieldTitle, "2", Store.YES));
		writer.addDocument(doc2);

		Document doc3 = new Document();
		doc3.add(new TextField(fieldName, "高等数学-赵仝著", Store.YES));
		doc3.add(new TextField(fieldTitle, "3", Store.YES));
		writer.addDocument(doc3);

		Document doc4 = new Document();
		doc4.add(new TextField(fieldName, "高等数学-周炼著", Store.YES));
		doc4.add(new TextField(fieldTitle, "4", Store.YES));
		writer.addDocument(doc4);

		Document doc5 = new Document();
		doc5.add(new TextField(fieldName, "高等数学-张翔著", Store.YES));
		doc5.add(new TextField(fieldTitle, "5", Store.YES));
		writer.addDocument(doc5);

		Document doc6 = new Document();
		doc6.add(new TextField(fieldName, "高等数学-刘双喜著", Store.YES));
		doc6.add(new TextField(fieldTitle, "6", Store.YES));
		writer.addDocument(doc6);

		// 强制合并为1个段
		writer.forceMerge(1);
		writer.commit();
		writer.close();
		/**************** 创建测试索引end ********************/

		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		BooleanQuery booleanQuery = new BooleanQuery();   
		Query query = new TermQuery(new Term(fieldName, queryString));
		Query query2 = new TermQuery(new Term(fieldName, queryString2));
		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
//		 String[] queries = { queryString,queryString2};        
//		 String[] fields = {fieldName,fieldName};       
//		 BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };  
//		 Query query = MultiFieldQueryParser.parse(Version.LUCENE_45, queries, fields, clauses,analyzer);
		TopDocs topDocs = searcher.search(booleanQuery, Integer.MAX_VALUE);
		ScoreDoc[] docs = topDocs.scoreDocs;
		if (null == docs || docs.length <= 0) {
			System.out.println("No results.");
			return;
		}

		// 打印查询结果
		System.out.println("ID[Score]\tcontent");
		List<Map<String,Object>>listResult=new ArrayList<Map<String,Object>>();
		for (int i=docs.length-1;i>=0;i--) {
			Map<String,Object>map=new HashMap<String, Object>();
			int docID = docs[i].doc;
			Document document = searcher.doc(docID);
			String content = document.get(fieldName);
			String title = document.get(fieldTitle);
			map.put("content", content);
			map.put("title", title);
			listResult.add(map);
			float score = docs[i].score;
			System.out.println(docID + "[" + score + "]\t" + content);
		}
	}
}
