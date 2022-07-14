package com.vinci.jigsaw.component;

import java.time.LocalDate;

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
        // 遍历地图

        // 获取一张拼图碎片，测试是否能放下，能则继续，不能则转换方向，如果还不能则切换碎片
    }

    public static void main(String[] args) {
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle();
        jigsawPuzzle.showBoard();
        // jigsawPuzzle.setDate(LocalDate.of(2022, 8, 2));
        jigsawPuzzle.setDateNow();
        jigsawPuzzle.showBoard();

    }
}
