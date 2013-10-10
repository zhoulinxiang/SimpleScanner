package com.example.simplescanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.example.simplescanner.IImageFilter;
import com.example.simplescanner.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

public class ChaliActivity_heibai extends Activity {
	private Bitmap srcBitmap, dstBitmap,scBitmap;
	private float Threshold = 0.5f;
	private String Cfile=null;
	private int imgHeight, imgWidth;
	private SeekBar liangduseekBar=null;
	private ImageView ChuliView = null;
	private Bitmap bmp=null;
	private Image img=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chali_activity_heibai);
		liangduseekBar=(SeekBar) findViewById(R.id.liangduseekbar_heibai);
		ChuliView=(ImageView) findViewById(R.id.Chuliimageview_heibai);
		Bundle b=getIntent().getExtras();
		Cfile=(String)b.getString("pathname");
		srcBitmap = BitmapFactory.decodeFile(Cfile);
		Button bn2=(Button)findViewById(R.id.button_chuli_heibai);
		bn2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChaliActivity_heibai.this,MainActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
		
		img=new Image(srcBitmap);
		  int r, g, b1;
	        int threshold = (int)(this.Threshold * 255f);

	        for (int x = 0; x < img.getWidth(); x++) {
	            for (int y = 0; y < img.getHeight(); y++) {
	                 r = img.getRComponent(x, y);
	                 g = img.getGComponent(x, y);
	                 b1 = img.getBComponent(x, y);

	                 int rgb = (((r * 0x1b36) + (g * 0x5b8c)) + (b1 * 0x93e)) >> 15;
	            	 r = g = b1  = rgb > threshold ? 0xff : 0;
	                 img.setPixelColor(x,y,r,g,b1);
	             }
	        }
		//srcBitmap= convertToBlackWhite(srcBitmap);
	        img.copyPixelsFromBuffer();
	        scBitmap=img.getImage();
	        bmp=scBitmap;
		ChuliView.setImageBitmap(scBitmap);
		
		imgHeight = scBitmap.getHeight();
		imgWidth = scBitmap.getWidth();
		//dstBitmap = Bitmap.createBitmap(imgWidth, imgHeight, Config.ARGB_8888);
		Button bn=(Button)findViewById(R.id.shanchu_heibai);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				File file= new File(Cfile);
				file.delete();
				// 创建需要启动的Activity对应的Intent
				Intent intent = new Intent(ChaliActivity_heibai.this,CameraActivity_heibai.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
				finish();
			}
		});
		Button bn3=(Button)findViewById(R.id.wancheng_heibai);
		bn3.setOnClickListener(new OnClickListener()
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
				Intent intent = new Intent(ChaliActivity_heibai.this,ChakanActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
				finish();
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
				canvas.drawBitmap(scBitmap, 0, 0, paint);
				
				
				ChuliView.setImageBitmap(bmp);
				
				
				//paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));				
			}

			public void onStartTrackingTouch(SeekBar bar) {
				
			}

			public void onStopTrackingTouch(SeekBar bar) {
				
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chali_activity_heibai, menu);
		return true;
	}

}

