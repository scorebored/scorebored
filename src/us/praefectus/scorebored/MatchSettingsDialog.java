package us.praefectus.scorebored;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import us.praefectus.scorebored.Team.Side;
import us.praefectus.scorebored.swing.WindowManager;
import us.praefectus.scorebored.talker.Voice;
import us.praefectus.scorebored.util.Validate;
import us.praefectus.scorebored.util.ValidationException;

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
        leftTeamNameText.setText(leftTeam.getName());
        leftTeamColorCombo.setSelectedItem(leftTeam.getColor());
        
        Team rightTeam = match.getTeam(Team.Side.RIGHT);
        rightTeamNameText.setText(rightTeam.getName());
        rightTeamColorCombo.setSelectedItem(rightTeam.getColor());
        
        subtitlesCheck.setSelected(match.isSubtitled());
        
        this.getRootPane().setDefaultButton(okButton);
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
            leftTeam.setName(leftTeamNameText.getText());
            leftTeam.setColor((TeamColor)leftTeamColorCombo.getSelectedItem());

            Team rightTeam = match.getTeam(Team.Side.RIGHT);
            rightTeam.setName(rightTeamNameText.getText());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        leftTeamNameText = new javax.swing.JTextField();
        leftTeamColorCombo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        styleCombo = new javax.swing.JComboBox();
        commentatorCombo = new javax.swing.JComboBox();
        gameLengthCombo = new javax.swing.JComboBox();
        matchLengthCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        subtitlesCheck = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        rightTeamNameText = new javax.swing.JTextField();
        rightTeamColorCombo = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Match Settings");
        setPreferredSize(new java.awt.Dimension(325, 535));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Left Team"));

        jLabel1.setText("Name");

        leftTeamColorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Color");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel6)
                        .add(20, 20, 20)
                        .add(leftTeamColorCombo, 0, 523, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(18, 18, 18)
                        .add(leftTeamNameText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(leftTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(leftTeamColorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanel2.setPreferredSize(new java.awt.Dimension(569, 220));

        jLabel5.setText("Commentator");

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

        commentatorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        gameLengthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        matchLengthCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Style");

        jLabel2.setText("Game Length");

        jLabel3.setText("Match Length");

        subtitlesCheck.setText("Subtitles");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(subtitlesCheck)
                .addContainerGap())
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel3)
                                .add(jLabel2))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(gameLengthCombo, 0, 448, Short.MAX_VALUE)
                                .add(matchLengthCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel4)
                                .add(jLabel5))
                            .add(11, 11, 11)
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(commentatorCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(styleCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .add(subtitlesCheck)
                .addContainerGap())
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(gameLengthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel2))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(matchLengthCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(styleCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel4))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(commentatorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel5))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Right Team"));

        jLabel7.setText("Name");

        rightTeamColorCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Color");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel8)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel7)
                        .add(18, 18, 18)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(rightTeamNameText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                            .add(rightTeamColorCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(rightTeamNameText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rightTeamColorCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 30, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .addContainerGap())
        );

        pack();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox commentatorCombo;
    private javax.swing.JComboBox gameLengthCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox leftTeamColorCombo;
    private javax.swing.JTextField leftTeamNameText;
    private javax.swing.JComboBox matchLengthCombo;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox rightTeamColorCombo;
    private javax.swing.JTextField rightTeamNameText;
    private javax.swing.JComboBox styleCombo;
    private javax.swing.JCheckBox subtitlesCheck;
    // End of variables declaration//GEN-END:variables
}
