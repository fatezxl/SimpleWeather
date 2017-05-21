package com.sampleWeather.activity;

import com.zxl.sampleWeather.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class ImageChangeActivity extends Activity {
	public static LinearLayout  mlayout_tab03_imagechange;
	public static int mlayout_tab03_imagechange_res=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab03_content_imagechange);
		mlayout_tab03_imagechange=(LinearLayout)findViewById(R.id.layout_tab03_imagechange);
		update_search_background();
	}

	public void return_onclick_imageChangeActivity(View view) {
		onBackPressed();
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.img_user_face1:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_1);
			break;
		case R.id.img_user_face2:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_2);
			break;
		case R.id.img_user_face3:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_3);
			break;
		case R.id.img_user_face4:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_4);
			break;
		case R.id.img_user_face5:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_5);
			break;
		case R.id.img_user_face6:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_6);
			break;
		case R.id.img_user_face7:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_7);
			break;
		case R.id.img_user_face8:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_8);
			break;
		case R.id.img_user_face9:
			MainActivity.mImg_user_image
					.setBackgroundResource(R.drawable.img_user_face_9);
			break;
		default:
			break;
		}
		new AlertDialog.Builder(ImageChangeActivity.this).setMessage("您的个性头像设置成功啦！")
				.setTitle("提示").setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						onBackPressed();
						// ImageChangeActivity.this.finish();
					}
				}).create().show();

	}
	
	private void update_search_background()
	{
		if (mlayout_tab03_imagechange_res != 0) {
			mlayout_tab03_imagechange.setBackgroundResource(mlayout_tab03_imagechange_res);
		}
	}
	protected void onRestart(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab03_content_imagechange);
		update_search_background();
	}
	//mImg_user_image.setBackgroundResource(R.drawable.ic_launcher);
}
