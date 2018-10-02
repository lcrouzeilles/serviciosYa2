
package com.capgemini.serviciosya.dao.orm;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.capgemini.serviciosya.beans.entity.CountryEntity;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.jdbc.ICountryDao;


public class CountryDaoHibernate implements ICountryDao {


    private SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory ();


    private static final Logger logger= Logger.getLogger (CountryDaoHibernate.class);


    public void create (CountryEntity target) {

        // Validate the arguments.
        if (target == null) {

            logger.warn ("Country object is null!");
            return;
        }

        Session session = null;
        Transaction tx = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();
            tx = session.beginTransaction ();

            logger.debug (String.format ("Creating new country %s", target));
            session.save (target);
            tx.commit ();
            logger.debug (String.format ("New country %s created!", target));

        } catch (Exception e) {

            logger.error (String.format ("Error creating new country %s", target));
            tx.rollback ();
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }
    }

    
    public void update (CountryEntity target) {

        // Validate the arguments.
        if (target == null) {

            logger.warn ("Country object is null!");
            return;
        }

        Session session = null;
        Transaction tx = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();
            tx = session.beginTransaction ();

            logger.debug (String.format ("Updating country %s", target));
            session.update (target);
            tx.commit ();
            logger.debug (String.format ("Country %s created!", target));

        } catch (Exception e) {

            logger.error (String.format ("Error updating country %s", target));
            tx.rollback ();
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }
    }

    
    public void delete (Integer id) {

        // Validate the arguments.
        if (id == null) {

            logger.warn ("Id country is null!");
            return;
        }

        Session session = null;
        Transaction tx = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();
            tx = session.beginTransaction ();

            logger.debug (String.format ("Deleting country by id %s", id.toString ()));
            CountryEntity c = (CountryEntity) session.get (CountryEntity.class, id);
            if (c != null) {

                session.delete (c);
                tx.commit ();
                logger.debug (String.format ("Country by id %s deleted!", id.toString ()));
            } else {
                logger.warn (String.format ("Country by id %s not found!", id.toString ()));
            }

        } catch (Exception e) {

            logger.error (String.format ("Error deleting country id %s", id.toString ()));
            tx.rollback ();
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }
    }

    
    @SuppressWarnings("unchecked")
	public List<CountryEntity> readAll() {

        List<CountryEntity> list = null;

        Session session = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();

            logger.debug ("Finding all countries");
            list = (List<CountryEntity>)session.createCriteria(CountryEntity.class).list();

        } catch (Exception e) {

            logger.error ("Error finding all countries id");
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }

        return list;
    }

    
    public CountryEntity read (Integer id) {

        // Validate the arguments.
        if (id == null) {

            logger.warn ("Id country is null!");
            return null;
        }

        Session session = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();

            logger.debug (String.format ("Finding country by id %s", id.toString ()));
            CountryEntity c = (CountryEntity) session.get (CountryEntity.class, id);
            if (c != null) {

                return c;
            } else {

               logger.warn (String.format ("Country by id %s not found!", id.toString ()));
               return null;
            }

        } catch (Exception e) {

            logger.error (String.format ("Error finding country id %s", id.toString ()));
            throw new DaoException (e.getMessage (), e);

        } finally {

            session.close ();
        }
    }
    
    public CountryEntity findByName (String name) {

        if (name.isEmpty()) {

            logger.warn ("Name argument is empty!");
            return null;
        }

        CountryEntity country;
        Session session = null;
        try {

            logger.debug ("Getting hibernate session...");
            session = this.sessionFactory.openSession ();

            logger.debug (String.format ("Finding country by name %s", name));
            /*country = (CountryEntity) session.
                    createQuery ("From Country c where c.name = '" + name + "'").
                    uniqueResult ();*/

            Criteria criteria = session.createCriteria (CountryEntity.class);
            criteria.add (Restrictions.eq ("name", "ARGENTINA"));

            country = (CountryEntity) criteria.uniqueResult ();

      } catch (Exception e) {

        logger.error ("Error finding all countries id");
        throw new DaoException (e.getMessage (), e);

      } finally {

        session.close ();
      }

      return country;
    }
}