package com.example.simplescanner;

import java.util.Timer;
import java.util.TimerTask;

import com.ant.liao.GifView;
//import com.example.saomiao2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class WelcomeActivity extends Activity {
	GifView mGifView;
	public boolean connect=true;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
		setContentView(R.layout.welcome);
		mGifView = (GifView)findViewById(R.id.loading_gif);  
		mGifView.setGifImage(R.drawable.welcome2);  
		final Intent intent2 = new Intent(WelcomeActivity.this,
				MainActivity.class);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				startActivity(intent2);
				finish(); // 执行
			}

		};
		//connect=SocketClient.isconnected();
		
		timer.schedule(task, 1000 *3);
		finish();
// 3秒后
	}
	}



