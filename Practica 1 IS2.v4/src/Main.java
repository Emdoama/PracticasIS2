import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    static Scanner scan= new Scanner(System.in);
    static ArrayList <Usuario> Usuarios = new ArrayList();
    static Usuario user;
    static int caso;
    static boolean flag=false;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    
    
    public static void main (String Args[])
        {
            //Creacion de datos base
        Usuarios.add(new Usuario("Emilio", "domae@uv.es"));
        Usuarios.add(new Usuario("Albert", "alpama@uv.es"));
        Usuarios.add(new Usuario("Diego", "diemomar@uv.es"));
        
        Usuarios.get(0).anyadirObjeto(new Objeto(0,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),10));
        Usuarios.get(0).anyadirObjeto(new Objeto(1,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),20));
        Usuarios.get(0).anyadirObjeto(new Objeto(2,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),30));
        
        Usuarios.get(1).anyadirObjeto(new Objeto(0,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),10));
        Usuarios.get(1).anyadirObjeto(new Objeto(1,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),20));
        Usuarios.get(1).anyadirObjeto(new Objeto(2,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),30));
        
        Usuarios.get(2).anyadirObjeto(new Objeto(0,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),10));
        Usuarios.get(2).anyadirObjeto(new Objeto(1,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),20));
        Usuarios.get(2).anyadirObjeto(new Objeto(2,"",new GregorianCalendar(2018,10,23),new GregorianCalendar(2019,10,23),30));
        
        Usuarios.get(0).Objetos.get(0).alquilarObjeto( new Alquiler (Usuarios.get(1),Usuarios.get(0).Objetos.get(0),new GregorianCalendar(2018,10,23),new GregorianCalendar(2018,11,23)) );
        Usuarios.get(0).Objetos.get(1).alquilarObjeto( new Alquiler (Usuarios.get(2),Usuarios.get(0).Objetos.get(1),new GregorianCalendar(2018,10,23),new GregorianCalendar(2018,11,23)) );
        Usuarios.get(2).Objetos.get(0).alquilarObjeto( new Alquiler (Usuarios.get(0),Usuarios.get(2).Objetos.get(0),new GregorianCalendar(2018,10,23),new GregorianCalendar(2018,11,23)) );
        
        //Mensaje bienvenida
        System.out.println("\n\n\n"+ ANSI_BLUE + "Bienvenido a la aplicación MaxRend\n" );

        //Bucle se repite mientras la opcion no sea la de salir
        do
        {
            //Mensaje opciones menu
            System.out.println("\nSelecciona una de las siguientes opciones:\n"+
                               "1. Alta usuario\n" +
                               "2. Alta objeto\n" +
                               "3. Alquiler de objeto\n" + 
                               "4. Listar todos los objetos\n" +
                               "5. Baja objeto\n" +
                               "6. Mostrar saldos\n" +
                               "7. Salir\n" + 
                               "8. Modificar Saldo\n" +
                               "9. Portabilidad Saldos\n" +
                               "0. Eliminar Usuario\n" );
           //Intenta leer un entero para las opciones
            try{
                scan= new Scanner(System.in);
                caso = scan.nextInt();
            //Captura el error   
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un número del 1 al 7, por favor.\n"); }
                     
            //Switch con las opciones
            switch (caso)
            {
                case 1:
                    AltaUsuario();                    
                    break;
                case 2:
                    AltaObjeto();
                    break;
                case 3:
                    AlquilarObjeto();
                    break;
                case 4:
                    ListarObjetos();
                    break;
                case 5:
                    BajaObjeto();
                    break;
                case 6:
                    MostrarSaldos();
                    break;                
                case 7:
                    System.exit(0);
                    break;
                 case 8:
                    ModificarSaldo();
                    break;
                 case 9:
                    PortabilidadSaldos();
                    break;
                 case 0:
                    EliminarUsuario();
                    break;      
                default:
                    System.out.println("La opción elegida no es valida\n");                          
            }
            
        }while(caso!=7);
    }
      
    //Funcion para dar de alta el usuario
    public static void AltaUsuario()
    {
        
        String name, mail;
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Pattern pattern2= Pattern.compile("^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*(.[a-zA-Z]{2,4})$");
        Matcher matcher;
         
        //recoge nombre
       
            
        try{   
                System.out.println("Introduce el nombre del usuario: \n"); 
                name = scan.next();
                matcher = pattern.matcher(name);
                 if(!matcher.find())
                 { 
                    throw new Exception("");
                 }
        }catch(Exception e ){System.out.println("Introduce solo letras.\n"); name= null;}
          
          
        //recoge mail
        
        try{ 
            System.out.println("\nIntroduce el mail del usuario: \n");     
            mail = scan.next();          
            matcher = pattern2.matcher(mail); 
              if(!matcher.find())
              { 
                    throw new Exception("");
              }
        }catch(Exception e ){System.out.println("Introduce un mail válido.\n"); mail = null;}
         
      //Solo crea si se han rellenado bien los campos
        if(name!= null && mail!= null)
        {
            //Constructor
            user = new Usuario(name, mail);
            //Añadimos al Array de usuarios
            Usuarios.add(user);
        }else
            System.out.println("Este usuario no se creara debido a valores incorrectos en los campos, por favor intentelo de nuevo\n");
    }   
    
    //Funcion para dar de alta un objeto
    public static void AltaObjeto()
    {
        
        int idPropietario, coste;
        Calendar inicio = null, fin = null;
        String descripcion;
        Pattern patronDia = Pattern.compile("^[1-31]+$");
        Pattern patronMes = Pattern.compile("^[1-12]+$");
        Pattern patronAnyo = Pattern.compile("^(20)[18-99]+$");
        Matcher matcher;

        //Listamos los usuarios
        System.out.println("\nLista de usuarios: ");
        for (int i = 0; i < Usuarios.size(); i++)
        {
            Usuarios.get(i).toString(); 
            System.out.println("\n");
        }
        //Bucle para poder elegir un usuario creado previamente, se repite minetras no haya errores
        do{
           
        try{
            
            System.out.println("\nIntroduce el numero de usuario: ");             
            scan= new Scanner(System.in);
            idPropietario = scan.nextInt();           
                 
            for (Usuario user: Usuarios)
            {           
                
                    if(user.getIDUsuario() == idPropietario )
                    {
                    flag=true;
                    }
                    
            }
            if(!flag)
                         throw new Exception("");
          
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idPropietario = 0;}
        catch(Exception e){ System.out.println("El usuario no esta dado de alta");idPropietario = 0;}   
        }while(idPropietario ==0 );
                
        flag= false;
       
            
        System.out.println("\nIntroduce la descripcion del objeto: ");
        scan.next();
        descripcion = scan.nextLine();                        
       
        //Capturamos las fechas que se quieren
        inicio = pedirFechaInicio();     
        fin=  pedirFechaFin();        
                        
        
       //Capturamos el coste
       do{       
            System.out.println("\nIntroduce el nuevo coste: ");       
            try{
            scan= new Scanner(System.in);
            coste = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); coste = -1;}
        }while(coste == -1);
        //Creamos el objeto
        Objeto objeto = new Objeto(idPropietario, descripcion, inicio, fin, coste);
        
        //Lo añadimos
        for (int i = 0; i < Usuarios.size(); i++)
        {           
            try{
            if( Usuarios.get(i).getIDUsuario() == idPropietario )
            {
                Usuarios.get(i).anyadirObjeto(objeto);
            }
            }catch(Exception e){ System.out.println("El usuario no esta dado de alta");}
        }
    }
    //Funcion para crear Alquileres
    public static void AlquilarObjeto()
    {
        int idObjeto, idUsuario;
        Calendar inicio = null, fin = null;        
        Usuario usuarioAct=null;
        Objeto objetoAct=null;
	
        
        //Bucle para poder elegir un usuario creado previamente, se repite minetras no haya errores
        do{
        System.out.println("\nIntroduce el id de usuario: ");
        idUsuario = scan.nextInt();
                
        for (int i = 0; i < Usuarios.size(); i++)
        {
            try{
            if( Usuarios.get(i).getIDUsuario() == idUsuario )
            {
                usuarioAct=Usuarios.get(i);                
            }
            }catch(Exception e){ System.out.println("El usuario no esta dado de alta");}
        }        
        }while(usuarioAct==null);
        
        //Mostramos los objetos alquilables
        for(Usuario user: Usuarios)
        {
            
            user.mostrarObjetosAlquilables();   
        
        }       
        //Pedimos uno, se repite mientras no se elija uno bueno
        do{    
            System.out.println("\nIntroduce el id del objeto: ");       
            try{
                idObjeto = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idObjeto = 0;}
        }while(idObjeto == 0); 
        //Si se puede alquilar
        try{
            if(objetoAct.isActivo())
            objetoAct = usuarioAct.getObjeto(idObjeto);                
        }catch(Exception e){System.out.println("El objeto no existe"); }
        
	//Pedimos fechas
        inicio = pedirFechaInicio();     
        fin=  pedirFechaFin();        
                             
        //Construimos el alquiler y lo añadimos al objeto
        Alquiler alquilerAct = new Alquiler(usuarioAct, objetoAct, inicio, fin); 
        objetoAct.alquilarObjeto(alquilerAct);
        
    }
    //Listamos los Objetos con sus alquileres
    public static void ListarObjetos()
    {
        for (int i = 0; i < Usuarios.size(); i++)
        { 
            System.out.println("PROPIETARIO " + i + "\n");
            System.out.println("Nombre del propietario: " + Usuarios.get(i).getNombre() + "\nCorreo Electronico: " + Usuarios.get(i).getMail() + "\n");             
            Usuarios.get(i).mostrarObjetosyAlquileres();
                      
        }
    }
    //Desactivamos un objeto para el alquiler
    public static void BajaObjeto()
    {
     int idObjeto,idPropietario;
     Usuario usuarioAct=null;

     
       do{
        try{
            
            System.out.println("\nIntroduce el numero de usuario: ");   
            idPropietario = scan.nextInt();           
                 
            for (int i = 0; i < Usuarios.size(); i++)
            {           
                
                    if( Usuarios.get(i).getIDUsuario() == idPropietario )
                    {
                        flag=true;
                        usuarioAct=Usuarios.get(i);
                    }
                    
            }
             if(!flag)
                         throw new Exception("");
                
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idPropietario = 0;}
        catch(Exception e){ System.out.println("El usuario no esta dado de alta");idPropietario = 0;}
       }while(idPropietario==0);
       
       flag=false;
       
       
        do{       
            System.out.println("\nIntroduce el id del objeto: ");       
            try{
             scan= new Scanner(System.in);   
             idObjeto = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idObjeto = 0;}
        }while(idObjeto == 0); 
        
        try{       
        usuarioAct.bajaObjeto(usuarioAct.devolverObjeto(idObjeto));
        System.out.println("Objejto dado de baja\n");
        }catch(Exception e){System.out.println("Objeto erroneo\n");}
    }
    //Mostramos Salgos
    public static void MostrarSaldos()
    {
        int cant=0;
        for (Usuario Usuario : Usuarios) 
        {
            System.out.println("PROPIETARIO " + Usuario.getIDUsuario() + "\n");
            System.out.println("Nombre del propietario: " + Usuario.getNombre() + "\nCorreo Electronico: " + Usuario.getMail() + "\n"); 
            for(Objeto obj: Usuario.Objetos) 
            {   
                cant = cant + obj.importeStarUp();
                System.out.println("\tPRESTAMOS DEL OBJETO " + obj.getIDObjeto() + "\n");
                if(!obj.Alquileres.isEmpty())                       
                    obj.mostrarAlquileres();
                else
                    System.out.println("\tEl objeto " + obj.getIDObjeto() + " no tiene prestamos a mostrar.\n");   
            }
            
        }
        
        System.out.println("El importe total para la startup es de:  " +cant + " euros\n");    
    }
    //Funcion auxiliar para pedir fechas iniciales
    public static GregorianCalendar pedirFechaInicio()
    {
        Pattern patronDia = Pattern.compile("^(([0]?[1-9])|([1-2][0-9])|(3[01]))$");
        Pattern patronMes = Pattern.compile("^[1-9]|(1[0-2])+$");
        Pattern patronAnyo = Pattern.compile("^(20)([2-9][0-9]|(1[8,9]))+$");
        Matcher matcher;
        Calendar inicio = null;
        int diaInicio, mesInicio, anyoInicio;
        
               
        do{
        try{
        System.out.println("\nIntroduce dia de inicio: ");        
        diaInicio = scan.nextInt();
        matcher = patronDia.matcher(String.valueOf(diaInicio));
         if(!matcher.find())
                 { 
                    throw new Exception("\nDebes introducir un dia válido\n");                 
                 }
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); diaInicio =0;}
        catch(Exception e){System.out.println(e.toString()); diaInicio =0;}
        }while(diaInicio ==0);
		
	do{	
        try{
        System.out.println("\nIntroduce mes de inicio: ");     
        mesInicio = scan.nextInt();
        matcher = patronMes.matcher(String.valueOf(mesInicio));
         if(!matcher.find())
                 { 
                    throw new Exception("");                 
                 }
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); mesInicio =0;}
        catch(Exception e){System.out.println("\nDebes introducir un mes  válido\n"); mesInicio =0;}
	}while(mesInicio ==0);	
		
        
        do{
        try{
        System.out.println("\nIntroduce anyo de inicio: ");   
        anyoInicio = scan.nextInt();
        matcher = patronAnyo.matcher(String.valueOf(anyoInicio));
         if(!matcher.find())
                 { 
                    throw new Exception("");                 
                 }
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); anyoInicio =0;}
        catch(Exception e){System.out.println("\nDebes introducir un anyo válido\n"); anyoInicio =0;}
	}while(anyoInicio ==0);	
        
        return new GregorianCalendar(anyoInicio, mesInicio, diaInicio);
        
    }
    //Funcion auxiliar para pedir fechar finales
     public static GregorianCalendar pedirFechaFin()
    {
        Pattern patronDia = Pattern.compile("^(([0]?[1-9])|([1-2][0-9])|(3[01]))$");
        Pattern patronMes = Pattern.compile("^[1-9]|(1[0-2])+$");
        Pattern patronAnyo = Pattern.compile("^(20)([2-9][0-9]|(1[8,9]))+$");
        Matcher matcher;        
        int diaFin, mesFin, anyoFin;
      
                      
        do{
        try{
            System.out.println("\nIntroduce dia de fin: ");
            diaFin = scan.nextInt();
            matcher = patronDia.matcher(String.valueOf(diaFin));
        if(!matcher.find())
                 { 
                    throw new Exception("");                 
                 }
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); diaFin =0;}
        catch(Exception e){System.out.println("\nDebes introducir un mes  válido\n"); diaFin =0;}
	}while(diaFin ==0);		
        
        do{
        try{
        System.out.println("\nIntroduce mes de fin: ");   
        mesFin = scan.nextInt();
        matcher = patronMes.matcher(String.valueOf(mesFin));
         if(!matcher.find())
                 { 
                    throw new Exception("");                 
                 }
         }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); mesFin =0;}
        catch(Exception e){System.out.println("\nDebes introducir un mes  válido\n"); mesFin =0;}
        }while(mesFin  ==0);
	
        do{        
        try{
        System.out.println("\nIntroduce anyo de fin: "); 
        anyoFin = scan.nextInt();
        matcher = patronAnyo.matcher(String.valueOf(anyoFin));
         if(!matcher.find())
                 { 
                    throw new Exception("");                 
                 }
         }catch(InputMismatchException ime){System.out.println("\nDebes introducir digitos\n"); anyoFin =0;}
        catch(Exception e){System.out.println("\nDebes introducir un mes  válido\n"); anyoFin =0;}
        }while(anyoFin  ==0);
        
        
        return new GregorianCalendar(anyoFin, mesFin ,diaFin);
        

    }
    //Primera Mejora 
    public static void ModificarSaldo()
    {
         int idObjeto,idPropietario,coste;
     Usuario usuarioAct=null;

     
       
       do
        try{
            
            System.out.println("\nIntroduce el numero de usuario: ");   
            idPropietario = scan.nextInt();           
                 
            for (Usuario user : Usuarios)
            {           
                
                    if( user.getIDUsuario() == idPropietario )
                    {
                    flag=true;
                    usuarioAct=user;
                    }
            }     
                if(!flag)
                         throw new Exception("");
        
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idPropietario = 0;}
        catch(Exception e){ System.out.println("El usuario no esta dado de alta");idPropietario = 0;}
        while(idPropietario==0);
       
       flag=false;
       
       
        do{       
            System.out.println("\nIntroduce el id del objeto: ");       
            try{
            scan= new Scanner(System.in);
            idObjeto = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idObjeto = 0;}
        }while(idObjeto == 0); 
        
       do{       
            System.out.println("\nIntroduce el nuevo coste: ");       
            try{
            scan= new Scanner(System.in);    
            coste = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); coste = -1;}
        }while(coste == -1);
       
        try{
        usuarioAct.modificarCosteObjeto(usuarioAct.devolverObjeto(idObjeto),coste);
        }catch(Exception e){System.out.print("El objeto no existe\n");}
    }
    //Segunda Mejora
     public static void PortabilidadSaldos()
    {
                  
        PrintStream fichero = null;
        PrintStream stdout = System.out;
        try {
            fichero = new PrintStream(new File("fichero.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.setOut(fichero);
            MostrarSaldos();
            System.setOut(stdout);
            System.out.println("Guardado");
         
    }
     //Tercera Mejora
     public static void EliminarUsuario()
    {
        
    int idObjeto,idPropietario;    
     Usuario usuarioAct=null;
      
     
      
        try{
            
            System.out.println("\nIntroduce el numero de usuario: ");   
            idPropietario = scan.nextInt();           
                 
            for (Usuario user : Usuarios)
            {           
                
                    if( user.getIDUsuario() == idPropietario )
                    {
                    flag=true;
                    Usuarios.remove(user);
                    }
            }     
                if(!flag)
                         throw new Exception("");
        
        }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n");}
        catch(Exception e){ System.out.println("El usuario no esta dado de alta");}
       
    if (flag)    
    System.out.println("Usuario eliminado\n");
    flag=false;
    }
     
     
}
     
     
     
