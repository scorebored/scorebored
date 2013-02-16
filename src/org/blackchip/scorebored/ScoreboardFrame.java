package org.blackchip.scorebored;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.apache.log4j.Logger;
import org.blackchip.scorebored.swing.Swing;
import org.blackchip.scorebored.swing.TextRenderer;
import org.blackchip.scorebored.swing.WindowManager;
import org.blackchip.scorebored.talker.Commentary;
import org.blackchip.scorebored.talker.Speech;
import org.blackchip.scorebored.talker.SwingTalker;
import org.blackchip.scorebored.talker.TalkListener;
import org.blackchip.scorebored.util.Strings;

public class ScoreboardFrame extends javax.swing.JFrame {

    private static final Logger log = 
            Logger.getLogger(ScoreboardFrame.class); 
    
    private static final GraphicsEnvironment graphicsEnvironment = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        
    private WindowManager windowManager = WindowManager.getInstance();
    private SwingTalker talker;
    private Match match;
    private KeyboardControl keyboardControl = new KeyboardControl();
    private TextRenderer subtitle;
    private boolean keyBindingsEnabled = false;
    private Font teamNameFont;
    private Font scoreFont; 
    private JacobExcuses excuses;
    
    public ScoreboardFrame(final Match match) {
        
        excuses = JacobExcuses.getInstance();
        
        this.match = match;
        this.talker = match.getTalker();
        if ( graphicsEnvironment.getDefaultScreenDevice().isFullScreenSupported() ) {
            this.setUndecorated(true);
        }
            
        initComponents();
        this.getRootPane().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) { 
                System.out.println(evt);
            }
        });
        
        teamNameFont = new Font("Inconsolata", Font.BOLD, 64);
        Font teamScoreFont = new Font("Inconsolata", Font.BOLD, 400);
        
        leftTeamNameText.setFont(teamNameFont);
        leftScoreText.setFont(teamScoreFont);
        rightTeamNameText.setFont(teamNameFont);
        rightScoreText.setFont(teamScoreFont);
        talkText.setText("");
        
        if ( !graphicsEnvironment.getDefaultScreenDevice()
                .isFullScreenSupported() ) { 
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }     
        subtitle = new TextRenderer(this, 0.5f, 0.9f);
        
        talker.addListener(new TalkListener() {
            @Override
            public void talkStarted(Speech speech) {
                if ( talker.isMute() || match.isSubtitled() ) { 
                    subtitle.setText(speech.getDisplayAs());
                    repaint();
                }
            }

            @Override
            public void talkEnded() {
                subtitle.fade(match.getStyle().getSubtitleFadeTime());
                repaint();
            }
        });

        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                applyStyle();
            }
        });
        
        pack();
        applyStyle();
    }

    @Override
    public void setVisible(boolean visible) { 
        GraphicsDevice graphicsDevice = 
                graphicsEnvironment.getDefaultScreenDevice();
        if ( graphicsDevice.isFullScreenSupported() ) {
            if ( visible && match.isFullScreen() ) { 
                graphicsDevice.setFullScreenWindow(this);
            } else {
                graphicsDevice.setFullScreenWindow(null);
            }
        }
        super.setVisible(visible);
        if ( visible ) { 
            JFrame frame = windowManager.getDashboardFrame();
            frame.setVisible(false);
        }
    }
    
    public void applyStyle() {
        final Component leftComponents[] = {
            leftTeamNameText, leftScoreText, leftServerText, leftWinsText 
        };
        
        final Component rightComponents[] = {
            rightTeamNameText, rightScoreText, rightServerText, rightWinsText 
        };
        
        Style style = match.getStyle();
        for ( Component c: leftComponents ) {
            c.setBackground(style.getBackgroundColor());
            c.setForeground(match.getTeam(Team.Side.LEFT).getColor().getColor());
        }
        for ( Component c: rightComponents ) {
            c.setBackground(style.getBackgroundColor());
            c.setForeground(match.getTeam(Team.Side.RIGHT).getColor().getColor());
        }
                
        teamNameFont = style.getTeamFont();
        
        Team leftTeam = match.getTeam(Team.Side.LEFT);
        if ( match.getServer() == Team.Side.LEFT ) {
            leftTeamNameText.setBackground(leftTeam.getColor().getColor());
            leftTeamNameText.setForeground(match.getStyle().getBackgroundColor());
        } else {
            leftTeamNameText.setForeground(leftTeam.getColor().getColor());
            leftTeamNameText.setBackground(match.getStyle().getBackgroundColor());           
        }
        setTextAndFont(teamNameFont, leftTeamNameText, leftTeam.getName().getDisplayAs());
        leftWinsText.setFont(teamNameFont);
        leftServerText.setFont(style.getTeamFont());
        
        Team rightTeam = match.getTeam(Team.Side.RIGHT);
        if ( match.getServer() == Team.Side.RIGHT ) {
            rightTeamNameText.setBackground(rightTeam.getColor().getColor());
            rightTeamNameText.setForeground(match.getStyle().getBackgroundColor());
        } else {
            rightTeamNameText.setForeground(rightTeam.getColor().getColor());
            rightTeamNameText.setBackground(match.getStyle().getBackgroundColor());           
        }
        setTextAndFont(teamNameFont, rightTeamNameText, rightTeam.getName().getDisplayAs());        
        rightWinsText.setFont(teamNameFont);
        rightServerText.setFont(style.getTeamFont());

        leftScoreText.setFont(style.getScoreFont());
        rightScoreText.setFont(style.getScoreFont());
        
        this.getContentPane().setBackground(style.getBackgroundColor());
        final JButton buttons[] = {
            yeahBabyButton, noBabyButton, shhButton, excuseButton,
            settingsButton, closeButton, muteButton, netButton, serverButton,
            scoreButton
        };
        for ( JButton b: buttons ) {
            b.setBackground(style.getBackgroundColor());
            b.setForeground(style.getButtonTextColor());
            b.addMouseListener(new ButtonHighlighter());
        }
        
        talkText.setBackground(style.getTextBackgroundColor());
        talkText.setForeground(style.getButtonTextColor());
        talkText.setCaretColor(style.getButtonTextColor());
    
        subtitle.setColor(match.getStyle().getSubtitleColor());
    }
    
    public void refresh() {
        Team leftTeam = match.getTeam(Team.Side.LEFT);
        setTextAndFont(teamNameFont, leftTeamNameText, leftTeam.getName().getDisplayAs());
        leftScoreText.setText("" + leftTeam.getScore());
        
        leftServerText.setText("");

        //leftServerText.setText(match.getServer() == Team.Side.LEFT 
        //        ? "\u21A2" : "");
        
        Team rightTeam = match.getTeam(Team.Side.RIGHT);
        setTextAndFont(teamNameFont, rightTeamNameText, rightTeam.getName().getDisplayAs());
        
        rightScoreText.setText("" + rightTeam.getScore());
        
        rightServerText.setText("");
        //rightServerText.setText(match.getServer() == Team.Side.RIGHT 
        //        ? "\u21A3" : "");
        
        if ( match.getMatchLength() == MatchLength.ONE ) {
            leftWinsText.setText("");
            rightWinsText.setText("");
        } else { 
            leftWinsText.setText("" + leftTeam.getWins());
            rightWinsText.setText("" + rightTeam.getWins());
        }
        
        applyStyle();
    }
    
    private void setTextAndFont(Font font, JTextField textField, String name) {

        Graphics g = this.getGraphics();
   
        int width = textField.getWidth();
        int actualWidth = (int)g.getFontMetrics(font).getStringBounds(name, g).getWidth();

        //System.out.println(name);
        //System.out.println("Width: " + width + ", actual: " + actualWidth + " font: " + font.getSize());

        
        while ( actualWidth > width && font.getSize() > 14 ) {
            font = font.deriveFont((float)font.getSize() - 1);
            width = textField.getWidth();
            actualWidth = (int)g.getFontMetrics(font).getStringBounds(name, g).getWidth();  
        }
        //System.out.println("Width: " + width + ", actual: " + actualWidth + " font: " + font.getSize());

        textField.setFont(font);
        textField.setText(name);
    }
    
    private void showAdjustmentDialog() {
        setVisible(false);
        JDialog dialog = new AdjustmentDialog(match);
        Swing.centerOnScreen(dialog);
        dialog.setVisible(true);
        setVisible(true);
        refresh();    
    }
    
    private void showSettingsDialog() {
        setVisible(false);
        JDialog dialog = new MatchSettingsDialog(match);
        Swing.centerOnScreen(dialog);
        dialog.setVisible(true);
        setVisible(true);
        refresh();
    }
    
    private void advanceTeam(Team.Side side) {
        try {
            match.incrementTeamScore(side);
        } catch ( Exception e ) {
            Swing.showError(e);
        }
        refresh();
    }
    
    private void advanceVictory(Team.Side side) {
        try {
            match.incrementTeamVictory(side);
        } catch ( Exception e ) {
            Swing.showError(e);
        }
        refresh();
    }

    private void retreatTeam(Team.Side side) {
        try {
            match.decrementTeamScore(side);
        } catch ( Exception e ) {
            Swing.showError(e);
        }
        refresh();
    }
    
    private void retreatVictory(Team.Side side) {
        try {
            match.decrementTeamVictory(side);
        } catch ( Exception e ) {
            Swing.showError(e);
        }
        refresh();
    }
        
    class KeyboardControl implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent ke) {
            if ( ke.getID() == KeyEvent.KEY_RELEASED ) {

                if ( ke.isShiftDown() ) {
                    switch ( ke.getKeyCode() ) {
                        case KeyEvent.VK_RIGHT:
                            retreatTeam(Team.Side.RIGHT);
                            return true;
                        case KeyEvent.VK_LEFT:
                            retreatTeam(Team.Side.LEFT);
                            return true;
                        case KeyEvent.VK_1:
                            retreatVictory(Team.Side.LEFT);
                            return true;
                        case KeyEvent.VK_2:
                            retreatVictory(Team.Side.RIGHT);
                            return true;
                    }
                }
                
                if ( (ke.getKeyCode() == KeyEvent.VK_LEFT || 
                      ke.getKeyCode() == KeyEvent.VK_RIGHT) && match.isEndOfMatch() ) {
                    setVisible(false);
                    windowManager.getDashboardFrame().setVisible(true);
                    match.reset();
                    return true;
                }

                switch ( ke.getKeyCode() ) { 
                    case KeyEvent.VK_RIGHT:
                        advanceTeam(Team.Side.RIGHT);
                        return true;
                    case KeyEvent.VK_LEFT:
                        advanceTeam(Team.Side.LEFT);
                        return true;
                    case KeyEvent.VK_1:
                        advanceVictory(Team.Side.LEFT);
                        return true;
                    case KeyEvent.VK_2:
                        advanceVictory(Team.Side.RIGHT);
                        return true;
                    case KeyEvent.VK_A:
                        showAdjustmentDialog();
                        return true;
                    case KeyEvent.VK_M:
                        toggleMute();
                        return true;
                    case KeyEvent.VK_N:
                        net();
                        return true;
                    case KeyEvent.VK_H:
                        shh();
                        return true;
                    case KeyEvent.VK_T:
                        showSettingsDialog();
                        return true;
                    case KeyEvent.VK_O:
                        noBaby();
                        return true;
                    case KeyEvent.VK_Y:
                        yeahBaby();
                        return true;
                     case KeyEvent.VK_E:
                        jacobExcuse();
                        return true;
                     case KeyEvent.VK_S:
                        score();
                        return true;
                     case KeyEvent.VK_V:
                        server();
                        return true;
                     case KeyEvent.VK_SPACE:
                         enableKeyBindings(false);
                         talkText.requestFocusInWindow();
                         return true;
                }
            }
            return false;
        }
    }
    
    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
        subtitle.paint(g1);
    }
    
    class ButtonHighlighter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent me) {
            JButton button = (JButton)me.getSource();
            button.setBackground(match.getStyle().getButtonHoverColor());
            button.setForeground(match.getStyle().getBackgroundColor());
        }

        @Override
        public void mouseExited(MouseEvent me) {
            JButton button = (JButton)me.getSource();
            button.setBackground(match.getStyle().getBackgroundColor());
            button.setForeground(match.getStyle().getButtonTextColor());
        }    
    }
    
    private void yeahBaby() { 
        talker.say("Yeah Baby!");
    }
    
    private void noBaby() {
        talker.say("No Baby!");
    }
    
    private void shh() {
        talker.say("Shh");
    }
    
    private void net() {
        talker.say("Nothing but Net!");
    }

    private void score() {
        AnnounceScore announceScore = new AnnounceScore(match);
        talker.say(announceScore.getScore());
    }

    private void server() {
        talker.say(new Commentary()
                .add(match.getTeam(match.getServer()).getName())
                .add(" is serving"));
    }

    private void jacobExcuse() {
       talker.say(excuses.getRandom());
    }
    
    private void toggleMute() {
        talker.toggleMute();
        if ( talker.isMute() ) { 
            talker.silence();
            subtitle.setText("Sound Off");
        } else { 
            subtitle.setText("Sound On");
        }
        subtitle.fade(1000, match.getStyle().getSubtitleFadeTime());
    }
    
    private void enableKeyBindings(boolean enable) { 
        if ( enable && !keyBindingsEnabled ) {
            log.trace("Enabled key bindings");
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .addKeyEventDispatcher(keyboardControl); 
            keyBindingsEnabled = true;
        } else if ( !enable && keyBindingsEnabled ) {
            log.trace("Disabled key bindings");
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .removeKeyEventDispatcher(keyboardControl);
            keyBindingsEnabled = false;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftTeamNameText = new javax.swing.JTextField();
        rightTeamNameText = new javax.swing.JTextField();
        yeahBabyButton = new javax.swing.JButton();
        rightServerText = new javax.swing.JTextField();
        leftServerText = new javax.swing.JTextField();
        leftWinsText = new javax.swing.JTextField();
        rightWinsText = new javax.swing.JTextField();
        leftScoreText = new javax.swing.JTextField();
        rightScoreText = new javax.swing.JTextField();
        noBabyButton = new javax.swing.JButton();
        shhButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        excuseButton = new javax.swing.JButton();
        talkText = new javax.swing.JTextField();
        settingsButton = new javax.swing.JButton();
        muteButton = new javax.swing.JButton();
        netButton = new javax.swing.JButton();
        scoreButton = new javax.swing.JButton();
        serverButton = new javax.swing.JButton();

        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        leftTeamNameText.setEditable(false);
        leftTeamNameText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        leftTeamNameText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        leftTeamNameText.setText("Home");
        leftTeamNameText.setBorder(null);

        rightTeamNameText.setEditable(false);
        rightTeamNameText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        rightTeamNameText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rightTeamNameText.setText("Away");
        rightTeamNameText.setBorder(null);

        yeahBabyButton.setMnemonic('Y');
        yeahBabyButton.setText("Yeah");
        yeahBabyButton.setActionCommand("Yeah Baby");
        yeahBabyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        yeahBabyButton.setOpaque(true);
        yeahBabyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yeahBabyButtonActionPerformed(evt);
            }
        });

        rightServerText.setEditable(false);
        rightServerText.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        rightServerText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        rightServerText.setText("*");
        rightServerText.setBorder(null);

        leftServerText.setEditable(false);
        leftServerText.setFont(new java.awt.Font("Lucida Grande", 1, 48)); // NOI18N
        leftServerText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        leftServerText.setText("*");
        leftServerText.setBorder(null);

        leftWinsText.setEditable(false);
        leftWinsText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        leftWinsText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        leftWinsText.setText("9");
        leftWinsText.setBorder(null);

        rightWinsText.setEditable(false);
        rightWinsText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        rightWinsText.setText("9");
        rightWinsText.setBorder(null);

        leftScoreText.setEditable(false);
        leftScoreText.setFont(new java.awt.Font("Inconsolata", 1, 200)); // NOI18N
        leftScoreText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        leftScoreText.setText("99");
        leftScoreText.setBorder(null);

        rightScoreText.setEditable(false);
        rightScoreText.setFont(new java.awt.Font("Inconsolata", 1, 200)); // NOI18N
        rightScoreText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rightScoreText.setText("99");
        rightScoreText.setBorder(null);

        noBabyButton.setMnemonic('O');
        noBabyButton.setText("No");
        noBabyButton.setActionCommand("Yeah Baby");
        noBabyButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        noBabyButton.setOpaque(true);
        noBabyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBabyButtonActionPerformed(evt);
            }
        });

        shhButton.setMnemonic('H');
        shhButton.setText("Shh");
        shhButton.setActionCommand("Yeah Baby");
        shhButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        shhButton.setOpaque(true);
        shhButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shhButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.setActionCommand("Yeah Baby");
        closeButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setOpaque(true);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        excuseButton.setMnemonic('E');
        excuseButton.setText("Excuse");
        excuseButton.setActionCommand("Yeah Baby");
        excuseButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        excuseButton.setOpaque(true);
        excuseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excuseButtonActionPerformed(evt);
            }
        });

        talkText.setText("Speech");
        talkText.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        talkText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talkTextActionPerformed(evt);
            }
        });
        talkText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                talkTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                talkTextFocusLost(evt);
            }
        });

        settingsButton.setMnemonic('T');
        settingsButton.setText("Settings");
        settingsButton.setActionCommand("Yeah Baby");
        settingsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        settingsButton.setOpaque(true);
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });

        muteButton.setMnemonic('M');
        muteButton.setText("Mute");
        muteButton.setActionCommand("Yeah Baby");
        muteButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        muteButton.setOpaque(true);
        muteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteButtonActionPerformed(evt);
            }
        });

        netButton.setMnemonic('N');
        netButton.setText("Net");
        netButton.setActionCommand("Yeah Baby");
        netButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        netButton.setOpaque(true);
        netButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netButtonActionPerformed(evt);
            }
        });

        scoreButton.setMnemonic('S');
        scoreButton.setText("Score");
        scoreButton.setActionCommand("Yeah Baby");
        scoreButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scoreButton.setOpaque(true);
        scoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreButtonActionPerformed(evt);
            }
        });

        serverButton.setMnemonic('V');
        serverButton.setText("Server");
        serverButton.setActionCommand("Yeah Baby");
        serverButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        serverButton.setOpaque(true);
        serverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(talkText)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(closeButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(muteButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(settingsButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(excuseButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(serverButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(scoreButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(netButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(shhButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(noBabyButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(yeahBabyButton))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(leftWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(leftTeamNameText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(leftServerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(leftScoreText))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(rightScoreText)
                                    .add(layout.createSequentialGroup()
                                        .add(rightServerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(rightTeamNameText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(rightWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 44, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                        .add(4, 4, 4)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {leftServerText, leftWinsText, rightServerText, rightWinsText}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(leftServerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rightWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(1, 1, 1)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rightServerText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(rightTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 59, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(leftTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(leftWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(leftScoreText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .add(rightScoreText))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(closeButton)
                    .add(excuseButton)
                    .add(settingsButton)
                    .add(muteButton)
                    .add(serverButton)
                    .add(scoreButton)
                    .add(netButton)
                    .add(shhButton)
                    .add(noBabyButton)
                    .add(yeahBabyButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(talkText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {leftServerText, leftTeamNameText, leftWinsText, rightServerText, rightTeamNameText, rightWinsText}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        enableKeyBindings(true);
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        enableKeyBindings(false);
    }//GEN-LAST:event_formWindowLostFocus

    private void yeahBabyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yeahBabyButtonActionPerformed
        yeahBaby();
    }//GEN-LAST:event_yeahBabyButtonActionPerformed

    private void noBabyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBabyButtonActionPerformed
        noBaby();
    }//GEN-LAST:event_noBabyButtonActionPerformed

    private void shhButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shhButtonActionPerformed
        shh();
    }//GEN-LAST:event_shhButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().setFullScreenWindow(null);
        this.setVisible(false);
        windowManager.getDashboardFrame().setVisible(true);
    }//GEN-LAST:event_closeButtonActionPerformed

    private void excuseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excuseButtonActionPerformed
        jacobExcuse();
    }//GEN-LAST:event_excuseButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        showSettingsDialog();
    }//GEN-LAST:event_settingsButtonActionPerformed

    private void talkTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_talkTextFocusGained
        enableKeyBindings(false);
    }//GEN-LAST:event_talkTextFocusGained

    private void talkTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_talkTextFocusLost
        enableKeyBindings(true);
    }//GEN-LAST:event_talkTextFocusLost

    private void talkTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talkTextActionPerformed
        if ( Strings.isEmpty(talkText.getText()) ) { 
            return;
        }
        talker.say(talkText.getText());
        talkText.setText("");
        this.requestFocusInWindow();
    }//GEN-LAST:event_talkTextActionPerformed

    private void muteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteButtonActionPerformed
        toggleMute();
    }//GEN-LAST:event_muteButtonActionPerformed

    private void netButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netButtonActionPerformed
        net();
    }//GEN-LAST:event_netButtonActionPerformed

    private void scoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreButtonActionPerformed
        score();
    }//GEN-LAST:event_scoreButtonActionPerformed

    private void serverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverButtonActionPerformed
        server();
    }//GEN-LAST:event_serverButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton excuseButton;
    private javax.swing.JTextField leftScoreText;
    private javax.swing.JTextField leftServerText;
    private javax.swing.JTextField leftTeamNameText;
    private javax.swing.JTextField leftWinsText;
    private javax.swing.JButton muteButton;
    private javax.swing.JButton netButton;
    private javax.swing.JButton noBabyButton;
    private javax.swing.JTextField rightScoreText;
    private javax.swing.JTextField rightServerText;
    private javax.swing.JTextField rightTeamNameText;
    private javax.swing.JTextField rightWinsText;
    private javax.swing.JButton scoreButton;
    private javax.swing.JButton serverButton;
    private javax.swing.JButton settingsButton;
    private javax.swing.JButton shhButton;
    private javax.swing.JTextField talkText;
    private javax.swing.JButton yeahBabyButton;
    // End of variables declaration//GEN-END:variables


}
