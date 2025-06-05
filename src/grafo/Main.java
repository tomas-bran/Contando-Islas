package grafo;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        /*try {
            Grafo grafo = new Grafo();

           
            for (int i = 0; i <= 12; i++) {
                grafo.agregarVertice(i);
            
         
            grafo.agregarArista(0, 1);
            grafo.agregarArista(0, 8);
            grafo.agregarArista(1, 2);
            grafo.agregarArista(1, 5);
            grafo.agregarArista(2, 3);
            grafo.agregarArista(4, 8);
            grafo.agregarArista(4, 5);
            grafo.agregarArista(5, 9);
            grafo.agregarArista(5, 6);
            grafo.agregarArista(6, 7);
            //grafo.agregarArista(8, 4);
            grafo.agregarArista(8, 9);
            grafo.agregarArista(9, 10);

            
            grafo.imprimirGrafo();

          
            grafo.bfs(0);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }*/
    	
    	
    	int isla[][] = new int[][] {
    			{1,1,0,0,0},
    			{1,1,0,0,0},
				{0,0,1,0,0},
				{0,0,0,1,1}
    			};
    			
    	int cant =	Main.cantIslas(isla);
    	
    	System.out.println("La cantidad de islas es de " + cant);
 }
    
    public static int cantIslas(int [][] isla) throws Exception {
    	
    	int filas = isla.length;
    	int columnas = isla[0].length;

    	Grafo grafo = new Grafo();

    	for (int i = 0; i < filas; i++) {
    	    for (int j = 0; j < columnas; j++) {
    	        if (isla[i][j] == 1) {
    	            int actual = i * columnas + j;
    	            grafo.agregarVertice(actual);

    	            // Abajo
    	            if (i + 1 < filas && isla[i + 1][j] == 1) {
    	                int abajo = (i + 1) * columnas + j;
    	                grafo.agregarVertice(abajo);
    	                grafo.agregarArista(actual, abajo);
    	            }

    	            // Derecha
    	            if (j + 1 < columnas && isla[i][j + 1] == 1) {
    	                int derecha = i * columnas + (j + 1);
    	                grafo.agregarVertice(derecha);
    	                grafo.agregarArista(actual, derecha);
    	            }

    	            // (arriba e izq no es necesario por como me estoy moviendo en la matriz)
    	        }
    	    }
    	}
    	
    	int cantIslas = 0;

    	Set<Integer> visitados = new HashSet<>();
    	
    	for(int vertice : grafo.obtenerTodosVertices()) {
    		if(!visitados.contains(vertice)) {
    			grafo.bfsIslas(vertice, visitados);
    			cantIslas++;
    		}
    	}
    	
    	return cantIslas;
    }
}