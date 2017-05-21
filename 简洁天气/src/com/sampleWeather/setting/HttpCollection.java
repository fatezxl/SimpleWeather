package com.sampleWeather.setting;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

//����http���ӣ�����json��ʽ����


import android.content.Intent;

public class HttpCollection {
	private  String city_name;
	private  String url;
	private  String json;
	private  String NAME_UTF8;//���е�UTF-8��
	private  String URL_BASE;//"http://op.juhe.cn/onebox/weather/query?cityname=";
	private  String KEY;//"&key=2dc31adf6ac8fa868e7f9f70cdab0a98";//key�������key��
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
	
	//�����߳�������������
		private void netWork( final String url) {
			new Thread(new Runnable() {
				@Override
				public void run() {	
					try {
						//����HttpClient����
						HttpClient myHttpClient=new DefaultHttpClient();
						//����get����
						HttpGet get =new HttpGet(url);
						//ִ��get���󣬵õ���Ӧ���ݰ�
						HttpResponse response= myHttpClient.execute(get);
						//����Ӧ���ݰ��еõ�Enity��Ӧʵ��
						HttpEntity mHttpEntity =response.getEntity();
						//����Ӧʵ���еõ�json��ʽ���ַ���
						json=EntityUtils.toString(mHttpEntity);
						System.out.println(json);
						System.out.println("json���ݽ��ճɹ���");
						status = 200;
					} 
					catch (Exception e) {
						status = 404;
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * �߳�ͨѶִ����UI�߳���
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
