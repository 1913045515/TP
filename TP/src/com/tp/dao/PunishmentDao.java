package com.tp.dao;
import java.util.List;
import com.tp.entity.Punishment;
public interface PunishmentDao {
	//惩罚记录接口
	List<Punishment>queryPunishment();
	Punishment queryPunishment(int id);
	int savePunishment(Punishment punishment);
	int deletePunishment(Punishment punishment);
	int updatePunishment(Punishment punishment);
}
