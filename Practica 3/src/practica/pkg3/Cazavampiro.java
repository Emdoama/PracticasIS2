package practica.pkg3;

import java.io.Serializable;

/**
 *
 * @author Alber
 */
public class Cazavampiro extends Humano implements Serializable {
    int cazados;
   
    public Cazavampiro(int DIA, int velocidad) {
        super(DIA, velocidad);
        cazados=0;
        
    }

    void caza() {
       cazados++; 
    }
  
    
}
