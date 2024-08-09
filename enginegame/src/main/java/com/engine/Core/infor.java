package com.engine.Core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.DisplayMode;

public abstract class infor {

    public boolean isFullScreen = false;
    public GraphicsDevice graphicsDevice;
    public JPanel panel;

    public int xOffset = 0;
    public int yOffset = 0;

    // hover button
    public boolean over;
    public Color color;
    public Color colorOver;
    public Color colorClick;
    public Color borderColor;
    public int radius = 0;

    // Contructor
    public infor() {

    }

    // Get screen size
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    DisplayMode dm = gd.getDisplayMode();
    public int screenWidthSplit = dm.getWidth();
    public int screenHeightSplit = dm.getHeight();
    // aspect ratio Splits
    public float ratioSplits = 0.94f; // 93% of screen

    public abstract void setupSizeScreen(int size_width, int size_height);

    public abstract void fullScreenToggle();

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

    public java.awt.Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(java.awt.Color colorOver) {
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

    public abstract void menuFrame();

}
