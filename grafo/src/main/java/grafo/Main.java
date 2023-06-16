import grafo.Graph;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Graph graph = new Graph();
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");
    graph.addVertex("F");
    graph.addVertex("G");

    graph.addEdge("A", "B");
    graph.addEdge("A", "C");
    graph.addEdge("B", "D");
    graph.addEdge("B", "E");
    graph.addEdge("C", "F");
    graph.addEdge("E", "G");

    System.out.println("Grafo:");
    System.out.println(graph);

    System.out.println("Vizinhos de B: " + graph.neighbours("B"));

    System.out.println("Existe uma aresta direta de C para F? " + graph.direct("C", "F"));
    System.out.println("Existe uma aresta direta de D para E? " + graph.direct("D", "E"));

    System.out.println("Caminho de A para G: " + graph.bfs("A", "G"));

    Graph graph2 = new Graph();
    graph2.addVertex("E");
    graph2.addVertex("F");
    graph2.addVertex("G");
    graph2.addEdge("E", "F");
    graph2.addEdge("F", "G");

    Graph union = graph.uniao(graph2);
    System.out.println("União dos grafos:");
    System.out.println(union);

    Graph intersection = graph.interseccao(graph2);
    System.out.println("Interseção dos grafos:");
    System.out.println(intersection);
  }
}
