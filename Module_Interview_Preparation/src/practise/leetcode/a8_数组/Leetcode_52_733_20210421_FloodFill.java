package practise.leetcode.a8_数组;

public class Leetcode_52_733_20210421_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        doFloodFill(image,sr,sc,-newColor,image[sr][sc]);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                image[i][j] = Math.abs(image[i][j]);
            }
        }
        return image;
    }

    private void doFloodFill( int[][] image, int sr, int sc, int newColor , int targetColor ) {
        if(image[sr][sc]!=targetColor) {
            return;
        }

        if(image[sr][sc] == targetColor) {
            image[sr][sc] = newColor;
            if (sr >= 1)
                doFloodFill(image, sr - 1, sc, newColor, targetColor);
            if (sc < image[sr].length - 1)
                doFloodFill(image, sr, sc + 1, newColor, targetColor);
            if (sr < image.length - 1)
                doFloodFill(image, sr + 1, sc, newColor, targetColor);
            if (sc >= 1)
                doFloodFill(image, sr, sc - 1, newColor, targetColor);
        }
    }

}
