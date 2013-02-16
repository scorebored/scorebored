package org.blackchip.scorebored;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.blackchip.scorebored.talker.Speech;
import org.blackchip.scorebored.Team.Side;
import org.blackchip.scorebored.swing.WindowManager;
import org.blackchip.scorebored.talker.SwingTalker;
import org.blackchip.scorebored.talker.Voice;
import org.blackchip.scorebored.util.Strings;
import org.blackchip.scorebored.util.Validate;
import org.blackchip.scorebored.util.ValidationException;

public class MatchSettingsDialog extends javax.swing.JDialog {

    private Match match;
    private WindowManager windowManager = WindowManager.getInstance();
    
    public MatchSettingsDialog(Match match) {
        super((JFrame)null, true);
        this.match = match;
        
        initComponents();
        
        matchLengthCombo.setModel(new DefaultComboBoxModel(
                MatchLength.values()));
        gameLengthCombo.setModel(new DefaultComboBoxModel(
                GameLength.values()));
        styleCombo.setModel(new DefaultComboBoxModel(
                Style.getStyles().toArray(new Style[] {})));
        commentatorCombo.setModel(new DefaultComboBoxModel(
                match.getTalker().getVoices().toArray(new Voice[] {})));
       
        matchLengthCombo.setSelectedItem(match.getMatchLength());
        gameLengthCombo.setSelectedItem(match.getGameLength());
        styleCombo.setSelectedItem(match.getStyle());
        commentatorCombo.setSelectedItem(match.getTalker().getVoice());
        
        updateTeamColorModel();
        
        Team leftTeam = match.getTeam(Team.Side.LEFT);
        leftTeamNameText.setText(leftTeam.getName().getDisplayAs());
        if ( !leftTeam.getName().getDisplayAs().equals(leftTeam.getName().getSpeakAs()) ) {
            leftTeamSayText.setText(leftTeam.getName().getSpeakAs());
        }
        leftTeamColorCombo.setSelectedItem(leftTeam.getColor());
        
        Team rightTeam = match.getTeam(Team.Side.RIGHT);
        rightTeamNameText.setText(rightTeam.getName().getDisplayAs());
        if ( !rightTeam.getName().getDisplayAs().equals(rightTeam.getName().getSpeakAs()) ) {
            rightTeamSayText.setText(rightTeam.getName().getSpeakAs());
        }
        rightTeamColorCombo.setSelectedItem(rightTeam.getColor());
        
        subtitlesCheck.setSelected(match.isSubtitled());

        this.getRootPane().setDefaultButton(okButton);
        
        this.setPreferredSize(new Dimension(350, 570));
        pack();
        
    }

    private void updateTeamColorModel() {
        Style style = (Style)styleCombo.getSelectedItem();
        leftTeamColorCombo.setModel(new DefaultComboBoxModel(
                style.getTeamColors().toArray(new TeamColor[] {})));
        rightTeamColorCombo.setModel(new DefaultComboBoxModel(
                style.getTeamColors().toArray(new TeamColor[] {})));
    }
    
