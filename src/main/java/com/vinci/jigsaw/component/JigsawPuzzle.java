package com.vinci.jigsaw.component;

import com.vinci.jigsaw.tool.ArrayTool;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * 拼图游戏
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawPuzzle {

    private final LocalDate jigsawDate;

    private final JigsawBoard jigsawBoard;

    private final List<JigsawPiece> jigsawPieceList;

    private final List<int[][]> jigsawResult;

    public JigsawPuzzle() {
        this.jigsawDate = LocalDate.now();
        this.jigsawBoard = new JigsawBoard().setDate(jigsawDate);
        this.jigsawPieceList = new JigsawPieceList().getPieceList();
        this.jigsawResult = new LinkedList<>();
    }

    public void search() {
        backTracking(jigsawResult, jigsawBoard.getBoard(), 0);
    }


    public void backTracking(List<int[][]> result, int[][] board, int pieceId) {
        // 终止条件
        if (pieceId >= jigsawPieceList.size()) {
            result.add(ArrayTool.deepCopyArray(board));
        }
        // 遍历每一个拼图块
        for (int i = 0; i < jigsawPieceList.size(); i++) {
            // 遍历每一个形状
            JigsawPiece jigsawPiece = jigsawPieceList.get(i);
            List<int[][]> shapes = jigsawPiece.getShapes();
            // 返回放置碎片的位置，若没有则会为默认的负值
            int[] local = new int[]{-1, -1};
            for (int j = 0; j < shapes.size(); j++) {
                int[] location = location(board, shapes.get(j), i, local);
                if (location[0] >= 0) {
                    jigsawPiece.setShapeId(j);
                    break;
                }
            }
            // 选择


            // 递归

            // 回退

        }
    }

    public int[] location(int[][] board, int[][] piece, int pieceId, int[] res) {
        // 遍历位置
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (check(board, piece, i, j, pieceId)) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    /** 判断拼图碎片放置的合法性 */
    public boolean check(int[][] board, int[][] piece, int br, int bc, int pieceId) {
        int pr = piece.length;
        int pc = piece[0].length;
        // 边界判断
        if (br + pr >= board.length || bc + pc >= board[0].length) {
            return false;
        }
        // 判断是否能匹配拼图到面板上
        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                if (piece[i][j] != 0 && board[br + i][bc + j] != 0) {
                    return false;
                }
            }
        }
        // 渲染拼图到面板上
        for (int i = 0; i < pr; i++) {
            for (int j = 0; j < pc; j++) {
                if (piece[i][j] != 0) {
//                    board[br + i][bc + j] = piece[i][j];
                    board[br + i][bc + j] = pieceId + 1;
                }
            }
        }
        // 校验孤岛剪枝
        if (!checkBoard(ArrayTool.deepCopyArray(board))) {
            // 如果不满足要求，则恢复面板
            for (int i = 0; i < pr; i++) {
                for (int j = 0; j < pc; j++) {
                    if (piece[i][j] != 0) {
                        board[br + i][bc + j] = 0;
                    }
                }
            }
            return false;
        }
        return true;
    }

    /** 检查孤岛面积是否符合符合拼图要求 */
    public boolean checkBoard(int[][] board) {

        return false;
    }


    public static void main(String[] args) {
        JigsawPuzzle jigsawPuzzle = new JigsawPuzzle();
//        jigsawPuzzle.showBoard();
//        // jigsawPuzzle.setDate(LocalDate.of(2022, 8, 2));
//        jigsawPuzzle.setDate();
//        jigsawPuzzle.showBoard();
    }
}
