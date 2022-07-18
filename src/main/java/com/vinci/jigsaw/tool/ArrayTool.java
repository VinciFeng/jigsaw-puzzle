package com.vinci.jigsaw.tool;

import com.vinci.jigsaw.constant.JigsawConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Vinci
 * @date 2022/07/15
 */
public class ArrayTool {

    private static final Logger logger = LoggerFactory.getLogger(ArrayTool.class);

    /** 二维数组旋转90度 */
    public static int[][] rotate(int[][] shape) {
        if (shape.length < 1) {
            return new int[][]{};
        }
        //
        int row = shape.length;
        int col = shape[0].length;
        int[][] res = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[j][row - i - 1] = shape[i][j];
            }
        }
        return res;
    }

    /** 二维数组左右翻转 */
    public static int[][] flip(int[][] shape) {
        // 保护性检查
        if (shape.length < 1) {
            return new int[][]{};
        }
        // 开始翻转
        int row = shape.length;
        int col = shape[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res[i][j] = shape[i][col - j - 1];
            }
        }
        return res;
    }

    /** */
    public static boolean isRepeat(int[][] array, int[][] array2) {
        return Arrays.deepEquals(array, array2);
    }

    /** 深拷贝二维数组 */
    public static int[][] deepCopyArray(int[][] array) {
        if (array.length < 1) {
            return new int[][]{};
        }
        int[][] copy = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }

    /** 日志控制台打印二维数组 */
    public static void printArray(int[][] array) {
        for (int[] line : array) {
            logger.info("{}", line);
        }
    }

    /** 孤岛检查 */
    public static boolean islandChecked(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    int area = dfs(i, j, board);
                    if (area > 0 && area < JigsawConstant.DEFAULT_MIN_ISLAND_CHECKED_THRESHOLD) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 1) {
            return 0;
        }
        grid[i][j] = 1;
        int num = 1;
        num += dfs(i + 1, j, grid);
        num += dfs(i - 1, j, grid);
        num += dfs(i, j + 1, grid);
        num += dfs(i, j - 1, grid);
        return num;
    }
}
