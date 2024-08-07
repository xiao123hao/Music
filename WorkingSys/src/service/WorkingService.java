package service;

import entity.Working;

import java.util.List;

public interface WorkingService {
	//添加
	int addWorking(Working work);
	//删除
	int delWorking(int id);
	//修改
	int updateWorking(Working work);
	//查询
	List<Working> selectAll();
	//
	Working getWorkingById(int id);
}
