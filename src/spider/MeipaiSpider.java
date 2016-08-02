package spider;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import entitys.MeipaiEntity;

public class MeipaiSpider extends VideoSpider {
	
	public static final String SPIDER_TAG = "meipai";
	private static final String TAG_INFO = "rightUser";
	private static final String TAG_INFO_NAME = "user-name";
	private static final String TAG_INFO_AVATAR = "avatar";
	private static final String TAG_INFO_NUM = "user-num";
	
	public static MeipaiEntity getMeipaiEntity(String url) throws IOException {
		MeipaiEntity entity = new MeipaiEntity();
		Document doc = getDoc(url);
		Element ele_info = doc.getElementById(TAG_INFO);
		entity.url = url;
		entity.nickname = ele_info.getElementsByClass(TAG_INFO_NAME).text();
		entity.introduce= ele_info.select("p.user-descript").text();
		String gender = ele_info.getElementsByClass(TAG_INFO_NAME).select("i").attr("class");
		String vip = ele_info.select("img.user-vip").toString();
		if(vip.trim().length() > 0) {
			entity.vip = "1";
		} else {
			entity.vip = "2";
		}
		entity.gender = fifterGender(gender);
		entity.avatar = ele_info.getElementsByClass(TAG_INFO_AVATAR).attr("src");
		Elements ele_num = ele_info.getElementsByClass(TAG_INFO_NUM);
		entity.meipai = ele_num.select("a").get(0).select("span").get(0).text();
		entity.forward = ele_num.select("a").get(1).select("span").get(0).text();
		entity.focus = ele_num.select("a").get(2).select("span").get(0).text();
		String fans = ele_num.select("a").get(3).select("span").get(0).text();
		entity.fans = formatFans(fans);
		return entity;
	}
	
	private static String formatFans(String fans) {
		String fan = fans;
		if(fan.contains("万")) {
			fan = fan.replace("万", "");
			fan = Float.valueOf(fan) * 10000 + "";
		}
		return fan;
	}
	private static String fifterGender(String gender) {
		if(gender.contains("female")) {
			return "2";
		} else {
			return "1";
		}
	}
}
