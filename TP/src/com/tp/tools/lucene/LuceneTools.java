package com.tp.tools.lucene;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
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
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.hibernate.result.internal.OutputsImpl;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.tp.entity.Commodity;
import com.tp.entity.Commoditypath;
import com.tp.tools.SaveLucene;
public class LuceneTools implements SaveLucene {
	private Directory directory = null;
	private Analyzer analyzer = null;
	private IndexWriterConfig config = null;
	private IndexWriter writer = null;
	private  SpellChecker spellchecker=null;
	public LuceneTools() throws IOException {
		directory = new RAMDirectory();
		analyzer = new PinyinAnalyzer();
		config = new IndexWriterConfig(Version.LUCENE_45, analyzer);
		writer = new IndexWriter(directory, config);
	}
	@SuppressWarnings("null")
	@Override
	public List<Object> queryCommodityDao(List<String> queryString) throws Exception {
		List<Object> Result = new ArrayList<Object>();
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		BooleanQuery booleanQuery = new BooleanQuery();
		for(int i=0;i<queryString.size();i++){
			Query query = new TermQuery(new Term("content", queryString.get(i)));
			booleanQuery.add(query, BooleanClause.Occur.MUST);
		}
		TopDocs topDocs = searcher.search(booleanQuery, Integer.MAX_VALUE);
		ScoreDoc[] docs = topDocs.scoreDocs;
		for (int i=docs.length-1;i>=0;i--) {
			Map<String,Object>map=new HashMap<String, Object>();
			int docID = docs[i].doc;
			Document document = searcher.doc(docID);
			map.put("id",document.get("id"));
			map.put("name",document.get("name"));
			map.put("originalPrice",document.get("originalPrice"));
			map.put("price",document.get("price"));
			map.put("number",document.get("number"));
			map.put("degree",document.get("degree"));
			map.put("describe",document.get("describe"));
			map.put("usersId",document.get("usersId"));
			//map.put("type",document.get("type"));
			map.put("editTime",document.get("editTime"));
			map.put("releaseTime",document.get("releaseTime")); 
			map.put("btId",document.get("btId")); 
			List<String>pathList=new ArrayList<String>();
			String[] str=document.get("path").split(",");
			for(String string:str){
				pathList.add(string);
			}
			if("1".equals(document.get("shelfState"))){
				map.put("shelfState","已下架");
			}else{
				map.put("shelfState","已上架");
			}	
			map.put("path",pathList);
			map.put("state",document.get("state"));
			Result.add(map);
		}
		if(Result.size()==0){
			StringBuffer buffer=new StringBuffer();
			for(int i=0;i<queryString.size();i++){
				buffer.append(queryString.get(i));
			}	
			List<String>list=new ArrayList<String>();
			list=querySpellCheck(buffer.toString());
			if(list.size()!=0){
				Result.add(queryCommodityDao(list));
			}else{
				Result.add("无该物品，请重新搜索");
			}		
		}
		return Result;
	}

	@Override
	public int saveInfo(List<Commodity> list)
			throws IOException {
		for (int i = 0; i < list.size(); i++) {
			Document doc = new Document();
			doc.add(new TextField("id", list.get(i).getId()+"", Store.YES));
			doc.add(new TextField("name", list.get(i).getName()+"", Store.YES));
			doc.add(new TextField("originalPrice", list.get(i).getOriginalPrice()+"", Store.YES));
			doc.add(new TextField("price", list.get(i).getPrice()+"", Store.YES));
			doc.add(new TextField("number", list.get(i).getNumber()+"", Store.YES));
			doc.add(new TextField("degree", list.get(i).getDegree()+"", Store.YES));
			doc.add(new TextField("describe", list.get(i).getDescribes()+"", Store.YES));
			doc.add(new TextField("usersId", list.get(i).getUsers().getId()+"", Store.YES));
			//doc.add(new TextField("type", list.get(i).getClassification().getName()+"", Store.YES));
			doc.add(new TextField("editTime",list.get(i).getEditTimes()+"", Store.YES));
			doc.add(new TextField("releaseTime", list.get(i).getReleaseTimes()+"", Store.YES));
			doc.add(new TextField("btId", list.get(i).getId()+"", Store.YES));
			doc.add(new TextField("shelfState", list.get(i).getShelfState()+"", Store.YES));
			Set<Commoditypath>set=list.get(i).getCommoditypaths();
			Iterator<Commoditypath>it=set.iterator();
			String path="";
			while(it.hasNext()){
				path=path+it.next().getPath()+",";
			}	
			doc.add(new TextField("path",path+"", Store.YES));
			doc.add(new TextField("type", list.get(i).getClassification().getName()+"", Store.YES));
			doc.add(new TextField("state", list.get(i).getShelfState()+"", Store.YES));
			doc.add(new TextField("content", list.get(i).toString(), Store.YES));
			writer.addDocument(doc);
		}
		try {
			writer.forceMerge(1);
			writer.commit();	
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}finally{
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public List<String> querySpellCheck(String queryString) throws Exception {
		List<String>list=new ArrayList<String>();
		spellchecker = new SpellChecker(directory);
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_45,
				new IKAnalyzer());
		spellchecker.indexDictionary(new PlainTextDictionary(new File(
				Constant.dicpath)), config, false);
		spellchecker.setAccuracy(0.4f);
		String[] suggests = spellchecker.suggestSimilar(queryString, 10);
		for(int i=0;i<suggests.length;i++){
			list.add(suggests[i]);
		}
		return list;
	}
	@Override
	public int deleteInfo(int id){
		try {
			writer.deleteDocuments(new Term("id",id+""));
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	@Override
	public int updateInfo(Commodity commodity,int id) {
		try {
			Document doc = new Document();
			doc.add(new TextField("id", commodity.getId()+"", Store.YES));
			doc.add(new TextField("name", commodity.getName()+"", Store.YES));
			doc.add(new TextField("originalPrice", commodity.getOriginalPrice()+"", Store.YES));
			doc.add(new TextField("price", commodity.getPrice()+"", Store.YES));
			doc.add(new TextField("number", commodity.getNumber()+"", Store.YES));
			doc.add(new TextField("degree", commodity.getDegree()+"", Store.YES));
			doc.add(new TextField("describe", commodity.getDescribes()+"", Store.YES));
			doc.add(new TextField("shelfState",commodity.getShelfState()+"", Store.YES));
			//doc.add(new TextField("type", commodity.getClassification().getName()+"", Store.YES));
			doc.add(new TextField("editTime", commodity.getEditTimes()+"", Store.YES));
			doc.add(new TextField("releaseTime", commodity.getReleaseTimes()+"", Store.YES));
			doc.add(new TextField("btId", commodity.getId()+"", Store.YES));
			doc.add(new TextField("usersId", commodity.getUsers().getId()+"", Store.YES));
			Set<Commoditypath>set=commodity.getCommoditypaths();
			Iterator<Commoditypath>it=set.iterator();
			String path="";
			while(it.hasNext()){
				path=path+it.next().getPath()+",";
			}	
			doc.add(new TextField("path",path+"", Store.YES));
			doc.add(new TextField("type", commodity.getClassification().getName()+"", Store.YES));
			doc.add(new TextField("state", commodity.getShelfState()+"", Store.YES));
			doc.add(new TextField("content", commodity.toString(), Store.YES));
			writer.updateDocument(new Term("id",id+""),doc);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
