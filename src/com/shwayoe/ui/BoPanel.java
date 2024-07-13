package com.shwayoe.ui;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.shwayoe.data.myanmarLocalizedData;

public class BoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public BoPanel(int kaliParam) {
		
        String[] columnNames = {
            "ကလိယုဂ်","သာဝန", "ကြမတ်", "တိထီ", "စန္ဒြမာသ",
            "ရက်လွန်", "နေ့", "အဓိမတ်", "ရက်ငင်"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        int M = kaliParam;
        double a = 1577917828d; 
        double c = 4320000d;
        
        double x = 1593336d; 
        double y = 837748d;
        double k = 25082252d;
        		
        for (int i = 0; i <= 20; i++) {
        	int kali = M + i;
        	double sawana = a * kali / c;
        	double kyamat = (a * kali) - (Math.floor(sawana) * c);
        	double haragone = Math.floor(sawana);
        	if(kyamat > 0 ) haragone = haragone +1;
        	double dy = (haragone + 5) / 7;
        	dy = (haragone + 5) - Math.floor(dy) * 7;
        	double Q = haragone * k / a;
        	double dnt = Math.floor(Q);
        	double titee = haragone + dnt;
        	double yetlon = titee / 30;
        	double sandramatha = Math.floor(yetlon);
        	yetlon = titee - sandramatha * 30;
        	double adimat = Math.floor(kali * x / c);
        	double yetngin = Math.floor(kali * y / c);
        	
            tableModel.addRow(new Object[]{
                myanmarLocalizedData.convertMMNumber(Integer.toString(kali)),myanmarLocalizedData.convertMMNumber(Integer.toString((int)haragone)), myanmarLocalizedData.convertMMNumber(Integer.toString((int)kyamat)), myanmarLocalizedData.convertMMNumber(Integer.toString((int)titee)), myanmarLocalizedData.convertMMNumber(Integer.toString((int)sandramatha)),
                myanmarLocalizedData.convertMMNumber(Integer.toString((int)yetlon)), myanmarLocalizedData.convertMMNumber(Integer.toString((int)dy)) + " " +myanmarLocalizedData.myanmarAtarNayt[(int)dy], myanmarLocalizedData.convertMMNumber(Integer.toString((int)adimat)),myanmarLocalizedData.convertMMNumber(Integer.toString((int)yetngin))
            });
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        for(int i = 0;i<table.getColumnCount();i++) {
        	table.getColumnModel().getColumn(i).setPreferredWidth(150);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
              
        JScrollPane scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
	}
}
