package com.shwayoe.ui;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.shwayoe.data.myanmarLocalizedData;
import com.shwayoe.logic.HoroscopeData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class HoroscopePanel extends JPanel implements Printable {
	 private BufferedImage bufferedImage;

	private static final long serialVersionUID = 1L;

	private double[] pan;
	private double[] pya;
	private double[] plat;
	private String[] planet2 = new String[5];
	private double[] latan;
	private double[] latlat;
	private double[] dlan;
	private double[] dllat;

	private double[] hya;
	private double[] han;
	private double[] hlat;
	private double[] cya;
	private double[] can;
	private double[] clat;
	private double[] r1;
	private double[] r2;
	
	private String[][] wbeData = new String[9][9];
	
	private String latt;
	private String sann;
	private String yarthi;
	
	private String nayt;
	
	private String lone;
	private String pat;
	private String gati;
	private String wikati;
	
	private String hawrat;
	private String tarni;
	private String nawin;
	
	private String nayi;
	private String pad;
	private String viz;
	private String kaya;
	private String wky;
	private String w;
	
	private String astroPawar;
	
	private String ewLog;
	private String snLat;
	
	private String l = "";
	private String l1 = "";
	
	// Set preferred size for the canvas
    private static final int CANVAS_WIDTH = 740;
    private static final int CANVAS_HEIGHT = 850;
    
    private String[] yathiData = {"","","","","","","","","","","",""};
    private String[] bawarData = {"","","","","","","","","","","",""};
    private String[] nawinData = {"","","","","","","","","","","",""};
    
	private double[] circleDegree = {0,28.5,60.5,90,118.5,151.5,180,208.5,239.5,270,298.5,328.5,};
	
    private HoroscopeData horoData;
    
    public HoroscopePanel(HoroscopeData horoData) {
    	setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
    	bufferedImage = new BufferedImage(CANVAS_WIDTH + 35, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    	this.horoData = horoData;
    	populateData();
    	setBackground(Color.WHITE);
    	
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        createHoroscope(g2d);
        
        Graphics2D g2dImage = bufferedImage.createGraphics();
        g2dImage.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2dImage.setColor(Color.WHITE);
        g2dImage.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g2dImage.setColor(Color.BLACK);
        createHoroscope(g2dImage);
        g2dImage.dispose();
        
   }
    
    public void saveImage() {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as PNG");
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            
            if (!fileToSave.getName().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
            }

            
            try {
                ImageIO.write(bufferedImage, "png", fileToSave);
                System.out.println("Image saved as " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
   private void drawTextData(Graphics2D g2d,int centerX, int centerY,String text) {
	   FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
       int textX = centerX + (755 - metrics.stringWidth(text)) / 2;
       int textY = centerY + ((30 - metrics.getHeight()) / 2) + metrics.getAscent();
	   g2d.drawString(text, textX, textY);
   }
   
   private void drawTablewithDate(Graphics2D g2d,int centerX,int centerY) {
	   g2d.drawString("(မှ) ထူလ             ဒဿာ(ထိ)               သခင်             " + l + "       ထူလ၏အန္တရရ                      သခင်            " + l1 + "      အန္တရရ၏ပျတန္တရရ                     သခင်", centerX + 45, centerY + 15);
	   int cellWidth = 105;
	   int cellHeight = 20;
	   int specialCellWidth = cellWidth / 3;
	   int x = 0;
	   for(int row=1;row<=9;row++) {
		   x = centerX;
		   for(int col = 0;col<9;col++) {
			   int colWidth = (col+1) % 3 == 0 ? specialCellWidth : cellWidth;
			   int y = centerY + (cellHeight *(row));
			   g2d.drawRect(x, y, colWidth, cellHeight);
			   FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
		        int textX = x + (colWidth - metrics.stringWidth(wbeData[col][row])) / 2;
		        int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent();
		        g2d.drawString(myanmarLocalizedData.convertMMNumber(wbeData[col][row]), textX, textY);
			   x += colWidth;
		   }
	   }
   }
    
   private void drawCircleWithLines(Graphics2D g2d, int centerX, int centerY, int radius,String title,String[] circleData) {
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        
        int x1 = centerX - radius + (4 * (2 * radius) / 10);
        int y1Offset = (int) Math.sqrt(radius * radius - (x1 - centerX) * (x1 - centerX));
        int y1 = centerY - y1Offset;
        int y2 = centerY + y1Offset;
        
        g2d.drawLine(x1, y1, x1, y2);
        
        int x2 = centerX - radius + (6 * (2 * radius) / 10);
        int y2Offset = (int) Math.sqrt(radius * radius - (x2 - centerX) * (x2 - centerX));
        int y3 = centerY - y2Offset;
        int y4 = centerY + y2Offset;
        
        g2d.drawLine(x2, y3, x2, y4);
        
        int y5 = centerY - radius + (4 * (2 * radius) / 10);
        int x1Offset = (int) Math.sqrt(radius * radius - (y5 - centerY) * (y5 - centerY));
        int x3 = centerX - x1Offset;
        int x4 = centerX + x1Offset;
        
        g2d.drawLine(x3, y5, x4, y5);
        
        int y6 = centerY - radius + (6 * (2 * radius) / 10);
        int x2Offset = (int) Math.sqrt(radius * radius - (y6 - centerY) * (y6 - centerY));
        int x5 = centerX - x2Offset;
        int x6 = centerX + x2Offset;
        
        g2d.drawLine(x5, y6, x6, y6);
        
        int offset = (int)(radius * .3);
        
        double angle = Math.toRadians(45);
        int x7 = centerX + (int) (offset * Math.cos(angle));
        int y7 = centerY + (int) (offset * Math.sin(angle));
        int x8 = centerX + (int) (radius * Math.cos(angle));
        int y8 = centerY + (int) (radius * Math.sin(angle));
        g2d.drawLine(x7, y7, x8, y8);
        
        double angle2 = Math.toRadians(135);
        int x9 = centerX + (int) (offset * Math.cos(angle2));
        int y9 = centerY + (int) (offset * Math.sin(angle2));
        int x10 = centerX + (int) (radius * Math.cos(angle2));
        int y10 = centerY + (int) (radius * Math.sin(angle2));
        g2d.drawLine(x9, y9, x10, y10);
        
        double angle3 = Math.toRadians(225);
        int x11 = centerX + (int) (offset * Math.cos(angle3));
        int y11 = centerY + (int) (offset * Math.sin(angle3));
        int x12 = centerX + (int) (radius * Math.cos(angle3));
        int y12 = centerY + (int) (radius * Math.sin(angle3));
        g2d.drawLine(x11, y11, x12, y12);
        
        double angle4 = Math.toRadians(315);
        int x13 = centerX + (int) (offset * Math.cos(angle4));
        int y13 = centerY + (int) (offset * Math.sin(angle4));
        int x14 = centerX + (int) (radius * Math.cos(angle4));
        int y14 = centerY + (int) (radius * Math.sin(angle4));
        g2d.drawLine(x13, y13, x14, y14);
        
        Font font = new Font("Myanmar", Font.PLAIN, 10);
        g2d.setFont(font);
        FontMetrics metrics = g2d.getFontMetrics(font);
        
        int cX1 = centerX - metrics.stringWidth(title) / 2;
		int cY1 = centerY + metrics.getAscent() / 2;
		g2d.drawString(title, cX1, cY1);
        
		for(int i = 0;i<circleData.length;i++) {
			double tangle1 = Math.toRadians(circleDegree[i]); // 0 28.5 60.5 90 118.5 151.5 180 208.5 239.5 270 298.5 328.5
			int tx1 = centerX + (int) ((radius - 20) * Math.cos(tangle1));
			int ty1 = centerY + (int) ((radius - 20) * Math.sin(tangle1));
			int textX1 = tx1 - metrics.stringWidth(circleData[i]) / 2;
			int textY1 = ty1 + metrics.getAscent() / 2;
			g2d.drawString(myanmarLocalizedData.convertMMNumber(circleData[i]), textX1, textY1);
		}
     }
    
		private void setYathiData(int i, String data) {
			this.yathiData[i] = data;
		}
		
		private void setBawarData(int i, String data) {
			this.bawarData[i] = data;
		}
		
		private void setNawinData(int i, String data) {
			this.nawinData[i] = data;
		}

		private void setWbeData(int i, String[] w) {
			this.wbeData[i] = w;
		}

		public void populateData() {

			this.hlat = horoData.getHlat();
			this.cya = horoData.getCya();
			this.can = horoData.getCan();
			this.clat = horoData.getClat();
			this.pan = horoData.getPan();
			this.pya = horoData.getPya();
			this.plat = horoData.getPlat();
			this.latan = horoData.getLatan();
			this.latlat = horoData.getLatlat();
			this.dlan = horoData.getDlan();
			this.dllat = horoData.getDllat();
			this.hya = horoData.getHya();
			this.han = horoData.getHan();

			// Name
			for (int i = 0; i < 12; i++) {
				if (this.pya[10] == i)
					latt = myanmarLocalizedData.yarthiGwins[i];
				if (this.pya[2] == i)
					sann = myanmarLocalizedData.yarthiGwins[i];
				if (this.pya[1] == i)
					yarthi = myanmarLocalizedData.yarthiGwins[i];
			}

			// mday
			double jd = horoData.getMyanmarDate().getJulianDay();
			double d7 = jd - 1;
			d7 = ((int) d7) - ((int) ((int) d7) / 7) * 7;
			for (int i = 0; i < 7; i++) {
				if (d7 == i)
					nayt = myanmarLocalizedData.myanmarNayt[i];
			}

			// Lsnk
			int[] lsnk = horoData.Lsnk();
			lone = Integer.toString(lsnk[0]);
			pat = Integer.toString(lsnk[1]);
			gati = Integer.toString(lsnk[2]);
			wikati = Integer.toString(lsnk[3]);

			// Hawrat
			int haw;
			if (pya[10] == 0 || pya[10] == 2 || pya[10] == 4 || pya[10] == 6 || pya[10] == 8 || pya[10] == 10) {
				haw = 1;
			} else {
				haw = 2;
			}
			if (haw == 1 && pan[10] <= 15)
				hawrat = "တနင်္ဂနွေ";
			if (haw == 1 && pan[10] > 15)
				hawrat = "တနင်္လာ";
			if (haw == 2 && pan[10] <= 15)
				hawrat = "တနင်္လာ";
			if (haw == 2 && pan[10] > 15)
				hawrat = "တနင်္ဂနွေ";

			// Tarni
			if (pya[10] == 0)
				pya[10] = pya[10] + 12;

			int a = ((int) (((pan[10] * 60) + plat[10]) / 600)) + 1;
			for (int i = 1; i < 13; i++) {
				if (pya[10] == i && a == 1)
					tarni = myanmarLocalizedData.tarni[i];
				if (pya[10] == i && a == 2)
					tarni = myanmarLocalizedData.tarni[i + 5];
				if (pya[10] == i && a == 3)
					tarni = myanmarLocalizedData.tarni[i + 9];
			}

			a = (int) (horoData.getAstroData().getHouseData().get("h")[1] / 3.333333333);
			a = a % 9;
			nawin = myanmarLocalizedData.tarni[a + 1];

			// Dini
			Map<String, Number> dini = horoData.getDini();
			nayi = dini.get("nayi").toString();
			pad = dini.get("pad").toString();
			viz = dini.get("viz").toString();
			kaya = dini.get("kaya").toString();
			wky = dini.get("wky").toString();
			int g = (int) dini.get("g");
			if (g == 1)
				w = "သန်းခေါင် ၂";
			if (g == 2)
				w = "သန်းလွဲ ၃";
			if (g == 3)
				w = "နေထွက် ၄";
			if (g == 4)
				w = "နေတက် ၁";
			if (g == 5)
				w = "မွန်းတည့် ၂";
			if (g == 6)
				w = "နေလွဲ ၃";
			if (g == 7)
				w = "နေဝင် ၄";
			if (g == 8)
				w = "ညည့် ၁";

			double[] p = horoData.getAstroData().getPlaneticalData().get("planet0");
			double[] p1 = horoData.getAstroData().getPlaneticalData().get("planet1");

			for (int i = 3; i <= 7; i++) {
				if (p1[i] > 360)
					p1[i] = p1[i] - 360;
			}

			planet2[0] = (p1[3] < p[3]) ? "၀ကြ" : "";
			planet2[1] = (p1[4] < p[4]) ? "၀ကြ" : "";
			planet2[2] = (p1[5] < p[5]) ? "၀ကြ" : "";
			planet2[3] = (p1[6] < p[6]) ? "၀ကြ" : "";
			planet2[4] = (p1[7] < p[7]) ? "၀ကြ" : "";

			this.r1 = horoData.getR1();
			this.r2 = horoData.getR2();

			this.astroPawar = horoData.getAstroPawar();
			
			if(horoData.getInputData().getLocation().getGeoLocation().getLogitude() >= 0) {
				ewLog = "အရှေ့";
			}
			else {
				ewLog = "အနောက်";
			}
			if(horoData.getInputData().getLocation().getGeoLocation().getLatitude() >= 0) {
				snLat = "မြောက်";
			}
			else {
				snLat = "တောင်";
			}
			
			Map<String,Map<String,String[]>> dasa = horoData.getDasa();
			Map<String,String[]> ww1ld = dasa.get("ww1ld");
			this.setWbeData(0, ww1ld.get("w"));
			this.setWbeData(1, ww1ld.get("w1"));
			this.setWbeData(2, ww1ld.get("ld"));
			
			
			Map<String,String[]> wbwb1ld1 = dasa.get("wbwb1ld1");
			this.setWbeData(3, wbwb1ld1.get("wb"));
			this.setWbeData(4, wbwb1ld1.get("wb1"));
			this.setWbeData(5, wbwb1ld1.get("ld1"));
			
			
			Map<String,String[]> e1e2e3 = dasa.get("e1e2e3");
			this.setWbeData(6, e1e2e3.get("e1"));
			this.setWbeData(7, e1e2e3.get("e2"));
			this.setWbeData(8, e1e2e3.get("e3"));
			
			l = wbwb1ld1.get("l")[0];
			l1 = e1e2e3.get("l1")[0];
			
			Map<String,String[][]> horo = horoData.getHoro();
			String[][] pa = horo.get("pa");
			this.setYathiData(0, pa[9][1] + pa[9][2] + pa[9][3]+ pa[9][4]+ pa[9][5]+ pa[9][6]+ pa[9][7]+ pa[9][8]+ pa[9][9]+ pa[9][10]);
			this.setYathiData(1, pa[8][1] + pa[8][2] + pa[8][3]+ pa[8][4]+ pa[8][5]+ pa[8][6]+ pa[8][7]+ pa[8][8]+ pa[8][9]+ pa[8][10]);
			this.setYathiData(2, pa[7][1] + pa[7][2] + pa[7][3]+ pa[7][4]+ pa[7][5]+ pa[7][6]+ pa[7][7]+ pa[7][8]+ pa[7][9]+ pa[7][10]);
			this.setYathiData(3, pa[6][1] + pa[6][2] + pa[6][3]+ pa[6][4]+ pa[6][5]+ pa[6][6]+ pa[6][7]+ pa[6][8]+ pa[6][9]+ pa[6][10]);
			this.setYathiData(4, pa[5][1] + pa[5][2] + pa[5][3]+ pa[5][4]+ pa[5][5]+ pa[5][6]+ pa[5][7]+ pa[5][8]+ pa[5][9]+ pa[5][10]);
			this.setYathiData(5, pa[4][1] + pa[4][2] + pa[4][3]+ pa[4][4]+ pa[4][5]+ pa[4][6]+ pa[4][7]+ pa[4][8]+ pa[4][9]+ pa[4][10]);
			this.setYathiData(6, pa[3][1] + pa[3][2] + pa[3][3]+ pa[3][4]+ pa[3][5]+ pa[3][6]+ pa[3][7]+ pa[3][8]+ pa[3][9]+ pa[3][10]);
			this.setYathiData(7, pa[2][1] + pa[2][2] + pa[2][3]+ pa[2][4]+ pa[2][5]+ pa[2][6]+ pa[2][7]+ pa[2][8]+ pa[2][9]+ pa[2][10]);
			this.setYathiData(8, pa[1][1] + pa[1][2] + pa[1][3]+ pa[1][4]+ pa[1][5]+ pa[1][6]+ pa[1][7]+ pa[1][8]+ pa[1][9]+ pa[1][10]);
			this.setYathiData(9, pa[0][1] + pa[0][2] + pa[0][3]+ pa[0][4]+ pa[0][5]+ pa[0][6]+ pa[0][7]+ pa[0][8]+ pa[0][9]+ pa[0][10]);
			this.setYathiData(10, pa[11][1] + pa[11][2] + pa[11][3]+ pa[11][4]+ pa[11][5]+ pa[11][6]+ pa[11][7]+ pa[11][8]+ pa[11][9]+ pa[11][10]);
			this.setYathiData(11, pa[10][1] + pa[10][2] + pa[10][3]+ pa[10][4]+ pa[10][5]+ pa[10][6]+ pa[10][7]+ pa[10][8]+ pa[10][9]+ pa[10][10]);
			
			String[][] ho = horo.get("ho");
			this.setBawarData(0, ho[9][1] + ho[9][2] + ho[9][3]+ ho[9][4]+ ho[9][5]+ ho[9][6]+ ho[9][7]+ ho[9][8]+ ho[9][9]+ ho[9][10]);
			this.setBawarData(1, ho[8][1] + ho[8][2] + ho[8][3]+ ho[8][4]+ ho[8][5]+ ho[8][6]+ ho[8][7]+ ho[8][8]+ ho[8][9]+ ho[8][10]);
			this.setBawarData(2, ho[7][1] + ho[7][2] + ho[7][3]+ ho[7][4]+ ho[7][5]+ ho[7][6]+ ho[7][7]+ ho[7][8]+ ho[7][9]+ ho[7][10]);
			this.setBawarData(3, ho[6][1] + ho[6][2] + ho[6][3]+ ho[6][4]+ ho[6][5]+ ho[6][6]+ ho[6][7]+ ho[6][8]+ ho[6][9]+ ho[6][10]);
			this.setBawarData(4, ho[5][1] + ho[5][2] + ho[5][3]+ ho[5][4]+ ho[5][5]+ ho[5][6]+ ho[5][7]+ ho[5][8]+ ho[5][9]+ ho[5][10]);
			this.setBawarData(5, ho[4][1] + ho[4][2] + ho[4][3]+ ho[4][4]+ ho[4][5]+ ho[4][6]+ ho[4][7]+ ho[4][8]+ ho[4][9]+ ho[4][10]);
			this.setBawarData(6, ho[3][1] + ho[3][2] + ho[3][3]+ ho[3][4]+ ho[3][5]+ ho[3][6]+ ho[3][7]+ ho[3][8]+ ho[3][9]+ ho[3][10]);
			this.setBawarData(7, ho[2][1] + ho[2][2] + ho[2][3]+ ho[2][4]+ ho[2][5]+ ho[2][6]+ ho[2][7]+ ho[2][8]+ ho[2][9]+ ho[2][10]);
			this.setBawarData(8, ho[1][1] + ho[1][2] + ho[1][3]+ ho[1][4]+ ho[1][5]+ ho[1][6]+ ho[1][7]+ ho[1][8]+ ho[1][9]+ ho[1][10]);
			this.setBawarData(9, ho[0][1] + ho[0][2] + ho[0][3]+ ho[0][4]+ ho[0][5]+ ho[0][6]+ ho[0][7]+ ho[0][8]+ ho[0][9]+ ho[0][10]);
			this.setBawarData(10, ho[11][1] + ho[11][2] + ho[11][3]+ ho[11][4]+ ho[11][5]+ ho[11][6]+ ho[11][7]+ ho[11][8]+ ho[11][9]+ ho[11][10]);
			this.setBawarData(11, ho[10][1] + ho[10][2] + ho[10][3]+ ho[10][4]+ ho[10][5]+ ho[10][6]+ ho[10][7]+ ho[10][8]+ ho[10][9]+ ho[10][10]);
			
			String[][] na = horo.get("na");
			this.setNawinData(0, na[9][1] + na[9][2] + na[9][3]+ na[9][4]+ na[9][5]+ na[9][6]+ na[9][7]+ na[9][8]+ na[9][9]+ na[9][10]);
			this.setNawinData(1, na[8][1] + na[8][2] + na[8][3]+ na[8][4]+ na[8][5]+ na[8][6]+ na[8][7]+ na[8][8]+ na[8][9]+ na[8][10]);
			this.setNawinData(2, na[7][1] + na[7][2] + na[7][3]+ na[7][4]+ na[7][5]+ na[7][6]+ na[7][7]+ na[7][8]+ na[7][9]+ na[7][10]);
			this.setNawinData(3, na[6][1] + na[6][2] + na[6][3]+ na[6][4]+ na[6][5]+ na[6][6]+ na[6][7]+ na[6][8]+ na[6][9]+ na[6][10]);
			this.setNawinData(4, na[5][1] + na[5][2] + na[5][3]+ na[5][4]+ na[5][5]+ na[5][6]+ na[5][7]+ na[5][8]+ na[5][9]+ na[5][10]);
			this.setNawinData(5, na[4][1] + na[4][2] + na[4][3]+ na[4][4]+ na[4][5]+ na[4][6]+ na[4][7]+ na[4][8]+ na[4][9]+ na[4][10]);
			this.setNawinData(6, na[3][1] + na[3][2] + na[3][3]+ na[3][4]+ na[3][5]+ na[3][6]+ na[3][7]+ na[3][8]+ na[3][9]+ na[3][10]);
			this.setNawinData(7, na[2][1] + na[2][2] + na[2][3]+ na[2][4]+ na[2][5]+ na[2][6]+ na[2][7]+ na[2][8]+ na[2][9]+ na[2][10]);
			this.setNawinData(8, na[1][1] + na[1][2] + na[1][3]+ na[1][4]+ na[1][5]+ na[1][6]+ na[1][7]+ na[1][8]+ na[1][9]+ na[1][10]);
			this.setNawinData(9, na[0][1] + na[0][2] + na[0][3]+ na[0][4]+ na[0][5]+ na[0][6]+ na[0][7]+ na[0][8]+ na[0][9]+ na[0][10]);
			this.setNawinData(10, na[11][1] + na[11][2] + na[11][3]+ na[11][4]+ na[11][5]+ na[11][6]+ na[11][7]+ na[11][8]+ na[11][9]+ na[11][10]);
			this.setNawinData(11, na[10][1] + na[10][2] + na[10][3]+ na[10][4]+ na[10][5]+ na[10][6]+ na[10][7]+ na[10][8]+ na[10][9]+ na[10][10]);
			
		}
		
		private void createHoroscope(Graphics2D g2d) {
			int cellWidth = 30;
	        int cellHeight = 20;
	        
	        // Font for the text
	        Font font = new Font("Myanmar", Font.PLAIN, 10);
	        g2d.setFont(font);
	        
	        String[] gyo = {"ဂြိုလ်","","ရာသီ","အံသာ","လိတ္တာ","သွားပုံ","ဝိက္ခေပ","", "ကြန္တီ",""};
	        for (int i = 0; i < gyo.length; i++) {
	            int x = 30;
	            int y = i * cellHeight + 10;
	            
	            // Determine the height of the cell
	            int height = cellHeight;
	            String d = gyo[i];
	            
	            if (!gyo[i].isEmpty() && i < gyo.length - 1 && gyo[i + 1].isEmpty() && i == 0) {
	                // Merge this cell with the next one
	                height = 2 * cellHeight;
	                // Skip the next cell
	                i++;
	            }

	            // Draw the cell border
	            g2d.drawRect(x, y, cellWidth, height);
	            
	            // Draw the character
	            if (!d.isEmpty()) {
	            	FontMetrics metrics = g2d.getFontMetrics(font); 
	            	int textX = x + (cellWidth - metrics.stringWidth(d)) / 2; 
	            	int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent(); 
	            	g2d.drawString(d, textX, textY);
	            }
	        }
	        
	        String[] kane = {"၁", "၂", "၃", "၄", "၅", "၆", "၀", "၈", "၉", ""};
	        for(int i =0;i<kane.length;i++) {
	        	int x = i * cellWidth + 60;
	        	int y = 10;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(kane[i])) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(kane[i], textX, textY);
	        }
	        
	        String[] gyoyet = {"နွေ", "လာ", "ဂါ", "ဟူး", "တေး", "ကြာ", "နေ", "ဟု","ကိတ်", "လ"};
	        for(int i =0;i<gyoyet.length;i++) {
	        	int x = i * cellWidth + 60;
	        	int y = 10 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(gyoyet[i])) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(gyoyet[i], textX, textY);
	        }
	        
	        for(int i = 1;i< pya.length;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 30 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)pya[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)pya[i])), textX, textY);
	        }
	        
	        for(int i = 1;i< pan.length;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 50 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)pan[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)pan[i])), textX, textY);
	        }
	        
	        for(int i = 1;i< plat.length;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 70 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)plat[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)plat[i])), textX, textY);
	        }
	        
	        for(int i = 3;i<=7;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 90 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(planet2[i-3])) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(planet2[i-3], textX, textY);
	        }
	        for(int i=2;i<=7;i++) {
	        	int x = (i-2) * cellWidth + 90;
	    		int y = 110 + cellHeight;
	    		
	    		g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)latan[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)latan[i])), textX, textY);
	    		
	        }
	        
	        for(int i = 2;i<=7;i++) {
	        	
	        	int	x = (i-2) * cellWidth + 90;
	        	int	y = 130 + cellHeight;
	        	
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)latlat[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)latlat[i])), textX, textY);
	        }
	        
	        for(int i = 1;i<8;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 150 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)dlan[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)dlan[i])), textX, textY);
	        }
	        
	        for(int i = 1;i<8;i++) {
	        	int x = (i-1) * cellWidth + 60;
	        	int y = 170 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)dllat[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString((int)dllat[i])), textX, textY);
	        }
	        
	        //Right Panel Data
	        String[] data1 = {"တ", "က", "သ", "ဗ", "ပု", "အာ", "ပ", "မ", "သု", "ကမ္မ", "လာ", "ဗျာ"};
	        for(int i = 0;i<data1.length;i++) {
	        	int x = i * cellWidth + 370;
	        	int y = 10;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(data1[i])) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(data1[i], textX, textY);
	        }
	        for(int i = 1;i<hya.length;i++) {
	        	int x = (i-1) * cellWidth + 370;
	        	int y = 10 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)hya[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)hya[i]))), textX, textY);
	        }
	        for(int i = 1;i<han.length;i++) {
	        	int x = (i-1) * cellWidth + 370;
	        	int y = 30 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)han[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)han[i]))), textX, textY);
	        }
	        for(int i = 1;i<hlat.length;i++) {
	        	int x = (i-1) * cellWidth + 370;
	        	int y = 50 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)hlat[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)hlat[i]))), textX, textY);
	        }
	        for(int i = 1;i<cya.length;i++) {
	        	int x = (i-1) * cellWidth + 385;
	        	int y = 70 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)cya[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)cya[i]))), textX, textY);
	        }
	        for(int i = 1;i<can.length;i++) {
	        	int x = (i-1) * cellWidth + 385;
	        	int y = 90 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)can[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)can[i]))), textX, textY);
	        }
	        for(int i = 1;i<clat.length;i++) {
	        	int x = (i-1) * cellWidth + 385;
	        	int y = 110 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)clat[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)clat[i]))), textX, textY);
	        }
	        String[] data2 = {"တိထီ", "မြင်", "စင်", "ပဉ္စန်း", "ဦး", "အဓိ", "ယော", "ရာဇ", "ပုတိ", "ဘင်္ဂ", "မရဏ", "သော","ယသ","သီရိ"};
	        for(int i = 0;i<data2.length;i++) {
	        	int x = i * cellWidth + 325;
	        	int y = 130 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(data2[i])) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(data2[i], textX, textY);
	        }
	        for(int i = 0;i<r1.length;i++) {
	        	int x = i * cellWidth + 325;
	        	int y = 150 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)r1[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)r1[i]))), textX, textY);
	        }
	        for(int i = 0;i<r2.length;i++) {
	        	int x = i * cellWidth + 325;
	        	int y = 170 + cellHeight;
	        	
	        	g2d.drawRect(x, y, cellWidth, cellHeight);
	        	
	        	FontMetrics metrics = g2d.getFontMetrics(font); 
	        	int textX = x + (cellWidth - metrics.stringWidth(Integer.toString(((int)r2[i])))) / 2; 
	        	int textY = y + ((cellHeight - metrics.getHeight()) / 2) + metrics.getAscent(); 
	        	g2d.drawString(myanmarLocalizedData.convertMMNumber(Integer.toString(((int)r2[i]))), textX, textY);
	        }
	        
	        g2d.drawRoundRect(10, 10, 755, 200, 30, 30);
	        g2d.drawRoundRect(10, 215, 755, 200, 30, 30);
	        g2d.drawRoundRect(10, 415, 755, 200, 30, 30);
	        drawCircleWithLines(g2d, 130, 515, 90, "ရာသီ",yathiData);
	        drawCircleWithLines(g2d, 375, 515, 90, "ဘာဝ",bawarData);
	        drawCircleWithLines(g2d, 620, 515, 90, "နဝင်း",nawinData);
	        g2d.drawRoundRect(10, 620, 755, 200, 30, 30);
	        drawTablewithDate(g2d, 20, 620);
	        
	        StringBuilder sb = new StringBuilder();
	        sb.append("  ဇေယျတု သက္ကရာဇ်    ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getMyanmarDate().getMyanmarYear())));
	        sb.append("    ကလိယုဂ်     ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getMyanmarDate().getKaliYote())));
	        sb.append("     နှစ်ကြွင်း   ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getMyanmarDate().getMyanmarYear() - 1100)));
	        sb.append("    ရဝိဖုဋ    ");
	        sb.append(yarthi);
	        sb.append("  ရာသီ ဝေါဟာရမာသ");
	        drawTextData(g2d, 20, 214,sb.toString());
	        
	        sb.setLength(0);
	        sb.append(myanmarLocalizedData.getMMMonths((int)horoData.getMyanmarDate().getMyanmarMonth(), horoData.getMyanmarDate().isWatat()));
	        sb.append(myanmarLocalizedData.moonPhases[(int)horoData.getMyanmarDate().getSanorSoak()]);
	        sb.append("   ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getMyanmarDate().getMyanmarMonthDay())));
	        sb.append("  ရက်       ");
	        sb.append(nayt);
	        sb.append(" နေ့၊စက်        ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getHours())));
	        sb.append("     နာရီ       ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getMinutes())));
	        sb.append("     မိနစ်။ မြန်မာ      ");
	        sb.append(w);
	        sb.append("    ချက်တီးကျော်");
	        drawTextData(g2d, 20, 239,sb.toString());
	        
	        sb.setLength(0);
	        sb.append(myanmarLocalizedData.convertMMNumber(nayi));
	        sb.append("      နာရီ        ");
	        sb.append(myanmarLocalizedData.convertMMNumber(pad));
	        sb.append("   ပါဒ်       ");
	        sb.append(myanmarLocalizedData.convertMMNumber(viz));
	        sb.append("    ဗီဇနာ      ");
	        sb.append(myanmarLocalizedData.convertMMNumber(kaya));
	        sb.append("   အခရာ      ");
	        sb.append(myanmarLocalizedData.convertMMNumber(wky));
	        sb.append("   ဝိခရာ       ");
	        sb.append(myanmarLocalizedData.convertMMNumber(latt));
	        sb.append(" လဂ်          ");
	        sb.append(myanmarLocalizedData.convertMMNumber(sann));
	        sb.append(" စန်း           ");
	        sb.append(myanmarLocalizedData.convertMMNumber(hawrat));
	        drawTextData(g2d, 20, 264,sb.toString());
	        
	        sb.setLength(0);
	        sb.append("ဟောရတ်          ");
	        sb.append(tarni);
	        sb.append("   တြင်း     ");
	        sb.append(nawin);
	        sb.append("   နဝင်း၊ လဂ်စီးနက္ခတ်       ");
	        sb.append(myanmarLocalizedData.convertMMNumber(lone));
	        sb.append("    လုံး    ");
	        sb.append(myanmarLocalizedData.convertMMNumber(pat));
	        sb.append("  ပါဒ်    ");
	        sb.append(myanmarLocalizedData.convertMMNumber(gati));
	        sb.append("   ဃဋီ     ");
	        sb.append(myanmarLocalizedData.convertMMNumber(wikati));
	        sb.append("   ဝိဃဋီ");
	        drawTextData(g2d, 20, 289,sb.toString());
	        
	        sb.setLength(0);
	        sb.append(" ထက်လဂ်စီးလျက် ");
	        sb.append(astroPawar);
	        sb.append("  ");
	        drawTextData(g2d, 20, 316,sb.toString());
	        
	        sb.setLength(0);
	        sb.append(snLat);
	        sb.append("  ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getLocation().getGeoLocation().getLatitude())));
	        sb.append("  အံသာ   ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getLocation().getGeoLocation().getLatMinutes())));
	        sb.append("  လိတ္တာ  ");
	        sb.append(ewLog);
	        sb.append("  ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getLocation().getGeoLocation().getLogitude())));
	        sb.append("  အံသာ   ");
	        sb.append(myanmarLocalizedData.convertMMNumber(Integer.toString((int)horoData.getInputData().getLocation().getGeoLocation().getLogMinutes())));
	        sb.append("  လိတ္တာ ရှိသော   ");
	        sb.append(horoData.getInputData().getLocation().getCity());
	        sb.append("  မြို့တွင်   ");
	        drawTextData(g2d, 20, 343,sb.toString());
	        
	        sb.setLength(0);
	        sb.append("သတို့   ဘွားသန့်စင်သည်၊ နာမံ  ");
	        sb.append(horoData.getInputData().getName());
	        sb.append(" ၏ဇာတာ၊ အသက် ၁၂၀ ရှည်စေသောဝ်။");
	        drawTextData(g2d, 20, 370,sb.toString());
		}
		
		public void printImage() {
			PrinterJob printerJob = PrinterJob.getPrinterJob();
			printerJob.setPrintable(this);
			
			if(printerJob.printDialog()) {
				try {
					printerJob.print();
				} catch(PrinterException pex) {
					pex.printStackTrace();
				}
			}
		}

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			if (pageIndex > 0) {
				return NO_SUCH_PAGE;
			}
			Graphics2D g2d = (Graphics2D)graphics;
			double scaleX = pageFormat.getImageableWidth() / bufferedImage.getWidth();
	        double scaleY = pageFormat.getImageableHeight() / bufferedImage.getHeight();
	        double scale = Math.min(scaleX, scaleY);
	        
	        // Calculate the translation for centering
	        double offsetX = (pageFormat.getImageableWidth() - bufferedImage.getWidth() * scale) / 2;
	        //double offsetY = (pageFormat.getImageableHeight() - bufferedImage.getHeight() * scale) / 2;
	        
	        g2d.translate(pageFormat.getImageableX() + offsetX, pageFormat.getImageableY());
	        g2d.scale(scale, scale);
	        
			createHoroscope(g2d);
			
			return PAGE_EXISTS;
		}
	}
