package ro.pub.cs.systems.eim.practicaltest01var07;

import java.sql.Date;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

 
public class PracticalTest01Var07Service extends Service {
 
    private ProcessingThread processingThread = null;
    
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	String textName = intent.getExtras().getString("textName");
    	String textGroup = intent.getExtras().getString("textGroup");
    	
    	processingThread = new ProcessingThread(this, textName, textGroup);
    	processingThread.start();
    	
    	return Service.START_REDELIVER_INTENT;
    }
    
    
    @Override
    public IBinder onBind(Intent intent) {
    	return null;
    }
    
    
    @Override
    public void onDestroy() {
    	processingThread.stopThread();
    }
    
    
    class ProcessingThread extends Thread {
    	
    	private Context context;
    	private String textName;
    	private String textGroup;
    	private boolean flag, isRunning = true;
    	
    	
    	public ProcessingThread(Context context, String textName, String textGroup) {
    		this.context = context;
    		this.textName = textName;
    		this.textGroup = textGroup;
    	}
    	
    	
    	@Override
    	public void run() {
    		Log.d("[ProcessingThread]", "Thread has started!");
    		
    		while (isRunning) {
    			Intent intent = new Intent();
    			if (flag == false) {
    				intent.setAction("ro.pub.cs.systems.eim.practicaltest01var07.name");
    				intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + textName);
    				flag = true;
    			}
    			else {
    				intent.setAction("ro.pub.cs.systems.eim.practicaltest01var07.group");
    				intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + textGroup);
    				flag = false;
    			}
    			
    			context.sendBroadcast(intent);
    			
    			try {
					Thread.sleep(5000);
				}
    			catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    		Log.d("[ProcessingThread]", "Thread has stopped!");
    	}
    	
    	
    	public void stopThread() {
    		isRunning = false;
    	}
    	
    }

}