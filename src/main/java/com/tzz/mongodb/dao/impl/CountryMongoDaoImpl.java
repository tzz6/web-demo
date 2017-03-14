package com.tzz.mongodb.dao.impl;

import org.springframework.stereotype.Repository;

import com.tzz.mongodb.domain.Country;

@Repository("countryMongoDaoImpl")
public class CountryMongoDaoImpl extends MongoBaseDaoImpl<Country> {

}
