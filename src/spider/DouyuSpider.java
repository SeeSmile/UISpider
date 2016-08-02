package spider;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import entitys.DouyuEntity;

public class DouyuSpider extends VideoSpider {
	
	private static final String TAG_INFO = "anchor-info";
	private static final String TAG_AVATAR = "anchor-pic";
	private static final String TAG_INTRODUCE = "div.headline";
	private static final String TAG_NAME = "a.zb-name";
	private static final String TAG_FANS = "a.num-v";
	private static final String TAG_WEIGHT = "a.weight-v";
	private static final String TAG_NOTICE = "p.column-cotent";
	

	public static DouyuEntity getDouyuEntity(String url) throws IOException {
		DouyuEntity entity = new DouyuEntity();
		Document doc = getDoc(url);
		entity.avatar = doc.getElementsByClass(TAG_AVATAR).select("img").attr("src");
		Element ele_info = doc.getElementById(TAG_INFO);
		if(ele_info == null) {
			return null;
		}
		entity.introduce = ele_info.select(TAG_INTRODUCE).select("h1").text();
		entity.nickname = ele_info.select(TAG_NAME).text();
		entity.fans = ele_info.select(TAG_FANS).text();
		entity.notice = doc.select(TAG_NOTICE).text();
		return entity;
	}
	
}
