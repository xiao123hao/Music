package dao;

import entity.Working;

import java.util.List;

public interface WorkingDao {
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
//	/**
//	 * ����id��ѯ����
//	 * @param id
//	 * @return
//	 */
//	 Working GetWorkingById(int id);
//
//	/**
//	 * ���ݱ����ѯ
//	 * @param title
//	 * @return
//	 */
//	 int GetWorkingByTitle(String title);
}
