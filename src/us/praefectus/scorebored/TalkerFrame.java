package us.praefectus.scorebored;

import javax.swing.DefaultComboBoxModel;
import us.praefectus.scorebored.talker.Speech;
import us.praefectus.scorebored.talker.TalkListener;
import us.praefectus.scorebored.talker.SwingTalker;
import us.praefectus.scorebored.talker.Voice;
import us.praefectus.scorebored.util.Strings;


public class TalkerFrame extends javax.swing.JFrame {
    
    private SwingTalker talker;
    
    public TalkerFrame(SwingTalker talkService) {
        this.talker = talkService;
        initComponents();
        voiceCombo.setModel(new DefaultComboBoxModel(
                talker.getVoices().toArray(new Voice[] {})));
        voiceCombo.setSelectedItem(talker.getVoice());
        talkText.setText("");
        talkText.requestFocusInWindow();
        this.getRootPane().setDefaultButton(speakButton);
        
        talker.addListener(new TalkListener() {
            @Override
            public void talkStarted(Speech speech) {
                silenceButton.setEnabled(true);
            }

            @Override
            public void talkEnded() {
                silenceButton.setEnabled(false);
            }
        });
    }
    
    private void talk() {
        if ( Strings.isEmpty(talkText.getText()) ) { 
            return;
        }
        talker.say(talkText.getText());      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        voiceCombo = new javax.swing.JComboBox();
        talkText = new javax.swing.JTextField();
        speakButton = new javax.swing.JButton();
        silenceButton = new javax.swing.JButton();

        setTitle("Commentator");

        jLabel1.setText("Voice:");

        voiceCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        voiceCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                voiceComboItemStateChanged(evt);
            }
        });

        talkText.setText("Speech Text");
        talkText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                talkTextActionPerformed(evt);
            }
        });

        speakButton.setText("Speak");
        speakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speakButtonActionPerformed(evt);
            }
        });

        silenceButton.setText("Silence");
        silenceButton.setEnabled(false);
        silenceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silenceButtonActionPerformed(evt);
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
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(voiceCombo, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(talkText)
                    .add(layout.createSequentialGroup()
                        .add(silenceButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 219, Short.MAX_VALUE)
                        .add(speakButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(voiceCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(talkText, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(speakButton)
                    .add(silenceButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voiceComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_voiceComboItemStateChanged
        talker.setVoice((Voice)voiceCombo.getSelectedItem());
    }//GEN-LAST:event_voiceComboItemStateChanged

    private void talkTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_talkTextActionPerformed
        talk();
    }//GEN-LAST:event_talkTextActionPerformed

    private void speakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speakButtonActionPerformed
        talk();
    }//GEN-LAST:event_speakButtonActionPerformed

    private void silenceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silenceButtonActionPerformed
        talker.silence();
    }//GEN-LAST:event_silenceButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton silenceButton;
    private javax.swing.JButton speakButton;
    private javax.swing.JTextField talkText;
    private javax.swing.JComboBox voiceCombo;
    // End of variables declaration//GEN-END:variables
}
