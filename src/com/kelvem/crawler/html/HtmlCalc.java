package com.kelvem.crawler.html;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kelvem.common.DateUtils;
import com.kelvem.crawler.model.HtmlNode;

/**
 * Created by kelvem on 16/3/3.
 */
public class HtmlCalc {

	public static void main(String[] args) throws Exception {

		Date start = new Date();
		String filePath = "D:/html_source/html400000/";
		File folder = new File(filePath);
		System.out.println(folder.getAbsolutePath());
		
		File[] files = folder.listFiles();
		HtmlCalc htmlCalc = new HtmlCalc();
		
		int sampleCount = 10;
		int len = files.length > sampleCount ? sampleCount : files.length;
		List<File> buf = new ArrayList<File>();
		for (int i = 0; i < len; i++) {
			buf.add(files[i]);
		}
		
		Map<String, Integer> result = htmlCalc.duplicate(buf);
		
		for (String key : result.keySet()) {
			int count = result.get(key);
			if (count > 10) {
				System.out.println(key + "\t" + count);
//				System.out.println(key.split(" ", 2)[0] + "\t" + key.split(" ", 2)[1] + "\t" + count + key.split(" ", 2)[1].trim().length());
			}
		}
		
		System.out.println("result.size() = " + result.size());
		System.out.println("files.length = " + files.length);

		Date e = new Date();
		long t = e.getTime() - start.getTime();
		t = t / 1000;
		t = t / 60;
		
		System.out.println("cost = " + t);
		System.out.println("End");
	}

	public Map<String, Integer> duplicate(List<File> files) {

		try {
			Map<String, Integer> result = new HashMap<String, Integer>();
			int count = 0;
			for (File file : files) {
				long s1 = new Date().getTime();
				Document doc = Jsoup.parse(file, "UTF-8");
				long s2 = new Date().getTime();
				Element body = doc.body();
				long s3 = new Date().getTime();

				HtmlParser htmlParser = new HtmlParser();
				HtmlNode html = htmlParser.parse(body);
				
				long s4 = new Date().getTime();
				duplicate(html, result);
				long s5 = new Date().getTime();
				
				System.out.println(s2 - s1);
				System.out.println(s3 - s2);
				System.out.println(s4 - s3);
				System.out.println(s5 - s4);
				System.out.println(" " + DateUtils.getDateString(new Date(), "hh:mm:ss SSS"));
				
				count++;
				if (count % 1000 == 0) {
					System.out.println(count);
				}
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void duplicate(HtmlNode node, Map<String, Integer> result) {
		
		for (int i = 0; i < node.getChildren().size(); i++) {
			
			duplicate(node.getChildren().get(i), result);
		}
		
		// 连接text
		String text = node.getText();
			
		if (text.length() <= 0) {
			return;
		}
		if (text.length() >= 200) {
			return;
		}
		
//		text = node.getTag() + "\t" +node.getParent().getTag() + "\t" + node.getStyle() + "\t" + node.getParentClass() + "\t" + node.getStyle() + "\t" + text;
		StringBuilder sb = new StringBuilder();
		sb.append(node.getTag()).append("\t");
		sb.append(node.getParent().getTag()).append("\t");
		sb.append(node.getStyle()).append("\t");
		sb.append(text);
		text = sb.toString();
		
		
//		System.out.println(text);
		if (result.containsKey(text)) {
			int count = result.get(text);
			count++;
			result.put(text, count);
		} else {
			result.put(text, 1);
		}
	}
	
	public void filter(HtmlNode htmlNode) {
		List<HtmlNode> nodeList = htmlNode.getChildren();
		for (int i = nodeList.size() - 1; i >= 0; i--) {
			HtmlNode subNode = nodeList.get(i);
			
			if (subNode.getCountATag() <= 0 && subNode.getCountButtonTag() <= 0 && subNode.getCountInnerText() <= 0 && subNode.getCountInputTag() <= 0) {
				nodeList.remove(i);
			} else {
				filter(subNode);
			}
		}
	}
}
