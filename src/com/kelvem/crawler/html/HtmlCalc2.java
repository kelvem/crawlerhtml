package com.kelvem.crawler.html;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.kelvem.crawler.model.HtmlNode;

/**
 * Created by kelvem on 16/3/3.
 */
public class HtmlCalc2 {

	public static void main(String[] args) throws Exception {

		Date start = new Date();
		String filePath = "D:/html_source/html400000/";
		File folder = new File(filePath);
		System.out.println(folder.getAbsolutePath());
		
		File[] files = folder.listFiles();
		HtmlCalc2 htmlParser = new HtmlCalc2();
		
		Map<String, Integer> result = htmlParser.duplicate(files);
		
		for (String key : result.keySet()) {
			int count = result.get(key);
			if (count > 100) {
				System.out.println(key.split(" ", 2)[0] + "\t" + key.split(" ", 2)[1] + "\t" + count + key.split(" ", 2)[1].trim().length());
			}
		}
		
		System.out.println(result.size());
		System.out.println(files.length);

		Date e = new Date();
		long t = e.getTime() - start.getTime();
		t = t / 1000;
		t = t / 60;
		
		System.out.println(t);
		System.out.println("End");
	}

	public Map<String, Integer> duplicate(File[] files) {

		try {
			Map<String, Integer> result = new HashMap<String, Integer>();
			int count = 0;
			for (File file : files) {
				Document doc = Jsoup.parse(file, "UTF-8");
				duplicate(doc, result);
				
				count++;
				if (count % 1000 == 0) {
					System.out.println(count);
				}
				if (count > 10000) {
					break;
				}
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void duplicate(Element jsoupElement, Map<String, Integer> result) {

		for (int i = 0; i < jsoupElement.childNodeSize(); i++) {

			if (jsoupElement.childNode(i) instanceof Element) {
				
				duplicate((Element)jsoupElement.childNode(i), result);
				
			} else if (jsoupElement.childNode(i) instanceof Node) {
//				System.out.println(jsoupElement.childNode(i).nodeName() + "\t" + jsoupElement.childNode(i).toString());
				if (jsoupElement.childNode(i).nodeName().equals("#text")) {
					// 连接text
					String text = jsoupElement.childNode(i).outerHtml();
					
					text = text.replace("&nbsp;", " ");
					
					if (text.startsWith("\r\n")) {
						text = text.substring(2);
					}
					if (text.startsWith("\n")) {
						text = text.substring(1);
					}
					
					text = text.trim();

					if (text.length() <= 0) {
						continue;
					}
					if (text.length() >= 200) {
						continue;
					}
					
					text = jsoupElement.tagName() + "\t" +jsoupElement.parent().tagName() + "\t" + text;
					
//						System.out.println(text);
					if (result.containsKey(text)) {
						int count = result.get(text);
						count++;
						result.put(text, count);
					} else {
						result.put(text, 1);
					}
				}
			} else {
				throw new RuntimeException("unknown jsoup node type, " + jsoupElement.getClass());
			}
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
