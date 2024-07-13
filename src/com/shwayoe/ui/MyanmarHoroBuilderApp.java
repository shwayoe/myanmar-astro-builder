package com.shwayoe.ui;
import javax.swing.*;
import com.shwayoe.data.LocationList;
import com.shwayoe.data.MyanmarTownList;
import com.shwayoe.data.myanmarLocalizedData;
import com.shwayoe.logic.AstrologicalData;
import com.shwayoe.logic.HoroscopeData;
import com.shwayoe.logic.JTKDays;
import com.shwayoe.logic.MyanmarDate;
import com.shwayoe.data.InputData;
import com.shwayoe.data.Location;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;


public class MyanmarHoroBuilderApp {
	
	static MyanmarCalendarPanel mmCal = new MyanmarCalendarPanel(YearMonth.now());
	static AstroPanel ap = new AstroPanel();
	static JTextField hhField = new JTextField(2);
    static JTextField mmField = new JTextField(2);
    static JTextField ssField = new JTextField(2);
    static JLabel countrydata = new JLabel("Myanmar");
	static JTextField latField = new JTextField(2);
    static JTextField latMMField = new JTextField(2);
    static JTextField logField = new JTextField(2);
    static JTextField logMMField = new JTextField(2);
    static JTextField tzField = new JTextField(2);
    static JTextField nameField = new JTextField();
	
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JComboBox<String> additionalComboBox = new JComboBox<>();
		        additionalComboBox.addItem("");
		        for(Location loc : LocationList.getLocations()){
		        	additionalComboBox.addItem(loc.getCity());
		        }
		        
		        
		        JFrame frame = new JFrame("Myanmar Horoscope Builder");
		        
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(1200, 800);
		        
		        JPanel leftPanel = new JPanel();
		        leftPanel.setLayout(new GridBagLayout());
		        GridBagConstraints gbc = new GridBagConstraints();
		        gbc.insets = new Insets(5, 5, 5, 5);
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        JLabel nameLabel = new JLabel("အမည်:");
		        leftPanel.add(nameLabel, gbc);
		        
		        gbc.gridx = 1;
		        leftPanel.add(nameField, gbc);

		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        JLabel genderLabel = new JLabel("လိင် (ကျား / မ):");
		        leftPanel.add(genderLabel, gbc);
		        
