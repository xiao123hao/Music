package service.impl;

import java.util.List;

import entity.Working;
import service.WorkingService;
import dao.WorkingDao;
import dao.impl.WorkingDaoImpl;

public class WorkingServiceImpl implements WorkingService {
	WorkingDao workingDao = new WorkingDaoImpl();
	@Override
	public int addWorking(Working work) {
		return workingDao.addWorking(work);
	}

	@Override
	public int delWorking(int id) {
		return workingDao.delWorking(id);
	}

	@Override
	public int updateWorking(Working work) {
		return workingDao.updateWorking(work);
	}

	@Override
	public List<Working> selectAll() {
		return workingDao.selectAll();
	}

	@Override
	public Working getWorkingById(int id) {
		return workingDao.getWorkingById(id);
	}
}
