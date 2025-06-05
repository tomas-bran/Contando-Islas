package grafo;

import java.util.*;

public class GrafoV2 {

	private Map<Integer, Map<Integer, Integer>> adyacencias; // clave: origen, valor: <destino, peso>
	private boolean esDirigido;

	public GrafoV2(boolean esDirigido) {
		adyacencias = new HashMap<>();
		this.esDirigido = esDirigido;
	}

	public void agregarVertice(int vertice) {
		adyacencias.putIfAbsent(vertice, new HashMap<>());
	}

	public void eliminarVertice(int vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vertice no se encuentra en el grafo");
		}

		adyacencias.remove(vertice);

		for (Map<Integer, Integer> vecinos : adyacencias.values()) {
			vecinos.remove(vertice);
		}
	}

	public void agregarArista(int origen, int destino, int peso) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vertice origen y/o destino no existen");
		}

		adyacencias.get(origen).put(destino, peso);
		if (!esDirigido) {
			adyacencias.get(destino).put(origen, peso);
		}
	}

	public void eliminarArista(int origen, int destino) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vertice origen y/o destino no existen");
		}

		adyacencias.get(origen).remove(destino);
		if (!esDirigido) {
			adyacencias.get(destino).remove(origen);
		}
	}

	public boolean contieneVertice(int vertice) {
		return adyacencias.containsKey(vertice);
	}

	public boolean contieneArista(int origen, int destino) {
		return adyacencias.containsKey(origen) && adyacencias.get(origen).containsKey(destino);
	}

	public Map<Integer, Integer> obtenerVecinos(int vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vertice no se encuentra en el grafo");
		}
		return adyacencias.get(vertice);
	}

	public void imprimirGrafo() {
		System.out.println("Representaci\u00f3n del grafo:");
		for (Map.Entry<Integer, Map<Integer, Integer>> entrada : adyacencias.entrySet()) {
			int vertice = entrada.getKey();
			Map<Integer, Integer> vecinos = entrada.getValue();
			System.out.println(vertice + " -> " + vecinos);
		}
	}

	public int obtenerCantidadVertices() {
		return adyacencias.size();
	}

	// Métodos como BFS o Dijkstra se pueden adaptar a esta nueva estructura
}
