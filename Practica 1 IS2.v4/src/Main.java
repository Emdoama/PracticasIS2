import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    static Scanner scan= new Scanner(System.in);
    static ArrayList <Usuario> Usuarios = new ArrayList();
    static Usuario user;
    static int caso;
    static boolean flag=false;
    
   
    
    
    public static void main (String Args[])
        {
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
        
        
        System.out.println("Bienvenido a la aplicación MaxRend\n");

        do
        {
            System.out.println("\nSelecciona una de las siguientes opciones:\n"+
                               "1. Alta usuario\n" +
                               "2. Alta objeto\n" +
                               "3. Alquiler de objeto\n" + 
                               "4. Listar todos los objetos\n" +
                               "5. Baja objeto\n" +
                               "6. Mostrar saldos\n" +
                               "7. Salir\n" + 
                               "8. Modificar Saldo\n" );
           
            try{
                scan= new Scanner(System.in);
                caso = scan.nextInt();
               
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un número del 1 al 7, por favor.\n"); }
                     
            
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
                default:
                    System.out.println("La opción elegida no es valida\n");                          
            }
            
        }while(caso!=7);
    }
      
    
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
         
      
        if(name!= null && mail!= null)
        {
            user = new Usuario(name, mail);
            Usuarios.add(user);
        }else
            System.out.println("Este usuario no se creara debido a valores incorrectos en los campos, por favor intentelo de nuevo\n");
    }         
    
    public static void AltaObjeto()
    {
        int idPropietario, coste;
        Calendar inicio = null, fin = null;
        String descripcion;
        Pattern patronDia = Pattern.compile("^[1-31]+$");
        Pattern patronMes = Pattern.compile("^[1-12]+$");
        Pattern patronAnyo = Pattern.compile("^(20)[18-99]+$");
        Matcher matcher;

        
        System.out.println("\nLista de usuarios: ");
        for (int i = 0; i < Usuarios.size(); i++)
        {
            Usuarios.get(i).toString(); 
            System.out.println("\n");
        }
        
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
       
        inicio = pedirFechaInicio();     
        fin=  pedirFechaFin();        
                        
        
        System.out.println("\nIntroduce el coste: ");        
        coste = scan.nextInt();
        
        Objeto objeto = new Objeto(idPropietario, descripcion, inicio, fin, coste);
        
        
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
    
    public static void AlquilarObjeto()
    {
        int idObjeto, idUsuario;
        Calendar inicio = null, fin = null;        
        Usuario usuarioAct=null;
        Objeto objetoAct=null;
	
        
        
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
        
        for(Usuario user: Usuarios)
        {
            user.mostrarObjetos();   
        
        }       
        
        do{    
            System.out.println("\nIntroduce el id del objeto: ");       
            try{
                idObjeto = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idObjeto = 0;}
        }while(idObjeto == 0); 
        
        try{
            objetoAct = usuarioAct.getObjeto(idObjeto);
        }catch(Exception e){System.out.println("El objeto no existe"); }
        
	
        inicio = pedirFechaInicio();     
        fin=  pedirFechaFin();        
                             
      
        Alquiler alquilerAct = new Alquiler(usuarioAct, objetoAct, inicio, fin); 
        objetoAct.alquilarObjeto(alquilerAct);
        
    }
    
    public static void ListarObjetos()
    {
        for (int i = 0; i < Usuarios.size(); i++)
        { 
            System.out.println("PROPIETARIO " + i + "\n");
            System.out.println("Nombre del propietario: " + Usuarios.get(i).getNombre() + "\nCorreo Electronico: " + Usuarios.get(i).getMail() + "\n");             
            Usuarios.get(i).mostrarObjetosyAlquileres();
                      
        }
    }
    
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
        flag =false;
        System.out.println("El importe total para la startup es de:  " +cant + " euros\n");    
    }
    
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
       
       
       
       
        do{       
            System.out.println("\nIntroduce el id del objeto: ");       
            try{
            idObjeto = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); idObjeto = 0;}
        }while(idObjeto == 0); 
        
       do{       
            System.out.println("\nIntroduce el nuevo coste: ");       
            try{
            coste = scan.nextInt();
            }catch(InputMismatchException ime){System.out.println("\nDebes introducir un ID válido\n"); coste = -1;}
        }while(coste == -1);
       
        try{
        usuarioAct.modificarCosteObjeto(usuarioAct.devolverObjeto(idObjeto),coste);
        }catch(Exception e){System.out.print("El objeto no existe\n");}
    }
    
}
     
     
     
