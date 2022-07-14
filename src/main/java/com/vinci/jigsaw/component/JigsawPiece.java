package com.vinci.jigsaw.component;

import com.vinci.jigsaw.constant.JigsawLogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼图碎片类
 * @author Vinci
 * @date 2022/07/14
 */
public class JigsawPiece {

    private static final Logger logger = LoggerFactory.getLogger(JigsawPiece.class);

    private final List<int[][]> pieces;

    public JigsawPiece() {
        pieces = new ArrayList<>();
        pieces.add(shape);
        pieces.add(shape2);
        pieces.add(shape3);
        pieces.add(shape4);
        pieces.add(shape5);
        pieces.add(shape6);
        pieces.add(shape7);
        pieces.add(shape8);
        pieces.add(shape9);
        pieces.add(shape10);
        logger.info(JigsawLogConstant.INIT_JIGSAW_PIECES);
    }

    public List<int[][]> getPieces() {
        return pieces;
    }

    int[][] shape = {
            {1, 1, 1},
            {1, -1, -1},
            {1, -1, -1}
    };

    int[][] shape2 = {
            {2, 2, -1},
            {-1, 2, -1},
            {-1, 2, 2}
    };

    int[][] shape3 = {
            {3, 3, 3},
            {-1, 3, -1},
            {-1, 3, -1}
    };

    int[][] shape4 = {
            {4, 4, 4, 4},
            {-1, -1, -1, -1}
    };

    int[][] shape5 = {
            {5, 5, 5, 5},
            {5, -1, -1, -1}
    };

    int[][] shape6 = {
            {6, 6, -1, -1},
            {-1, 6, 6, 6}
    };

    int[][] shape7 = {
            {7, 7, 7},
            {7, -1, 7}
    };

    int[][] shape8 = {
            {8, 8, -1},
            {-1, 8, 8}
    };

    int[][] shape9 = {
            {9, 9, 9},
            {9, -1, -1}
    };

    int[][] shape10 = {
            {10, 10, 10},
            {10, 10, -1}
    };
}
