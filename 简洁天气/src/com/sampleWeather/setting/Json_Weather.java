package com.sampleWeather.setting;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.content.Intent;

public class Json_Weather {

	/**
	 * 1城市名
	 * 2发布时间
	 * 3实时温度：23°
	 * 4天气情况：多云，晴，下雨
	 * 56没有最高和最低温度，有白天和夜间温度
	 * 789 pm25（等级，空气质量，建议）
	 * 
	 * ab日出和日落
	 * cd风：风力和风向
	 */
	public  String city;//1城市名
	public  String datetime;//2发布时间
	public  String curTemperature;//3实时温度：23°
	public  String weather;//4天气情况：多云，晴，下雨
	public  String MinTemperature;//5最低温度/夜间温度(没有最高和最低温度，有白天和夜间温度)
	public  String MaxTemperature;//6最高温度/白天温度(没有最高和最低温度，有白天和夜间温度)
	public  String pm25;//7 pm25值
	public  String pm25airQuality;//8 pm25空气质量
	public  String pm25Suggestion;//9 pm25建议
	public  String dressGuide;//10穿衣指导
	public  String sunriseTime;//a日出时间
	public  String sunsetTime;//b日落时间
	public  String windForce;//c风力级数
	public  String windDirection;//d风向
	
	public String date1;
	public String date2;
	public String date3;
	public String date4;
	public String date5;
	public String date6;
	public String weather1;
	public String weather2;
	public String weather3;
	public String weather4;
	public String weather5;
	public String weather6;
	public String temper1;
	public String temper2;
	public String temper3;
	public String temper4;
	public String temper5;
	public String temper6;

	//解析json
	public void parJson(String json) {//解析天气Json数据
		try {
			JSONObject jsonObject =new JSONObject(json);
			
			//if(jsonObject.getString("resultcode").equals("200")){
			
				if(jsonObject.getInt("error_code")==0){
				//主节点
				JSONObject jsonObject_result_data= jsonObject.getJSONObject("result").getJSONObject("data");
				//今天的天气
				JSONObject jsonObject_result_data_realtime=jsonObject_result_data.getJSONObject("realtime");
				//生活建议
				JSONObject jsonObject_result_data_life=jsonObject_result_data.getJSONObject("life");
				
				//未来一周的天气（数组）
				//第零天（今天）的天气情况
				JSONObject jsonObject_result_data_realtime_today0=jsonObject_result_data.getJSONArray("weather").getJSONObject(0);
				//第一天的天气情况
				JSONObject jsonObject_result_data_realtime_today1=jsonObject_result_data.getJSONArray("weather").getJSONObject(1);
				//第二天的天气情况
				JSONObject jsonObject_result_data_realtime_today2=jsonObject_result_data.getJSONArray("weather").getJSONObject(2);
				//第三天的天气情况
				JSONObject jsonObject_result_data_realtime_today3=jsonObject_result_data.getJSONArray("weather").getJSONObject(3);
				//第四天的天气情况
				JSONObject jsonObject_result_data_realtime_today4=jsonObject_result_data.getJSONArray("weather").getJSONObject(4);
				//第五天的天气情况
				JSONObject jsonObject_result_data_realtime_today5=jsonObject_result_data.getJSONArray("weather").getJSONObject(5);
				//第六天的天气情况
				JSONObject jsonObject_result_data_realtime_today6=jsonObject_result_data.getJSONArray("weather").getJSONObject(6);
				
				//PM25情况
				JSONObject jsonObject_result_data_PM25=jsonObject_result_data.getJSONObject("pm25");
				
				//信息单独存储
				
				date1=jsonObject_result_data_realtime_today1.getString("week");
				date2=jsonObject_result_data_realtime_today2.getString("week");
				date3=jsonObject_result_data_realtime_today3.getString("week");
				date4=jsonObject_result_data_realtime_today4.getString("week");
				date5=jsonObject_result_data_realtime_today5.getString("week");
				date6=jsonObject_result_data_realtime_today6.getString("week");
				weather1=jsonObject_result_data_realtime_today1.getJSONObject("info").getJSONArray("day").get(1).toString();
				weather2=jsonObject_result_data_realtime_today2.getJSONObject("info").getJSONArray("day").get(1).toString();
				weather3=jsonObject_result_data_realtime_today3.getJSONObject("info").getJSONArray("day").get(1).toString();
				weather4=jsonObject_result_data_realtime_today4.getJSONObject("info").getJSONArray("day").get(1).toString();
				weather5=jsonObject_result_data_realtime_today5.getJSONObject("info").getJSONArray("day").get(1).toString();
				weather6=jsonObject_result_data_realtime_today6.getJSONObject("info").getJSONArray("day").get(1).toString();
				temper1=jsonObject_result_data_realtime_today1.getJSONObject("info").getJSONArray("day").get(2).toString();
				temper2=jsonObject_result_data_realtime_today2.getJSONObject("info").getJSONArray("day").get(2).toString();
				temper3=jsonObject_result_data_realtime_today3.getJSONObject("info").getJSONArray("day").get(2).toString();
				temper4=jsonObject_result_data_realtime_today4.getJSONObject("info").getJSONArray("day").get(2).toString();
				temper5=jsonObject_result_data_realtime_today5.getJSONObject("info").getJSONArray("day").get(2).toString();
				temper6=jsonObject_result_data_realtime_today6.getJSONObject("info").getJSONArray("day").get(2).toString();
				
				
				
				city=jsonObject_result_data_realtime.getString("city_name");//1城市名
				datetime=jsonObject_result_data_realtime.getString("time");//2发布时间
				curTemperature=jsonObject_result_data_realtime.getJSONObject("weather")
						.getString("temperature");//3实时温度：23°
				weather=jsonObject_result_data_realtime.getJSONObject("weather").getString("info");//4天气情况：多云，晴，下雨
				MinTemperature=jsonObject_result_data_realtime_today0.getJSONObject("info")
						.getJSONArray("night").get(2).toString();//5最低温度/夜间温度(没有最高和最低温度，有白天和夜间温度)
				MaxTemperature=jsonObject_result_data_realtime_today0.getJSONObject("info")
						.getJSONArray("day").get(2).toString();//6最高温度/白天温度(没有最高和最低温度，有白天和夜间温度)
				pm25=jsonObject_result_data_PM25.getJSONObject("pm25").getString("curPm");//7 pm25值
				pm25airQuality=jsonObject_result_data_PM25.getJSONObject("pm25").getString("quality");//8 pm25空气质量
				pm25Suggestion=jsonObject_result_data_PM25.getJSONObject("pm25").getString("des");//9 pm25建议
				dressGuide=jsonObject_result_data_life.getJSONObject("info").getJSONArray("chuanyi").get(1).toString();//10穿衣指导
				sunriseTime=jsonObject_result_data_realtime_today0.getJSONObject("info").getJSONArray("day").get(5).toString();//a日出时间
				sunsetTime=jsonObject_result_data_realtime_today0.getJSONObject("info").getJSONArray("night").get(5).toString();//b日落时间
				windForce=jsonObject_result_data_realtime.getJSONObject("wind").getString("power");//c风力级数
				windDirection=jsonObject_result_data_realtime.getJSONObject("wind").getString("direct");//d风向	
			}else{
				System.out.println(jsonObject.get("error_code")+":"+jsonObject.get("reason"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}	
}






