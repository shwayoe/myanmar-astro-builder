package com.shwayoe.logic;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Map;

import com.shwayoe.data.InputData;
import com.shwayoe.data.myanmarLocalizedData;

public class HoroscopeData {
	private AstrologicalData astroData;
	private MyanmarDate md;
	private InputData info;
	
	private double p[] = new double[11],h[] = new double[12],c[] =  new double[13],dl[] = new double[10],lat[] = new double[8];
	private double deg, yarsi, antha,lateta;
	private double pya[] =new double[11], pan[] = new double[11],plat[] = new double[11];
	private double hya[]= new double[13],han[]= new double[13],hlat[]= new double[13],cya[]= new double[13],can[]= new double[13],clat[]= new double[13],latan[]= new double[13],latlat[]= new double[13],dlan[]= new double[13],dllat[] = new double[13];
	
	String[] w = new String[10];
	String[] w1 = new String[10];
	String[] ld = new String[10];
	
	String[] wb = new String[10];
	String[] wb1 = new String[10];
	String[] ld1 = new String[10];
	
	String[] e1 = new String[10];
	String[] e2 = new String[10];
	String[] e3 = new String[10];
	
	double[] r1 = new double[14];
	double[] r2 = new double[14];
	
	double[] atr = new double[10];
	double[] butti = new double[10];
	String ww = "";
	double xq = 0;
	
	String astroPawar = "";
	
	public HoroscopeData(InputData info) {
		this.info = info;
		astroData = new AstrologicalData(info.getDay(), info.getMonth(), info.getYear(), info.getHours(), info.getMinutes(), info.getSeconds(), info.getLocation().getGeoLocation().getLogitude(), info.getLocation().getGeoLocation().getLogMinutes(),info.getLocation().getGeoLocation().getLatitude(), info.getLocation().getGeoLocation().getLatMinutes(), info.getLocation().getTimeZone());
		md = new MyanmarDate(new JTKDays (info.getDay(), info.getMonth(), info.getYear(), info.getHours(), info.getMinutes(), info.getSeconds()));
		calculateHoroscope();
		titee();
	}
	
	public int[] Lsnk() {
		double snk = p[10] / 13.33333333;
		int snk1 = (int) snk;
		double snk2 = (snk - snk1) * 4;
		int snk3 = (int) snk2;
		double snk4 = (snk2 - snk3) * 15;
		int snk5 = (int) snk4;
		double snk6 = (snk4 - snk5) * 60;
		int snk7 = (int) snk6;

		return new int[] { snk1, snk3, snk5, snk7 };
	}
	
