package practica.pkg3;

import java.io.Serializable;

 public abstract class ser implements Serializable{
     int diaNacimiento;
     int velocidad;
    
    /**
     * Clases abstractas para los seres
     */
    public abstract void Nacer(int dia, int velocidad);      
    public abstract void Reproducirse();
    public abstract void Morir();
    
    
}
