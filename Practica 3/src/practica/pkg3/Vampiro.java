package practica.pkg3;

import java.io.Serializable;
    

public class Vampiro implements Serializable{
    int cantidad;
    int diaNacimiento;
    public Vampiro(int dia)
    {
        cantidad=0;
        diaNacimiento=dia;
    }
    void come() {
        cantidad++;
    }

  
    
}
