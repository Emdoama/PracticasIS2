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
    static HashSet<Cazavampiros> HumanosCazavampiros = new HashSet();
    static HashSet<Vampiro> Vampiros = new HashSet();
    static HashSet<Zombie> Zombies = new HashSet();
    
    static float temperatura = 20;
    static int DIA = 1;
    Random ram = new Random(System.currentTimeMillis());
    

    void CrearEntorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'primerosHumanos 2000'       
        for (int i = 0; i < 4000 + (ram.nextInt(oscilacionHumanos)); i++)
        {
            Humano humanoAntecesor = new Humano(DIA);
            Humanos.add(humanoAntecesor);
        }
    
        //Sacar numero aleatorio 'primerosCazavampiros 5'        
        for (int i = 0; i < 10 + (ram.nextInt(oscilacionCazavampiros)); i++)
        {
            Cazavampiros cazavampirosAntecesor = new Cazavampiros(DIA);
            HumanosCazavampiros.add(cazavampirosAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosHumanos' 5
        
        for (int i = 0; i < 15 + (ram.nextInt(oscilacionVampiros)); i++)
        {
            Vampiro vampiroAntecesor = new Vampiro();
            Vampiros.add(vampiroAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosZombies' 10      
        for (int i = 0; i < 20 + (ram.nextInt(oscilacionZombies)); i++)
        {
            Zombie zombieAntecesor = new Zombie();
            Zombies.add(zombieAntecesor);
        }
    }
    
    public void modificarTemperatura()
    {
        int aux = ram.nextInt(100);
        
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
}
