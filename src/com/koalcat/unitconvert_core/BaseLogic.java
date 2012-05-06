package com.koalcat.unitconvert_core;

/**
 * @author xuchdeid@gmail.com
 *  __________________________     \_/
   |                          |   /._.\
   |  Android!Android!         > U|   |U
   |                xuchdeid  |   |___|
   |__________________________|    U U
 * */

import org.javia.arity.Symbols;

import android.content.Context;
import android.widget.TextView;

public abstract class BaseLogic {
	protected boolean direction;
	protected boolean cansetting;
	protected String[] mTitles;
	protected String[] mUnits;
	protected int from,to;
	protected int ID;
	protected String NAME;
	protected TextView vfrom,vto,vinfo;
	protected int ArrayItemId;
	protected Context context;
	protected int size;
	protected Symbols mSymbols;
	public boolean MutiLauPlug;
	
	public BaseLogic(Context context,boolean MutiLauPlug){
		this.context = context;
		this.MutiLauPlug = MutiLauPlug;
		
		InitTitlesAndUnits();
	}
	
	public int getArrayItemId(){
		return ArrayItemId;
	}
	
	public int size(){
		return size;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getNAME(){
		return NAME;
	}
	
	public void InitTextView(TextView vfrom,TextView vto,TextView vinfo){
		this.vfrom = vfrom;
		this.vto = vto;
		this.vinfo = vinfo;
		this.direction = true;
		
		setFromAndTo(0,0);
	}
	
	public void setDirection(boolean direction){
		this.direction = direction;
	}
	
	public boolean getSettingable(){
		return cansetting;
	} 
	
	public String getTitle(int id){
		return mTitles[id];
	}
	
	public String getUnits(int id){
		return mUnits[id];
	}
	
	protected String getinfo(){

		return mTitles[from] + ":" + mTitles[to] +
				"=" + "1:" + getfl();
	}
	
	abstract Object getfl();
	
	public void setFromAndTo(int from,int to){
		if(mSymbols == null){
			mSymbols = new Symbols();
		}
		this.from = from;
		this.to = to;
		if(vfrom != null){
			vfrom.setText(mUnits[from]);
		}
		if(vto != null){
			vto.setText(mUnits[to]);
		}
		if(vinfo != null){
			vinfo.setText(getinfo());
		}
	}
	
	public abstract String calculator(String in);
	protected abstract void InitTitlesAndUnits();
	
	public void writeRate(int from,int to,double rate){
		
	}
}
