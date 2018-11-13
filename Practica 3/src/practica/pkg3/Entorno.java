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
    boolean EventoAleatorio=false;
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
        ArrayList<Humano> aux = (ArrayList<Humano>)Humanos.clone();
         ArrayList <Humano> nuevos = new ArrayList();
        for(Humano human: Humanos)
        {            
           if(probabilidades.reproduceHumano(temperatura)){
                p = probabilidades.calculoAleatorio(3,1);
                for(int i =0 ; i<p; i++){
                nuevos.add((Humano)human.Reproducirse(DIA));
                }
           }
           if(MuerteHumano())
               human.Morir();                 
        }         
        for(Humano human: aux) if(!human.isVivo())Humanos.remove(human);
        for (Humano human : nuevos) insertarEnVectorOdenado(false, human);
        
    }
    
    public void CazavampirosActuan()
    {
        int p=0;
         ArrayList<Cazavampiro> aux = (ArrayList<Cazavampiro>)Cazavampiros.clone();
         ArrayList <Cazavampiro> nuevos = new ArrayList();
        for(Cazavampiro hunter : aux)
        {            
            if(probabilidades.reproduceHumano(temperatura)){
                p = probabilidades.calculoAleatorio(3,1);
                for(int i =0 ; i<p; i++){
                nuevos.add((Cazavampiro)hunter.Reproducirse(DIA));
                }
            }  
            if(MuerteHumano())
                hunter.Morir();
            if(ConsigueCazar())
            {
               hunter.caza();
               if(!Vampiros.isEmpty())
                 Vampiros.remove(Vampiros.size()-1);
               
            }
        } 
        for(Cazavampiro hunter : aux) if(!hunter.isVivo())Cazavampiros.remove(hunter);        
        for (Cazavampiro hunter : nuevos) insertarEnVectorOdenado(true, hunter);
        
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
        if((probabilidades.calculoAleatorio(probabilidades.getProb_muerte_nat(), 1) == 1) || Humanos.size() > 6500)
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
        ArrayList <Vampiro> nuevos = new ArrayList();
        for(Vampiro vamp : aux)
        {
            if(probabilidades.calculoAleatorio(100,0) >= probabilidades.getProb_comer_vamp())
            {
                /*El vampiro intenta comer de un humano */
                if (!Humanos.isEmpty())    
                {   /*y  este puede morir..*/                   
                    haComido =vamp.Come(haComido);                    
                    Humanos.remove(Probabilidades.calculoAleatorio(Humanos.size(),1));
                               
                    /*o ser convertido*/
                    if(probabilidades.calculoAleatorio(100, 0) >= probabilidades.getProb_conv_vamp())
                       nuevos.add(new Vampiro(DIA));
                }
                /*Si no puede comer muere por inanición*/
               if(haComido == false) 
                  vamp.Morir();
              // if(Vampiros.isEmpty())
              //     System.out.print("Los vampiros se han extinguido.");
            }           
        }
        for(Vampiro vamp : aux) 
            if(!vamp.isVivo())
                Vampiros.remove(vamp);
        for (Vampiro vamp : nuevos) 
            Vampiros = nuevos;
        
    }

    private void ZombiesActuan() 
    {
        ArrayList <Zombie> aux= (ArrayList <Zombie> )Zombies.clone();
        ArrayList <Zombie> nuevos = new ArrayList();
        for(Zombie zomb: aux)
        {
            if(DIA - zomb.getdiaNacimiento() >= probabilidades.getDias_vida_zomb())
                zomb.Morir();
            else
            {
                if(probabilidades.calculoAleatorio(probabilidades.getProb_conv_zomb(), 1) == 1)
                {
                   if( ZombieMata()){//Mata al ultimo del array, el cual es el mas lento
                    zomb.convierte();                   
                    nuevos.add(new Zombie(DIA));
                   }
                }
            }
        }
        for(Zombie zomb: aux) if(!zomb.isVivo())Zombies.remove(zomb);
        for(Zombie zomb: nuevos) Zombies.add(zomb);
    }
    
    private boolean ZombieMata ()
    {
        if(!Humanos.isEmpty() && !Cazavampiros.isEmpty()) //Si no hay ningun grupo extinguido
        {
            if( Humanos.get(Humanos.size()-1).getVelocidad() <= Cazavampiros.get(Cazavampiros.size()-1).getVelocidad() )
            {
                Humanos.remove(Humanos.size()-1);
            }
            else if ( Humanos.get(Humanos.size()-1).getVelocidad() > Cazavampiros.get(Cazavampiros.size()-1).getVelocidad() )
            {
                Cazavampiros.remove(Cazavampiros.size()-1);
            }
        }
        else if (Humanos.isEmpty())
            Cazavampiros.remove(Cazavampiros.size()-1);
        else if (Cazavampiros.isEmpty())
            Humanos.remove(Humanos.size()-1);
        else return false;
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
    public void InvasionZombie(boolean activado)
    {
        if(activado)
            probabilidades.setProb_conv_zomb(3);
        else 
            probabilidades.setProb_conv_zomb(10);
    }
    public String DetallesDiaActual()
    {
        return this.toString();
    }
    public String Resumen()
    {
        return Humanos.toString() + Vampiros.toString() + Cazavampiros.toString() + Zombies.toString();
    }
    
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
