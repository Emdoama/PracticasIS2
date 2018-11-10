package practica.pkg3;

import java.io.Serializable;
import java.util.Random;

public class Probabilidades implements Serializable{
    
    private int prob_conv_zomb = 10;
    private int prob_conv_vamp = 50;
    private int dias_vida_zomb = 8;
    private int prob_comer_vamp = 50;
    private int prob_muerte_nat = 500;
    private int prob_muerte_cat = 300;
    private int prob_cazar = 3;


    static Random ram = new Random(System.currentTimeMillis());
    
    public float modificarTemperatura(float temperatura)
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
        
        return temperatura;
    }
    
    public boolean reproduceHumano(float temperatura)
    {
        if(temperatura >= 22)
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
    
    public static int calculoAleatorio(int hasta, int desde)
    {
        return ram.nextInt(hasta-desde+1)+desde;
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
 
    public int getDias_vida_zomb() {
        return dias_vida_zomb;
    }
}
