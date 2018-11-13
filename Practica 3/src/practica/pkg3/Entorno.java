package practica.pkg3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Entorno implements Serializable{
    
    ArrayList<Humano> Humanos = new ArrayList();
    ArrayList<Cazavampiro> Cazavampiros = new ArrayList();
    ArrayList<Vampiro> Vampiros = new ArrayList();
    ArrayList<Zombie> Zombies = new ArrayList();
    Probabilidades probabilidades = new Probabilidades();
    private int zombN,vampN,humanN,hunterN,humanM,hunterM,vampM,zombM,cazas;
    private float temperatura = 20;   
    private int DIA = 1;
    
    Random ram = new Random(System.currentTimeMillis());
    

   // Humano humano= new Humano(DIA, 0); // Necesario para poder usar la funcion nacer. Averiguar por que.
   // Cazavampiro cazavampiros=new Cazavampiro(DIA,0);// Necesario para poder usar la funcion nacer. Averiguar por que.
    
    public Entorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'oscilacionHumanos 2000'       
        for (int i = 0; i < 4000 + (probabilidades.calculoAleatorio(oscilacionHumanos,0)); i++)
        {
            insertarEnVectorOdenado(false, new Humano(DIA,(ram.nextInt((100-60+1))+60)));
            //humano.Nacer(DIA,(ram.nextInt((100-60+1))+60));
        }
    
        //Sacar numero aleatorio 'oscilacionCazavampiros 5'        
        for (int i = 0; i < 10 + (probabilidades.calculoAleatorio(oscilacionCazavampiros,0)); i++)
        {
            insertarEnVectorOdenado(true, new Cazavampiro(DIA,(ram.nextInt((100-60+1))+60)));
            //cazavampiros.Nacer(DIA,(ram.nextInt((100 - 60 + 1)) + 60));
        }
        
        //Sacar numero aleatorio oscilacionVampiros 5' 
        
        for (int i = 0; i < 15 + (probabilidades.calculoAleatorio(oscilacionVampiros,0)); i++)
        {
            Vampiro vampiroAntecesor = new Vampiro(DIA);
            Vampiros.add(vampiroAntecesor);
        }
        
        //Sacar numero aleatorio 'oscilacionZombies 10'      
        for (int i = 0; i < 20 + (probabilidades.calculoAleatorio(oscilacionZombies,0)); i++)
        {
            Zombie zombieAntecesor = new Zombie(DIA);
            Zombies.add(zombieAntecesor);
        }
        
    }
    
    public void AvanzarDia()
    {
        DIA++;      
        
        temperatura = probabilidades.modificarTemperatura(temperatura);
        zombN=vampN=humanN=hunterN=humanM=hunterM=vampM=zombM=cazas=0;
        if(!Humanos.isEmpty())
            HumanosActuan();
        if(!Cazavampiros.isEmpty())
            CazavampirosActuan();
        if(!Vampiros.isEmpty())
            VampirosActuan();
        if(!Zombies.isEmpty())
            ZombiesActuan();
    }
    public void HumanosActuan()
    {    
        int p;        
        
        for(int i=0 ; i<Humanos.size();i++)
        {            
           if(probabilidades.reproduceHumano(temperatura))
           {
                p = probabilidades.calculoAleatorio(3,1);
                for(int j =0 ; j<p; j++)
                {
                insertarEnVectorOdenado(false,(Humano)Humanos.get(i).Reproducirse(DIA));
                humanN++;
                }
           }
           if(MuerteHumano())
           {             
               Humanos.remove(i);
               humanM++;
           }
        }         
       
        
    }
    
    public void CazavampirosActuan()
    {
        int p=0;
                
        for(int i=0 ; i<Cazavampiros.size();i++)
        {            
            if(probabilidades.reproduceHumano(temperatura)){
                p = probabilidades.calculoAleatorio(3,1);
                for(int j=0 ; j<p; j++)
                {
                 insertarEnVectorOdenado(true,(Cazavampiro)Cazavampiros.get(i).Reproducirse(DIA));
                 hunterN++;
                }
            }  
            if(MuerteHumano())
            {                
                 Cazavampiros.remove(i); 
                hunterM++;
            }
            if(ConsigueCazar())
            {
               Cazavampiros.get(i).caza();
               if(!Vampiros.isEmpty())
               {    
                 Vampiros.remove(Vampiros.size()-1);
                 cazas++;
               }
            }
        } 
        
    }
    
    private boolean ConsigueCazar() 
    {
     if(probabilidades.calculoAleatorio(probabilidades.getProb_cazar(), 1) == 1)
         return true;
     return false;
    }
    public boolean MuerteHumano()
    {
        /*Muerte natural*/
        if((probabilidades.calculoAleatorio(probabilidades.getProb_muerte_nat(), 1) == 1))
            return true;
        /*Muerte por catastrofe*/
        if(probabilidades.calculoAleatorio(probabilidades.getProb_muerte_cat(), 1) == 1)
            return true;
    
        return false;
    }
    
    private void VampirosActuan() 
    {
        boolean haComido = false;
        ArrayList <Vampiro> aux= (ArrayList <Vampiro>)Vampiros.clone();
        
        for(int i=0 ; i<Vampiros.size();i++)
        {
            if(probabilidades.calculoAleatorio(100,0) >= probabilidades.getProb_comer_vamp())
            {
                /*El vampiro intenta comer de un humano */
                if (!Humanos.isEmpty())    
                {   /*y  este puede morir..*/                   
                    haComido =Vampiros.get(i).Come(haComido);                    
                    Humanos.remove(Probabilidades.calculoAleatorio(Humanos.size()-1,0));humanM++;
                               
                    /*o ser convertido*/
                    if(probabilidades.calculoAleatorio(100, 0) >= probabilidades.getProb_conv_vamp())
                    {
                       Vampiros.add(new Vampiro(DIA));vampN++;
                    }
                }
                /*Si no puede comer muere por inanición*/
               if(haComido == false)
               {                 
                  Vampiros.remove(i);
                  vampM++;
               }
            }           
        }
       
        
    }

    private void ZombiesActuan() 
    {
               
        for(int i=0 ; i<Zombies.size();i++)
        {
            if(DIA - Zombies.get(i).getdiaNacimiento() >= probabilidades.getDias_vida_zomb())
            {
                Zombies.remove(i);
                zombM++;
            }
            else
            {
                if(probabilidades.calculoAleatorio(probabilidades.getProb_conv_zomb(), 1) == 1)
                {
                   if( ZombieMata())//Mata al ultimo del array, el cual es el mas lento
                   {
                    Zombies.get(i).convierte(); 
                    zombN++;                   
                    Zombies.add(new Zombie(DIA));
                   }
                }
            }
        }
        
    }
    
    private boolean ZombieMata ()
    {
        if(!Humanos.isEmpty() && !Cazavampiros.isEmpty()) //Si no hay ningun grupo extinguido
        {
            if( Humanos.get(Humanos.size()-1).getVelocidad() <= Cazavampiros.get(Cazavampiros.size()-1).getVelocidad() )//Comparamos el último de los humanos con el de los CazaVampiros y damos priorida los humanos
            {
                Humanos.remove(Humanos.size()-1);
                humanM++;
            }
            else if ( Humanos.get(Humanos.size()-1).getVelocidad() > Cazavampiros.get(Cazavampiros.size()-1).getVelocidad() )//Comparamos el último de los humanos con el de los CazaVampiros
            {
                Cazavampiros.remove(Cazavampiros.size()-1);
                hunterM++;
            }
        }
        else if (Humanos.isEmpty() && !Cazavampiros.isEmpty()) //Si no quedan humanos pero si Cazavampiros
        {
            Cazavampiros.remove(Cazavampiros.size()-1);
            hunterM++;
        }
        else if (Cazavampiros.isEmpty() && !Humanos.isEmpty()) //Si no quedan cazavampiros pero si humanos
        {
            Humanos.remove(Humanos.size()-1);
            humanM++;
        }    
        else //Todos muertos
            return false;
       return true;
    }
    
    public void Avanzar10Dias()
    {
        for(int i = 0; i < 10; i++)
        {
            AvanzarDia();            
        }
    }
    
    public void Glaciacion()
    {
        temperatura = temperatura - 10;
    }
    
    public void CalentamientoGlobal()
    {
        temperatura = temperatura + 10;
    }
    //Mata la mitad de humanos y cazavampiros
    public void EpidemiaGlobal()
    {
        
         for(int i=0 ; i<(Humanos.size()-1)/2;i++)
        {    
            Humanos.remove(probabilidades.calculoAleatorio(Humanos.size()-1,0));
        }
          for(int i=0 ; i<(Cazavampiros.size()-1)/2;i++)
        {    
            Cazavampiros.remove(probabilidades.calculoAleatorio(Cazavampiros.size()-1,0));
        }
    }
    //Modifica probabilidaddes de convertir
    public void InvasionZombie(boolean activado)
    {
        if(activado)
            probabilidades.setProb_conv_zomb(3);
        else 
            probabilidades.setProb_conv_zomb(10);
    }
    //Los vampiros se vuelven mas agresivos
     public void SedDeSangre(boolean activado)
    {
        if(activado){
            probabilidades.setProb_comer_vamp(10);probabilidades.setProb_conv_vamp(60);}
        else {
            probabilidades.setProb_comer_vamp(50);probabilidades.setProb_conv_vamp(50);}
    }
    public String DetallesDiaActual()
    {
        return this.toString();
    }
    public String Resumen()
    {
        return "Los humanos: \n" + Humanos.toString() + "Los Cazavampiros: \n" + Cazavampiros.toString() + "Los Vampiros: \n" + Vampiros.toString() + "Los Zombies: \n" + Zombies.toString();
    }
    //Inserta en los vectores de humanos y cazavampiros de forma ordenada por su velocidad
    public void insertarEnVectorOdenado(boolean hunter, Object o)
    {
        boolean insertado = false;
        int i = Humanos.size();
        Humano humano;
        Cazavampiro cazavampiro;
    
        if(!hunter){     
           humano=(Humano)o;   
        try{            
        do
        {                 
            if (humano.getVelocidad() < Humanos.get(i-1).getVelocidad())
            {
                Humanos.add(i,humano);
                insertado = true;
            }
            else
                i--;
        }while(insertado != true);
        }catch(IndexOutOfBoundsException e){Humanos.add(i,humano);}       
      
        }else{
             cazavampiro=(Cazavampiro)o;
            i = Cazavampiros.size();
            try{
                do
                {
                    if (cazavampiro.getVelocidad()< Cazavampiros.get(i-1).getVelocidad())
                    {
                        Cazavampiros.add(i, cazavampiro);
                        insertado = true;
                    }
                    else
                        i--;
                }while(insertado != true);
            }catch(IndexOutOfBoundsException e){Cazavampiros.add(i,cazavampiro);}
        }

    }
    public String ResumenDia()
    {
        return  "\nHan nacido: " + humanN + " humanos y " + hunterN + " cazavampiros" +
                "\nhan muerto: " + humanM + " humanos y " + hunterM + " cazavampiros" +
                "\nSe han convertido: " + vampN + " vampiros y " + zombN + " zombies" +
                "\nLos Cazavampiros han cazado " + cazas + " vampiros\nhan muerto por no comer " + vampM +
                "\nhan muerto " + zombM + " zombies\n";
    
    }
    @Override
    public String toString()
    {
        return  "\nDIA: " + DIA + 
                "\nTemperatura actual: " + temperatura +
                "\nHumanos: " + Humanos.size() +
                "\nCazavampiros: " + Cazavampiros.size() +
                "\nVampiros: " + Vampiros.size() +
                "\nZombie: " + Zombies.size()+"\n";
    }
}
