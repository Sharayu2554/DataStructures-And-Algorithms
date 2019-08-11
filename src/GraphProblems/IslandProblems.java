/**
 * Class to Implement IslanProblems
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/5/19
 * Project : GraphProblems
 **/

package GraphProblems;

public class IslandProblems {

    public int islandPerimeterOptimized(int[][] grid) {
        int cube = 0;
        int duplicate = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    cube++;
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1)
                        duplicate++;
                    if (i < grid.length - 1 && grid[i + 1][j] == 1)
                        duplicate++;
                }
            }
        }
        return 4 * cube - 2 * duplicate;
    }

    public boolean isSafe(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r > grid.length-1 || c > grid[0].length -1 || grid[r][c] == 0) {
            return false;
        }
        return true;
    }

    public int perimeter(int[][] grid, int r, int c, boolean[][] visited) {

        if (!isSafe(grid, r, c)) {
            return 1;
        }

        if (visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;
        int count = 0;
        count += perimeter(grid, r-1, c, visited);
        count += perimeter(grid, r+1, c, visited);
        count += perimeter(grid, r, c-1, visited);
        count += perimeter(grid, r, c+1, visited);
        return count;
    }

    public int islandPerimeter(int[][] grid) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j =0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return perimeter(grid, i, j, visited);
                }
            }
        }
        return 0;
    }

    private void DFSMarking(int[][] grid, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != 1) return;
        grid[i][j] = '0';
        DFSMarking(grid, i + 1, j, n, m);
        DFSMarking(grid, i - 1, j, n, m);
        DFSMarking(grid, i, j + 1, n, m);
        DFSMarking(grid, i, j - 1, n, m);
    }


    public int numIslands(int[][] grid) {

        int count = 0;
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 1) {
                    DFSMarking(grid, i, j, n, m);
                    ++count;
                }
        }
        return count;
    }

    public void wallsAndGates(int[][] rooms) {
        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j]==0){
                    helper(rooms,i,j,0);
                }
            }
        }
    }
    public void helper(int[][] rooms, int i ,int j,int distance){
        if(i<0||j<0||i>=rooms.length||j>=rooms[0].length||rooms[i][j]<distance){
            return;
        }
        rooms[i][j]=distance;
        helper(rooms,i+1,j,distance+1);
        helper(rooms,i-1,j,distance+1);
        helper(rooms,i,j+1,distance+1);
        helper(rooms,i,j-1,distance+1);
    }

    public static void main(String args[]) {
        IslandProblems is = new IslandProblems();
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int peri = is.islandPerimeter(grid);
        System.out.println("Island Perimiter " + peri);

        peri = is.islandPerimeterOptimized(grid);
        System.out.println("Island Perimiter " + peri);

        System.out.println(" Number of islands : " + is.numIslands(grid));

        int[][] rooms = {
            {2147483647,-1,0,2147483647},
            {2147483647,2147483647,2147483647,-1},
            {2147483647,-1,2147483647,-1},
            {0,-1,2147483647,2147483647}
        };
        is.wallsAndGates(rooms);


    }
}
