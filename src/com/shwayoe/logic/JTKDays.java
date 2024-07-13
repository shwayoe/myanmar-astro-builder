package com.shwayoe.logic;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.signum;

public class JTKDays {
	private int dy;
	private int mn;
	private int yr;
	private int hh;
	private int mm;
	private int ss;
	
	public JTKDays(int dy,int mn,int yr,int hh,int mm,int ss) {
		this.dy = dy;
		this.mn = mn;
		this.yr = yr;
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
	}
	
	public double getJulianDay() {
		double jd = 0;
		
		double M1 = mn, Y1 = yr, b1 = 0;
		   double c1 = 0;
		   if (Y1 < 1) Y1 += 1;
		  
	       if (mn < 3) {
	           M1 = mn + 12;
	           Y1 -= 1;
	       }
	       if (Y1 > 1582) {
	    	   double a = (int)Y1/100;
	    	   b1 = 2 - a + (int)(a/4);
	    	   c1 = ((int)(365.25 * Y1))- 694025;
	       }else if (Y1 < 1582) {
	    	   c1 = ((int)(365.25 * Y1))- 694025;
	       }
	       
	       if(Y1 == 1582 && mn < 10) {
	    	   c1 = ((int)(365.25 * Y1))- 694025;
	       }
	       
	       if(Y1 == 1582 && mn == 10 && dy < 5) {
	    	   c1 = ((int)(365.25 * Y1))- 694025;
	       }
	       
	       if(mn > 10 || dy >= 15) {
	    	   double a = (int)Y1/100;
	    	   b1 = 2 - a + (int)(a/4);
	    	   c1 = ((int)(365.25 * Y1))- 694025;
	       }
	       
	       if(Y1 < 0) {
	    	   c1 = ((365.25 * Y1) - 0.75) - 694025;
	    	   c1 = signum(c1) * floor(abs(c1)); 
	       }
	       
	       double d = (int)(30.6001 * (M1 + 1));
	       double dj = b1 + c1+ d+ dy - 0.5;
	       jd = dj + 2415020d;
	       
		return jd;
	}
	
	public double getTwnDay() {
		double jd = getJulianDay();
		double twn1 = (int)jd - 588465 ;
		return twn1;
	}
	
	public double getKaliDay() {
		double twn = getTwnDay();
		twn = twn + (hh / 24d + mm / 1440d + ss / 86400d);
		double kali = twn * 4320000 / 1577917828;
		return kali;
	}
}
