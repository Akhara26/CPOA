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
public class FenetreProduits extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rset;
    
    
    //liste des catégories pour jcb_categories
    String[] listeCategories = listeColonne("CPOA_CATEGORIE","NOM_CAT");
    DefaultComboBoxModel m_listeCategories = new DefaultComboBoxModel(listeCategories);
    
    //liste des produits pour jcb_produits
    String[] listeProduits = listeColonne("CPOA_PRODUIT","LIBELLE_PRODUIT");
    DefaultComboBoxModel m_listeProduits = new DefaultComboBoxModel(listeProduits);
    
    
    /*
    connexion à la BD !
    */
    public void connection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1","p2003225","564077");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FenetreProduits.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FenetreProduits.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("ça se passe pas hyper bien");
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
    public void affichageListeProduits() {
        try {
            connection();
            String query = "SELECT ID_PRODUIT, PRIX, DESCRIPTION, STOCK_PRO, ID_CAT, LIBELLE_PRODUIT FROM P2003225.CPOA_PRODUIT";
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);
            String columns[] = { "ID", "libellé", "description", "prix", "stock", "ID cat" };
            String data[][] = new String[nombreLignes("CPOA_PRODUIT")][6];
            int i = 0;
            while (res.next()) {
                int id = res.getInt("ID_PRODUIT");
                int prix = res.getInt("PRIX");
                String description = res.getString("DESCRIPTION");
                int stock = res.getInt("STOCK_PRO");
                int id_cat = res.getInt("ID_CAT");
                String libelle = res.getString("LIBELLE_PRODUIT");
                data[i][0] = id + "";
                data[i][1] = libelle;
                data[i][2] = description;
                data[i][3] = prix + "";
                data[i][4] = stock + "";
                data[i][5] = id_cat + "";
                i++;
            }
            DefaultTableModel model = new DefaultTableModel(data, columns);
            jt_produits.setModel(model);
            jt_produits.setShowGrid(true);
            jt_produits.setShowVerticalLines(true);
            JScrollPane pane = new JScrollPane(jt_produits);
            JPanel panel = new JPanel();
            panel.add(pane);
            pst.setString(1, jtf_description.getText());
            stm.execute(query);
            con.close();
            JOptionPane.showMessageDialog(null,"enregistrement réussi");
            jt_produits.setDefaultEditor(Object.class, null);
            stm.close();
            res.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"erreur affichage tableau : " + e.getMessage());}
        }
    }
    
    /*
    ajoute un produit dans la BDD (l'incrémentation de l'ID est automatisée)
    */
    public void ajoutProduit() {
        //connexion à la BD
        connection();

        try {
            String sql = "INSERT INTO P2003225.CPOA_PRODUIT (PRIX, DESCRIPTION, STOCK_PRO, ID_CAT, LIBELLE_PRODUIT) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(jtf_prix.getText()));
            pst.setString(2, jtf_description.getText());
            pst.setInt(3, Integer.parseInt(jtf_stock.getText()));
            pst.setInt(4, Integer.parseInt(itemSelectionne("ID_CAT", "CPOA_CATEGORIE", "NOM_CAT", (String) jcb_categorie.getSelectedItem())));
            pst.setString(5, jtf_libelle.getText());
            pst.execute();
            con.close();
            
            JOptionPane.showMessageDialog(null,"enregistrement réussi");
            
            //fermeture
            pst.close();
            rset.close();
            
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"veuillez remplir tous les champs avant d'ajouter un produit");}
        }
    }
    
    /*
    modifie une ligne de la table CPOA_PRODUIT
    */
    public void modifierProduit( ){
        System.out.println("jui dans modification produit");
        connection();

        try {
            String sql = "UPDATE P2003225.CPOA_PRODUIT SET PRIX = ?, DESCRIPTION = ?, STOCK_PRO = ?, ID_CAT = ?, LIBELLE_PRODUIT = ? WHERE ID_PRODUIT = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(jtf_prix.getText()));
            pst.setString(2, jtf_description.getText());
            pst.setInt(3, Integer.parseInt(jtf_stock.getText()));
            pst.setInt(4, Integer.parseInt(itemSelectionne("ID_CAT", "CPOA_CATEGORIE", "NOM_CAT", (String) jcb_categorie.getSelectedItem())));
            pst.setString(5, jtf_libelle.getText());
            pst.setInt(6, Integer.parseInt(itemSelectionne("ID_PRODUIT", "CPOA_PRODUIT", "LIBELLE_PRODUIT", (String) jcb_libelle.getSelectedItem())));
            
            pst.execute();
            con.close();
            
            
            
            JOptionPane.showMessageDialog(null,"mise à jour réussie");
            
            //fermeture
            pst.close();
            rset.close();
            
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"veuillez remplir tous les champs avant de modifier un produit" + e.getMessage());}
        }
        
    }
    
    /*
    supprime une ligne de la table CPOA_PRODUIT
    */
    public void supprimerProduit(){
        System.out.println("jui dans modification produit");
        connection();

        try {
            String sql = "DELETE FROM P2003225.CPOA_PRODUIT WHERE ID_PRODUIT = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(itemSelectionne("ID_PRODUIT", "CPOA_PRODUIT", "LIBELLE_PRODUIT", (String) jcb_libelle.getSelectedItem())));
            pst.execute();
            con.close();
            JOptionPane.showMessageDialog(null,"suppression réussie");
            
            //fermeture
            pst.close();
            rset.close();
        } catch (Exception e) {
            if (e.getMessage() != null) {JOptionPane.showMessageDialog(null,"le produit a déjà été supprimé");}
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
            jtf_libelle.setEnabled(false);
            jcb_libelle.setEnabled(false);
            jtf_prix.setEnabled(false);
            jtf_stock.setEnabled(false);
            jcb_categorie.setEnabled(false);
            jtf_description.setEnabled(false);
            jb_valider.setEnabled(false);            
        }else{
            jb_valider.setEnabled(true);
            jcb_libelle.setEnabled(!jtg_ajouter.isSelected());
            jtf_libelle.setEnabled(jtg_ajouter.isSelected() || jtg_modifier.isSelected());

            // champs jtf à désactiver quand supprimer est sélectionné
            jtf_prix.setEnabled(!jtg_supprimer.isSelected());
            jtf_stock.setEnabled(!jtg_supprimer.isSelected());
            jcb_categorie.setEnabled(!jtg_supprimer.isSelected());
            jtf_description.setEnabled(!jtg_supprimer.isSelected());
        }
        
        
        
    }
    
    /**
     * Creates new form Fenetre
     */
    public FenetreProduits() {
        initComponents(); 
        
        
        
        
        
        
        // ne garder que les chiffres pour l'ajout d'un prix d'un produit
        jtf_prix.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        
        // ne garder que les chiffres pour l'ajout d'un stock d'un produit
        jtf_stock.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                     e.consume();  // if it's not a number, ignore the event
                }
            }
        });
        
        
        
        
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp_produit = new javax.swing.JPanel();
        jl_libelle = new javax.swing.JLabel();
        jtf_libelle = new javax.swing.JTextField();
        jl_prix = new javax.swing.JLabel();
        jtf_prix = new javax.swing.JTextField();
        jl_description = new javax.swing.JLabel();
        jl_categorie = new javax.swing.JLabel();
        jtf_description = new javax.swing.JTextField();
        jl_stock = new javax.swing.JLabel();
        jtf_stock = new javax.swing.JTextField();
        jcb_categorie = new javax.swing.JComboBox<>();
        jcb_libelle = new javax.swing.JComboBox<>();
        jb_valider = new javax.swing.JButton();
        jtg_ajouter = new javax.swing.JToggleButton();
        jtg_modifier = new javax.swing.JToggleButton();
        jtg_supprimer = new javax.swing.JToggleButton();
        jp_entete = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jt_produits = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion Jardin d'Enfants");
        setSize(new java.awt.Dimension(800, 600));

        jl_libelle.setText("libellé");

        jtf_libelle.setEnabled(false);
        jtf_libelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_libelleActionPerformed(evt);
            }
        });

        jl_prix.setText("prix");

        jtf_prix.setEnabled(false);
        jtf_prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_prixActionPerformed(evt);
            }
        });

        jl_description.setText("description");

        jl_categorie.setText("catégorie");

        jtf_description.setEnabled(false);
        jtf_description.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_descriptionActionPerformed(evt);
            }
        });

        jl_stock.setText("stock");

        jtf_stock.setEnabled(false);
        jtf_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_stockActionPerformed(evt);
            }
        });

        jcb_categorie.setModel(m_listeCategories);
        jcb_categorie.setEnabled(false);
        jcb_categorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_categorieActionPerformed(evt);
            }
        });

        jcb_libelle.setModel(m_listeProduits);
        jcb_libelle.setEnabled(false);

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

        javax.swing.GroupLayout jp_produitLayout = new javax.swing.GroupLayout(jp_produit);
        jp_produit.setLayout(jp_produitLayout);
        jp_produitLayout.setHorizontalGroup(
            jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_produitLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtg_ajouter)
                .addGap(18, 18, 18)
                .addComponent(jtg_modifier)
                .addGap(18, 18, 18)
                .addComponent(jtg_supprimer)
                .addGap(35, 35, 35))
            .addGroup(jp_produitLayout.createSequentialGroup()
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_produitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_produitLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_libelle, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jl_prix, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_produitLayout.createSequentialGroup()
                                        .addComponent(jtf_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcb_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtf_prix, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jp_produitLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jl_stock)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtf_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_produitLayout.createSequentialGroup()
                                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jl_description)
                                    .addComponent(jl_categorie))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_description, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jp_produitLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jb_valider)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jp_produitLayout.setVerticalGroup(
            jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_produitLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtg_ajouter)
                    .addComponent(jtg_modifier)
                    .addComponent(jtg_supprimer))
                .addGap(40, 40, 40)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_libelle)
                    .addComponent(jcb_libelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_prix)
                    .addComponent(jtf_prix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_categorie)
                    .addComponent(jcb_categorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_description)
                    .addComponent(jtf_description, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_produitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_stock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_valider)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jp_entete.setBackground(new java.awt.Color(153, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRODUITS");

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

        jt_produits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jt_produits.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jt_produits.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_produits.getTableHeader().setReorderingAllowed(false);
        affichageListeProduits();
        jt_produits.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_produitsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_produitsFocusLost(evt);
            }
        });
        jScrollPane.setViewportView(jt_produits);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jp_produit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jp_produit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jt_produitsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_produitsFocusGained
        ///
    }//GEN-LAST:event_jt_produitsFocusGained

    private void jt_produitsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_produitsFocusLost
        //
    }//GEN-LAST:event_jt_produitsFocusLost

    private void jb_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_validerActionPerformed
        if (jtg_ajouter.isSelected()){
            ajoutProduit();
        }
        if (jtg_modifier.isSelected()){
            modifierProduit();
        }
        if (jtg_supprimer.isSelected()){
            supprimerProduit();
        }
    }//GEN-LAST:event_jb_validerActionPerformed

    private void jcb_categorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_categorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_categorieActionPerformed

    private void jtf_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_stockActionPerformed

    private void jtf_descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_descriptionActionPerformed

    private void jtf_prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_prixActionPerformed
        //
    }//GEN-LAST:event_jtf_prixActionPerformed

    private void jtf_libelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_libelleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_libelleActionPerformed

    private void jtg_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_ajouterActionPerformed
        majAffichage(jtg_ajouter);
    }//GEN-LAST:event_jtg_ajouterActionPerformed

    private void jtg_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_modifierActionPerformed
        majAffichage(jtg_modifier);
    }//GEN-LAST:event_jtg_modifierActionPerformed

    private void jtg_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtg_supprimerActionPerformed
        majAffichage(jtg_supprimer);
    }//GEN-LAST:event_jtg_supprimerActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JButton jb_valider;
    private javax.swing.JComboBox<String> jcb_categorie;
    private javax.swing.JComboBox<String> jcb_libelle;
    private javax.swing.JLabel jl_categorie;
    private javax.swing.JLabel jl_description;
    private javax.swing.JLabel jl_libelle;
    private javax.swing.JLabel jl_prix;
    private javax.swing.JLabel jl_stock;
    private javax.swing.JPanel jp_entete;
    private javax.swing.JPanel jp_produit;
    private javax.swing.JTable jt_produits;
    private javax.swing.JTextField jtf_description;
    private javax.swing.JTextField jtf_libelle;
    private javax.swing.JTextField jtf_prix;
    private javax.swing.JTextField jtf_stock;
    private javax.swing.JToggleButton jtg_ajouter;
    private javax.swing.JToggleButton jtg_modifier;
    private javax.swing.JToggleButton jtg_supprimer;
    // End of variables declaration//GEN-END:variables
}
