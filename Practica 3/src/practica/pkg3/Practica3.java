package practica.pkg3;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Practica3 implements Serializable{
    Entorno entorno;
    
    public Entorno getEntorno() {
        return entorno;
    }

    public void setEntorno(Entorno entorno) {
        this.entorno = entorno;
    }
    public Entorno crearEntorno()
    {
       return new Entorno();
    }  
    public Practica3(){

        
        ObjectInputStream is;
            
        
        try
        {            
            is = new ObjectInputStream(new FileInputStream("Entorno.dat"));
            entorno = (Entorno)is.readObject();
            is.close();
        }catch (IOException | ClassNotFoundException e){entorno = new Entorno();}
    }
    
    public void GuardarEstado(Entorno entorno) 
    {       
        try (ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("Entorno.dat"))) 
        {
            os.writeObject(entorno);  
        }catch(Exception e){System.out.println("No se ha podido guardar el entorno.");}
    }
    
}
