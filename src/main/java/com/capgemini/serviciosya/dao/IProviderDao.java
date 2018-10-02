package com.capgemini.serviciosya.dao;

import com.capgemini.serviciosya.beans.entity.ProviderEntity;

public interface IProviderDao extends IJdbcDao<ProviderEntity, Integer> {

	ProviderEntity findById(Integer id);

	ProviderEntity findByEmail(String email);

	ProviderEntity findByDNI(Integer dni);

	ProviderEntity findByPhone(String phone);

}
