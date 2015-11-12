/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;
import javax.swing.*;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author gorec
 */
public class main_form extends javax.swing.JFrame {
    
    private boolean sniffing_in_process=false;    
    RSSIAggregator aggregator;
    private int sniffing_interval;
    /**
     * Creates new form main_form
     */
    public main_form() {        
        aggregator=new RSSIAggregator(this);
        initComponents();             
    }

    public void decIntCounter(){
        int counter=((Number)jSpinnerInterval.getValue()).intValue();
        Number i=counter;        
        if(counter>0){
            --counter;
            jSpinnerInterval.setValue((Object)counter);
        }
        else{
            
        }
    }
        
    public void resetAggregatorState(){        
        jButton_Start.setText("Start");
        jSpinnerInterval.setEnabled(true);
        jSpinnerInterval.setValue((Object)10);
        int status=aggregator.Stop();            
    }
    
    public void outputStatus(String text){
        jLabel_status.setText(text);
    }    

    public void setSnifferStatus(String text){
        jLabelSnifferStatus.setText(text);
    } 
     /* This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_SnifferClient = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelSnifferStatus = new javax.swing.JLabel();
        jLabel_status = new javax.swing.JLabel();
        jButton_Start = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextPendingPeriod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerInterval = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_SnifferClient.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Status:");

        javax.swing.GroupLayout jPanel_SnifferClientLayout = new javax.swing.GroupLayout(jPanel_SnifferClient);
        jPanel_SnifferClient.setLayout(jPanel_SnifferClientLayout);
        jPanel_SnifferClientLayout.setHorizontalGroup(
            jPanel_SnifferClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SnifferClientLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel2)
                .addGap(73, 73, 73)
                .addComponent(jLabelSnifferStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_SnifferClientLayout.setVerticalGroup(
            jPanel_SnifferClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SnifferClientLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel_SnifferClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelSnifferStatus))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton_Start.setText("Start");
        jButton_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_StartActionPerformed(evt);
            }
        });

        jLabel3.setText("Sniffing interval:");

        jLabel4.setText("s");

        jTextPendingPeriod.setEnabled(false);
        jTextPendingPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPendingPeriodActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Pending period:");

        jSpinnerInterval.setModel(new javax.swing.SpinnerNumberModel(10, 0, 10000, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_SnifferClient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Start)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSpinnerInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(jTextPendingPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Start)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextPendingPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_SnifferClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jLabel_status))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_StartActionPerformed
        int status;        
        if(aggregator.running_flag==false){            
            sniffing_interval=((Number)jSpinnerInterval.getValue()).intValue();
            if(sniffing_interval>0){
                status=aggregator.Start(sniffing_interval);
                if(status==0){
                    jButton_Start.setText("Stop");
                    jSpinnerInterval.setEnabled(false);                             
                }                
            }
            else{
                outputStatus("Invalid sniffing interval");
            }
        }
        else{
            resetAggregatorState();
        }
    }//GEN-LAST:event_jButton_StartActionPerformed

    private void jTextPendingPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPendingPeriodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPendingPeriodActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Start;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelSnifferStatus;
    private javax.swing.JLabel jLabel_status;
    private javax.swing.JPanel jPanel_SnifferClient;
    private javax.swing.JSpinner jSpinnerInterval;
    private javax.swing.JTextField jTextPendingPeriod;
    // End of variables declaration//GEN-END:variables
}
