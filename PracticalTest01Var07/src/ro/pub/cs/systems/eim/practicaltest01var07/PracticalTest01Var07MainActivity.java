package ro.pub.cs.systems.eim.practicaltest01var07;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;


public class PracticalTest01Var07MainActivity extends Activity {
	
	private CheckBox checkName, checkGroup;
	private EditText textName, textGroup;
	
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
