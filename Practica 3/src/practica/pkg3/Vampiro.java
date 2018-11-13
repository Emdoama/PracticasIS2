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
    boolean Come(boolean haComido) {       
            cantidadHumanosConvertidos++;         
        return true;
    }
    @Override
    public String toString()
    {
        return "";
    }
}
