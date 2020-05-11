package assessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * problem statement : https://leetcode.com/problems/critical-connections-in-a-network/
 * reference : tarjan's algorithm
 */
public class Critical_Routers_Optimised {


    List<List<Integer>> graph;
    int depth;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        graph = new ArrayList<>();
        depth = 0;
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }
        for(List<Integer> list : connections) {
            addConnection(list.get(0), list.get(1));
        }
        int[] disc = new int[n];
        Arrays.fill(disc, -1);
        int[] low = new int[n];
        List<List<Integer>> response = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(disc[i] == -1) {
                dfs(i, disc, low, response, i);
            }
        }
        return response;
    }

    private void dfs(int u, int[] disc, int[] low,List<List<Integer>> result, int parent) {
        disc[u] = low[u] = ++depth;
        for (int v : graph.get(u)) {
            if (v != parent) {
                if (disc[v] == -1) {
                    dfs(v, disc, low, result, u);
                    if (low[v] > disc[u]) {
                        result.add(Arrays.asList(u, v));
                    }
                }
                low[u] = Math.min(low[u], low[v]);
            }
        }
    }

    void addConnection(int u, int v) {
        List<Integer> list = graph.get(u);
        list.add(v);
        graph.set(u, list);
        list = graph.get(v);
        list.add(u);
        graph.set(v, list);
    }
}
