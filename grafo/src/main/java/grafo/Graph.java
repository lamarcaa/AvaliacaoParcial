
package grafo;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Set;

/* codigo gerado pelo professor */

class Edge {
  private float weight = 1;
  private Vertex from;
  private Vertex to;

  public Edge(Vertex from, Vertex to) {
    setFrom(from);
    setTo(to);
  }

  public Edge(Vertex from, Vertex to, float weight) {
    setFrom(from);
    setTo(to);
    setWeight(weight);
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getWeight() {
    return weight;
  }

  private void setFrom(Vertex from) {
    this.from = from;
  }

  public Vertex getFrom() {
    return from;
  }

  private void setTo(Vertex to) {
    this.to = to;
  }

  public Vertex getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "[" + 
        this.weight + ", " + 
        this.from.getLabel() + ", " + 
        this.to.getLabel() + "]";
  }
}

class Vertex {
  private String label;
  private List<Edge> edges = new LinkedList<>();

  public Vertex(String label) {
    setLabel(label);
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void addEdge(Edge edge) {
    this.edges.add(edge);
  }

  public void removeEdge(Edge edge) {
    edges.remove(edge);
  }

  public List<Edge> getEdges() {
      return edges;
  } 

  @Override
  public String toString() {
    String ts = "[Label: " + label;
    for (Edge edge : this.edges) 
      ts += edge;
    return ts + "]";
  }
}

public class Graph {
  
  private List<Vertex> vertexes = new LinkedList<>();

  public Graph() {  }

  protected Vertex findVertex(String value) {
    try {
      return vertexes
          .stream()
          .filter(v -> v.getLabel().equals(value))
          .collect(Collectors.toList())
          .get(0);
    } catch(Exception e){
      return null;
    }
  }

  public void addVertex(String value) {
    var vertex = findVertex(value);
    if (vertex == null)
      vertexes.add(new Vertex(value));
  }

  public void removeVertex(String value) {
    var vertex = findVertex(value);
    if (vertex != null)
      vertexes.remove(vertex);
  }
  

  public void addEdge(String valA, String valB) {
    var va = findVertex(valA);
    var vb = findVertex(valB);
    va.addEdge(new Edge(va, vb));
    vb.addEdge(new Edge(vb, va));
  }

  public List<Vertex> neighbours(String value) {
      var v = findVertex(value);
      if (v == null)
          return null;
      List<Vertex> vs = new LinkedList<>();
      for(Edge e : v.getEdges())
          vs.add(e.getTo());
      
      return vs;
  }

  public boolean direct(String vA, String vB) {
      var v = findVertex(vA);
      if (v != null) {
        return v.getEdges()
            .stream()
            .filter(e -> e.getTo().getLabel().equals(vB))
            .count() == 1;
      }
      return false;
  }

  @Override
  public String toString() {
    var s = "";
    for(Vertex v : vertexes)
      s += v;
    return s;
  }
  
  /* codigo gerado pelo professor */
  
  /* codigo da prova parcial */
  
  public List<Vertex> caminho(String a, String b) {
    Vertex start = findVertex(a);
    Vertex end = findVertex(b);

    if (start == null || end == null)
        return null;

    Set<Vertex> visited = new HashSet<>();
    Queue<List<Vertex>> queue = new LinkedList<>();

    visited.add(start);
    queue.add(Arrays.asList(start));

    while (!queue.isEmpty()) {
        List<Vertex> path = queue.remove();
        Vertex last = path.get(path.size() - 1);

        if (last == end)
            return path;

        for (Edge edge : last.getEdges()) {
            Vertex neighbor = edge.getTo();
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                List<Vertex> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.add(newPath);
            }
        }
    }
    return null;
}


  
  public Graph uniao(Graph graph2) {
    Graph union = new Graph();
    union.vertexes.addAll(this.vertexes);
    union.vertexes.addAll(graph2.vertexes);
    for (Vertex v : graph2.vertexes) {
        for (Edge e : v.getEdges()) {
            Vertex to = e.getTo();
            if (union.findVertex(to.getLabel()) == null) {
                union.addVertex(to.getLabel());
            }
            union.addEdge(v.getLabel(), to.getLabel());
        }
    }
    return union;
}

  
  public Graph interseccao(Graph graph2) {
    Graph intersection = new Graph();

    for (Vertex v : vertexes) {
        if (graph2.findVertex(v.getLabel()) != null) {
            intersection.addVertex(v.getLabel());
            for (Edge e : v.getEdges()) {
                if (graph2.findVertex(e.getTo().getLabel()) != null) {
                    intersection.addEdge(v.getLabel(), e.getTo().getLabel());
                }
            }
        }
    }

    return intersection;
    }

/* codigo da avaliacao final */


  public List<Vertex> bfs(String startLabel, String endLabel) {
    // Encontra os vértices de início e fim com base em seus rótulos
    Vertex start = findVertex(startLabel);
    Vertex end = findVertex(endLabel);

    // Verifica se os vértices de início e fim existem no grafo
    if (start == null || end == null)
      return null;

    // Conjunto de vértices visitados durante a busca
    Set<Vertex> visited = new HashSet<>();
    
    // Fila para armazenar os caminhos a serem explorados
    Queue<List<Vertex>> queue = new LinkedList<>();

    // Marca o vértice de início como visitado e adiciona o caminho inicial à fila
    visited.add(start);
    queue.add(Arrays.asList(start));

    // Algoritmo BFS
    while (!queue.isEmpty()) {
      // Remove o próximo caminho da fila para explorá-lo
      List<Vertex> path = queue.remove();
      Vertex last = path.get(path.size() - 1);

      // Verifica se o último vértice no caminho é o vértice de destino
      if (last == end)
        return path;

      // Explora todos os vizinhos do último vértice no caminho
      for (Edge edge : last.getEdges()) {
        Vertex neighbor = edge.getTo();
        if (!visited.contains(neighbor)) {
          // Se o vizinho não foi visitado, marca-o como visitado
          visited.add(neighbor);
          // Cria um novo caminho adicionando o vizinho ao final do caminho atual
          List<Vertex> newPath = new ArrayList<>(path);
          newPath.add(neighbor);
          // Adiciona o novo caminho à fila para ser explorado posteriormente
          queue.add(newPath);
        }
      }
    }

    // Se não foi encontrado um caminho entre os vértices de início e fim
    return null;
}
}