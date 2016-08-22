package test;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spider.BiGaoSpider;

public class TestBiGao {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		String result = BiGaoSpider.getResult("b");
		JSONObject json = new JSONObject(result);
		if(json.getInt("code") == 200) {
			json.opt("");
			JSONObject json_data = json.getJSONObject("data");
			JSONArray array_first = json_data.getJSONArray("first");
			
			if(array_first.getJSONObject(0).has("json")) {
				System.out.println("json:\n" + array_first.getJSONObject(0).getString("json"));
			}
			System.out.println(array_first);
		}
	}

}
