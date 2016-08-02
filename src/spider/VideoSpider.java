package spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

public class VideoSpider {
	
	public String meipai;
	public String focus;
	public String forward;
	public String fans;
	public String vip;
	
	protected static Document getDoc(String url) throws IOException {
		return Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.04")
				.get();
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
