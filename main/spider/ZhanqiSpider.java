package spider;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import entitys.ZhanqiEntity;

public class ZhanqiSpider extends VideoSpider {
	
	private static final String URL_TAG = "http://www.zhanqi.tv/api/static/anchor.tags/";
	
	public static ZhanqiEntity getZhanqiEntity(String url) throws IOException {
		ZhanqiEntity entity = new ZhanqiEntity();
		Document doc = getDoc(url);
		entity.url = url;
		int p_start = doc.toString().indexOf("window.oPageConfig.oRoom");
		if(p_start < 0) {
			return null;
		}
		String text = doc.toString().substring(p_start);
		p_start = text.indexOf("{");
		int p_end = text.indexOf("};");
		text = text.substring(p_start, p_end + 1);
		try {
			JSONObject json = new JSONObject(text);
			entity.nickname = json.getString("nickname");
			entity.avatar = json.getString("avatar");
			entity.introduce = json.getString("title");
			entity.level = json.getInt("hotsLevel") + "";
			entity.fans = json.getInt("follows") + "";
			entity.hostinfo = json.getString("roomDesc");
			entity.tag = getTag(json.getString("uid"));
			entity.type = json.getString("gameName");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	private static String getTag(String id)throws IOException {
		Document doc_tag = getDoc(URL_TAG + id + ".json");
		String tag = "";
		JSONObject json;
		try {
			json = new JSONObject(doc_tag.body().text());
			System.out.println("jsonTAG:" + json);
			JSONArray array = json.getJSONArray("data");
			for(int i = 0; i < array.length(); i++) {
				tag += array.getJSONObject(i).getString("name") + " ";
			}
		} catch (JSONException e) {
//			System.out.println("没有 tag");
		}
		return tag;
		
	}
}
