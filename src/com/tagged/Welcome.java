package com.tagged;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class Welcome extends SherlockActivity {

	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);
		getSupportActionBar().hide();

		checkUserRegistration();
		setUp();
	}

	private void checkUserRegistration() {
		// TODO Auto-generated method stub
		SharedPreferences sp = this.getSharedPreferences(getPackageName(),
				MODE_PRIVATE);
		Boolean registered = sp.getBoolean("registered", false);
		if (registered == true) {
			Intent i = new Intent(getApplicationContext(), Careers.class);
			startActivity(i);
			finish();
		}

	}

	private void setUp() {
		// TODO Auto-generated method stub
		Typeface font = Typeface.createFromAsset(getAssets(),
				"fonts/scribblet.otf");
		
		TextView tvTagged = (TextView) findViewById(R.id.tvTagged);
		tvTagged.setTypeface(font);
		
		Button bContinue = (Button) findViewById(R.id.bContinue);
		bContinue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i = new Intent(getApplicationContext(), Careers.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
