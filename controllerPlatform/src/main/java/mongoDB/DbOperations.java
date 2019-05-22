package mongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class DbOperations {
	

	public DbOperations() {}
	MongoClient mongo = new MongoClient(Constants.dbHost, Constants.dbPort);
	

	public void initialize() {
		
		System.out.println(mongo);
		MongoDatabase plateDatabase = mongo.getDatabase("plateApi");
		MongoDatabase phoneDatabase = mongo.getDatabase("phNoApi");
		plateDatabase.createCollection("plateRecognition");
		phoneDatabase.createCollection("phoneRecognition");

	}

	public void insertPlateRecognition(int picture, String plateNo) {
		MongoDatabase plateDatabase = mongo.getDatabase("plateApi");
		MongoCollection<Document> platecollection = plateDatabase.getCollection("plateRecognition");
		Document document = new Document().append("picture", picture).append("plateNo", plateNo);
		platecollection.insertOne(document);
	}

	public void insertPhNoRecognition(String plateNo, String phNo) {
		MongoDatabase phoneDatabase = mongo.getDatabase("phNoApi");
		MongoCollection<Document> phonecollection = phoneDatabase.getCollection("phoneRecognition");
		Document document = new Document().append("plateNo", plateNo).append("phNo", phNo);
		phonecollection.insertOne(document);

	}

	public void deletePlateRecognition(int picture, String plateNo) {
		MongoDatabase plateDatabase = mongo.getDatabase("plateDetection");
		MongoCollection<Document> platecollection = plateDatabase.getCollection("plateCollection");
		platecollection.deleteOne(Filters.eq("picture", picture));
	}

	public void deletePhNoRecognition(String plateNo, String phNo) {
		MongoDatabase phoneDatabase = mongo.getDatabase("phNoDetection");
		MongoCollection<Document> phonecollection = phoneDatabase.getCollection("phoneCollection");
		phonecollection.deleteOne(Filters.eq("plateNo", plateNo));

	}

	public String plateRecognitionAPI(int picture) {
		MongoDatabase plateDatabase = mongo.getDatabase("plateApi");
		MongoCollection<Document> platecollection = plateDatabase.getCollection("plateRecognition");
		for (Document cur : platecollection.find()) {
			if (cur.containsValue(picture)){
				return cur.get("plateNo").toString();
		}}
			return "Could not recognize plate";
	}

	public String phNoRecognitionAPI(String plateNo) {
		MongoDatabase phoneDatabase = mongo.getDatabase("phNoApi");
		MongoCollection<Document> phonecollection = phoneDatabase.getCollection("phoneRecognition");
		for (Document cur : phonecollection.find()) {
			if (cur.containsValue(plateNo)){
				return cur.get("phNo").toString();
		}}
		return "Could not find PhoneNumber";
	}
}
