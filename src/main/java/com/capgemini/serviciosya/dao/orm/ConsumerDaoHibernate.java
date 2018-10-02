package com.capgemini.serviciosya.dao.orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.log4j.Logger;

import com.capgemini.serviciosya.beans.entity.ConsumerEntity;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IConsumerDao;

public class ConsumerDaoHibernate implements IConsumerDao {

	private SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(ConsumerDaoHibernate.class);

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(ConsumerEntity object) {

		// Validate the arguments.
		if (object == null) {

			logger.warn("Country object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Creating new Consumer %s", object));
			session.save(object);
			tx.commit();
			logger.debug(String.format("New Consumer %s created!", object));

		} catch (Exception e) {

			logger.error(String.format("Error creating new Consumer %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public ConsumerEntity read(Integer p_key) {

        // Validate the arguments.
        if (p_key == null) {

            logger.warn ("Id Consumer is null!");
            return null;
        }

        Session session = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();

            logger.debug (String.format ("Finding Consumer by id %s", p_key.toString ()));
            ConsumerEntity c = (ConsumerEntity) session.get (ConsumerEntity.class, p_key);
            if (c != null) {

                return c;
            } else {

               logger.warn (String.format ("Consumer by id %s not found!", p_key.toString ()));
               return null;
            }

        } catch (Exception e) {

            logger.error (String.format ("Error finding Consumer id %s", p_key.toString ()));
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }
	}

	public void update(ConsumerEntity object) {

		// Validate the arguments.
		if (object == null) {

			logger.warn("Consumer object is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Updating Consumer %s", object));
			session.update(object);
			tx.commit();
			logger.debug(String.format("Consumer %s updated!", object));

		} catch (Exception e) {

			logger.error(String.format("Error updating Consumer %s", object));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	public void delete(Integer p_key) {

		// Validate the arguments.
		if (p_key == null) {

			logger.warn("Id Consumer is null!");
			return;
		}

		Session session = null;
		Transaction tx = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();
			tx = session.beginTransaction();

			logger.debug(String.format("Deleting Consumer by id %s", p_key.toString()));
			ConsumerEntity c = (ConsumerEntity) session.get(ConsumerEntity.class, p_key);
			if (c != null) {

				session.delete(c);
				tx.commit();
				logger.debug(String.format("Consumer by id %s deleted!", p_key.toString()));
			} else {
				logger.warn(String.format("Consumer by id %s not found!", p_key.toString()));
			}

		} catch (Exception e) {

			logger.error(String.format("Error deleting Consumer id %s", p_key.toString()));
			tx.rollback();
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConsumerEntity> readAll() {

		List<ConsumerEntity> list = null;

		Session session = null;
		try {

			logger.debug("Getting hibernate session...");
			session = this.sessionFactory.openSession();

			logger.debug("Finding all Consumers...");
			list = (List<ConsumerEntity>) session.createCriteria(ConsumerEntity.class).list();

		} catch (Exception e) {

			logger.error("Error finding all Consumers id");
			throw new DaoException(e.getMessage(), e);

		} finally {

			session.close();
		}

		return list;
	}

}