	public Map<String,Number> getDini(){
		
		Map<String,Number> diniData = new Hashtable<>();
		
		double hh = info.getHours();
		double mm = info.getMinutes();
		
		double zz = hh + mm / 60;
		double mt = zz * 2.5;
		double Lati = info.getLocation().getGeoLocation().getLatitude() + info.getLocation().getGeoLocation().getLatMinutes() / 60d;
		double pres = ((int)astroData.getPres()) + (((int)astroData.getPresLatter()) / 60d);
		double xp = p[1] + pres;
		double r = 57.29577951,Q = 24;
		
		double a = Math.sin(xp / r) * Math.sin(Q / r);
		double xb = Math.atan(a / (Math.sqrt(1 - a * a) + 1E-20)) * r;
		double xc = 1 - (1 - Math.cos(xb / r));
		double kiti = Math.tan(Lati / r) * a;
		double saya = kiti / xc;
		double dinat = 0;
        double nithat = 0;
        double d1 = 0;
        double d2 = 0;
		
		double sayap = 0;
		try {
			sayap = Math.atan(saya / (Math.sqrt(1d - saya * saya) + 1E-20)) * r;
			dinat = (sayap / 6) + 15;
	        nithat = 30 - dinat;
	        d1 = dinat / 2;
	        d2 = nithat / 2;
		} catch (Exception e) {
			System.out.println("Error: North pole can't calculate 89 59 N,00 00 E, 89 59 S,00 00 E");
		}
		
		double[] x = new double[9];
		x[3] = nithat;
        x[4] = x[3] + d1;
        x[5] = x[4] + d1;
        x[6] = x[5] + d1;
        x[7] = x[6] + d1;
        x[8] = x[7] + d2;
        x[1] = 0;
        x[2] = x[1] + d2;
        
        double f = 0;
        int g = 0;
        
        for (int i = 1; i <= 7; i++) {
            if (mt > x[i] && mt < x[i + 1]) {
                f = mt - x[i];
                g = i;
            }
        }
        
        if (mt > x[8] && mt < 60) {
            f = mt - x[8];
            g = 8;
        }
        
        int nayi = (int) f;
        double pad1 = (f - nayi) * 4;
        int pad = (int) pad1;
        double viz1 = (pad1 - pad) * 15;
        int viz = (int) viz1;
        double kaya1 = (viz1 - viz) * 60;
        int kaya = (int) kaya1;
        double wky1 = (kaya1 - kaya) * 60;
        int wky = (int) wky1;
        
        diniData.put("nayi", nayi);
        diniData.put("pad", pad);
        diniData.put("viz", viz);
        diniData.put("kaya", kaya);
        diniData.put("wky", wky);
        diniData.put("g", g);
        
        return diniData;
	}

