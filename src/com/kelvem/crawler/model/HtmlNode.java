package com.kelvem.crawler.model;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

/**
 * Created by kelvem on 16/3/3.
 */
public class HtmlNode {

	public HtmlNode() {

	}

	public HtmlNode(Element element) {
		this.jsoupElement = element;

		this.tag = element.tagName();
		this.id = element.id();
		this.name = element.nodeName();
//		this.text = element.text();
		this.value = element.val();
		this.clazz = element.className();
		
		try {
			this.style = element.cssSelector();
		} catch (Exception e) {
//			System.err.println(this.jsoupElement.outerHtml());
//			System.out.println("------------------------");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		}
	}

	public HtmlNode(Element element, HtmlNode parentNode) {
		this(element);
		this.parent = parentNode;
		this.level = parentNode.getLevel() + 1;
	}

	private Element jsoupElement = null;
	
	private int level = 0;
	private String tag;
	private String id;
	private String name;
	private String text = "";
	private String value;
	private String style;
	private String clazz;

	private HtmlNode parent = null;
	private List<HtmlNode> children = new ArrayList<HtmlNode>();


	
	public void addChild(HtmlNode node) {
		this.getChildren().add(node);
	}

	public void appendText(String text) {
		this.text += text.replace("&nbsp;", "\t");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append("HtmlNode [");
//		sb.append("tag=").append(tag).append(", ");
//		sb.append("id=").append(id).append(", ");
//		sb.append("name=").append(name).append(", ");
//		// sb.append("text=").append(text).append(", ");
//		sb.append("value=").append(value).append(", ");
//		sb.append("style=").append(style).append(", ");
//		sb.append("class=").append(clazz).append(", ");
//		sb.append("parentTag=").append((parent == null) ? null : parent.getStrParentTag());
//		sb.append("]");

		sb.append("[").append(this.tag).append("]").append("\t")
			.append(this.getStrParentTag() + "\t")
			.append(this.getStrParentClass() + "\t")
			.append(this.getStyle() + "\t")
			.append("a" + this.getCountATag() + "\t")
			.append("t" + this.getCountTextTag() + "\t")
			.append("i" + this.getCountInnerText() + "\t")
	//		.append(this.text.replace("&nbsp;", "\t")).append("\r\n");
			.append(this.getInnerText().length() > 50 ? this.getInnerText().substring(0, 50) : this.getInnerText());
		
		return sb.toString();
	}

	public String toTree() {
		return toTree(this, 0);
	}

	private String toTree(HtmlNode node, int level) {

		String tab = "";
		for (int i = 0; i < level; i++) {
			tab += "  ";
		}
		
		if (level > 6) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		if (node.tag.equals("div")) {
			sb.append(tab).append(node.toString()).append("\r\n");
		}
		
		for (HtmlNode childNode : node.getChildren()) {
			sb.append(toTree(childNode, level + 1));
		}
		
		return sb.toString();
	}

	public String getTag() {
		return tag;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		text = text.replace("&nbsp;", " ");
		
		if (text.startsWith("\r\n")) {
			text = text.substring(2);
		}
		if (text.startsWith("\n")) {
			text = text.substring(1);
		}
		
		text = text.trim();
		
		return text;
	}

	public String getValue() {
		return value;
	}

	public String getStyle() {
		return style;
	}

	public String getClazz() {
		return clazz;
	}

	public HtmlNode getParent() {
		return parent;
	}

	public List<HtmlNode> getChildren() {
		return children;
	}

	public List<String> getParentId() {
		return parentId;
	}

	public List<String> getParentName() {
		return parentName;
	}

	public List<String> getParentClass() {
		return parentClass;
	}

	public List<String> getParentStyle() {
		return parentStyle;
	}

	public List<String> getParentTag() {
		return parentTag;
	}

	public Element getJsoupElement() {
		return jsoupElement;
	}

	public int getLevel() {
		return level;
	}
	
	
	private Integer countTextTag = null;
	private Integer countATag = null;
	private Integer countButtonTag = null;
	private Integer countInputTag = null;
	private Integer countInnerText = null;
	private String innerText = null;

	public Integer getCountTextTag() {
		if (countTextTag == null) {
			countTextTag = 0;
			if (this.text.trim().length() > 0) {
				countTextTag++;
			}
			for (HtmlNode childNode : children) {
				countTextTag += childNode.getCountTextTag();
			}
		}
		return countTextTag;
	}

	public Integer getCountATag() {
		if (countATag == null) {
			countATag = 0;
			if ("a".equalsIgnoreCase(tag)) {
				countATag++;
			}
			for (HtmlNode childNode : children) {
				countATag += childNode.getCountATag();
			}
		}
		return countATag;
	}

	public Integer getCountButtonTag() {
		if (countButtonTag == null) {
			countButtonTag = 0;
			if ("button".equalsIgnoreCase(tag)) {
				countButtonTag++;
			}
			for (HtmlNode childNode : children) {
				countButtonTag += childNode.getCountButtonTag();
			}
		}
		return countButtonTag;
	}

	public Integer getCountInputTag() {
		if (countInputTag == null) {
			countInputTag = 0;
			if ("input".equalsIgnoreCase(tag)) {
				countInputTag++;
			}
			for (HtmlNode childNode : children) {
				countInputTag += childNode.getCountInputTag();
			}
		}
		return countInputTag;
	}

	public String getInnerText() {
		if (innerText == null) {
			innerText = "";
			for (HtmlNode childNode : children) {
				innerText += childNode.getInnerText();
			}
			innerText += text;
		}
		return innerText;
	}

	public Integer getCountInnerText() {
		if (countInnerText == null) {
			countInnerText = getInnerText().length();
		}
		return countInnerText;
	}


	private List<String> parentId = null;
	private List<String> parentName = null;
	private List<String> parentClass = null;
	private List<String> parentStyle = null;
	private List<String> parentTag = null;


	public String getStrParentId() {

		StringBuilder sb = new StringBuilder();
		HtmlNode buf = this;
		do {
			sb.insert(0, "|").insert(0, buf.id);
			buf = buf.parent;
		} while (buf != null);
		
		return sb.toString();
	}

	private Object getStrParentTag() {

		StringBuilder sb = new StringBuilder();
		HtmlNode buf = this;
		do {
			sb.insert(0, "|").insert(0, buf.tag);
			buf = buf.parent;
		} while (buf != null);
		
		return sb.toString();
	}

	private Object getStrParentStyle() {

		StringBuilder sb = new StringBuilder();
		HtmlNode buf = this;
		do {
			sb.insert(0, "|").insert(0, buf.style);
			buf = buf.parent;
		} while (buf != null);
		
		return sb.toString();
	}

	private Object getStrParentClass() {

		StringBuilder sb = new StringBuilder();
		HtmlNode buf = this;
		do {
			sb.insert(0, "|").insert(0, buf.clazz);
			buf = buf.parent;
		} while (buf != null);
		
		return sb.toString();
	}
	
	
	
	
}
