package com.tzz.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * MongoDB数据库工具类
 *
 */
public class MongoDBUtil {
	public static String ip = "localhost";
	public static int port = 27017;

	/** 连接数据库 */
	public static MongoDatabase getMongoDatabase() {
		MongoClient mongoClient = null;
		MongoDatabase mongoDatabase = null;
		try {
			// 连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
			// ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress(ip, port);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			// 连接到 mongodb 服务
			mongoClient = new MongoClient(addrs);
			// 连接到数据库
			mongoDatabase = mongoClient.getDatabase("demo");
			System.out.println("Connect to " + mongoDatabase.getName() + " database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return mongoDatabase;
	}

	/** 创建集合 */
	public static void createCollection(String collectionName) {
		MongoDatabase mongoDatabase = getMongoDatabase();
		System.out.println("Connect to database successfully");
		mongoDatabase.createCollection(collectionName);
		System.out.println("集合创建成功");
	}

	/** 获取集合 */
	public static MongoCollection<Document> getCollection(String collectionName) {
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		return collection;
	}

	/** 删除集合 */
	public static void dropCollection(String collectionName) {
		MongoDatabase mongoDatabase = getMongoDatabase();
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		collection.drop();
		System.out.println("集合" + collectionName + "删除成功");
	}

	/** 插入文档 */
	public static void insertMany(String collectionName, List<Document> documents) {
		// 插入文档
		// 1. 创建文档 org.bson.Document 参数为key-value的格式
		// 2. 创建文档集合List<Document>
		// 3.将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>)
		// 插入单个文档可以用mongoCollection.insertOne(Document)
		MongoCollection<Document> collection = getCollection(collectionName);
		collection.insertMany(documents);
		System.out.println("文档插入成功");
	}

	/***
	 * 检索文档 
	 * @param collectionName 集合名
	 * @param filter 查询条件
	 * @param sort 排序
	 * @param skip 跳过的记录条数
	 * @param limit 读取的记录条数
	 * @return
	 */
	public static MongoCursor<Document> find(String collectionName, Bson filter, Bson sort, Integer skip,
			Integer limit) {
		// 检索所有文档
		// 1. 获取迭代器FindIterable<Document>
		// 2. 获取游标MongoCursor<Document>
		// 3. 通过游标遍历检索出的文档集合
		MongoCollection<Document> collection = getCollection(collectionName);
		FindIterable<Document> findIterable = collection.find();
		if (filter != null) {
			findIterable.filter(filter);
		}
		if (sort != null) {
			findIterable.sort(sort);
		}
		if (skip != null) {
			findIterable.skip(limit);
		}
		if (limit != null) {
			findIterable.limit(limit);
		}
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		return mongoCursor;
	}

	/** 更新文档 */
	public static UpdateResult updateMany(String collectionName, Bson filter, Bson update) {
		MongoCollection<Document> collection = getCollection(collectionName);
		// 更新文档 将文档中likes=100的文档修改为likes=200
		return collection.updateMany(filter, update);
	}

	/** 删除第一个文档 */
	public static DeleteResult deleteOne(String collectionName, Bson filter) {
		DeleteResult result = null;
		try {
			MongoCollection<Document> collection = getCollection(collectionName);
			// 删除符合条件的第一个文档
			result = collection.deleteOne(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** 删除所有符合条件的文档 */
	public static DeleteResult deleteMany(String collectionName, Bson filter) {
		MongoCollection<Document> collection = getCollection(collectionName);
		// 删除所有符合条件的文档
		return collection.deleteMany(filter);
	}

	/** 输出结果 */
	public static void printResult(String collectionName) {
		// 查看更新后的结果
		MongoCursor<Document> mongoCursor = MongoDBUtil.find(collectionName, null, null, null, null);
		while (mongoCursor.hasNext()) {
			Document document = mongoCursor.next();
			System.out.println(document);
		}
	}
}