	public Map<String,Map<String,String[]>> getDasa() {
		
		double moon = p[2];
		int[] yd = new int[] {0,7,20,6,10,7,18,16,19,17};
		String[] p = new String[28];
		int[] y = new int[28];
		
		Map<String,String[]> ww1ld = new Hashtable<>();
		Map<String,String[]> wbwb1ld1 = new Hashtable<>();
		Map<String,String[]> e1e2e3 = new Hashtable<>();
		Map<String,Map<String,String[]>> dasa = new Hashtable<>();
		
		for(int i=1;i<=9;i++) {
			p[i] = myanmarLocalizedData.myanmarDasa[i];
			p[i+9] = myanmarLocalizedData.myanmarDasa[i];
			p[i+18]= myanmarLocalizedData.myanmarDasa[i];
			
			y[i] = yd[i];
			y[i+9] = yd[i];
			y[i+18] = yd[i];
		}
		
		int dd = info.getDay();
		int mm = info.getMonth();
		int yy = info.getYear();
		int d = LocalDate.now().getDayOfMonth();
		int M = LocalDate.now().getMonthValue();
		int xy = LocalDate.now().getYear();
		if(yy < 0) {
			xy = yy;
			M = mm;
			d = dd;
		}
		
		double b = 0;
		double d1 = moon * 0.075;
		double d2 = 1 - (d1 - ((int)d1));
		int nat = ((int)d1)+1;
		int Q = nat;
		
		if (Q > 18)Q = Q - 18;
		if (Q > 9)Q = Q - 9;
		double t = d2 * y[Q];
		double ttt = y[Q] - t;
		
		double cr = xy + M / 12d + d / 360d;
		double cr1 = yy + mm / 12d + dd / 360d;
		
		xq = cr1;Da1();w[1] = ww;b = cr1 + t;xq = b;Da1();w1[1] = ww;ld[1] = p[Q];
		int j = 1;
		for(int i=2;i<=9;i++) {
			xq = b;Da1();w[i]=ww;b=b+y[Q+j];xq=b;Da1();w1[i]=ww;ld[i]=p[Q+j];
			j++;
		}
		ww1ld.put("w", w);
		ww1ld.put("w1", w1);
		ww1ld.put("ld", ld);
		
		double age = cr - cr1;
		if (age >= 108) age = 100;
		if (age <= t) cr1 = cr1 - ttt;
		
		atr[1] = t;
		for(int i=1;i<=8;i++) {
			atr[i+1] = atr[i] + y[Q + i];
		}
		
		double at1 = 0;
		double age1 = 0;
		int l = 0;
		
		if(age < atr[1]) {
			at1 = y[Q];
			age1 = cr1;
			l = Q -1;
		}
		
		for(int i=1;i<=8;i++) {
			if(age < atr[i+1] && age > atr[i]) {
				at1 = y[Q + i];
				age1 = cr1 + atr[i];
				l = Q + (i-1);
			}
		}
		
		if(age > atr[9]) {
			at1 = y[Q + 8];
			age1 = cr1 + atr[8];
			l = Q + 7;
		}
		
		for(int i=1;i<=9;i++) {
			butti[i] = at1 * y[l + i] / 120d;
		}
		
		xq = age1;Da1();wb[1] = ww;b = age1 + butti[1];xq = b;Da1();wb1[1] = ww;ld1[1] = p[l+1];
		for(int i = 2;i<=9;i++) {
			xq = b; Da1();wb[i] = ww; b = b + butti[i];xq = b; Da1();wb1[i] = ww; ld1[i] = p[l + i];
		}
		
		wbwb1ld1.put("wb", wb);
		wbwb1ld1.put("wb1", wb1);
		wbwb1ld1.put("ld1", ld1);
		wbwb1ld1.put("l", new String[] {p[l+1]});
		
		double[] pya = new double[10];
		double py = 0;
		double age2 = 0;
		int l1 = 0;
		
		pya[1] = age1;
		for(int i = 1;i<=8;i++) {
			pya[i+1] = pya[i] + butti[i];
		}
		
		if(cr < pya[1]) {
			py = butti[9];
			age2 = pya[9];
			l1 = l - 1;
		}
		
		for(int i=1;i<=8;i++) {
			if(cr < pya[i+1] && cr > pya[i]) {
				py = butti[i];
				age2 = pya[i];
				l1 = l + (i-1);
			}
		}
		
		if(cr > pya[9]) {
			py = butti[9];
			age2 = pya[9];
			l1 = l + 8;
		}
		
		if (l1 >= 27) l1 = l1 - 27;
		if (l1 >= 18) l1 = l1 - 18;
		
		double[] buti = new double[10];
		for(int i=1;i<=9;i++) {
			buti[i] = py * y[l1 + i] / 120d;
		}
		
		xq = age2;Da1();e1[1] = ww;b = age2 + buti[1];xq = b;Da1();e2[1] = ww;e3[1] = p[l1+1];
		for(int i = 2;i<=9;i++) {
			xq = b; Da1();e1[i] = ww; b = b + buti[i];xq = b; Da1();e2[i] = ww; e3[i] = p[l1 + i];
		}
		
		e1e2e3.put("e1", e1);
		e1e2e3.put("e2", e2);
		e1e2e3.put("e3", e3);
		e1e2e3.put("l1", new String[] {p[l1 + 1]});
		
		dasa.put("ww1ld", ww1ld);
		dasa.put("wbwb1ld1", wbwb1ld1);
		dasa.put("e1e2e3", e1e2e3);
		
		return dasa;
		
	}
	
