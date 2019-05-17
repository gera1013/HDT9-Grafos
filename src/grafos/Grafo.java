package grafos;

import java.util.*;

/**
 *
 * @author Gerardo
 */
public class Grafo{
    
    class Edge{
        int v;
        
	public Edge(int v)
        {
            this.v = v;
	}
        
	@Override
	public String toString()
        {
            return String.valueOf(v);
	}
    }
    
    List<Edge> G[];
    List<String> Ciudades = new LinkedList<>();
    
    public Grafo(int n)
    {
        G = new LinkedList[n];
	for(int i = 0; i < G.length; i++){
            G[i] = new LinkedList();
            int x = 0;
            while(x < n){
                G[i].add(new Edge(0));
                x++;
            }
        }
    }
   	
    public void addEdge(int u, int v, int w)
    {
        G[u].set(w, new Edge(v));
    }
    
    public void addCiudad(String city)
    {
        Ciudades.add(city);
    }

    @Override
    public String toString()
    {
	String result = "";
	for(int i = 0; i < G.length ;i++) result += i + "  =>  " + G[i] + "\n";
	return result;
    }
    
    public void remove(int u)
    {
        G[u].remove(u);
    }
    
    /*boolean isConnected(int u,int v)
    {
        for(Edge i: G[u]) if(i.v==v) return true;
        return false;
    }*/
}