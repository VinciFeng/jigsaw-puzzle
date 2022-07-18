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
        this(new JigsawPieceBasicShape(), new ArrayList<>(), true);
    }

    public JigsawPieceList(boolean withFlip) {
        this(new JigsawPieceBasicShape(), new ArrayList<>(), withFlip);
    }

    public JigsawPieceList(JigsawPieceBasicShape pieceBasicShape, List<JigsawPiece> pieceList, boolean withFlip) {
        this.pieceList = pieceList;
        List<int[][]> pieceShapes = pieceBasicShape.getPieces();
        for (int i = 0; i < pieceShapes.size(); i++) {
            pieceList.add(new JigsawPiece(pieceShapes.get(i), withFlip).setPieceID(i + 1));
        }
    }

    public List<JigsawPiece> getPieceList() {
        return pieceList;
    }
}
