package com.koalcat.unitconvert_core;

import java.math.BigDecimal;

import android.util.Log;
import android.widget.EditText;

public class ScienceNotation 
{
	EditText in;
	EditText out;
	BaseLogic mBaseLogic;
	public void takeScienceNotation(EditText in,EditText out,BaseLogic mBaseLogic)
	{
		if(mBaseLogic != null)
		{
			String strIn = in.getText().toString();
			int max = 12;
			if(in.getText().toString().equals("-"))
			{
				out.setText("0");
			}
			else if(strIn.charAt(strIn.length()-1)=='E')
			{
				out.setText("0");
			}
			else if(strIn.charAt(0)=='E')
			{
				out.setText("0");			
			}
			else if(strIn.indexOf("E")!=-1&&strIn.charAt(strIn.length()-1)=='-')
			{
				out.setText("0");
			}
			else if(strIn.indexOf('E')<strIn.indexOf('.')&&strIn.indexOf('E')!=-1)
			{
				out.setText("ERROR");			
			}
			else
			{
				out.setText(mBaseLogic.calculator(in.getText().toString()));
				if(out.getText().length() > max)
				{
					String strOut0 = out.getText().toString();
					String strOut1 = "";
					int positionE,positionPoint,length,length1,length2,length3;
					positionPoint = strOut0.indexOf('.');
					length = strOut0.length();
					int i;
					if(strOut0.charAt(0)!='-')
					{
						if(strOut0.indexOf('E')==-1)
						{
							if(strOut0.charAt(0)!='0')
							{
								strOut1 = "0."+""+strOut0.substring(0,positionPoint)+""+
									strOut0.substring(positionPoint+1,length)+"E"+positionPoint;
								Log.d("111","1:"+""+ strOut1);
							}
							else
							{
								if(strOut0.charAt(2) != '0')
								{
									strOut1 = strOut0;
								}
								else
								{
									for(i = 3;i < length ;i++)
									{
										if(strOut0.charAt(i)!='0')
										{
											strOut1 = "0."+""+strOut0.substring(i,length)+"E"+(2-i);break;
										}	
									}
								}
							}
						}
						else
						{
							if(strOut0.charAt(0)!='0')
							{
								int positionE0 = strOut0.indexOf('E');
								if(strOut0.charAt(positionE0+1)=='+')
								{
									strOut1 = "0."+""+strOut0.substring(0,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint+Integer.parseInt(strOut0.substring(positionE0+2, length)));
								}
								else if(strOut0.charAt(positionE0+1)=='-')
								{
									strOut1 = "0."+""+strOut0.substring(0,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint-Integer.parseInt(strOut0.substring(positionE0+2, length)));
								}
								else
								{
									strOut1 = "0."+""+strOut0.substring(0,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint+Integer.parseInt(strOut0.substring(positionE0+1, length)));
								Log.d("111","1:"+""+ strOut1);
								}
								
							}
							else
							{
								if(strOut0.charAt(2) != '0')
								{
									strOut1 = strOut0;
								}
								else
								{
									for(i = 3;i < length ;i++)
									{
										if(strOut0.charAt(i)!='0')
										{
											strOut1 = "0."+""+strOut0.substring(i,length)+"E"+(2-i);break;
										}	
									}
								}
							}
						}
						
					}
					else
					{
						if(strOut0.indexOf('E')==-1)
						{
							if(strOut0.charAt(1)!='0')
							{
								strOut1 = "-0."+""+strOut0.substring(1,positionPoint)+""+
									strOut0.substring(positionPoint+1,length)+"E"+""+(positionPoint-1);
							}
							else
							{
								if(strOut0.charAt(3)!='0')
								{
									strOut1 = strOut0;
								}
								else
								{
									for(i = 4;i < length ;i++)
									{
										if(strOut0.charAt(i)!='0')
										{
											strOut1 = "-0."+""+strOut0.substring(i,length)+"E"+(3-i);break;
										}
									}
								}
							}
						}
						else
						{
							int positionE0 = strOut0.indexOf('E');
							if(strOut0.charAt(1)!='0')
							{
								if(strOut0.charAt(positionE0+1)=='+')
								{
									strOut1 = "-0."+""+strOut0.substring(1,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint-1+Integer.parseInt(strOut0.substring(positionE0+2,length)));
								}
								else if(strOut0.charAt(positionE0+1)=='-')
								{
									strOut1 = "-0."+""+strOut0.substring(1,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint-1-Integer.parseInt(strOut0.substring(positionE0+2,length)));
								}
								else
								{
									strOut1 = "-0."+""+strOut0.substring(1,positionPoint)+""+
									strOut0.substring(positionPoint+1,positionE0)+"E"+""+(positionPoint-1+Integer.parseInt(strOut0.substring(positionE0+1,length)));
								}
								
							}
							else
							{
								if(strOut0.charAt(3)!='0')
								{
									strOut1 = strOut0;
								}
								else
								{
									for(i = 4;i < length ;i++)
									{
										if(strOut0.charAt(i)!='0')
										{
											strOut1 = "-0."+""+strOut0.substring(i,length)+"E"+(3-i);break;
										}
									}
								}
							}
						}
						
					}
					positionE = strOut1.indexOf('E');
					length2 = strOut1.length();
					length1 = max+positionE-length2-2;
					length3 = max+positionE-length2-3;
					if(strOut1.indexOf("E")==-1)
					{
						if(strOut1.charAt(0)!='-')
						{
							double f = Double.valueOf(strOut1.substring(0,length2));
							BigDecimal b0 = new BigDecimal(f);
							double f1 = b0.setScale(max-2, BigDecimal.ROUND_HALF_UP).doubleValue();
							out.setText(""+f1);
							Log.d("111","2:"+""+ f1);
						}
						else
						{
							double f = Double.valueOf(strOut1.substring(0,length2));
							BigDecimal b1 = new BigDecimal(f);
							double f1 = b1.setScale(max-3, BigDecimal.ROUND_HALF_UP).doubleValue();
							out.setText(""+f1);
							Log.d("111","3:"+""+ f1);
						}
					}
					else
					{
						if(length2-positionE>=14)
						{
							out.setText("ERROR");
						}
						else
						{		
							if(strOut1.charAt(0)!='-')
							{
								double f = Double.valueOf(strOut1.substring(0,positionE));
								BigDecimal b = new BigDecimal(f);
								double f1 = b.setScale(length1, BigDecimal.ROUND_HALF_UP).doubleValue();
								out.setText(f1+strOut1.substring(positionE, length2));
								Log.d("111","4:"+f1+strOut1.substring(positionE, length2));
							}
							else
							{
								double f = Double.valueOf(strOut1.substring(0,positionE));
								BigDecimal b = new BigDecimal(f);
								double f1 = b.setScale(length3, BigDecimal.ROUND_HALF_UP).doubleValue();
								out.setText(f1+"E"+strOut1.substring(positionE+1, length2));
								Log.d("111","5:"+f1+strOut1.substring(positionE+1,length2));
							}
						}
					}
				}
			}
		}
		else
		{
			out.setText("error");
		}
		
	}	
}
