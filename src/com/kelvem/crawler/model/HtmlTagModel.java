package com.kelvem.crawler.model;

import java.util.Date;

import com.kelvem.common.database.sqlite.ModelHandler;

public class HtmlTagModel extends SqliteBaseModel {

	public static void main(String[] args) {
		
		ModelHandler.dropTable(HtmlTagModel.class);
		ModelHandler.createTable(HtmlTagModel.class);
		
		
		
//		HtmlTagModel m = new HtmlTagModel();
//		m.setCountATag(1);
//		m.setCountButtonTag(2);
//		int c = m.addRecord(m);
//		System.out.println(c);
//		
//		int count = HtmlTagModel.count(m);
//		System.out.println(count);
//		
//		List<HtmlTagModel> list = HtmlTagModel.queryModel(m);
//		System.out.println(list.size());
//		System.out.println(list.get(0));
	}

	private Integer htmlTagId = null;
	private Integer parentTagId = null;
	private Integer level = null;
	private Integer htmlSourceId = null;
	private Integer tagRuleId = null;
	private String tagType = null;
	
	private String tag = null;
	private String parentTag = null;
	private String name;
	private String value;

	private String text = null;
	private String style = null;
	private String clazz = null;

	private Integer countTextTag = null;
	private Integer countATag = null;
	private Integer countButtonTag = null;
	private Integer countInputTag = null;
	private Integer countInnerText = null;
	private Date createTime = null;
	
	
	public Integer getHtmlTagId() {
		return htmlTagId;
	}
	public void setHtmlTagId(Integer htmlTagId) {
		this.htmlTagId = htmlTagId;
	}
	public Integer getHtmlSourceId() {
		return htmlSourceId;
	}
	public void setHtmlSourceId(Integer htmlSourceId) {
		this.htmlSourceId = htmlSourceId;
	}
	public Integer getTagRuleId() {
		return tagRuleId;
	}
	public void setTagRuleId(Integer tagRuleId) {
		this.tagRuleId = tagRuleId;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getParentTag() {
		return parentTag;
	}
	public void setParentTag(String parentTag) {
		this.parentTag = parentTag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public Integer getCountTextTag() {
		return countTextTag;
	}
	public void setCountTextTag(Integer countTextTag) {
		this.countTextTag = countTextTag;
	}
	public Integer getCountATag() {
		return countATag;
	}
	public void setCountATag(Integer countATag) {
		this.countATag = countATag;
	}
	public Integer getCountButtonTag() {
		return countButtonTag;
	}
	public void setCountButtonTag(Integer countButtonTag) {
		this.countButtonTag = countButtonTag;
	}
	public Integer getCountInputTag() {
		return countInputTag;
	}
	public void setCountInputTag(Integer countInputTag) {
		this.countInputTag = countInputTag;
	}
	public Integer getCountInnerText() {
		return countInnerText;
	}
	public void setCountInnerText(Integer countInnerText) {
		this.countInnerText = countInnerText;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getParentTagId() {
		return parentTagId;
	}
	public void setParentTagId(Integer parentTagId) {
		this.parentTagId = parentTagId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}


	
}
