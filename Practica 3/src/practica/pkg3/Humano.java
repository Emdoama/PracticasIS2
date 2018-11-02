package practica.pkg3;

import java.io.Serializable;
import java.util.Random;

public class Humano extends ser implements Serializable{
      
    Random ram = new Random(System.currentTimeMillis());
    
    public Humano(int DIA)
    {
        this.diaNacimiento = DIA;
        this.velocidad = (ram.nextInt((100-60+1))+60);
    }

    @Override
    public void Nacer(int dia, int velocidad) {
        
    }

    @Override
    public void Morir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Reproducirse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

