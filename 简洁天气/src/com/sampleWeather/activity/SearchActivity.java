package com.sampleWeather.activity;

import com.zxl.sampleWeather.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchActivity extends Activity{
	private TextView mtv_OK_search;
	private EditText et_search;
	public static LinearLayout mtab01_search;
	public static int mtab01_search_res = 0; 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab01_content_search);
		//切换壁纸
		mtab01_search=(LinearLayout)findViewById(R.id.layout_tab01_search);
		update_search_background();
		
		et_search = (EditText) findViewById(R.id.et_enterCity_search);
		mtv_OK_search=(TextView)findViewById(R.id.tv_OK_search);
		mtv_OK_search.setOnClickListener(new View.OnClickListener() {  
		    public void onClick(View v) {  
//TODO
		    	Intent intent = new Intent(SearchActivity.this,MainActivity.class);
		    	//intent.putExtra("city_name",m);
		    	MainActivity.city_name=et_search.getText().toString();
		    	startActivity(intent);
		    }  
		});  
	}
	
	protected void onRestart(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab01_content_search);
		update_search_background();
	}
	
	private void update_search_background()
	{
		if (mtab01_search_res != 0) {
			mtab01_search.setBackgroundResource(mtab01_search_res);
		}
	}
	public void return_onclick_searchActivity(View view) {
		SearchActivity.this.finish();
	}
	 public void hotcity_onclick(View arg0) {
		    String hot_City;
		    switch (arg0.getId()) {
			case R.id.tv_hotCity_xian:
				hot_City="西安";break;
			case R.id.tv_hotCity_shanghai:
				hot_City="上海";break;
			case R.id.tv_hotCity_beijing:
				hot_City="北京";break;
			case R.id.tv_hotCity_shenzhen:
				hot_City="深圳";break;
			case R.id.tv_hotCity_nanjing:
				hot_City="南京";break;
			case R.id.tv_hotCity_suizhou:
				hot_City="苏州";break;
			default:
				hot_City="西安";
				break;
			}
	    	//Intent intent = new Intent(SearchActivity.this,MainActivity.class);
		    //Log.i("点击的是",hot_City);
	    	et_search.setText(hot_City);
	    	//MainActivity.city_name=hot_City;
	    	MainActivity.mTv_city.setText(hot_City);
	    	Log.i("此时",MainActivity.city_name);
	    	SearchActivity.this.finish();
	    	//startActivity(intent);
	    }  

}
