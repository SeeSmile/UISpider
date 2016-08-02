package test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import spider.ZhanqiSpider;
import utils.SFileUtil;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		String url = "http://www.zhanqi.tv/11511848";
		System.out.println(ZhanqiSpider.getZhanqiEntity(url));
	}

}
