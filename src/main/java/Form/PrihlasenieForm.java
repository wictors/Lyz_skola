package Form;

import Dao.*;
import Entity.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCrypt;
import sk.upjs.ics.lyz_skola.ObjectFactory;
import javax.swing.*;


public class PrihlasenieForm extends javax.swing.JFrame {

    private InstruktorDao instruktorDao = ObjectFactory.INSTANCE.getInstruktorDao();
    private PrihlasenieDao prihlasenieDao = ObjectFactory.INSTANCE.getPrihlasenieDao();
    
    public PrihlasenieForm() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailTextField = new javax.swing.JTextField();
        hesloTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        hesloLabel = new javax.swing.JLabel();
        prihlasitButton = new javax.swing.JButton();
        zrusitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(300, 150));
        setResizable(false);

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 0, 51));
        emailLabel.setText("Email  : ");

        hesloLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hesloLabel.setForeground(new java.awt.Color(255, 0, 0));
        hesloLabel.setText("Heslo :");

        prihlasitButton.setText("Prihlásiť sa");
        prihlasitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prihlasitButtonActionPerformed(evt);
            }
        });

        zrusitButton.setText("Zrušiť");
        zrusitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zrusitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prihlasitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zrusitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(hesloLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hesloTextField)
                            .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hesloLabel)
                    .addComponent(hesloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prihlasitButton)
                    .addComponent(zrusitButton))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zrusitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zrusitButtonActionPerformed
        dispose();
    }//GEN-LAST:event_zrusitButtonActionPerformed

    private void prihlasitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prihlasitButtonActionPerformed
        if(!emailTextField.getText().equals(" ")){
            Instruktor instruktor = instruktorDao.podlaEmailu(emailTextField.getText());
            if(instruktor != null){
                String vstupneHeslo = hesloTextField.getText();
                String heslo = instruktor.getHeslo();
                if(BCrypt.checkpw(vstupneHeslo, heslo)){
                    Prihlasenie prihlasenie = new Prihlasenie();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    prihlasenie.setEmail(emailTextField.getText());
                    prihlasenie.setDatum(dateFormat.format(new Date()));
                    prihlasenie.setStav("Úspešné");
                    prihlasenieDao.ulozPrihlasenie(prihlasenie);
                    UvodnyPanelForm uvodnyPanel = new UvodnyPanelForm();
                    uvodnyPanel.setVisible(true);
                    this.dispose();
                }else{
                    Prihlasenie prihlasenie = new Prihlasenie();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    prihlasenie.setEmail(emailTextField.getText());
                    prihlasenie.setDatum(dateFormat.format(new Date()));
                    prihlasenie.setStav("Neúspešné");
                    prihlasenieDao.ulozPrihlasenie(prihlasenie);
                                        
                }
            }
        }
    }//GEN-LAST:event_prihlasitButtonActionPerformed

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
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
                  javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                  
           }           
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrihlasenieForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrihlasenieForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrihlasenieForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrihlasenieForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrihlasenieForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel hesloLabel;
    private javax.swing.JTextField hesloTextField;
    private javax.swing.JButton prihlasitButton;
    private javax.swing.JButton zrusitButton;
    // End of variables declaration//GEN-END:variables
}
