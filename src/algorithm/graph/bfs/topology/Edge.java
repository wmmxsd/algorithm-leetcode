package algorithm.graph.bfs.topology;

/**
 * 网络连线类
 */
public class Edge {
    private final Node source;
    private final Node destination;

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getSource() {
        return source;
    }

    public Node getDestination() {
        return destination;
    }
}
