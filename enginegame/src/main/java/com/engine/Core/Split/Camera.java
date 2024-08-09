package com.engine.Core.Split;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class Camera extends JPanel {

    // Create Object
    private JDesktopPane desktopPane;
    private JInternalFrame internalFrame; // add JInternalFraem to JDesktopPane

    // contructor 
    public Camera() {
        setLayout(new BorderLayout());
        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);
        
        internalFrame = new JInternalFrame("Internal Frame", true, true, true, true);
        internalFrame.setSize(1020, 680);
        internalFrame.setVisible(true);

        desktopPane.add(internalFrame);
    }

    // Create JPanel of JInternalFrame
    JPanel panel = new JPanel(new BorderLayout());
    
}
