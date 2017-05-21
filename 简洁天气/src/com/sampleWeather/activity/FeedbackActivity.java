package com.sampleWeather.activity;

import com.zxl.sampleWeather.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FeedbackActivity extends Activity{
	private EditText met_feedback;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab03_content_feedback);
		met_feedback=(EditText)findViewById(R.id.et_feedback);
	}
	public void feedback_return_onclick(View view){
		this.finish();
	}

	public void send_onclick(View view){
		String content=met_feedback.getText().toString();
		Uri uri = Uri.parse("mailto:1025293651@qq.com");   
		String[] email = {"1025293651@qq.com"};  
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);  
		//intent.putExtra(Intent.EXTRA_CC, email); // 抄送人 
		intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
		intent.putExtra(Intent.EXTRA_SUBJECT, "这是邮件的主题部分"); // 主题  
		intent.putExtra(Intent.EXTRA_TEXT, content); // 正文  
		startActivity(Intent.createChooser(intent, "请选择邮件类应用"));  
	}
}
