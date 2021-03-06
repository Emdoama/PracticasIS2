package practica.pkg3;

import java.io.Serializable;

public class Cazavampiro extends Humano implements Serializable {
    int cantidadVampirosCazados;
   
    public Cazavampiro(int DIA, int velocidad) {
        super(DIA, velocidad);
        cantidadVampirosCazados = 0;
    }
    
    @Override
    public Object Nacer(int dia, int velocidad) {
        
        return new Cazavampiro(dia, velocidad);
        
    }
    @Override
     public Object Reproducirse(int dia) 
    {        
            return this.Nacer(dia, velocidad);
            
    }
     
    //@Override
   /* public void Morir() {  
        vivo=false;
    }*/
    void caza() {
       cantidadVampirosCazados++; 
    }
    @Override
    public String toString()
    {
        return "Cantidad de vampiros cazados: " + cantidadVampirosCazados + ". Dia de nacimiento: " + this.diaNacimiento + ". Velocidad: " + velocidad + ".\n";
    }
}
