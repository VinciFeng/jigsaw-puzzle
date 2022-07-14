package com.vinci.jigsaw.component;

import java.time.LocalDate;
import java.util.List;

/**
 * 拼图游戏
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawPuzzle {

    JigsawBoard jigsawBoard;

    JigsawPiece jigsawPiece;

    public JigsawPuzzle() {
        this.jigsawBoard = new JigsawBoard();
        this.jigsawPiece = new JigsawPiece();
    }

    public void setDateNow() {
        jigsawBoard.setDateNow();
    }

    public void setDate(LocalDate date) {
        jigsawBoard.setDate(date);
    }

    public void showBoard() {
        jigsawBoard.showBoard();
    }

    public void solution() {
        // 获取地图
        int[][] board = jigsawBoard.getBoard();
        List<int[][]> pieces = jigsawPiece.getPieces();
        // 遍历地图
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i][j]; j++) {
                // 获取一张拼图碎片，测试是否能放下，能则继续，不能则转换方向，如果还不能则切换碎片
            }
        }

    }

    /** 判断拼图碎片放置的合法性 */
    public boolean isLegal(int[][] piece, int[][] board, int i, int j) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (i + r < board.length && j + c < board[i].length && board[i + r][j + c] != 0) {
                    return false;
                }
                board[i + r][j + c] = piece[r][c];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle();
        jigsawPuzzle.showBoard();
        // jigsawPuzzle.setDate(LocalDate.of(2022, 8, 2));
        jigsawPuzzle.setDateNow();
        jigsawPuzzle.showBoard();
    }
}
