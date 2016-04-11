package com.kelvem.crawler.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

/**
 * Created by kelvem on 16/3/3.
 */
public class HtmlNode2 {

	public HtmlNode2() {
		
	}
	
    public HtmlNode2(Element element) {
		this.jsoupElement = element;
		this.tag = element.tagName();
		this.id = element.id();
		this.name = element.nodeName();
		this.text = element.text();
		this.value = element.val();
		this.style = element.cssSelector();
		this.clazz = element.className();
	}
	
    public HtmlNode2(Element element, HtmlNode2 parentNode) {
		this(element);
		this.parent = parentNode;
	}

    public String tag;
	public String id;
    public String name;
    public String text;
    public String value;
    public String style;
    public String clazz;


    public HtmlNode2 parent = null;
    public List<HtmlNode2> children = new ArrayList<HtmlNode2>();

    public List<String> parentId = new ArrayList<String>();
    public List<String> parentName = new ArrayList<String>();
    public List<String> parentClass = new ArrayList<String>();
    public List<String> parentStyle = new ArrayList<String>();
    public List<String> parentTag = new ArrayList<String>();
    
    public Element jsoupElement = null;

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("HtmlNode [");
        sb.append("tag=").append(tag).append(", ");
    	sb.append("id=").append(id).append(", ");
        sb.append("name=").append(name).append(", ");
        sb.append("text=").append(text).append(", ");
        sb.append("value=").append(value).append(", ");
        sb.append("style=").append(style).append(", ");
        sb.append("class=").append(clazz).append(", ");
        sb.append("parentTag=").append((parent == null) ? null : parent.parentTag);
    	sb.append("]");
    	
    	return sb.toString();
    }
}
