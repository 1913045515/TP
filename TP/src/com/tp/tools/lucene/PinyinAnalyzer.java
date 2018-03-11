package com.tp.tools.lucene;
import java.io.Reader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKTokenizer;
/**
 * 自定义拼音分词器
 * 
 * @author Lanxiaowei
 * 
 */
public class PinyinAnalyzer extends Analyzer {
	private int minGram;
	private int maxGram;
	private boolean useSmart;
	private boolean firstChar;
	public PinyinAnalyzer() {
		super();
		this.maxGram = Constant.DEFAULT_MAX_GRAM;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = Constant.DEFAULT_IK_USE_SMART;
	}

	public PinyinAnalyzer(boolean useSmart) {
		super();
		this.maxGram = Constant.DEFAULT_MAX_GRAM;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = useSmart;
	}

	public PinyinAnalyzer(int maxGram) {
		super();
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = Constant.DEFAULT_IK_USE_SMART;
	}

	public PinyinAnalyzer(int maxGram, boolean useSmart) {
		super();
		this.maxGram = maxGram;
		this.minGram = Constant.DEFAULT_MIN_GRAM;
		this.useSmart = useSmart;
	}

	public PinyinAnalyzer(int minGram, int maxGram, boolean useSmart) {
		super();
		this.minGram = minGram;
		this.maxGram = maxGram;
		this.useSmart = useSmart;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader arg1) {
		Tokenizer tokenizer = new IKTokenizer(arg1, useSmart);
		// 转拼音
		TokenStream tokenStream = new PinyinTokenFilter(tokenizer,
		Constant.DEFAULT_FIRST_CHAR, Constant.DEFAULT_MIN_TERM_LRNGTH);
		// 对拼音进行NGram处理
		tokenStream = new PinyinNGramTokenFilter(tokenStream, this.minGram,
				this.maxGram);
		tokenStream = new LowerCaseFilter(Version.LUCENE_45, tokenStream);
		tokenStream = new StopFilter(Version.LUCENE_45,tokenStream,
				StopAnalyzer.ENGLISH_STOP_WORDS_SET);
		return new Analyzer.TokenStreamComponents(tokenizer, tokenStream);
	}
}
