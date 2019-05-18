package grafos;

import java.util.ArrayList;

/**
 *
 * @author Gerardo
 */

  
public class Floyd 
{ 
    final static int INF = 99999;
    int V;
    public Floyd(int vertex){
        V = vertex;
    }
  
    int[][] floydWarshall(int graph[][]) 
    { 
        int dist[][] = new int[V][V]; 
        int i, j, k; 
  
        /* 
         *  Se inicializa la matriz nueva con los mismos valores que 
         *  están en la matriz que se toma como parametro
         */
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j];
  
        /* 
         * Se suman los vertices uno por uno al set de vertices intermedios 
         */
        for (k = 0; k < V; k++) 
        { 
            // Se toman todos los vertices como origen uno por uno
            for (i = 0; i < V; i++) 
            { 
                // Se toman todos los vertices como destino del vertice
                // elegido anteriormente
                for (j = 0; j < V; j++) 
                { 
                    // Si el vertice k tiene una ruta mas corta de i a j 
                    // se actualiza el valor de dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        //Devuelve la matriz con las rutas más cortas entre ciudades 
        return dist;
    } 
  
    public void printSolution(int dist[][]) 
    { 
        System.out.println("\nSe muestran las rutas mas cortas entre las ciudades"); 
        for (int i=0; i<V; ++i) 
        {
            if(i < 10)System.out.print(i + "  =>  ");
            if(i >= 10)System.out.print(i + " =>  ");
            for (int j=0; j<V; ++j) 
            { 
                if (dist[i][j] == INF) 
                    System.out.print("INF "); 
                else{
                    if (dist[i][j] < 10) System.out.print(dist[i][j] + "   ");
                    if (dist[i][j] >= 10) System.out.print(dist[i][j] + "  ");
                }
                    
            } 
            System.out.println(); 
        } 
    }
    
    void centro(int[][] grafo, ArrayList<String> cities){
        int[] maximos = new int[cities.size()];
        for(int i = 0; i < cities.size(); i++)
        {
            int max = 0;
            for(int j = 0; j < cities.size(); j++)
            {
                if(i != j && grafo[j][i] > max)
                {
                    max = grafo[i][j];
                    maximos[i] = max;
                }
            }
        }
        int min = 10000;
        
        for(int i = 0; i < cities.size(); i++) if(maximos[i] < min) min = maximos[i];
        System.out.println("\nEl centro del grafo es " + cities.get(min));
    }
}
