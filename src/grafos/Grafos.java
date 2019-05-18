package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Grafos {
    public static void main(String[] args) throws FileNotFoundException 
    {
        ArrayList<String> cities = new ArrayList();
        ArrayList<String> array = new ArrayList(); 
        Scanner read = new Scanner(new File("guategrafo.txt"));
        Scanner scan = new Scanner(System.in);
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
        
        int[][] grafo = new int[cities.size()][cities.size()];
        
        for(String x : array){
            String[] b = x.split(",");
            String origen = b[0];
            String destino = b[1];
            String distancia = b[2];
            grafo[cities.indexOf(origen)][cities.indexOf(destino)] = Integer.parseInt(distancia);
        }
                
        int x = 0;
        
        while(x != 6)
        {
            try{
                System.out.println("\nIngrese una opción del menu\n1. Mostrar valor de ruta más corta\n2. Mostrar centro del grafo\n3. Interrupcion de trafico entre ciudades\n"
                + "4. Nueva conexion entre ciudades\n5. Imprimir matriz de grafo\n6. Salir");
                x = scan.nextInt();
                String jump = scan.nextLine();
                
                while(x >= 7 || x <= 0)
                {
                    System.out.println("\nIngrese una opción del menu\n1. Mostrar valor de ruta más corta\n2. Mostrar centro del grafo\n3. Interrupcion de trafico entre ciudades\n"
                + "4. Nueva conexion entre ciudades\n5. Imprimir matriz de grafo\n6. Salir");
                    x = scan.nextInt();
                }
            } catch(InputMismatchException e)
            {
                System.out.println("Ingrese solamente numeros por favor");
                System.exit(0);
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

            switch(x){
                case 1:
                    System.out.println("Ingrese la ciudad de origen de la ruta deseada");
                    String origen = scan.nextLine();
                    System.out.println("Ingrese la ciudad de destino de la ruta deseada");
                    String destino = scan.nextLine();
                    if(cities.lastIndexOf(origen) < 0 || cities.lastIndexOf(destino) < 0) System.out.println("\nAlguna de las ciudades ingresadas"
                            + " es incorrecta\n");
                    else{
                        int distanciaCorta = caminoCorto[cities.indexOf(origen)][cities.indexOf(destino)];
                        System.out.println("\nLa distancia mas corta entre " + origen + " y " + destino + " es de " + distanciaCorta + " kilometros");
                    }
                    break;
                case 2:
                    a.centro(caminoCorto, cities);
                    break;
                case 3:
                    System.out.println("Ingrese la ciudad de origen de la ruta bloqueada");
                    origen = scan.nextLine();
                    System.out.println("Ingrese la ciudad de destino de la ruta bloqueada");
                    destino = scan.nextLine();
                    if(cities.lastIndexOf(origen) < 0 || cities.lastIndexOf(destino) < 0) System.out.println("\nAlguna de las ciudades ingresadas"
                            + " es incorrecta\n");
                    else{
                        grafo[cities.indexOf(origen)][cities.indexOf(destino)] = 0;
                        System.out.println("\nRuta entre " + origen + " y " + destino + " actualizada con exito");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese la ciudad de origen de la nueva ruta");
                    origen = scan.nextLine();
                    System.out.println("Ingrese la ciudad de destino de la nueva ruta");
                    destino = scan.nextLine();
                    if(cities.lastIndexOf(origen) < 0 || cities.lastIndexOf(destino) < 0) System.out.println("\nAlguna de las ciudades ingresadas"
                            + " es incorrecta\n");
                    else{
                        System.out.println("Ingrese la distancia de la nueva ruta");
                        if(scan.hasNextInt()) {
                            int distancia = scan.nextInt();
                            grafo[cities.indexOf(origen)][cities.indexOf(destino)] = distancia;
                            System.out.println("\nNueva ruta entre " + origen + " y " + destino + " creada con exito");
                        }
                        else{
                            System.out.println("Ingrese un valor numerico para la ruta");
                        }
                    }
                    break;
                case 5:
                    a.printSolution(caminoCorto);
                    break;
                case 6:
                    System.out.println("\nFinalizando programa...");
                    System.exit(0);
                    break;
            }
        }
    }
}
