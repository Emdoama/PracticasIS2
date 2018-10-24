/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg3;

import java.io.Serializable;
import java.util.HashSet;

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
    static int DIA = 0;    

    void CrearEntorno()
    {
        int primerosHumanos = 2000, primerosCazavampiros = 5, primerosVampiros = 5, primerosZombies = 10;
        
        //Sacar numero aleatorio 'primerosHumanos 2000'
        primerosHumanos = (int) (Math.random() * primerosHumanos);
        for (int i = 0; i <= 4000 + primerosHumanos; i++)
        {
            Humano humanoAntecesor = new Humano();
            Humanos.add(humanoAntecesor);
        }
    
        //Sacar numero aleatorio 'primerosCazavampiros 5'
        primerosCazavampiros = (int) (Math.random() * primerosHumanos);
        for (int i = 0; i <= 10 + primerosCazavampiros; i++)
        {
            Cazavampiros cazavampirosAntecesor = new Cazavampiros();
            HumanosCazavampiros.add(cazavampirosAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosHumanos' 5
        primerosVampiros = (int) (Math.random() * primerosHumanos);
        for (int i = 0; i <= 15 + primerosVampiros; i++)
        {
            Vampiro vampiroAntecesor = new Vampiro();
            Vampiros.add(vampiroAntecesor);
        }
        
        //Sacar numero aleatorio 'primerosZombies' 10
        primerosZombies = (int) (Math.random() * primerosHumanos);
        for (int i = 0; i <= 20 + primerosZombies; i++)
        {
            Zombie zombieAntecesor = new Zombie();
            Zombies.add(zombieAntecesor);
        }
    }
    
    public void modificarTemperatura()
    {
        int aux = (int) (Math.random() * 99);
        
        if (temperatura >= 22)
        {
            if (aux  >= 45)
                temperatura = (float) (temperatura - (float)0.5);
            else
                temperatura =  (temperatura + (float)0.5);
        }       
        else if ( (18 < temperatura) && (temperatura < 22) )
        {
            if (aux  < 65)
                temperatura = (temperatura + (float)0.5);
            if ( (aux  >= 65) && (aux < 95) )
                temperatura = (temperatura - (float)0.5);
        }
        else if (temperatura <= 18)
        {
            if (aux  >= 45)
                temperatura = (temperatura + (float)0.5);
            else
                temperatura = (temperatura - (float)0.5);
        }
    }
}
