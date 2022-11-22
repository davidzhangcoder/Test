package practise.leetcode.a10_动态规划;

public class Leetcode_58_64_20210614_MinimumPathSum {

    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(i==0){
                    if(j>0)
                        grid[i][j] = grid[i][j] + grid[i][j-1];
                }else {
                    if(j==0)
                        grid[i][j] = grid[i][j] + grid[i-1][j];
                    else
                        grid[i][j] = Math.min(grid[i][j] + grid[i][j-1],grid[i][j] + grid[i-1][j]);
                }
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }

}
