package com.koalcat.unitconvert_core;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.javia.arity.SyntaxException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.TextView;

public class MoneyLogic extends BaseLogic{
	
	enum State{
		init,
		run,
		fail,
		success
	};
	
	private State mState = State.init;
	//private boolean sync = false;
	private String URL1 = "http://hk.finance.yahoo.com/d/quotes.csv";
	private String URL2 = "http://download.finance.yahoo.com/d/quotes.csv";
	//private String URL3 = "http://api.liqwei.com/currency/?exchange=CNY|USD&count=1";
	private MoneySyncListener mMoneySyncListener;

	public MoneyLogic(Context context,boolean MutiLauPlug) {
		super(context,MutiLauPlug);
		// TODO Auto-generated constructor stub
		size = 153;
		ID = 3;
		NAME = "Currency";
		AllSync = new boolean[size];
		for(int i = 0;i < size;i++){
			AllSync[i] = false;
		}
	}
	
	public void setMoneySyncListener(MoneySyncListener mMoneySyncListener){
		this.mMoneySyncListener = mMoneySyncListener;
		if(mMoneySyncListener != null){
			if(mState == State.init){
				mMoneySyncListener.MoneySyncInit();
			}else if(mState == State.run){
				mMoneySyncListener.MoneySyncStart();
			}else if(mState == State.success){
				mMoneySyncListener.MoneySyncSuccess();
			}else if(mState == State.fail){
				mMoneySyncListener.MoneySyncFailed();
			}
		}
	}

	public void InitTextView(TextView vfrom, TextView vto, TextView vinfo) {
		super.InitTextView(vfrom, vto, vinfo);
		
		// TODO Auto-generated constructor stub
	}
	
	private double l[] = {Utils.money.USD,
			Utils.money.CNY,
			Utils.money.EUR,
			Utils.money.HKD,
			Utils.money.GBP,
			Utils.money.JPY,
			Utils.money.TWD,
			Utils.money.ALL,
			Utils.money.BBD,
			Utils.money.BDT,
			Utils.money.BGN,
			Utils.money.BHD,
			Utils.money.BMD};

	@Override
	Object getfl() {
		// TODO Auto-generated method stub
		return (float)getRate(from,to);
	}
	
	private SharedPreferences mData;
	private SharedPreferences mCurrency,mSyncDate,mSyncState;
	
	public float getRate(int from,int to){//from:to=1:return
		if(from != to){
			String fresult = mCurrency.getString(Units[from] + Units[0], null);
			String tresult = mCurrency.getString(Units[to] + Units[0], null);
			if(from == 0){
				fresult = "1";
			}
			if(to == 0){
				tresult = "1";
			}
			Log.d("CORE",mUnits[from] + mUnits[0] + " fresult:" + fresult + " " + mUnits[to] + mUnits[0] +" tresult:" + tresult);
			if(fresult == null || tresult == null){
				if(to >= l.length || from >= l.length){
					return 0;
				}else{
					return 0;
				}
			}else{
				double f = Double.valueOf(fresult);
				double t = Double.valueOf(tresult);
				if(String.valueOf((float)(t/f)).equals("Infinity") ){
					Log.d("CORE","error Infinity");
					//Log.d("MM",""+R.string.numform+""+(float) (l[to]/l[from]));
					//Toast.makeText(this.context, R.string.numform, Toast.LENGTH_LONG).show();
					if(to >= l.length || from >= l.length){
						return 0;
					}else{
						return 0;
					}
				}
				else{
					if(f == 0){
						return 0;
					}else{
						return (float) (t/f);
					}
				}
			}
		}else{
			return 1;
		}
	}
	
	@Override
	public void writeRate(int from,int to,double rate){
		//float out = (float) (getRate(from)*rate);
		if(from != to){
			if(rate != 0){
				if(String.valueOf((float)rate).equals("Infinity")){
					//Toast.makeText(this.context, R.string.numform, Toast.LENGTH_LONG).show();
					if(to >= l.length || from >= l.length){
						rate =  1;
					}else{
						rate = 1;
					}
				}
				Editor editor = mData.edit();
				editor.putString(Units[to] + Units[from], "" + rate);
				//editor.putString(mUnits[to] + ":" + mUnits[from], rate + ":1");
				//editor.putFloat(mUnits[to], out);
				editor.commit();
				//Log.d("xuchdeid","write:" + mUnits[to] + ":" + rate);
			}
		}else{
			Editor editor = mData.edit();
			editor.putString(Units[to] + Units[from], "1.0");
			editor.commit();
		}
	}
	
