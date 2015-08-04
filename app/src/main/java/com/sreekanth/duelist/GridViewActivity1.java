package com.sreekanth.duelist;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class GridViewActivity1 extends Activity {

	GridView gridView;

	static final String[] MOBILE_OS = new String[] { "InForce", "Probation",
			"CM", "ZM" , "DM" , "BM" , "DA" , "All" , "No Email" , "No Mobile" };

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				String label = ((TextView) v.findViewById(R.id.grid_item_label)).getText().toString();
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.grid_item_label))
								.getText(), Toast.LENGTH_SHORT).show();
			//	String message =  v.findViewById(R.id.grid_item_label).toString();
				Intent intent = new Intent(GridViewActivity1.this,AndroidListViewCursorAdaptorActivity.class);
				intent.putExtra("MESSAGE", label);
				startActivity(intent);

			}
		});

	}

}