
package com.capgemini.serviciosya.dao.orm;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.apache.log4j.Logger;

public final class HibernateUtil {

	private static SessionFactory sessionAnnotationFactory;
	private static SessionFactory sessionXMLFactory;

	private static final Logger logger = Logger.getLogger(HibernateUtil.class);

	private HibernateUtil() {

		super();
	}

	private static SessionFactory buildSessionAnnotationFactory() {

		try {

			logger.debug("Loading Hibernate Annotation Configuration...");
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");
			logger.debug("Hibernate Annotation Configuration loaded...");

			logger.debug("Creating Hibernate Annotation serviceRegistry...");
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			logger.debug("Hibernate Annotation serviceRegistry created...");

			logger.debug("Creating Session Factory...");
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			logger.debug("Session Factory created...");

			return sessionFactory;
		}

		catch (Throwable ex) {

			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionAnnotationFactory() {

		if (sessionAnnotationFactory == null) {

			sessionAnnotationFactory = buildSessionAnnotationFactory();
		}

		return sessionAnnotationFactory;
	}

	private static SessionFactory buildSessionXMLFactory() {

		try {

			Configuration configuration = new Configuration();
			configuration.configure("hibernate-xml.cfg.xml");
			logger.debug("Hibernate Configuration loaded...");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			logger.debug("Hibernate serviceRegistry created...");

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;

		} catch (Throwable ex) {

			logger.error("Initial SessionFactory creation failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionXMLFactory() {

		if (sessionXMLFactory == null) {

			sessionXMLFactory = buildSessionXMLFactory();
		}

		return sessionXMLFactory;
	}
}
