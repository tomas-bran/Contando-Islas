package grafo;

import java.util.*;

public class Grafo {

	private Map<Integer, List<Integer>> adyacencias;
	private boolean esDirigido;

	public Grafo() {
		adyacencias = new HashMap<Integer, List<Integer>>();
	}

	// Agrega un nuevo vértice al grafo
	public void agregarVertice(int vertice) {
		adyacencias.putIfAbsent(vertice, new ArrayList<>());
	}

	// Elimina un vértice y todas sus aristas
	public void eliminarVertice(int vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vertice no se encuentra en el grafo");
		}

		adyacencias.remove(vertice);

		for (List<Integer> vertices : adyacencias.values()) {
			vertices.remove(vertice);
		}
	}
	
	public Set<Integer> obtenerTodosVertices() {
		return adyacencias.keySet();
	}

	// Agrega una arista entre dos vértices (no dirigida)
	public void agregarArista(int origen, int destino) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vertice origen y/o destino no existen");
		}

		adyacencias.get(origen).add(destino);
		adyacencias.get(destino).add(origen);
	}

	// Elimina la arista entre dos vértices
	public void eliminarArista(int origen, int destino) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vertice origen y/o destino no existen");
		}

		/*
		 * for(int vertices : adyacencias.get(origen)) { if(vertices == destino)
		 * adyacencias.get(origen).remove(vertices); }
		 * 
		 * for(int vertices : adyacencias.get(destino)) { if(vertices == origen)
		 * adyacencias.get(destino).remove(vertices); }
		 */

		adyacencias.get(origen).remove(Integer.valueOf(destino));
		adyacencias.get(destino).remove(Integer.valueOf(origen));

	}

	// Verifica si el vértice existe
	public boolean contieneVertice(int vertice) {
		return adyacencias.containsKey(vertice);
	}

	// Verifica si existe una arista entre dos vértices
	public boolean contieneArista(int origen, int destino) {
		return adyacencias.get(origen).contains(destino);
	}

	// Devuelve la lista de vecinos (adyacentes) de un vértice
	public List<Integer> obtenerVecinos(int vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vertice no se encuentra en el grafo");
		}

		return adyacencias.get(vertice);
	}

	// Imprime el grafo (útil para debug)
	public void imprimirGrafo() {

		System.out.println("Representación del grafo:");
		for (Map.Entry<Integer, List<Integer>> entrada : adyacencias.entrySet()) {
			int vertice = entrada.getKey();
			List<Integer> vecinos = entrada.getValue();
			System.out.println(vertice + " -> " + vecinos);
		}
	}

	public int[] obtenerGradoDirigido(int vertice) {

		int contSalidas = 0;
		int contEntradas = 0;

		for (int vertices : adyacencias.get(vertice)) {
			contSalidas++; // si fuese dirigido, seria el cont de vertice -> x (Grado Salida)
		}

		for (List<Integer> vecinos : adyacencias.values()) {
			if (vecinos.contains(vertice)) {
				contEntradas++;
			}
		}

		int retorno[] = { contSalidas, contEntradas };

		return retorno;
	}

	public int obtenerGradoNoDirigido(int vertice) {
		int cont = 0;

		for (int vertices : adyacencias.get(vertice)) {
			cont++;
		}
		return cont;
	}

	public int obtenerCantidadVertices() {

		int retorno = 0;

		for (int vertices : adyacencias.keySet()) {
			retorno++;
		}

		return retorno;
	}

	// Recorrido en anchura (BFS)
	public void bfs(int inicio) throws Exception {
		Queue<Integer> colaPorVisitar = new LinkedList<>();
		int cantVertices = obtenerCantidadVertices();
		Integer[] distancia = new Integer[cantVertices];
		Arrays.fill(distancia, Integer.MAX_VALUE);

		distancia[inicio] = 0;

		colaPorVisitar.offer(inicio); 

		while (!colaPorVisitar.isEmpty()) {

			int v = colaPorVisitar.poll();

			List<Integer> adyacentes = obtenerVecinos(v);

			for (int vertice : adyacentes) {
				if (distancia[vertice] == Integer.MAX_VALUE) {
					colaPorVisitar.offer(vertice);
					distancia[vertice] = distancia[v] + 1;
				}
			}
		}
		System.out.println("Distancias desde el nodo " + inicio + ": " + Arrays.toString(distancia));
	}
	
	public void bfsIslas(int origen, Set<Integer> visitados) throws Exception {
		
		Queue<Integer> colaPorVisitar = new LinkedList<>();
		int cantVertices = obtenerCantidadVertices();

		colaPorVisitar.offer(origen);
		
		while(!colaPorVisitar.isEmpty()) {
			
			int v = colaPorVisitar.poll();
			List<Integer> u = obtenerVecinos(v);
			
			for(int vertice : u)
				if(!visitados.contains(vertice)) {
					colaPorVisitar.offer(vertice);
					visitados.add(vertice);
				}
			}
		}
	
	// Recorrido en profundidad (DFS)
	public void dfs(int inicio) {
	}

}
