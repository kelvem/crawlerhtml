package com.kelvem.crawler.html;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.ParseConversionEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.kelvem.crawler.model.HtmlNode;

/**
 * Created by kelvem on 16/3/3.
 */
public class HtmlParser {

	public static void main(String[] args) throws Exception {

//		String filePath = "./html1/1.htm";
		String filePath = "./html1/sohu.htm";
		HtmlParser htmlParser = new HtmlParser();
		
		HtmlNode root = htmlParser.parse(filePath);
		htmlParser.filter(root);
		
		System.out.println(root.toTree());

		System.out.println("End");
	}

	public HtmlNode parse(String filePath) {

		try {
			Document doc = Jsoup.parse(new File(filePath), "UTF-8");

			return parse(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HtmlNode parse(Element jsoupElement) {

		HtmlNode root = new HtmlNode(jsoupElement);
		parse(jsoupElement, root);

		System.out.println("====================================================== " + x);
		return root;
	}

	static int x = 0;
	private void parse(Element jsoupElement, HtmlNode htmlNode) {
x++;
		int size = jsoupElement.childNodeSize();
		for (int i = 0; i < size; i++) {

			if (jsoupElement.childNode(i) instanceof Element) {
				Element e = (Element) jsoupElement.childNode(i);
				HtmlNode node = new HtmlNode(e, htmlNode);
				
				// 钻取节点
				parse(e, node);
				
				// 连接tag
				htmlNode.addChild(node);
				
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
					
					if (text.trim().length() > 0) {
//						System.out.println(text);
						htmlNode.appendText(text);
					}
				} else if (jsoupElement.childNode(i).nodeName().equals("#text")) {
					// 连接text
					String text = ((Element)jsoupElement).text();
					text = text.replace("&nbsp;", " ");
					if (text.startsWith("\r\n")) {
						text = text.substring(2);
					}
					if (text.startsWith("\n")) {
						text = text.substring(1);
					}
					
					if (text.trim().length() > 0) {
//						System.out.println(text);
						htmlNode.appendText(text);
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
