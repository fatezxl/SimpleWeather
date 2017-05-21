package com.sampleWeather.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.zxl.sampleWeather.R;
import com.sampleWeather.setting.HttpCollection;
import com.sampleWeather.setting.Json_Weather;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private static String Weather_URL_BASE="http://op.juhe.cn/onebox/weather/query?cityname=";
	private static String Weather_KEY="&key=2dc31adf6ac8fa868e7f9f70cdab0a98";//key是申请的key码
	public static String city_name = "西安";//

	private HttpCollection hc;// httpCollection对象
	
	public static ImageView mimg_weather;
	private TextView mtv_username;
	public static ImageView mImg_user_image;
	// 初始化天气TextView控件
	public static TextView mTv_city;// 1标题城市
	private TextView mTv_datetime;// 2发布时间
	private TextView mTv_temperature;// 3实时温度
	private TextView mTv_weather;// 4天气情况：多云，晴，下雨
	private TextView mTv_MinTemperature;// 5最低温度/夜间温度(没有最高和最低温度，有白天和夜间温度)
	private TextView mTv_MaxTemperature;// 6最高温度/白天温度(没有最高和最低温度，有白天和夜间温度)
	private TextView mTv_pm25;// 7 pm25值
	private TextView mTv_pm25airQuality;// 8 pm25空气质量
	private TextView mTv_pm25Suggestion;// 9 pm25建议
	private TextView mTv_dressGuide;// 10穿衣指导
	private TextView mTv_sunriseTime;// a日出时间
	private TextView mTv_sunsetTime;// b日落时间
	private TextView mTv_windForce;// c风力级数
	private TextView mTv_windDirection;// d风向
	
	private TextView mtv_date1;
	private TextView mtv_date2;
	private TextView mtv_date3;
	private TextView mtv_date4;
	private TextView mtv_date5;
	private TextView mtv_date6;
	private TextView mtv_weather1;
	private TextView mtv_weather2;
	private TextView mtv_weather3;
	private TextView mtv_weather4;
	private TextView mtv_weather5;
	private TextView mtv_weather6;
	private TextView mtv_temper1;
	private TextView mtv_temper2;
	private TextView mtv_temper3;
	private TextView mtv_temper4;
	private TextView mtv_temper5;
	private TextView mtv_temper6;
