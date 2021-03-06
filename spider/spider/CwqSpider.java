package spider;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.WebUtil;
import com.google.gson.Gson;
import db.SpiderWbDB;
import db.SpiderWxDB;
import entity.CwqFriEntity;
import entity.CwqWBEntity;
import entity.CwqWXEntity;

public class CwqSpider extends BaseSpider {
	
	/**
	 * 登录
	 */
	private final String URL_LOGIN = "http://www.cwq.com/Owner/Account/check_login/";
	
	/**
	 * 获取微信列表
	 */
	private final String URL_WEIXIN = "http://www.cwq.com/Owner/Weixin/get_weixin_list/";
	
	/**
	 * 获取微播列表
	 */
	private final String URL_WEIBO = "http://www.cwq.com/Owner/Weibo/get_grassroots_list/";
	
	private final String URL_WEIBO_FAMOUS = "http://www.cwq.com/Owner/Weibo/get_celeprity_list/";
	
	/**
	 * 获取朋友圈数据
	 */
	private final String URL_FRIEND = "http://www.cwq.com/Owner/Pengyouquan/get_weixin_list/";
	
	/**
	 * 朋友圈详情
	 */
	private final String URL_FRIEND_INFO = "http://www.cwq.com/Owner/Pengyouquan/getAccountInfo/";
	
	/**
	 * 获取详细信息
	 */
	private final String URL_WEIBO_DETAIL = "http://www.cwq.com/Owner/Weibo/getAccountInfo/";
	
	private final String URL_WEIXIN_DETAIL = "http://www.cwq.com/weixin/";
	
	/**
	 * 获取区域
	 */
	private final String URL_AREA = "http://www.cwq.com/Owner/Tool/get_Region_Data";
	private final String USER_NAME = "lengzhifu";
	private final String USER_PWD = "wlf2016";
	
	private int startpage;
	private boolean isRun = false;
	private String type_id;
	private String area;
	private String type_push;
	private JTextArea jt;
	
	public void setJt(JTextArea jt) {
		this.jt = jt;
	}
	
	public void showMsg(String text) {
		jt.append(text);
	}
	
	public CwqSpider() {
		super(BaseSpider.Type.CWQ);
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPusthType() {
		return type_push;
	}

	public void setType(String type_push) {
		this.type_push = type_push;
	}

	public void setTypeId(String id) {
		this.type_id = id;
	}
	
	
	public void setPage(int page) {
		this.startpage = page;
	}
	
	public String login(String account, String pwd) {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("account", account));
		urlParameters.add(new BasicNameValuePair("password", pwd));
		urlParameters.add(new BasicNameValuePair("verify", ""));
		urlParameters.add(new BasicNameValuePair("inajax", "1"));
		
		try {
			String result = WebUtil.sendPOST(URL_LOGIN, urlParameters);
			JSONObject json = new JSONObject(result);
			String status = json.optString("status");
			if("1".equals(status)) {
				return "1";
			} else {
				return json.optString("info");
			}
			
		} catch (Exception e) {
			System.out.println("login exception");
		}
		return "登陆失败";
	}
	
