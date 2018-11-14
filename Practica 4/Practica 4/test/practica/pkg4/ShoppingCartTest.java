/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg4;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
        System.out.println("removeItem");
        Product p = null;
        ShoppingCart instance = new ShoppingCart();
        instance.removeItem(p);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getItemCount method, of class ShoppingCart.
     */
    @Test
    public void testGetItemCount() {
        /*System.out.println("getItemCount");
        ShoppingCart instance = new ShoppingCart();
        int expResult = 0;
        int result = instance.getItemCount();
        assertEquals(expResult, result);*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of empty method, of class ShoppingCart.
     */
    @Test
    public void testEmpty() {
        /*System.out.println("empty");
        ShoppingCart instance = new ShoppingCart();
        instance.empty();*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class ShoppingCart.
     */
    @Test
    public void testIsEmpty() {
       /* System.out.println("isEmpty");
        ShoppingCart instance = new ShoppingCart();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        /*System.out.println("findProduct");
        String titulo = "";
        ShoppingCart instance = new ShoppingCart();
        boolean expResult = false;
        boolean result = instance.findProduct(titulo);
        assertEquals(expResult, result);*/
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
}
