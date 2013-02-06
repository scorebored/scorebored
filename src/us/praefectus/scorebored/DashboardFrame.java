package us.praefectus.scorebored;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import us.praefectus.scorebored.swing.Swing;
import us.praefectus.scorebored.swing.WindowManager;

public class DashboardFrame extends javax.swing.JFrame {

    private Match match;
    private WindowManager windowManager = WindowManager.getInstance();

    public DashboardFrame(Match match) {
        this.match = match;
        initComponents();
        match.addListener(new MatchListener() {
            @Override
            public void matchStarted() {
                matchLabel.setText("Resume Match");
                resetMatchButton.setEnabled(true);
            }

            @Override
            public void matchEnded() {
                matchLabel.setText("New Match");
                resetMatchButton.setEnabled(false);
            }
        });
        this.getRootPane().setDefaultButton(scoreboardButton);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jacobExcuseButton = new javax.swing.JButton();
        scoreboardButton = new javax.swing.JButton();
        commentatorButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        resetMatchButton = new javax.swing.JButton();
        matchLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");

        jacobExcuseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/jacob.png"))); // NOI18N
        jacobExcuseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jacobExcuseButtonActionPerformed(evt);
            }
        });

        scoreboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paddle.png"))); // NOI18N
        scoreboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreboardButtonActionPerformed(evt);
            }
        });

        commentatorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/microphone.png"))); // NOI18N
        commentatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentatorButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Reset Match");

        jLabel2.setText("Commentator");

        jLabel3.setText("Jacob Excuse");

        resetMatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset.png"))); // NOI18N
        resetMatchButton.setEnabled(false);
        resetMatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetMatchButtonActionPerformed(evt);
            }
        });

        matchLabel.setText("New Match");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(scoreboardButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(matchLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(resetMatchButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 111, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jacobExcuseButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(commentatorButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {jLabel1, matchLabel}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(scoreboardButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(commentatorButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(matchLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jacobExcuseButton)
                    .add(resetMatchButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scoreboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreboardButtonActionPerformed
        if ( match.isActive() ) { 
            windowManager.getScoreboardFrame().setVisible(true);
        } else { 
            JDialog dialog = new MatchSettingsDialog(match);
            Swing.centerOnScreen(dialog);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_scoreboardButtonActionPerformed

    private void commentatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentatorButtonActionPerformed
        windowManager.getTalkerFrame().setVisible(true);
    }//GEN-LAST:event_commentatorButtonActionPerformed

    private void resetMatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetMatchButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Reset current match?", 
                "Match Reset", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        if ( result == JOptionPane.YES_OPTION ) { 
            match.reset();
        }
    }//GEN-LAST:event_resetMatchButtonActionPerformed

    private void jacobExcuseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jacobExcuseButtonActionPerformed
        windowManager.getJacobExcusesFrame().setVisible(true);
    }//GEN-LAST:event_jacobExcuseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton commentatorButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jacobExcuseButton;
    private javax.swing.JLabel matchLabel;
    private javax.swing.JButton resetMatchButton;
    private javax.swing.JButton scoreboardButton;
    // End of variables declaration//GEN-END:variables
}