/*
	// 初始化星座TextView控件
	private TextView mtv_tianqi_tiancheng;
	private TextView mtv_tianqi_shuiping;
	private TextView mtv_tianqi_baiyang;
	private TextView mtv_tianqi_juxie;
	private TextView mtv_tianqi_mojie;
	private TextView mtv_tianqi_jinniu;
	private TextView mtv_tianqi_shizi;
	private TextView mtv_tianqi_tianxie;
	private TextView mtv_tianqi_shuangzi;
	private TextView mtv_tianqi_chunv;
	private TextView mtv_tianqi_sheshou;
	private TextView mtv_tianqi_shuangyu;
*/
	private ViewPager mViewPager;// 用来放置界面切换
	private PagerAdapter mPagerAdapter;// 初始化View适配器
	private List<View> mViews = new ArrayList<View>();// 用来存放Tab01-03
	// 三个Tab，每个Tab包含一个按钮
	private LinearLayout mTabtianqi;
	private LinearLayout mTabCity;
	private LinearLayout mTabSetting;

	public static LinearLayout mlayout_main;
	// 三个按钮
	private ImageButton mTianqiImg;
	private ImageButton mCityImg;
	private ImageButton mSettingImg;

	// 几个重要的显示t
	// Intent
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		initViewPage();
		initEvent();
	}

	private void initEvent() {
		mTabtianqi.setOnClickListener(this);
		mTabCity.setOnClickListener(this);
		mTabSetting.setOnClickListener(this);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * ViewPage左右滑动时
			 */
			@Override
			public void onPageSelected(int arg0) {
				int currentItem = mViewPager.getCurrentItem();
				switch (currentItem) {
				case 0:
					resetImg();
					mTianqiImg.setImageResource(R.drawable.tab_weather_selected);
					break;
				case 1:
					resetImg();
					mCityImg.setImageResource(R.drawable.tab_xingzuo_selected);
					break;
				case 2:
					resetImg();
					mSettingImg
							.setImageResource(R.drawable.tab_my_selected);
					break;
				default:
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	/**
	 * 初始化设置
	 */
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpage);

		mlayout_main = (LinearLayout) findViewById(R.id.layout_main);
		//设置全部壁纸
		mlayout_main.setBackgroundResource(R.drawable.background_4);
		// 初始化三个LinearLayout
		mTabtianqi = (LinearLayout) findViewById(R.id.id_tab_tianqi);
		mTabCity = (LinearLayout) findViewById(R.id.id_tab_city);
		mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);

		// 初始化三个按钮
		mTianqiImg = (ImageButton) findViewById(R.id.id_tab_tianqi_img);
		mCityImg = (ImageButton) findViewById(R.id.id_tab_city_img);
		mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
	}

	/**
	 * 初始化ViewPage
	 */
	private void initViewPage() {

		// 初始化三个布局
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View tab01 = mLayoutInflater.inflate(R.layout.tab01, null);
		View tab02 = mLayoutInflater.inflate(R.layout.tab02, null);
		View tab03 = mLayoutInflater.inflate(R.layout.tab03, null);

		// 初始化ViewPage中子layout中的控件
		mTv_city = (TextView) tab01.findViewById(R.id.tv_city);
		mTv_city.setText(city_name);

		mtv_username = (TextView) tab03.findViewById(R.id.tv_username);
		mImg_user_image = (ImageView) tab03.findViewById(R.id.img_user_image);
		// mImg_user_image.

		// 显示天气相关控件的提取
		mTv_datetime = (TextView) tab01.findViewById(R.id.tv_datetime);
		mTv_temperature = (TextView) tab01.findViewById(R.id.tv_temperature);
		mTv_weather = (TextView) tab01.findViewById(R.id.tv_weather);
		mTv_MinTemperature = (TextView) tab01
				.findViewById(R.id.tv_MinTemperature);
		mTv_MaxTemperature = (TextView) tab01
				.findViewById(R.id.tv_MaxTemperature);
		mTv_pm25 = (TextView) tab01.findViewById(R.id.tv_pm25);
		mTv_pm25airQuality = (TextView) tab01
				.findViewById(R.id.tv_pm25airQuality);
		mTv_pm25Suggestion = (TextView) tab01
				.findViewById(R.id.tv_pm25Suggestion);
		mTv_dressGuide = (TextView) tab01.findViewById(R.id.tv_dressGuide);
		mTv_sunriseTime = (TextView) tab01.findViewById(R.id.tv_sunriseTime);
		mTv_sunsetTime = (TextView) tab01.findViewById(R.id.tv_sunsetTime);
		mTv_windForce = (TextView) tab01.findViewById(R.id.tv_windForce);
		mTv_windDirection = (TextView) tab01
				.findViewById(R.id.tv_windDirection);
		
		mtv_date1=(TextView) tab01.findViewById(R.id.tv_date1);
		mtv_date2=(TextView) tab01.findViewById(R.id.tv_date2);
		mtv_date3=(TextView) tab01.findViewById(R.id.tv_date3);
		mtv_date4=(TextView) tab01.findViewById(R.id.tv_date4);
		mtv_date5=(TextView) tab01.findViewById(R.id.tv_date5);
		mtv_date6=(TextView) tab01.findViewById(R.id.tv_date6);

		mtv_weather1=(TextView) tab01.findViewById(R.id.tv_weather1);
		mtv_weather2=(TextView) tab01.findViewById(R.id.tv_weather2);
		mtv_weather3=(TextView) tab01.findViewById(R.id.tv_weather3);
		mtv_weather4=(TextView) tab01.findViewById(R.id.tv_weather4);
		mtv_weather5=(TextView) tab01.findViewById(R.id.tv_weather5);
		mtv_weather6=(TextView) tab01.findViewById(R.id.tv_weather6);
		
		mtv_temper1=(TextView) tab01.findViewById(R.id.tv_temper1);
		mtv_temper2=(TextView) tab01.findViewById(R.id.tv_temper2);
		mtv_temper3=(TextView) tab01.findViewById(R.id.tv_temper3);
		mtv_temper4=(TextView) tab01.findViewById(R.id.tv_temper4);
		mtv_temper5=(TextView) tab01.findViewById(R.id.tv_temper5);
		mtv_temper6=(TextView) tab01.findViewById(R.id.tv_temper6);

		
		
		//天气图标
		mimg_weather=(ImageView)tab01.findViewById(R.id.img_weather);
		
		
		// 刷新按钮
		ImageButton mImgBtn_refresh = (ImageButton) tab01
				.findViewById(R.id.imgBtn_refresh);
		// 获取标题String格式城市
		//final String city = mTv_city.getText().toString();

		// 刷新按钮监控事件
		
		mImgBtn_refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 建立HttpCollection对象
				hc = new HttpCollection(Weather_URL_BASE,mTv_city.getText().toString(),Weather_KEY);
				hc.httpGetData();// 建立http连接获取json格式数据
				try {
					// 接收天气json格式数据
					String city_information = hc.getJson();
					// 解析json
					Json_Weather json_Results = new Json_Weather();// 构建解析器
					json_Results.parJson(city_information);// 解析json数据

					// 设置数据
					mTv_datetime.setText(json_Results.datetime);
					mTv_temperature.setText(json_Results.curTemperature);
					mTv_weather.setText(json_Results.weather);
					
					// 根据天气的情况来设置天气小图标
					String m = mTv_weather.getText().toString().trim();
					Log.i("天气是：",m);
					if (m.equals("晴")){
						
						mimg_weather
								.setBackgroundResource(R.drawable.sun);
					
					}
					else if (m.equals("下雨"))
						mimg_weather
								.setBackgroundResource(R.drawable.rain);
					else
						mimg_weather
								.setBackgroundResource(R.drawable.duoyun);
						
						
						
					mTv_MinTemperature.setText(json_Results.MinTemperature);
					mTv_MaxTemperature.setText(json_Results.MaxTemperature);
					mTv_pm25.setText(json_Results.pm25);
					mTv_pm25airQuality.setText(json_Results.pm25airQuality);
					mTv_pm25Suggestion.setText(json_Results.pm25Suggestion);
					mTv_dressGuide.setText(json_Results.dressGuide);
					mTv_sunriseTime.setText(json_Results.sunriseTime);
					mTv_sunsetTime.setText(json_Results.sunsetTime);
					mTv_windForce.setText(json_Results.windForce);
					mTv_windDirection.setText(json_Results.windDirection);
					
					mtv_date1.setText(json_Results.date1);
					mtv_date2.setText(json_Results.date2);
					mtv_date3.setText(json_Results.date3);
					mtv_date4.setText(json_Results.date4);
					mtv_date5.setText(json_Results.date5);
					mtv_date6.setText(json_Results.date6);
					mtv_weather1.setText(json_Results.weather1);
					mtv_weather2.setText(json_Results.weather2);
					mtv_weather3.setText(json_Results.weather3);
					mtv_weather4.setText(json_Results.weather4);
					mtv_weather5.setText(json_Results.weather5);
					mtv_weather6.setText(json_Results.weather6);
					mtv_temper1.setText(json_Results.temper1);
					mtv_temper2.setText(json_Results.temper2);
					mtv_temper3.setText(json_Results.temper3);
					mtv_temper4.setText(json_Results.temper4);
					mtv_temper5.setText(json_Results.temper5);
					mtv_temper6.setText(json_Results.temper6);
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//Log.i("connect failed ", e.toString());
					e.printStackTrace();
				}
			}
			// 非阻塞的线程处理方式

		});
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		// 适配器初始化并设置
		mPagerAdapter = new PagerAdapter() {

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(mViews.get(position));
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				View view = mViews.get(position);
				container.addView(view);
				return view;
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {

				return arg0 == arg1;
			}

			@Override
			public int getCount() {

				return mViews.size();
			}
		};
		mViewPager.setAdapter(mPagerAdapter);
	}

	/**
	 * 判断哪个要显示，及设置按钮图片
	 */
	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.id_tab_tianqi:
			mViewPager.setCurrentItem(0);
			resetImg();
			mTianqiImg.setImageResource(R.drawable.tab_weather_selected);
			break;
		case R.id.id_tab_city:
			mViewPager.setCurrentItem(1);
			resetImg();
			mCityImg.setImageResource(R.drawable.tab_xingzuo_selected);
			break;
		case R.id.id_tab_settings:
			mViewPager.setCurrentItem(2);
			resetImg();
			mSettingImg.setImageResource(R.drawable.tab_my_selected);
			break;
		default:
			break;
		}
	}

	/**
	 * 把所有图片变暗
	 */
	private void resetImg() {
		mTianqiImg.setImageResource(R.drawable.tab_weather_unselected);
		mCityImg.setImageResource(R.drawable.tab_xingzuo_unselected);
		mSettingImg.setImageResource(R.drawable.tab_my_unselected);
	}

	public void img_add_city_click(View view) {
		Intent intent = new Intent(MainActivity.this, XingzuoActivity.class);
		this.startActivity(intent);
	}

	public void img_search_city_click(View view) {
		Intent intent = new Intent(MainActivity.this, SearchActivity.class);
		this.startActivity(intent);
	}

	public void tv_username_click(View view) {
		final EditText et_content = new EditText(this);
		new AlertDialog.Builder(this)
				.setTitle("修改昵称")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(et_content)
				.setPositiveButton("确定",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mtv_username.setText(et_content.getText()
										.toString());
							}
						}).setNegativeButton("取消", null).show();
	}

	public void img_change_background(View view) {
		Intent intent = new Intent(MainActivity.this, ImageChangeActivity.class);
		startActivity(intent);
	}

	public void layout_wallpaperchange_onClick(View view) {
		Intent intent = new Intent(MainActivity.this,
				WallpaperChangeActivity.class);
		startActivity(intent);
	}

	public void share_onclick(View view) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, "亲，来一块使用“简洁天气”吧！");
		shareIntent.setType("text/plain");

		// 设置分享列表的标题，并且每次都显示分享列表
		startActivity(Intent.createChooser(shareIntent, "分享到"));
	}

	public void onClick_Xingzuo(View arg0) {
		Intent intent;
		switch (arg0.getId()) {
		case R.id.tv_xingzuo_baiyang:
			Log.i("点击成功！",R.id.tv_xingzuo_baiyang+"?"+arg0.getId());
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "白羊座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_chunv:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "处女座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_jinniu:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "金牛座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_juxie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "巨蟹座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_mojie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "摩羯座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_sheshou:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "射手座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shizi:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "狮子座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuangyu:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "双鱼座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuangzi:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "双子座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuiping:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "水瓶座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_tiancheng:
			//Log.i("点击成功！",R.id.tv_xingzuo_tiancheng+"?"+arg0.getId());
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "天秤座");//传值到下一个activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_tianxie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "天蝎座");//传值到下一个activity
			startActivity(intent);
			break;
		}
	}
	
	protected void onRestart(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTv_city.setText(city_name);
		Log.i("传值为：",mTv_city.getText().toString());
	}
	protected void onStart(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTv_city.setText(city_name);
		Log.i("传值为：",mTv_city.getText().toString());
	}
	
	public void feedback_onclick(View view){
		Intent intent=new Intent(MainActivity.this,FeedbackActivity.class);
		startActivity(intent);
	}
}
