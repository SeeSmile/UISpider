package spider;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.jsoup.nodes.Document;

import utils.SwebUtil;

import entitys.QuanminEntity;

public class QuanminSpider extends VideoSpider{
	
	private static final String URL_MAIN = "http://www.quanmin.tv/json/rooms/";
	private static final String URL_MAIN2 = "/info4.json";
	
	
	public static QuanminEntity getQuanminEntity(String url) throws Exception {
		QuanminEntity entity = new QuanminEntity();
		entity.url = url;
		String id = url.substring(url.lastIndexOf("/") + 1, url.length());
		String url_api = URL_MAIN + id + URL_MAIN2;
		String result = SwebUtil.doGet(url_api);
		JSONObject json = new JSONObject(result);
		entity.nickname = json.getString("nick");
		entity.avatar = json.getString("avatar");
		entity.type = json.getString("category_name");
		entity.introduce = json.getString("intro");
		entity.fight = json.getLong("weight") + "";
		entity.fans = json.getLong("follow") + "";
		return entity;
	}

}
