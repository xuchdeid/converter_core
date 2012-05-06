package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class PowerLogic extends BaseLogic{

	public PowerLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 4;
		NAME = "Power";
	}
	
	private double[] l = {Utils.power.W,Utils.power.KW,Utils.power.mw,Utils.power.HP,Utils.power.hp,Utils.power.PS,
			Utils.power.KGM_S,Utils.power.KCAL_S,Utils.power.BTU_S,Utils.power.BTU_m,Utils.power.BTU_h,
			Utils.power.FTLB_S,Utils.power.FTLB_m,Utils.power.FTLB_h,Utils.power.J_S,Utils.power.NM_S,
			Utils.power.NM_M,Utils.power.NM_H};

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
		ArrayItemId = R.array.power;
		if(!MutiLauPlug){
			mTitles = new String[]{"Watt","Kilowatt","Megawatt","Metric Horsepower","Horsepower","Pferdestarke","Kilogram Meter/Second",
				"Kilocalorie/Second","BTU/Second","BTU/Minute","BTU/Hour","Foot-Pound/Second","Foot-Pound/Minute","Foot-Pound/Hour",
				"Joule/Second","Newton Meter/Second","Newton Meter/Minute","Newton Meter/Hour"};
			mUnits = new String[]{"W","kW","MW","hp","hp","PS","kg.m/s","kcal/s","BTU/s","BTU/min","BTU/h","ft.lb/s","ft.lb/min","ft.lb/h",
				"J/s","N.m/s","N.m/min","N.m/h"};
		}
		this.cansetting = false;
	}

}