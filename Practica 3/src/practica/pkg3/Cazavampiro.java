package practica.pkg3;

import java.io.Serializable;

/**
 *
 * @author Alber
 */
public class Cazavampiro extends Humano implements Serializable {
    int cantidadVampirosCazados;
   
    public Cazavampiro(int DIA, int velocidad) {
        super(DIA, velocidad);
        cantidadVampirosCazados = 0;
    }
    
    public void Nacer(int dia, int velocidad) {
        
        Cazavampiro cazavampiros = new Cazavampiro(dia, velocidad);
        boolean insertado = false;
        int i = Entorno.Cazavampiros.size();
        try{
        do
        {
            if (velocidad < Entorno.Cazavampiros.get(i).getVelocidad())
            {
                Entorno.Cazavampiros.add(i+1, cazavampiros);
                insertado = true;
            }
            else
                i--;
        }while(insertado != true);
        }catch(IndexOutOfBoundsException e){Entorno.Cazavampiros.add(cazavampiros);}
    }

    void caza() {
       cantidadVampirosCazados++; 
    }
    @Override
    public String toString()
    {
        return "";
    }
}
