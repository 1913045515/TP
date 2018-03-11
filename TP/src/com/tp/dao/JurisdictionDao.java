package com.tp.dao;
import java.util.List;
import com.tp.entity.Jurisdiction;
public interface JurisdictionDao {
	//权限接口
	List<Jurisdiction>queryJurisdiction();
	Jurisdiction queryJurisdiction(int id);
	int saveJurisdiction(Jurisdiction jurisdiction);
	int deleteJurisdiction(Jurisdiction jurisdiction);
	int updateJurisdiction(Jurisdiction jurisdiction);
}
