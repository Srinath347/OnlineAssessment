package assessment;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  problem statement : https://leetcode.com/problems/number-of-islands/
 */
public class Number_of_Clusters {

    class Pair {
        int i;
        int j;
        Pair(int i,int j) {
            this.i = i;
            this.j = j;
        }
    }

    public int numIslands(char[][] grid) {

        int rows = grid.length;
        if(rows == 0) {
            return rows;
        }
        int columns = grid[0].length;
        if(columns == 0) {
            return 0;
        }
        int islands = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(grid[i][j] == '1') {
                    islands++;
                    changeAllConnectedLand(grid , i, j, rows, columns);
                }
            }
        }
        return islands;
    }

    private void changeAllConnectedLand(char[][] grid, int row, int col, int rows, int columns) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        grid[row][col] = '0';
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            int i = p.i;
            int j = p.j;
            if(i-1 >= 0 && grid[i-1][j] == '1') {
                grid[i-1][j] = '0';
                queue.add(new Pair(i-1,j));
            }
            if(j-1 >= 0 && grid[i][j-1] == '1') {
                grid[i][j-1] = '0';
                queue.add(new Pair(i,j-1));
            }
            if(i+1 < rows && grid[i+1][j] == '1') {
                grid[i+1][j] = '0';
                queue.add(new Pair(i+1,j));
            }
            if(j+1 < columns && grid[i][j+1] == '1') {
                grid[i][j+1] = '0';
                queue.add(new Pair(i,j+1));
            }
        }
    }
}
