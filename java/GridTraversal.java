import java.util.ArrayDeque;

public class GridTraversal {
    private static class Cell {
        private int row;
        private int col;
        private int dist;
        private Cell(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
    private int INF = 2147483647;
    private boolean check(int row, int col, int[][] grid){
        return row >=0 && col>=0 && row < grid.length && col < grid[0].length && grid[row][col] == INF;
    }
    private void gridBfs(int[][] grid, ArrayDeque<Cell> queue){
        int[] dirX = new int[]{-1,1,0,0};
        int[] dirY = new int[]{0,0,-1,1};
        while(!queue.isEmpty()){
            Cell cell = queue.pollFirst();
            int row = cell.row, col = cell.col, dist = cell.dist;
            for(int i = 0; i < 4; i++){
                int nrow = row + dirX[i];
                int ncol = col + dirY[i];
                if(check(nrow, ncol, grid)){
                    grid[nrow][ncol] = 1 + dist;
                    queue.offerLast(new Cell(nrow, ncol, 1 + dist));
                }
            }
        }
        return;
    }
    public void islandsAndTreasure(int[][] grid) {
        ArrayDeque<Cell> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new Cell(i, j, 0));
                }
            }
        }
        gridBfs(grid, queue);
        return;
    }
    public static void main(String[] args){
        System.out.println("Grid traversal");
    }
}
