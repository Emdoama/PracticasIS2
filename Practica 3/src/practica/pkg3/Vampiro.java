package practica.pkg3;

import java.io.Serializable;
    

public class Vampiro implements Serializable{
    int cantidadHumanosConvertidos;
    int diaNacimiento;
    public Vampiro(int dia)
    {
        cantidadHumanosConvertidos = 0;
        diaNacimiento=dia;
    }
    boolean Come() {
        boolean haComido = false;
        if (!Entorno.Humanos.isEmpty())    
        {
            cantidadHumanosConvertidos++;
            haComido = true;
            Entorno.Humanos.get(Probabilidades.calculoAleatorio(1, Entorno.Humanos.size())).Morir();
        }
        return haComido;
    }

  
    
}
