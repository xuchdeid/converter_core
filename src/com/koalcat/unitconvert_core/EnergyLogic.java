package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class EnergyLogic extends BaseLogic{

	public EnergyLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 1;
		NAME = "Energy";
	}
	
	private double[] l = {Utils.energy.J,Utils.energy.kj,Utils.energy.mj,Utils.energy.BTU,Utils.energy.ERG,Utils.energy.THERM,Utils.energy.THERM1,
			Utils.energy.KWH,Utils.energy.FTLB,Utils.energy.ftpdl,Utils.energy.inlb,Utils.energy.cal,Utils.energy.kcal,Utils.energy.ev,
			Utils.energy.ha,Utils.energy.quad,Utils.energy.hph,Utils.energy.tce,
			Utils.energy.toe,Utils.energy.ttnt,Utils.energy.bboe};

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
			
			Expression mExpression = new Expression(in + "*" +l[from]/l[to]);
			out = mExpression.calcuCurrentExpression();
				
		}else{
			Expression mExpression = new Expression(in + "*" +l[to]/l[from]);
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
		ArrayItemId = R.array.energy;
		if(!MutiLauPlug){
			mTitles = new String[]{"Joule","Kilojoule","Megajoule","British Thermal Unit",
					"Erg","Therm(E.C.)","Therm(US)","Kilowatt-Hour","Foot-Pound Force","Foot-Poundal",
					"Inch-Pound Force","Calorie","Kilocalorie","Electronvolt","Hartree",
					"Quad","Horsepower-Hour","Ton of Coal Equivalent","Ton of Oil Equivalent",
					"Ton of TNT","Barrel of Oil Equivalent"};
			mUnits = new String[]{"J","kJ","MJ","BTU","erg","therm","therm","kW-h","ft lbf","ft pdl",
					"in lbf","cal","kcal","eV","Ha","quad","hp.h",
					"TCE","TOE","tTNT","bboe"};
		}
		this.cansetting = false;
	}

}