package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafos {
    public static void main(String[] args) throws FileNotFoundException 
    {
        int INF = 999999;
        ArrayList<String> cities = new ArrayList();
        ArrayList<String> array = new ArrayList(); 
        Scanner read = new Scanner(new File("guategrafo.txt"));
        //Scanner scan = new Scanner(System.in);
        read.useDelimiter("\\r\\n");
        String data;
        
        while(read.hasNext())
        {
            if(read.hasNextLine()){
                data = read.next();
                array.add(data);
            }
        }
        
        for(String x : array){
            String[] b = x.split(",");
            String city1 = b[0];
            String city2 = b[1];
            if(!cities.contains(city1)) cities.add(city1);
            if(!cities.contains(city2)) cities.add(city2);
        }
        
        System.out.println(cities.toString());
        Grafo g = new Grafo(cities.size());
        
        int[][] grafo = new int[cities.size()][cities.size()];
        
        for(String x : array){
            String[] b = x.split(",");
            String origen = b[0];
            String destino = b[1];
            String distancia = b[2];
            g.addEdge(cities.indexOf(origen), Integer.parseInt(distancia), cities.indexOf(destino));
            grafo[cities.indexOf(origen)][cities.indexOf(destino)] = Integer.parseInt(distancia);
        }
        
        for(int i = 0; i < cities.size(); i++){
            for(int j = 0; j < cities.size(); j++){
                if(i != j && grafo[i][j] == 0){
                    grafo[i][j] = 99999;
                }
            }
        }
                
        
        Floyd a = new Floyd(cities.size());
        
        int[][] caminoCorto = a.floydWarshall(grafo);
        int[] maximos = new int[cities.size()];
        
        for(int i = 0; i < cities.size(); i++){
            int max = 0;
            for(int j = 0; j < cities.size(); j++){
                if(i != j && caminoCorto[j][i] > max){
                    max = caminoCorto[i][j];
                    maximos[i] = max;
                }
            }
        }
        
        int max = 10000;
        for(int i = 0; i < cities.size(); i++) if(maximos[i] < max) max = maximos[i];
        
        System.out.println("El centro del grafo es: " + cities.get(max));
        System.out.println(g);
    } 
        
        
	/*g.addEdge(0, 2, 3);
	g.addEdge(0, 5, 5);
	g.addEdge(2, 5, 9);
	g.addEdge(9, 3, 6);
        g.addCiudad("Guatemala");
        g.addCiudad("Chiquimula");
        g.addCiudad("Zacapa");
        g.addCiudad("Jalapa");
        g.addCiudad("Jutiapa");
        g.addCiudad("El Progreso");
        g.addCiudad("Peten");
        g.addCiudad("Huehuetenango");
        g.addCiudad("Amatitlan");
        g.addCiudad("Izabal");*/
}
