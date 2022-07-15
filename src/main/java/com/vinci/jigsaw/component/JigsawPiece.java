package com.vinci.jigsaw.component;

import com.vinci.jigsaw.constant.JigsawLogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼图碎片类
 * @author Vinci
 * @date 111/01/11
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
            {1, 0, 0},
            {1, 0, 0}
    };

    int[][] shape2 = {
            {1, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
    };

    int[][] shape3 = {
            {1, 1, 1},
            {0, 1, 0},
            {0, 1, 0}
    };

    int[][] shape4 = {
            {1, 1, 1, 1},
            {0, 0, 0, 0}
    };

    int[][] shape5 = {
            {1, 1, 1, 1},
            {1, 0, 0, 0}
    };

    int[][] shape6 = {
            {1, 1, 0, 0},
            {0, 1, 1, 1}
    };

    int[][] shape7 = {
            {1, 1, 1},
            {1, 0, 1}
    };

    int[][] shape8 = {
            {1, 1, 0},
            {0, 1, 1}
    };

    int[][] shape9 = {
            {1, 1, 1},
            {1, 0, 0}
    };

    int[][] shape10 = {
            {1, 1, 1},
            {1, 1, 0}
    };
}
