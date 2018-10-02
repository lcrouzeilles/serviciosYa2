package com.capgemini.serviciosya.dao.orm;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.capgemini.serviciosya.beans.entity.OccupationEntity;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IOccupationDao;

public class OccupationDaoHibernate implements IOccupationDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
	private static final Logger logger = Logger.getLogger(OccupationDaoHibernate.class);

	public void create(OccupationEntity object) {
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
			logger.debug(String.format("Creating new Occupation %s", object));
			session.save(object);
			tx.commit();
			logger.debug(String.format("New Occupation %s created!", object));
		} catch (Exception e) {
			logger.error(String.format("Error creating new Occupation %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}

	}

	public OccupationEntity read(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id Occupation is null!");
			return null;
		}

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding Occupation by id %s", p_key.toString()));
			OccupationEntity c = (OccupationEntity) session.get(OccupationEntity.class, p_key);
			if (c != null) {

				return c;
			} else {

				logger.warn(String.format("Occupation by id %s not found!", p_key.toString()));
				return null;
			}

		} catch (Exception e) {

			logger.error(String.format("Error finding Occupation id %s", p_key.toString()));
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void update(OccupationEntity object) {

		// Validate the arguments.
		if (object == null) {

			logger.warn("Occupation object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Updating Occupation %s", object));
			session.update(object);
			tx.commit();
			logger.debug(String.format("Occupation %s created!", object));

		} catch (Exception e) {

			logger.error(String.format("Error updating Occupation %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void delete(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id Occupation is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Deleting Occupation by id %s", p_key.toString()));
			OccupationEntity c = (OccupationEntity) session.get(OccupationEntity.class, p_key);
			if (c != null) {

				session.delete(c);
				tx.commit();
				logger.debug(String.format("Occupation by id %s deleted!", p_key.toString()));
			} else {
				logger.warn(String.format("Occupation by id %s not found!", p_key.toString()));
			}

		} catch (Exception e) {

			logger.error(String.format("Error deleting Occupation id %s", p_key.toString()));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OccupationEntity> readAll() {


		List<OccupationEntity> list = null;

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug("Finding all Occupations...");
			list = (List<OccupationEntity>) session.createCriteria(OccupationEntity.class).list();

		} catch (Exception e) {

			logger.error("Error finding all Occupations id");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return list;
	}

}
