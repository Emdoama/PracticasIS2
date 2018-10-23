import java.util.Calendar;

public class Alquiler {
   
    int importeTotalPropietario, importeStartup;
    String nombreUsuario;
    Calendar inicio, fin;
    
     
    public Alquiler(Usuario usuario, Objeto objeto, Calendar inicio, Calendar fin){
        
        this.nombreUsuario = usuario.getNombre();
        this.importeTotalPropietario = objeto.getCoste() * (int)((fin.getTimeInMillis() - inicio.getTimeInMillis()) / (long)(1000 * 60 * 60 * 24)) ;           
       
        this.importeStartup = this.importeTotalPropietario / 10;
        this.inicio = inicio;
        this.fin = fin;
       
    }
     
    
    
    @Override
    public String toString()
    {   
        String alquiler = 
        "\t\tNombre del cliente: " + nombreUsuario + "\n" +
        "\t\tFecha del prestamo: " + inicio.getTime().getDate() + "/" + inicio.getTime().getMonth() + "/" + (inicio.getTime().getYear() +1900) +" - " + fin.getTime().getDate() + "/" + fin.getTime().getMonth() + "/" + (fin.getTime().getYear() +1900) + "\n" +
        "\t\timporte para el propietario: " + (importeTotalPropietario) + "\n" +
        "\t\tImporte para la startup: " + importeStartup + "\n";
        
        return alquiler;
    }    
}