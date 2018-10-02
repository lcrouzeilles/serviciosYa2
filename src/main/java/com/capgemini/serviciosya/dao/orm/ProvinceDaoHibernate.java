package com.capgemini.serviciosya.dao.orm;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;


import com.capgemini.serviciosya.beans.entity.ProvinceEntity;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IProvinceDao;

public class ProvinceDaoHibernate implements IProvinceDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

	private static final Logger logger = Logger.getLogger(ProvinceDaoHibernate.class);

	public void create(ProvinceEntity target) {

		// Validate the arguments.
		if (target == null) {

			logger.warn("Province object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Creating new Province %s", target));
			session.save(target);
			tx.commit();
			logger.debug(String.format("New Province %s created!", target));

		} catch (Exception e) {

			logger.error(String.format("Error creating new Province %s", target));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void update(ProvinceEntity target) {

		// Validate the arguments.
		if (target == null) {

			logger.warn("Province object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Updating Province %s", target));
			session.update(target);
			tx.commit();
			logger.debug(String.format("Province %s created!", target));

		} catch (Exception e) {

			logger.error(String.format("Error updating Province %s", target));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void delete(Integer id) {

		// Validate the arguments.
		if (id == null) {

			logger.warn("Id Province is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Deleting Province by id %s", id.toString()));
			ProvinceEntity c = (ProvinceEntity) session.get(ProvinceEntity.class, id);
			if (c != null) {

				session.delete(c);
				tx.commit();
				logger.debug(String.format("Province by id %s deleted!", id.toString()));
			} else {
				logger.warn(String.format("Province by id %s not found!", id.toString()));
			}

		} catch (Exception e) {

			logger.error(String.format("Error deleting Province id %s", id.toString()));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProvinceEntity> readAll() {

		List<ProvinceEntity> list = null;

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug("Finding all provinces...");
			list = (List<ProvinceEntity>) session.createCriteria(ProvinceEntity.class).list();

		} catch (Exception e) {

			logger.error("Error finding all provinces id");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return list;
	}

	public ProvinceEntity read(Integer id) {

		// Validate the arguments.
		if (id == null) {

			logger.warn("Id Province is null!");
			return null;
		}

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding Province by id %s", id.toString()));
			ProvinceEntity c = (ProvinceEntity) session.get(ProvinceEntity.class, id);
			if (c != null) {

				return c;
			} else {

				logger.warn(String.format("Province by id %s not found!", id.toString()));
				return null;
			}

		} catch (Exception e) {

			logger.error(String.format("Error finding Province id %s", id.toString()));
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}
}