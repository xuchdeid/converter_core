package com.koalcat.unitconvert_core;

import org.javia.arity.SyntaxException;

import android.content.Context;
import android.util.Log;

public class TemperatureLogic extends BaseLogic{

	public TemperatureLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		ID = 7;
		NAME = "Temperature";
	}

	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculator(String in) {
		// TODO Auto-generated method stub
		/*String out;
			if(this.direction){
				Expression mExpression = new Expression(getinfo(from,to,in));
				out = mExpression.calcuCurrentExpression();
			}else{
				Expression mExpression = new Expression(getinfo(to,from,in));
				out = mExpression.calcuCurrentExpression();
			}
	
		return String.valueOf(out);*/
		
		double out = 0;
		String expression;
		
		if(this.direction){
			expression = getinfo(from,to,in);
		}else{
			expression = getinfo(to,from,in);
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
		ArrayItemId = R.array.temperature;
		if(!MutiLauPlug){
			mTitles = new String[]{"Degree Celsius","Degree Fahrenheit","Kelvin",
				"Degree Rankine","degree Réaumur","degree Rømer"};
			mUnits = new String[]{"°C","°F","K","°R","°Ré","°Rø"};
		}
		T = new String[]{"°C","°F","K","°R","°Ré","°Rø"};
		S = new String[]{"°C = (°F - 32)/1.8","°F = °C*1.8 + 32","K = °C + 273.15",
				"°C = K - 273.15","°R = °C*1.8 + 32 + 459.67","°C = (°R - 32 - 459.67)/1.8",
				"°Ré = °C*0.8","°C = °Ré*1.25","°F = K*1.8 - 459.67",
				"K = (°F + 459.67)/1.8","°F = °R - 459.67","°R = °F + 459.67",
				"°F = °Ré*2.25 + 32","°Ré = (°F - 32)/2.25","K = °R/1.8",
				"°R = K*1.8","K = °Ré*1.25 + 273.15","°Ré = (K - 273.15)*0.8",
				"°R = °Ré*2.25 + 32 + 459.67","°Ré = (°R - 459.67 - 32)/2.25","°F = (°Rø - 7.5)*40/21",
				"°Rø = °F*21/40 + 7.5","°F = (°Rø - 7.5)*24/7 + 32","°Rø = (°F - 32)*7/24 + 7.5",
				"K = (°Rø - 7.5)*40/21 + 273.15","°Rø = (K - 273.15)*21/40 + 7.5","°R = (°Rø - 7.5)*24/7 + 491.67",
				"°Rø = (°R - 491.67)*7/24 + 7.5","°Ré = (°Rø - 7.5)*32/21","°Rø = °Ré*21/32 + 7.5"};
		this.cansetting = false;
		size = T.length;
	}
	
	private String[] T;
	private String[] S;

	
	String replace(String in,String SS){
		String s = SS;
		int id = s.indexOf("=");
		s = s.substring(id + 1, s.length());
		for(int i = 0;i < T.length;i++){
			id = s.indexOf(T[i]);
			if(id != -1){
				s = s.replace(T[i], in);
				break;
			}
		}
		return s;
	}
	
	String replace(int from,String in,String SS){
		String s = SS;
		int id = s.indexOf("=");
		s = s.substring(id + 1, s.length());
		id = s.indexOf(T[from]);
		if(id != -1){
			s = s.replace(T[from], in);
		}else{
			s = "error";
		}
		Log.d("CORE","return s:" + s);
		return s;
	}
	
	String getinfo(int from,int to,String in){
		if(from == to)
		{
			return String.valueOf(in);	
		}
		else
		{
			int result = from*10 + to;
			switch(result)
			{
			case 10:return replace(from,in,S[0]);
			case 1:return this.replace(from,in,S[1]);
			case 2:return replace(from,in,S[2]);
			case 20:return replace(from,in,S[3]);
			case 3:return replace(from,in,S[4]);
			case 30:return replace(from,in,S[5]);
			case 4:return replace(from,in,S[6]);
			case 40:return replace(from,in,S[7]);
			case 21:return replace(from,in,S[8]);
			case 12:return replace(from,in,S[9]);
			case 31:return replace(from,in,S[10]);
			case 13:return replace(from,in,S[11]);
			case 41:return replace(from,in,S[12]);
			case 14:return replace(from,in,S[13]);
			case 32:return replace(from,in,S[14]);
			case 23:return replace(from,in,S[15]);
			case 42:return replace(from,in,S[16]);
			case 24:return replace(from,in,S[17]);
			case 43:return replace(from,in,S[18]);
			case 34:return replace(from,in,S[19]);
			case 50:return replace(from,in,S[20]);
			case 5:return replace(from,in,S[21]);
			case 51:return replace(from,in,S[22]);
			case 15:return replace(from,in,S[23]);
			case 52:return replace(from,in,S[24]);
			case 25:return replace(from,in,S[25]);
			case 53:return replace(from,in,S[26]);
			case 35:return replace(from,in,S[27]);
			case 54:return replace(from,in,S[28]);
			case 45:return replace(from,in,S[29]);
			}	
		}
		return "error";
	
	}
	
	@Override
	protected String getinfo(){
		if(from == to){
			return T[from] + " = " + T[to];	
		}else{
			int result = from*10 + to;
			switch(result){
			case 10:return S[0];
			case 1:return S[1];
			case 2:return S[2];
			case 20:return S[3];
			case 3:return S[4];
			case 30:return S[5];
			case 4:return S[6];
			case 40:return S[7];
			case 21:return S[8];
			case 12:return S[9];
			case 31:return S[10];
			case 13:return S[11];
			case 41:return S[12];
			case 14:return S[13];
			case 32:return S[14];
			case 23:return S[15];
			case 42:return S[16];
			case 24:return S[17];
			case 43:return S[18];
			case 34:return S[19];
			case 50:return S[20];
			case 5:return S[21];
			case 51:return S[22];
			case 15:return S[23];
			case 52:return S[24];
			case 25:return S[25];
			case 53:return S[26];
			case 35:return S[27];
			case 54:return S[28];
			case 45:return S[29];
			}
		}
		return "error";
	}

}
