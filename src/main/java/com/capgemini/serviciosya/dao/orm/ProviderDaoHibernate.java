
package com.capgemini.serviciosya.dao.orm;

import com.capgemini.serviciosya.beans.entity.ProviderEntity;

import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IProviderDao;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.util.List;


@SuppressWarnings("unused")
public class ProviderDaoHibernate implements IProviderDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

	private static final Logger logger = Logger.getLogger(ProviderDaoHibernate.class);

	public void create(ProviderEntity object) {
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
			logger.debug(String.format("Creating new Provider %s", object));
			session.save(object);
			tx.commit();
			logger.debug(String.format("New Provider %s created!", object));
		} catch (Exception e) {
			logger.error(String.format("Error creating new Provider %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);
		} finally {
			session.close();
		}

	}

	// Spring
	/*
	 * public void create(ProviderEntity target) {
	 * 
	 * // Validate the arguments. if (target == null) {
	 * 
	 * logger.warn("Provider object is null!"); return; }
	 * 
	 * ValidatorFactory validatorFactory =
	 * Validation.buildDefaultValidatorFactory(); Validator validator =
	 * validatorFactory.getValidator();
	 * 
	 * Set<ConstraintViolation<ProviderEntity>> validationErrors =
	 * validator.validate(target);
	 * 
	 * if (!validationErrors.isEmpty()) { for (ConstraintViolation<ProviderEntity>
	 * error : validationErrors) { System.out.println( error.getMessageTemplate() +
	 * "::" + error.getPropertyPath() + "::" + error.getMessage());
	 * 
	 * } }
	 * 
	 * Session session = null; Transaction tx = null; try {
	 * logger.debug("Getting hibernate session..."); session =
	 * this.sessionFactory.openSession(); tx = session.beginTransaction();
	 * 
	 * logger.debug(String.format("Creating new Provider %s", target));
	 * session.save(target); tx.commit();
	 * logger.debug(String.format("New Provider %s created!", target));
	 * 
	 * } catch (Exception e) {
	 * 
	 * logger.error(String.format("Error creating new Provider %s", target));
	 * tx.rollback(); throw new DaoException(e.getMessage(), e);
	 * 
	 * } finally {
	 * 
	 * session.close(); } }
	 */

	public ProviderEntity read(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id Provider is null!");
			return null;
		}

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding Provider by id %s", p_key.toString()));
			ProviderEntity c = (ProviderEntity) session.get(ProviderEntity.class, p_key);
			if (c != null) {

				return c;
			} else {

				logger.warn(String.format("Provider by id %s not found!", p_key.toString()));
				return null;
			}

		} catch (Exception e) {

			logger.error(String.format("Error finding Provider id %s", p_key.toString()));
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void update(ProviderEntity target) {

		// Validate the arguments.
		if (target == null) {

			logger.warn("Provider object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Updating Provider %s", target));
			session.update(target);
			tx.commit();
			logger.debug(String.format("Provider %s created!", target));

		} catch (Exception e) {

			logger.error(String.format("Error updating Provider %s", target));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void delete(Integer id) {

		// Validate the arguments.
		if (id == null) {

			logger.warn("Id Provider is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Deleting Provider by id %s", id.toString()));
			ProviderEntity c = (ProviderEntity) session.get(ProviderEntity.class, id);
			if (c != null) {

				session.delete(c);
				tx.commit();
				logger.debug(String.format("Provider by id %s deleted!", id.toString()));
			} else {
				logger.warn(String.format("Provider by id %s not found!", id.toString()));
			}

		} catch (Exception e) {

			logger.error(String.format("Error deleting Provider id %s", id.toString()));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProviderEntity> readAll() {

		List<ProviderEntity> list = null;

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug("Finding all Providers...");
			list = (List<ProviderEntity>) session.createCriteria(ProviderEntity.class).list();

		} catch (Exception e) {

			logger.error("Error finding all Providers id");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return list;
	}

	public ProviderEntity findById(Integer id) {

		// Validate the arguments.
		if (id == null) {

			logger.warn("Id Provider is null!");
			return null;
		}

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding Provider by id %s", id.toString()));
			ProviderEntity c = (ProviderEntity) session.get(ProviderEntity.class, id);
			if (c != null) {

				return c;
			} else {

				logger.warn(String.format("Provider by id %s not found!", id.toString()));
				return null;
			}

		} catch (Exception e) {

			logger.error(String.format("Error finding Provider id %s", id.toString()));
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public ProviderEntity findByEmail(String email) {

		if (isEmpty(email)) {

			logger.warn("Email argument is empty!");
			return null;
		}

		ProviderEntity provider;
		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding provider by emai %s", email));
			provider = (ProviderEntity) session.createQuery("From Provider p where p.email = '" + email + "'")
					.uniqueResult();

		} catch (Exception e) {

			logger.error("Error finding a provider by email");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return provider;
	}

	public ProviderEntity findByDNI(Integer dni) {

		if (dni == null) {

			logger.warn("DNI argument is empty!");
			return null;
		}

		ProviderEntity provider;
		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding provider by dni %s", dni.toString()));
			Criteria criteria = session.createCriteria(ProviderEntity.class);
			criteria.add(Restrictions.eq("dni", dni));

			provider = (ProviderEntity) criteria.uniqueResult();

		} catch (Exception e) {

			logger.error("Error finding a provider by dni");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return provider;
	}

	public ProviderEntity findByPhone(String phone) {

		if (isEmpty(phone)) {

			logger.warn("Phone argument is empty!");
			return null;
		}

		ProviderEntity provider;
		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug(String.format("Finding provider by phone %s", phone));
			provider = (ProviderEntity) session.getNamedQuery("ProviderFindByPhone").setString("phone", phone)
					.uniqueResult();

		} catch (Exception e) {

			logger.error("Error finding a provider by dni");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return provider;
	}

}