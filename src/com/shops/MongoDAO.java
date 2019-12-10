package com.shops;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {
	
	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";
	
	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}
	
	
	//load Offices
	
	public ArrayList<Offices> loadOffices() throws Exception {
		ArrayList<Offices> offices = new ArrayList<Offices>();
		FindIterable<Document> docOffice = collection.find();
		
		for(Document docs:docOffice) {
			Offices o = new Offices();
			o.setStoreId((int) (docs.get("_id")));
			o.setLocation((String) docs.get("location"));
			
			offices.add(o);
			
		}
		
		mongoClient.close();
		return offices;
	}
	
	//add store method
	public void addOffice(Offices office) throws Exception {
		
		FindIterable<Document> docOffice = collection.find();
	}
}
