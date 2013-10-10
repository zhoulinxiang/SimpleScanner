package com.example.simplescanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ChaliActivity_chakan extends Activity {
	private Bitmap srcBitmap, dstBitmap;
	private String Cfile=null;
	private int imgHeight, imgWidth;
	private SeekBar baoheduseekBar = null,duibiduseekBar=null,liangduseekBar=null;
	private ImageView ChuliView = null;
	private Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chali_activity_chakan);
		baoheduseekBar = (SeekBar) findViewById(R.id.baoheduseekbar_chakan);
		duibiduseekBar=(SeekBar) findViewById(R.id.duibiduseekbar_chakan);
		liangduseekBar=(SeekBar) findViewById(R.id.liangduseekbar_chakan);
		ChuliView=(ImageView) findViewById(R.id.Chuliimageview_chakan);
		Button bn=(Button)findViewById(R.id.button_camera_caise);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				Intent intent = new Intent(ChaliActivity_chakan.this,MainActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
		Bundle b=getIntent().getExtras();
		Cfile=(String)b.getString("pathname");
		srcBitmap = BitmapFactory.decodeFile(Cfile);
		ChuliView.setImageBitmap(srcBitmap);
		imgHeight = srcBitmap.getHeight();
		imgWidth = srcBitmap.getWidth();
		bmp=srcBitmap;
		dstBitmap = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);
		Button bn2=(Button)findViewById(R.id.shanchu_chakan);
		bn2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				File file= new File(Cfile);
				file.delete();
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChaliActivity_chakan.this,ChakanActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
		Button bn3=(Button)findViewById(R.id.wancheng_chakan);
		bn3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				File file2= new File(Cfile);
				
				
				FileOutputStream b = null;
				
				try {
	                b = new FileOutputStream(file2);
	                bmp.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    b.flush();
	                    b.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
				//完成的实现
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChaliActivity_chakan.this,ChakanActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
		baoheduseekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// 当拖动条的滑块位置发生改变时触发该方法
			public void onProgressChanged(SeekBar arg0, int progress, boolean fromUser) {
				// 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
				bmp = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);
				ColorMatrix cMatrix = new ColorMatrix();
				// 设置饱和度
				cMatrix.setSaturation((float) (progress / 100.0));
				Paint paint = new Paint();
				paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

				Canvas canvas = new Canvas(bmp);
				// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
				canvas.drawBitmap(srcBitmap, 0, 0, paint);
				ChuliView.setImageBitmap(bmp);
			}
			public void onStartTrackingTouch(SeekBar bar) {
				
			}

			public void onStopTrackingTouch(SeekBar bar) {
				
			}
		});
		liangduseekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// 当拖动条的滑块位置发生改变时触发该方法
			public void onProgressChanged(SeekBar arg0, int progress, boolean fromUser) {
			
				 bmp = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);
				int brightness = progress - 127;
				ColorMatrix cMatrix = new ColorMatrix();
				// 改变亮度
				cMatrix.set(new float[] { 
						1, 0, 0, 0, brightness, 0, 1,
						0, 0, brightness,
						0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });

				Paint paint = new Paint();
				paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
				Canvas canvas = new Canvas(bmp);
				// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
				canvas.drawBitmap(srcBitmap, 0, 0, paint);
				ChuliView.setImageBitmap(bmp);
				
				paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));				
			}

			public void onStartTrackingTouch(SeekBar bar) {
				
			}

			public void onStopTrackingTouch(SeekBar bar) {
				
			}

		});
		duibiduseekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			// 当拖动条的滑块位置发生改变时触发该方法
			public void onProgressChanged(SeekBar arg0, int progress, boolean fromUser) {
				 bmp = Bitmap.createBitmap(imgWidth, imgHeight,  Config.ARGB_8888);
				// int brightness = progress - 127;
				float contrast = (float) ((progress + 64) / 128.0);
				ColorMatrix cMatrix = new ColorMatrix();
				// 改变对比度
				cMatrix.set(new float[] { 
						contrast, 0, 0, 0, 0, 0,
						contrast, 0, 0, 0,
						0, 0, contrast, 0, 0, 0, 0, 0, 1, 0 });
	
				Paint paint = new Paint();
				paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));
	
				Canvas canvas = new Canvas(bmp);
				// 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
				canvas.drawBitmap(srcBitmap, 0, 0, paint);
	
				ChuliView.setImageBitmap(bmp);
			}

			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chali_activity_chakan, menu);
		return true;
	}

}
