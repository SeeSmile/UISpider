package spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entitys.MiaopaiEntity;

public class MiaopaiSpider extends VideoSpider {
	
	
	private static final String TAG_INFO = "personal_msg";
	private static final String TAG_AVATAR = "div.head";
	private static final String TAG_INTRO = "p.p_itro";
	private static final String TAG_VIP = "img.vip";
	
	
	
	public static MiaopaiEntity getMiaopaiEntity(String url) throws IOException {
		MiaopaiEntity entity = new MiaopaiEntity();
		entity.url = url;
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Linux; U; Android 4.4.4; Nexus 5 Build/KTU84P) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30")
				.get();
		if(doc.getElementsByClass(TAG_INFO) == null || doc.getElementsByClass(TAG_INFO).size() == 0) {
			return null;
		}
		Element ele_info = doc.getElementsByClass(TAG_INFO).get(0);
		
		entity.avatar = ele_info.select(TAG_AVATAR).attr("data-url");
		entity.introduce = ele_info.select(TAG_INTRO).text();
		String vip = ele_info.select(TAG_VIP).attr("src");
		if(vip.length() > 0) {
			entity.isVip = "1";
		} else {			
			entity.isVip = "2";
		}
		entity.nickname = ele_info.select("p").get(0).text();
		String gender = ele_info.select("p").get(0).select("img").attr("src");
		if(gender.contains("girl")) {
			entity.gender = "2";
		} else {
			entity.gender = "1";
		}
		Elements ele_num = ele_info.select("p").last().select("a");
		entity.video = ele_num.get(0).text();
		entity.focus = ele_num.get(1).text();
		entity.fans = ele_num.get(2).text();
		return entity;
	}
	
	
}
