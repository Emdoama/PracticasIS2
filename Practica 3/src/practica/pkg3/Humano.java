/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg3;

import java.io.Serializable;

/**
 *
 * @author Alber
 */
public class Humano extends ser implements Serializable{
    int fechaNacimiento;
    int velocidad;
    
    public Humano(int DIA)
    {
        super();
        fechaNacimiento = DIA;
    }
    
}

