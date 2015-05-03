package com.tagged;

import java.util.ArrayList;
import java.util.Collections;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.tagged.PullToRefreshListView.OnRefreshListener;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class Careers extends SherlockFragmentActivity {

	ArrayList<String> al = new ArrayList<String>();
	TagsFragmentAdapter mAdapter;
	ViewPager mPager;
	Intent i;
	PageIndicator mIndicator;
	ListItemAdapter adapter;
	PullToRefreshListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		getSupportActionBar().setHomeButtonEnabled(true);
		setAdapter();
	}

	public void initPagerView(int position, final View view) {
		listView = (PullToRefreshListView) findViewById(R.id.lvListItems);
		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				view.invalidate();

			}
		});

		switch (position) {
		case 0:
			final ArrayList<ListItem> careers = new ArrayList<ListItem>();
			careers.add(new ListItem("Plumbers", "foo bar..."));
			careers.add(new ListItem("Doctors", "foo bar..."));
			careers.add(new ListItem("Carpenters", "foo bar..."));
			careers.add(new ListItem("Electricians", "foo bar..."));
			careers.add(new ListItem("Lawn Mowers", "foo bar..."));
			careers.add(new ListItem("Hair Dressers", "foo bar..."));
			careers.add(new ListItem("Barbers", "foo bar..."));
			adapter = new ListItemAdapter(getApplicationContext(),
					R.layout.list_item, careers);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Toast.makeText(getApplicationContext(),
							"course_selected: " + arg2, Toast.LENGTH_SHORT)
							.show();
					Bundle bundle = new Bundle();
					bundle.putString("career", careers.get(arg2).getName());
					i = new Intent(Careers.this, Specific_Workers.class);
					i.putExtras(bundle);
					startActivity(i);

				}
			});
			break;
		case 1:
			ArrayList<ListItem> myTags = new ArrayList<ListItem>();
			myTags.add(new ListItem("Jake", "#Uber Driverless car, #Space X..."));
			myTags.add(new ListItem("Finn", "#Maize prices, #Milk prices... "));
			myTags.add(new ListItem("Marceline",
					"#Situation in Nigeria, #Bitcoin..."));
			myTags.add(new ListItem("Ice King",
					"#Whiplash, North Korean situation..."));
			myTags.add(new ListItem("Bmo ",
					"#Agriculture investment, #Startup opportunities"));
			myTags.add(new ListItem("Princess Bubblegum",
					"#Regional trade, #Constitutional ammendments"));
			myTags.add(new ListItem("Tree Trunks",
					"#WorldBank, #Beef prices soar..."));
			adapter = new ListItemAdapter(getApplicationContext(),
					R.layout.list_item, myTags);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Toast.makeText(getApplicationContext(),
							"Tag selected: " + arg2, Toast.LENGTH_SHORT).show();
				}
			});
			break;
		case 2:
			ArrayList<ListItem> allTags = new ArrayList<ListItem>();
			allTags.add(new ListItem("Culture", "Tags related to culture"));
			allTags.add(new ListItem("Technology", "Tags related to technology"));
			allTags.add(new ListItem("Politics", "Tags related to Politics"));
			allTags.add(new ListItem("Entertainment",
					"Tags related to Entertainment"));
			allTags.add(new ListItem("Podcasts", "Tags related to Podcasts"));
			allTags.add(new ListItem("Sports", "Tags related to Sports"));
			adapter = new ListItemAdapter(getApplicationContext(),
					R.layout.list_item, allTags);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Toast.makeText(getApplicationContext(),
							"Tag selected: " + arg2, Toast.LENGTH_SHORT).show();
				}
			});
			break;
		}
	}

	private void populateList(final ArrayList<String> al) {
		// TODO Auto-generated method stub
		ArrayList<ListItem> topTags = new ArrayList<ListItem>();

		Collections.reverse(al);

		for (String tag : al) {
			topTags.add(new ListItem(tag, "Top tag description"));

		}

		adapter = new ListItemAdapter(getApplicationContext(),
				R.layout.list_item, topTags);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(),
						"Tag selected: " + (arg2 - 1), Toast.LENGTH_SHORT)
						.show();
				String tag = al.get(arg2 - 1);
				Bundle b = new Bundle();
				b.putString("tag", tag);
				i = new Intent(Careers.this, Specific_Workers.class);
				i.putExtras(b);
				startActivity(i);

			}
		});

		listView.onRefreshComplete();
	}

	private void setAdapter() {
		// TODO Auto-generated method stub
		TagsFragmentAdapter adapter = new TagsFragmentAdapter(Careers.this);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(adapter);
		mPager.setCurrentItem(0);

		mIndicator = (TitlePageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.search_menu, menu);
		try {
			SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
			SearchView searchView = (SearchView) menu.findItem(R.id.mSearch)
					.getActionView();

			if (searchView != null) {
				searchView.setSearchableInfo(searchManager
						.getSearchableInfo(getComponentName()));
				searchView.setIconifiedByDefault(false);
				searchView.setIconified(false);
				searchView.setQueryHint("Enter a tag...");
			}

			searchView.setOnQueryTextListener(new OnQueryTextListener() {

				@Override
				public boolean onQueryTextSubmit(String query) {
					// TODO Auto-generated method stub
					try {
						searchForTag(query);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception: " + e.toString());
					}
					return false;
				}

				@Override
				public boolean onQueryTextChange(String newText) {
					// TODO Auto-generated method stub
					return false;
				}
			});

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exeption on Search: " + e.toString());
		}
		return super.onCreateOptionsMenu(menu);
	}

	private void searchForTag(String query) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "Searching", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			i = new Intent(getApplicationContext(), About.class);
			startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}