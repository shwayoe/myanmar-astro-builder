package com.shwayoe.logic;

import java.util.Map;

public class MyanmarDate {
	
	private double myanmarYear;
	private double myanmarMonth;
	private double myanmarDay;
	private double myanmarMonthDay;
	private double monthLength;
	private double monthType;
	private double yearLength;
	private double sanorSoak;
	private double weekDay;
	private double atarDayNumber;
	private double titheeYetlon;
	private double wallharYetlon;
	private double thoteDateDan;
	private double kaliYote;
	private double julianDay;
	private double tharwanaDay;
	private boolean watat;
	private boolean bwar;
	private JTKDays jtkDays;
//	==================
	
	private double twn1 =0;
	private double al = 1577917828d, cl = 4320000d, ak = 25082252d;
	//private double b = 1603000080d;
	private double kali = 0;
	private double haragone = 0;
	
	public MyanmarDate(JTKDays jd) {
		double b,dd,c,e,f;
		int a;
		julianDay = jd.getJulianDay();
		twn1 = jd.getTwnDay();
		kali = jd.getKaliDay();
		
		Map<String,Integer> yo = MyanmarYearChecker.checkMyanmarYear((int)getMyanmarYear());
		long jdn = Math.round(julianDay);
		
		dd = jdn - yo.get("tg1")+1; 
		b = (int)(yo.get("myt") / 2);
		c =  (int)(1/ (yo.get("myt") +1 ));
		
		yearLength = (short)(354+(1-c) * 30 + b);
		monthType = (short)((dd-1)/yearLength);
		
		dd -= (short)(monthType * yearLength);
		
		a = (int)((dd+ 423) / 512);
		myanmarMonth = (short) Math.floor((dd-b * a + c * a* 30 + 29.26) / 29.544);
		e = (short)(myanmarMonth + 12) /16;
		f = (short)(myanmarMonth + 11) / 16;
		
		myanmarMonthDay = (short)(dd - Math.floor(29.544 * myanmarMonth - 29.26) - b * e + c * f * 30);
		
		myanmarMonth += (short)(f * 3 - e * 4 + 12  * monthType);
		
		monthLength = 30 - myanmarMonth % 2;
		
		if(myanmarMonth == 3) {
			monthLength += yo.get("myt") / 2;
		}
		
		sanorSoak = (short) (((int)(myanmarMonthDay + 1) / 16) + (int)(myanmarMonthDay / 16) + (int)(myanmarMonthDay / monthLength));
		
		myanmarDay = (short)(myanmarMonthDay - 15 * (int)(myanmarMonthDay / 16));
		
		weekDay = (short)((jdn +2) % 7);
		
		watat = yo.get("myt") > 0;
		bwar = yo.get("myt") == 2;
		
		//Calcuate အတာရက် (atarDayNumber)
		double kal = (int)kali;
		double sawana = al * kal / cl;
		double kyamat = (al * kal) - (((int) sawana) * cl);
		haragone = (int)sawana;
		if (kyamat > 0 ) haragone +=1;
		atarDayNumber = (haragone + 5 ) / 7;
		atarDayNumber = (haragone + 5) - ((int)atarDayNumber) * 7;
		
		//Calculate တိထီရက်လွန် (titheeYatlon)
		double Q = haragone * ak / al;
		double dnt = (int)Q;
		double titi = haragone + dnt;
		titheeYetlon = titi / 30;
		double sandramatha = (int)titheeYetlon;
		titheeYetlon = titi - (sandramatha * 30);
		
		//Calculate သုဒ္ဒဒိန် (thoteDateDan)
		double twn = (int)twn1;
		thoteDateDan = twn - (kal * al / cl);
		//thoteDateDan = ((int)thoteDateDan) + 1;
		thoteDateDan = Math.floor(thoteDateDan) + 1;
		
	}
	
	public double getMyanmarYear() {
		myanmarYear = getKaliYote() - 3739;
		return myanmarYear;
	}
	
	public double getMyanmarMonth() {
		return myanmarMonth;
	}
	
	public double getMyanmarDay() {
		return myanmarDay;
	}

	public double getMyanmarMonthDay() {
		return myanmarMonthDay;
	}

	public double getMonthLength() {
		return monthLength;
	}

	public double getMonthType() {
		return monthType;
	}

	public double getYearLength() {
		return yearLength;
	}
	
	public double getSanorSoak() {
		return sanorSoak;
	}
	
	public double getWeekDay() {
		return weekDay;
	}
	
	public double getAtarDayNumber() {
		return atarDayNumber;
	}
	
	public double getTitheeYetlon() {
		return titheeYetlon;
	}
	
	public double getWallharYetlon() {
		wallharYetlon = getTitheeYetlon();
		
		if(getKaliYote() > 5001 && getKaliYote() < 5110) {
			if (wallharYetlon == 0) wallharYetlon = wallharYetlon + 30;
			wallharYetlon +=1;
		}
		
		int[] M3 = {0,1273,1278,1283,1308,1309,1313,1314,1316,1317,1318,1319,1324,1327,1328,1329,1330,1332,1333,1335,1340,1343,1344,1345,1348,1349,1350,1354,1355,1359,1360,1364,1365,1366,1370,0,0,0,0,0,0,0};
		for(int i = 1;i< M3.length;i++) {
			if (myanmarYear == M3[i]) wallharYetlon -= 1;
		}
		
		if (myanmarYear == 1334) wallharYetlon = wallharYetlon + 29;
		if (myanmarYear == 1269) wallharYetlon = wallharYetlon + 31;
		if (myanmarYear == 1372) wallharYetlon = wallharYetlon + 31;
		if (myanmarYear == 1377) wallharYetlon = wallharYetlon + 1;
		if (myanmarYear == 1380) wallharYetlon = wallharYetlon + 1;
		
		int[] M = {0,1272,1277,1280,1288,1291,1296,1299,1307,1310,1315,1326,1353,0,0,0};
		for(int i=1;i<M.length;i++) {
			if(myanmarYear == M[i]) wallharYetlon = wallharYetlon + 30;
		}
		if(wallharYetlon > 30) wallharYetlon -= 30;
		
		return wallharYetlon;
	}
	
	public double getThoteDateDan() {
		return thoteDateDan;
	}
	
	public double getKaliYote() {
		kaliYote = (int)kali;
		return kaliYote;
	}
	
	public double getJulianDay() {
		return Math.round(julianDay);
	}
	
	public double getTharwanaDay() {
		tharwanaDay = twn1 + 1;
		return tharwanaDay;
	}
	
	public boolean isWatat() {
		return watat;
	}

	public boolean isBwar() {
		return bwar;
	}
	
	public JTKDays getJTKDays() {
		return this.jtkDays;
	}
}
