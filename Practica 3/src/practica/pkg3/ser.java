package practica.pkg3;

import java.io.Serializable;

 public abstract class ser implements Serializable{
     int diaNacimiento;
     int velocidad;
    
    /**
     * 
     * @param dia
     * @param velocidad
     */
    public abstract void Nacer(int dia, int velocidad);   
    
    /**
     * 
     * @param cantidad
     * @param dia
     */
    public abstract void Reproducirse(int cantidad,int dia);
    
    /**
     * 
     */
    public abstract void Morir();
    
    
}
