package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;

public class VolumeLogic extends BaseLogic{

	public VolumeLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = l.length;
		ID = 9;
		NAME = "Volume";
	}

	private double[] l = {Utils.volume.M3,Utils.volume.L,Utils.volume.CM3,Utils.volume.FT3,
			Utils.volume.IN3,Utils.volume.yd3,Utils.volume.UKGAL,Utils.volume.USGAL,Utils.volume.galusdry,Utils.volume.USBBL,
			Utils.volume.fbuk,Utils.volume.fbus,Utils.volume.usbl,Utils.volume.bkt,Utils.volume.bu,Utils.volume.cus,
			Utils.volume.cim,Utils.volume.cca,Utils.volume.cme,Utils.volume.tspus,Utils.volume.tspim,Utils.volume.tspca,Utils.volume.tspme,
			Utils.volume.tspus,Utils.volume.tbspim,Utils.volume.tbspca,Utils.volume.tbspme,Utils.volume.flozus,Utils.volume.flozim,
			Utils.volume.dashim,Utils.volume.dashus,Utils.volume.dropim,Utils.volume.dropmed,Utils.volume.dropmet,Utils.volume.dropus,
			Utils.volume.piim,Utils.volume.pius,Utils.volume.ptim,Utils.volume.ptusd,Utils.volume.ptusf,Utils.volume.qtim,Utils.volume.qtusd,
			Utils.volume.qtusf,Utils.volume.giim,Utils.volume.gius,Utils.volume.hhdim,Utils.volume.hhdus,Utils.volume.fldrim,Utils.volume.fldrus,
			Utils.volume.fls
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
		ArrayItemId = R.array.volume;
		if(!MutiLauPlug){
			mTitles = new String[]{"Cubic Meter","Liter","Cubic Centimeter","Cubic Foot","Cubic Inch","Cubic Yard",
				"Gallon(Imperial)","Gallon(US Fluid)","Gallon(US dry)","Barrel(petroleum)","Barrel(imperial)","Barrel(US fluid)","Barrel(US dry)",
				"Bucket(Imperial)","Bushel(Imperial)","Cup(US)","Cup(Imperial)","Cup(Canadian)","Cup(Metric)","Teaspoon(US)",
				"Teaspoon(Imperial)","Teaspoon(Canadian)","Teaspoon(Metric)","Tablespoon(US)","Tablespoon(Imperial)","Tablespoon(Canadian)",
				"Tablespoon(Metric)","Fluid Ounce(US)","Fluid Ounce(Imperial)","Dash(Imperial)","Dash(US)",
				"Drop(imperial)","Drop(mediacal)","Drop(metric)","Drop(US)","Pinch(imperial)","Pinch(US)",
				"Pint(imperial)","Pint(US dry)","Pint(US fluid)","Quart(imperial)","Quart(US dry)","Quart(US fluid)",
				"Gill(imperial)","Gill(US)","Hogshead(imperial)","Hogshead(US)","Fluid Drachm(imperial)","Fluid Dram(US)","Fluid Scruple(imperial)"};
			mUnits = new String[]{"m<sup>3</sup>","L","cm<sup>3</sup>","ft<sup>3</sup>",
					"in<sup>3</sup>",
				"yd<sup>3</sup>","gal","gal","gal","bl","bl","fl bl","bl","bkt","bu",
				"c","c","c","c","tsp","tsp","tsp","tsp","tbsp","tbsp",
				"tbsp","tbsp","fl oz","fl oz","dash","dash","drop","drop","drop","drop",
				"pinch","pinch","pt","pt","pt","qt","qt","qt","gi","gi",
				"hhd","hhd","fl dr","fl dr","fl s"};
		}
		this.cansetting = false;
	}

}