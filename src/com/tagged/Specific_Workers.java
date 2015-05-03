package com.tagged;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Specific_Workers extends Activity {

	ListView lv;

	ArrayList<String> plumbers = new ArrayList<String>();
	ArrayList<String> carpenters = new ArrayList<String>();
	ArrayList<String> electricians = new ArrayList<String>();
	ArrayList<String> doctors = new ArrayList<String>();

	ArrayAdapter<String> arrayAdapter;
	Intent i;

	ArrayList<WorkerItem> list = new ArrayList<WorkerItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_taggs);

		Bundle b = getIntent().getExtras();
		String tag = b.getString("career");
		Toast.makeText(this, tag, Toast.LENGTH_LONG).show();
		lv = (ListView) findViewById(R.id.lvListTaggs);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {

				i = new Intent(Specific_Workers.this, Profile.class );
				startActivity(i);
			}

		});

		populateAllLists();

		populateworkers(this);
	}

	public void populateworkers(Context context) {

		for (String name : plumbers) {

			WorkerItem item = new WorkerItem();
			item.setName(name);
			list.add(item);
			WorkerAdapter adapt = new WorkerAdapter(context, 0, list);
			lv.setAdapter(adapt);
		}

	}

	public void populateAllLists() {
		plumbers.add("Company A");
		plumbers.add("Company B");
		plumbers.add("Company C");
		plumbers.add("Company D");
		plumbers.add("Company E");
		plumbers.add("Company F");
		plumbers.add("Company G");

		carpenters.add("Company A");
		carpenters.add("Company B");
		carpenters.add("Company C");
		carpenters.add("Company D");
		carpenters.add("Company E");
		carpenters.add("Company F");
		carpenters.add("Company G");

		electricians.add("Company A");
		electricians.add("Company B");
		electricians.add("Company C");
		electricians.add("Company D");
		electricians.add("Company E");
		electricians.add("Company F");
		electricians.add("Company G");

	}
}
