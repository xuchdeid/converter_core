package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class WeightLogic extends BaseLogic{

	public WeightLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 10;
		NAME = "Weight";
	}
	
	private double[] l = {Utils.Weight.G,Utils.Weight.KG,Utils.Weight.TON,Utils.Weight.MG,
			Utils.Weight.UG,Utils.Weight.LB,Utils.Weight.OZ,Utils.Weight.CT,Utils.Weight.GR,Utils.Weight.IT,Utils.Weight.ST,
			Utils.Weight.YINGSHI,Utils.Weight.DR,Utils.Weight.drt,Utils.Weight.ozt,Utils.Weight.ozav,
			Utils.Weight.lbt,Utils.Weight.sap,Utils.Weight.gr,Utils.Weight.slug,Utils.Weight.kip,Utils.Weight.dwt,
			Utils.Weight.gamma,Utils.Weight.grave
	};

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
		ArrayItemId = R.array.weight;
		if(!MutiLauPlug){
			mTitles = new String[]{"Gram","Kilogram","Tonne","Milligram","Microgram",
				"Pound(us)","Ounce(us)","Carat","Grain","Long Ton","Short Ton","Stone","Dram(us)","Dram(Troy)",
				"Ounce(Troy)","Ounce(avoirdupois)","Pound(Troy)","Scruple","Grain","Slug","Kip","Pennyweight",
				"Gamma","Grave"};
			mUnits = new String[]{"g","kg","t","mg","µg","lb",
				"oz","ct","gr","ton","sh tn","st","dr av","dr t",
				"oz t","oz av","lb t","s ap","gr","slug","kip","dwt","γ","G"};
		}
		this.cansetting = false;
	}

}
