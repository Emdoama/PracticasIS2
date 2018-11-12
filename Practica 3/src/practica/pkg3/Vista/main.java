/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg3.Vista;

import practica.pkg3.Practica3;


public class main {
    
     public static void main(String args[]) {
         Practica3 p3= new Practica3();
         PantallaPrincipal pal= new PantallaPrincipal(p3);
         pal.setVisible(true);
         
         
     }
     }