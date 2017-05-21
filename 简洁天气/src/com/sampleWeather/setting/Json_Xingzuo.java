package com.sampleWeather.setting;

import org.json.JSONException;
import org.json.JSONObject;

public class Json_Xingzuo {
	public  String name;//1日期
	public  String all;//2综合指数
	public  String color;//3幸运色
	public  String health;//4健康指数
	public  String love;//5爱情指数
	public  String money;//6财运指数
	public  String number;//7幸运数字
	public  String QFriend;//8速配星座
	public  String summary;//9友情提示
	public  String work;//10工作指数
	
	
	public void parJson_Xingzuo(String json){//解析星座Json数据
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
