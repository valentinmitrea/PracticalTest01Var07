package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class PracticalTest01Var07SecondaryActivity extends Activity {
	
	private TextView textViewName, textViewGroup;
	private Button okButton, cancelButton;
	
	
	private OnClickListener onClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.button_ok) {
				setResult(RESULT_OK, null);
				finish();
			}
			else if (v.getId() == R.id.button_cancel) {
				setResult(RESULT_CANCELED, null);
				finish();
			}
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var07_secondary);
		
		textViewName = (TextView)findViewById(R.id.text_view_name);
		textViewGroup = (TextView)findViewById(R.id.text_view_group);
		okButton = (Button)findViewById(R.id.button_ok);
		cancelButton = (Button)findViewById(R.id.button_cancel);
		
		//extragem informatiile primite de la Intent-ul cu care a fost pornita activitatea
		Intent intent = getIntent();
		if (intent != null && intent.getExtras().containsKey("textName") && intent.getExtras().containsKey("textGroup")) {
			textViewName.setText(intent.getExtras().getString("textName"));
			textViewGroup.setText(intent.getExtras().getString("textGroup"));
		}
		
		//setam listener pentru butoane
		okButton.setOnClickListener(onClickListener);
		cancelButton.setOnClickListener(onClickListener);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater()
				.inflate(R.menu.practical_test01_var07_secondary, menu);
		
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
