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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(300, 150));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(emailTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 56, 160, -1));
        getContentPane().add(hesloTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 94, 160, -1));

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 0, 51));
        emailLabel.setText("Email : ");
        getContentPane().add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 82, -1));

        hesloLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hesloLabel.setForeground(new java.awt.Color(255, 0, 0));
        hesloLabel.setText("Heslo : ");
        getContentPane().add(hesloLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 80, -1));

        prihlasitButton.setText("Prihlásiť sa");
        prihlasitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prihlasitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(prihlasitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 100, -1));

        zrusitButton.setText("Zrušiť");
        zrusitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zrusitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(zrusitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 132, 100, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Silvia\\Desktop\\obrazky NETBeans\\prihlasenie_uprava.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 220));

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton prihlasitButton;
    private javax.swing.JButton zrusitButton;
    // End of variables declaration//GEN-END:variables
}
