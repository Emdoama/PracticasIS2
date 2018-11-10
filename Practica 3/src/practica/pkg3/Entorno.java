package practica.pkg3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Entorno implements Serializable{
    
    public static ArrayList<Humano> Humanos = new ArrayList();
    static HashSet<Cazavampiro> Cazavampiros = new HashSet();
    static ArrayList<Vampiro> Vampiros = new ArrayList();
    static HashSet<Zombie> Zombies = new HashSet();
    Probabilidades probabilidades = new Probabilidades();
    boolean EventoAleatorio=false;
    
    private float temperatura = 20;   
    private int DIA = 1;
    Random ram = new Random(System.currentTimeMillis());
    

    void CrearEntorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'oscilacionHumanos 2000'       
        for (int i = 0; i < 4000 + (probabilidades.calculoAleatorio(oscilacionHumanos,0)); i++)
        {
            Humano humanoAntecesor = new Humano(DIA,(ram.nextInt((100-60+1))+60));
            /*ordenar los humanos por la velocidad mayor a menor*/
            Humanos.add(humanoAntecesor);
        }
    
        //Sacar numero aleatorio 'oscilacionCazavampiros 5'        
        for (int i = 0; i < 10 + (probabilidades.calculoAleatorio(oscilacionCazavampiros,0)); i++)
        {
            Cazavampiro cazavampirosAntecesor = new Cazavampiro(DIA,(ram.nextInt((100-60+1))+60));
            /*ordenar los humanos por la velocidad mayor a menor*/
            Cazavampiros.add(cazavampirosAntecesor);
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
    
    public void avanzarDia()
    {
        DIA++;
       
        if (EventoAleatorio)
        {
            
        }else{
            temperatura = probabilidades.modificarTemperatura(temperatura);
        }
        
        humanosActuan();
        cazavampirosActuan();
        vampirosActuan();
        zombiesActuan();
        
        System.out.println("Otro día más...");
    }
    public void humanosActuan()
    {    
        for(Humano human: Humanos)
        {            
           if(probabilidades.reproduceHumano(temperatura))
               human.Reproducirse(probabilidades.calculoAleatorio(3,1),DIA);
           if(muerteHumano())
               human.Morir();                     
        }     
    }
    
    public void cazavampirosActuan()
    {
        for(Cazavampiro hunter: Cazavampiros)
        {            
           if(probabilidades.reproduceHumano(temperatura))
               hunter.Reproducirse(probabilidades.calculoAleatorio(3,1), DIA);           
           if(muerteHumano())
               hunter.Morir();
           if(consigueCazar())
           {
               hunter.caza();
               Vampiros.remove(Vampiros.size()-1);
           }
        } 
    
    }
    
    private boolean consigueCazar() 
    {
     if(probabilidades.calculoAleatorio(probabilidades.getProb_cazar(),1)==1)
         return true;
     return false;
    }    
    
    
    public boolean muerteHumano()
    {
        /*Muerte natural*/
        if(probabilidades.calculoAleatorio(probabilidades.getProb_muerte_nat(), 1)== 1)
            return true;
        /*Muerte por catastrofe*/
        if(probabilidades.calculoAleatorio(probabilidades.getProb_muerte_cat(), 1)== 1)
            return true;
    
        return false;
    }
    
    private void vampirosActuan() 
    {
        boolean ha_comido = true;
        for(Vampiro vamp : Vampiros)
        {
            if(probabilidades.calculoAleatorio(100,0) >= probabilidades.getProb_comer_vamp())
            {
                try
                {
                    vamp.come();
                    /*Si come el humano desaparece de los humanos, y puede morir...*/
                    //Humanos.remove(probabilidades.calculoAleatorio(Humanos.size(), 0));
                    MatarHumano();
                    /*o ser convertido*/
                    if(probabilidades.calculoAleatorio(100,0) >= probabilidades.getProb_conv_vamp())
                        Vampiros.add(new Vampiro(DIA));                
                }catch(Exception e){ha_comido=false;}
            }
            /*Muerte por inanición*/
            if(ha_comido==false) 
                Vampiros.remove(vamp);
        }
    }

    private void zombiesActuan() 
    {
        for(Zombie zomb: Zombies)
        {
            if(DIA - zomb.getdiaNacimiento()>=probabilidades.getDias_vida_zomb())
                Zombies.remove(zomb);
            else
            {
                if(probabilidades.calculoAleatorio(probabilidades.getProb_conv_zomb(),1)==1)
                {
                    zomb.convierte();
                    //Humanos.remove(getHumanoMasLento());
                    MatarHumano();
                    Zombies.add(new Zombie(DIA));
                }
            }
        }
    }
    /*
        Mantiene la coleccion de Humanos ordenadas, donde el ultimo es el mas lento.
    */
    /*private void OrdenarHumanosVelocidad()
    {
        Humanos.add(e)
    }*/
    /*private Humano getHumanoMasLento() 
    {
        Humano o= new Humano(DIA,0);
        int aux=100;
        
        
        for(Humano human: Humanos)
            
            if(human.getVelocidad() < aux)
            {
                o=human;
                aux=human.getVelocidad();
            }    
            
        for(Cazavampiro hunter: Cazavampiros)    
            if(hunter.getVelocidad() < aux)
            {
                o=hunter;
                aux=hunter.getVelocidad();
            } 
        
        return o;
    }*/
    
    private void MatarHumano ()
    {
        Humanos.remove(Humanos.size());
    }
}
