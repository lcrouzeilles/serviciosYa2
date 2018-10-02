
package com.capgemini.serviciosya.service.test;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.apache.commons.lang3.RandomStringUtils.*;


public class DataSourceTest {


    private BasicDataSource dataSource;


    // Logger object.
    private static final Logger logger = Logger.getLogger (DataSourceTest.class);


    public DataSourceTest() {

        super ();
    }


    private void writeResult (ResultSet rs) throws SQLException {

        // ResultSet is initially before the first data set
        while (rs.next ()) {

            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring (2);
            String    user = rs.getString (2);
            String email   = rs.getString (3);
            String website = rs.getString (4);
            String summary = rs.getString (5);
            String comment = rs.getString (6);

            logger.info (String.format ("User: %s", user));
            logger.info (String.format ("Email: %s", email));
            logger.info (String.format ("Website: %s", website));
            logger.info (String.format ("summary: %s", summary));
            logger.info (String.format ("Comment: %s", comment));
        }
    }


    @Before
    public void setup () throws Exception {

        // Create a new Datasource.
        this.dataSource = new BasicDataSource ();

        dataSource.setUrl("jdbc:mysql://capgemini.ceikpglz7cl4.sa-east-1.rds.amazonaws.com:3306/capgemini");
        dataSource.setUsername ("root");
        dataSource.setPassword ("dcd16cR_");
        dataSource.setMinIdle (5);
        dataSource.setMaxIdle (10);
        dataSource.setMaxOpenPreparedStatements (100);
    }

    @Test
    public void testCreate () {

        try {

            PreparedStatement pstm = this.dataSource.getConnection ().prepareStatement ("INSERT INTO comments VALUES (default, ?, ?, ?, ?, ?)");

            pstm.setString (1, randomNumeric (10));
            pstm.setString (2, String.format ("%s@gmail.com", randomAlphabetic (10)));
            pstm.setString (3, randomAlphabetic (20));
            pstm.setString (4, randomAlphanumeric (10));
            pstm.setString (5, randomAlphanumeric (50));

            // Execute the insert.
            int r = pstm.executeUpdate ();

            Assert.assertTrue ("Failure executing Insert !!!", r > 0);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Insert...", e == null);
        }
    }

    @Test
    public void testRead () {

        try {

            Statement stm = this.dataSource.getConnection ().createStatement ();

            // Result set get the result of the SQL query
            ResultSet resultSet = stm.executeQuery ("SELECT * from comments");

            Assert.assertTrue("The tables is empty !!!", resultSet.isBeforeFirst ());

            // Print the messages.
            writeResult (resultSet);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing SELECT Into...", e == null);
        }
    }

    @Test
    public void testUpdate () {

        try {

            Statement stm = this.dataSource.getConnection ().createStatement ();

            int r = stm.executeUpdate ("UPDATE comments SET summary = '--'");

            Assert.assertTrue ("Failure executing Update !!!", r > 0);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Update...", e == null);
        }
    }

    @Test
    public void testDelete () {

        try {

            PreparedStatement stm = this.dataSource.getConnection ().prepareStatement ("DELETE FROM comments");
            int r = stm.executeUpdate ();

            Assert.assertTrue("Failure executing Delete !!!", r > 0);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Delete...", e == null);
        }
    }

    @Test
    public void testTX () {

        Connection cnn = null;
        try {

            cnn = this.dataSource.getConnection ();

            cnn.setAutoCommit (false);

            cnn.createStatement ().
                    executeUpdate (String.
                            format ("INSERT INTO comments (myuser, email, webpage, summary, comments) VALUES ('%s', '%s', '%s', '%s', '%s')",
                                    "raulgpena",
                                    "raul.pena@gmail.com",
                                    randomAlphabetic (20),
                                    randomAlphanumeric (10),
                                    randomAlphanumeric (50)));

            cnn.createStatement ().
                    executeUpdate (String.
                            format ("INSERT INTO comments (myuser, email, webpage, summary, comments) VALUES ('%s', '%s', '%s', '%s', '%s')",
                                    "raulgpena",
                                    "raul.pena@gmail.com",
                                    randomAlphabetic (20),
                                    randomAlphanumeric (10),
                                    randomAlphanumeric (50)));

            cnn.commit ();

        } catch (Exception e) {

            try {
                cnn.rollback ();
            } catch (Exception e2) {}

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Insert...", e == null);
        }
    }

    @After
    public void destroy () throws Exception {

        this.dataSource.close ();
    }
}