	public Map<String,String[][]> getHoro(){
		
		//double lateta, deg,yarsi,antha;
		double[] pya = new double[11], pan = new double[11];
		//double[] han = new double[13];
		//double[] can = new double[13];
		String[] pp = {"","1","2","3","4","5","6","0","8","9","လ"};
		String[][] pa = {{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""}};
		//String[][] pa = new String[12][11];
		String[][] ho = {{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""}};
		//String[][] ho = new String[12][13];
		String[][] na = {{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""},{"","","","","","","","","","",""}};
		//String[][] na = new String[13][11];
		
		double[] lord = new double[11], nawin = new double[11];
		//double[] nrasi = new double[13],
		double[] naw = new double[13]; 
		double[] hou = new double[13];
		Map<String,String[][]> horo = new Hashtable<>();
		
		for(int i=1;i<=10;i++) {
			pya[i] = (int)(p[i] / 30);
			pan[i] = ((int)p[i]) - pya[i] * 30;
		}
		
		for(int i=1;i<=10;i++) {
			for(int j=0;j<12;j++) {
				if(pya[i] == j) {
					pa[j][i] = pp[i];
				}
			}
		}
		
		double a = pya[10];c[13] = c[1];c[0] = 0;
		for(int i=0;i<=12;i++) {
			if(c[i] < 30) c[i] = c[i] + 360;
		}
		
		for(int i=0;i<=12;i++) {
			for(int j=1;j<=8;j++) {
				if(p[j] > c[i] && p[j] < c[i+1]) {
					hou[j] = i + a;
				}
			}
			if(p[10] >= 0 * i && p[10] < 30 * i) {
				hou[10] = i + a;
			}
		}
		
		for(int i=0;i<=24;i++) {
			if(hou[8] == i) hou[9] = i+6;
		}
		
		if (hou[9] > 24) hou[9] = hou[9] - 24;
		if (hou[9] > 12) hou[9] = hou[9] - 12;
		
		for(int i=1;i<=12;i++) {
			if (hou[i] >= 12) hou[i] = hou[i] - 12;
		}
		pp[10] = "တ";
		for(int i=1;i<=10;i++) {
			for(int j=0;j<=11;j++) {
				if (hou[i] == j) {
					ho[j][i] = pp[i];
				}
			}
		}
		
		for(int i=1;i<=10;i++) {
			if(pya[i] == 0 || pya[i] == 4 || pya[i] == 8 ) lord[i] = 0;
			if(pya[i] == 1 || pya[i] == 5 || pya[i] == 9 ) lord[i] = 9;
			if(pya[i] == 2 || pya[i] == 6 || pya[i] == 10 ) lord[i] = 6;
			if(pya[i] == 3 || pya[i] == 7 || pya[i] == 11 ) lord[i] = 3;
		}
		
		for(int i=1;i<=10;i++) {
			nawin[i] = (int)(pan[i] / 3.3333333);
		}
		
		for(int i=1;i<=10;i++) {
			for(int j=0;j<=8;j++) {
				if(nawin[i] == j) naw[i] = lord[i] + j;
			}
			if(naw[i] > 11) naw[i] = naw[i] - 12;
		}
		pp[10] = "လ";
		
		for(int i=1;i<=10;i++) {
			for(int j=0;j<=11;j++) {
				if(naw[i] == j) na[j][i] = pp[i];
			}
		}
		horo.put("pa", pa);
		horo.put("ho", ho);
		horo.put("na", na);
		
		return horo;
	}
	
	public double[] getPya() {
		return pya;
	}

	public double[] getPan() {
		return pan;
	}

	public double[] getPlat() {
		return plat;
	}

	public double[] getHya() {
		return hya;
	}

	public double[] getHan() {
		return han;
	}

	public double[] getHlat() {
		return hlat;
	}

	public double[] getCya() {
		return cya;
	}

	public double[] getCan() {
		return can;
	}

	public double[] getClat() {
		return clat;
	}

	public double[] getLatan() {
		return latan;
	}

	public double[] getLatlat() {
		return latlat;
	}

	public double[] getDlan() {
		return dlan;
	}

	public double[] getDllat() {
		return dllat;
	}
	
	public AstrologicalData getAstroData() {
		return this.astroData;
	}
	
	public MyanmarDate getMyanmarDate() {
		return this.md;
	}
	
	public InputData getInputData() {
		return this.info;
	}
	
