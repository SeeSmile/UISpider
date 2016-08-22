package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import spider.CwqSpider;
import utils.WebUtil;

public class Testspider {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
		CwqSpider sp = new CwqSpider();
		sp.login();
		for(int i = 1; i <= 15; i++) {
			sp.setTypeId(i + "");
			sp.setPage(1);
			sp.getFriend();
		}
	}

}
