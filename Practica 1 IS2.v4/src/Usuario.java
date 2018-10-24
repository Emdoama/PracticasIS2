
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */
public class Usuario {
    
    static int IDCLIENTE_PROXIMO = 1;
    int IDUsuario;
    String nombre, mail;    
    ArrayList <Objeto> Objetos;
    ArrayList <Alquiler> Alquileres;

   /* public static ArrayList<Objeto> getObjetos() {
        return Objetos;
    }*/

    
    public Usuario(String nombre, String mail){
        this.nombre = nombre;
        this.mail = mail;
        IDUsuario = IDCLIENTE_PROXIMO;
        IDCLIENTE_PROXIMO++;
        Objetos = new ArrayList();
        Alquileres = new ArrayList();
    }
    
    @Override
    public String toString()
    {
        String cadena_IDUsuario = String.format("%03d", IDUsuario);
        System.out.println("Codigo de usuario: " + cadena_IDUsuario + " Nombre: " + nombre + "\n");
        return cadena_IDUsuario + nombre;
    }
    
    
    public String getNombre()
    {
        return nombre;
    }
    public String getMail()
    {
        return mail;
    }
    
    public Objeto getObjeto(int IDObjeto)
    {
        for (int i = 0; i < Objetos.size(); i++)
        { 
            if (Objetos.get(i).getIDObjeto() == IDObjeto) 
            {
                return Objetos.get(i); 
            }
       
        }
        return null;
    }
    public int getIDUsuario(){return IDUsuario;}
    public void anyadirObjeto(Objeto objeto){Objetos.add(objeto);}
    public void mostrarObjetos()
    { 
        for (int i = 0; i < Objetos.size(); i++) 
        { 
            System.out.println(Objetos.get(i).toString());            
           
        }
    }
    public void mostrarObjetosAlquilables()
    { 
        for (int i = 0; i < Objetos.size(); i++) 
        { 
            if(Objetos.get(i).isActivo())
            System.out.println(Objetos.get(i).toString());            
           
        }
    }
    
     public void mostrarObjetosyAlquileres()
    { 
        for (int i = 0; i < Objetos.size(); i++) 
        { 
             System.out.println(Objetos.get(i).toString());   
            
            Objetos.get(i).mostrarAlquileres();
        }
    }
    public void bajaObjeto(Objeto objeto)
    {
        objeto.desactivarObjecto();
    }
    public Objeto devolverObjeto(int IDObjeto)
    {
        for (int i = 0; i < Objetos.size(); i++)
        { 
            if (Objetos.get(i).getIDObjeto() == IDObjeto) 
            {
                return Objetos.get(i);
               
            }
        }
        return null;
    }

    public void modificarCosteObjeto(Objeto obj, int coste) {
       obj.modificarCosteObjeto(coste);
    }
}
