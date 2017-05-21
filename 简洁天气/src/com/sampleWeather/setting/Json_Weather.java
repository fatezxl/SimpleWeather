package com.sampleWeather.setting;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.content.Intent;

public class Json_Weather {

	/**
	 * 1������
	 * 2����ʱ��
	 * 3ʵʱ�¶ȣ�23��
	 * 4������������ƣ��磬����
	 * 56û����ߺ�����¶ȣ��а����ҹ���¶�
	 * 789 pm25���ȼ����������������飩
	 * 
	 * ab�ճ�������
	 * cd�磺�����ͷ���
	 */
	public  String city;//1������
	public  String datetime;//2����ʱ��
	public  String curTemperature;//3ʵʱ�¶ȣ�23��
	public  String weather;//4������������ƣ��磬����
	public  String MinTemperature;//5����¶�/ҹ���¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
	public  String MaxTemperature;//6����¶�/�����¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
	public  String pm25;//7 pm25ֵ
	public  String pm25airQuality;//8 pm25��������
	public  String pm25Suggestion;//9 pm25����
	public  String dressGuide;//10����ָ��
	public  String sunriseTime;//a�ճ�ʱ��
	public  String sunsetTime;//b����ʱ��
	public  String windForce;//c��������
	public  String windDirection;//d����
	
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

	//����json
	public void parJson(String json) {//��������Json����
		try {
			JSONObject jsonObject =new JSONObject(json);
			
			//if(jsonObject.getString("resultcode").equals("200")){
			
				if(jsonObject.getInt("error_code")==0){
				//���ڵ�
				JSONObject jsonObject_result_data= jsonObject.getJSONObject("result").getJSONObject("data");
				//���������
				JSONObject jsonObject_result_data_realtime=jsonObject_result_data.getJSONObject("realtime");
				//�����
				JSONObject jsonObject_result_data_life=jsonObject_result_data.getJSONObject("life");
				
				//δ��һ�ܵ����������飩
				//�����죨���죩���������
				JSONObject jsonObject_result_data_realtime_today0=jsonObject_result_data.getJSONArray("weather").getJSONObject(0);
				//��һ����������
				JSONObject jsonObject_result_data_realtime_today1=jsonObject_result_data.getJSONArray("weather").getJSONObject(1);
				//�ڶ�����������
				JSONObject jsonObject_result_data_realtime_today2=jsonObject_result_data.getJSONArray("weather").getJSONObject(2);
				//��������������
				JSONObject jsonObject_result_data_realtime_today3=jsonObject_result_data.getJSONArray("weather").getJSONObject(3);
				//��������������
				JSONObject jsonObject_result_data_realtime_today4=jsonObject_result_data.getJSONArray("weather").getJSONObject(4);
				//��������������
				JSONObject jsonObject_result_data_realtime_today5=jsonObject_result_data.getJSONArray("weather").getJSONObject(5);
				//��������������
				JSONObject jsonObject_result_data_realtime_today6=jsonObject_result_data.getJSONArray("weather").getJSONObject(6);
				
				//PM25���
				JSONObject jsonObject_result_data_PM25=jsonObject_result_data.getJSONObject("pm25");
				
				//��Ϣ�����洢
				
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
				
				
				
				city=jsonObject_result_data_realtime.getString("city_name");//1������
				datetime=jsonObject_result_data_realtime.getString("time");//2����ʱ��
				curTemperature=jsonObject_result_data_realtime.getJSONObject("weather")
						.getString("temperature");//3ʵʱ�¶ȣ�23��
				weather=jsonObject_result_data_realtime.getJSONObject("weather").getString("info");//4������������ƣ��磬����
				MinTemperature=jsonObject_result_data_realtime_today0.getJSONObject("info")
						.getJSONArray("night").get(2).toString();//5����¶�/ҹ���¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
				MaxTemperature=jsonObject_result_data_realtime_today0.getJSONObject("info")
						.getJSONArray("day").get(2).toString();//6����¶�/�����¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
				pm25=jsonObject_result_data_PM25.getJSONObject("pm25").getString("curPm");//7 pm25ֵ
				pm25airQuality=jsonObject_result_data_PM25.getJSONObject("pm25").getString("quality");//8 pm25��������
				pm25Suggestion=jsonObject_result_data_PM25.getJSONObject("pm25").getString("des");//9 pm25����
				dressGuide=jsonObject_result_data_life.getJSONObject("info").getJSONArray("chuanyi").get(1).toString();//10����ָ��
				sunriseTime=jsonObject_result_data_realtime_today0.getJSONObject("info").getJSONArray("day").get(5).toString();//a�ճ�ʱ��
				sunsetTime=jsonObject_result_data_realtime_today0.getJSONObject("info").getJSONArray("night").get(5).toString();//b����ʱ��
				windForce=jsonObject_result_data_realtime.getJSONObject("wind").getString("power");//c��������
				windDirection=jsonObject_result_data_realtime.getJSONObject("wind").getString("direct");//d����	
			}else{
				System.out.println(jsonObject.get("error_code")+":"+jsonObject.get("reason"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}	
}






