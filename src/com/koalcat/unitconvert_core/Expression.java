package com.koalcat.unitconvert_core;
/******************
*Author :Ethan.zhao
*Date : 2010-10-25
*****************/

import java.math.BigDecimal;

public class Expression {
	String mExpression;
	private int mScale = 20;
	
	public int getMScale() {  
		return mScale;
	}

	public void setMScale(int scale) { 
		mScale = scale;
	}

	public Expression(String s) {	
		this.mExpression = takeOffBlank(s);
	}

	public String calcuCurrentExpression() {	
		String calc_result;
		if ("".equals(mExpression))
			return null;
		calc_result = calcuThisExpression(mExpression).toString();
		return calc_result;
	}

	private int findTheLowestPriority(String s) {	
		int i = 0, k = 0, min_priority = 99999, p = 0;
		for (i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '+':
			case '-':
				
				if ((i == 0) || (s.charAt(i - 1) == '('))
						break;
				if(s.charAt(i-1)!='E')
				{
					if ((k + 1) <= min_priority) {
						min_priority = k + 1;
						p = i;
					}
				}
				
				break;
			case '*':
			case '/':
				
					if ((k + 2) <= min_priority) {
						min_priority = k + 2;
						p = i;
					}
				
				
				break;
			case '(':
				k += 10;
				break;
			case ')':
				k -= 10;
				break;
			}
			
		}
		//if (k != 0)
			//throw new Exception("Parentheses are not matching !");
		
		return p;
	}

	private String takeOffOuterParenthesis(String s) {	
		int i = 0, k = 0;
		boolean flag = true;
		while ((s.charAt(0) == '(') && ((s.charAt(s.length() - 1) == ')'))
				&& flag) {
			k = 0;
			i = 0;
			flag = false;
			for (i = 0; i < s.length(); i++) {
				switch (s.charAt(i)) {
				case '(':
					k += 10;
					break;
				case ')':
					k -= 10;
					break;
				}
				if (k == 0)
					break;
			}
			if (i == (s.length() - 1)) {
				flag = true;
				s = s.substring(1, s.length() - 1);
			}
		}
		return s;
	}

	private String takeOffBlank(String s) {	
		String c = new String("");
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ')
				c = c + s.charAt(i);
		}
		return c;
	}

	private BigDecimal calcuThisExpression(String s){	
		
		BigDecimal calc_result = null;	
		
		int lowest_priority = 0;	
		s = takeOffOuterParenthesis(s);	

		lowest_priority = findTheLowestPriority(s);	
		if (lowest_priority == 0) {	
			calc_result = new BigDecimal(s);
			return calc_result;
		}
		
		BigDecimal num1 = null,num2 = null;
		num1 =calcuThisExpression(s.substring(0, lowest_priority));	
		num2 =calcuThisExpression(s.substring(lowest_priority + 1, s.length())); 
		

			switch (s.charAt(lowest_priority)) 
			{	
			case '+':
				calc_result = num1.add(num2);
				break;
			case '-':
				calc_result = num1.subtract(num2);
				break;
			case '*':
				calc_result = num1.multiply(num2);
				break;
			case '/':
				calc_result = num1.divide(num2,mScale,BigDecimal.ROUND_HALF_UP);
				break;
			}
		
		
		return calc_result;		
	}
	
}