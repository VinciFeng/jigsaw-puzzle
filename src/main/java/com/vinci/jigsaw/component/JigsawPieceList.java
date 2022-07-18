package com.vinci.jigsaw.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinci
 * @date 2022/07/15
 */
public class JigsawPieceList {

    private List<JigsawPiece> pieceList;

    public JigsawPieceList() {
        this(new JigsawPieceBasicShape(), new ArrayList<>(), true, false);
    }

    public JigsawPieceList(boolean withFlip, boolean withCheck) {
        this(new JigsawPieceBasicShape(), new ArrayList<>(), withFlip, withCheck);
    }

    public JigsawPieceList(JigsawPieceBasicShape pieceBasicShape, List<JigsawPiece> pieceList, boolean withFlip, boolean withCheck) {
        this.pieceList = pieceList;
        List<int[][]> pieceShapes = pieceBasicShape.getPieces();
        for (int i = 0; i < pieceShapes.size(); i++) {
            pieceList.add(new JigsawPiece(pieceShapes.get(i), withFlip, withCheck).setPieceID(i + 1));
        }
    }

    public List<JigsawPiece> getPieceList() {
        return pieceList;
    }
}
