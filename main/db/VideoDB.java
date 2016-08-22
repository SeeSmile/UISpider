package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import entitys.VideoEntity;

public class VideoDB extends BaseMonGoDB{

	private static final String DB_NAME = "wlf_data_video";
	
	public void insertInfo(VideoEntity entity) {
		getDataBase().getCollection(DB_NAME).insertOne(Document.parse(entity.toString()));
	}
	
	public List<String> getAll() {
		List<String> list = new ArrayList<>();
		MongoCursor<Document> lit = getDataBase().getCollection(DB_NAME).find().iterator();
		while(lit.hasNext()) {
			list.add(lit.next().toJson());
		}
		return list;
	}
}
