package practica.pkg3;

import java.io.Serializable;

 public abstract class ser implements Serializable{
     int diaNacimiento;
     int velocidad;
    
    /**
     * 
     * @param dia
     * @param velocidad
     * @return 
     */
    public abstract Object Nacer(int dia, int velocidad);   
    
    /**
     * 
     * @param cantidad
     * @param dia
     */
    public abstract Object Reproducirse(int dia);
        
    
}
