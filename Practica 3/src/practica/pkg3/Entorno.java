package practica.pkg3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Entorno implements Serializable{
    
    public static ArrayList<Humano> Humanos = new ArrayList();
    static ArrayList<Cazavampiro> Cazavampiros = new ArrayList();
    static ArrayList<Vampiro> Vampiros = new ArrayList();
    static HashSet<Zombie> Zombies = new HashSet();
    Probabilidades probabilidades = new Probabilidades();
    boolean EventoAleatorio=false;
    private float temperatura = 20;   
    private int DIA = 1;
    
    Random ram = new Random(System.currentTimeMillis());
    

    Humano humano= new Humano(DIA, 0); // Necesario para poder usar la funcion nacer. Averiguar por que.
    Cazavampiro cazavampiros=new Cazavampiro(DIA,0);// Necesario para poder usar la funcion nacer. Averiguar por que.
    
    public Entorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'oscilacionHumanos 2000'       
        for (int i = 0; i < 4000 + (probabilidades.calculoAleatorio(oscilacionHumanos,0)); i++)
        {
            humano.Nacer(DIA,(ram.nextInt((100-60+1))+60));
        }
    
        //Sacar numero aleatorio 'oscilacionCazavampiros 5'        
        for (int i = 0; i < 10 + (probabilidades.calculoAleatorio(oscilacionCazavampiros,0)); i++)
        {
            cazavampiros.Nacer(DIA,(ram.nextInt((100 - 60 + 1)) + 60));
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
        
        if(!Humanos.isEmpty())
            HumanosActuan();
        if(!Cazavampiros.isEmpty())
            CazavampirosActuan();
        if(!Vampiros.isEmpty())
            VampirosActuan();
        if(!Zombies.isEmpty())
            ZombiesActuan();
        
        System.out.println("Otro día más...");
    }
    public void HumanosActuan()
    {    
        for(Humano human: Humanos)
        {            
           if(probabilidades.reproduceHumano(temperatura))
               human.Reproducirse(probabilidades.calculoAleatorio(3,1), DIA);
           if(MuerteHumano())
               human.Morir();                     
        }     
    }
    
    public void CazavampirosActuan()
    {
        for(Cazavampiro hunter : Cazavampiros)
        {            
            if(probabilidades.reproduceHumano(temperatura))
                hunter.Reproducirse(probabilidades.calculoAleatorio(3,1), DIA);           
            if(MuerteHumano())
                hunter.Morir();
            if(ConsigueCazar())
            {
                hunter.caza();
               Vampiros.remove(Vampiros.size()-1);
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
        if(probabilidades.calculoAleatorio(probabilidades.getProb_muerte_nat(), 1) == 1)
            return true;
        /*Muerte por catastrofe*/
        if(probabilidades.calculoAleatorio(probabilidades.getProb_muerte_cat(), 1) == 1)
            return true;
    
        return false;
    }
    
    private void VampirosActuan() 
    {
        boolean haComido = false;
        for(Vampiro vamp : Vampiros)
        {
            if(probabilidades.calculoAleatorio(100,0) >= probabilidades.getProb_comer_vamp())
            {
                /*El vampiro come de un humano y puede morir..*/
                haComido = vamp.Come(haComido);                    
                /*o ser convertido*/
                if(probabilidades.calculoAleatorio(100, 0) >= probabilidades.getProb_conv_vamp())
                    Vampiros.add(new Vampiro(DIA));                
                /*Muerte por inanición*/
               if(haComido == false) 
                   Vampiros.remove(vamp);
               if(Vampiros.isEmpty())
                   System.out.print("Los vampiros se han extinguido.");
            }
            
           
        }
    }

    private void ZombiesActuan() 
    {
        for(Zombie zomb: Zombies)
        {
            if(DIA - zomb.getdiaNacimiento() >= probabilidades.getDias_vida_zomb())
                Zombies.remove(zomb);
            else
            {
                if(probabilidades.calculoAleatorio(probabilidades.getProb_conv_zomb(), 1) == 1)
                {
                    zomb.convierte();
                    ZombieMata(); //Mata al ultimo del array, el cual es el mas lento
                    Zombies.add(new Zombie(DIA));
                }
            }
        }
    }
    
    private void ZombieMata ()
    {
        if(!Humanos.isEmpty() && !Cazavampiros.isEmpty()) //Si no hay ningun grupo extinguido
        {
            if( Humanos.get(Humanos.size()).getVelocidad() <= Cazavampiros.get(Cazavampiros.size()).getVelocidad() )
            {
                Humanos.remove(Humanos.size());
            }
            else if ( Humanos.get(Humanos.size()).getVelocidad() > Cazavampiros.get(Cazavampiros.size()).getVelocidad() )
            {
                Cazavampiros.remove(Cazavampiros.size());
            }
        }
        else if (Humanos.isEmpty())
            Cazavampiros.remove(Cazavampiros.size());
        else if (Cazavampiros.isEmpty())
            Humanos.remove(Humanos.size());
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
    public void InvasionZombie()
    {
        probabilidades.setProb_conv_zomb(3);
    }
    public void DetallesDiaActual()
    {
        this.toString();
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
