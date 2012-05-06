package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class AreaLogic extends BaseLogic{

	public AreaLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		size = l.length;
		ID = 0;
		NAME = "Area";
		// TODO Auto-generated constructor stub
	}
	
	private double[] l = {Utils.area.M2,Utils.area.CM2,Utils.area.KM2,
			Utils.area.HA,Utils.area.ACRE,Utils.area.ac,Utils.area.OM,Utils.area.MILE2,Utils.area.mile2,Utils.area.FT2,Utils.area.ft2,Utils.area.YD2,
			Utils.area.are,Utils.area.dunam,Utils.area.b,Utils.area.barony,Utils.area.bd,Utils.area.circin,Utils.area.cord,
			Utils.area.guntha,Utils.area.ro,Utils.area.sqrd,Utils.area.sqmil,Utils.area.sqchi,Utils.area.sqchu};

	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return (float)(l[to]/l[from]);
	}

	@Override
	public String calculator(String in) {
		// TODO Auto-generated method stub
		double out = 0;
		String expression;
		//String out;
		//try {
			if(this.direction){
				//Expression mExpression = new Expression(in + "*" + l[from]/l[to]);
				//out = mExpression.calcuCurrentExpression();
				expression = in + "*" + l[from]/l[to];
			}else{
				//Expression mExpression = new Expression(in + "*" + l[to]/l[from]);
				//out = mExpression.calcuCurrentExpression();
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
		ArrayItemId = R.array.area;
		if(!MutiLauPlug){
			mTitles = new String[]{"Square Meter","Square Decimeter","Square Kilometer","Hectare","Acre(international)","Acre(US survey)",
					"Square Inch","Square Mile","Square Mile(US Survey)","Square Mil","Square Foot","Square Foot(US Survey)","Square Yard",
					"Are","Dunam","Barn","Barony","Board","Circular Inch",
					"Cord","Guntha","Rood","Square Rod/Pole/Perch","Square Chain(international)","Square Chain(US Survey)"};
			mUnits = new String[]{"m<sup>2</sup>","cm<sup>2</sup>","km<sup>2</sup>","ha","ac","ac",
					"sq in","sq mi","sq mi","sq mil","sq ft","sq ft","sq yd","a","dunam","b","barony","bd","circ in",
					"cord","guntha","ro","sq rd","sq ch","sq ch"};
		}
		this.cansetting = false;

	}

}