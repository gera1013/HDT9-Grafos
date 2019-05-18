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
  
        /* Initialize the solution matrix same as input graph matrix. 
           Or we can say the initial values of shortest distances 
           are based on shortest paths considering no intermediate 
           vertex. */
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j];
  
        /* Add all vertices one by one to the set of intermediate 
           vertices. 
          ---> Before start of an iteration, we have shortest 
               distances between all pairs of vertices such that 
               the shortest distances consider only the vertices in 
               set {0, 1, 2, .. k-1} as intermediate vertices. 
          ----> After the end of an iteration, vertex no. k is added 
                to the set of intermediate vertices and the set 
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        //printSolution(dist); 
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
