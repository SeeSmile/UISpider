package spider;

import java.io.IOException;

import utils.WebUtil;

public class BiGaoSpider {
	
	private static String URL_FIND = "http://www.qubigao.com/bigao-service/media/searchMedia?platform=0&page=1&rows=10&name=";
	
	public static String getResult(String keyword) throws IOException {
		String url = URL_FIND + keyword;
		return WebUtil.sendGET(url);
	}
}
