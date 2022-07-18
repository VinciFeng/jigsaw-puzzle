package com.vinci.jigsaw.component;

import com.vinci.jigsaw.constant.JigsawConstant;
import com.vinci.jigsaw.tool.ArrayTool;
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

    private int[][] walls;

    /** 构造初始化 */
    public JigsawBoard() {
        // 初始化面板
        initOriginalBoard();
        // 初始化墙体
        initWall();
        // 初始化日期坐标
        initCoordinate();
        logger.info(JigsawConstant.INIT_JIGSAW_BOARD);
    }

    /** 拼图面板初始化 */
    public void initOriginalBoard() {
        board = new int[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        logger.info(JigsawConstant.INIT_ORIGINAL_BOARD);
    }

    // 初始化墙体
    public void initWall() {
        walls = new int[][]{
                {0, 6},
                {1, 6},
                {7, 0},
                {7, 1},
                {7, 2},
                {7, 3},
        };
        initWall(walls);
    }

    public void initWall(int[][] walls) {
        for (int[] wall : walls) {
            board[wall[0]][wall[1]] = JigsawConstant.INIT_WALL_VALUE;
        }
        logger.info(JigsawConstant.INIT_WALL);
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
        logger.info(JigsawConstant.INIT_DATE_COORDINATE);
    }

    /** 设置日期占位 */
    public JigsawBoard setDate() {
        setDate(LocalDate.now());
        return this;
    }

    /** 设置日期占位 */
    public JigsawBoard setDate(LocalDate date) {
        int[] month = months.get(date.getMonthValue());
        int[] dayOfMonth = dayOfMonths.get(date.getDayOfMonth());
        int[] dayOfWeek = dayOfWeeks.get(date.getDayOfWeek().getValue());
        board[month[0]][month[1]] = JigsawConstant.INIT_DATE_VALUE;
        board[dayOfMonth[0]][dayOfMonth[1]] = JigsawConstant.INIT_DATE_VALUE;
        board[dayOfWeek[0]][dayOfWeek[1]] = JigsawConstant.INIT_DATE_VALUE;
        logger.info(JigsawConstant.FINISHED_DATE_SETTING);
        return this;
    }

    public int[][] getBoard() {
        return board;
    }

    /** 显示拼图面板 */
    public void showBoard() {
        logger.info(JigsawConstant.SHOW_JIGSAW_BOARD_HEAD);
        ArrayTool.printArray(board);
        logger.info(JigsawConstant.SHOW_JIGSAW_BOARD_TAIL);
    }

    public Map<Integer, int[]> getMonths() {
        return months;
    }

    public JigsawBoard setMonths(Map<Integer, int[]> months) {
        this.months = months;
        return this;
    }

    public Map<Integer, int[]> getDayOfMonths() {
        return dayOfMonths;
    }

    public JigsawBoard setDayOfMonths(Map<Integer, int[]> dayOfMonths) {
        this.dayOfMonths = dayOfMonths;
        return this;
    }

    public Map<Integer, int[]> getDayOfWeeks() {
        return dayOfWeeks;
    }

    public JigsawBoard setDayOfWeeks(Map<Integer, int[]> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
        return this;
    }

    public JigsawBoard setBoard(int[][] board) {
        this.board = board;
        return this;
    }

    public int[][] getWalls() {
        return walls;
    }

    public JigsawBoard setWalls(int[][] walls) {
        this.walls = walls;
        return this;
    }
}
