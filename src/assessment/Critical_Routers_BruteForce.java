package assessment;

import java.util.*;

/**
 * problem statement : https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class Critical_Routers_BruteForce {

    List<HashSet<Integer>> graph;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        for(int i=0;i<n;i++) {
            graph.add(new HashSet<>());
        }
        for(List<Integer> list : connections) {
            addConnection(list.get(0), list.get(1));
        }
        List<List<Integer>> response = new ArrayList<>();
        for(List<Integer> list : connections) {
            deleteConnection(list.get(0), list.get(1));
            if(areMultipleConnectedComponents()) {
                response.add(Arrays.asList(list.get(0), list.get(1)));
            }
            addConnection(list.get(0), list.get(1));
        }
        return response;
    }

    void addConnection(int u, int v) {
        HashSet<Integer> set = graph.get(u);
        set.add(v);
        graph.set(u, set);
        set = graph.get(v);
        set.add(u);
        graph.set(v, set);
    }

    void deleteConnection(int u, int v) {
        HashSet<Integer> set = graph.get(u);
        set.remove(v);
        graph.set(u, set);
        set = graph.get(v);
        set.remove(u);
        graph.set(v, set);
    }

    private boolean areMultipleConnectedComponents() {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited.add(0);
        while(!queue.isEmpty()) {
            int source = queue.poll();
            Iterator<Integer> itr = graph.get(source).iterator();
            while(itr.hasNext()) {
                int vertex = itr.next();
                if(visited.add(vertex)) {
                    queue.add(vertex);
                }
            }
        }
        return (visited.size() != graph.size());
    }
}
