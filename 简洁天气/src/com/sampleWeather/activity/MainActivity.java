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
	private static String Weather_KEY="&key=2dc31adf6ac8fa868e7f9f70cdab0a98";//key�������key��
	public static String city_name = "����";//

	private HttpCollection hc;// httpCollection����
	
	public static ImageView mimg_weather;
	private TextView mtv_username;
	public static ImageView mImg_user_image;
	// ��ʼ������TextView�ؼ�
	public static TextView mTv_city;// 1�������
	private TextView mTv_datetime;// 2����ʱ��
	private TextView mTv_temperature;// 3ʵʱ�¶�
	private TextView mTv_weather;// 4������������ƣ��磬����
	private TextView mTv_MinTemperature;// 5����¶�/ҹ���¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
	private TextView mTv_MaxTemperature;// 6����¶�/�����¶�(û����ߺ�����¶ȣ��а����ҹ���¶�)
	private TextView mTv_pm25;// 7 pm25ֵ
	private TextView mTv_pm25airQuality;// 8 pm25��������
	private TextView mTv_pm25Suggestion;// 9 pm25����
	private TextView mTv_dressGuide;// 10����ָ��
	private TextView mTv_sunriseTime;// a�ճ�ʱ��
	private TextView mTv_sunsetTime;// b����ʱ��
	private TextView mTv_windForce;// c��������
	private TextView mTv_windDirection;// d����
	
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
	// ��ʼ������TextView�ؼ�
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
	private ViewPager mViewPager;// �������ý����л�
	private PagerAdapter mPagerAdapter;// ��ʼ��View������
	private List<View> mViews = new ArrayList<View>();// �������Tab01-03
	// ����Tab��ÿ��Tab����һ����ť
	private LinearLayout mTabtianqi;
	private LinearLayout mTabCity;
	private LinearLayout mTabSetting;

	public static LinearLayout mlayout_main;
	// ������ť
	private ImageButton mTianqiImg;
	private ImageButton mCityImg;
	private ImageButton mSettingImg;

	// ������Ҫ����ʾt
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
			 * ViewPage���һ���ʱ
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
	 * ��ʼ������
	 */
	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpage);

		mlayout_main = (LinearLayout) findViewById(R.id.layout_main);
		//����ȫ����ֽ
		mlayout_main.setBackgroundResource(R.drawable.background_4);
		// ��ʼ������LinearLayout
		mTabtianqi = (LinearLayout) findViewById(R.id.id_tab_tianqi);
		mTabCity = (LinearLayout) findViewById(R.id.id_tab_city);
		mTabSetting = (LinearLayout) findViewById(R.id.id_tab_settings);

		// ��ʼ��������ť
		mTianqiImg = (ImageButton) findViewById(R.id.id_tab_tianqi_img);
		mCityImg = (ImageButton) findViewById(R.id.id_tab_city_img);
		mSettingImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
	}

	/**
	 * ��ʼ��ViewPage
	 */
	private void initViewPage() {

		// ��ʼ����������
		LayoutInflater mLayoutInflater = LayoutInflater.from(this);
		View tab01 = mLayoutInflater.inflate(R.layout.tab01, null);
		View tab02 = mLayoutInflater.inflate(R.layout.tab02, null);
		View tab03 = mLayoutInflater.inflate(R.layout.tab03, null);

		// ��ʼ��ViewPage����layout�еĿؼ�
		mTv_city = (TextView) tab01.findViewById(R.id.tv_city);
		mTv_city.setText(city_name);

		mtv_username = (TextView) tab03.findViewById(R.id.tv_username);
		mImg_user_image = (ImageView) tab03.findViewById(R.id.img_user_image);
		// mImg_user_image.

		// ��ʾ������ؿؼ�����ȡ
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

		
		
		//����ͼ��
		mimg_weather=(ImageView)tab01.findViewById(R.id.img_weather);
		
		
		// ˢ�°�ť
		ImageButton mImgBtn_refresh = (ImageButton) tab01
				.findViewById(R.id.imgBtn_refresh);
		// ��ȡ����String��ʽ����
		//final String city = mTv_city.getText().toString();

		// ˢ�°�ť����¼�
		
		mImgBtn_refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����HttpCollection����
				hc = new HttpCollection(Weather_URL_BASE,mTv_city.getText().toString(),Weather_KEY);
				hc.httpGetData();// ����http���ӻ�ȡjson��ʽ����
				try {
					// ��������json��ʽ����
					String city_information = hc.getJson();
					// ����json
					Json_Weather json_Results = new Json_Weather();// ����������
					json_Results.parJson(city_information);// ����json����

					// ��������
					mTv_datetime.setText(json_Results.datetime);
					mTv_temperature.setText(json_Results.curTemperature);
					mTv_weather.setText(json_Results.weather);
					
					// �����������������������Сͼ��
					String m = mTv_weather.getText().toString().trim();
					Log.i("�����ǣ�",m);
					if (m.equals("��")){
						
						mimg_weather
								.setBackgroundResource(R.drawable.sun);
					
					}
					else if (m.equals("����"))
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
			// ���������̴߳���ʽ

		});
		mViews.add(tab01);
		mViews.add(tab02);
		mViews.add(tab03);
		// ��������ʼ��������
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
	 * �ж��ĸ�Ҫ��ʾ�������ð�ťͼƬ
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
	 * ������ͼƬ�䰵
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
				.setTitle("�޸��ǳ�")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setView(et_content)
				.setPositiveButton("ȷ��",
						new android.content.DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								mtv_username.setText(et_content.getText()
										.toString());
							}
						}).setNegativeButton("ȡ��", null).show();
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
		shareIntent.putExtra(Intent.EXTRA_TEXT, "�ף���һ��ʹ�á�����������ɣ�");
		shareIntent.setType("text/plain");

		// ���÷����б�ı��⣬����ÿ�ζ���ʾ�����б�
		startActivity(Intent.createChooser(shareIntent, "����"));
	}

	public void onClick_Xingzuo(View arg0) {
		Intent intent;
		switch (arg0.getId()) {
		case R.id.tv_xingzuo_baiyang:
			Log.i("����ɹ���",R.id.tv_xingzuo_baiyang+"?"+arg0.getId());
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "������");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_chunv:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "��Ů��");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_jinniu:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "��ţ��");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_juxie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "��з��");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_mojie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "Ħ����");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_sheshou:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "������");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shizi:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "ʨ����");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuangyu:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "˫����");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuangzi:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "˫����");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_shuiping:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "ˮƿ��");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_tiancheng:
			//Log.i("����ɹ���",R.id.tv_xingzuo_tiancheng+"?"+arg0.getId());
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "�����");//��ֵ����һ��activity
			startActivity(intent);
			break;
		case R.id.tv_xingzuo_tianxie:
			intent=new Intent(MainActivity.this,XingzuoActivity.class);
			intent.putExtra("Xingzuo", "��Ы��");//��ֵ����һ��activity
			startActivity(intent);
			break;
		}
	}
	
	protected void onRestart(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTv_city.setText(city_name);
		Log.i("��ֵΪ��",mTv_city.getText().toString());
	}
	protected void onStart(Bundle savedInstanceState) {
		this.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTv_city.setText(city_name);
		Log.i("��ֵΪ��",mTv_city.getText().toString());
	}
	
	public void feedback_onclick(View view){
		Intent intent=new Intent(MainActivity.this,FeedbackActivity.class);
		startActivity(intent);
	}
}
