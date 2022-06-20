/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package application;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author noemi
 */
public class FenetreCategories extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rset;
    
    
    //liste des catégories pour jcb_categories
    String[] listeCategories = listeColonne("CPOA_CATEGORIE","NOM_CAT");
    DefaultComboBoxModel m_listeCategories = new DefaultComboBoxModel(listeCategories);
    
    /*
    connexion à la BD !
    */
    public void connection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1","p2003225","564077");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FenetreCategories.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FenetreCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    /*
    donne le nombre de lignes de la table passée en paramètre
    */
    public int nombreLignes(String nomTable) {
        Connection conNb;
        Statement stmNb;
        ResultSet resNb;
        int total = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conNb = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1","p2003225","564077");
            String query = "SELECT count(*) as nblignes FROM P2003225." + nomTable;
            stmNb = conNb.createStatement();
            resNb = stmNb.executeQuery(query);
            while (resNb.next()){
                total = resNb.getInt("nblignes");
            }
            stmNb.execute(query);
            conNb.close();
            stmNb.close();
            resNb.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"erreur recherche nombre de produits : " + e.getMessage());}
        }
        return total;
    }
    
    /*
    donne une liste, la liste des éléments de la colonne de la table passées en paramètres
    */
    public String[] listeColonne(String table, String colonne){
        String liste[] = new String[nombreLignes(table)];
        Connection conLC;
        Statement stmLC;
        ResultSet resLC;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conLC = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1","p2003225","564077");
            String query = "SELECT " + colonne + " FROM P2003225." + table;
            stmLC = conLC.createStatement();
            resLC = stmLC.executeQuery(query);
            int i=0;
            while (resLC.next()){
                liste[i] = resLC.getString(colonne);
                i++;
            }
            stmLC.execute(query);
            conLC.close();
            stmLC.close();
            resLC.close();
            return liste;
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"erreur affichage liste : " + e.getMessage());}
        }
        
        return liste;
    }    
    
    /*
    select COLONNE from TABLE where COND1 = COND2
    */
    public String itemSelectionne(String colonne, String table, String cond1, String cond2) {
        Connection conIS;
        Statement stmIS;
        ResultSet resIS;
        String item = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conIS = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1","p2003225","564077");
            String query = "SELECT "+colonne+" as item FROM P2003225."+table+" where "+cond1+" = '"+cond2+"'";
            stmIS = conIS.createStatement();
            resIS = stmIS.executeQuery(query);
            while (resIS.next()){
                item = resIS.getString("item");
            }
            stmIS.execute(query);
            conIS.close();
            stmIS.close();
            resIS.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"erreur fonction item : " + e.getMessage());}
        }
        return item;
    
    }
    
    /*
    méthode du tableau d'affichage des produits
    */
    public void affichageListeCategories() {
        try {
            connection();
            String query = "SELECT ID_CAT, NOM_CAT FROM P2003225.CPOA_CATEGORIE";
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);
            String columns[] = { "ID catégorie ", "nom catégorie" };
            String data[][] = new String[nombreLignes("CPOA_CATEGORIE")][2];
            int i = 0;
            while (res.next()) {
                int id = res.getInt("ID_CAT");
                String nom = res.getString("NOM_CAT");
                data[i][0] = id + "";
                data[i][1] = nom;
                i++;
            }
            DefaultTableModel model = new DefaultTableModel(data, columns);
            jt_categories.setModel(model);
            jt_categories.setShowGrid(true);
            jt_categories.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(jt_categories);
            JPanel panel = new JPanel();
            panel.add(pane);
            //pst.setString(1, jtf_description.getText());
            stm.execute(query);
            con.close();
            jt_categories.setDefaultEditor(Object.class, null);
            stm.close();
            res.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"erreur affichage tableau : " + e.getMessage());}
        }
    }
    
    /*
    ajoute un produit dans la BDD (l'incrémentation de l'ID est automatisée)
    */
    public void ajoutCategorie() {
        //connexion à la BD
        connection();

        try {
            String sql = "INSERT INTO P2003225.CPOA_CATEGORIE (NOM_CAT) VALUES (?)";
            pst = con.prepareStatement(sql);
            pst.setString(1,  jtf_categorie.getText() );
            pst.execute();
            con.close();
            
            JOptionPane.showMessageDialog(null,"enregistrement de la catégorie "+jtf_categorie.getText()+" réussi");
            
            //fermeture
            pst.close();
            rset.close();
            
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"veuillez remplir le champ pour ajouter une catégorie");}
        }
    }
    
    /*
    modifie une ligne de la table CPOA_CATEGORIE
    */
    public void modifierCategorie( ){
        connection();

        try {
            String sql = "UPDATE P2003225.CPOA_CATEGORIE SET NOM_CAT = ? WHERE ID_CAT = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, jtf_categorie.getText());
            pst.setInt(2, Integer.parseInt(itemSelectionne("ID_CAT", "CPOA_CATEGORIE", "NOM_CAT", (String) jcb_categorie.getSelectedItem())));
           
            pst.execute();
            con.close();
            
            JOptionPane.showMessageDialog(null,"mise à jour réussie");
            
            //fermeture
            pst.close();
            rset.close();
            
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"veuillez renseigner le nouveau nom de la catégorie " + e.getMessage());}
        }
    }
    
    /*
    supprime une ligne de la table CPOA_CATEGORIE
    */
    public void supprimerCategorie(){
        connection();

        try {
            String sql = "DELETE FROM P2003225.CPOA_CATEGORIE WHERE ID_CAT = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(itemSelectionne("ID_CAT", "CPOA_CATEGORIE", "NOM_CAT", (String) jcb_categorie.getSelectedItem())));
            pst.execute();
            con.close();
            JOptionPane.showMessageDialog(null,"suppression réussie");
            
            //fermeture
            pst.close();
            rset.close();
            
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"la catégorie a déjà été supprimée");}
        }
        
    }
    
    public void majAffichage(JToggleButton jtg){
        if (jtg == jtg_modifier){
            jtg_ajouter.setSelected(false);
            jtg_supprimer.setSelected(false);
        }else if(jtg == jtg_ajouter){
            jtg_modifier.setSelected(false);
            jtg_supprimer.setSelected(false);
        }else if (jtg == jtg_supprimer){
            jtg_modifier.setSelected(false);
            jtg_ajouter.setSelected(false);
        }
        
        if (!jtg_supprimer.isSelected()&& !jtg_modifier.isSelected() && !jtg_ajouter.isSelected()){
            jb_valider.setEnabled(false);
            jcb_categorie.setEnabled(false);
            jtf_categorie.setEnabled(false);
        }else{
            jb_valider.setEnabled(true);
        }
    }
    
    /**
     * Creates new form Fenetre
     */
    public FenetreCategories() {
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

        jp_categories = new javax.swing.JPanel();
        jl_categorie = new javax.swing.JLabel();
        jcb_categorie = new javax.swing.JComboBox<>();
        jb_valider = new javax.swing.JButton();
        jtg_ajouter = new javax.swing.JToggleButton();
        jtg_modifier = new javax.swing.JToggleButton();
        jtg_supprimer = new javax.swing.JToggleButton();
        jtf_categorie = new javax.swing.JTextField();
        jp_entete = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jt_categories = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion Jardin d'Enfants");
        setSize(new java.awt.Dimension(800, 600));

        jl_categorie.setText("nom catégorie");

        jcb_categorie.setModel(m_listeCategories);
        jcb_categorie.setEnabled(false);
        jcb_categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_categorieActionPerformed(evt);
            }
        });

        jb_valider.setBackground(new java.awt.Color(153, 0, 0));
        jb_valider.setForeground(new java.awt.Color(255, 255, 255));
        jb_valider.setText("valider");
        jb_valider.setEnabled(false);
        jb_valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_validerActionPerformed(evt);
            }
        });

        jtg_ajouter.setBackground(new java.awt.Color(153, 0, 0));
        jtg_ajouter.setForeground(new java.awt.Color(255, 255, 255));
        jtg_ajouter.setText("ajouter");
        jtg_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtg_ajouterActionPerformed(evt);
            }
        });

        jtg_modifier.setBackground(new java.awt.Color(153, 0, 0));
        jtg_modifier.setForeground(new java.awt.Color(255, 255, 255));
        jtg_modifier.setText("modifier");
        jtg_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtg_modifierActionPerformed(evt);
            }
        });

        jtg_supprimer.setBackground(new java.awt.Color(153, 0, 0));
        jtg_supprimer.setForeground(new java.awt.Color(255, 255, 255));
        jtg_supprimer.setText("supprimer");
        jtg_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtg_supprimerActionPerformed(evt);
            }
        });

        jtf_categorie.setEnabled(false);
        jtf_categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_categorieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_categoriesLayout = new javax.swing.GroupLayout(jp_categories);
        jp_categories.setLayout(jp_categoriesLayout);
        jp_categoriesLayout.setHorizontalGroup(
            jp_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_categoriesLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jtg_ajouter)
                .addGap(18, 18, 18)
                .addComponent(jtg_modifier)
                .addGap(18, 18, 18)
                .addComponent(jtg_supprimer)
                .addGap(35, 35, 35))
            .addGroup(jp_categoriesLayout.createSequentialGroup()
                .addGroup(jp_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_categoriesLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jl_categorie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtf_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_categoriesLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jb_valider)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_categoriesLayout.setVerticalGroup(
            jp_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_categoriesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jp_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtg_ajouter)
                    .addComponent(jtg_modifier)
                    .addComponent(jtg_supprimer))
                .addGap(128, 128, 128)
                .addGroup(jp_categoriesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_categorie)
                    .addComponent(jcb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtf_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(158, 158, 158)
                .addComponent(jb_valider)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jp_entete.setBackground(new java.awt.Color(153, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CATÉGORIES");

        javax.swing.GroupLayout jp_enteteLayout = new javax.swing.GroupLayout(jp_entete);
        jp_entete.setLayout(jp_enteteLayout);
        jp_enteteLayout.setHorizontalGroup(
            jp_enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jp_enteteLayout.setVerticalGroup(
            jp_enteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_enteteLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        jt_categories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jt_categories.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jt_categories.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_categories.getTableHeader().setReorderingAllowed(false);
        affichageListeCategories();
        jt_categories.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_categoriesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_categoriesFocusLost(evt);
            }
        });
        jScrollPane.setViewportView(jt_categories);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_categories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addComponent(jp_entete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_entete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_categories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_categoriesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_categoriesFocusGained
        ///
    }//GEN-LAST:event_jt_categoriesFocusGained

    private void jt_categoriesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_categoriesFocusLost
        //
    }//GEN-LAST:event_jt_categoriesFocusLost

    private void jb_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_validerActionPerformed
        if (jtg_ajouter.isSelected()){
            ajoutCategorie();
        }
        if (jtg_modifier.isSelected()){
            modifierCategorie();
        }
        if (jtg_supprimer.isSelected()){
            supprimerCategorie();
        }
    }//GEN-LAST:event_jb_validerActionPerformed

    private void jtg_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_ajouterActionPerformed
        jcb_categorie.setEnabled(false);
        jtf_categorie.setEnabled(true);
        majAffichage(jtg_ajouter);
    }//GEN-LAST:event_jtg_ajouterActionPerformed

    private void jtg_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_modifierActionPerformed
        jcb_categorie.setEnabled(true);
        jtf_categorie.setEnabled(true);
        majAffichage(jtg_modifier);
    }//GEN-LAST:event_jtg_modifierActionPerformed

    private void jtg_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_supprimerActionPerformed
        jcb_categorie.setEnabled(true);
        jtf_categorie.setEnabled(false);
                majAffichage(jtg_supprimer);
    }//GEN-LAST:event_jtg_supprimerActionPerformed

    private void jcb_categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_categorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_categorieActionPerformed

    private void jtf_categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_categorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_categorieActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton jb_valider;
    private javax.swing.JComboBox<String> jcb_categorie;
    private javax.swing.JLabel jl_categorie;
    private javax.swing.JPanel jp_categories;
    private javax.swing.JPanel jp_entete;
    private javax.swing.JTable jt_categories;
    private javax.swing.JTextField jtf_categorie;
    private javax.swing.JToggleButton jtg_ajouter;
    private javax.swing.JToggleButton jtg_modifier;
    private javax.swing.JToggleButton jtg_supprimer;
    // End of variables declaration//GEN-END:variables
}