	public boolean login() {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("account", USER_NAME));
		urlParameters.add(new BasicNameValuePair("password", USER_PWD));
		urlParameters.add(new BasicNameValuePair("verify", ""));
		urlParameters.add(new BasicNameValuePair("inajax", "1"));
		try {
			String result = WebUtil.sendPOST(URL_LOGIN, urlParameters);
			JSONObject json = new JSONObject(result);
			String status = json.optString("status");
			if("1".equals(status)) {
				System.out.println("login ok");
				return true;
			} else {
				System.out.println("fail to login:" + json.optString("info"));
			}
			
		} catch (Exception e) {
			System.out.println("login exception");
		}
		return false;
	}
	
	public void getWxData() {
		String type_area = getAreaByUrl();
		startGetList(getWXParam(type_id, type_area, type_push), URL_WEIXIN, new GetListener() {
			
			@Override
			public void OnFinish(JSONObject json) {
				CwqWXEntity wx = new Gson().fromJson(json.toString(), CwqWXEntity.class);
				wx.setSolt_price2(json.optString("soft_price2"));
				wx.setSolt_price3(json.optString("soft_price3"));
				System.out.println(wx);
				try {
					String label = getWeixinDetail(wx, json.optString("bs_id"));
					System.out.println(label);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				try {
//					SpiderWxDB.getInstance().insertInfo(wx, type_push, area, type_id);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
			}
		});
	}
	
	private String getWeixinDetail(CwqWXEntity entity, String id) throws IOException {
		String result = WebUtil.sendGET(URL_WEIXIN_DETAIL + id + ".html");
		org.jsoup.nodes.Document doc = Jsoup.parse(result);
		Elements eles_label = doc.select("div.label").select("span");
		String label = "";
		for(int i = 1; i < eles_label.size(); i++) {
			label += eles_label.get(i).text() + " ";
		}
		String hotword = "";
		Elements eles_word = doc.getElementById("rotate").select("a");
		for(Element ele : eles_word) {
			hotword += ele.text() + " ";
		}
		System.out.println(hotword);
		return label;
	}
	
	public void getWbData() {
		String type_area = getAreaByUrl();
//		String type_area = "322";
		startGetList(getWBParam(type_id, type_area), URL_WEIBO, new GetListener() {
		
			@Override
			public void OnFinish(JSONObject json) {
				System.out.println("json:" + json);
				String id = json.optString("bs_id");
				CwqWBEntity entity = getDetailWB(id);
				System.out.println(entity.toString());
				try {
					SpiderWbDB.getInstance().insertInfo(entity, area, type_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getWbDataFamous() {
//		String type_area = getAreaByUrl();
//		String type_area = "322";
		startGetList(getWBParamFamous(), URL_WEIBO_FAMOUS, new GetListener() {
		
			@Override
			public void OnFinish(JSONObject json) {
				System.out.println("json:" + json.optString("bs_account_name"));
				String name = json.optString("bs_account_name");
				try {
					if(SpiderWbDB.getInstance().isExistAccount(name)) {
						String id = json.optString("bs_id");
						CwqWBEntity entity = getDetailWB(id);
						System.out.println(entity.toString());
						try {
							SpiderWbDB.getInstance().insertInfo2(entity, area, type_id);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						SpiderWbDB.getInstance().updateType(name);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getFriend() {
		startGetList(getFriendParam(), URL_FRIEND, new GetListener() {
			
			@Override
			public void OnFinish(JSONObject json) {
				
				String id = json.optString("bs_id");
				getDetailFriend(id);
			}
		});
	}
	
	public void getDetailFriend(String id) {
		List<NameValuePair> param = new ArrayList<>();
		param.add(new BasicNameValuePair("account_id", id));
		param.add(new BasicNameValuePair("is_type", "0"));
		try {
			String result = WebUtil.sendPOST(URL_FRIEND_INFO, param);
			JSONObject json = new JSONObject(result);
			
			CwqFriEntity entity = new Gson().fromJson(json.getJSONObject("data").toString(), CwqFriEntity.class);
			System.out.println(entity.toString());
			
		} catch (Exception e) {
			
		}
	}
	
	public CwqWBEntity getDetailWB(String id) {
		List<NameValuePair> param = new ArrayList<>();
		param.add(new BasicNameValuePair("account_id", id));
		param.add(new BasicNameValuePair("is_type", "0"));
		param.add(new BasicNameValuePair("pt_type", "1"));
		CwqWBEntity entity = new CwqWBEntity();
		try {
			String result = WebUtil.sendPOST(URL_WEIBO_DETAIL, param);
			JSONObject json = new JSONObject(result);
			if(json.getInt("status") == 0) {
				json = json.getJSONObject("data");
				entity = new Gson().fromJson(json.toString(), CwqWBEntity.class);
			}
			return entity;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * 获取需要的请求参数
	 * @param id 分类的编号
	 * @param area 地区编号，格式：324,532,6435,777,55
	 * @return 初始化的参数
	 */
	private List<NameValuePair> getWXParam(String type_id, String type_area, String type_push) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//硬广1，软广2
//		params.add(new BasicNameValuePair("flex", type_push));
		//地区id
		params.add(new BasicNameValuePair("dfmr_mt", type_area));
		//类型过滤
		params.add(new BasicNameValuePair("cjfl", type_id));
//		params.add(new BasicNameValuePair("is_celebrity", "0"));
		params.add(new BasicNameValuePair("zfjg_type", "2"));
		System.out.println("params:\n" + params.toString());
		return params;
	}
	
	private List<NameValuePair> getWBParam(String type_id, String type_area) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//地区id
		params.add(new BasicNameValuePair("dfmr_mt", type_area));
		//类型过滤
		params.add(new BasicNameValuePair("cjfl", type_id));
		params.add(new BasicNameValuePair("py_type", "1"));
		params.add(new BasicNameValuePair("is_celebrity", "0"));
		params.add(new BasicNameValuePair("zfjg_type", "1"));
		System.out.println("params:\n" + params.toString());
		return params;
	}
	
	private List<NameValuePair> getFriendParam() {
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair("cjfl", type_id));
		list.add(new BasicNameValuePair("is_celebrity", "0"));
//		list.add(new BasicNameValuePair("ids", ""));
//		list.add(new BasicNameValuePair("order_by", ""));
		list.add(new BasicNameValuePair("zfjg_type", "1"));
		return list;
	}
	
	private List<NameValuePair> getWBParamFamous() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("py_type", "1"));
		params.add(new BasicNameValuePair("is_celebrity", "1"));
		System.out.println("params:\n" + params.toString());
		return params;
	}
	
	private void startGetList(List<NameValuePair> param, String url, GetListener listener) {
		int page = startpage;
		isRun = true;
		String lastData = "";
		List<NameValuePair> param_url = new ArrayList<>();
		while(isRun) {
			try {
				System.out.println("current page of " + page);
				param_url.clear();
				param_url.addAll(param);
				param_url.add(new BasicNameValuePair("p", page + ""));
				System.out.println(param_url);
				String result = WebUtil.sendPOST(url, param_url);
				JSONObject json = new JSONObject(result);
				json = json.optJSONObject("data");
				JSONArray array = json.optJSONArray("list");
				if(array.length() == 0 || lastData.equals(array.toString())) {
					isRun = false;
					System.out.println("stoped");
					break;
				}
				for(int i = 0; i < array.length(); i++) {
					json = array.getJSONObject(i);
					listener.OnFinish(json);
				}
				lastData = array.toString();
			} catch (Exception e) {
				System.out.println("run exception");
				e.printStackTrace();
			}
			page++;
		}
	}
	
	private String getAreaByUrl() {
		List<NameValuePair> param = new ArrayList<>();
		param.add(new BasicNameValuePair("parent_id", area));
		try {
			String result = WebUtil.sendPOST(URL_AREA, param);
			JSONObject json = new JSONObject(result);
			JSONArray array = json.getJSONArray("data");
			String str_area = "";
			for(int i = 0; i < array.length(); i++) {
				str_area += array.getJSONObject(i).getString("region_id") + ",";
			}
			return str_area.substring(0, str_area.length() - 1);
		} catch (Exception e) {
			
		}
		return null;
	}
	
	interface GetListener{
		public void OnFinish(JSONObject json);
	}
}
