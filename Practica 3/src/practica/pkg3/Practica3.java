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
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bs;
        ObjectInputStream is;
        FileInputStream entrada;
        Entorno entorno;
        
        try
        {
            entrada = new FileInputStream("Entorno.dat");
            is = new ObjectInputStream(entrada);
            entorno = (Entorno)is.readObject();
            is.close();
        }catch (FileNotFoundException e){entorno = new Entorno(); entorno.CrearEntorno();}
    }
    
    void GuardarEstado(Entorno entorno) throws IOException
    {
        FileOutputStream salida = new FileOutputStream("Entorno.dat");
        try (ObjectOutputStream os = new ObjectOutputStream (salida)) 
        {
            os.writeObject(entorno);  
        }
    }
}
