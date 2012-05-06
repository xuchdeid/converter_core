package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class TimeLogic extends BaseLogic{

	public TimeLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 8;
		NAME = "Time";
	}
	
	private double[] l = {Utils.Time.S,Utils.Time.MIN,Utils.Time.H,Utils.Time.D,
			Utils.Time.WEEK,Utils.Time.YEAR,Utils.Time.MS,Utils.Time.US,
			Utils.Time.NS,Utils.Time.PS,Utils.Time.FS};

	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return (float)(l[to]/l[from]);
	}

	@Override
	public String calculator(String in) {
		// TODO Auto-generated method stub
		/*String out;
			if(this.direction){
				Expression mExpression = new Expression(in + "*" + l[from]/l[to]);
				out = mExpression.calcuCurrentExpression();
			}else{
				Expression mExpression = new Expression(in + "*" + l[to]/l[from]);
				out = mExpression.calcuCurrentExpression();
			}
		
		return String.valueOf(out);*/
		
		double out = 0;
		String expression;
		
		if(this.direction){
			expression = in + "*" + l[from]/l[to];
		}else{
			expression = in + "*" + l[to]/l[from];
		}
		
		try {
			out = mSymbols.eval(expression);
			return String.valueOf(out);
		} catch (SyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	protected void InitTitlesAndUnits() {
		// TODO Auto-generated method stub
		ArrayItemId = R.array.time;
		if(!MutiLauPlug){
			mTitles = new String[]{"Second","Minute","Hour","Day","Week","Year","MilliSecond",
				"MicroSecond","NanoSecond","Picosecond","Femtosecond"};
			mUnits = new String[]{"s","min","h","d","week","year","ms","µs","ns","ps","fs"};
		}
		this.cansetting = false;
	}

}