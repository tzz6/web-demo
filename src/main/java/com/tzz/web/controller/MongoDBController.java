package com.tzz.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tzz.mongodb.dao.impl.CountryMongoDaoImpl;
import com.tzz.mongodb.domain.City;
import com.tzz.mongodb.domain.Country;

/**
 * MongoDB
 *
 */
@Controller
@RequestMapping("/mongoDB")
public class MongoDBController extends BaseController {

	@Autowired
	private CountryMongoDaoImpl countryMongoDao;

	@RequestMapping("/list")
	public String list(ModelMap model) {
		List<Country> countrys = countryMongoDao.findAll();
		model.put("countrys", countrys);
		return "/mongoDB/list";
	}

	/** 到达添加页面 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String toAddUser(ModelMap model) {
		return "/mongoDB/add";
	}

	/** 到达修改页面 */
	@RequestMapping(value = "/toUpdate/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Integer id, ModelMap model) {
		Query query = new Query(Criteria.where("id").is(id));
		Country country = countryMongoDao.findOne(query);
		model.put("country", country);
		return "/mongoDB/add";

	}

	/** 保存 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Country country, BindingResult result) {
		if (country.getId() != null) {// 编辑
			Query query = new Query(Criteria.where("id").is(country.getId()));
			Update update = new Update().set("name", country.getName()).set("enName", country.getEnName())
					.set("code", country.getCode());
			countryMongoDao.update(query, update);
		} else {// 新增
			Integer id = 0;
			List<Country> list = countryMongoDao.findAll();
			if (list != null) {
				id = list.size() + 1;
			}
			country.setId(id);
			country.setCreateDate(new Date());
			List<City> citys = new ArrayList<City>();
			City city = new City();
			city.setId(id);
			city.setName("深圳");
			citys.add(city);
			country.setCitys(citys);
			countryMongoDao.insert(country);
		}
		return "redirect:/mongoDB/list";
	}

	/** 删除 */
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		Query query = new Query(Criteria.where("id").is(id));
		countryMongoDao.remove(query);
		return "redirect:/mongoDB/list";
	}
}