	public void WriteRate(int from,int to,double rate){
		if(from != to){
			if(rate != 0){
				if(String.valueOf((float)rate).equals("Infinity")){
					//Toast.makeText(this.context, R.string.numform, Toast.LENGTH_LONG).show();
					if(to >= l.length || from >= l.length){
						rate =  1;
					}else{
						rate = 1;
					}
				}
				Log.d("CORE","write rate:" + Units[to] + Units[from] + " " + rate);
				Editor editor = mCurrency.edit();
				editor.putString(Units[to] + Units[from], "" + rate);
				editor.commit();
			}
		}else{
			Editor editor = mCurrency.edit();
			editor.putString(Units[to] + Units[from], "1.0");
			editor.commit();
		}
	}

	@Override
	public String calculator(String in) {
		// TODO Auto-generated method stub
		//double out = 0;
		/*String out;
		//try {
			if(this.direction){
				Expression mExpression = new Expression(in + " * " + getRate(to,from));
				out = mExpression.calcuCurrentExpression();
			}else{
				Expression mExpression = new Expression(in + " * " + getRate(from,to));
				out = mExpression.calcuCurrentExpression();
			}
		
		return String.valueOf(out);*/
		
		double out = 0;
		String expression;
		
		if(this.direction){
			expression = in + " * " + getRate(to,from);
		}else{
			expression = in + " * " + getRate(from,to);
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
	
	private String time;
	
	@Override
	protected void InitTitlesAndUnits() {
		// TODO Auto-generated method stub
		mData = context.getSharedPreferences("money", 0);
		
		mCurrency = context.getSharedPreferences("currency", 0);
		mSyncDate = context.getSharedPreferences("date", 0);
		mSyncState  = context.getSharedPreferences("state", 0);
		
		Date date=new Date();
		SimpleDateFormat matter=new SimpleDateFormat("MM/dd/yyyy");
		time = matter.format(date);
		
		ArrayItemId = R.array.money;
		if(!MutiLauPlug){
			mTitles = new String[]{"United States Dollar","Australian Dollar","Euro","British Pound",
					"Canadian Dollar","Chinese Yuan","Hong Kong Dollar","Japanese Yen","Taiwan Dollar","Singapore Dollar","Swiss Franc",
					"Albanian Lek","Algerian Dinar","Aluminium Ounces","Argentine Peso",
					"Aruba Florin","Bahamian Dollar","Bahraini Dinar","Bangladesh Taka",
					"Barbados Dollar","Belarus Ruble","Belize Dollar","Bermuda Dollar","Bhutan Ngultrum",
					"Bolivian Boliviano","Botswana Pula","Brazilian Real","Brunei Dollar","Bulgarian Lev",
					"Burundi Franc","Cambodia Riel","Cape Verde Escudo",
					"Cayman Islands Dollar","CFA Franc (BCEAO)","CFA Franc (BEAC)","Chilean Peso","Colombian Peso",
					"Comoros Franc","Copper Pounds","Costa Rica Colon","Croatian Kuna","Cuban Peso",
					"Czech Koruna","Danish Krone","Dijibouti Franc","Dominican Peso",
					"East Caribbean Dollar","Ecuador Sucre","Egyptian Pound","El Salvador Colon","Eritrea Nakfa",
					"Estonian Kroon","Ethiopian Birr","Falkland Islands Pound","Fiji Dollar",
					"Indonesian Rupiah","Indian Rupee","Gambian Dalasi","Ghanian Cedi","Gibraltar Pound",
					"Gold Ounces","Guatemala Quetzal","Guinea Franc","Guyana Dollar","Haiti Gourde",
					"Honduras Lempira","Hungarian Forint","Iceland Krona","Iran Rial","Iraqi Dinar",
					"Israeli Shekel","Jamaican Dollar","Jordanian Dinar","Kazakhstan Tenge",
					"Kenyan Shilling","Kuwaiti Dinar","Lao Kip","Latvian Lat","Lebanese Pound",
					"Lesotho Loti","Liberian Dollar","Libyan Dinar","Lithuanian Lita","Macau Pataca",
					"Macedonian Denar","Malawi Kwacha","Malaysian Ringgit","Maldives Rufiyaa","Maltese Lira",
					"Mauritania Ougulya","Mauritius Rupee","Mexican Peso","Moldovan Leu","Mongolian Tugrik",
					"Moroccan Dirham","Myanmar Kyat","Namibian Dollar","Nepalese Rupee","Neth Antilles Guilder",
					"New Zealand Dollar","Nicaragua Cordoba","Nigerian Naira","North Korean Won","Norwegian Krone",
					"Omani Rial","Pacific Franc","Pakistani Rupee","Palladium Ounces","Panama Balboa",
					"Papua New Guinea Kina","Paraguayan Guarani","Peruvian Nuevo Sol","Philippine Peso","Platinum Ounces",
					"Polish Zloty","Qatar Rial","Romanian New Leu","Russian Rouble","Rwanda Franc",
					"South Korean Won","Samoa Tala","Sao Tome Dobra","Saudi Arabian Riyal",
					"Seychelles Rupee","Sierra Leone Leone","Silver Ounces","Slovak Koruna",
					"Slovenian Tolar","Solomon Islands Dollar","Somali Shilling","South African Rand","Sri Lanka Rupee",
					"St Helena Pound","Sudanese Pound","Swaziland Lilageni","Swedish Krona","Syrian Pound",
					"Thai Baht","Turkish Lira","Tanzanian Shilling",
					"Tonga Pa'ang","Trinidad  Tobago Dollar","Tunisian Dinar","UAE Dirham","Ugandan Shilling",
					"Ukraine Hryvnia","Uruguayan New Peso","Vanuatu Vatu","Venezuelan Bolivar Fuerte","Vietnam Dong",
					"Yemen Riyal","Zambian Kwacha","Zimbabwe dollar"};
			//mUnits = context.getResources().getStringArray(R.array.money1);
			mUnits = new String[]{"USD","AUD","EUR","GBP","CAD","CNY","HKD","JPY","TWD","SGD","CHF",
					"ALL","DZD","XAL","ARS","AWG","BSD","BHD","BDT",
					"BBD","BYR","BZD","BMD","BTN","BOB","BWP","BRL","BND","BGN",
					"BIF","KHR","CVE","KYD","XOF","XAF","CLP","COP",
					"KMF","XCP","CRC","HRK","CUP","CZK","DKK","DJF","DOP",
					"XCD","ECS","EGP","SVC","ERN","EEK","ETB","FKP","FJD",
					"IDR","INR","GMD","GHC","GIP","XAU","GTQ","GNF","GYD","HTG",
					"HNL","HUF","ISK","IRR","IQD","ILS","JMD","JOD","KZT",
					"KES","KWD","LAK","LVL","LBP","LSL","LRD","LYD","LTL","MOP",
					"MKD","MWK","MYR","MVR","MTL","MRO","MUR","MXN","MDL","MNT",
					"MAD","MMK","NAD","NPR","ANG","NZD","NIO","NGN","KPW","NOK",
					"OMR","XPF","PKR","XPD","PAB","PGK","PYG","PEN","PHP","XPT",
					"PLN","QAR","RON","RUB","RWF","KRW","WST","STD","SAR",
					"SCR","SLL","XAG","SKK","SIT","SBD","SOS","ZAR","LKR",
					"SHP","SDG","SZL","SEK","SYP","THB","TRY","TZS",
					"TOP","TTD","TND","AED","UGX","UAH","UYU","VUV","VEF","VND",
					"YER","ZMK","ZWD"};
		}
		Units = new String[]{"USD","AUD","EUR","GBP","CAD","CNY","HKD","JPY","TWD","SGD","CHF",
				"ALL","DZD","XAL","ARS","AWG","BSD","BHD","BDT",
				"BBD","BYR","BZD","BMD","BTN","BOB","BWP","BRL","BND","BGN",
				"BIF","KHR","CVE","KYD","XOF","XAF","CLP","COP",
				"KMF","XCP","CRC","HRK","CUP","CZK","DKK","DJF","DOP",
				"XCD","ECS","EGP","SVC","ERN","EEK","ETB","FKP","FJD",
				"IDR","INR","GMD","GHC","GIP","XAU","GTQ","GNF","GYD","HTG",
				"HNL","HUF","ISK","IRR","IQD","ILS","JMD","JOD","KZT",
				"KES","KWD","LAK","LVL","LBP","LSL","LRD","LYD","LTL","MOP",
				"MKD","MWK","MYR","MVR","MTL","MRO","MUR","MXN","MDL","MNT",
				"MAD","MMK","NAD","NPR","ANG","NZD","NIO","NGN","KPW","NOK",
				"OMR","XPF","PKR","XPD","PAB","PGK","PYG","PEN","PHP","XPT",
				"PLN","QAR","RON","RUB","RWF","KRW","WST","STD","SAR",
				"SCR","SLL","XAG","SKK","SIT","SBD","SOS","ZAR","LKR",
				"SHP","SDG","SZL","SEK","SYP","THB","TRY","TZS",
				"TOP","TTD","TND","AED","UGX","UAH","UYU","VUV","VEF","VND",
				"YER","ZMK","ZWD"};
		
		
		this.cansetting = true;
		
	}
	
	private String[] Units;
	private BackSyncThread sync;
	private BackSyncThread1 sync1;
	
	public void startSync(boolean set){
		/**
		if(mState != State.run){
			mState = State.run;
			stopSync();
			sync = new BackSyncThread(set);
			sync.start();
			if(mMoneySyncListener != null){
				mMoneySyncListener.MoneySyncStart();
			}
		}**/
	}
	
	public void startSync1(boolean set,int from,int to){
		Log.d("CORE","startSync1:" + from + " " + to);
		
		if(from == to){
			return;
		}
		
		if(!AllSync[from] || !AllSync[to] || set){
			if(sync1 != null){
				if(!set && (sync1.from == from && sync1.to == to) || (sync1.from == to && sync1.to == from)){
					Log.d("CORE","the same");
					return;
				}
				sync1.Stop = true;
				sync1 = null;
			}
			sync1 = new BackSyncThread1(set,from,to);
			sync1.start();
			if(mMoneySyncListener != null){
				mMoneySyncListener.MoneySyncStart();
			}
		}
	}
	
	public boolean isSync(){
		if(mState != State.run){
			return false;
		}else{
			return true;
		}
	}
	
	public void stopSync(){
		if(mState == State.run){
			if(sync != null){
				sync.Stop = true;
				sync = null;
				Log.d("CORE","stop sync thread");
			}
		}
		
		if(sync1 != null){
			sync1.Stop = true;
			sync1 = null;
			Log.d("CORE","stop sync thread");
		}
	}
	
	@Override
	public void setFromAndTo(int from,int to){
		super.setFromAndTo(from, to);
		if(from != to){
			startSync1(false,from,to);
		}
	}
	
	class BackSyncThread extends Thread{
		private boolean set;
		public boolean Stop = false;
		public BackSyncThread(boolean set){
			super();
			this.set = set;
		}
		public void run(){
			if(SyncGetRate(set,this)){
				mState = MoneyLogic.State.success;
				if(mMoneySyncListener != null){
					if(!Stop){
						mMoneySyncListener.MoneySyncSuccess();
					}
				}
			}else{
				if(mState != MoneyLogic.State.init){
					mState = MoneyLogic.State.fail;
					if(mMoneySyncListener != null){
						if(!Stop){
							mMoneySyncListener.MoneySyncFailed();
						}
					}
				}else{
					if(mMoneySyncListener != null){
						if(!Stop){
							mMoneySyncListener.MoneySyncInit();
						}
					}
				}
			}
		}
	}
	
	class BackSyncThread1 extends Thread{
		private boolean set;
		public int from,to;
		public boolean Stop = false;
		public BackSyncThread1(boolean set,int from,int to){
			super();
			this.set = set;
			this.from = from;
			this.to = to;
		}
		public void run(){
			mState = MoneyLogic.State.run;
			int s = SyncGetRate(this,set,from,to);
			
			if(s == 1){

				if(mMoneySyncListener != null){
					if(!Stop){
						mMoneySyncListener.MoneySyncSuccess();
					}
				}
			}else if(s == 2){

				if(mMoneySyncListener != null){
					if(!Stop){
						mMoneySyncListener.MoneySyncFailed();
					}
				}
			}else if(s == 0){
				if(mMoneySyncListener != null){
					if(!Stop){
						mMoneySyncListener.MoneySyncInit();
					}
				}
			}
			mState = MoneyLogic.State.init;
		}
	}
	
	private boolean SyncGetRate(boolean set,BackSyncThread mBackSyncThread){

		HashMap<String,Double> map = new HashMap<String,Double>();
		//Date date=new Date();
		//SimpleDateFormat matter=new SimpleDateFormat("MM/dd/yyyy");
		boolean result = true;
		//String time = matter.format(date);
		
		if(set || !time.equals(mData.getString("lastSyncTime", null)) || !mData.getBoolean("lastSync", false)){
			Log.d("CORE","start core rate sync");
			try{
				String url;
				for(int i = 1;i < Units.length;i++){
					if(mBackSyncThread.Stop){
						return false;
					}
					url = (i%2 == 1 ? URL1:URL2) + "?s=" + Units[i] + Units[0] + "=X&f=sl1d1t1ba&e=.csv";
					Log.d("CORE",url);
					url = loadHtml(url);
					String[] r = url.split(",");
					if(r == null){
						result = false;
						Log.e("CORE","Sync rate error:1");
						break;
					}else{
						if(r.length < 3){
							Log.e("CORE","Sync rate error:2");
							result = false;
							break;
						}else{
							try{
								map.put(Units[i] + Units[0], Double.valueOf(r[1]));
							}catch(Exception e){
								Log.e("CORE","Sync rate error:3");
								result = false;
								break;
							}
						}
					}
				}
	
			}catch(Exception e){
				//Log.d("CORE","Sync rate error:" + e.toString());
				Log.e("CORE","Sync rate error:4");
				result = false;
			}
			
			if(result){
				for(int i = 0;i < map.size();i++){
					//Log.d("CORE","write rate " + mUnits[i + 1] + mUnits[0] + ":" + map.get(mUnits[i + 1] + mUnits[0]));
					writeRate(0,i + 1,map.get(mUnits[i + 1] + mUnits[0]));
				}
				writeRate(0,0,1.0);
			}
			
			Editor editor = mData.edit();
			editor.putBoolean("lastSync", result);
			editor.putString("lastSyncTime", time);
			editor.commit();
		}else{
			Log.d("CORE","do not need start core rate sync");
			result = false;
			mState = MoneyLogic.State.init;
		}
		Log.d("CORE","result is " + result);
		return result;
	}
	
	private boolean[] AllSync;
	
	private int SyncGetRate(BackSyncThread1 mBackSyncThread1,boolean set,int id){
		//mCurrency,mSyncDate,mSyncState
		if(id != 0){
			double currency = 0;
			int result = -1;//0(not need),1(success),2(fail)
			
			if(set || !time.equals(mSyncDate.getString(Units[id], null)) || !mSyncState.getBoolean(Units[id], false)){
				try{
					String url;
					url = (id%2 == 1 ? URL1:URL2) + "?s=" + Units[id] + Units[0] + "=X&f=sl1d1t1ba&e=.csv";
					Log.d("CORE","get url:" + url);
					url = loadHtml(url);
					String[] r = url.split(",");
					if(r == null){
						result = 2;
						Log.e("CORE","Sync rate error:1");
					}else{
						if(r.length < 3){
							Log.e("CORE","Sync rate error:2");
							result = 2;
						}else{
							try{
								Log.d("CORE","get currency:" + r[1]);
								currency = Double.valueOf(r[1]);
								result = 1;
							}catch(Exception e){
								Log.e("CORE","Sync rate error:3");
								result = 2;
							}
						}
					}
				}catch(Exception e){
					Log.e("CORE","Sync rate error:4");
					result = 2;
				}
				
				if(!mBackSyncThread1.Stop){
					if(result == 1){
						WriteRate(0,id,currency);
						AllSync[id] = true;
					}
					
					Editor editor = mSyncState.edit();
					editor.putBoolean(Units[id], result == 1);
					editor.commit();
					
					editor = mSyncDate.edit();
					editor.putString(Units[id], time);
					editor.commit();
				}
				
			}else{
				result = 0;
				AllSync[id] = true;
			}
			return result;
		}else{
			return 0;
		}
	}
	
	private int SyncGetRate(BackSyncThread1 mBackSyncThread1,boolean set,int from,int to){
		int a = 0,b = 0;
		if(!AllSync[from] || set){
			a = SyncGetRate(mBackSyncThread1,set,from);
		}
		if(!AllSync[to] || set){
			b = SyncGetRate(mBackSyncThread1,set,to);
		}
		
		if(a == 0 && b == 0){
			a = 0;
		}else if(a == 2 || b == 2){
			a = 2;
		}else{
			a = 1;
		}
		
		return a;
	}
	
    private String loadHtml(String urlpath) throws Exception {
    	InputStream inputstream;
		
		URL url = new URL(urlpath);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		inputstream = conn.getInputStream();
		
		ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[512];
		int len = -1;
		while((len = inputstream.read(buffer)) != -1){
			outputstream.write(buffer,0,len);
		}
		inputstream.close();
		outputstream.close();
		
		return outputstream.toString();
    }
    
    public interface MoneySyncListener{
    	void MoneySyncInit();
    	void MoneySyncStart();
    	void MoneySyncFailed();
    	void MoneySyncSuccess();
    }

}
