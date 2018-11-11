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
        
        do
        {
            if (velocidad < Entorno.Cazavampiros.get(i).getVelocidad())
            {
                Entorno.Humanos.add(i+1, cazavampiros);
                insertado = true;
            }
            else
                i--;
        }while(insertado != true);
    }

    void caza() {
       cantidadVampirosCazados++; 
    }
  
    
}
