/**
 * Class to Implement RatInMaze
 *
 * @author Sharayu Sharad Mantri
 * Date : 2/21/19
 * Project : DynamicProgramming
 **/

package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class RatInMaze {

    class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r &&
                    c == point.c;
        }

        @Override
        public String toString() {
            return "( " + r + " , " + c + " ) " ;
        }
    }

    /**
     * check if its is a valid row ,column and is not blocked
     * @param r
     * @param c
     * @param maze
     * @return
     */
    public boolean isSafe(int r, int c, boolean[][] maze) {

        if (r >= maze.length || c >= maze[0].length) {
            return false;
        }
        return maze[r][c];
    }

    /**
     * find a path given we can move only in front and bottom by 1 if its not blocked
     * @param maze
     * @param r
     * @param c
     * @param sol
     * @param failed
     * @return
     */
    public boolean findPath(boolean[][] maze, int r, int c, ArrayList<Point> sol, HashSet<Point> failed) {

        if (!isSafe(r, c, maze)) {
            return false;
        }

        if (r == maze.length -1 && c == maze[0].length-1) {
            sol.add(new Point(r, c));
            return true;
        }

        Point p = new Point(r, c);
        if (failed.contains(p)) {
            return false;
        }

        if (findPath(maze, r+1, c, sol, failed) || findPath(maze, r, c+1, sol, failed)) {
            sol.add(p);
            return true;
        }

        failed.add(p);
        return false;
    }

    /**
     * Print all possible paths to reach from 0,0 to n,n
     * @param maze
     * @param r
     * @param c
     * @param path
     * @param failed
     * @return
     */
    public boolean printAllPaths(boolean[][] maze, int r, int c, LinkedList<Point> path, HashSet<Point> failed) {

        if (!isSafe(r, c, maze)) {
            return false;
        }

        Point p = new Point(r, c);
        if (failed.contains(p)) {
            return false;
        }

        path.add(p);
        if (r == maze.length -1 && c == maze[0].length -1) {
            System.out.println(Arrays.toString(path.toArray()));
            path.removeLast();
            return true;
        }

        boolean c1 = printAllPaths(maze, r+1, c, path, failed);
        boolean c2 = printAllPaths(maze, r, c+1, path, failed);
        path.removeLast();

        return c1 || c2;
    }

    /**
     * Count total paths to reach from 0,0 to n,n
     * @param maze
     * @return
     */

    public int countOfPaths(boolean maze[][]) {
        int[][] count = new int[maze.length + 1][maze[0].length +1];
        count[1][1] = 1;

        for (int i =1; i <= maze.length; i++) {
            for (int j = 1; j <= maze[0].length; j++) {
                if (maze[i-1][j-1]) {
                    count[i][j] = count[i][j] + count[i-1][j] + count[i][j-1];
                }
            }
        }

        return count[maze.length][maze[0].length];
    }

    public ArrayList<Point> path(boolean maze[][]) {
        ArrayList<Point> sol = new ArrayList<>();
        HashSet<Point> failed = new HashSet<>();
        findPath(maze, 0, 0, sol, failed);
        return sol;
    }

    public void printAllPaths(boolean[][] maze) {
        HashSet<Point> failed = new HashSet<>();
        LinkedList<Point> path = new LinkedList<>();
        printAllPaths(maze, 0, 0, path, failed);
    }

    public static void main(String args[]) {

         boolean maze[][] = {
                {true,  true, true, true },
                {true,  false,  true, true  },
                {false, true,  true, true },
                {true,  true,  true,  true  }
        };
        RatInMaze r = new RatInMaze();
        ArrayList<Point>  sol = r.path(maze);
//        System.out.println(Arrays.toString(sol.toArray()));

        r.printAllPaths(maze);
        System.out.println("Total Number of Paths " + r.countOfPaths(maze));

    }
}
