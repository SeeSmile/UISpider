package test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.VideoDB;

import spider.ZhanqiSpider;
import utils.SFileUtil;

public class Test1 {

	/**
	 * @param args
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		VideoDB db = new VideoDB();
		System.out.println(db.getAll());
	}

}
