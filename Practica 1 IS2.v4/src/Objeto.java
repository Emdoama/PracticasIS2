import java.util.ArrayList;
import java.util.Calendar;

public class Objeto {
    
    static int IDObjetoProximo = 1;
    int IDPopietario, IDObjeto, coste;
    String descripcion;
    Calendar inicio, fin;
    boolean activo;    
    ArrayList <Alquiler> Alquileres;
    
   
    
    public Objeto(int idPopietario, String descripcion, Calendar inicio, Calendar fin, int coste)
    {
        this.IDPopietario = idPopietario;
        this.IDObjeto = IDObjetoProximo;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.fin = fin;
        this.coste = coste;
        IDObjetoProximo++;
        Alquileres= new ArrayList();
        activo =true;
  
    }
    
    @Override
    public String toString()
    {   
        String usuario = 
        "\tCódigo del objeto: " + IDObjeto + "\n" +
        "\tDescripción: " + descripcion + "\n" +
        "\tFecha de disponiblidad: " + inicio.getTime().getDate() + "/" + inicio.getTime().getMonth() + "/" + (inicio.getTime().getYear() +1900) +" - " + fin.getTime().getDate() + "/" + fin.getTime().getMonth() + "/" + (fin.getTime().getYear() +1900) + "\n" +
        "\tCoste del préstamo por día: " + coste + " euros\n";
        
        return usuario;
    }
    public void desactivarObjecto(){activo =false;}    
    
    public int getCoste(){return coste;}
    
    public int getIDObjeto(){return IDObjeto;}
    
    public void alquilarObjeto(Alquiler alquiler)
    {
        Alquileres.add(alquiler);        
    }

    public boolean isActivo() {
        return activo;
    }
    
    public void mostrarAlquileres()
    {
        for (int i = 0; i < Alquileres.size(); i++)
        { 
          System.out.println(Alquileres.get(i).toString());
        } 
    }

    public void modificarCosteObjeto(int coste) {
        this.coste=coste;
    }
    public int importeStarUp()
    {
        int cant=0;
        
        for(Alquiler alq : Alquileres)
        {
           cant = cant + alq.importeStartup;
        }    
        return cant;
    }
}
