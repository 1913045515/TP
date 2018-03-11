package com.tp.dao;
import java.util.List;
import com.tp.entity.Change;
public interface ChangeDao {
	//更改痕迹接口
	List<Change>queryChange();
	Change queryChange(int id);
	int saveChange(Change change);
	int deleteChange(Change change);
	int updateChange(Change change);
}
