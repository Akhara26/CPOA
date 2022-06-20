/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author noemi
 */
public class MenuConnection extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form MenuConnection
     */
    public MenuConnection() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jp_entete = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jp_connexion = new javax.swing.JPanel();
        jl_id = new javax.swing.JLabel();
        jl_mdp = new javax.swing.JLabel();
        jtf_id = new javax.swing.JTextField();
        jpf_mdp = new javax.swing.JPasswordField();
        jb_connexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_entete.setBackground(new java.awt.Color(153, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("connexion");

        javax.swing.GroupLayout jp_enteteLayout = new javax.swing.GroupLayout(jp_entete);
        jp_entete.setLayout(jp_enteteLayout);
        jp_enteteLayout.setHorizontalGroup(
            jp_enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
        );
        jp_enteteLayout.setVerticalGroup(
            jp_enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_enteteLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        getContentPane().add(jp_entete, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        jl_id.setText("identifiant");

        jl_mdp.setText("mdp");

        jtf_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_idActionPerformed(evt);
            }
        });

        jpf_mdp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpf_mdpActionPerformed(evt);
            }
        });

        jb_connexion.setText("jButton1");
        jb_connexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_connexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_connexionLayout = new javax.swing.GroupLayout(jp_connexion);
        jp_connexion.setLayout(jp_connexionLayout);
        jp_connexionLayout.setHorizontalGroup(
            jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_connexionLayout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jl_mdp)
                    .addComponent(jl_id))
                .addGap(51, 51, 51)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpf_mdp)
                    .addComponent(jtf_id))
                .addContainerGap(285, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_connexionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jb_connexion)
                .addGap(334, 334, 334))
        );
        jp_connexionLayout.setVerticalGroup(
            jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_connexionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_id)
                    .addComponent(jtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_mdp)
                    .addComponent(jpf_mdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_connexion)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jp_connexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 780, 160));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_idActionPerformed

    private void jpf_mdpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpf_mdpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpf_mdpActionPerformed

    private void jb_connexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_connexionActionPerformed
        
    }//GEN-LAST:event_jb_connexionActionPerformed

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
            java.util.logging.Logger.getLogger(MenuConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuConnection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton jb_connexion;
    private javax.swing.JLabel jl_id;
    private javax.swing.JLabel jl_mdp;
    private javax.swing.JPanel jp_connexion;
    private javax.swing.JPanel jp_entete;
    private javax.swing.JPasswordField jpf_mdp;
    private javax.swing.JTextField jtf_id;
    // End of variables declaration//GEN-END:variables
}