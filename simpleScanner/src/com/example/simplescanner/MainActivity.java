package com.example.simplescanner;


import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button_caise =(Button) findViewById(R.id.button_caise);
		
		Button button_heibai =(Button) findViewById(R.id.button_heibai);
		
		Button button_chakan =(Button) findViewById(R.id.button_chakan);
		button_heibai.setOnClickListener(new MyClickListener());
		button_caise.setOnClickListener(new MyClickListener2());
		button_chakan.setOnClickListener(new MyClickListener3());
		Button bn2=(Button)findViewById(R.id.button_main_about);
		bn2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				Intent intent = new Intent(MainActivity.this,AboutActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
	}
	class MyClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			// 创建需要启动的Activity对应的Intent
			Intent intent = new Intent(MainActivity.this,
					CameraActivity_heibai.class);
			startActivity(intent);
			finish();
		}
	}
	class MyClickListener2 implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			// 创建需要启动的Activity对应的Intent
			Intent intent2 = new Intent(MainActivity.this,
					CameraActivity_caise.class);
			startActivity(intent2);
			finish();
		}
	}
	class MyClickListener3 implements View.OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			Intent intent3 = new Intent(MainActivity.this,
					ChakanActivity.class);
			startActivity(intent3);
			finish();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
