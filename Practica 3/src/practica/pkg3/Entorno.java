package practica.pkg3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Alber
 */
public class Entorno implements Serializable{
    
    static ArrayList<Humano> Humanos = new ArrayList();
    static HashSet<Cazavampiro> Cazavampiros = new HashSet();
    static ArrayList<Vampiro> Vampiros = new ArrayList();
    static HashSet<Zombie> Zombies = new HashSet();
    
    private int prob_conv_zomb= 10;
    private int prob_conv_vamp= 50;
    private int dias_vida_zomb= 8;
    private int prob_comer_vamp=50;
    private int prob_muerte_nat=500;
    private int prob_muerte_cat=300;
    private int prob_cazar= 3;
     
    private float temperatura = 20;   
    private int DIA = 1;
    Random ram = new Random(System.currentTimeMillis());
    

    void CrearEntorno()
    {
        int oscilacionHumanos = 2000, oscilacionCazavampiros = 5, oscilacionVampiros = 5, oscilacionZombies = 10;
        
        //Sacar numero aleatorio 'oscilacionHumanos 2000'       
        for (int i = 0; i < 4000 + (calculoAleatorio(oscilacionHumanos,0)); i++)
        {
            Humano humanoAntecesor = new Humano(DIA,(ram.nextInt((100-60+1))+60));
            Humanos.add(humanoAntecesor);
        }
    
        //Sacar numero aleatorio 'oscilacionCazavampiros 5'        
        for (int i = 0; i < 10 + (calculoAleatorio(oscilacionCazavampiros,0)); i++)
        {
            Cazavampiro cazavampirosAntecesor = new Cazavampiro(DIA,(ram.nextInt((100-60+1))+60));
            Cazavampiros.add(cazavampirosAntecesor);
        }
        
        //Sacar numero aleatorio oscilacionVampiros 5' 
        
        for (int i = 0; i < 15 + (calculoAleatorio(oscilacionVampiros,0)); i++)
        {
            Vampiro vampiroAntecesor = new Vampiro(DIA);
            Vampiros.add(vampiroAntecesor);
        }
        
        //Sacar numero aleatorio 'oscilacionZombies 10'      
        for (int i = 0; i < 20 + (calculoAleatorio(oscilacionZombies,0)); i++)
        {
            Zombie zombieAntecesor = new Zombie(DIA);
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
        vampirosActuan();
        zombiesActuan();
        
        System.out.println("Otro día más...");
    }
    public void humanosActuan()
    {
        
        for(Humano human: Humanos)
        {            
           if(reproduceHumano())
               human.Reproducirse(calculoAleatorio(3,1),DIA);
           if(muerteHumano())
               human.Morir();                     
        }
       
          
    }
    
    public void cazavampirosActuan()
    {
        for(Cazavampiro hunter: Cazavampiros)
        {            
           if(reproduceHumano())
               hunter.Reproducirse(calculoAleatorio(3,1), DIA);           
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
     if(calculoAleatorio(prob_cazar,1)==1)
         return true;
     return false;
    }    
    
    
    public boolean muerteHumano()
    {
        /*Muerte natural*/
        if(calculoAleatorio(prob_muerte_nat,1)== 1)
            return true;
        /*Muerte por catastrofe*/
        if(calculoAleatorio(prob_muerte_cat,1)== 1)
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
           if(calculoAleatorio(30,1)== 1)
                return true;            
        }
        else if (temperatura <= 18)
        {
           if(calculoAleatorio(50,1)== 1)
                return true;            
        }
        return false;
    }
     
     public int calculoAleatorio(int hasta, int desde)
     {
        return ram.nextInt(hasta-desde+1)+desde;
     }

    private void vampirosActuan() 
    {
        boolean ha_comido=true;
        for(Vampiro vamp: Vampiros)
        {
            if(calculoAleatorio(100,0)>=prob_comer_vamp)
            {
                try
                {
                    vamp.come();
                    /*Si come el humano desaparece de los humanos, y puede morir...*/
                    Humanos.remove(calculoAleatorio(Humanos.size(),0));
                    /*o ser convertido*/
                    if(calculoAleatorio(100,0)>=prob_conv_vamp)
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
            if(DIA - zomb.getdiaNacimiento()>=dias_vida_zomb)
                Zombies.remove(zomb);
            else
            {
                if(calculoAleatorio(prob_conv_zomb,1)==1)
                {
                    zomb.convierte();
                    Humanos.remove(getHumanoMasLento());
                    Zombies.add(new Zombie(DIA));
                }
            }
        }
        
    }

    private Humano getHumanoMasLento() 
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
    }
    
  public void setProb_conv_zomb(int prob_conv_zomb) {
        this.prob_conv_zomb = prob_conv_zomb;
    }

    public void setProb_conv_vamp(int prob_conv_vamp) {
        this.prob_conv_vamp = prob_conv_vamp;
    }

    public void setDias_vida_zomb(int dias_vida_zomb) {
        this.dias_vida_zomb = dias_vida_zomb;
    }

    public void setProb_comer_vamp(int prob_comer_vamp) {
        this.prob_comer_vamp = prob_comer_vamp;
    }

    public void setProb_muerte_nat(int prob_muerte_nat) {
        this.prob_muerte_nat = prob_muerte_nat;
    }

    public void setProb_muerte_cat(int prob_muerte_cat) {
        this.prob_muerte_cat = prob_muerte_cat;
    }

    public void setProb_cazar(int prob_cazar) {
        this.prob_cazar = prob_cazar;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public int getProb_conv_zomb() {
        return prob_conv_zomb;
    }

    public int getProb_conv_vamp() {
        return prob_conv_vamp;
    }

    public int getProb_comer_vamp() {
        return prob_comer_vamp;
    }

    public int getProb_muerte_nat() {
        return prob_muerte_nat;
    }

    public int getProb_muerte_cat() {
        return prob_muerte_cat;
    }

    public int getProb_cazar() {
        return prob_cazar;
    }

    public float getTemperatura() {
        return temperatura;
    }

}
