package com.engine.Core.BarTitle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.engine.Core.BarTitle.Function.EventFunction;

public class Menus extends JPanel {

    private ShadowType shadowType = ShadowType.CENTER;
    private int shadowSize = 3;
    private float shadowOpacity = 0.5f;
    private Color shadowColor = Color.RED;

    // Add Button
    private CustomButton[] options;

    // hover button
    public boolean over;
    public Color color;
    public Color colorOver;
    public Color colorClick;
    public Color borderColor;
    public int radius = 0;

    public Menus() {
        setOpaque(false);
        setLayout(null);
        setPreferredSize(new Dimension(500, 50)); // Set preferred size of JPanel
        addHeaderMenu();
    }

    private void addHeaderMenu() {

        // Color using the hover background
        colorOver = new Color(136, 139, 141);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(255,255,255);

        // set Font
        Font F_BTNname = new Font("Arial", Font.BOLD, 10);

        String[] buttonNames = { "File", "Edit", "View", "Window", "Help" };
        int x = 0; // Starting x position
        int y = 0; // Starting y position
        int width = 75; // Button width
        int height = 20; // Button height
        int spacing = 0; // Spacing between buttons
        options = new CustomButton[buttonNames.length];
        
        for (int i = 0; i < buttonNames.length; i++) {
            final int index = i;
            options[index] = new CustomButton(buttonNames[index]);

            // Customize to make the button clear
            options[index].setOpaque(false);
            options[index].setBorderPainted(false);
            options[index].setFocusPainted(false);
            options[index].setBackground(new Color(0,0,0,0));
            options[index].setForeground(new Color(255,0,0));

            FunctionEvent(options[index]);
            options[index].setContentAreaFilled(false);

            options[index].setActionCommand("Options " + (i + 1));
            options[index].addActionListener(new EventFunction());
            options[index].setFont(F_BTNname);
            options[index].setBounds(x, y, width, height); // Set position and size of each button

            // add MouseListener to handle hover effect
            options[index].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    // options[index].setBackground(new Color(200, 200, 200)); // Change to hover color
                    ((CustomButton) evt.getSource()).setCustomBackground(new Color(200, 200, 200)); // Change to hover color
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    // options[index].setBackground(new Color(255, 255, 255)); // Change back to original color
                    ((CustomButton) evt.getSource()).setCustomBackground(new Color(255,255,255)); // Change back to original color
                }
            });

            // Add MouseMotionListener to handle dragging
            options[index].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                @Override
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    // options[index].repaint(); // Repaint button when dragged
                    ((CustomButton) evt.getSource()).repaint(); // Repaint button when dragged
                }
            });

            add(options[index]);
            x += width + spacing; // Update x position for the next button

        }
        setLocation(0, 0);
    }

    // add Hover Button
    private void FunctionEvent(JButton option) {
        option.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                option.setBackground(colorOver);
                over = true;
            };

            @Override
            public void mouseExited(MouseEvent e) {
                option.setBackground(color);
                over = false;
            };

            @Override
            public void mousePressed(MouseEvent me) {
                option.setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    option.setBackground(colorOver);
                } else {
                    option.setBackground(color);
                }
            }

        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
        super.paintComponent(grphcs);
    }

    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        // int width = getWidth() - size;
        // int height = getHeight() - size;

        if (shadowType == ShadowType.TOP) {
            x = shadowSize;
            y = size;
        } else if (shadowType == ShadowType.BOT) {
            x = shadowSize;
            y = 0;
        } else if (shadowType == ShadowType.TOP_LEFT) {
            x = size;
            y = size;
        } else if (shadowType == ShadowType.TOP_RIGHT) {
            x = 0;
            y = size;
        } else if (shadowType == ShadowType.BOT_LEFT) {
            x = size;
            y = 0;
        } else if (shadowType == ShadowType.BOT_RIGHT) {
            x = 0;
            y = 0;
        } else {
            // Center
            x = shadowSize;
            y = shadowSize;
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int w_width = screenSize.width;
        // int h_height = screenSize.height;

        BufferedImage img = new BufferedImage(w_width, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(new Color(119,136,153));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // g.fillRoundRect(0, 0, width, height, 10, 10);
        g.fillRect(0, 0, w_width, 20); // Fill a square instead of an oval
        // Create Shadow
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
    }

    public ShadowType getShadowType() {
        return shadowType;
    }

    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}

 // Custom Button Class
 class CustomButton extends JButton {
    public boolean over;
    public Color color;
    public Color colorOver;
    public Color colorClick;
    public Color borderColor;
    public int radius = 0;

    private Color customBackground;

    public CustomButton(String text) {
        super(text);
        this.customBackground = getBackground();
        this.borderColor = Color.GRAY; // Default border color
        setContentAreaFilled(false);
    }

    public void setCustomBackground(Color color) {
        this.customBackground = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(customBackground);
        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}
