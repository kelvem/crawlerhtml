package com.kelvem.crawler.baidu.baike;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kelvem.common.FileUtils;


/**
 * Created by kelvem on 16/3/3.
 */
public class BaikeParser {

    public static void main(String[] args) throws Exception {

        String filePath = "./html1/1.htm";
        Document doc = Jsoup.parse(new File(filePath), "UTF-8");
        
//        Element body = doc.body();
//        System.out.println(body.html());

        Elements h1s = doc.getElementsByTag("h1");
        for (Element h1 : h1s) {
            // style="background-color: #333333"
            String style = h1.attr("style");
            style += "background-color: grey;";
            h1.attr("style", style);
            
//            System.out.println(h1.html());
        }

        Elements h2s = doc.getElementsByTag("h2");
        for (Element h2 : h2s) {
            // style="background-color: #333333"
            String style = h2.attr("style");
            style += "background-color: grey;";
            h2.attr("style", style);
            
//            System.out.println(h2.html());
        }

        Elements h3s = doc.getElementsByTag("h3");
        for (Element h3 : h3s) {
            // style="background-color: #333333"
            String style = h3.attr("style");
            style += "background-color: grey;";
            h3.attr("style", style);
            
//            System.out.println(h3.html());
        }

        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            // style="background-color: #333333"
            img.remove();
        }

        Elements elements = doc.getElementsByClass("single-video-module");
        for (Element e : elements) {
            e.remove();
        }

        elements = doc.getElementsByClass("navbar-wrapper");
        for (Element e : elements) {
            e.remove();
        }

        elements = doc.getElementsByClass("header-wrapper");
        for (Element e : elements) {
            e.remove();
        }

        elements = doc.getElementsByClass("top-tool");
        for (Element e : elements) {
            e.remove();
        }
        
        elements = doc.getElementsByClass("side-content");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("wgt-footer-main");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("lemmaWgt-searchHeader");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("bdsharebuttonbox");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("after-content");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("lemma-album");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByClass("lemma-picture");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByTag("sup");
        for (Element e : elements) {
        	e.remove();
        }
        
        elements = doc.getElementsByTag("a");
        for (Element e : elements) {
        	if (!e.attr("target").equals("_blank")) {
				continue;
			}
        	if (!e.attr("href").endsWith("htm")) {
				continue;
			}
//        	System.out.println(e.outerHtml());
        }
        
        elements = doc.getElementsByTag("div");
        for (Element e : elements) {
        	if (e.getElementsByTag("div").size() > 1) {
        		System.out.println("div > 0");
				continue;
			}
        	if (e.text().length() <= 10) {
				continue;
			}
//        	System.out.println(e.outerHtml());
//        	System.out.println(e.text());

            // style="background-color: #333333"
            String style = e.attr("style");
            style += "background-color: yellow;";
            e.attr("style", style);
        }
        
        Elements tags = doc.getAllElements();
        
        
        
        
        String newHtml = doc.html();
//        System.out.println(newHtml);

        FileUtils.writeFile("./html1/new.1.htm", newHtml);


        System.out.println("End");

    }
}
