/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.lang.Class;

/**
 *
 * @author noemi
 */
public class MenuProduit extends javax.swing.JFrame {
    //Connection con;
    //PreparedStatement pst;
    
   
    /**
     * Creates new form MenuConnection
     */
    public MenuProduit() {
        initComponents();
        
         String url = "jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1";
    String login = "p2003225";
    String password = "564077";
    Connection conn;

    try{
        System.out.println("test 0");
        Class.forName("oracle.jdbc.OracleDriver");
        System.out.println("test");
        conn = DriverManager.getConnection(url, login, password);
        System.out.println("test 1");
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT NOM_CAT FROM p2003225.CPOA_CATEGORIE");
        System.out.println("test 2");
        while(res.next())
        {
            //System.out.println(enterpwd + ' ' + pwd+ ' ' + enteruser+ ' ' + user);
            System.out.println(res.getString("NOM_CAT"));
            //jComboBox_Categorie.addItem(res.getString("nom_cat").substring(0, 1).toUpperCase() + res.getString("nom_cat").substring(1));
        }
        conn.close();
        System.out.println("fin");
    }catch(Exception e){ 
        System.out.println(e);
        System.out.println("exeption écrite au dessus");
    }
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
        jl_id2 = new javax.swing.JLabel();
        jtf_id2 = new javax.swing.JTextField();
        jp_entete = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jp_connexion = new javax.swing.JPanel();
        jl_id = new javax.swing.JLabel();
        jtf_id = new javax.swing.JTextField();
        jl_libelle = new javax.swing.JLabel();
        jtf_libelle = new javax.swing.JTextField();
        jl_prix = new javax.swing.JLabel();
        jtf_prix = new javax.swing.JTextField();
        jl_categorie = new javax.swing.JLabel();
        jtf_categorie = new javax.swing.JTextField();
        jl_description = new javax.swing.JLabel();
        jtf_description = new javax.swing.JTextField();
        jl_stock = new javax.swing.JLabel();
        jtf_stock = new javax.swing.JTextField();
        jb_ajoutProduit = new javax.swing.JButton();

        jl_id2.setText("id_produit");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jp_entete.setBackground(new java.awt.Color(153, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("produits");

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

        jl_id.setText("id");

        jtf_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_idActionPerformed(evt);
            }
        });

        jl_libelle.setText("libellé");

        jtf_libelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_libelleActionPerformed(evt);
            }
        });

        jl_prix.setText("prix");

        jtf_prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_prixActionPerformed(evt);
            }
        });

        jl_categorie.setText("catégorie");

        jtf_categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_categorieActionPerformed(evt);
            }
        });

        jl_description.setText("description");

        jtf_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_descriptionActionPerformed(evt);
            }
        });

        jl_stock.setText("stock");

        jtf_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_stockActionPerformed(evt);
            }
        });

        jb_ajoutProduit.setText("ajouter");
        jb_ajoutProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ajoutProduitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_connexionLayout = new javax.swing.GroupLayout(jp_connexion);
        jp_connexion.setLayout(jp_connexionLayout);
        jp_connexionLayout.setHorizontalGroup(
            jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_connexionLayout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_connexionLayout.createSequentialGroup()
                        .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_libelle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_prix, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_id, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_prix, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(223, 223, 223))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_connexionLayout.createSequentialGroup()
                        .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_description)
                            .addComponent(jl_categorie)
                            .addComponent(jl_stock))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtf_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_description, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_ajoutProduit)
                            .addComponent(jtf_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(213, 213, 213))
        );
        jp_connexionLayout.setVerticalGroup(
            jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_connexionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_id))
                .addGap(18, 18, 18)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_libelle))
                .addGap(18, 18, 18)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_prix)
                    .addComponent(jtf_prix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_categorie)
                    .addComponent(jtf_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_description)
                    .addComponent(jtf_description, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jp_connexionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_stock))
                .addGap(21, 21, 21)
                .addComponent(jb_ajoutProduit))
        );

        getContentPane().add(jp_connexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 780, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_libelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_libelleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_libelleActionPerformed

    private void jb_ajoutProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ajoutProduitActionPerformed
       //
    }//GEN-LAST:event_jb_ajoutProduitActionPerformed

    private void jtf_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_stockActionPerformed

    private void jtf_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_idActionPerformed

    private void jtf_prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_prixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_prixActionPerformed

    private void jtf_descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_descriptionActionPerformed

    private void jtf_categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_categorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_categorieActionPerformed

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
            java.util.logging.Logger.getLogger(MenuProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuProduit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
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
    private javax.swing.JButton jb_ajoutProduit;
    private javax.swing.JLabel jl_categorie;
    private javax.swing.JLabel jl_description;
    private javax.swing.JLabel jl_id;
    private javax.swing.JLabel jl_id2;
    private javax.swing.JLabel jl_libelle;
    private javax.swing.JLabel jl_prix;
    private javax.swing.JLabel jl_stock;
    private javax.swing.JPanel jp_connexion;
    private javax.swing.JPanel jp_entete;
    private javax.swing.JTextField jtf_categorie;
    private javax.swing.JTextField jtf_description;
    private javax.swing.JTextField jtf_id;
    private javax.swing.JTextField jtf_id2;
    private javax.swing.JTextField jtf_libelle;
    private javax.swing.JTextField jtf_prix;
    private javax.swing.JTextField jtf_stock;
    // End of variables declaration//GEN-END:variables
}
