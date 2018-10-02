package com.capgemini.serviciosya.dao.jdbc;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

import com.capgemini.serviciosya.dao.DaoException;


public final class CapgeminiDB {


    private static final BasicDataSource dataSource;


    static {

        dataSource = new BasicDataSource ();

        try {

            Properties pop = new Properties();
            pop.load(CapgeminiDB.class.getClassLoader().
                    getResourceAsStream ("jdbc.properties"));


            dataSource.setUrl(pop.getProperty ("jdbc.url"));
            dataSource.setUsername (pop.getProperty ("jdbc.user"));
            dataSource.setPassword (pop.getProperty ("jdbc.pw"));
            dataSource.setDriverClassName(pop.getProperty ("jdbc.driver"));
            dataSource.setMinIdle (Integer.parseInt (pop.getProperty ("jdbc.pool.min")));
            dataSource.setMaxIdle (Integer.parseInt (pop.getProperty ("jdbc.pool.max")));

        } catch (Exception e) {

            throw new DaoException(e);
        }
    }


    private CapgeminiDB () {

        super ();
    }


    protected static Connection getConnection () throws SQLException {

        return dataSource.getConnection();
    }
}