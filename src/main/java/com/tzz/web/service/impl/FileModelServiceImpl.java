package com.tzz.web.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tzz.web.dao.BaseDao;
import com.tzz.web.domain.FileModel;
import com.tzz.web.service.FileModelService;

@Service("fileModelService")
public class FileModelServiceImpl extends BaseServiceImpl<FileModel> implements FileModelService {

	@Override
	@Resource(name = "fileModelDao")
	public void setBaseDao(BaseDao<FileModel> baseDao) {
		super.setBaseDao(baseDao);
	}

	/** 单值检索 */
	public Object uniqueResult(String hql, Serializable... serializables) {
		return uniqueResult(hql, serializables);
	}
}
