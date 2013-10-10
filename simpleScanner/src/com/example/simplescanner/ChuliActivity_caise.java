package com.example.simplescanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ChuliActivity_caise extends Activity {
	private Bitmap srcBitmap, dstBitmap;
	private String Cfile=null;
	private int imgHeight, imgWidth;
	private SeekBar baoheduseekBar=null,duibiduseekBar=null,liangduseekBar=null;
	private ImageView ChuliView = null;
	private Bitmap bmp=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chuli_activity_caise);
		duibiduseekBar=(SeekBar) findViewById(R.id.duibiduseekbar_caise);
		ChuliView=(ImageView) findViewById(R.id.Chuliimageview_caise);
		Bundle b=getIntent().getExtras();
		Cfile=(String)b.getString("pathname");
		srcBitmap = BitmapFactory.decodeFile(Cfile);
		Button bn3=(Button)findViewById(R.id.button_chuli_caise);
		bn3.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChuliActivity_caise.this,MainActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
		
		
		srcBitmap= sharpenImageAmeliorate(srcBitmap);
		bmp=srcBitmap;
		ChuliView.setImageBitmap(srcBitmap);
		imgHeight = srcBitmap.getHeight();
		imgWidth = srcBitmap.getWidth();
		//dstBitmap = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);
		Button bn=(Button)findViewById(R.id.shanchu_caise);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				File file= new File(Cfile);
				file.delete();
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChuliActivity_caise.this,CameraActivity_caise.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
				finish();
			}
		});
		Button bn2=(Button)findViewById(R.id.wancheng_caise);
		bn2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//String a = Cfile;
				 // a=a.substring(0, a.length() - 3);
				  //System.out.println(a);
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
				Intent intent = new Intent(ChuliActivity_caise.this,ChakanActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
				finish();
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
	/**
	* 图片锐化（拉普拉斯变换）
	* 
	* @param bmp
	* @return
	*/
	public static Bitmap sharpenImageAmeliorate(Bitmap bmp) {
	// 拉普拉斯矩阵
	int[] laplacian = new int[] { -1, -1, -1, -1, 9, -1, -1, -1, -1 };
	int width = bmp.getWidth();
	int height = bmp.getHeight();
	Bitmap bitmap = Bitmap.createBitmap(width, height,
	Bitmap.Config.RGB_565);
	int pixR = 0;
	int pixG = 0;
	int pixB = 0;
	int pixColor = 0;
	int newR = 0;
	int newG = 0;
	int newB = 0;
	int idx = 0;
	float alpha = 0.3F;
	int[] pixels = new int[width * height];
	bmp.getPixels(pixels, 0, width, 0, 0, width, height);
	for (int i = 1, length = height - 1; i < length; i++) {
	for (int k = 1, len = width - 1; k < len; k++) {
	idx = 0;
	for (int m = -1; m <= 1; m++) {
	for (int n = -1; n <= 1; n++) {
	pixColor = pixels[(i + n) * width + k + m];
	pixR = Color.red(pixColor);
	pixG = Color.green(pixColor);
	pixB = Color.blue(pixColor);
	newR = newR + (int) (pixR * laplacian[idx] * alpha);
	newG = newG + (int) (pixG * laplacian[idx] * alpha);
	newB = newB + (int) (pixB * laplacian[idx] * alpha);
	idx++;
	}
	}
	newR = Math.min(255, Math.max(0, newR));
	newG = Math.min(255, Math.max(0, newG));
	newB = Math.min(255, Math.max(0, newB));
	pixels[i * width + k] = Color.argb(255, newR, newG, newB);
	newR = 0;
	newG = 0;
	newB = 0;
	}
	}
	bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
	return bitmap;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chuli_activity_caise, menu);
		return true;
	}

}
