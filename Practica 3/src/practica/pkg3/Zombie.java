package practica.pkg3;

import java.io.Serializable;

public class Zombie implements Serializable{
    private int cantidadHumanosConvertidos;
    private int diaNacimiento;

    Zombie(int dia) {
       diaNacimiento = dia;
       cantidadHumanosConvertidos = 0;
    }
    public void convierte() 
    {
        cantidadHumanosConvertidos++;
    }
    int getdiaNacimiento() 
    {
        return diaNacimiento;
    }
    
}
