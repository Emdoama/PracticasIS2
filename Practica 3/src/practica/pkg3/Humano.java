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
        
        Humano humano = new Humano(dia, velocidad);
        boolean insertado = false;
        int i = Entorno.Humanos.size();
        
       
        try{
        do
        {   
                    
            if (velocidad < Entorno.Humanos.get(i).getVelocidad())
            {
                Entorno.Humanos.add(i+1,humano);
                insertado = true;
            }
            else
                i--;
        }while(insertado != true);
        }catch(IndexOutOfBoundsException e){Entorno.Humanos.add(humano);}
    }
    
    @Override
    public void Morir() {
        Entorno.Humanos.remove(Probabilidades.calculoAleatorio(0, Entorno.Humanos.size()));
    }
    @Override
    public void Reproducirse(int cantidad, int dia) 
    {
        for (int i=1 ; i <= cantidad; i++)
        {
            this.Nacer(dia, velocidad);
        }       
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

