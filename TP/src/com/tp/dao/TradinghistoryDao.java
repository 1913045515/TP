package com.tp.dao;
import java.util.List;
import com.tp.entity.Tradinghistory;
public interface TradinghistoryDao {
	//交易历史接口
	List<Tradinghistory>queryTradinghistory();
	Tradinghistory queryTradinghistory(int id);
	int saveTradinghistory(Tradinghistory tradinghistory);
	int deleteTradinghistory(Tradinghistory tradinghistory);
	int updateTradinghistory(Tradinghistory tradinghistory);
}
