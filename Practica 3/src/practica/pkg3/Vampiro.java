package practica.pkg3;

import java.io.Serializable;
    

public class Vampiro implements Serializable{
    int cantidadHumanosConvertidos;
    int diaNacimiento;
   /* boolean vivo;*/
    public Vampiro(int dia)
    {
        cantidadHumanosConvertidos = 0;
        diaNacimiento=dia;
        //vivo=true;
    }
    boolean Come(boolean haComido) {       
            cantidadHumanosConvertidos++;
        return true;
    }
  /*  public void Morir()
    {
        vivo =false;
    }    */

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

  /*  public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }*/
    @Override
    public String toString()
    {
        return "Dia de nacimiento: " + diaNacimiento + ". Cantidad de humanos convertidos: " + cantidadHumanosConvertidos + ".\n";
    }
}
