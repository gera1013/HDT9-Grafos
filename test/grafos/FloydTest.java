/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gerardo
 */
public class FloydTest {
    
    public FloydTest() {
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
     * Test of centro method, of class Floyd.
     */
    @Test
    public void testCentro() {
        System.out.println("centro");
        int INF = 99999;
        int[][] grafo = { {0,   5,  INF, 10}, 
                          {INF, 0,   3, INF}, 
                          {INF, INF, 0,   1}, 
                          {INF, INF, INF, 0} 
                        };
        ArrayList<String> cities = null;
        cities.add("Nueva York");
        cities.add("Chicago");
        cities.add("Boston");
        cities.add("Portland");
        Floyd instance = new Floyd(4);
        assertEquals("Nueva York", instance.centro(grafo, cities));
    }
    
}
