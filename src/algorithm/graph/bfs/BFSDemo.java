package algorithm.graph.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wangmingming160328
 * @date @2021/4/13 16:48
 */
public class BFSDemo {
    private static void bfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Integer> dist, char start) {
        Queue<Character> q = new LinkedList<>();
        //将s作为起始顶点加入队列
        q.add(start);
        dist.put(start, 0);
        int i = 0;
        while (!q.isEmpty()) {
            //取出队首元素
            char top = q.poll();
            i++;
            System.out.println("The " + i + "th element:" + top + " Distance from s is:" + dist.get(top));
            //得出其周边还未被访问的节点的距离
            int d = dist.get(top) + 1;
            for (Character c : graph.get(top)) {
                //如果dist中还没有该元素说明还没有被访问
                if (!dist.containsKey(c)) {
                    dist.put(c, d);
                    q.add(c);
                }
            }
        }
    }

    public static void main(String[] args) {
        HashMap<Character, LinkedList<Character>> graph = new HashMap<>();

    }
}
