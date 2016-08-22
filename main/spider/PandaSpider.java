package spider;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;

import entitys.PandaEntity;

public class PandaSpider extends VideoSpider {
	
	private static final String URL_MAIN = "http://www.panda.tv/api_room_v2?roomid=";
	private static final String URL_FOLLOW = "http://www.panda.tv/room_followinfo?roomid=";

	public static PandaEntity getPandaEntity(String url) throws IOException {
		PandaEntity entity = new PandaEntity();
		entity.url = url;
		String id = url.substring(url.lastIndexOf("/") + 1).trim();
		Document doc = getDoc(URL_MAIN + id);
		JSONObject json;
		try {
			json = new JSONObject(doc.body().text());
			json = json.getJSONObject("data");
			System.out.println(json);
			entity.avatar = json.getJSONObject("hostinfo").getString("avatar");
			entity.nickname = json.getJSONObject("hostinfo").getString("name");
			entity.bamboos = json.getJSONObject("hostinfo").getString("bamboos");
			entity.type = json.getJSONObject("roominfo").getString("classification");
			entity.introduce = json.getJSONObject("roominfo").getString("name");
			entity.notice = json.getJSONObject("roominfo").getString("details");
			entity.lasttime = json.getJSONObject("roominfo").getLong("start_time") + "";
			entity.hot = json.getJSONObject("roominfo").getString("person_num");
			String roomid = json.getJSONObject("roominfo").getString("id");
			entity.fans = getFollow(roomid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	private static String getFollow(String id) throws IOException, JSONException {
		String url = URL_FOLLOW + id;
		Document doc = getDoc(url);
		JSONObject json = new JSONObject(doc.body().text());
		return json.getJSONObject("data").getLong("fans") + "";
	}
}
