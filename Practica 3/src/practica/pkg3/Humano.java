package practica.pkg3;

import java.io.Serializable;

public class Humano extends ser implements Serializable{
 
    public Humano(int DIA, int velocidad)
    {
        this.diaNacimiento = DIA;
        this.velocidad = velocidad;
    }
    @Override
    public void Nacer(int dia, int velocidad) {
        Entorno.Humanos.add(new Humano(dia, velocidad));
    }
    @Override
    public void Morir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void Reproducirse(int cantidad, int dia) 
    {
        for (int i=1 ; i<=cantidad; i++)
        {
            this.Nacer(dia, velocidad);
        }       
    }
    int getVelocidad() {
        return velocidad;
    }
}

