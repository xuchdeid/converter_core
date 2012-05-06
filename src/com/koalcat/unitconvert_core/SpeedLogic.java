package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class SpeedLogic extends BaseLogic{

	public SpeedLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 6;
		NAME = "Speed";
	}
	private double[] l = {Utils.Speed.MPS,Utils.Speed.KMPS,Utils.Speed.KMPH,
			Utils.Speed.INPS,Utils.Speed.ipm,Utils.Speed.iph,
			Utils.Speed.FTPS,Utils.Speed.FTPM,Utils.Speed.FTPH,
			Utils.Speed.mps,Utils.Speed.mpm,Utils.Speed.MIPH,Utils.Speed.Kn,Utils.Speed.kn,
			Utils.Speed.C};
	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return (float)(l[to]/l[from]);
	}
	
	@Override
	public String calculator(String in) {
		// TODO Auto-generated method stub
		//double out = 0;
		/*String out;
		//try {
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
		ArrayItemId = R.array.speed;
		if(!MutiLauPlug){
			mTitles = new String[]{"Meters per second","Kilometers per second","Kilometers per hour",
				"Inch per second","Inch per Minute","Inch per Hour","Foot per Second","Foot per Minute",
				"Foot per Hour","Mile per Second","Mile per Minute","Mile per Hour","Knot",
				"Knot(Admiralty)","Velocity of light"};
			mUnits = new String[]{"m/s","km/s","km/h","ips","ipm","iph","fps","fpm","fph","mps","mpm","mph","kt","kn","c"};
		}
		this.cansetting = false;
	}

}
