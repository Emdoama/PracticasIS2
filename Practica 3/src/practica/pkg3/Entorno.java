package practica.pkg3;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Alber
 */
public class Entorno implements Serializable{
    
    static HashSet<Humano> Humanos = new HashSet();
    static HashSet<Cazavampiro> Cazavampiros = new HashSet();
    static HashSet<Vampiro> Vampiros = new HashSet();
    static HashSet<Zombie> Zombies = new HashSet();
    
    
    float temperatura = 20;
    int DIA = 1;
    Random ram = new Random(System.currentTimeMillis());
    

    void CrearEntorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'primerosHumanos 2000'       
        for (int i = 0; i < 4000 + (calculoAleatorio(oscilacionHumanos,0)); i++)
        {
            Humano humanoAntecesor = new Humano(DIA);
            Humanos.add(humanoAntecesor);
        }
    
        //Sacar numero aleatorio 'primerosCazavampiros 5'        
        for (int i = 0; i < 10 + (calculoAleatorio(oscilacionCazavampiros,0)); i++)
        {
            Cazavampiro cazavampirosAntecesor = new Cazavampiro(DIA);
            Cazavampiros.add(cazavampirosAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosHumanos' 5
        
        for (int i = 0; i < 15 + (calculoAleatorio(oscilacionVampiros,0)); i++)
        {
            Vampiro vampiroAntecesor = new Vampiro();
            Vampiros.add(vampiroAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosZombies' 10      
        for (int i = 0; i < 20 + (calculoAleatorio(oscilacionZombies,0)); i++)
        {
            Zombie zombieAntecesor = new Zombie();
            Zombies.add(zombieAntecesor);
        }
    }
    
    public void modificarTemperatura()
    {
        int aux = calculoAleatorio(100,1);
        
        if (temperatura >= 22)
        {
            if (aux  >= 45)
                temperatura = (float) (temperatura - 0.5);
            else
                temperatura =  (float) (temperatura + 0.5);
        }       
        else if ( (18 < temperatura) && (temperatura < 22) )
        {
            if (aux  < 65)
                temperatura = (float)(temperatura + 0.5);
            if ( (aux  >= 65) && (aux < 95) )
                temperatura = (float)(temperatura - 0.5);
        }
        else if (temperatura <= 18)
        {
            if (aux  >= 45)
                temperatura = (float)(temperatura + 0.5);
            else
                temperatura =  (float)(temperatura - 0.5);
        }
    }
    
    public void avanzarDia()
    {
        DIA++;
        /*Si eventoAleatorio*/
        /*then eventoAleatorio*/
        /*sino si */
        modificarTemperatura();
        
        humanosActuan();
        cazavampirosActuan();
        /*vampirosActuan*/
        /*zombiesActuan*/
        
        System.out.println("Otro día más...");
    }
    public void humanosActuan()
    {
        
        for(Humano human: Humanos)
        {            
           if(reproduceHumano())human.Reproducirse(calculoAleatorio(3,1));
           if(muerteHumano())human.Morir();                     
        }
       
          
    }
    
    public void cazavampirosActuan()
    {
        for(Cazavampiro hunter: Cazavampiros)
        {            
           if(reproduceHumano())
               hunter.Reproducirse(calculoAleatorio(3,1));           
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
     if(calculoAleatorio(3,1)==1)return true;
     return false;
    }    
    
    
    public boolean muerteHumano()
    {
        /*Muerte natural*/
        if(calculoAleatorio(500,1)== 1)
            return true;
        /*Muerte por catastrofe*/
        if(calculoAleatorio(300,1)== 1)
            return true;
    
        return false;
    }
     public boolean reproduceHumano()
    {
        if(temperatura>=22)
        {
            if(calculoAleatorio(15,1)== 1)
                return true;           
        }       
        else if ( (18 < temperatura) && (temperatura < 22) )
        {
           if(calculoAleatorio(15,1)== 1)
                return true;            
        }
        else if (temperatura <= 18)
        {
           if(calculoAleatorio(15,1)== 1)
                return true;            
        }
        return false;
    }
     
     public int calculoAleatorio(int hasta, int desde)
     {
        return ram.nextInt(hasta-desde+1)+desde;
     }

}
