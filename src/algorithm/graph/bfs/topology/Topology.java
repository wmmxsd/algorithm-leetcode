package algorithm.graph.bfs.topology;

import java.util.*;

/**
 * 网络拓扑类
 */
public class Topology {
    private final List<Node> nodes;
    private final List<Edge> edges;

    public Topology() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node source, Node destination) {
        if (source == null || destination == null) {
            throw new NullPointerException("source or destination can not be null");
        }

        edges.add(new Edge(source, destination));
        source.addNeighbor(destination);
        destination.addNeighbor(source);
    }

    public void bfsWithLevel(Node startNode) {
        List<List<Node>> result = new ArrayList<>();

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(startNode);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            List<Node> temp = new LinkedList<>();
            int size = queue.size();

            for (int index = 0; index < size; index++) {
                Node node = queue.poll();
                temp.add(node);

                for (Node neighbor : node.getNeighbors()) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }

            }

            result.add(temp);
        }

        result.forEach(System.out::println);
    }

    public void bfsWithoutLevel(Node startNode){
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        visited.add(startNode);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("Visiting Node: " + node.getId());

            for (Node neighbor : node.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public void dfsWithRecursion(Node startNode) {
        Set<Node> visited = new HashSet<>();
        dfs(startNode, visited);
    }

    private void dfs(Node node, Set<Node> visited) {
        if (!visited.contains(node)) {
            System.out.println("Visiting Node: " + node.getId());
            visited.add(node);
            for (Node neighbor : node.getNeighbors()) {
                dfs(neighbor, visited);
            }
        }
    }

    public void dfsWithStack(Node startNode) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.push(startNode);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (!visited.contains(node)) {
                System.out.println("Visiting Node: " + node.getId());
                visited.add(node);
                for (Node neighbor : node.getNeighbors()) {
                    stack.push(neighbor);
                }
            }
        }
    }
}
