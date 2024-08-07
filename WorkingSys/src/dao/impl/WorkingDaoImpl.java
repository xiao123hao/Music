package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.WorkingDao;
import entity.Working;

public class WorkingDaoImpl extends BaseDao implements WorkingDao {

	@Override
	public List<Working> selectAll() {
		String sql ="select * from working";
		List<Working> workingList = new ArrayList<>();
		ResultSet rs = BaseDao.query(sql,null);
		try {
			while (rs.next()) {
				Working working=new Working();
				working.setId(rs.getInt("id"));
				working.setTitle(rs.getString("title"));
				working.setContent(rs.getString("content"));
				working.setCreateDate(rs.getTimestamp("createDate"));
				working.setType(rs.getInt("type"));
				workingList.add(working);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			BaseDao.closeAll(BaseDao.con,BaseDao.pstmt,BaseDao.rs);
		}
		return workingList;
	}

	@Override
	public Working getWorkingById(int id) {
		Working working = null;
		String sql ="select * from working where id=?";
		ResultSet rs = BaseDao.query(sql,new Object[]{id});
		try {
			while (rs.next()) {
				working=new Working();
				working.setId(rs.getInt("id"));
				working.setTitle(rs.getString("title"));
				working.setContent(rs.getString("content"));
				working.setCreateDate(rs.getTimestamp("createDate"));
				working.setType(rs.getInt("type"));
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}finally {
			BaseDao.closeAll(BaseDao.con,BaseDao.pstmt,BaseDao.rs);
		}
		return working;
	}

	@Override
	public int addWorking(Working work) {
		String sql = "insert into working (title,content,createDate,type) values (?,?,?,?);";
		int count =  BaseDao.update(sql, new Object[] {work.getTitle(),work.getContent(),work.getCreateDate(),work.getType()});
		return count;
	}

	@Override
	public int delWorking(int id) {
		String sql = "delete from working where id=?";
		int count = BaseDao.update(sql,new Object[]{id});
		return count;
	}

	@Override
	public int updateWorking(Working work) {
		String sql ="update working set title=?,content=?,type=? where id=?";
		int count = BaseDao.update(sql,new Object[]{work.getTitle(),work.getContent(),work.getType(),work.getId()});
		return count;
	}
}
