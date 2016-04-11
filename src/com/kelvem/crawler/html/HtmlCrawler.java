package com.kelvem.crawler.html;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kelvem.common.CrawlerUtil;
import com.kelvem.common.FileUtils;
import com.kelvem.crawler.model.HtmlSourceModel;

/**
 * Created by kelvem on 16/2/15.
 */
public class HtmlCrawler {

    public static void main(String[] args) throws IOException {

//    	for (int i = 1000000; i< 1500000; i++) {
//            crawlerBaike(i);
//        }
    	
    	for (int i = 1; i < 100000; i++) {
    		String url = "http://baike.baidu.com/view/" + i + ".htm";
    		HtmlSourceModel html = new HtmlSourceModel();
    		html.setMainDomain("baidu.com");
    		html.setSubDomain("baike");
    		html.setUrl(url);
    		String content = FileUtils.readFile("D:\\html_source\\html\\" + i + ".htm");
//    		html.setContent("'\"\\/[]()");
    		html.setContent(content);
    		
    		HtmlSourceModel.addRecord(html);
		}
    	
    }

    public static void crawlerBaike(int id) throws IOException {

        String url = "http://baike.baidu.com/view/" + id + ".htm";
        String fileName = "html" + (int)(id / 100000) + "00000/" + id + ".htm";

        String html = CrawlerUtil.get(url);
        FileUtils.writeFile(fileName, html);
        
    }

    public static void recognise(int id) {
        String fileName = "html1/" + id + ".htm";
        String content = readFile(fileName);

        System.out.println(content);

        // <h1 >百度百科</h1>
        try {
            String regex = "<h[0-9][^>]*>[\\w|\\W]*?</h[0-9][^>]*>";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(content);

            while (m.find()) {
                System.out.println(m.group());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String regex = "(<div class=\"header-wrapper\">[\\w|\\W]*)<div class=\"body-wrapper\">";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(content);


            System.out.println(m.find());
            System.out.println(m.group(1));
            content = m.replaceAll("");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 做些什么呢  spark    学习新知识   概率论 贝叶斯 发现内存溢出  事前预测    事后分析    内存模型
        // 姜宁 开源的工具
        String targetFileName = "html1/r." + id + ".htm";
        writeile(targetFileName, content);
    }



    public static String readFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static void writeile(String fileName, String content) {
        try {

            File file = new File(fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

            System.out.println("Write File Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
