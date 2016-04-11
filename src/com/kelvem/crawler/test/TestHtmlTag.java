package com.kelvem.crawler.test;

import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.kelvem.common.DateUtils;
import com.kelvem.common.database.sqlite.ModelHandler;
import com.kelvem.crawler.html.HtmlParser;
import com.kelvem.crawler.model.HtmlNode;
import com.kelvem.crawler.model.HtmlSourceModel;
import com.kelvem.crawler.model.HtmlTagModel;

public class TestHtmlTag {

	public static void main(String[] args) {


		ModelHandler.dropTable(HtmlTagModel.class);
		ModelHandler.createTable(HtmlTagModel.class);
		
		HtmlSourceModel htmlQuery = new HtmlSourceModel();
		htmlQuery.setMainDomain("baidu.com");
		htmlQuery.setSubDomain("baike");
		
		List<HtmlSourceModel> htmlList = HtmlSourceModel.queryModel(htmlQuery);
		
		int p = 0;
		for (HtmlSourceModel source : htmlList) {
			String content = source.getContent();
			
			Document doc = Jsoup.parse(content);
			Element body = doc.body();

			System.out.println(DateUtils.getDateTimeString(new Date()));
			HtmlParser htmlParser = new HtmlParser();
			HtmlNode html = htmlParser.parse(body); 

			System.out.println(DateUtils.getDateTimeString(new Date()));
			htmlParser.filter(html);

			System.out.println(DateUtils.getDateTimeString(new Date()));
			saveTag(html, source.getHtmlSourceId(), 0);
			

			System.out.println(DateUtils.getDateTimeString(new Date()));
			if (p % 10 == 0) {
				System.out.println(p + "\t" + DateUtils.getDateTimeString(new Date()));
			}
			
			
			p++;
			break;
		}
		
	}

	private static void saveTag(HtmlNode html, Integer htmlSourceId, Integer parentTagId) {
		HtmlTagModel tag = new HtmlTagModel();
		tag.setHtmlSourceId(htmlSourceId);
		tag.setParentTagId(parentTagId);
		
		tag.setClazz(html.getClazz());
		tag.setCountATag(html.getCountATag());
		tag.setCountButtonTag(html.getCountButtonTag());
		tag.setCountInnerText(html.getCountInnerText());
		tag.setCountInputTag(html.getCountInputTag());
		tag.setCountTextTag(tag.getCountTextTag());
		tag.setName(html.getName());
		tag.setStyle(html.getStyle());
		tag.setTag(html.getTag());
		tag.setText(html.getText());
		tag.setValue(html.getValue());
		tag.setLevel(html.getLevel());
		
		if (html.getParent() != null) {
			tag.setParentTag(html.getParent().getTag());
		}
		
		// save
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 100; j++) {
				Integer htmlTagId = HtmlTagModel.addRecord(tag);
			}
			System.out.println(i + "\t" + DateUtils.getDateTimeString(new Date()));
			
		}
		
//		for (HtmlNode subNode : html.getChildren()) {
//			saveTag(subNode, htmlSourceId, htmlTagId);
//		}
		
	}

}