	private void titee() {
		Map<String,double[]> pdata = astroData.getPlaneticalData();
		double p1 = pdata.get("planet0")[1];
		double p2 = pdata.get("planet0")[2];
		
    	if(p2 < p1) p2 = p2 + 360;
    	double a = ((p2 - p1) / 12);
    	double bb = (int)a;
    	double cc = (a - bb) * 60;
    	double d = (int)cc;
    	//double e = (cc - d) * 60;
    	//double f = (int)e;
    	if(bb > 15) bb = bb - 15;
    	r1[0] = bb;
    	r2[0] = d;
    	
    	if(p2 > 360) p2 = p2 - 360;
    	a = p2 / 13.333333333;
    	double a1 = (int)a;
    	double a2 = (a - a1) * 60;
    	double a3 = (int)a2;
    	//double a4 = (a2 -  a3) * 60;
    	//double a5 = (int)a4;
    	r1[1] = a1;
    	r2[1] = a3;
    	r1[2] = a1;
    	r2[2] = (int)(a3 / 15);
    	
    	double adi = (md.getMyanmarYear() + 15) - ((int)((md.getMyanmarYear() + 15) / 27)) * 27;
    	double tdd;
    	if(md.getThoteDateDan() == 0) {
    		tdd = 0;
    	}
    	else {
    		tdd = md.getThoteDateDan();
    	}
    	
    	double adip = (int) tdd / 91;
    	//double adig = (int)(((tdd / 91) - adip) * 15);
    	r1[5] = adi;
    	r2[5] = adip;
    	
    	for(int i = 1;i<=8;i++) {
    		r1[i+5] = adi + (3 * i);
    		if(r1[i+5] > 27) {
    			r1[i+5] = r1[i+5] - 27;
    		}
    		r2[i+5] = adip;
    	}
    	
    	double pyinsan = (md.getMyanmarYear() - (((int)(md.getMyanmarYear() / 108)) * 108)) / 4;
    	double pyinnak = (int)pyinsan;
    	double pyinpak = (int)((pyinsan - pyinnak) * 4);
    	double pyingadi = (int)(tdd / 24);
    	r1[3] = pyinnak;
    	r2[3] = pyinpak;
    	a = p2 / 13.3333333333;
    	double aa = pyingadi / 60 + pyinpak / 4 + pyinnak;
    	double aa1 = a + aa;
    	double aa2 = (int) aa1;
    	double aa3 = (aa1 - aa2) * 4;
    	double aa4 = (int)aa3;
    	r1[4] = aa2;
    	r2[4] = aa4;
	}
	
