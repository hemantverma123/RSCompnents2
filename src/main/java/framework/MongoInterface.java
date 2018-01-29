package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.junit.Test;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoCredential;
import com.mongodb.MongoSecurityException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.ServerAddress;
import com.google.gson.JsonParseException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoInterface extends TestBase {

	DB db;
	DBCollection ApplicantsCollection;
	DBCollection col;
	DBCursor dbcursor;
	BasicDBObject query;
	
	MongoClient mongoClient = null;
	
	public MongoInterface(String collection){

		try {
			mongoLogin();
		} catch (MongoCommandException | MongoSecurityException e)

		{
			System.out.println("cannot login");
			e.printStackTrace();
		}

		System.out.println("Connecting to collecton: "+ collection);
		col = db.getCollection(collection);
	}

	public void mongoLogin() {

		MongoCredential mongoCredential = MongoCredential.createMongoCRCredential("firstuser", "testdb",
				"first".toCharArray());
		// MongoCredential{mechanism=MONGODB-CR, userName='firstuser', source='testdb',
		// password=<hidden>, mechanismProperties={}}
		System.out.println("credential is: " + mongoCredential.toString());
		mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(mongoCredential));
		db = mongoClient.getDB("testdb");
		//DBCollection Applicants=db.createCollection("Applicants",new BasicDBObject()); 
		System.out.println("Status is " + db.getStats());
		System.out.println("collections name: " + db.getCollectionNames());
	}

	public void createuser() {
		System.out.println("Creating user...");
		db.addUser("firstuser", "first".toCharArray());
	}
	
	public void addDataToMongo(BasicDBObject msg ) {
		
		System.out.println("adding msg to collection..."+ col);
		
   	 	try {
   	 		//DBObject msgdata = (DBObject) BasicDBObject.parse(msg);
   	 		col.insert(msg);
		} catch (MongoWriteConcernException|JsonParseException  e) {

			System.out.println("Cannot write to the collection: " +col);
			e.printStackTrace();
		}
	}

	public void deleteMongoData(BasicDBObject query) {
		System.out.println("Deleting data using query: "+query);
		
		if(query.isEmpty()) {
		System.out.println("Please specify the valid query...");
		}else{			
			col.remove(query);
		}
	}

	
	public void writeMongoToFile(String file, BasicDBObject query) {
		System.out.println("Writing data to file" + file);
		String data = getMongoData(query);
		writeToDataFile(file,data);
	}
	
	public void writeToMongofromExcel(BasicDBObject msg) {
		addDataToMongo(msg);
	}
	
	public String getMongoData(BasicDBObject query) {
		String data="";
		
		System.out.println("getting data using query: "+query);
		
		if(query.isEmpty()) {
		dbcursor = col.find();
		}else{			
			dbcursor = col.find(query);
		}

		System.out.println("dbcursor size = "+dbcursor.size());
		try {
            while (dbcursor.hasNext()) {
                //System.out.println(dbcursor.next());
            	data += dbcursor.next().toString() + "\n";
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
		return data;
	}

	
	public void showMongoData(BasicDBObject query) {
		System.out.println("Showing using query: "+query);
		String data = getMongoData(query);
		System.out.println(data);
	}

	public DBObject findDocumentById(String id) {

	    BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));

	    DBObject dbObj = col.findOne(query);
	    System.out.println("using doc id: " + dbObj);
	    return dbObj;
	}
	
	public void mongoClose() {
		mongoClient.close();
	}


//public void allkeys() {
//	var keys = [];
//	db.marks.find().forEach(function(doc){
//	    for (var key in doc){ 
//	        if(keys.indexOf(key) < 0){
//	           keys.push(key);
//	        }
//	    }
//	});
//
//	print(keys);
//}
	
	//	public void uniquekeys() {
//		mr = db.runCommand({
//			  "mapreduce" : "my_collection",
//			  "map" : function() {
//			    for (var key in this) { emit(key, null); }
//			  },
//			  "reduce" : function(key, stuff) { return null; }, 
//			  "out": "my_collection" + "_keys"
//			})
//				
//				db[mr.result].distinct("_id");				
//				
//	}
	
}
