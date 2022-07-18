package com.vinci.jigsaw.component;

import com.vinci.jigsaw.constant.JigsawConstant;
import com.vinci.jigsaw.tool.ArrayTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawPuzzle {

    private static final Logger logger = LoggerFactory.getLogger(JigsawPuzzle.class);

    private final JigsawBoard jigsawBoard;

    private final List<JigsawPiece> jigsawPieceList;

    private final List<int[][]> jigsawResult;

    public JigsawPuzzle() {
        this.jigsawBoard = new JigsawBoard().setDate(LocalDate.now());
        this.jigsawPieceList = new JigsawPieceList().getPieceList();
        this.jigsawResult = new LinkedList<>();
    }

    public JigsawPuzzle(LocalDate localDate) {
        this.jigsawBoard = new JigsawBoard().setDate(localDate);
        this.jigsawPieceList = new JigsawPieceList().getPieceList();
        this.jigsawResult = new LinkedList<>();
    }

    /** 默认搜索10条结果 */
    public void searchResult() {
        search(JigsawConstant.DEFAULT_RESULT_NUMBER);
    }

    /** 搜索指定数目结果 */
    public void searchResult(int resultNumber) {
        search(resultNumber);
    }

    /** 搜索所有结果 */
    public void searchAllResult() {
        search(JigsawConstant.ALL_RESULT_NUMBER);
    }

    public void search(int resultNumber) {
        backTracing(jigsawBoard.getBoard(), 0, resultNumber);
        renderDateAndWall();
    }

    public void backTracing(int[][] board, int pieceId, int resultNumber) {
        // 终止条件1，如果遍历完了最后一个拼图
        if (pieceId >= jigsawPieceList.size()) {
            // 深拷贝解决方案，添加到解的集合中
            jigsawResult.add(ArrayTool.deepCopyArray(board));
            return;
        }
        // 终止条件2，判断当前是否有过小的孤岛
//        if (!ArrayTool.islandChecked(ArrayTool.deepCopyArray(board))) {
//            return;
//        }
        // 终止条件3，判断是否已经搜索到足够数目的结果
        if (resultNumber != -1 && jigsawResult.size() == resultNumber) {
            return;
        }
        // 获取当前的拼图碎片
        JigsawPiece jigsawPiece = jigsawPieceList.get(pieceId);
        // 遍历所有形状
        for (int i = 0; i < jigsawPiece.getShapes().size(); i++) {
            // 设置形状ID
            jigsawPiece.setShapeId(i);
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board[j].length; k++) {
                    // 如果匹配上了，做选择
                    if (match(board, jigsawPiece, j, k)) {
                        // 渲染面板
                        render(board, jigsawPiece);
                        // 递归
                        backTracing(board, pieceId + 1, resultNumber);
                        // 回溯
                        recover(board, jigsawPiece);
                    }
                }
            }
        }
        //
    }

    /** 检查拼图与当前位置是否匹配 */
    private boolean match(int[][] board, JigsawPiece jigsawPiece, int br, int bc) {
        int[][] piece = jigsawPiece.getShapes().get(jigsawPiece.getShapeId());
        int pr = piece.length;
        int pc = piece[0].length;
        // 边界检查判断
        if (br + pr - 1 >= board.length || bc + pc - 1 >= board[0].length) {
            return false;
        }
        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                if (piece[i][j] != 0 && board[br + i][bc + j] != 0) {
                    return false;
                }
            }
        }
        // 设置坐标位置
        jigsawPiece.setCoordinate(new int[]{br, bc});
        return true;
    }

    /** 渲染拼图碎片到面板上 */
    private void render(int[][] board, JigsawPiece jigsawPiece) {
        int[][] piece = jigsawPiece.getShapes().get(jigsawPiece.getShapeId());
        int pr = piece.length;
        int pc = piece[0].length;
        int[] coordinate = jigsawPiece.getCoordinate();
        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                if (piece[i][j] != 0) {
                    board[coordinate[0] + i][coordinate[1] + j] = jigsawPiece.getPieceID();
                }
            }
        }
    }

    /** 从当前面板上恢复碎片位置的占用 */
    private void recover(int[][] board, JigsawPiece jigsawPiece) {
        int[][] piece = jigsawPiece.getShapes().get(jigsawPiece.getShapeId());
        int pr = piece.length;
        int pc = piece[0].length;
        int br = jigsawPiece.getCoordinate()[0];
        int bc = jigsawPiece.getCoordinate()[1];
        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                if (piece[i][j] != 0) {
                    board[br + i][bc + j] = 0;
                }
            }
        }
        jigsawPiece.clearCoordinate();
    }

    private void renderDateAndWall() {
        renderDate();
        renderWall();
    }

    private void renderDate() {
        for (int[][] board : jigsawResult) {
            LocalDate date = LocalDate.now();
            int[] month = jigsawBoard.getMonths().get(date.getMonthValue());
            int[] dayOfMonth = jigsawBoard.getDayOfMonths().get(date.getDayOfMonth());
            int[] dayOfWeek = jigsawBoard.getDayOfWeeks().get(date.getDayOfWeek().getValue());
            board[month[0]][month[1]] = JigsawConstant.RENDER_DATE_VALUE;
            board[dayOfMonth[0]][dayOfMonth[1]] = JigsawConstant.RENDER_DATE_VALUE;
            board[dayOfWeek[0]][dayOfWeek[1]] = JigsawConstant.RENDER_DATE_VALUE;
        }
    }

    private void renderWall() {
        for (int[][] board : jigsawResult) {
            for (int[] wall : jigsawBoard.getWalls()) {
                board[wall[0]][wall[1]] = JigsawConstant.RENDER_WALL_VALUE;
            }
        }
    }

    /** 打印游戏的初始面板 */
    public void showInitBoard() {
        jigsawBoard.showBoard();
    }

    /** 打印所有结果 */
    public void showResult() {
        for (int i = 0; i < jigsawResult.size(); i++) {
            logger.info(JigsawConstant.THE_TH_SOLUTION, i + 1);
            ArrayTool.printArray(jigsawResult.get(i));
        }
    }

}
