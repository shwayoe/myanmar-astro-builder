package com.shwayoe.ui;
import javax.swing.*;
import java.awt.*;

public class AstroPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Define a title for the table
    private String title = "";

    // Define a 2D array to store data for each cell
    private String[][] astroData = {
        {"ကလိယုဂ်", ""},
        {"အတာနေ့", ""},
        {"တိထီရက်လွန်", ""},
        {"ဝေါဟာရရက်လွန်", ""},
        {"တိထီ ဃဋီ", ""},
        {"သုဒ္ဒဒိန်", ""},
        {"ဂျုလီယံရက်", ""},
        {"သာဝဏရက်", ""},
        {"လဂ်သွားစက်", ""},
        {"မြန်မာအယန", ""},
    };

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Get the width and height of the panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Number of rows and columns
        int rows = 10;
        int cols = 2;

        // Calculate the width and height of each cell
        int cellWidth = panelWidth / cols;
        int cellHeight = (panelHeight - 50) / rows; // Adjust cell height to account for title

        // Draw the title
        g2d.setFont(new Font("SansSerif", Font.BOLD, 15));
        FontMetrics titleMetrics = g2d.getFontMetrics();
        int titleWidth = titleMetrics.stringWidth(title);
        int titleX = (panelWidth - titleWidth) / 2;
        int titleY = titleMetrics.getAscent() + 10; // Add some padding

        g2d.drawString(title, titleX, titleY);
        

        // Draw the rows and columns
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * cellWidth;
                int y = row * cellHeight + 50; // Adjust y position to account for the title

                // Draw the cell border
                g2d.drawRect(x, y, cellWidth, cellHeight);

                // Draw the cell data
                String text = astroData[row][col];
                g2d.setFont(new Font("SansSerif", Font.PLAIN, 14));
                FontMetrics metrics = g2d.getFontMetrics();
                int textWidth = metrics.stringWidth(text);
                int textHeight = metrics.getHeight();

                int textX = x + (cellWidth - textWidth) / 2;
                int textY = y + (cellHeight - textHeight) / 2 + metrics.getAscent();

                g2d.drawString(text, textX, textY);
            }
        }
    }
    
    public void setData(int index, String data) {
    	this.astroData[index][1] = data;
    }
    
    public void setTitle(String t) {
    	this.title = t;
    }
    
    public void updateTable() {
    	repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 500); // Set preferred size for the panel
    }
}
