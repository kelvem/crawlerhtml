package com.kelvem.crawler.model;

import com.kelvem.common.database.sqlite.ModelHandler;

public class HtmlSourceModel extends SqliteBaseModel {

	public static void main(String[] args) {
		
		ModelHandler.dropTable(HtmlSourceModel.class);
		ModelHandler.createTable(HtmlSourceModel.class);
		
//		HtmlSourceModel h = new HtmlSourceModel();
//		h.setText("''");
//		h.setHtmlId(1);
//		addRecord(h);
	}

	private Integer htmlSourceId = null;
	private Integer tagRuleGroupId = null;
	private String htmlType = null;
	private String url = null;
	private String mainDomain = null;
	private String subDomain = null;
	private String content = null;
	private String text = null;
	
	
	
	public Integer getHtmlSourceId() {
		return htmlSourceId;
	}
	public void setHtmlSourceId(Integer htmlSourceId) {
		this.htmlSourceId = htmlSourceId;
	}
	public Integer getTagRuleGroupId() {
		return tagRuleGroupId;
	}
	public void setTagRuleGroupId(Integer tagRuleGroupId) {
		this.tagRuleGroupId = tagRuleGroupId;
	}
	public String getHtmlType() {
		return htmlType;
	}
	public void setHtmlType(String htmlType) {
		this.htmlType = htmlType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMainDomain() {
		return mainDomain;
	}
	public void setMainDomain(String mainDomain) {
		this.mainDomain = mainDomain;
	}
	public String getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(String subDomain) {
		this.subDomain = subDomain;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
