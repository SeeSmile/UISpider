package entity;

import com.google.gson.Gson;

public class CwqFriEntity {
	
	public String pg_area_name;
	public String bs_remark;
	public String pg_industries_explain;
	public String pg_fans_num_explain;
	public String pg_label_explain;
	public String bs_sex;
	public String bs_age;
	public String bs_friend_desc;
	public String bs_zhi_price;
	public String bs_zhuan_price;
	public String bs_head_img;
	public String bs_account_name;
	public String bs_weixinhao;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
