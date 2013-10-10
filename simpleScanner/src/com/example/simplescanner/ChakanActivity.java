package com.example.simplescanner;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class ChakanActivity extends Activity {
	private GridView grid;
	private List<String> ImageList;
	private String[] list;
	
	private String pathname=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chakan);
		ImageList = getInSDPhoto();
        list = ImageList.toArray(new String[ImageList.size()]);
        Button bn=(Button)findViewById(R.id.button_chakan);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				
				
				Intent intent = new Intent(ChakanActivity.this,MainActivity.class
						);
				// 启动intent对应的Activity
				startActivity(intent);
			}
		});
        List<Map<String, Object>> listItems = 
				new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", list[i]);
			listItems.add(listItem);
		}
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
        		listItems
				// 使用/layout/cell.xml文件作为界面布局
				, R.layout.cell, new String[] { "image" },
				new int[] { R.id.image1 });
        grid=(GridView)findViewById(R.id.gridchakan);
        grid.setAdapter(simpleAdapter);
       //为grid绑定监控器，链接至编辑图片的activity
       grid.setOnItemSelectedListener(new OnItemSelectedListener()
    	{
    		@Override
    		public void onItemSelected(AdapterView<?> parent, View view,
    				int position, long id)
    		{
    			// 显示当前被选中的图片
    		    File f = new File("/sdcard/myImage/");
    		    if(!f.exists()){f.mkdirs();}
    		    File[] files = f.listFiles();
    			File file = files[position];
    			pathname=file.getPath();
    			Bundle data=new Bundle();
        		data.putString("pathname",pathname);
        		Intent sintent=new Intent(ChakanActivity.this,ChaliActivity_chakan.class);
        		sintent.putExtras(data);
        		startActivity(sintent);
        		finish();
    			
    		}

    		@Override
    		public void onNothingSelected(AdapterView<?> parent)
    		{
    		}
    	});
    	// 添加列表项被单击的监听器
    	grid.setOnItemClickListener(new OnItemClickListener()
    	{
    		@Override
    		public void onItemClick(AdapterView<?> parent, View view,
    				int position, long id)
    		{
    			// 显示被单击的图片的图片
    			File f = new File("/sdcard/myImage/");
    			File[] files = f.listFiles();
    			
    			File file = files[position];
    			pathname=file.getPath();
    			Bundle data=new Bundle();
        		data.putString("pathname",pathname);
        		Intent sintent=new Intent(ChakanActivity.this,ChaliActivity_chakan.class);
        		sintent.putExtras(data);
        		startActivity(sintent);
        		finish();
    		}
    	}); 
    	
        
        
          
	}
	private List<String> getInSDPhoto() {
    	/**
		 *  设定图片所在路径
		 */
		List<String> it = new ArrayList<String>();
		
		/**
		 * 此处小马直接把图片push进了SD卡根路径下，如果朋友们要放到其它文件下，
		 * 可先判断下SD卡存在时将路径指定到自己的路径下就可以了，试试吧，吼吼
		 */
		File f = new File("/sdcard/myImage/");
		File[] files = f.listFiles();

		/**
		 *  将所有文件存入ArrayList中,这个地方存的还是文件路径哦
		 */
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (getAllImage(file.getPath()))
				it.add(file.getPath());
		}
		return it;
	}
    /**
	 * 获取所有图片文件的实现
	 * @param fName
	 * @return
	 */
	private boolean getAllImage(String fName) {
		boolean re;

		/* 取得扩展名 */
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		/* 按扩展名的类型决定MimeType */
		if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			re = true;
		} else {
			re = false;
		}
		return re;
	}
	public List<String> getImageList() {
		return ImageList;
	}
	public void setImageList(List<String> imageList) {
		ImageList = imageList;
	}
	public String[] getList() {
		return list;
	}
	public void setList( final String[] list) {
		this.list = list;
	
	
	
      
	}   




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chakan, menu);
		return true;
	}

}
