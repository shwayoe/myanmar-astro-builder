package com.shwayoe.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import com.shwayoe.data.myanmarLocalizedData;
import com.shwayoe.logic.JTKDays;
import com.shwayoe.logic.MyanmarDate;

public class MyanmarCalendarPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private YearMonth currentMonth;
    private LocalDate selectedDate;
    private Map<LocalDate, Rectangle> dayBounds = new HashMap<>();
    private CalendarActionListener calListener;
    
    MyanmarDate md = new MyanmarDate(new JTKDays(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(), LocalDate.now().getYear(), LocalTime.now().getHour(), LocalTime.now().getMinute(), LocalTime.now().getSecond()));
    
    public MyanmarCalendarPanel(YearMonth initialMonth) {
    	this.currentMonth = initialMonth;
    	this.selectedDate = LocalDate.now();
    	
    	String[] months = new String[]{
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };
    	
    	addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                for (Map.Entry<LocalDate, Rectangle> entry : dayBounds.entrySet()) {
                    if (entry.getValue().contains(clickPoint)) {
                        selectedDate = entry.getKey();
                        currentMonth = YearMonth.of(selectedDate.getYear(), selectedDate.getMonth());
                        calListener.dayClicked();
                        repaint();
                        break;
                    }
                }
            }
        });
    	
    	JComboBox<String> monthComboBox = new JComboBox<>(months);
        monthComboBox.setSelectedIndex(LocalDate.now().getMonthValue() - 1);
        
        int currentYear = LocalDate.now().getYear();
        Integer[] years = IntStream.rangeClosed(currentYear - 80, currentYear + 5).boxed().toArray(Integer[]::new);
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);
        yearComboBox.setSelectedItem(currentYear);
        
        monthComboBox.addActionListener(e -> updateCalendar(monthComboBox, yearComboBox));
        yearComboBox.addActionListener(e -> updateCalendar(monthComboBox, yearComboBox));
        
        JButton prevButton = new JButton("<<");
        JButton nextButton = new JButton(">>");
        
        prevButton.addActionListener(e -> {
            YearMonth newMonth = this.getCurrentMonth().minusMonths(1);
            this.setCurrentMonth(newMonth);
            updateComboBoxes(monthComboBox, yearComboBox, newMonth);
        });
        
        nextButton.addActionListener(e -> {
            YearMonth newMonth = this.getCurrentMonth().plusMonths(1);
            this.setCurrentMonth(newMonth);
            updateComboBoxes(monthComboBox, yearComboBox, newMonth);
        });
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(prevButton);
        controlPanel.add(monthComboBox);
        controlPanel.add(yearComboBox);
        controlPanel.add(nextButton);
        
        this.add(controlPanel);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCalendar(g);
    }
    
    private void drawCalendar(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        LocalDate today = LocalDate.now();
        LocalDate firstOfMonth = currentMonth.atDay(1);
        int daysInMonth = currentMonth.lengthOfMonth();
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue(); // 1=Monday, 7=Sunday
                
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        String[] daysOfWeek = {"Saturday", "Sunday","Monday", "Tuesday", "Wednesday", "Thurday", "Friday"};

        int cellWidth = getWidth() / 7;
        int cellHeight = 60;
        int calendarWidth = cellWidth * 7;
        int calendarHeight = cellHeight * 7;

        // Draw header with days of the week
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 1; i < daysOfWeek.length; i++) {
            g2d.drawString(myanmarLocalizedData.convertMMString(daysOfWeek[i]), (i-1) * cellWidth + 10, cellHeight + 30 - 10);
        }
        g2d.drawString(myanmarLocalizedData.convertMMString(daysOfWeek[0]), 6 * cellWidth + 10, cellHeight + 30 - 10);
        
        dayBounds.clear();
        
        int day = 1;
        for(int week=1;week<=6;week++) {
        	for(int dayOfWeek = 1;dayOfWeek <8;dayOfWeek++) {
        		int	x =0;
        		int	y =0;
        		int newdayOfWeek = (dayOfWeek + 1) % 8;
        		if (newdayOfWeek != 0) {
        			x = (newdayOfWeek - 1) * cellWidth;
        			y = week * cellHeight + 30; // Adjust y position to account for the title
        		}
        		else {
        				x = newdayOfWeek * cellWidth;
            			y = (week +1) * cellHeight + 30;
        		}
        		
               
        		
        		if(week == 1 && dayOfWeek < startDayOfWeek) {
        			continue;
        		}
        		
        		if(day <= daysInMonth) {
        			LocalDate date = currentMonth.atDay(day);
        			MyanmarDate mdc = new MyanmarDate(new JTKDays(date.getDayOfMonth(),date.getMonthValue(),date.getYear(),0,0,0));
        			int mmindex = (int)mdc.getMyanmarMonth();
        			String mmdMonth ="";
        			if (mmindex >=0) {
        				mmdMonth = myanmarLocalizedData.getMMMonths(mmindex, mdc.isWatat());
        			}
        			
        			//System.out.println(mdc.isBwar());
        			
        			//System.out.println(mmdMonth);
        			String sanOrSoak = myanmarLocalizedData.moonPhases[(int)mdc.getSanorSoak()];
        			String mmdDay = myanmarLocalizedData.convertMMNumber(Integer.toString((int)mdc.getMyanmarDay()));
        			
        			// Highlight the current date
                    if (date.equals(today)) {
                        g2d.setColor(Color.YELLOW); // Background color for current date
                        g2d.fillRect(x, y, cellWidth, cellHeight);
                        g2d.setColor(Color.BLACK); // Reset color for text
                    }
                    
                    // Highlight the selected date
                    if (date.equals(selectedDate)) {
                        g2d.setColor(Color.CYAN); // Background color for selected date
                        g2d.fillRect(x, y, cellWidth, cellHeight);
                        g2d.setColor(Color.BLACK); // Reset color for text
                    }
                    
        			g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        			g2d.drawString(String.valueOf(day), x + 10, y + 15);
        			g2d.setFont(new Font("SansSerif", Font.PLAIN, 10));
        			g2d.drawString(mmdMonth, x + 10, y + 30);
        			g2d.drawString(sanOrSoak + " " + mmdDay, x + 10, y + 45);
        			
                    dayBounds.put(date, new Rectangle(x, y, cellWidth, cellHeight));
                    day++;
        		}
        	}
        }
        // Draw Vertical lines
        for (int i = 0; i <= 7; i++) {
            g2d.drawLine(i * cellWidth, cellHeight + 30, i * cellWidth, calendarHeight + 30);
        }
        
        // Draw Horizontal lines
        for (int i = 1; i <= 7; i++) {
            g2d.drawLine(0, i * cellHeight + 30, calendarWidth, i * cellHeight + 30);
        }
        
    }
    public void setCurrentMonth(YearMonth currentMonth) {
        this.currentMonth = currentMonth;
        repaint();
    }

    public YearMonth getCurrentMonth() {
        return currentMonth;
    }
    
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
    
    public void setDayClickedListener(CalendarActionListener calListener) {
    	this.calListener = calListener;
    }
    
    private void updateCalendar(JComboBox<String> monthComboBox, JComboBox<Integer> yearComboBox) {
        int month = monthComboBox.getSelectedIndex() + 1;
        int year = (int) yearComboBox.getSelectedItem();
        this.setCurrentMonth(YearMonth.of(year, month));
    }

    private void updateComboBoxes(JComboBox<String> monthComboBox, JComboBox<Integer> yearComboBox, YearMonth newMonth) {
        monthComboBox.setSelectedIndex(newMonth.getMonthValue() - 1);
        yearComboBox.setSelectedItem(newMonth.getYear());
    }
}
