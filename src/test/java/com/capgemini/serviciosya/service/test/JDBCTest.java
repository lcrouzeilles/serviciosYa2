
package com.capgemini.serviciosya.service.test;


import java.sql.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class JDBCTest {


    private Connection cnn;


    // Logger object.
    private static final Logger logger = Logger.getLogger (JDBCTest.class);


    public JDBCTest () {

        super ();
    }


    private void writeResult (ResultSet resultSet) throws SQLException {

        // ResultSet is initially before the first data set
        while (resultSet.next ()) {

            int id = resultSet.getInt ("ID");
            String name   = resultSet.getString ("NAME");

            logger.debug (String.format ("Id: %s", id));
            logger.info (String.format ("Name: %s", name));
        }
    }


    @Before
    public void setup () throws Exception {

        // Register the driver.
        Class.forName ("org.postgresql.Driver");

        // Create a new connection.
        this.cnn = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/serviciosya", "postgres", "dcd16cP_");
    }

    @Test
    public void testCreate () {

        try {

            PreparedStatement pstm = this.cnn.prepareStatement ("INSERT INTO \"COUNTRY\" (name) VALUES (?)");

            pstm.setString (1, "Uruguay");

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

            Statement stm = this.cnn.createStatement ();

            // Result set get the result of the SQL query
            ResultSet resultSet = stm.executeQuery ("SELECT * from \"COUNTRY\"");

            // Print the messages.
            writeResult (resultSet);

            Assert.assertTrue("The tables is empty !!!", !resultSet.isBeforeFirst ());

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing SELECT Into...", e == null);
        }
    }



    @Test
    public void testUpdate () {

        try {

            Statement stm = this.cnn.createStatement ();

            int r = stm.executeUpdate ("UPDATE \"COUNTRY\" SET \"NAME\" = UPPER(\"NAME\")");

            Assert.assertTrue ("Failure executing Update !!!", r > 0);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Update...", e == null);
        }
    }

    @Test
    public void testDelete () {

        try {

            PreparedStatement stm = this.cnn.prepareStatement ("DELETE FROM \"COUNTRY\"");
            int r = stm.executeUpdate ();

            Assert.assertTrue("Failure executing Delete !!!", r > 0);

        } catch (Exception e) {

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Delete...", e == null);
        }
    }

    @Test
    public void testTX () {

        try {

            this.cnn.setAutoCommit (false);

            this.cnn.createStatement ().
                    executeUpdate ("INSERT INTO \"COUNTRY\" (\"NAME\") VALUES ('BRASIL')");

            this.cnn.createStatement ().
                    executeUpdate ("INSERT INTO \"COUNTRY\" (\"NAME\") VALUES ('PERU')");

            this.cnn.createStatement ().
                    executeUpdate ("DELETE FROM \"COUNTRY\"");

            int a = 1/0;

            this.cnn.commit ();

        } catch (Exception e) {

            try {
                this.cnn.rollback ();
            } catch (Exception e2) {}

            e.printStackTrace ();
            Assert.assertTrue ("Failure executing Insert...", e == null);
        }
    }

    @After
    public void destroy () throws Exception {

        this.cnn.close ();
    }
}