package practica.pkg3;

import java.io.Serializable;

public class Zombie implements Serializable{
    private int cantidad;
    private int diaNacimiento;

    Zombie(int dia) {
       diaNacimiento=dia;
    }
   void convierte() 
    {
        cantidad++;
    }
   
    int getdiaNacimiento() 
    {
        return diaNacimiento;
    }

  
    
}
