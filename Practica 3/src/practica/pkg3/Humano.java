package practica.pkg3;
import java.io.Serializable;

public class Humano extends ser implements Serializable{
    public Humano(int DIA, int velocidad)
    {
        this.diaNacimiento = DIA;
        this.velocidad = velocidad;
    }
    @Override
    public Object Nacer(int dia, int velocidad) {
        
       return new Humano(dia, velocidad);
               
    }
    
    @Override
    public void Morir() {     
    }
    @Override
    public Object Reproducirse( int dia) 
    {
       
            return this.Nacer(dia, velocidad);
            
    }
    int getVelocidad() {
        return velocidad;
    }
    @Override
    public String toString()
    {
        return "";
    }
}

