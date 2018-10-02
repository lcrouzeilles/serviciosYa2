
package com.capgemini.serviciosya.service.test;


import org.apache.log4j.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.serviciosya.beans.domain.Occupation;
import com.capgemini.serviciosya.dao.IJdbcDao;
import com.capgemini.serviciosya.dao.jdbc.OccupationJdbcDao;

import java.util.List;


public class OccupationServiceTest {


    private static final Logger logger = Logger.getLogger (OccupationServiceTest.class);


    @SuppressWarnings("rawtypes")
	@Test
    public void testAdd () {

        try {

            // Insert new value.
            logger.info ("Starting occupation add test.");
            IJdbcDao dao = new OccupationJdbcDao ();

            logger.debug ("Inserting new occupation.");
            Occupation c = new Occupation ();
            c.setName ("Encantador de suegras.");
            c.setDescription ("Amo el peligro");
            dao.create(c);

           logger.debug ("Checking test result.");
           List<Occupation> list = dao.readAll ();

           boolean r = Boolean.FALSE;

           for (Occupation item: list) {

               if (item.getName ().equals (c.getName ())) {
                   r = Boolean.TRUE;
                   break;
               }
           }

           logger.info ("Finishing the test...");
           Assert.assertTrue ("Failure inserting new Occupation.", r);


        } catch (Exception e) {

            Assert.assertNull (e);
        }
    }

}