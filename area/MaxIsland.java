public class MaxIsland {
    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println(new MaxIsland().maxAreaOfIsland(grid));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visit = new boolean[rows][cols];
        int res = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                res = Math.max(res, dfs(grid, i, j, visit));
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int r, int c, boolean[][] visit) {
        if (r >= grid.length || c >= grid[0].length || r < 0 || c < 0) return 0;
        if (visit[r][c] || grid[r][c] != 1) return 0;
        visit[r][c] = true;
        int res = 1 + dfs(grid, r+1, c, visit) + dfs(grid, r, c+1, visit) + dfs(grid, r-1, c, visit) + dfs(grid, r, c-1, visit);
        return res;
    }
}
