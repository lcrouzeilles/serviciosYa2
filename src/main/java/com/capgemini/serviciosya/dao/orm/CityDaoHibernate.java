package com.capgemini.serviciosya.dao.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.capgemini.serviciosya.beans.entity.CityEntity;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.ICityDao;

public class CityDaoHibernate implements ICityDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
	private static final Logger logger = Logger.getLogger(CityDaoHibernate.class);

	public void create(CityEntity object) {
		if (object == null) {
			logger.warn("Object does not contain any value");
			return;
		}
		Session session = null;
		Transaction tx = null;

		try {
			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();
			logger.debug(String.format("Creating new City %s", object));
			session.save(object);
			tx.commit();
			logger.debug(String.format("New City %s created!", object));
		} catch (Exception e) {
			logger.error(String.format("Error creating new City %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}

	}

	public CityEntity read(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id City is null!");
			return null;
		}

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding City by id %s", p_key.toString()));
			CityEntity c = (CityEntity) session.get(CityEntity.class, p_key);
			if (c != null) {

				return c;
			} else {

				logger.warn(String.format("City by id %s not found!", p_key.toString()));
				return null;
			}

		} catch (Exception e) {

			logger.error(String.format("Error finding City id %s", p_key.toString()));
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void update(CityEntity object) {

		// Validate the arguments.
		if (object == null) {

			logger.warn("City object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Updating City %s", object));
			session.update(object);
			tx.commit();
			logger.debug(String.format("City %s created!", object));

		} catch (Exception e) {

			logger.error(String.format("Error updating City %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void delete(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id City is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Deleting City by id %s", p_key.toString()));
			CityEntity c = (CityEntity) session.get(CityEntity.class, p_key);
			if (c != null) {

				session.delete(c);
				tx.commit();
				logger.debug(String.format("City by id %s deleted!", p_key.toString()));
			} else {
				logger.warn(String.format("City by id %s not found!", p_key.toString()));
			}

		} catch (Exception e) {

			logger.error(String.format("Error deleting City id %s", p_key.toString()));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<CityEntity> readAll() {


		List<CityEntity> list = null;

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug("Finding all Providers...");
			list = (List<CityEntity>) session.createCriteria(CityEntity.class).list();

		} catch (Exception e) {

			logger.error("Error finding all Providers id");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return list;
	}

}
