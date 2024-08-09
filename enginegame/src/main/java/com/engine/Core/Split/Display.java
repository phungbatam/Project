package com.engine.Core.Split;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Display extends JSplitPane {
    
    private JPanel topSplit, bottomSplit;
    private JTabbedPane topTabs, bottomTabs;

    // Camera 
    private Camera cam;

    public Display(int orientation, JSplitPane top, JSplitPane bottom) {
        super(orientation, top, bottom);
        
        // cam = new Camera();
        topSplit = new JPanel(new BorderLayout());
        bottomSplit = new JPanel(new BorderLayout());
        setTopComponent(topSplit);
        setBottomComponent(bottomSplit);

        divScreen();
        setDividerLocation(300);
        setDividerSize(5);

        // config color Split
        this.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                BasicSplitPaneDivider divider = super.createDefaultDivider();
                divider.setBackground(Color.BLACK); // Set divider background color
                return divider;
            }
        });

    }

    private void divScreen() {

        // Create top Tabs
        topTabs = new JTabbedPane();
        // Camera Topcamera = new Camera();
        // topTabs.add(Topcamera);

        // Create Bottom Tabs
        bottomTabs = new JTabbedPane();
        // Camera camera = new Camera(); // Create instance of Camera
        bottomTabs.addTab("Terminal", null); // Add Camera for tab "Terminal"
        bottomTabs.addTab("Information", new JLabel("this is terminal"));
        bottomTabs.addTab("Source", new JLabel("this is terminal"));
        
        bottomSplit.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                bottomTabs.setPreferredSize(new Dimension(bottomSplit.getWidth(), bottomSplit.getHeight()));
                bottomSplit.revalidate();
            }
        });
        
        bottomTabs.revalidate();
        bottomTabs.repaint();

        topSplit.add(topTabs, BorderLayout.CENTER);
        bottomSplit.add(bottomTabs, BorderLayout.CENTER);
    }

}
