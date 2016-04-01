package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01Var07MainActivity extends Activity {
	
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	private boolean serviceStarted;
	private IntentFilter intentFilter = new IntentFilter();
	
	private CheckBox checkName, checkGroup;
	private EditText textName, textGroup;
	
	//listener pentru checkbox-uri
	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (buttonView.getId() == R.id.check_name) {
				if (checkName.isChecked())
					textName.setEnabled(true);
				else
					textName.setEnabled(false);
			}
			else if (buttonView.getId() == R.id.check_group) {
				if (checkGroup.isChecked())
					textGroup.setEnabled(true);
				else
					textGroup.setEnabled(false);
			}
			
			//daca ambele checkbox-uri sunt debifate
			if (!checkName.isChecked() && !checkGroup.isChecked() && !serviceStarted) {
				Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
				intent.putExtra("textName", textName.getText().toString());
				intent.putExtra("textGroup", textGroup.getText().toString());
				
				startService(intent);
				serviceStarted = true;
			}
		}
	};
	
	//listener pentru butonul de trecut la a doua activitate
	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.button_navigate) {
				Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
				intent.putExtra("textName", textName.getText().toString());
				intent.putExtra("textGroup", textGroup.getText().toString());
				
				startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
			}
		}
	};
	
	//broadcast receiver pentru serviciu
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.d("[Message]", intent.getStringExtra("message"));
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var07_main);
		
		checkName = (CheckBox)findViewById(R.id.check_name);
		checkGroup = (CheckBox)findViewById(R.id.check_group);
		
		textName = (EditText)findViewById(R.id.text_name);
		textGroup = (EditText)findViewById(R.id.text_group);
		
		//atasam listener-ul la checkbox-uri
		checkName.setOnCheckedChangeListener(onCheckedChangeListener);
		checkGroup.setOnCheckedChangeListener(onCheckedChangeListener);
		
		((Button)findViewById(R.id.button_navigate)).setOnClickListener(onClickListener);
		
		//punem actiunile pentru IntentFilter-ul asociat serviciului
		intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01var07.name");
		intentFilter.addAction("ro.pub.cs.systems.eim.practicaltest01var07.group");
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putBoolean("checkName", checkName.isChecked());
		savedInstanceState.putString("textName", textName.getText().toString());
		savedInstanceState.putBoolean("checkGroup", checkGroup.isChecked());
		savedInstanceState.putString("textGroup", textGroup.getText().toString());
	}
	
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey("checkName"))
			checkName.setChecked(savedInstanceState.getBoolean("checkName"));
		else
			checkName.setChecked(false);
		
		if (savedInstanceState.containsKey("textName"))
			textName.setText(savedInstanceState.getString("textName"));
		else
			textName.setText("");
		
		if (savedInstanceState.containsKey("checkGroup"))
			checkGroup.setChecked(savedInstanceState.getBoolean("checkGroup"));
		else
			checkGroup.setChecked(false);
		
		if (savedInstanceState.containsKey("textGroup"))
			textGroup.setText(savedInstanceState.getString("textGroup"));
		else
			textGroup.setText("");
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE)
			Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(broadcastReceiver, intentFilter);
	}
	
	
	@Override
	protected void onPause() {
		unregisterReceiver(broadcastReceiver);
		super.onPause();
	}
	
	
	@Override
	protected void onDestroy() {
		Intent intent = new Intent(this, PracticalTest01Var07Service.class);
		stopService(intent);
		
		super.onDestroy();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var07_main, menu);
		
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;
		
		return super.onOptionsItemSelected(item);
	}
	
}
