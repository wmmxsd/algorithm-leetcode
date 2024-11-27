package algorithm.graph.bfs.topology;

import java.util.LinkedList;
import java.util.List;

/**
 * 网络节点类
 */
public class Node {
    private final String id;

    private final List<Node> neighbors;

    public Node(String id) {
        this.id = id;
        this.neighbors = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Node)) {
            return false;
        }

        Node node = (Node) o;

        return getId().equals(node.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
