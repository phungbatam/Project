package com.engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
// import javax.swing.UIManager;

import com.engine.Core.window;
// import com.formdev.flatlaf.FlatLightLaf;

public class App {
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = toolkit.getScreenSize();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                // Config Nimbus 
                // try {
                //     for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                //         if("Nimbus".equals(info.getName())) {
                //             UIManager.setLookAndFeel(info.getClassName());
                //             break;
                //         }
                //     }
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }

                window winFrame = new window();
                winFrame.menuFrame();
                winFrame.fullscreen();
                // winFrame.fullScreenToggle();
                winFrame.setupSizeScreen(screenSize.width, screenSize.height);
                System.out.println("Screen width: " + screenSize.width);
                System.out.println("Screen height: " + screenSize.height);
                winFrame.showFrame();
            }
        });
    }
}