	private String getAstro() {
		String st = "";
		double stro = ((info.getHours() * 60 + info.getMinutes()) / 4) + p[1];
		if (stro > 360) stro = stro - 360;
		double[] ast = new double[28];
		ast[1] = 8; ast[2] = 18; ast[3] = 34;
		ast[4] = 46; ast[5] = 60; ast[6] = 65;
		ast[7] = 92; ast[8] = 106; ast[9] = 110;
		ast[10] = 129; ast[11] = 145; ast[12] = 154;
		ast[13] = 164; ast[14] = 179; ast[15] = 192;
		ast[16] = 213; ast[17] = 224; ast[18] = 229;
		ast[19] = 242; ast[20] = 257; ast[21] = 262;
		ast[22] = 275; ast[23] = 287; ast[24] = 313;
		ast[25] = 323; ast[26] = 339; ast[27] = 350;
		if (stro > 350 || stro < 8) st = myanmarLocalizedData.myanmarAstro[1];
		for(int i=1;i<=27;i++) {
			if(stro > ast[i] || stro < ast[i + 1]) st = myanmarLocalizedData.myanmarAstro[i];
		}
		int ab = (int)stro;
		int ac = (int)((stro - ab)*60);
		StringBuilder sb = new StringBuilder();
		sb.append(st);
		sb.append("  နက္ခတ်");
		sb.append("  ");
		sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString(ab)) + " အံသာ");
		sb.append("  ");
		sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString(ac))+ " လိတ္တာ");
		sb.append("  ");
		sb.append("မွန်းတည့်မတ်သောအခါ");
		
		return sb.toString();
	}
	
	private String getPawar() {
		double r = 57.29577951;
		double pres = ((int)astroData.getPres()) + (((int)astroData.getPresLatter()) / 60d); 
		double xp = p[1] + pres;
		double a = Math.sin(xp / r) * Math.sin(24 / r);
		double xb = Math.atan(a / (Math.sqrt(1 - a * a) + 1E-20)) * r;
		double Lati = info.getLocation().getGeoLocation().getLatitude() + info.getLocation().getGeoLocation().getLatMinutes() / 60d;
		double xx = 7 * Math.tan((Lati - xb) / r);
		int d = (int) xx;
		double M1 = (xx - d) * 60;
		double M = Math.floor(M1);
		double san = (M1 - M) * 60;
		double san1 = Math.floor(san);
		StringBuilder sb = new StringBuilder();
		sb.append("ထိုနေ့၏ မွန်းတည့်ဖဝါးသော်ကား ");
		sb.append("  ");
		sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString(Math.abs(d))));
		sb.append(" ဘဝါး");
		sb.append("  ");
		sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)Math.abs(M))));
		sb.append(" မုယော");
		sb.append("  ");
		sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)Math.abs(san1))));
		sb.append(" ဆံခြည်");
		sb.append("   ရရသောအခါ");
		
		return sb.toString();
	}
	
	public String getAstroPawar() {
		if(info.getHours() <= 6 && info.getHours() >= 18) {
			this.astroPawar = getAstro();
		}
		else {
			this.astroPawar = getPawar();
		}
		
		return this.astroPawar;
	}
	
	public double[] getR1() {
		return this.r1;
	}
	
	public double[] getR2() {
		return this.r2;
	}
	
	private void Da1() {
		int aa = (int)xq;
		double b1 = (xq - aa) * 12;
		int bb = (int)b1;
		double c1 = (b1 - bb) * 30;
		int cc = ((int)c1) + 1;
		if(bb == 2 && cc == 30) cc = cc - 1;
		
		if(bb == 0) {
			bb = bb + 12;
			aa = aa - 1;
		}
		String a1 = Integer.toString(cc);
		String a2 = Integer.toString(bb);
		String a3 = Integer.toString(aa);
		
		if(bb < 10) {
			ww = a1 + "      " + a2 + "      " + a3;
		}
		else {
			ww = a1 + "      " + a2 + "      " + a3;
		}
	}
	
	private void calculateHoroscope() {
		double[] pp = astroData.getPlaneticalData().get("planet0");
		h = astroData.getHouseData().get("h");
		c = astroData.getHouseData().get("c");
		lat = astroData.getPlaneticalData().get("lat0");
		dl = astroData.getDegreeData().get("dl");
		
		for(int i=1;i< pp.length;i++) {
			p[i] = pp[i];
		}
		
		p[10] = h[1];

		for(int i=1;i<=10;i++) {
			deg = p[i];
			DMS();
			pya[i] = yarsi;
			pan[i] = antha;
			plat[i] = lateta;
		}
		
		for(int i=1;i<=12;i++) {
			deg = h[i];
			DMS();
			hya[i] = yarsi;
			han[i] = antha;
			hlat[i] = lateta;
		}
		
		for(int i=1;i<=12;i++) {
			deg = c[i];
			DMS();
			cya[i] = yarsi;
			can[i] = antha;
			clat[i] = lateta;
		}
		
		for(int i=2;i<=7;i++) {
			deg = lat[i];
			DMS1();
			latan[i] = antha;
			latlat[i] = lateta;
		}
		
		for (int i=1;i<=7;i++) {
			deg = dl[i];
			DMS1();
			dlan[i] = antha;
			dllat[i] = lateta;
		}
	}
	
	private void DMS() {
		yarsi = (int)(deg / 30);
		antha = ((int)deg)-yarsi * 30;
		lateta = (int)((deg - ((int)deg)) * 60);
		if (yarsi >= 12) yarsi = yarsi - 12;
	}
	
	private void DMS1() {
		antha = (int)(deg);
		lateta = (int)((Math.abs(deg) - ((int)Math.abs(deg))) * 60);
	}
	
	
}
