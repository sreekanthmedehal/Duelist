package com.sreekanth.duelist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Otherbranchmenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar abar = getActionBar();
		abar.hide();
		setContentView(R.layout.activity_otherbranchmenu);
		Button button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(onclicklistener);
		Button button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(onclicklistener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.otherbranchmenu, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private OnClickListener onclicklistener = new OnClickListener() {
	    public void onClick(View v) {
	    	int id = (v.getId());
	   
		    switch (id) {
		    case R.id.button1 :
		    	  startActivity (new Intent(getApplicationContext(),F4Activity.class));
		    	  break;
		      case R.id.button2 :
		    	  startActivity (new Intent(getApplicationContext(),Tabbed.class));
		    	  break;
		    }
	}};
}