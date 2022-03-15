package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.webkit.*;
import android.widget.*;
import android.view.*;

public class WebActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		try{
			WebView web = findViewById(R.id.webView);
			WebSettings settings = web.getSettings();
			settings.setJavaScriptEnabled(true);
			web.setWebViewClient(new WebViewClient());
			web.loadUrl("https://kpopping.com/kpics/skip-SW");
			
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
 	// TODO: Implement this method
		if(item.getItemId() == android.R.id.home){
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
