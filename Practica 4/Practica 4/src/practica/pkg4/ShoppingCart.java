package practica.pkg4;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * An example shopping cart. 
 * This class should not be mistaken for a production-quality shopping cart. 
 * It's merely provided as an example class under test as described in the
 * JUnitPrimer.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting, Inc.</a> 
 */
 
public class ShoppingCart {

    private ArrayList<Product> _items;

    /**
     * Constructs a ShoppingCart instance.
     */
    public ShoppingCart() {
        _items = new ArrayList<Product>();
    }

    /**
     * Returns the balance.
     * @return Balance.
     */
    public double getBalance() {
        Iterator<Product> i = _items.iterator();
        double balance = 0.00;
        while (i.hasNext()) {
            Product p = (Product)i.next();
            balance = balance + p.getPrice();
        }

        return balance;
    }
    
    /**
     * Adds the specified product.
     * @param p Product.
     */
    public void addItem(Product p) {
        _items.add(p);
    }

    /**
     * Removes the specified product.
     * @param p Product.
     * @throws ProductNotFoundException If the product does not exist.
     */
    public void removeItem(Product p) throws ProductNotFoundException {
        if (!_items.remove(p)) {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Returns the number of items in the cart.
     * @return Item count.
     */
    public int getItemCount() {
        return _items.size();
    }
    
    /**
     * Empties the cart.
     */
    public void empty() {
        _items = new ArrayList<Product>();
    }

    /**
     * Indicates whether the cart is empty.
     * @return true if the cart is empty;
     *     false otherwise.
     */
    public boolean isEmpty() {
        return (_items.size() == 0);
    }
    
    public void imprimeFactura(Writer writer) throws IOException
    {
    	writer.append(_items.toString());
    	writer.flush();
    	writer.close();
    }
    
    // Ejercicio 1: Añadir metodo findProduct(String titulo)  // Devuelve true o false si el titulo esta
    
    public boolean findProduct(String titulo)
    {
        boolean existe = false;
        
        for (int i = 0; i < getItemCount(); i++)
        {
          
            
            if(titulo.equals(_items.get(i).getTitle()))
            {
              
                existe = true;
            }
        }
        
        return existe;
    }
}
