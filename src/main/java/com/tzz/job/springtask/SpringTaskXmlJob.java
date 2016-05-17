package com.tzz.job.springtask;

import org.apache.log4j.Logger;

import com.tzz.job.quartz.QuartzJob;

/**
 * XML方式
 *
 */
public class SpringTaskXmlJob {

	protected final transient Logger log = Logger.getLogger(QuartzJob.class);
	
	public void push(){
		log.info("***SpringTask定时任务执行中***Xml方式***");
	}
}
