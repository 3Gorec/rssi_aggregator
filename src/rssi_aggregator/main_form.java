/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rssi_aggregator;
import com.google.protobuf.ByteString;
import javax.swing.*;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;


/**
 *
 * @author gorec
 */
public class main_form extends javax.swing.JFrame {        
    RSSIAggregator aggregator;       
    ByteString mac_filter;
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

    
    private void UpdateOutFileLabel(){
        jLabelOutFileTemplate.setText(jTextFieldOutFileTemplate.getText()+"_"+jSpinnerX.getValue().toString()+"_"+jSpinnerY.getValue().toString()+".txt");
    }
     /* This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel_SnifferClient = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelSnifferStatus = new javax.swing.JLabel();
        jLabel_status = new javax.swing.JLabel();
        jButton_Start = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerInterval = new javax.swing.JSpinner();
        jSpinnerPeriod = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jSpinnerX = new javax.swing.JSpinner();
        jSpinnerY = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldOutFileTemplate = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabelOutFileTemplate = new javax.swing.JLabel();
        jTextFieldMAC = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckUseFilter = new javax.swing.JCheckBox();

        jDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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
                .addComponent(jLabelSnifferStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
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

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Pending period:");

        jSpinnerInterval.setModel(new javax.swing.SpinnerNumberModel(10, 0, 10000, 1));

        jSpinnerPeriod.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel5.setText("s");

        jSpinnerX.setModel(new javax.swing.SpinnerNumberModel(0, -1000, 1000, 1));
        jSpinnerX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerXStateChanged(evt);
            }
        });

        jSpinnerY.setModel(new javax.swing.SpinnerNumberModel(0, -1000, 1000, 1));
        jSpinnerY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerYStateChanged(evt);
            }
        });

        jLabel6.setText("x:");

        jLabel7.setText("y:");

        jTextFieldOutFileTemplate.setText("captured");
        jTextFieldOutFileTemplate.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldOutFileTemplateCaretUpdate(evt);
            }
        });

        jLabel8.setText("Name template:");

        jLabelOutFileTemplate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelOutFileTemplate.setText("captured_0_0.txt");

        jTextFieldMAC.setText("00:00:00:00:00:00");
        jTextFieldMAC.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldMAC.setEnabled(false);

        jLabel9.setText("MAC filter:");

        jCheckUseFilter.setText("Use Mac Filter");
        jCheckUseFilter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckUseFilterStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_SnifferClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_Start)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerPeriod)
                            .addComponent(jSpinnerInterval))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextFieldMAC, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelOutFileTemplate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldOutFileTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jCheckUseFilter)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Start)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinnerPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldOutFileTemplate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckUseFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOutFileTemplate)
                    .addComponent(jTextFieldMAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jPanel_SnifferClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_status))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_StartActionPerformed
        int status;        
        
        if(aggregator.running_flag==false){            
            int sniffing_interval=((Number)jSpinnerInterval.getValue()).intValue();
            int pending_period=((Number)jSpinnerPeriod.getValue()).intValue();
            Mac mac_filter=null;
            
            if(jCheckUseFilter.isSelected()){
                mac_filter=new Mac(jTextFieldMAC.getText());
            }
            
            if(sniffing_interval>0){
                status=aggregator.Start(sniffing_interval,pending_period,jLabelOutFileTemplate.getText(), mac_filter);
                if(status==0){
                    jButton_Start.setText("Stop");
                    jSpinnerInterval.setEnabled(false);                             
                }                
            }            
        }
        else{
            resetAggregatorState();
        }
    }//GEN-LAST:event_jButton_StartActionPerformed

    private void jSpinnerXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerXStateChanged
        UpdateOutFileLabel();
    }//GEN-LAST:event_jSpinnerXStateChanged

    private void jSpinnerYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerYStateChanged
        UpdateOutFileLabel();
    }//GEN-LAST:event_jSpinnerYStateChanged

    private void jTextFieldOutFileTemplateCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldOutFileTemplateCaretUpdate
        UpdateOutFileLabel();
        
    }//GEN-LAST:event_jTextFieldOutFileTemplateCaretUpdate

    private void jCheckUseFilterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckUseFilterStateChanged
        if(jCheckUseFilter.isSelected()){
            jTextFieldMAC.setEnabled(true);
        }
        else{
            jTextFieldMAC.setEnabled(false);
            jTextFieldMAC.setText("00:00:00:00:00:00");
        }
    }//GEN-LAST:event_jCheckUseFilterStateChanged

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
    private javax.swing.JCheckBox jCheckUseFilter;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelOutFileTemplate;
    private javax.swing.JLabel jLabelSnifferStatus;
    private javax.swing.JLabel jLabel_status;
    private javax.swing.JPanel jPanel_SnifferClient;
    private javax.swing.JSpinner jSpinnerInterval;
    private javax.swing.JSpinner jSpinnerPeriod;
    private javax.swing.JSpinner jSpinnerX;
    private javax.swing.JSpinner jSpinnerY;
    private javax.swing.JTextField jTextFieldMAC;
    private javax.swing.JTextField jTextFieldOutFileTemplate;
    // End of variables declaration//GEN-END:variables
}
