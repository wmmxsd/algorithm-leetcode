package algorithm.graph.bfs.topology;

public class App {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 创建节点和网络拓扑
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        Node nodeJ = new Node("J");

        Topology network = new Topology();
        network.addNode(nodeA);
        network.addNode(nodeB);
        network.addNode(nodeC);
        network.addNode(nodeD);
        network.addNode(nodeE);
        network.addNode(nodeF);
        network.addNode(nodeG);
        network.addNode(nodeH);
        network.addNode(nodeI);
        network.addNode(nodeJ);

        network.addEdge(nodeA, nodeB);
        network.addEdge(nodeA, nodeC);
        network.addEdge(nodeA, nodeD);

        network.addEdge(nodeC, nodeE);

        network.addEdge(nodeE, nodeF);
        network.addEdge(nodeE, nodeG);
        network.addEdge(nodeE, nodeH);

        network.addEdge(nodeG, nodeI);
        network.addEdge(nodeG, nodeJ);
        network.addEdge(nodeI, nodeJ);

        System.out.println("深度优先搜索遍历：");
        network.dfsWithStack(nodeA);

        System.out.println("深度优先搜索遍历：");
        network.dfsWithRecursion(nodeA);

        System.out.println("\n广度优先搜索遍历：");
        network.bfsWithLevel(nodeA);

        System.out.println("\n广度优先搜索遍历：");
        network.bfsWithoutLevel(nodeA);
    }
}
