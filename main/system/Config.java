package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jdk.internal.jfr.events.FileWriteEvent;

import com.google.gson.Gson;

import utils.SFileUtil;

public class Config {
	
	private final String FILE_CONFIG = "config";
	
	/**
	 * 微信数据文件
	 */
	public String PATH_WEIXIN;
	
	/**
	 * 微博数据文件
	 */
	public String PATH_WEIBO;
	
	/**
	 * 日志输入文件夹
	 */
	public String PATH_LOG;
	
	private static Config mConfig;
	
	public Config() {
		
	}
	
	public static Config getInstance() {
		if(mConfig == null) {
			mConfig = new Config();
		}
		return mConfig;
	}
	
	/**
	 * 加载配置文件
	 * @throws IOException 
	 */
	public void init() throws IOException {
		File file = new File(FILE_CONFIG);
		if(!file.exists()) {
			file.createNewFile();
		}
		String config = SFileUtil.readFile(file);
		mConfig = new Gson().fromJson(config, Config.class);
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public void saveConfig() throws IOException {
		FileWriter writer = new FileWriter(new File(FILE_CONFIG), false);
		writer.write(mConfig.toString());
		writer.close();
	}
}
