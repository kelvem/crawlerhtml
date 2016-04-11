package com.kelvem.crawler.html;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kelvem.common.CrawlerUtil;

public class HtmlContent {

	public static void main(String[] args) {

		getContent("http://bbs.csdn.net/topics/390082054");
		
		
//		String[] a = getDomains("http://bbs.csdn.net/topics/390082054");
//		for (String string : a) {
//			System.out.println(string);
//		}
//		System.out.println("-------------------");
//		System.out.println();
//		
//		
//		getDomains("http://www.csdn.net/topics/390082054");
//		for (String string : a) {
//			System.out.println(string);
//		}
//		System.out.println("-------------------");
//		System.out.println();
		
	}
	
	public static String getContent(String url) {
		
		if (url == null || url.length() < 3) {
			throw new RuntimeException("url太短");
		}
		System.out.println("准备解析url : " + url);
		
		String[] domains = getDomains(url);
		if (domains.length < 2) {
			throw new RuntimeException("url域名解析错误");
		}
		String mainDomain = domains[1] + "." + domains[0];
		
		String subDomain = "";
		if (domains.length >= 3) {
			subDomain = domains[2];
		}
		System.out.println("域名提取 #" + subDomain + "." + mainDomain);
		
		String html = CrawlerUtil.get(url);
		System.out.println("爬取网页 : " + ((html.length() > 400) ? html.substring(0, 400) : html));
		
		System.out.println("使用jsoup解析html");
		Document doc = Jsoup.parse(html);
		Element body = doc.body();
		
		/**
		 * 
		 */
		System.out.println("初始化相应规则库开始");
		List<TagRuleHandle> ruleList = getTagRule(mainDomain, subDomain);
		if (ruleList == null || ruleList.size() <= 0) {
			System.out.println("规则库不存在, 计算规则中");
			// to do 规则初始化
			/**
			 * ...................
			 */
		}
		System.out.println("初始化相应规则库完成");
		
		Element content = getContentTag(body, ruleList);
		
		
		
		return null;
	}
	
	private static Element getContentTag(Element body, List<TagRuleHandle> ruleList) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<TagRuleHandle> getTagRule(String mainDomain, String subDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String[] getDomains(String url){
		
		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		Matcher m = p.matcher(url);
		if(m.find()){
			String domain = m.group();
			String[] buf = domain.split("\\.");
			int size = buf.length;
			String[] domains = new String[size];
			for (int i = 0; i < size; i++) {
				domains[i] = buf[size - i - 1];
			}
			return domains;
		} else {
			return new String[0];
		}
	}

}
