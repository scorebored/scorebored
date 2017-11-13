package org.blackchip.scorebored;

import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import org.blackchip.scorebored.swing.Swing;
import org.blackchip.scorebored.swing.WindowManager;

public class DashboardFrame extends javax.swing.JFrame {

    private Match match;
    private WindowManager windowManager = WindowManager.getInstance();

    public DashboardFrame(JMenuBar menu, Match match) {
        this.match = match;
        this.setJMenuBar(menu);        
        initComponents();

        this.getRootPane().setDefaultButton(newMatchButton);
        this.setIconImage(Scorebored.getIconImage());
        
        newMatchButton.setAction(Actions.NEW_MATCH);
        newMatchButton.setIcon(Scorebored.PADDLE_ICON);
        newMatchButton.setText("");
        continueMatchButton.setAction(Actions.CONTINUE_MATCH);
        continueMatchButton.setIcon(Scorebored.GREEN_PONG_ICON);
        continueMatchButton.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newMatchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        continueMatchButton = new javax.swing.JButton();
        matchLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");

        newMatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/paddle.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Continue Match");

        continueMatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/green_pong.png"))); // NOI18N
        continueMatchButton.setEnabled(false);

        matchLabel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        matchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        matchLabel.setText("New Match");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(continueMatchButton)
                        .add(18, 18, 18)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(newMatchButton)
                        .add(18, 18, 18)
                        .add(matchLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(newMatchButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(matchLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(continueMatchButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueMatchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel matchLabel;
    private javax.swing.JButton newMatchButton;
    // End of variables declaration//GEN-END:variables
}
