package entitys;

import com.google.gson.Gson;

public class PandaEntity extends VideoEntity {
	
	/**
	 * 类型
	 */
	public String type;
	
	/**
	 * 直播公告
	 */
	public String notice;
	
	/**
	 * 上次直播时间
	 */
	public String lasttime;
	
	/**
	 * 订阅数
	 */
	public String fans;
	
	/**
	 * 人气
	 */
	public String hot;
	
	/**
	 * 身高(除以1000后的单位是mm，类推)
	 */
	public String bamboos;
	
}
