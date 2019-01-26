/**
 * Class to Implement RatInMaze
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/24/19
 * Project : BackTracking
 **/

package BackTracking;

import java.util.Arrays;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e.,
 * maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * A rat starts from source and has to reach the destination. The rat can move only in two directions: forward and down.
 * In the maze matrix, 0 means the block is a dead end and 1 means the block can be used in the path from source
 * to destination. Note that this is a simple version of the typical Maze problem. For example, a more complex version
 * can be that the rat can move in 4 directions and a more complex version can be with a limited number of moves.
 */
public class RatInMaze {

    /**
     * Using Backtracking :
     * @param maze
     * @return
     */
    public int[][] solveMaze(int[][] maze) {
        int[][] ans = new int[maze.length][maze[0].length];
        if (!isSafe(maze, 0, 0) || !isSafe(maze, maze.length-1, maze[0].length-1)) {
            return ans;
        }

        solveMaze(maze, 0, 0, ans);
        printSolution(ans);

        return ans;
    }

    public boolean isSafe(int[][] maze, int i, int j) {
        if (maze[i][j] == 1) {
            return  true;
        }
        return false;
    }

    public boolean solveMaze(int[][] maze, int i, int j, int[][] ans) {
        if (i == maze.length-1 && j == maze[0].length-1 && isSafe(maze, i, j)) {
            ans[i][j] = 1;
            return true;
        }
        if (i < maze.length && j < maze[0].length && isSafe(maze, i, j)) {
            ans[i][j] = 1;
            if (solveMaze(maze, i, j + 1, ans) || solveMaze(maze, i + 1, j, ans)) {
                return true;
            }
            ans[i][j] = 0;
        }
        return false;
    }

    public void printSolution(int[][] ans) {
        for (int i = 0; i < ans.length; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }
    }

    public int countPaths(int[][] maze) {

        int[][] path = new int[maze.length][maze[0].length];
        int R = maze.length;
        int C = maze[0].length;

        for (int i = 0; i < R; i++) {
            if (maze[i][0] == 1)
                path[i][0] = 1;
            else
                break;
        }

        for (int i = 1; i < C; i++) {
            if (maze[0][i] == 1)
                path[0][i] = 1;
            else
                break;
        }

        for (int i =1; i < R; i++) {
            for (int j = 1; j < C; j++) {

                if (maze[i][j] == 0)
                    continue;

                else
                    path[i][j] += path[i][j-1] + path[i-1][j];
            }
        }

        return path[R-1][C-1];
    }

    public static void main(String args[])
    {
        RatInMaze rat = new RatInMaze();
        int maze[][] = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {1, 1, 1, 1}
        };
        System.out.println(" Maze is ");
        rat.printSolution(maze);

        System.out.println("\nSolution is :");
        rat.solveMaze(maze);

        System.out.println(" Total Paths count is : " + rat.countPaths(maze));
    }
}
