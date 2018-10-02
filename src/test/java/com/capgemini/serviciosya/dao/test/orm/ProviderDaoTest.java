
package com.capgemini.serviciosya.dao.test.orm;


import com.capgemini.serviciosya.beans.entity.CityEntity;
import com.capgemini.serviciosya.beans.entity.ProviderEntity;
import com.capgemini.serviciosya.dao.IProviderDao;
import com.capgemini.serviciosya.dao.orm.ProviderDaoHibernate;
import org.junit.Assert;
import org.junit.Test;



public class ProviderDaoTest {


    private IProviderDao dao = new ProviderDaoHibernate ();

  @Test
  public void testCreate () {

    ProviderEntity p = new ProviderEntity ();
    p.setName("Bart");
    p.setLastName("Simpson");
    p.setDni(1234);
    p.setEmail("bartsimpsongmail.com");
    p.setPhone ("1234567890");
    p.setAddress("Cordoba");
    CityEntity city = new CityEntity();
    city.setId(2);
    p.setCity(city);
    p.setStatus(0);
    this.dao.create (p);

    Assert.assertNotNull ("Failure creating new country.", p.getId ());
  }


    @Test
    public void testFindAll () {


        ProviderEntity p = this.dao.findById (1);

        p.getOccupations().forEach( e -> System.out.println (e.getName ()));


        Assert.assertFalse ("Failure find all provinces.", p.getOccupations().isEmpty());
    }

    @Test
    public void testFindByDNI () {

        ProviderEntity p = this.dao.findByDNI (123);

        Assert.assertNotNull ("Failure find by dni.", p);
    }
}