		        gbc.gridx = 1;
		        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"ကျား", "မ"});
		        leftPanel.add(genderComboBox, gbc);
		        
		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        JLabel townLabel = new JLabel("မွေးမြို့ (မြန်မာပြည်):");
		        leftPanel.add(townLabel, gbc);
		        
		        gbc.gridx = 1;
		        JComboBox<String> townComboBox = new JComboBox<>();
		        townComboBox.addItem("");
		        for(Location loc : MyanmarTownList.getLocations()) {
		        	townComboBox.addItem(loc.getCity());
		        }
		        
		        townComboBox.setSelectedIndex(252);
		        Location loca = MyanmarTownList.getLocationByTown(townComboBox.getSelectedItem().toString());
		    	countrydata.setText(loca.getCountry());
		    	latField.setText(Integer.toString(loca.getGeoLocation().getLatitude()));
		    	latMMField.setText(Integer.toString(loca.getGeoLocation().getLatMinutes()));
		    	logField.setText(Integer.toString(loca.getGeoLocation().getLogitude()));
		    	logMMField.setText(Integer.toString(loca.getGeoLocation().getLogMinutes()));
		    	tzField.setText(Double.toString(loca.getTimeZone()));
		    	
		        townComboBox.addActionListener((e)->{
		        	if(townComboBox.getSelectedIndex() != 0) {
			        	Location loc = MyanmarTownList.getLocationByTown(townComboBox.getSelectedItem().toString());
			        	countrydata.setText(loc.getCountry());
			        	latField.setText(Integer.toString(loc.getGeoLocation().getLatitude()));
			        	latMMField.setText(Integer.toString(loc.getGeoLocation().getLatMinutes()));
			        	logField.setText(Integer.toString(loc.getGeoLocation().getLogitude()));
			        	logMMField.setText(Integer.toString(loc.getGeoLocation().getLogMinutes()));
			        	tzField.setText(Double.toString(loc.getTimeZone()));
			        	additionalComboBox.setSelectedIndex(0);
		        	}
		        });
		        
		        leftPanel.add(townComboBox, gbc);

		        gbc.gridx = 0;
		        gbc.gridy = 3;
		        JLabel othertownLabel = new JLabel("မွေးမြို့ (နိုင်ငံခြား):");
		        leftPanel.add(othertownLabel, gbc);
		        
		        
		        gbc.gridx = 1;
		        additionalComboBox.addActionListener((e)->{
		        	if(additionalComboBox.getSelectedIndex() != 0) {
			        	Location loc = LocationList.getLocationByCity(additionalComboBox.getSelectedItem().toString());
			        	countrydata.setText(loc.getCountry());
			        	latField.setText(Integer.toString(loc.getGeoLocation().getLatitude()));
			        	latMMField.setText(Integer.toString(loc.getGeoLocation().getLatMinutes()));
			        	logField.setText(Integer.toString(loc.getGeoLocation().getLogitude()));
			        	logMMField.setText(Integer.toString(loc.getGeoLocation().getLogMinutes()));
			        	tzField.setText(Double.toString(loc.getTimeZone()));
			        	townComboBox.setSelectedIndex(0);
		        	}
		        });
		        leftPanel.add(additionalComboBox, gbc);
		        
		        gbc.gridx = 0;
		        gbc.gridy = 4;
		        JLabel countryLabel = new JLabel("တိုင်းပြည်:");
		        leftPanel.add(countryLabel, gbc);
		        
		        gbc.gridx = 1;
		        leftPanel.add(countrydata, gbc);

		        gbc.gridx = 0;
		        gbc.gridy = 5;
		        JLabel latLongLabel = new JLabel("တည်နေရာ:");
		        leftPanel.add(latLongLabel, gbc);
		        
		        gbc.gridx = 1;
		        JPanel locationPanel = new JPanel();
		        locationPanel.setLayout(new GridBagLayout());
		        
		        latField.setColumns(3);
		        latMMField.setColumns(3);
		        logField.setColumns(3);
		        logMMField.setColumns(3);
		        tzField.setColumns(3);
		        
		        latField.setEnabled(false);
		        latMMField.setEnabled(false);
		        logField.setEnabled(false);
		        logMMField.setEnabled(false);
		        tzField.setEnabled(false);
		        
		        GridBagConstraints gbcLocation = new GridBagConstraints();
		        gbcLocation.insets = new Insets(2, 2, 2, 2);
		        
		        gbcLocation.gridx = 0;
		        locationPanel.add(new JLabel("လတ္တီကျု ဒီဂရီ: "), gbcLocation);
		        
		        gbcLocation.gridx = 1;
		        locationPanel.add(latField, gbcLocation);
		        
		        gbcLocation.gridx = 2;
		        locationPanel.add(new JLabel("   မိနစ်: "), gbcLocation);
		        
		        gbcLocation.gridx = 3;
		        locationPanel.add(latMMField, gbcLocation);
		        
		        gbcLocation.gridx = 0;
		        gbcLocation.gridy = 1;
		        locationPanel.add(new JLabel("လောင်ဂျီကျု ဒီဂရီ: "), gbcLocation);
		        
		        gbcLocation.gridx = 1;
		        locationPanel.add(logField, gbcLocation);
		        
		        gbcLocation.gridx = 2;
		        locationPanel.add(new JLabel("   မိနစ်: "), gbcLocation);
		        
		        gbcLocation.gridx = 3;
		        locationPanel.add(logMMField, gbcLocation);
		        
		        gbcLocation.gridx = 0;
		        gbcLocation.gridy = 2;
		        locationPanel.add(new JLabel("စံ‌တော်ချိန်နှင့်ဂရင်းနှစ်ကွာနာရီ: "), gbcLocation);
		        
		        gbcLocation.gridx = 1;
		        locationPanel.add(tzField, gbcLocation);
		        
		        leftPanel.add(locationPanel, gbc);
		        
		        gbc.gridx = 0;
		        gbc.gridy = 6;
		        LocalTime lt = LocalTime.now();
		        
		        JLabel timeLabel = new JLabel("မွေးချိန်:");
		        leftPanel.add(timeLabel, gbc);
		        
		        gbc.gridx = 1;
		        JPanel timePanel = new JPanel();
		        timePanel.setLayout(new GridBagLayout());
		        
		        hhField.setColumns(3);
		        mmField.setColumns(3);
		        ssField.setColumns(3);
		        
		        hhField.setText(Integer.toString(lt.getHour()));
		        mmField.setText(Integer.toString(lt.getMinute()));
		        ssField.setText(Integer.toString(lt.getSecond()));
		        
		        GridBagConstraints gbcTime = new GridBagConstraints();
		        gbcTime.insets = new Insets(0, 2, 0, 2);
		        
		        gbcTime.gridx = 0;
		        timePanel.add(hhField, gbcTime);
		        
		        gbcTime.gridx = 1;
		        timePanel.add(new JLabel(" နာရီ "), gbcTime);
		        
		        gbcTime.gridx = 2;
		        timePanel.add(mmField, gbcTime);
		        
		        gbcTime.gridx = 3;
		        timePanel.add(new JLabel(" မိနစ် "), gbcTime);
		        
		        gbcTime.gridx = 4;
		        timePanel.add(ssField, gbcTime);
		        
		        gbcTime.gridx = 5;
		        timePanel.add(new JLabel(" စက္ကန့်"), gbcTime);
		        
		        leftPanel.add(timePanel, gbc);

		        gbc.gridx = 0;
		        gbc.gridy = 7;
		        JLabel dateLabel = new JLabel("မွေးသက္ကရာဇ်:");
		        leftPanel.add(dateLabel, gbc);
		        
		        gbc.gridx = 1;
		        gbc.gridwidth = 1;
		        gbc.gridheight = 1;
		        gbc.weightx = 1;
		        gbc.weighty = 1;
		        gbc.fill = GridBagConstraints.BOTH;
		        gbc.anchor = GridBagConstraints.CENTER;
		        mmCal.setDayClickedListener(() -> {
		        	updateTable();
		        });
		        
		        leftPanel.add(mmCal, gbc);

		        JPanel rightPanel = new JPanel();
		        rightPanel.setLayout(new GridBagLayout());
		        GridBagConstraints rgbc = new GridBagConstraints();
		        rgbc.insets = new Insets(5, 5, 5, 5);
		        rgbc.fill = GridBagConstraints.HORIZONTAL;
		        
		        rgbc.gridy=0;
		        rgbc.gridwidth=1;
		        rgbc.gridheight=1;
		        rgbc.weightx = 1;
		        rgbc.weighty = 1;
		        rgbc.fill = GridBagConstraints.BOTH;
		        rgbc.anchor = GridBagConstraints.CENTER;
		        
		        rightPanel.add(ap,rgbc);
		        
		        rgbc.gridy = 1;
		        JPanel lblPanel = new JPanel();
		        JLabel lblcurrentDate = new JLabel();
		        lblcurrentDate.setText("ယနေ့ သက္ကရာဇ် - " + LocalDate.now().toString());
		        lblcurrentDate.setFont(new Font("SansSerif", Font.BOLD, 16));
		        lblPanel.add(lblcurrentDate);
		        rightPanel.add(lblPanel,rgbc);
		        
		        rgbc.gridy =2;
		        JPanel btnPanel = new JPanel();
		        btnPanel.setLayout(new FlowLayout());
		        JButton btnHoro = new JButton("View Horo");
		        btnHoro.addActionListener((e)->{
		        	JFrame frame2 = new JFrame("Your Horoscope");
		        	
		        	Rectangle oldBounds = frame.getBounds();
		        	int newX = oldBounds.x + (oldBounds.width - frame2.getWidth()) /6;
		            int newY = oldBounds.y + 35;
		            frame2.setLocation(newX, newY);
		            
		        	LocalDate selectedDate = mmCal.getSelectedDate();
		        	int dy = selectedDate.getDayOfMonth(); // Day
					int mn = selectedDate.getMonthValue(); // Month
					int yr = selectedDate.getYear(); // Year
					int hh = Integer.parseInt(hhField.getText()); // Hour
					int mm = Integer.parseInt(mmField.getText()); // Minute
					int ss = Integer.parseInt(ssField.getText()); // Second
					
					Location loc = null;
					if(townComboBox.getSelectedIndex() != 0) {
						loc = MyanmarTownList.getLocationByTown(townComboBox.getSelectedItem().toString());
					}
					
					if(additionalComboBox.getSelectedIndex() != 0) {
						loc = LocationList.getLocationByCity(additionalComboBox.getSelectedItem().toString());
					}

					InputData id = new InputData(dy, mn, yr, hh, mm, ss);
					id.setLocation(loc);
					id.setName(nameField.getText());

					HoroscopeData horoData = new HoroscopeData(id);
					HoroscopePanel panel = new HoroscopePanel(horoData);
					
					JPanel homePanel = new JPanel();
					homePanel.setBackground(Color.WHITE);
					homePanel.setLayout(new GridBagLayout());
					GridBagConstraints homegbc = new GridBagConstraints();
					homegbc = new GridBagConstraints();
					homegbc.insets = new Insets(0, 2, 0, 2);
					
					JPanel buttonPanel = new JPanel();
					buttonPanel.setBackground(Color.WHITE);
					JButton btnSave = new JButton("Save");
					btnSave.addActionListener((btne)->{
						panel.saveImage();
					});
					buttonPanel.add(btnSave);
					
					JButton btnPrint = new JButton("Print");
					btnPrint.addActionListener((pe)->{
						panel.printImage();
					});
					buttonPanel.add(btnPrint);
					homePanel.add(buttonPanel,homegbc);
		 
			        homegbc.gridy = 1;
					homegbc.gridwidth = 1;
			        homegbc.gridheight = 1;
			        homegbc.weightx = 1;
			        homegbc.weighty = 1;
			        homegbc.fill = GridBagConstraints.BOTH;
			        homegbc.anchor = GridBagConstraints.CENTER;
					homePanel.add(panel,homegbc);
					
		            JScrollPane scroll = new JScrollPane(homePanel);
		            scroll.setPreferredSize(new Dimension(800, 700)); // Viewport size
		            frame2.add(scroll);
		            frame2.pack();
		            frame2.setVisible(true);
		        });
		        
		        JButton btnBo = new JButton("View Bo");
		        btnBo.addActionListener((ebo)->{
		        	JFrame frame3 = new JFrame("SURIA SIDDHANTA Bo");
		            frame3.setSize(800, 600);
		            frame3.setLocationRelativeTo(null);
		            LocalDate ld = mmCal.getSelectedDate();
		            MyanmarDate md = new MyanmarDate(new JTKDays(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear(), 0, 0, 0));
		            int kali =(int) md.getKaliYote();
		            
		            BoPanel bp = new BoPanel(kali);
		            frame3.add(bp, BorderLayout.CENTER);

		            frame3.setVisible(true);

		        });

		        btnPanel.add(btnHoro);
		        btnPanel.add(btnBo);
		        rightPanel.add(btnPanel,rgbc);
		        updateTable();
		        
		        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		        splitPane.setDividerLocation(600);
		        frame.add(splitPane);

		        frame.setVisible(true);
			}
    	});
    }
    
    private static void updateTable() {
    	LocalDate ld = mmCal.getSelectedDate();
    	
    	MyanmarDate md = new MyanmarDate(new JTKDays(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear(), 0, 0, 0));
    	
    	int mmYear = (int)md.getMyanmarYear();
        int mmMonth = (int)md.getMyanmarMonth();
        int mmKalyu = (int)md.getKaliYote();
        String war = "";
        boolean wartat = md.isWatat();
        
        if(wartat) {
        	if(md.isBwar()) {
        		war = "(ဝါကြီး)";
        	}
        	else {
        		war = "(ဝါငယ်)";
        	}
        }
        else {
        	war = "";
        }
        
        String thetKayit = myanmarLocalizedData.getMMThetKayit(mmKalyu);
        String mmtitle = thetKayit + myanmarLocalizedData.convertMMNumber(Integer.toString(mmYear)) + " ခု " + myanmarLocalizedData.getMMMonths(mmMonth, wartat) + " " + myanmarLocalizedData.moonPhases[(int)md.getSanorSoak()] + " " + myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getMyanmarDay())) + " ရက်" + " " + war;
    	
    	AstrologicalData astrodata = new AstrologicalData(ld.getDayOfMonth(), ld.getMonthValue(), ld.getYear(),Integer.parseInt(hhField.getText()), Integer.parseInt(mmField.getText()), Integer.parseInt(ssField.getText()),Integer.parseInt(logField.getText()), Integer.parseInt(logMMField.getText()), Integer.parseInt(latField.getText()),Integer.parseInt(latMMField.getText()), Double.parseDouble(tzField.getText()));
    	ap.setData(0, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getKaliYote())));
    	ap.setData(1, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getAtarDayNumber())) + " " + myanmarLocalizedData.myanmarAtarNayt[(int)md.getAtarDayNumber()]);
    	ap.setData(2, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getTitheeYetlon())));
    	ap.setData(3, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getWallharYetlon())));
    	ap.setData(4, myanmarLocalizedData.convertMMNumber((astrodata.getThitee().get("thiteeHour").toString())) + " : " + myanmarLocalizedData.convertMMNumber((astrodata.getThitee().get("thiteeMinutes").toString())));
    	ap.setData(5, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getThoteDateDan())));
    	ap.setData(6, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getJulianDay())));
    	ap.setData(7, myanmarLocalizedData.convertMMNumber(Integer.toString((int)md.getTharwanaDay())));
    	ap.setData(8, myanmarLocalizedData.convertMMNumber((astrodata.getLST().get("lstHours").toString())) + " နာရီ " + myanmarLocalizedData.convertMMNumber((astrodata.getLST().get("lstMinutes").toString())) + " မိနစ်");
    	ap.setData(9, myanmarLocalizedData.convertMMNumber(Integer.toString((int)astrodata.getPres()) + " အံသာ " + myanmarLocalizedData.convertMMNumber(Integer.toString((int)astrodata.getPresLatter())) + " လိတ္တာ"));
    	
    	ap.setTitle(mmtitle);
    	ap.updateTable();
    }
}
