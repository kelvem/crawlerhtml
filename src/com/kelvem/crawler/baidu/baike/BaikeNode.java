package com.kelvem.crawler.baidu.baike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvem on 16/3/3.
 */
public class BaikeNode {

    public String text;
    public String style;
    public String name;
    public String id;
    public String value;
    public String tag;


    public BaikeNode parent;
    public List<BaikeNode> children = new ArrayList<BaikeNode>();

    public List<String> parentId = new ArrayList<String>();
    public List<String> parentName = new ArrayList<String>();
    public List<String> parentClass = new ArrayList<String>();
    public List<String> parentStyle = new ArrayList<String>();
    public List<String> parentTag = new ArrayList<String>();
    

}
