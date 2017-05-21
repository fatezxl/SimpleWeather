package com.sampleWeather.activity;

import com.zxl.sampleWeather.R;
import com.sampleWeather.setting.HttpCollection;
import com.sampleWeather.setting.Json_Weather;
import com.sampleWeather.setting.Json_Xingzuo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class XingzuoActivity extends Activity {
	private String Xingzuo_URL_BASE = "http://web.juhe.cn:8080/constellation/getAll?type=today&consName=";
	private String Xingzuo_KEY = "&key=8e3d45f51cbaf65bb558fac7aa791c84";
	
	public static int mtab02_content_xingzuo_res = 0; 
	public static LinearLayout mtab02_content_xingzuo;
//获取TextView控件
	private TextView mtv_name;
	private TextView mtv_all;
	private TextView mtv_color;
	private TextView mtv_health;
	private TextView mtv_love;
	private TextView mtv_money;
	private TextView mtv_number;
	private TextView mtv_QFriend;
	private TextView mtv_summary;
	private TextView mtv_work;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab02_content_xingzuo);
		
		mtab02_content_xingzuo=(LinearLayout)findViewById(R.id.tab02_content_xingzuo);
		update_search_background();
		//初始化控件
		mtv_name=(TextView)findViewById(R.id.tv_name);
		mtv_all=(TextView)findViewById(R.id.tv_all);
		mtv_color=(TextView)findViewById(R.id.tv_color);
		mtv_health=(TextView)findViewById(R.id.tv_health);
		mtv_love=(TextView)findViewById(R.id.tv_love);
		mtv_money=(TextView)findViewById(R.id.tv_money);
		mtv_number=(TextView)findViewById(R.id.tv_number);
		mtv_QFriend=(TextView)findViewById(R.id.tv_QFriend);
		mtv_summary=(TextView)findViewById(R.id.tv_summary);
		mtv_work=(TextView)findViewById(R.id.tv_work);
		
		Intent intent = getIntent();
		String content = intent.getStringExtra("Xingzuo");// 接受上一个activity传过来的值
		
		// 建立HttpCollection对象
		HttpCollection hc = new HttpCollection(Xingzuo_URL_BASE, content,Xingzuo_KEY);
		hc.httpGetData();// 建立http连接获取json格式数据
		try {
			// 接收天气json格式数据
			String Xingzuo_information = hc.getJson();
			// 解析json
			Json_Xingzuo json_Xingzuo = new Json_Xingzuo();// 构建解析器
			json_Xingzuo.parJson_Xingzuo(Xingzuo_information);// 解析json数据
			
			// 设置数据
			mtv_name.setText(json_Xingzuo.name);
			
			mtv_all.setText(json_Xingzuo.all);
			mtv_color.setText(json_Xingzuo.color);
			mtv_health.setText(json_Xingzuo.health);
			mtv_love.setText(json_Xingzuo.love);
			mtv_money.setText(json_Xingzuo.money);
			mtv_number.setText(json_Xingzuo.number);
			mtv_QFriend.setText(json_Xingzuo.QFriend);
			mtv_summary.setText(json_Xingzuo.summary);
			mtv_work.setText(json_Xingzuo.work);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void return_onclick(View view) {
		XingzuoActivity.this.finish();
	}
	private void update_search_background()
	{
		if (mtab02_content_xingzuo_res != 0) {
			mtab02_content_xingzuo.setBackgroundResource(mtab02_content_xingzuo_res);
		}
	}
}
