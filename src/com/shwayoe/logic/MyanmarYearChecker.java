package com.shwayoe.logic;

import java.util.Hashtable;
import java.util.Map;

import com.shwayoe.data.Constants;

public class MyanmarYearChecker {
	public static Map<String,Integer> checkMyanmarYear(int myanmarYear){
		
		Map<String,Integer> y2 = checkWatat(myanmarYear);
		int myt = y2.get("watat");
		int yd = 0;
		Integer bw =0;
		
		Map<String,Integer> y1;
		do {
			yd++;
			y1 = checkWatat(myanmarYear - yd);
		}while(y1.get("watat") == 0 && yd < 3);
		
		int fm;
		int werr = 0;
		
		if(myt > 0) {
			double nd = (y2.get("fm") - y1.get("fm")) % 354;
			bw =  (int)Math.floor( nd / 31);
			myt = (int)bw + 1;
			fm = y2.get("fm");
			if(nd != 30 && nd != 31) {
				werr = 1;
			}
		}
		else {
			fm = y1.get("fm") + 354 * yd;
		}
		
		int tg1 = y1.get("fm") + 354 * yd - 102;
		
		Map<String,Integer> myanmarYearMap = new Hashtable<>();
		
		myanmarYearMap.put("myt",myt);
		myanmarYearMap.put("tg1", tg1);
		myanmarYearMap.put("fm",fm);
		myanmarYearMap.put("werr", werr);
		
		return myanmarYearMap;
	}
	
	public static Map<String,Integer> checkWatat(int myanmarYear){
		Map<String,Double> myanCalConst = Constants.getMyanmarCalConstants(myanmarYear);
		
		double threshold = (365.2587565 / 12 - 29.53058795) * (12 - myanCalConst.get("NM"));
		double ed = (365.2587565 * (myanmarYear + 3739)) % 29.53058795;
		
		if(ed < threshold) {
			ed += 29.53058795;
		}
		
		int fm = (int)Math.round(365.2587565 * myanmarYear + 1954168.050623 - ed + 4.5 * 29.53058795 + myanCalConst.get("WO"));
		
		int watat = 0;
		double tw = 0;
		if(myanCalConst.get("EI") >= 2) {
			tw = (29.53058795 - (365.2587565 / 12 - 29.53058795) * myanCalConst.get("NM"));
			
			if (ed >= tw) {
				watat = 1;
			}
		} else {
			watat = (myanmarYear * 7 + 2) % 19;
			if(watat < 0) {
				watat += 19;
			}
			
			watat = (int) Math.floor(watat / 12.0);
		}
		
		watat ^= myanCalConst.get("EW").intValue();
		
		Map<String,Integer> watatMap = new Hashtable<>();
		watatMap.put("fm", fm);
		watatMap.put("watat", watat);
		
		return watatMap;
	}

}
