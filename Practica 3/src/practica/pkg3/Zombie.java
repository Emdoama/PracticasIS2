package practica.pkg3;

import java.io.Serializable;

public class Zombie implements Serializable{
    private int cantidadHumanosConvertidos;
    private int diaNacimiento;
    private boolean vivo;

    public int getCantidadHumanosConvertidos() {
        return cantidadHumanosConvertidos;
    }

    public void setCantidadHumanosConvertidos(int cantidadHumanosConvertidos) {
        this.cantidadHumanosConvertidos = cantidadHumanosConvertidos;
    }

    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(int diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    Zombie(int dia) {
       diaNacimiento = dia;
       cantidadHumanosConvertidos = 0;
       vivo = true;
    }
    public void convierte() 
    {
        cantidadHumanosConvertidos++;
    }
    int getdiaNacimiento() 
    {
        return diaNacimiento;
    }
    public void Morir()
    {
        vivo =false;
    } 
    @Override
    public String toString()
    {
        return "Humanos convertidos: " + cantidadHumanosConvertidos + ". Dia de nacimiento: " + diaNacimiento + ".\n";
    }
    
}
