package com.tzz.test.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.tzz.util.MongoDBUtil;

/**
 * MongoDB数据库测试类（NoSql数据库）
 *
 */
public class MongoDBUtilTest {

	public static String collectionName = "test";

	/** 创建集合 */
	@Test
	public void testCreateCollection() {
		MongoDBUtil.createCollection(collectionName);
	}

	/** 获取集合 */
	@Test
	public void testGetCollection() {
		MongoCollection<Document> collection = MongoDBUtil.getCollection(collectionName);
		System.out.println(collection.count());
	}

	/** 删除集合 */
	@Test
	public void testDropCollection() {
		MongoDBUtil.dropCollection(collectionName);
	}

	/** 插入文档 */
	@Test
	public void testInsertMany() {
		// 插入文档
		// 1. 创建文档 org.bson.Document 参数为key-value的格式
		// 2. 创建文档集合List<Document>
		// 3.将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
		// 插入单个文档可以用mongoCollection.insertOne(Document)
		List<Document> documents = new ArrayList<Document>();
		for (int i = 0; i < 1000000; i++) {
			Document document = new Document("id", i + 1).append("title", "MongoDB").append("description", "database")
					.append("likes", 100).append("by", "Fly");
			documents.add(document);
		}
		MongoDBUtil.insertMany(collectionName, documents);
	}

	/** 检索所有文档 */
	@Test
	public void testFindAll() {
		// 检索所有文档
		// 1. 获取迭代器FindIterable<Document>
		// 2. 获取游标MongoCursor<Document>
		// 3. 通过游标遍历检索出的文档集合
		System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
		MongoCursor<Document> mongoCursor = MongoDBUtil.find(collectionName,null,null,null,null);
		System.out.println(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
		while (mongoCursor.hasNext()) {
			Document document = mongoCursor.next();
			System.out.println(document);
			System.out.println(document.toJson());
			Set<String> keys = document.keySet();
			for (String key : keys) {
				System.out.println(document.get(key));
			}
		}
	}

	/** 检索文档 */
	@Test
	public void testFindByFilte() {
		// 检索所有文档
		// 1. 获取迭代器FindIterable<Document>
		// 2. 获取游标MongoCursor<Document>
		// 3. 通过游标遍历检索出的文档集合
		// 查询条件
		Bson bson = Filters.or(Filters.and(Filters.eq("likes", 300), Filters.gt("id", 3)),
				Filters.or(Filters.eq("likes", 100), Filters.eq("id", 2)));
		// 排序1：升序、-1:降序
		Bson sort = Filters.eq("id", 1);
		// 跳过的记录条数
		Integer skip = 3;
		// 读取的记录条数
		Integer limit = 10;
		MongoCursor<Document> mongoCursor = MongoDBUtil.find(collectionName, bson, sort, skip, limit);
		while (mongoCursor.hasNext()) {
			Document document = mongoCursor.next();
			System.out.println(document);
		}
	}

	/** 更新文档 */
	@Test
	public void testUpdateMany() {
		// 更新
		UpdateResult result = MongoDBUtil.updateMany(collectionName, Filters.eq("likes", 200),
				new Document("$set", new Document("likes", 300)));
		System.out.println("更新成功：" + result.getModifiedCount() + "条记录");
		// 查看更新后的结果
		MongoDBUtil.printResult(collectionName);
	}

	/** 删除第一个文档 */
	@Test
	public void testDeleteOne() {
		DeleteResult result = MongoDBUtil.deleteOne(collectionName, Filters.eq("likes", 300));
		System.out.println("删除成功：" + result.getDeletedCount() + "条记录");
		// 查看更新后的结果
		MongoDBUtil.printResult(collectionName);
	}

	/** 删除所有符合条件的文档 */
	@Test
	public void testDeleteMany() {
		Bson bson = Filters.and(Filters.eq("likes", 100), Filters.gte("id", 0));
		DeleteResult result = MongoDBUtil.deleteMany(collectionName, bson);
		System.out.println("删除成功：" + result.getDeletedCount() + "条记录");
		// 查看更新后的结果
		MongoDBUtil.printResult(collectionName);
	}
}