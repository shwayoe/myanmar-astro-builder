package com.shwayoe.data;

import java.util.HashMap;
import java.util.Map;

public class myanmarLocalizedData {
	public static String[] myanmarWeekDays = new String[] {"တနင်္လာ","အင်္ဂါ","ဗုဒ္ဓဟူး","ကြာသပတေး","သောကြာ","စနေ","တနင်္ဂနွေ"};
	public static String[] myanmarMonths = new String[] {"ပထမ ဝါဆို","တန်ခူး","ကဆုန်","နယုန်","ဝါဆို","ဝါခေါင်","တော်သလင်း","သီတင်းကျွတ်","တန်ဆောင်မုန်း","နတ်တော်","ပြာသို","တပို့တွဲ","တပေါင်း","နှောင်းတန်ခူး","နှောင်းကဆုန်"};
	public static String[] moonPhases = new String[] {"လဆန်း","လပြည့်","လဆုတ်","လကွယ်"};
	public static String[] yarthiGwins = new String[] {"မိဿ","ပြိဿ","မေထုံ","ကြဋ်","သိဟ်","ကန်","တူ","ဗြိစ္ဆာ","","မကာရရ","ကုံ","မိန်"}; 
	public static String[] myanmarchatTee = new String[] {"","သန်းခေါင်၂","သန်းလွဲ ၃","နေထွက် ၄","နေတက် ၁","မွန်းတည့် ၂","နေလွဲ ၃","နေဝင် ၄","ညည့်  ၁"};
	public static String[] myanmarAstro = new String[] {"","အဿဝဏီ","ဘရရဏီ","ကြက္တိကာ","ရေရာဟဏီ","မိဂသီ","အဒြ","ပုဏ္ဏဖုသျှ","ဖုသျှ","အသလိသ","မာဃ","ပြုဗ္ဗဖလဂုနီ","ဥတ္တရရဖလဂုနီ","ဟဿတ","စိတြ","သွာတိ","ဝိသာခါ","အနုရရာဒ","ဇေဋ္ဌ","မူလ","ပြုဗ္ဗာသာဠ်","သတ္တရရသာဠ်","သရရဝဏ်","ဒနသိဒ္ဓိ","သတ္တဘိသျှ","ပြုဗ္ဗပရရပိုဒ်","ဥတ္တရရပရရပိုဒ်","ရေရဝတီ"};
	public static String[] myanmarDasa = new String[] {"","ကိတ်","၆ကြာ","၁နွေ","၂လာ","၃ဂါ","ရာဟု","၅တေး","စနေ","၄ဟူး"};
	public static String[] myanmarNayt = new String[] {"အင်္ဂါ", "ဗုဒ္ဓဟူး", "ကြာသပတေး", "သောကြာ", "စနေ", "တနင်္ဂနွေ","တနင်္လာ"};
	public static String[] myanmarAtarNayt = new String[] {"စနေ", "တနင်္ဂနွေ","တနင်္လာ","အင်္ဂါ", "ဗုဒ္ဓဟူး", "ကြာသပတေး", "သောကြာ"};
	public static String[] tarni = new String[] {"","အင်္ဂါ","သောကြာ","ဗုဒ္ဓဟူး", "တနင်္လာ", "တနင်္ဂနွေ","ဗုဒ္ဓဟူး","သောကြာ","အင်္ဂါ","ကြာသပတေး","စနေ","စနေ","ကြာသပတေး","အင်္ဂါ","သောကြာ","ဗုဒ္ဓဟူး", "တနင်္လာ", "တနင်္ဂနွေ","ဗုဒ္ဓဟူး","သောကြာ","အင်္ဂါ","ကြာသပတေး","စနေ","စနေ","ကြာသပတေး"};
	
	private static final Map<Character, Character> englishToMyanmarMap = new HashMap<>();
	private static final Map<String, String> englishToMyanmarStringMap = new HashMap<>();
	

