package com.vinci.jigsaw.tool;

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
}
