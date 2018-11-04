package practica.pkg3;

import java.io.Serializable;

/**
 *
 * @author Alber
 */
public class Cazavampiro extends Humano implements Serializable {
    int cazados;
    public Cazavampiro(int DIA) {
        super(DIA);
    }

    void caza() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    
}
