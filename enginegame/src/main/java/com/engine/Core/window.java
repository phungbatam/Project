package com.engine.Core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
// import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
// import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.engine.Core.BarTitle.Menus;
import com.engine.Core.BarTitle.ShadowType;
import com.engine.Core.Split.Camera;
import com.engine.Core.Split.Display;
import com.formdev.flatlaf.FlatLightLaf;

public class window extends infor {

    public JFrame frame;

    // titleBar
    private JPanel wtitleBar;
    private JPanel btnPanel;
    private JLabel textname;
    private JButton addContentBarClose, addContentResize, addResizebleButton;

    // vertical split
    private JSplitPane splitPane;

    // Menu bar
    public window() {
        frame = new JFrame();

        frame.setTitle("Full screen REizeble JFrame");

        // call object JPanel
        wtitleBar = new JPanel();
        btnPanel = new JPanel();

        addContentBarClose = new JButton() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Paint Border
                g2.setColor(borderColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(getBackground());
                // Border set 2 Pix
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
                super.paintComponent(grphcs);
            };
        };

        addContentResize = new JButton() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Paint Border
                g2.setColor(borderColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(getBackground());
                // Border set 2 Pix
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
                super.paintComponent(grphcs);
            };
        };

        addResizebleButton = new JButton() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Paint Border
                g2.setColor(borderColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(getBackground());
                // Border set 2 Pix
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
                super.paintComponent(grphcs);
            }
        };

        textname = new JLabel();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(java.awt.event.WindowEvent e) {
                frame.setVisible(true);
            }

            @Override
            public void windowDeiconified(java.awt.event.WindowEvent e) {
                // System.out.println("Window restored");
                frame.setExtendedState(JFrame.NORMAL);

            }
        });

        display();

    }

    // Add Display
    private void display() {
        try {

            UIManager.setLookAndFeel(new FlatLightLaf());

            // Create JTabbedPane for left and right panels
            JTabbedPane leftTabs = new JTabbedPane();
            leftTabs.addTab("Tab 1", new JLabel("Content of Tab 1"));
            leftTabs.addTab("Tab 2", new JLabel("Content of Tab 2"));

            JTabbedPane rightTabs = new JTabbedPane();
            rightTabs.addTab("Command Output", new Display(JSplitPane.VERTICAL_SPLIT, new JSplitPane(), new JSplitPane()));
            rightTabs.addTab("Edit Input", new Camera());
            rightTabs.addTab("Output", new JLabel());

            // vertical Splits
            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBackground(Color.CYAN);
            leftPanel.add(leftTabs, BorderLayout.CENTER);

            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBackground(Color.GRAY);
            rightPanel.add(rightTabs, BorderLayout.CENTER);

            // set preferred sizes based on screen size
            int panelHeight = (int) (screenHeightSplit * ratioSplits);
            leftPanel.setPreferredSize(new Dimension(screenWidthSplit / 2, panelHeight));
            rightPanel.setPreferredSize(new Dimension(screenWidthSplit / 2, panelHeight));

            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);

            // create vertical stripes
            JPanel addSplit = new JPanel();

            splitPane.setOneTouchExpandable(true);
            splitPane.setDividerLocation(300);
            splitPane.setDividerSize(5);
            addSplit.setBackground(Color.YELLOW);
            addSplit.setLayout(new BorderLayout()); // Đảm bảo addSplit sử dụng BorderLayout
            addSplit.add(splitPane, BorderLayout.LINE_START);

            frame.getContentPane().add(addSplit, BorderLayout.SOUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Show Frame
    public void showFrame() {
        frame.getContentPane().setBackground(Color.decode("0x123456"));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Screen
    public void fullscreen() {
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.NORMAL);
        frame.setResizable(false);
        int state = frame.getExtendedState();
        System.out.println("Current extended state: " + state);

        // Current screen width and height
        SwingUtilities.invokeLater(() -> {
            int width = frame.getWidth();
            int height = frame.getHeight();

            frame.setSize(width, height);
            System.out.println("Information screen");
            System.out.println("Current frame width: " + width);
            System.out.println("Current frame height: " + height);
        });

        if ((state & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            System.out.println("The frame is maximized both horizontally and vertically.");
        }

        updateFrameBounds();
        NonetitleBar();
    }

    private void updateFrameBounds() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle usableBounds = ge.getMaximumWindowBounds();
        frame.setBounds(usableBounds);
    }

    private void NonetitleBar() {
        // frame.setUndecorated(true);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        wtitleBar.setBackground(Color.BLACK);
        int width = frame.getWidth();
        wtitleBar.setLayout(new BorderLayout());
        wtitleBar.setPreferredSize(new Dimension(width, 20));

        addContentBarClose.setText("X");
        addContentBarClose.setToolTipText("Close");
        addContentBarClose.addActionListener(e -> System.exit(0));

        // Customize to make the button clear
        addContentBarClose.setOpaque(true);
        addContentBarClose.setBorderPainted(false);
        addContentBarClose.setFocusPainted(false);
        addContentBarClose.setBackground(new Color(0, 0, 0, 0));

        // create Background
        // Color hoverColor = new Color(255,255, 0, 100);

        // Color using the hover background
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(30, 136, 56);

        addContentBarClose.setContentAreaFilled(false);

        // add hover Button
        addContentBarClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addContentBarClose.setBackground(colorOver);
                over = true;
            };

            @Override
            public void mouseExited(MouseEvent e) {
                addContentBarClose.setBackground(color);
                over = false;
            };

            @Override
            public void mousePressed(MouseEvent me) {
                addContentBarClose.setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    addContentBarClose.setBackground(colorOver);
                } else {
                    addContentBarClose.setBackground(color);
                }
            }

        });

        wtitleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }

        });

        wtitleBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Point p = MouseInfo.getPointerInfo().getLocation();
                // frame.setLocation(p.x - xOffset, p.y - yOffset);
            }

        });

        addContentResize.setText("-");
        addContentResize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setExtendedState(JFrame.ICONIFIED);
            };
        });
        addContentResize.setToolTipText("turn off screen");
        addContentResize.setOpaque(true);
        addContentResize.setBorderPainted(false);
        addContentResize.setFocusPainted(false);
        addContentResize.setBackground(new Color(0, 0, 0, 0));

        // add hover Button Content Resize
        addContentResize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addContentResize.setBackground(colorOver);
                over = true;
            };

            @Override
            public void mouseExited(MouseEvent e) {
                addContentResize.setBackground(color);
                over = false;
            };

            @Override
            public void mousePressed(MouseEvent me) {
                addContentResize.setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    addContentResize.setBackground(colorOver);
                } else {
                    addContentResize.setBackground(color);
                }
            }

        });

        addContentResize.setContentAreaFilled(false);

        addResizebleButton.setText("❏");
        addResizebleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            };
        });

        addResizebleButton.setToolTipText("full screen normal");
        addResizebleButton.setOpaque(true);
        addResizebleButton.setBorderPainted(false);
        addResizebleButton.setFocusPainted(false);
        addResizebleButton.setBackground(new Color(0, 0, 0, 0));

        // add hover Button Content Resize
        addResizebleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addResizebleButton.setBackground(colorOver);
                over = true;
            };

            @Override
            public void mouseExited(MouseEvent e) {
                addResizebleButton.setBackground(color);
                over = false;
            };

            @Override
            public void mousePressed(MouseEvent me) {
                addResizebleButton.setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    addResizebleButton.setBackground(colorOver);
                } else {
                    addResizebleButton.setBackground(color);
                }
            }

        });

        addResizebleButton.setContentAreaFilled(false);

        textname.setText("Game Engine 3D");
        textname.setForeground(Color.WHITE);

        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.add(addResizebleButton);
        btnPanel.add(addContentResize);
        btnPanel.add(addContentBarClose);

        wtitleBar.add(btnPanel, BorderLayout.EAST);
        wtitleBar.add(textname, BorderLayout.BEFORE_LINE_BEGINS);
        // add title Frame
        frame.add(wtitleBar, BorderLayout.NORTH);
    }

    @Override
    public void setupSizeScreen(int size_width, int size_height) {
        frame.setSize(size_width, size_height);
        frame.setResizable(false);
    }

    @Override
    public void fullScreenToggle() {
        JButton toogleBtn = new JButton("Toggle Full screen");
        toogleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                toggleFullScreen();
            }
        });

        panel = new JPanel();
        panel.add(toogleBtn);

        frame.add(panel);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice = ge.getDefaultScreenDevice();

    }

    private void toggleFullScreen() {
        if (isFullScreen) {
            // Exit full-screen mode
            graphicsDevice.setFullScreenWindow(null);
            SwingUtilities.invokeLater(() -> {
                int width = frame.getWidth();
                int height = frame.getHeight();
                setupSizeScreen(width, height);
            });
        } else {
            // Enter full-screen mode
            graphicsDevice.setFullScreenWindow(frame);
            frame.setSize(graphicsDevice.getDisplayMode().getWidth(), graphicsDevice.getDisplayMode().getHeight());
        }
        isFullScreen = !isFullScreen;
    }

    @Override
    public void menuFrame() {

        Menus shadow = new Menus();

        shadow.setBackground(new java.awt.Color(92, 78, 210));
        shadow.setShadowColor(new java.awt.Color(79, 79, 79));
        shadow.setShadowType(ShadowType.BOT_RIGHT);

        shadow.setSize(100, 100);
        frame.add(shadow);
    }

}
