package grafo;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("b", "c");
        graph.addEdge("a", "d");

        System.out.println(graph);
        System.out.println("Vizinhos: " + graph.neighbours("a"));

        if (graph.direct("a", "c"))
            System.out.println("A e C estão ligados!");

        if (!graph.direct("b", "d"))
            System.out.println("B e D NÃO estão ligados!");

        Graph graph2 = new Graph();
        graph2.addVertex("a");
        graph2.addVertex("e");
        graph2.addVertex("f");
        graph2.addVertex("g");
        graph2.addEdge("a", "e");
        graph2.addEdge("a", "f");
        graph2.addEdge("g", "e");
        graph2.addEdge("e", "f");

        System.out.println("Caminho: " + graph.caminho("a", "b"));
        System.out.println("União: " + graph.uniao(graph2));
        System.out.println("Interseção: " + graph.interseccao(graph2));
    }
}
