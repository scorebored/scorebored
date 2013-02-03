package us.praefectus.scorebored;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import us.praefectus.scorebored.Team.Side;
import us.praefectus.scorebored.util.Validate;
import us.praefectus.scorebored.util.ValidationException;

public class AdjustmentDialog extends javax.swing.JDialog {

    private Match match;
    private Team leftTeam;
    private Team rightTeam;
    
    public AdjustmentDialog(Match match) {
        super((JFrame)null, true);
        this.match = match;
        
        initComponents();

        leftTeam = match.getTeam(Side.LEFT);
        rightTeam = match.getTeam(Side.RIGHT);

        if ( match.getMatchLength() == MatchLength.ONE ) { 
            leftTeamWinsText.setVisible(false);
            rightTeamWinsText.setVisible(false);
        }
        
        leftTeamNameLabel.setText(leftTeam.getName());
        leftTeamScoreText.setText(String.valueOf(leftTeam.getScore()));
        leftTeamWinsText.setText(String.valueOf(leftTeam.getWins()));
        if ( match.getServer() == Side.LEFT ) { 
            leftTeamServerRadio.setSelected(true);
        }

        rightTeamNameLabel.setText(rightTeam.getName());
        rightTeamScoreText.setText(String.valueOf(rightTeam.getScore()));
        rightTeamWinsText.setText(String.valueOf(rightTeam.getWins()));
        if ( match.getServer() == Side.RIGHT ) { 
            rightTeamServerRadio.setSelected(true);
        }        
    }

    private void update() {
        try {
            int leftTeamScore = Validate.isInteger(leftTeamScoreText.getText(), 
                    "Invalid score for " + leftTeam.getName());
            Validate.notNegative(leftTeamScore, 
                    "Invalid score for " + leftTeam.getName());
            int rightTeamScore = Validate.isInteger(rightTeamScoreText.getText(), 
                    "Invalid score for " + rightTeam.getName());
            Validate.notNegative(rightTeamScore, 
                    "Invalid score for " + rightTeam.getName());
            int leftTeamWins = Validate.isInteger(leftTeamWinsText.getText(), 
                    "Invalid wins for " + leftTeam.getName());
            if ( leftTeamWins < 0 || leftTeamWins >= 
                    match.getMatchLength().getMaxGames() ) {
                throw new ValidationException("Invalid wins for " + 
                        leftTeam.getName());
            }
            int rightTeamWins = Validate.isInteger(rightTeamWinsText.getText(), 
                    "Invalid wins for " + rightTeam.getName());
            if ( rightTeamWins < 0 || rightTeamWins >= 
                    match.getMatchLength().getMaxGames() ) { 
                throw new ValidationException("Invalid wins for " + 
                        rightTeam.getName());
            }
            
            leftTeam.setScore(leftTeamScore);
            leftTeam.setWins(leftTeamWins);
            if ( leftTeamServerRadio.isSelected() ) {
                match.setServer(Team.Side.LEFT);
            }
            rightTeam.setScore(rightTeamScore);
            rightTeam.setWins(rightTeamWins);
            if ( rightTeamServerRadio.isSelected() ) {
                match.setServer(Team.Side.RIGHT);
            }
            match.clearHistory();
            this.setVisible(false);
            this.dispose();
        } catch ( ValidationException ve ) {
            JOptionPane.showMessageDialog(this, ve.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        serverButtonGroup = new javax.swing.ButtonGroup();
        leftTeamScoreText = new javax.swing.JTextField();
        leftTeamNameLabel = new javax.swing.JLabel();
        rightTeamNameLabel = new javax.swing.JLabel();
        rightTeamScoreText = new javax.swing.JTextField();
        leftTeamServerRadio = new javax.swing.JRadioButton();
        rightTeamServerRadio = new javax.swing.JRadioButton();
        leftTeamWinsText = new javax.swing.JTextField();
        rightTeamWinsText = new javax.swing.JTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adjust Match");

        leftTeamScoreText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        leftTeamScoreText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        leftTeamScoreText.setText("99");

        leftTeamNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        leftTeamNameLabel.setText("Home Team");

        rightTeamNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightTeamNameLabel.setText("Away Team");

        rightTeamScoreText.setFont(new java.awt.Font("Inconsolata", 1, 48)); // NOI18N
        rightTeamScoreText.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rightTeamScoreText.setText("99");

        serverButtonGroup.add(leftTeamServerRadio);
        leftTeamServerRadio.setLabel("Server");

        serverButtonGroup.add(rightTeamServerRadio);
        rightTeamServerRadio.setLabel("Server");

        leftTeamWinsText.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        leftTeamWinsText.setText("9");

        rightTeamWinsText.setText("9");

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
                    .add(layout.createSequentialGroup()
                        .add(leftTeamServerRadio)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(leftTeamWinsText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .add(leftTeamScoreText)
                    .add(leftTeamNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(cancelButton)
                        .add(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, rightTeamScoreText)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(rightTeamWinsText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(rightTeamServerRadio)
                        .add(5, 5, 5))
                    .add(rightTeamNameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(leftTeamNameLabel)
                    .add(rightTeamNameLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(rightTeamScoreText)
                    .add(leftTeamScoreText, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(rightTeamServerRadio)
                    .add(leftTeamServerRadio)
                    .add(leftTeamWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rightTeamWinsText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(okButton)
                    .add(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        update();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel leftTeamNameLabel;
    private javax.swing.JTextField leftTeamScoreText;
    private javax.swing.JRadioButton leftTeamServerRadio;
    private javax.swing.JTextField leftTeamWinsText;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel rightTeamNameLabel;
    private javax.swing.JTextField rightTeamScoreText;
    private javax.swing.JRadioButton rightTeamServerRadio;
    private javax.swing.JTextField rightTeamWinsText;
    private javax.swing.ButtonGroup serverButtonGroup;
    // End of variables declaration//GEN-END:variables
}