    private void update() {
        try {
            Validate.notEmpty(leftTeamNameText.getText(), 
                    "Left team needs a name");
            Validate.notEmpty(rightTeamNameText.getText(), 
                    "Right team needs a name");
            
            int matchLength = 
                    ((MatchLength)matchLengthCombo.getSelectedItem()).getMinGames();
            if ( match.getTeam(Team.Side.LEFT).getWins() >= matchLength ||
                    match.getTeam(Team.Side.RIGHT).getWins() >= matchLength ) { 
                throw new ValidationException("Match length would end the " + 
                        "match");
            }
            
            GameLength gameLength = 
                    (GameLength)gameLengthCombo.getSelectedItem();
            if ( gameLength != match.getGameLength() ) {
                int leftScore = match.getTeam(Side.LEFT).getScore(); 
                int rightScore = match.getTeam(Side.RIGHT).getScore();
                int gamePoints = gameLength.getPoints();
                if ( leftScore >= gamePoints || rightScore >= gamePoints ) {
                    throw new ValidationException("The game length cannot " + 
                            "be changed");
                }
            }
            
            match.setGameLength((GameLength)gameLengthCombo.getSelectedItem());
            match.setMatchLength((MatchLength)matchLengthCombo.getSelectedItem());
            match.setStyle((Style)styleCombo.getSelectedItem());
            match.getTalker().setVoice((Voice)commentatorCombo.getSelectedItem());
            match.setSubtitled(subtitlesCheck.isSelected());
            Team leftTeam = match.getTeam(Team.Side.LEFT);

            leftTeam.setName(new Speech(leftTeamNameText.getText(), leftTeamSayText.getText()));
            leftTeam.setColor((TeamColor)leftTeamColorCombo.getSelectedItem());

            Team rightTeam = match.getTeam(Team.Side.RIGHT);
            rightTeam.setName(new Speech(rightTeamNameText.getText(), rightTeamSayText.getText()));
            rightTeam.setColor((TeamColor)rightTeamColorCombo.getSelectedItem());
                        
            boolean newGame = !match.isActive();
            
            match.setActive(true);

            this.setVisible(false);

            ScoreboardFrame scoreboardFrame = windowManager.getScoreboardFrame();
            
            scoreboardFrame.applyStyle();
            scoreboardFrame.refresh();
            scoreboardFrame.setVisible(true);
            
            if ( newGame ) {
                match.introductionCommentary();
                scoreboardFrame.repaint();
            }
        } catch ( ValidationException ve ) { 
             JOptionPane.showMessageDialog(this, ve.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);           
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        gameLengthCombo = new javax.swing.JComboBox();
        matchLengthCombo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        styleCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        commentatorCombo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        subtitlesCheck = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        leftTeamNameText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        leftTeamSayText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        leftTeamColorCombo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        rightTeamNameText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rightTeamSayText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        rightTeamColorCombo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        speakLeftButton = new javax.swing.JButton();
        speakRightButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match Settings");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Settings");

        jLabel2.setText("Game Length");

        gameLengthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        matchLengthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Match Length");

        styleCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        styleCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                styleComboItemStateChanged(evt);
            }
        });
        styleCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleComboActionPerformed(evt);
            }
        });

        jLabel4.setText("Style");

        commentatorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Commentator");

        subtitlesCheck.setText("Subtitles");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Left Team");

        jLabel1.setText("Name");

        jLabel9.setText("Say As");

        leftTeamColorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Color");

        jLabel7.setText("Name");

        jLabel10.setText("Say As");

        rightTeamColorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Color");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Right Team");

        speakLeftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/speaker.png"))); // NOI18N
        speakLeftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speakLeftButtonActionPerformed(evt);
            }
        });

        speakRightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/speaker.png"))); // NOI18N
        speakRightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speakRightButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, leftTeamNameText)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, leftTeamColorCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(leftTeamSayText)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(speakLeftButton)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(styleCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(commentatorCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(gameLengthCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, matchLengthCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jSeparator2)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel7)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(rightTeamNameText)
                            .add(rightTeamColorCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(rightTeamSayText)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(speakRightButton)
                                .add(3, 3, 3)))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jSeparator1))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(subtitlesCheck))
                            .add(layout.createSequentialGroup()
                                .add(cancelButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 149, Short.MAX_VALUE)
                                .add(okButton)))
                        .add(13, 13, 13))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(gameLengthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(matchLengthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(styleCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(commentatorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel12)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(leftTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(speakLeftButton)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(leftTeamSayText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel9)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(leftTeamColorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel13)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rightTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(rightTeamSayText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel10))
                    .add(speakRightButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rightTeamColorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(subtitlesCheck)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void styleComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_styleComboItemStateChanged

    }//GEN-LAST:event_styleComboItemStateChanged

    private void styleComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleComboActionPerformed
        updateTeamColorModel();
        Style style = (Style)styleCombo.getSelectedItem();
        if ( style.getTeamColors().size() == 1 ) {
            leftTeamColorCombo.setSelectedIndex(0);
            rightTeamColorCombo.setSelectedIndex(0);
        } else { 
            leftTeamColorCombo.setSelectedIndex(0);
            rightTeamColorCombo.setSelectedIndex(1);
        }
    }//GEN-LAST:event_styleComboActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        update();
    }//GEN-LAST:event_okButtonActionPerformed

    private void speakLeftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speakLeftButtonActionPerformed
        SwingTalker talker = match.getTalker();
        if ( ! Strings.isEmpty(leftTeamSayText.getText()) ) {
            talker.say(leftTeamSayText.getText());
        } else if ( ! Strings.isEmpty(leftTeamNameText.getText()) ) {
            talker.say(leftTeamNameText.getText());
        }
    }//GEN-LAST:event_speakLeftButtonActionPerformed

    private void speakRightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speakRightButtonActionPerformed
        SwingTalker talker = match.getTalker();
        if ( ! Strings.isEmpty(rightTeamSayText.getText()) ) {
            talker.say(rightTeamSayText.getText());
        } else if ( ! Strings.isEmpty(rightTeamNameText.getText()) ) {
            talker.say(rightTeamNameText.getText());
        }
    }//GEN-LAST:event_speakRightButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox commentatorCombo;
    private javax.swing.JComboBox gameLengthCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox leftTeamColorCombo;
    private javax.swing.JTextField leftTeamNameText;
    private javax.swing.JTextField leftTeamSayText;
    private javax.swing.JComboBox matchLengthCombo;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox rightTeamColorCombo;
    private javax.swing.JTextField rightTeamNameText;
    private javax.swing.JTextField rightTeamSayText;
    private javax.swing.JButton speakLeftButton;
    private javax.swing.JButton speakRightButton;
    private javax.swing.JComboBox styleCombo;
    private javax.swing.JCheckBox subtitlesCheck;
    // End of variables declaration//GEN-END:variables
}
