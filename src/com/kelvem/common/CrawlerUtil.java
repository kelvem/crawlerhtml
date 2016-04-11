package com.kelvem.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.client.CircularRedirectException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class CrawlerUtil {

    private static final String USER_AGENT = "Mozilla/5.0 Firefox/26.0";

    private static final int TIMEOUT_SECONDS = 120;

    private static final int POOL_SIZE = 120;
    
    private static final int RETRY_COUNT = 5;
    
    
	private CrawlerUtil() {

	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
    public static String get(String url) {

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(TIMEOUT_SECONDS * 1000)
                .setConnectTimeout(TIMEOUT_SECONDS * 1000)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setUserAgent(USER_AGENT)
                .setMaxConnTotal(POOL_SIZE)
                .setMaxConnPerRoute(POOL_SIZE)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();

        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Referer", url);

//        System.out.println("executing request " + httpget.getURI());

    	for (int r = 0; r < RETRY_COUNT; r++) {
    		CloseableHttpResponse response = null;
        	try {
        		response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();

                if (response.getStatusLine().getStatusCode() >= 400) {
                    System.out.println("Got bad response, error code = " + response.getStatusLine().getStatusCode() + " imageUrl: " + url);
                } else {
                    if (entity != null) {
                        InputStream is = entity.getContent();
                        
                        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
                        int i=-1; 
                        while((i=is.read())!=-1){ 
                        	baos.write(i); 
                        } 
                        String html = baos.toString(); 
                        return html;
                    }
                }
			} catch (HttpHostConnectException e) {
				// do nothing
			} catch (SocketTimeoutException e) {
				// do nothing
			} catch (ClientProtocolException e) {
				// do nothing
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					try {
						response.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	        }
		}

        return "";
    }
    
    
    
}
