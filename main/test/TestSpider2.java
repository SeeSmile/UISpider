package test;

import spider.CwqSpider;

public class TestSpider2 {
	
	public static void main(String[] args) {
		CwqSpider spider = new CwqSpider();
		spider.login();
		spider.setArea("2");
		spider.setPage(1);
		spider.getWxData();
	}
}
