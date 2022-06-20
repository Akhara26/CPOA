/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.fenetre;

import menu.MenuConnection;
import menu.MenuProduit;

/**
 *
 * @author noemi
 */
public class Fenetre {

    public static void main(String[] args) {
        System.out.println("ça fonctionne ??");
        
        //MenuConnection connexion = new MenuConnection();//////////////////////
        //connexion.setVisible(false);
        
        MenuProduit produit = new MenuProduit();//////////////////////
        produit.setVisible(true);
    }
}
