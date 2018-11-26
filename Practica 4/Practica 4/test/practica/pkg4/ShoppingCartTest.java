/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg4;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Alber
 */
public class ShoppingCartTest {
    
    public ShoppingCartTest() {
        ShoppingCart carrito = new ShoppingCart();
        
        assertTrue(carrito.isEmpty());
   
                
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBalance method, of class ShoppingCart.
     */
    @Test
    public void testGetBalance() {
        System.out.println("Test funcion obtener balance. GetBalance()\n");
        Product p, p1, p2, p3;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        p2 = new Product("Producto 2", 2);
        p3 = new Product("Producto 2", 3);
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(p);
        instance.addItem(p1);
        instance.addItem(p2);
        instance.addItem(p3);
        double expResult = 6.0;
        double result = instance.getBalance();
        
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addItem method, of class ShoppingCart.
     */
    @Test
    public void testAddItem() {
        System.out.println("Test funcion añadir objeto. Additem()\n");
        Product p, p1, p2, p3;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        p2 = new Product("Producto 2", 2);
        p3 = new Product("Producto 2", 3);
        
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(p);
        instance.addItem(p1);
        instance.addItem(p2);
        instance.addItem(p3);
        // TODO review the generated test code and remove the default call to fail.
        if(instance.isEmpty())
            fail("Ha fallado la funcion añadir productos. | isEmpty() | AddItem()\n");
        if(instance.getItemCount() != 4)
            fail("Ha fallado la funcion añadir productos. | getItemCount() != 4 | AddItem()\n");
    }

    /**
     * Test of removeItem method, of class ShoppingCart.
     */
    @Test
    public void testRemoveItem() throws Exception {
        System.out.println("Test funcion borrar objeto. Removeitem()\n");
        Product p = new Product("Producto", 1);
        
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(p);
        
        try{
            instance.removeItem(p); 
            
        }catch( Exception e){fail("No se ha podido remover el objeto p.");}

        
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getItemCount method, of class ShoppingCart.
     */
    @Test
    public void testGetItemCount() {
        System.out.println("Test obtener cuenta carrito | GetItemCount");
        ShoppingCart instance = new ShoppingCart();
        int expResult = 0;
        int result = instance.getItemCount();
        Product p, p1;

        assertEquals(expResult, result);
        
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        
        instance.addItem(p);
        instance.addItem(p1);
        result = instance.getItemCount();

        assertEquals(2, result);
    }

    /**
     * Test of empty method, of class ShoppingCart.
     */
    @Test
    public void testEmpty() {
        System.out.println("Test vaciar carrito. | testEmpty() | ");
        ShoppingCart instance = new ShoppingCart();
        int result = instance.getItemCount();
        Product p, p1;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        assertEquals(0, result);
        instance.addItem(p);
        instance.addItem(p1);
        result = instance.getItemCount();
        assertEquals(2, result);
        instance.empty();
        result = instance.getItemCount();
        assertEquals(0, result);
    }

    /**
     * Test of isEmpty method, of class ShoppingCart.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("Test comprovacion si Vacio | isEmpty()");
        ShoppingCart instance = new ShoppingCart();
        Product p, p1;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        instance.addItem(p);
        instance.addItem(p1);
        boolean result = instance.isEmpty();
        assertEquals(false, result);
    }

    /**
     * Test of imprimeFactura method, of class ShoppingCart.
     */
    @Test
    public void testImprimeFactura() throws Exception {
        /*System.out.println("imprimeFactura");
        Writer writer = null;
        ShoppingCart instance = new ShoppingCart();
        instance.imprimeFactura(writer);*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findProduct method, of class ShoppingCart.
     */
    @Test
    public void testFindProduct() {
        System.out.println("Test encontrar producto | FindProduct()");
        String titulo = "Producto";
        String titulo2 = "Producto 1";
        String titulo3 = "Esteproductonoesta";
        ShoppingCart instance = new ShoppingCart();
        Product p, p1;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        instance.addItem(p);
        instance.addItem(p1);
        boolean result = instance.findProduct(titulo);
        boolean result2 = instance.findProduct(titulo2);
        boolean result3 = instance.findProduct(titulo3);
        assertEquals(true, result);
        assertEquals(true, result2);
        assertEquals(false, result3);
    }
    
    
    @Test
    public void testAddItemFindProduct() {
        System.out.println("Test funcion añadir objeto. Additem()\n");
        Product p, p1, p2, p3;
        p = new Product("Producto", 0);
        p1 = new Product("Producto 1", 1);
        p2 = new Product("Producto 2", 2);
        p3 = new Product("Producto 2", 3);
        
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(p);
        instance.addItem(p1);
        instance.addItem(p2);
        instance.addItem(p3);

        if(! (instance.findProduct(p.getTitle())))
            fail("Ha fallado la funcion FindProduct. | p.getTitle() | testAddItemFindProduct()\n");
        if(!(instance.findProduct(p1.getTitle())))
            fail("Ha fallado la funcion FindProduct. | p1.getTitle() | testAddItemFindProduct()\n");
        if(!(instance.findProduct(p2.getTitle())))
            fail("Ha fallado la funcion FindProduct. | p2.getTitle() | testAddItemFindProduct()\n"); 
        if(!(instance.findProduct(p3.getTitle())))
            fail("Ha fallado la funcion FindProduct. | p3.getTitle() | testAddItemFindProduct()\n");
    }
    
    @Test
    public void testRemoveItem2() throws Exception {
        System.out.println("Test funcion borrar objeto. Removeitem()\n");
        Product p = new Product("Producto", 1);
        
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(p);
        instance.removeItem(p);
        
        assertTrue(instance.isEmpty()); 
    }
    
    @Test
    public void testRemoveItemCarroVacio() throws Exception {
        System.out.println("Test funcion borrar objeto. Removeitem()\n");
        Product p = new Product("Producto", 1);
        
        ShoppingCart instance = new ShoppingCart();

        try
        {
            instance.removeItem(p);
            fail("No ha saltado la excepcion de removeItem().");
        }catch( Exception e){}
    }
}