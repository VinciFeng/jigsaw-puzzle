package com.vinci.jigsaw.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawBoard {

    private static final Logger logger = LoggerFactory.getLogger(JigsawBoard.class);

    /** 拼图面板 */
    private int[][] board;

    /** 记录月、日、星期的坐标，用于快速获取日期坐标，空间换时间 */
    private Map<Integer, int[]> months;

    private Map<Integer, int[]> dayOfMonths;

    private Map<Integer, int[]> dayOfWeeks;


    /** 构造初始化 */
    public JigsawBoard() {
        // 初始化面板
        initBoard();
        // 初始化坐标
        initCoordinate();
    }

    /** 拼图面板初始化 */
    public void initBoard() {
        board = new int[][]{
                {0, 0, 0, 0, 0, 0, -1},
                {0, 0, 0, 0, 0, 0, -1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {-1, -1, -1, -1, 0, 0, 0},
        };
    }

    /** 日期坐标初始化 */
    public void initCoordinate() {
        months = new HashMap<>(12);
        dayOfMonths = new HashMap<>(31);
        dayOfWeeks = new HashMap<>(7);
        for (int i = 1; i <= 12; i++) {
            months.put(i, new int[]{(i - 1) / 6, (i - 1) % 6});
        }
        for (int i = 1; i <= 31; i++) {
            dayOfMonths.put(i, new int[]{(i - 1) / 7 + 2, (i - 1) % 7});
        }
        dayOfWeeks.put(7, new int[]{6, 3});
        for (int i = 1; i <= 3; i++) {
            dayOfWeeks.put(i, new int[]{6, i + 3});
        }
        for (int i = 4; i <= 6; i++) {
            dayOfWeeks.put(i, new int[]{7, i});
        }
    }

    /** 显示拼图面板 */
    public void showBoard() {
        logger.info("==========拼图面板==========");
        for (int[] line : board) {
            logger.info("{}", line);
        }
        logger.info("==========================");
    }

    /** 设置日期占位 */
    public void setDate() {
        setDate(LocalDate.now());
    }

    /** 设置日期占位 */
    public void setDate(LocalDate date) {
        int[] month = months.get(date.getMonthValue());
        int[] dayOfMonth = dayOfMonths.get(date.getDayOfMonth());
        int[] dayOfWeek = dayOfWeeks.get(date.getDayOfWeek().getValue());
        board[month[0]][month[1]] = -2;
        board[dayOfMonth[0]][dayOfMonth[1]] = -2;
        board[dayOfWeek[0]][dayOfWeek[1]] = -2;
    }

    public static void main(String[] args) {
        JigsawBoard jigsawBoard = new JigsawBoard();
        jigsawBoard.showBoard();
        jigsawBoard.setDate();
        jigsawBoard.showBoard();
    }
}
