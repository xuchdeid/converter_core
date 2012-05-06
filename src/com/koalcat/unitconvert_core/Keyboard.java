package com.koalcat.unitconvert_core;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import android.view.View;

public class Keyboard implements View.OnClickListener, View.OnLongClickListener{
	
	public static int ONCLICK = 0;
	public static int ONLONGCLICK = 1;
	private Map<String,String> mOnClickKeys,mOnLongClickKeys;
	private IOManager mIOManager;

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		String id = v.toString();
		if(mOnLongClickKeys != null){
			OnKeyDown(mOnLongClickKeys.get(id));
		}else{
			Log.d("CORE","mOnLongClickKeys is null");
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String id = v.toString();
		if(mOnClickKeys != null){
			OnKeyDown(mOnClickKeys.get(id));
		}else{
			Log.d("CORE","mOnClickKeys is null");
		}
	}
	
	private void OnKeyDown(String key){
		if(key != null){
			if(mIOManager != null){
				//Log.d("CORE","input " + key);
				mIOManager.InputKey(key);
			}else{
				Log.d("CORE","mIOManager is null");
			}
		}else{
			Log.d("CORE","key is null");
		}
	}
	
	public void registerIOManager(IOManager mIOManager){
		this.mIOManager = mIOManager;
	}
	
	public void registerKey(View Key,String key,int station){
		if(station == ONCLICK){
			if(mOnClickKeys == null){
				mOnClickKeys = new HashMap<String,String>();
			}
			mOnClickKeys.put(Key.toString(), key);
			Key.setOnClickListener(this);
		}
		if(station == ONLONGCLICK){
			if(mOnLongClickKeys == null){
				mOnLongClickKeys = new HashMap<String,String>();
			}
			mOnLongClickKeys.put(Key.toString(), key);
			Key.setOnLongClickListener(this);
		}
	}

}
