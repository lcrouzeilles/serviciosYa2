
package com.capgemini.serviciosya.dao.test;


import org.junit.Assert;
import org.junit.Test;

import com.capgemini.serviciosya.beans.entity.CountryEntity;
import com.capgemini.serviciosya.dao.ICountryDao;
import com.capgemini.serviciosya.dao.orm.CountryDaoHibernate;

import java.util.List;


public class CountryDaoTest {


    private ICountryDao dao = (ICountryDao) new CountryDaoHibernate ();


    @Test
    public void testCreate () {

        CountryEntity c = new CountryEntity ();
        c.setName ("Venezuela");
        this.dao.create (c);

      Assert.assertNotNull ("Failure creating new country.", c.getId ());
    }

    @Test
    public void testUpdate () {

        CountryEntity c = new CountryEntity ();
        c.setId (3);
        c.setName ("ARGENTINA");
        this.dao.update (c);

        Assert.assertEquals ("Failure updating country.", "ARGENTINA", c.getName ());
    }

    @Test
    public void testDelete () {

        int id = 4;
        this.dao.delete (id);

        CountryEntity c = this.dao.read (id);

        Assert.assertNull ("Failure deleting country.", c);
    }

    @Test
    public void testFindAll () {

        List<CountryEntity> list = this.dao.readAll ();

        list.forEach (System.out::println);

        Assert.assertFalse ("Failure find all countries.", list.isEmpty ());
    }
}