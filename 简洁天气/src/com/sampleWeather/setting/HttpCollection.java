package com.sampleWeather.setting;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//建立http连接，接收json格式数据


import android.content.Intent;

public class HttpCollection {
	private  String city_name;
	private  String url;
	private  String json;
	private  String NAME_UTF8;//城市的UTF-8码
	private  String URL_BASE;//"http://op.juhe.cn/onebox/weather/query?cityname=";
	private  String KEY;//"&key=2dc31adf6ac8fa868e7f9f70cdab0a98";//key是申请的key码
	private  int status = 0;
	
	public HttpCollection(String URL_BASE,String city_name,String KEY){
		this.city_name=city_name;
		this.URL_BASE=URL_BASE;
		this.KEY=KEY;
		NAME_UTF8=URLtoUTF8.toUtf8String(city_name);
		url=URL_BASE+NAME_UTF8+KEY;
	}
	
	public void httpGetData() {
		System.out.println("url:"+url);
	    netWork(url);
	}
	public String getJson() throws Exception{
		while (this.status != 200) {
			if (this.status != 0 && this.status != 200) {
				throw new Exception("http status:"+status);
			}
		}
		return json;
	}
	
	//在子线程中完成网络操作
		private void netWork( final String url) {
			new Thread(new Runnable() {
				@Override
				public void run() {	
					try {
						//生成HttpClient对象
						HttpClient myHttpClient=new DefaultHttpClient();
						//创建get请求
						HttpGet get =new HttpGet(url);
						//执行get请求，得到响应数据包
						HttpResponse response= myHttpClient.execute(get);
						//从响应数据包中得到Enity响应实体
						HttpEntity mHttpEntity =response.getEntity();
						//从响应实体中得到json格式的字符串
						json=EntityUtils.toString(mHttpEntity);
						System.out.println(json);
						System.out.println("json数据接收成功！");
						status = 200;
					} 
					catch (Exception e) {
						status = 404;
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * 线程通讯执行在UI线程中
					 */
/*
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Intent intent = new Intent(this,ShowResults.class);
							intent.putExtra("json", json);
							startActivity(intent);
						}
					});
					*/
				}
			}).start();
			
		}
}
