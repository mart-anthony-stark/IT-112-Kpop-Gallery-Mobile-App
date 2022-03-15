package com.mycompany.myapp;

import android.app.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.ContextMenu.*;

public class MainActivity extends Activity {
	private Dialog modal;
    private int[] images = {
		R.drawable.ryujin,
		R.drawable.yuna,
		R.drawable.lia,
		R.drawable.yeji,
		R.drawable.dahyun,
		R.drawable.sana,
		R.drawable.tzuyu,
		R.drawable.eunha
	};
	private ImageView options;
	private Button prev, next;
	private int currentIndex=0;
	private ImageSwitcher switcher;
	private String[] names={"Shin Ryujin", "Shin Yuna","Choi Lia", "Hwang Yeji", "Kim Dahyun", "Minatozaki Sana", "Tzuyu", "Eunha"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		getActionBar().hide();
		
		options = findViewById(R.id.options);
		registerForContextMenu(options);
		
		options.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					openContextMenu(options);
				}
			});
		
		modal = new Dialog(MainActivity.this);
		modal.setContentView(R.layout.modal);

		modal.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		modal.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		modal.setCancelable(true);
		
		switcher = modal.findViewById(R.id.modalImage);
		switcher.setFactory(new ViewSwitcher.ViewFactory(){
				@Override
				public View makeView(){
					ImageView img = new ImageView(MainActivity.this);
					img.setImageResource(images[currentIndex]);
					return img;
				}
			});
		
		prev = modal.findViewById(R.id.prev);
		next = modal.findViewById(R.id.next);
		prev.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					switchImg('l');
				}
			});
		next.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					switchImg('r');
				}
			});
		
		GridView gv = findViewById(R.id.gridview);
		CustomAdapter adapter = new CustomAdapter();
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4){
					Toast.makeText(getApplicationContext(), names[p3], Toast.LENGTH_LONG).show();
					currentIndex=p3;
					switcher.setImageResource(images[currentIndex]);
					modal.show();
				}
			});
    }

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		// TODO: Implement this method
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select Idol");
		menu.add(0, v.getId(), 0, "Shin Ryujin");
		menu.add(1, v.getId(), 0, "Shin Yuna");
		menu.add(2, v.getId(), 0, "Choi Lia");
		menu.add(3, v.getId(), 0, "Hwang Yeji");
		menu.add(4, v.getId(), 0, "Kim Dahyun");
		menu.add(5, v.getId(), 0, "Minatozaki Sana");
		menu.add(6, v.getId(), 0, "Tzuyu");
		menu.add(7, v.getId(), 0, "Eunha");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item){
		currentIndex = item.getGroupId();
		switcher.setImageResource(images[currentIndex]);
		modal.show();
		return super.onContextItemSelected(item);
	}
	
	
	void switchImg(char dir){
		switch(dir){
			case 'l':
				currentIndex = currentIndex>0 ? currentIndex-1 : images.length-1;
				break;
			case 'r':
				currentIndex = currentIndex<images.length-1 ? currentIndex+1 : 0;
				break;
		}
		switcher.setImageResource(images[currentIndex]);
	}
	
	public class CustomAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public Object getItem(int p1) {
			return null;
		}

		@Override
		public long getItemId(int p1) {
			return 0;
		}

		@Override
		public View getView(int i, View p2, ViewGroup p3) {
			View viewl = getLayoutInflater().inflate(R.layout.tile, null);
			ImageView img = viewl.findViewById(R.id.tileImageView);
			img.setImageResource(images[i]);
			return viewl;
		}
		
		
	}
    
}