    static {
        englishToMyanmarMap.put('0', '၀');
        englishToMyanmarMap.put('1', '၁');
        englishToMyanmarMap.put('2', '၂');
        englishToMyanmarMap.put('3', '၃');
        englishToMyanmarMap.put('4', '၄');
        englishToMyanmarMap.put('5', '၅');
        englishToMyanmarMap.put('6', '၆');
        englishToMyanmarMap.put('7', '၇');
        englishToMyanmarMap.put('8', '၈');
        englishToMyanmarMap.put('9', '၉');
        
        englishToMyanmarStringMap.put("January","ဇန်နဝါရီ");
        englishToMyanmarStringMap.put("February","ဖေဖေါ်ဝါရီ");
        englishToMyanmarStringMap.put("March","မတ်");
        englishToMyanmarStringMap.put("April","ဧပြီ");
        englishToMyanmarStringMap.put("May","မေ");
        englishToMyanmarStringMap.put("June","ဇွန်");
        englishToMyanmarStringMap.put("July","ဇူလှိုင်");
        englishToMyanmarStringMap.put("August","ဩဂတ်");
        englishToMyanmarStringMap.put("September","စက်တင်ဘာ");
        englishToMyanmarStringMap.put("October","အောက်တိုဘာ");
        englishToMyanmarStringMap.put("November","နိုဝင်ဘာ");
        englishToMyanmarStringMap.put("December","ဒီဇင်ဘာ");
        //"တနင်္လာ","အင်္ဂါ","ဗုဒ္ဓဟူး","ကြာသပတေး","သောကြာ","စနေ","တနင်္ဂနွေ"
        englishToMyanmarStringMap.put("Saturday","စနေ");
        englishToMyanmarStringMap.put("Sunday","တနင်္ဂနွေ");
        englishToMyanmarStringMap.put("Monday","တနင်္လာ");
        englishToMyanmarStringMap.put("Tuesday","အင်္ဂါ");
        englishToMyanmarStringMap.put("Wednesday","ဗုဒ္ဓဟူး");
        englishToMyanmarStringMap.put("Thurday","ကြာသပတေး");
        englishToMyanmarStringMap.put("Friday","သောကြာ");
        
    }
	
	public static String convertMMNumber(String enNumber) {
		StringBuilder myanmarNumber = new StringBuilder();
        for (char ch : enNumber.toCharArray()) {
            if (englishToMyanmarMap.containsKey(ch)) {
                myanmarNumber.append(englishToMyanmarMap.get(ch));
            } else {
                myanmarNumber.append(ch);
            }
        }
        return myanmarNumber.toString();		
	}
	
	public static String convertMMString(String enString) {
		return englishToMyanmarStringMap.get(enString);
	}
	
	public static String getMMThetKayit(int mmKalyu) {
		
		String thetKayit = "";
		if(mmKalyu < 2410) {
			thetKayit = "ကုသမင်း သက္ကရာဇ် ";
		}
		else if (mmKalyu >= 2410 && mmKalyu < 2558) {
			thetKayit = "မဟာ သက္ကရာဇ် ";
		}
		else if (mmKalyu >= 2558 && mmKalyu < 3180) {
			thetKayit = "အဇာတသတ်မင်း(သာသနာ)သက္ကရာဇ် ";
		}
		else if (mmKalyu >= 3180 && mmKalyu < 3740) {
			thetKayit = "သမုန္ဒြိမင်း(သရေခေတ္တရာ)သက္ကရာဇ် ";
		}
		else if (mmKalyu >= 3740 && mmKalyu < 5002) {
			thetKayit = "မြန်မာ ကောဇာ သက္ကရာဇ် ";
		}
		if (mmKalyu > 3739) {
			thetKayit = "မြန်မာ ကောဇာ သက္ကရာဇ် ";
		}
		
		return thetKayit;
	}
	public static String getMMMonths(int mmMonth,boolean iswar) {
		
		String mm = "";
		
		if(mmMonth == 4 && iswar == true) {
			mm = "ဒုတိယ " + myanmarMonths[mmMonth];
		}
		else {
			mm = myanmarMonths[mmMonth];
		}
		return mm;
	}
}
