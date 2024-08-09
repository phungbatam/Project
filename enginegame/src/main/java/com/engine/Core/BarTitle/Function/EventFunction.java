package com.engine.Core.BarTitle.Function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventFunction implements ActionListener {

    private File files;
    private Edit edits;
    private View views;
    private Window windows;
    private Help helps;

    public EventFunction() {
        files = new File();
        edits = new Edit();
        views = new View();
        windows = new Window();
        helps = new Help();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Options 1":
                files.setAlwaysOnTop(true);
                files.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                files.setVisible(true);
                System.err.println("xin chao");
                files.setSize(1200, 680);
                break;
            case "Options 2":
                edits.setVisible(true);
            case "Options 3":
                files.setVisible(true);
                break;
            case "Options 4":
                edits.setVisible(true);
            case "Options 5":
                files.setVisible(true);
                break;
            default:
                break;
        }
    }

}
