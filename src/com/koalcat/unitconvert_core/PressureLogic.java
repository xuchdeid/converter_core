package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class PressureLogic extends BaseLogic{

	public PressureLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 5;
		NAME = "Pressure";
	}
	
	private double[] l = {Utils.pressure.PA,Utils.pressure.KPA,Utils.pressure.MPA,Utils.pressure.B,Utils.pressure.MB,Utils.pressure.ATM,
			Utils.pressure.MMHG,Utils.pressure.cmhg,Utils.pressure.INHG,Utils.pressure.mmh2o,Utils.pressure.cmh2o,Utils.pressure.inh2o,
			Utils.pressure.PSI,Utils.pressure.PSF,Utils.pressure.torr,Utils.pressure.kgcm,Utils.pressure.kgm};

	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return (float)(l[to]/l[from]);
	}

	@Override
	public String calculator(String in) 
	{
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
		ArrayItemId = R.array.pressure;
		if(!MutiLauPlug){
			mTitles = new String[]{"Pascal","KiloPascal","MPa","Bar","MBar","Atmosphere",
				"MM of Mercury","CM of Mercury","Inch of Mercury","MM of Water","CM of Water","Inch of Water",
				"Pound/Square Inch","Pound/Square Foot","Torr","KG/Square CM","KG/Square Meter"};
			mUnits = new String[]{"Pa","kPa","MPa","bar","Mb","atm","mmHg","cmHg","inHg","mmH<SUB>2</SUB>O",
					"cmH<SUB>2</SUB>O","inH<SUB>2</SUB>O",
				"psi","psf","torr","kg/cm<sup>2</sup>","kg/m<sup>2</sup>"};
		}
		this.cansetting = false;
	}

}
