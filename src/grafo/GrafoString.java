package grafo;

import java.util.*;

public class GrafoString {

	private Map<String, List<String>> adyacencias;

	public GrafoString() {
		adyacencias = new HashMap<>();
	}

	public void agregarVertice(String vertice) {
		adyacencias.putIfAbsent(vertice, new ArrayList<>());
	}

	public void eliminarVertice(String vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vértice no se encuentra en el grafo");
		}

		adyacencias.remove(vertice);

		for (List<String> vecinos : adyacencias.values()) {
			vecinos.remove(vertice);
		}
	}

	public void agregarArista(String origen, String destino) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vértice origen y/o destino no existen");
		}

		adyacencias.get(origen).add(destino);
		adyacencias.get(destino).add(origen);
	}

	public void eliminarArista(String origen, String destino) throws Exception {
		if (!adyacencias.containsKey(origen) || !adyacencias.containsKey(destino)) {
			throw new Exception("El vértice origen y/o destino no existen");
		}

		adyacencias.get(origen).remove(destino);
		adyacencias.get(destino).remove(origen);
	}

	public boolean contieneVertice(String vertice) {
		return adyacencias.containsKey(vertice);
	}

	public boolean contieneArista(String origen, String destino) {
		return adyacencias.get(origen).contains(destino);
	}

	public List<String> obtenerVecinos(String vertice) throws Exception {
		if (!adyacencias.containsKey(vertice)) {
			throw new Exception("Ese vértice no se encuentra en el grafo");
		}

		return adyacencias.get(vertice);
	}

	public void imprimirGrafo() {
		System.out.println("Representación del grafo:");
		for (Map.Entry<String, List<String>> entrada : adyacencias.entrySet()) {
			String vertice = entrada.getKey();
			List<String> vecinos = entrada.getValue();
			System.out.println(vertice + " -> " + vecinos);
		}
	}

	public int obtenerCantidadVertices() {
		return adyacencias.size();
	}
}

