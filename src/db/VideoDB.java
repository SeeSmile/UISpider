package db;

import org.bson.Document;

import entitys.VideoEntity;

public class VideoDB extends BaseMonGoDB{

	private static final String DB_NAME = "wlf_data_video";
	
	public void insertInfo(VideoEntity entity) {
		getDataBase().getCollection(DB_NAME).insertOne(Document.parse(entity.toString()));
	}
	
}
