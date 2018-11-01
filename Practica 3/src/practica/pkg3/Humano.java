package practica.pkg3;

import java.io.Serializable;
import java.util.Random;

public class Humano extends ser implements Serializable{
    
    int velocidad;
    Random ram = new Random(System.currentTimeMillis());
    
    public Humano(int DIA)
    {
        this.diaNacimiento = DIA;
        velocidad = (ram.nextInt((100-60+1))+60);
    }

    @Override
    public void Nacer() {
        
    }

    @Override
    public void Morir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

