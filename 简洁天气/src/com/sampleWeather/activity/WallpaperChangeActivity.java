package com.sampleWeather.activity;

import com.zxl.sampleWeather.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class WallpaperChangeActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab03_content_wallpaperchange);
	}

	public void return_onclick(View view) {
		onBackPressed();
	}

	public void wallpaperchange_onClick(View view) {
		//Log.i("image view id:", " "+view.getId()+" =? "+R.id.img_background_1);
		switch (view.getId()) {
		case R.id.img_background_1:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_1);
			SearchActivity.mtab01_search_res = R.drawable.background_1;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_1;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_1;
			
			break;
		case R.id.img_background_2:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_2);
			SearchActivity.mtab01_search_res = R.drawable.background_2;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_2;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_2;
			
			break;
		case R.id.img_background_3:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_3);
			SearchActivity.mtab01_search_res = R.drawable.background_3;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_3;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_3;
			
			break;
		case R.id.img_background_4:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_4);
			SearchActivity.mtab01_search_res = R.drawable.background_4;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_4;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_4;
			
			break;
		case R.id.img_background_5:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_5);
			SearchActivity.mtab01_search_res = R.drawable.background_5;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_5;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_5;
			
			break;
		case R.id.img_background_6:
			MainActivity.mlayout_main.setBackgroundResource(R.drawable.background_6);
			SearchActivity.mtab01_search_res = R.drawable.background_6;
			ImageChangeActivity.mlayout_tab03_imagechange_res=R.drawable.background_6;
			XingzuoActivity.mtab02_content_xingzuo_res=R.drawable.background_6;
			
			break;

		default:
			break;
		}
		new AlertDialog.Builder(WallpaperChangeActivity.this)
				.setMessage("壁纸设置成功！").setTitle("提示")
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						onBackPressed();
						// ImageChangeActivity.this.finish();
					}
				}).create().show();

	}
	// mImg_user_image.setBackgroundResource(R.drawable.ic_launcher);
}
