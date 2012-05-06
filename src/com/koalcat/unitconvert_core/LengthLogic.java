package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class LengthLogic extends BaseLogic{

	public LengthLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 2;
		NAME = "Length";
	}
	
	private double[] l = {Utils.Length.M,Utils.Length.KM,Utils.Length.DM,Utils.Length.CM,Utils.Length.MM,Utils.Length.UM,Utils.Length.NM,
			Utils.Length.A,Utils.Length.NMI,Utils.Length.nmi,Utils.Length.MI,Utils.Length.FG,Utils.Length.FM,Utils.Length.YD,Utils.Length.IN,Utils.Length.FT,
			Utils.Length.AU,Utils.Length.ly,Utils.Length.pc,Utils.Length.ch,Utils.Length.fem,Utils.Length.lea,Utils.Length.mil,
			Utils.Length.ell,Utils.Length.xu,Utils.Length.rd,Utils.Length.nl,Utils.Length.pace,Utils.Length.palm,Utils.Length.link1,Utils.Length.link2
	};

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
		ArrayItemId = R.array.length;
		if(!MutiLauPlug){
			mTitles = new String[]{"Meter","KiloMeter","DeciMete","Centimetre","MilliMeter","Micron",
				"Nanometer","Ångström","Nautical Mile(international)","Nautical Mile(Admiralty)","Mile","Furlong","Fathom","Yard",
				"Inch","Foot","Astronomical Unit","Light-Year","Parsec","Chain","Fermi","League","Mil",
				"ell(H)","x unit","rod;pole;perch","Nautical League","Pace","Palm","Link(Gunter's;Surveyor's)",
				"Link(Ramsden's;Engineer's)"};
			mUnits = new String[]{"m","km","dm","cm","mm","µm","nm","Å",
				"nmi","nmi","mi","fur","fm","yd","in","ft","AU","ly","pc","ch","fm",
				"lea","mil","ell","xu","rd","nl","pace","palm","lnk","lnk"};
		}
		this.cansetting = false;
	}

}
