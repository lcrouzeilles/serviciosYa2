
package com.capgemini.serviciosya.service;

import java.util.List;

import com.capgemini.serviciosya.beans.domain.Occupation;
import com.capgemini.serviciosya.beans.entity.OccupationEntity;
import com.capgemini.serviciosya.dao.IOccupationDao;

public class OccupationService {

	private IOccupationDao occupationDao;

	public OccupationService() {

		super();
	}

	public OccupationService(IOccupationDao occupationDao) {

		super();

		this.occupationDao = occupationDao;
	}

	public void setOccupationDao(IOccupationDao occupationDao) {

		this.occupationDao = occupationDao;
	}

	public List<Occupation> findAllOccupations() {

		return ((OccupationService) this.occupationDao).findAllOccupations();
	}

	public void create(OccupationEntity occupation) {

		((List<OccupationEntity>) this.occupationDao).add(occupation);
	}

	public void delete(Integer id) {

		this.occupationDao.delete(id);
	}

	public void update(OccupationEntity occupation) {
		this.occupationDao.update(occupation);
	}
}