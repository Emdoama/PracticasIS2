/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg3;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Alber
 */
public class Humano extends ser implements Serializable{
    Calendar fechaNacimiento = Calendar.getInstance();
    int velocidad;
}

