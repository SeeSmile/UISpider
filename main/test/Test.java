package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.VideoDB;

import entitys.MeipaiEntity;
import entitys.VideoEntity;
import entitys.ZhanqiEntity;

import spider.DouyuSpider;
import spider.MeipaiSpider;
import spider.MiaopaiSpider;
import spider.PandaSpider;
import spider.QuanminSpider;
import spider.ZhanqiSpider;
import utils.SFileUtil;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws Exception 
	 */
	public static void main(String[] args) throws IOException  {
		List<String> list = new ArrayList<>();
		try {
			list = SFileUtil.readFileToList(new File(SFileUtil.getDataFile("video.txt")));
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		int index = 0;
		int p = 0;
		VideoDB db = new VideoDB();
		List<String> list_nodata = new ArrayList<>();
		for(String url : list) {
			System.out.println(index + " || " + url);
			index++;
			url = url.trim();
			url = url.substring(url.indexOf("http"));
			if(index < p) {
				continue;
			}
			VideoEntity entity = null;
			if(url.contains("meipai")) {
				entity = MeipaiSpider.getMeipaiEntity(url);
			} else if(url.contains("zhanqi")) {
				entity = ZhanqiSpider.getZhanqiEntity(url);
			} else if(url.contains("panda")) {
				entity = PandaSpider.getPandaEntity(url);
			} else if(url.contains("miaopai") || url.contains("yixia")) {
				try {
					entity = MiaopaiSpider.getMiaopaiEntity(url);
				} catch (IOException e) {
					System.out.println("美拍的io错误了");
				}
				
			} else if(url.contains("douyu")) {
				entity = DouyuSpider.getDouyuEntity(url);
			} else if(url.contains("quanmin")) {
				try {
					entity = QuanminSpider.getQuanminEntity(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(entity != null) {				
				db.insertInfo(entity);
			} else {
				list_nodata.add(url);
			}
		}
		System.out.println("没有数据的:");
		for(String str : list_nodata) {
			System.out.println(str);
		}
	}

}
