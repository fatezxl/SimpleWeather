package com.sampleWeather.setting;

import org.json.JSONException;
import org.json.JSONObject;

public class Json_Xingzuo {
	public  String name;//1����
	public  String all;//2�ۺ�ָ��
	public  String color;//3����ɫ
	public  String health;//4����ָ��
	public  String love;//5����ָ��
	public  String money;//6����ָ��
	public  String number;//7��������
	public  String QFriend;//8��������
	public  String summary;//9������ʾ
	public  String work;//10����ָ��
	
	
	public void parJson_Xingzuo(String json){//��������Json����
		try {
			JSONObject jsonObject =new JSONObject(json);
				if(jsonObject.getInt("error_code")==0){
				name = jsonObject.getString("name");
				all= jsonObject.getString("all");
				color= jsonObject.getString("color");
				health= jsonObject.getString("health");
				love= jsonObject.getString("love");
				money= jsonObject.getString("money");
				number= jsonObject.getString("number");
				QFriend= jsonObject.getString("QFriend");
				summary= jsonObject.getString("summary");
				work= jsonObject.getString("work");
			}else{
				System.out.println(jsonObject.get("error_code")+":"+jsonObject.get("reason"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
