package practica.pkg3;
import java.io.Serializable;

public class Humano extends ser implements Serializable{
    protected boolean vivo;

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    public Humano(int DIA, int velocidad)
    {
        this.diaNacimiento = DIA;
        this.velocidad = velocidad;
        this.vivo = true;
    }
    @Override
    public Object Nacer(int dia, int velocidad) {
        
       return new Humano(dia, velocidad);
               
    }
    
    @Override
    public void Morir() {   
        vivo=false;
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

