package com.shwayoe.logic;

import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AstrologicalData {
	private int hh;
	private int mm;
	private int ss;
	private int dll;
	private int dm;
	private int dla;
	private int lma;
	private double tz;
	private JTKDays jdo;
	private double presd;
	Map<String, Number> lstData;
	
    public AstrologicalData(int dy,int mn,int yr,int hh, int mm, int ss,int dll, int dm, int dla, int lma, double tz) {
    	this.hh = hh;
    	this.mm = mm;
    	this.ss = ss;
    	this.dll = dll;
    	this.dm = dm;
    	this.dla = dla;
    	this.lma = lma;
    	this.tz = tz;
    	this.jdo = new JTKDays(dy,mn,yr,hh,mm,ss);
    	
    	//calculateLST(hh, mm, ss, dla, lma, tz);
    	calculateLST(hh, mm, ss, dll, dm, tz);
   }
    
   public Map<String,double[]> getPlaneticalData() {
	   
		Map<String, double[]> data = new Hashtable<>();

		double twn = jdo.getTwnDay();
		twn = twn + ((hh / 24d) + (mm / 1440d) + (ss / 86400d));

		for (int k = 0; k <= 1; k++) {
			twn += k;
			List<double[]> astroData = getAstrologicalData(twn);

			double r = 57.29577951d;
			double e1 = 2.183333333, e2 = 5.05, e3 = 0.2, ee3 = 0.64444444, e4 = 0.0777777777, ee4 = 0.3666666666,
					e5 = 0.0888888888, ee5 = 0.2, e6 = 0.030555555, ee6 = 0.7222222222, e7 = 0.13333333333,
					ee7 = 0.11111111111;
			double[] p = new double[10];
			double[] lat = new double[8];

			p[1] = sin((astroData.get(1)[1] - astroData.get(0)[1]) / r) * e1 + astroData.get(0)[1];
			p[2] = sin((astroData.get(0)[9] - astroData.get(0)[2]) / r) * e2 + astroData.get(0)[2];

			double Q = astroData.get(0)[1] - astroData.get(0)[3], a = ee3, pl = astroData.get(0)[3];
			double mar1 = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r))) * r / 2) + pl;
			double mar2 = Math.sin((astroData.get(1)[3] - mar1) / r) * e3;
			mar2 = ((Math.sqrt(1 + mar2 * mar2) * Math.atan(mar2) * r) / 2) + mar1;
			double mar3 = Math.sin((astroData.get(1)[3] - mar2) / r) * e3;
			mar3 = (Math.sqrt(1 + mar3 * mar3) * Math.atan(mar3) * r) + astroData.get(0)[3];
			Q = astroData.get(0)[1] - mar3;
			a = ee3;
			pl = mar3;
			p[3] = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r)))) * r + pl;

			Q = astroData.get(0)[4] - astroData.get(0)[1];
			a = ee4;
			pl = astroData.get(0)[1];
			double mcu1 = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r))) * r / 2) + pl;
			double mcu2 = Math.sin((astroData.get(1)[4] - mcu1) / r) * e4;
			mcu2 = ((Math.sqrt(1 + mcu2 * mcu2) * Math.atan(mcu2) * r) / 2) + mcu1;
			double mcu3 = Math.sin((astroData.get(1)[4] - mcu2) / r) * e4;
			mcu3 = (Math.sqrt(1 + mcu3 * mcu3) * Math.atan(mcu3) * r) + astroData.get(0)[1];
			Q = astroData.get(0)[4] - mcu3;
			a = ee4;
			pl = mcu3;
			p[4] = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r)))) * r + pl;

			Q = astroData.get(0)[1] - astroData.get(0)[5];
			a = ee5;
			pl = astroData.get(0)[5];
			double jup1 = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r))) * r / 2) + pl;
			double jup2 = Math.sin((astroData.get(1)[5] - jup1) / r) * e5;
			jup2 = ((Math.sqrt(1 + jup2 * jup2) * Math.atan(jup2) * r) / 2) + jup1;
			double jup3 = Math.sin((astroData.get(1)[5] - jup2) / r) * e5;
			jup3 = (Math.sqrt(1 + jup3 * jup3) * Math.atan(jup3) * r) + astroData.get(0)[5];
			Q = astroData.get(0)[1] - jup3;
			a = ee5;
			pl = jup3;
			p[5] = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r)))) * r + pl;

			Q = astroData.get(0)[6] - astroData.get(0)[1];
			a = ee6;
			pl = astroData.get(0)[1];
			double ven1 = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r))) * r / 2) + pl;
			double ven2 = Math.sin((astroData.get(1)[6] - ven1) / r) * e6;
			ven2 = ((Math.sqrt(1 + ven2 * ven2) * Math.atan(ven2) * r) / 2) + ven1;
			double ven3 = Math.sin((astroData.get(1)[6] - ven2) / r) * e6;
			ven3 = (Math.sqrt(1 + ven3 * ven3) * Math.atan(ven3) * r) + astroData.get(0)[1];
			Q = astroData.get(0)[6] - ven3;
			a = ee6;
			pl = ven3;
			p[6] = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r)))) * r + pl;

			Q = astroData.get(0)[1] - astroData.get(0)[7];
			a = ee7;
			pl = astroData.get(0)[7];
			double sat1 = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r))) * r / 2) + pl;
			double sat2 = Math.sin((astroData.get(1)[7] - sat1) / r) * e7;
			sat2 = ((Math.sqrt(1 + sat2 * sat2) * Math.atan(sat2) * r) / 2) + sat1;
			double sat3 = Math.sin((astroData.get(1)[7] - sat2) / r) * e7;
			sat3 = (Math.sqrt(1 + sat3 * sat3) * Math.atan(sat3) * r) + astroData.get(0)[7];
			Q = astroData.get(0)[1] - sat3;
			a = ee7;
			pl = sat3;
			p[7] = (Math.atan(a * Math.sin(Q / r) / (1 + a * Math.cos(Q / r)))) * r + pl;

			p[8] = 360 - astroData.get(0)[8];
			p[9] = p[8] + 180;

			for (int i = 1; i <= 9; i++) {
				if (p[i] < 0)
					p[i] = p[i] + 360;
				if (p[i] > 360)
					p[i] = p[i] - 360;
			}

			data.put("planet" + k, p);

			lat[1] = 0;
			lat[2] = Math.sin((p[2] - p[8]) / r) * 5.116666667;
			pl = astroData.get(0)[3];
			Q = astroData.get(0)[1] - mar3;
			a = ee3;
			double pro4 = p[3] - mar3, b1 = 1.75, ntt = astroData.get(2)[3];
			lat[3] = (Math.sin((pl - (ntt + pro4)) / r) * b1) / (a * Math.sin(Q / r) / Math.sin(pro4 / r));

			pl = astroData.get(0)[4];
			Q = astroData.get(0)[4] - mcu3;
			a = ee4;
			pro4 = p[4] - mcu3;
			b1 = 2.333333333;
			ntt = astroData.get(2)[4];
			lat[4] = (Math.sin((pl - (ntt + pro4)) / r) * b1) / (a * Math.sin(Q / r) / Math.sin(pro4 / r));

			pl = astroData.get(0)[5];
			Q = astroData.get(0)[1] - jup3;
			a = ee5;
			pro4 = p[5] - jup3;
			b1 = 1.166666666;
			ntt = astroData.get(2)[5];
			lat[5] = (Math.sin((pl - (ntt + pro4)) / r) * b1) / (a * Math.sin(Q / r) / Math.sin(pro4 / r));

			pl = astroData.get(0)[6];
			Q = astroData.get(0)[6] - ven3;
			a = ee6;
			pro4 = p[6] - ven3;
			b1 = 2.333333333;
			ntt = astroData.get(2)[6];
			lat[6] = (Math.sin((pl - (ntt + pro4)) / r) * b1) / (a * Math.sin(Q / r) / Math.sin(pro4 / r));

			pl = astroData.get(0)[7];
			Q = astroData.get(0)[1] - sat3;
			a = ee7;
			pro4 = p[7] - sat3;
			b1 = 2.333333333;
			ntt = astroData.get(2)[7];
			lat[7] = (Math.sin((pl - (ntt + pro4)) / r) * b1) / (a * Math.sin(Q / r) / Math.sin(pro4 / r));

			data.put("lat" + k, lat);

		}

		return data;
	}

	public Map<String, double[]> getDegreeData() {

		Map<String, double[]> planetData = getPlaneticalData();
		Map<String, double[]> degreeData = new Hashtable<>();

		double kali = jdo.getKaliDay();

		double r = 57.29577951;
		double[] dl = new double[8];

		double presg = (int) (kali + 88) / 1800;
		presg = (presg / 4) - (int) (presg / 4);
		presg = presg * 4;
		double pres = (kali + 88) * 0.015;
		pres = pres - (int) (pres / 27) * 27;
		if (presg == 1 || presg == 3)
			pres = 27 - pres + 0.015;

		double pl = planetData.get("planet0")[1], d1 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		double a = d1, latt = 0;
		dl[1] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[2];
		double d2 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d2;
		latt = planetData.get("lat0")[2];
		dl[2] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[3];
		double d3 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d3;
		latt = planetData.get("lat0")[3];
		dl[3] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[4];
		double d4 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d4;
		latt = planetData.get("lat0")[4];
		dl[4] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[5];
		double d5 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d5;
		latt = planetData.get("lat0")[5];
		dl[5] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[6];
		double d6 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d6;
		latt = planetData.get("lat0")[6];
		dl[6] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		pl = planetData.get("planet0")[7];
		double d7 = Math.sin((pl + pres) / r) * Math.sin(23.45 / r);
		a = d7;
		latt = planetData.get("lat0")[7];
		dl[7] = (Math.sqrt(1 + a * a) * Math.atan(a) * r) + latt;

		degreeData.put("dl", dl);

		return degreeData;
	}

	public Map<String, double[]> getHouseData() {

		Map<String, double[]> houseData = new Hashtable<>();

		Map<String, Number> lstd = calculateLST(hh, mm, ss, dll, dm, tz); //dll or dla

		double lst = ((int) lstd.get("lstHours")) + ((int) lstd.get("lstMinutes") / 60d)
				+ ((int) (lstd.get("lstSeconds")) / 3600d);

		
		double pres = getPres();
		
		double r = 57.29577951;
		double[] h = new double[14];
		double[] c = new double[14];

		double qu = (int) (lst / 6);
		double qu1 = 0;
		if (qu == 1 || qu == 2)
			qu1 = 180;
		if (qu == 3)
			qu1 = 360;

		lst = lst * 15d;
		double lati = (int) (dla + lma / 60);
		h[1] = Math.atan(
				Math.sin(23.45 / r) * Math.tan(lati / r) / Math.cos(lst / r) + Math.cos(23.45 / r) * Math.tan(lst / r))
				* r + 90 + qu1 - pres;
		h[10] = ((Math.atan(Math.tan(lst / r) / Math.cos(23.45 / r))) * r) + qu1 - pres;
		h[4] = h[10] + 180;
		double ad1 = (h[4] - h[1]) / 6d;
		double ad2 = (30d - ad1);
		c[1] = h[1] + ad1;
		h[2] = c[1] + ad1;
		c[2] = h[2] + ad1;
		h[3] = c[2] + ad1;
		c[3] = h[3] + ad1;
		c[4] = h[4] + ad2;
		h[5] = c[4] + ad2;
		c[5] = h[5] + ad2;
		h[6] = c[5] + ad2;
		c[6] = h[6] + ad2;
		h[7] = h[1] + 180;
		h[8] = h[2] + 180;
		h[9] = h[3] + 180;
		h[11] = h[5] + 180;
		h[12] = h[6] + 180;
		c[7] = c[1] + 180;
		c[8] = c[2] + 180;
		c[9] = c[3] + 180;
		c[10] = c[4] + 180;
		c[11] = c[5] + 180;
		c[12] = c[6] + 180;

		for (int j = 1; j <= 12; j++) {
			if (h[j] < 0)
				h[j] = h[j] + 360;
			if (h[j] > 360)
				h[j] = h[j] - 360;
			if (c[j] < 0)
				c[j] = c[j] + 360;
			if (c[j] > 360)
				c[j] = c[j] - 360;
		}

		houseData.put("h", h);
		houseData.put("c", c);

		return houseData;
	}

	// Calcuate LST - LST - Local Sidereal Time
	private Map<String, Number> calculateLST(int hh, int mm, int ss, int dll, int dm, double tz) {
		double jd = jdo.getJulianDay();
		double dd = dll + (dm / 60d) + (9.97 / 3600d); // convert decimal degree.
		double dh = hh + (mm / 60d) + (ss / 3600d); // convert decimal hours.
		double ajd = jd - 2444240.5d + ((dh - tz) / 24d) + (tz / 24); // adjust Julian days
		double jdyear = ajd / 365.25; // convert Julian days into year.
		double pc = 100.80247 + 360 * jdyear + 0.000021165061 * (1 + ajd) + dd + (dh - tz) * 15; // calculate sidereal
																									// time or
																									// positional
																									// calculation.
		double lst = pc / 15;
		double lsth = lst - Math.floor(lst / 24d) * 24d;
		double lstm = ((lsth - Math.floor(lsth)) * 60);
		double lsts = ((lstm - Math.floor(lstm)) * 60);

		lstData = new Hashtable<>();
		//lstData.put("lstHours", (int) lsth);
		lstData.put("lstHours", (int)lsth);
		lstData.put("lstMinutes", (int) lstm);
		lstData.put("lstSeconds", (int) lsts);

		return lstData;
	}
	
	public double getPres() {
		double kali = jdo.getKaliDay();
		double presg = (int) (kali + 88) / 1800;
		presg = (presg / 4) - (int) (presg / 4);
		presg = presg * 4;
		presd = (kali + 88) * 0.015;
		presd = presd - (int) (presd / 27) * 27;
		if (presg == 1 || presg == 3)
			presd = 27 - presd + 0.015;
		
		return presd;
	}
	
	public double getPresLatter() {
		double p = getPres();
		double pllt = ((p - (int)p)*60);
		return (int)pllt;
	}
	
	public Map<String,Number> getLST(){
		return lstData;
	}

	public Map<String,Number> getThitee() {
		Map<String,double[]> pdata = this.getPlaneticalData();
		Map<String,Number> thiteeNumbers = new Hashtable<>();
		
		double a = pdata.get("planet0")[1];
		double b = pdata.get("planet0")[2];
		if ( b < a) {
			b = b + 360;
		}
		double thi = b - a;
		thi = thi / 12;
		int thi1 = (int)thi;
		double thi2 = (thi - thi1) * 60;
		double thi3 = thi2 - ((int)thi2);
		if (thi3 > 0.5) thi2 = thi2 + 1;
		if (thi1 >= 15) thi1 = thi1 - 15;
		thiteeNumbers.put("thiteeHour", thi1);
		thiteeNumbers.put("thiteeMinutes", (int)thi2);
		
		return thiteeNumbers;
	}

	private List<double[]> getAstrologicalData(double twn) {

		List<double[]> list = new ArrayList<>();

		double[] pm = new double[10];
		double[] apm = new double[10];
		double[] pnt = new double[10];

		double kali = twn * 4320000 / 1577917828;
		// double M = kali - 3739;

		double[] b = { 0, 4320000, 57753336, 2296832, 17937060, 364220, 7022376, 146568, 232238, 488203 }; // b[9] is
																											// moonapogee.
		double[] ap = { 0, 387, 0, 204, 368, 900, 535, 39 };
		double[] nt = { 0, 0, 0, 214, 488, 174, 903, 662 };
		double S = 1577917828;
		double sage = 1955880000;
		double age = 4320000000L;

		// calculate PM data.
		for (int i = 1; i <= 9; i++) {
			double bgn = b[i];
			pm[i] = ((bgn * twn / S) - ((int) (bgn * twn / S))) * 360;
		}
		pm[1] -= 0.0582;
		pm[2] -= 1.020865741;
		pm[3] += 0.966884259;
		pm[4] -= 4.251194444;
		pm[5] -= 3.00200463;
		pm[6] -= 6.100291667;
		pm[7] += 5.005763889;
		pm[8] += 184.11025;
		pm[9] += 85.66124537;

		for (int i = 1; i <= 9; i++) {
			if (pm[i] > 360) {
				pm[i] = pm[i] - 360;
			}
		}

		// calculate AP data.
		for (int i = 1; i <= 7; i++) {
			double bgn = ap[i];
			apm[i] = ((bgn * (sage + kali) / age) - ((int) (bgn * (sage + kali) / age))) * 360;
		}

		apm[3] += 1;
		apm[4] += 13;
		apm[5] -= 1;
		apm[6] -= 2;
		apm[7] += 12;

		// calculate NT data.
		for (int i = 1; i <= 7; i++) {
			double bgn = nt[i];
			pnt[i] = ((bgn * (sage + kali) / age) - ((int) (bgn * (sage + kali) / age))) * 360;
			pnt[i] = 360 - pnt[i];
		}

		pnt[3] -= 20;
		pnt[4] += 4;
		pnt[5] -= 3;
		pnt[6] -= 6;
		pnt[7] -= 9;

		for (int i = 1; i <= 9; i++) {
			if (apm[i] < pm[i]) {
				apm[i] = apm[i] + 360;
			}
		}

		list.add(pm);
		list.add(apm);
		list.add(pnt);

		return list;
	}
}
