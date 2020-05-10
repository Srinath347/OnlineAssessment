package assessment;

import java.util.LinkedList;
import java.util.Queue;

/**
 * problem statement : https://leetcode.com/discuss/interview-question/411357/
 * similar to : https://leetcode.com/problems/rotting-oranges/
 * TC : O(rows*columns)
 * SC : O(rows*columns)
 */
public class Zombie_In_A_Matrix {

    class Pair  {
        int i;
        int j;
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        if(rows == 0) {
            return 0;
        }
        int columns = grid[0].length;
        if(columns == 0) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<>();
        int fresh = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new Pair(i, j));
                } else if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int time = 0;
        while(!queue.isEmpty() && fresh > 0) {
            time++;
            int queueSize = queue.size();
            while(queueSize > 0) {
                Pair p = queue.poll();
                int i = p.i;
                int j = p.j;
                if(i-1 >= 0 && grid[i-1][j] == 1) {
                    grid[i-1][j] = 2;
                    queue.add(new Pair(i-1, j));
                    fresh--;
                }
                if(j-1 >= 0 && grid[i][j-1] == 1) {
                    grid[i][j-1] = 2;
                    queue.add(new Pair(i, j-1));
                    fresh--;
                }
                if(i+1 < rows && grid[i+1][j] == 1) {
                    grid[i+1][j] = 2;
                    queue.add(new Pair(i+1, j));
                    fresh--;
                }
                if(j+1 < columns && grid[i][j+1] == 1) {
                    grid[i][j+1] = 2;
                    queue.add(new Pair(i, j+1));
                    fresh--;
                }
                queueSize--;
            }
        }
        return (fresh == 0 ? time : -1);
    }
}
