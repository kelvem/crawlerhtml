package com.kelvem.crawler.model;

import com.kelvem.common.database.sqlite.ModelHandler;

public class TagRuleModel extends SqliteBaseModel {

	public static void main(String[] args) {

		ModelHandler.dropTable(TagRuleModel.class);
		ModelHandler.createTable(TagRuleModel.class);
		
//		TagRuleModel.addRecord(new TagRuleModel());
	}

	public TagRuleModel() {

	}
	

	private Integer tagRuleId = null;
	private Integer tagRuleGroupId = null;
	private String tagRuleType = null;
	private String mainDomain = null;
	private String subDomain = null;
	private String tag = null;
	private String parentTag = null;
	private String text = null;
	private String style = null;
	private String clazz = null;
	private Integer percent = null;
	
	
	
	
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getTagRuleId() {
		return tagRuleId;
	}

	public void setTagRuleId(Integer tagRuleId) {
		this.tagRuleId = tagRuleId;
	}

	public Integer getTagRuleGroupId() {
		return tagRuleGroupId;
	}

	public void setTagRuleGroupId(Integer tagRuleGroupId) {
		this.tagRuleGroupId = tagRuleGroupId;
	}

	public String getTagRuleType() {
		return tagRuleType;
	}

	public void setTagRuleType(String tagRuleType) {
		this.tagRuleType = tagRuleType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getParentTag() {
		return parentTag;
	}

	public void setParentTag(String parentTag) {
		this.parentTag = parentTag;
	}

}
