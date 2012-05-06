package com.koalcat.unitconvert_core;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.koalcat.unitconvert_core.MoneyLogic.MoneySyncListener;

import android.app.Activity;
import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class IOManager {
	
	public static final int AREA = 0;
	public static final int ENERGY = 1;
	public static final int LENGTH = 2;
	public static final int MONEY = 3;
	public static final int POWER = 4;
	public static final int PRESSURE = 5;
	public static final int SPEED = 6;
	public static final int TEMPERATURE = 7;
	public static final int TIME = 8;
	public static final int VOLUME = 9;
	public static final int WEIGHT = 10;
	
	private static final char[] ACCEPTED_CHARS = 
	        "0123456789.-\u2212E".toCharArray();
	private ArrayList<EditText> AllEdits;
	private Activity context;
	private NumberKeyListener mListener;
	private EditText mInputEditText,mOutputEditText;
	public BaseLogic mLogic;
	//private ScienceNotation mScienceNotation = new ScienceNotation();
	public EditTextOnFocusChangeListener mEditTextOnFocusChangeListener;
	public IOManagerOnClickPlug mIOManagerOnClickPlug;
	public IOManagermLogicMutiLanguagePlug mIOManagermLogicMutiLanguagePlug;
	public boolean MutiLauPlug = false;
	
	public IOManager(Activity context){
		this.context = context;
	}
	
	public void destory(){
		if(mLogic != null){
			if(mLogic instanceof MoneyLogic){
				MoneyLogic a = (MoneyLogic)mLogic;
				a.stopSync();
			}
		}
	}
	
	public void registerEditText(EditText edit){
		if(AllEdits == null){
			AllEdits = new ArrayList<EditText>();
		}
		if(mListener == null){
			mListener =new NumberKeyListener() {
				public int getInputType() {
					// Don't display soft keyboard.
					return InputType.TYPE_NULL;
			    }
	
				@Override
				protected char[] getAcceptedChars() {
					// TODO Auto-generated method stub
					return ACCEPTED_CHARS;
				}
			};
		}
		AllEdits.add(edit);
		/*if(AllEdits.size() == 1){
			if(mInputEditText == null){
				mInputEditText = edit;
			}
		}*/
		edit.setKeyListener(mListener);
		edit.setOnFocusChangeListener(mOnFocusChangeListener);
	}
	
	private OnFocusChangeListener mOnFocusChangeListener = new OnFocusChangeListener(){

		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if(hasFocus){
				mInputEditText = (EditText) v;
				if(mEditTextOnFocusChangeListener != null){
					mEditTextOnFocusChangeListener.onFocus(v);
				}
			}
		}
	};
	
	public void registerKeyboard(Keyboard mKeyboard){
		mKeyboard.registerIOManager(this);
	}
	
	public void setLogic(int logic){
		switch(logic){
		case AREA:mLogic = new AreaLogic(context,MutiLauPlug);break;
		case ENERGY:mLogic = new EnergyLogic(context,MutiLauPlug);break;
		case LENGTH:mLogic = new LengthLogic(context,MutiLauPlug);break;
		case MONEY:mLogic = new MoneyLogic(context,MutiLauPlug);break;
		case POWER:mLogic = new PowerLogic(context,MutiLauPlug);break;
		case PRESSURE:mLogic = new PressureLogic(context,MutiLauPlug);break;
		case SPEED:mLogic = new SpeedLogic(context,MutiLauPlug);break;
		case TEMPERATURE:mLogic = new TemperatureLogic(context,MutiLauPlug);break;
		case TIME:mLogic = new TimeLogic(context,MutiLauPlug);break;
		case VOLUME:mLogic = new VolumeLogic(context,MutiLauPlug);break;
		case WEIGHT:mLogic = new WeightLogic(context,MutiLauPlug);break;
		}
		
		if(MutiLauPlug && mIOManagermLogicMutiLanguagePlug != null){
			String[] temp = mIOManagermLogicMutiLanguagePlug.getLanguageTitles(logic);
			if(temp != null && temp.length == mLogic.size){
				//Log.d("CORE","mLogic.mTitles = temp");
				mLogic.mTitles = temp;
			}
			
			temp = mIOManagermLogicMutiLanguagePlug.getLanguageUnits(logic);
			if(temp != null && temp.length == mLogic.size){
				//Log.d("CORE","mLogic.mUnits = temp");
				mLogic.mUnits = temp;
			}
			
			String name = mIOManagermLogicMutiLanguagePlug.getName(logic);
			if(name != null){
				mLogic.NAME = name;
			}
		}else{
			Log.d("CORE","mIOManagermLogicMutiLanguagePlug is null and MutiLauPlug is " + MutiLauPlug);
		}
		
		if(mLogic instanceof MoneyLogic){
			MoneyLogic a = (MoneyLogic)mLogic;
			a.setMoneySyncListener(mMoneySyncListener);
			a.startSync(false);
		}
		
		refresh();
	}
	
	private MoneySyncListener mMoneySyncListener;
	
	public void setMoneySyncListener(MoneySyncListener mMoneySyncListener){
		this.mMoneySyncListener = mMoneySyncListener;
		if(mLogic != null && mLogic instanceof MoneyLogic){
			MoneyLogic a = (MoneyLogic)mLogic;
			a.setMoneySyncListener(mMoneySyncListener);
		}
	}
	
	private BaseLogic getLogic(int logic){
		BaseLogic m = null;
		switch(logic){
		case AREA:m = new AreaLogic(context,true);break;
		case ENERGY:m = new EnergyLogic(context,true);break;
		case LENGTH:m = new LengthLogic(context,true);break;
		case MONEY:m = new MoneyLogic(context,true);break;
		case POWER:m = new PowerLogic(context,true);break;
		case PRESSURE:m = new PressureLogic(context,true);break;
		case SPEED:m = new SpeedLogic(context,true);break;
		case TEMPERATURE:m = new TemperatureLogic(context,true);break;
		case TIME:m = new TimeLogic(context,true);break;
		case VOLUME:m = new VolumeLogic(context,true);break;
		case WEIGHT:m = new WeightLogic(context,true);break;
		}
		return m;
	}
	
	public ArrayList<String> getAllLogic(){
		ArrayList<String> all = new ArrayList<String>();
		for(int i = 0;i < 11;i++){
			all.add(getLogic(i).getNAME());
		}
		return all;
	}
	
	public void setEditTextUnit(EditText edit,int unit){
		if(mLogic != null){
			if(AllEdits.contains(edit)){
				if((unit >= 0) && (unit < mLogic.size())){
					edit.setTag(unit);
				}else if(unit < 0){
					edit.setTag(0);
				}else if(unit >= mLogic.size()){
					edit.setTag(mLogic.size() - 1);
				}
				refresh();
			}
		}
	}
	
	public void clearAllEdits(){
		for(int i = 0;i < AllEdits.size();i++){
			AllEdits.get(i).setText("");
		}
	}
	
	private void insertkey(String key)
	{
		String strIn = mInputEditText.getText().toString();
		mInputEditText.setSelection(strIn.length());
		
		if(strIn.equals("error")){
			clearAllEdits();
		}
		
		if(key.equals(".")){
			if(mInputEditText.length() == 0){
				int cursor = mInputEditText.getText().toString().length();
				mInputEditText.getText().insert(cursor, "0.");
			}
			else if(strIn.equals("-")){
				insert("0.");
			}
					
			else if(mInputEditText.length()!=0&&strIn.indexOf(".")==-1){
				insert(".");
			}
			else{
				insert("");
			}
		}else if(key.equals("back")){
			context.dispatchKeyEvent(new KeyEvent(0,KeyEvent.KEYCODE_DEL));
		}else if(key.equals("-")){
			if(mInputEditText.length() < max && mInputEditText.length() > 0){
				String t = mInputEditText.getText().toString();
				if(t.charAt(0) == '-'){
					t = t.substring(1);//+
				}else{
					t = "-" + t;//-
				}
				mInputEditText.setText(t);
			}else if(mInputEditText.length() == max){
				String t = mInputEditText.getText().toString();
				if(t.charAt(0) == '-'){
					t = t.substring(1);//+
				}
				mInputEditText.setText(t);
			}else if(mInputEditText.length() == 0){
				mInputEditText.setText("-");
			}
		}else if(key.equals("clear")){
			if(mInputEditText.length() != 0){
				mInputEditText.setText("");
			}
		}else{
			insert(key);
			if(strIn.equals("0") || strIn.equals("-0")){
				if(key.equals("0")){
					mInputEditText.setText("0");
				}else{
					clearAllEdits();
					insert(key);
				}
			}
		}
	
		mInputEditText.setSelection(mInputEditText.getText().toString().length());
	}
	
	private int max = 12;
	private String out;
	
	private void insert(String text){
		//int cursor = mInputEditText.getSelectionStart(); 
		int cursor = mInputEditText.getText().toString().length();
		//cursor = cursor >= 0 ? cursor : 0;
		mInputEditText.getText().insert(cursor, text);
		//mInputEditText.getSelectionStart();
		if(mInputEditText.length() >= max)
		{
			String str0 = mInputEditText.getText().toString();
			mInputEditText.setText(str0.substring(0, max));
			mInputEditText.setSelection(max);
		}
	}
	
	public void refresh(){
		if(AllEdits != null){
			for(int i = 0;i < AllEdits.size();i++){
				mOutputEditText = AllEdits.get(i);
				if(mOutputEditText == mInputEditText){
					//Log.d("CORE","mOutputEditText == mInputEditText");
					continue;
				}else{
					if(mInputEditText != null && mInputEditText.getTag() != null &&
							mOutputEditText != null && mOutputEditText.getTag() != null && mLogic != null){
						mLogic.setDirection(true);
						mLogic.setFromAndTo((Integer)mInputEditText.getTag(), (Integer)mOutputEditText.getTag());
						String input = mInputEditText.getText().toString();
						if(input == null || input.equals("") || input.equals("-")){
							mOutputEditText.setText("");
						}else{
							//mScienceNotation.takeScienceNotation(mInputEditText,mOutputEditText, mLogic);
							String l;
							int a;
							a = input.length();
							if(a > 0){
								l = input.substring(a - 1, a);
								if(l.equals("-")){
									input = input.substring(0, a - 2);
								}else if(l.equals("E")){
									input = input.substring(0, a - 1);
								}
							}
							
							out = mLogic.calculator(input);
							if(!out.equals("error")){
								if(Double.valueOf(out)/1 == 0){
									out = "0";
								}
								
								if(out.length() > max){
									a = out.indexOf("E");
									if(a == -1){
										out = out.substring(0, max);
										l = deletzero(out);
										if(l != null){
											out = l;
										}
										//Log.d("CORE","" + deletzero(out));
									}else{
										String f = out.substring(0,a);
										l = out.substring(a, out.length());
										f = f.substring(0, max - l.length());
										out = deletzero(f);
										if(out != null){
											f = out;
										}
										//Log.d("CORE","" + deletzero(f));
										out = f + l;
										//Log.d("CORE","f:" + f + " l:" + l);
									}
								}else{
									a = out.indexOf("E");
									if(a == -1){
										String f = deletzero(out);
										if(f != null){
											out = f;
										}
									}else{
										String f = out.substring(0,a);
										l = out.substring(a, out.length());
										//f = f.substring(0, max - l.length());
										out = deletzero(f);
										if(out != null){
											f = out;
										}
										//Log.d("CORE","" + deletzero(f));
										out = f + l;
										//Log.d("CORE","f:" + f + " l:" + l);
									}
								}
								
							}
							
							mOutputEditText.setText(out);
						}
						//mOutputEditText.setText(mLogic.calculator(input));
						
					}else{
						//Log.d("CORE","refresh is null");
					}
				}
			}
		}else{
			//Log.d("CORE","AllEdits is null");
		}
	}
	
	private Pattern pattern1 = Pattern.compile("[0]+$");
	//private Pattern pattern2 = Pattern.compile("^[0]+");
	private Matcher matcher;
	
	private String deletzero(String in){
		if(in.contains(".")){
			//Pattern pattern = Pattern.compile("[0]+$");
			//Matcher matcher;
			matcher = pattern1.matcher(in);
			if(matcher.find()){
				in = in.substring(0, matcher.start(0));
				if(in.charAt(in.length() - 1) == '.'){
					in = in.substring(0, in.length() - 1);
				}
				return in;
			}else{
				return null;
			}
		}else{
			return null;
			//Pattern pattern = Pattern.compile("^[0]+");
			//Matcher matcher;
			/*matcher = pattern2.matcher(in);
			if(matcher.find()){
				in = in.substring(matcher.end(0), in.length());
				return in;
			}else{
				return null;
			}*/
		}
	}
	
	public void InputKey(String key){
		if(mIOManagerOnClickPlug != null){
			mIOManagerOnClickPlug.OnClick(key);
		}
		if(mInputEditText != null){
			insertkey(key);
			refresh();
		}
	}
	
	public interface EditTextOnFocusChangeListener{
		public void onFocus(View v);
	}
	
	public interface IOManagerOnClickPlug{
		public void OnClick(String key);
	}
	
	public interface IOManagermLogicMutiLanguagePlug{
		public String[] getLanguageTitles(int logic);
		public String[] getLanguageUnits(int logic);
		public String getName(int logic);
	}
}

