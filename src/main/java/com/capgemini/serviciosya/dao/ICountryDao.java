
package com.capgemini.serviciosya.dao;


import com.capgemini.serviciosya.beans.entity.CountryEntity;


public interface ICountryDao extends IJdbcDao<CountryEntity, Integer> {
	CountryEntity findByName (String name);
}