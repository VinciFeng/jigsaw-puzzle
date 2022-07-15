package com.vinci.jigsaw.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinci
 * @date 2022/07/15
 */
public class JigsawPieceList {

    JigsawPieceBasicShape pieceBasicShape;

    List<JigsawPiece> pieceList;

    public JigsawPieceList() {
        this(new JigsawPieceBasicShape(), new ArrayList<>());
    }

    public JigsawPieceList(JigsawPieceBasicShape pieceBasicShape, List<JigsawPiece> pieceList) {
        this.pieceBasicShape = pieceBasicShape;
        this.pieceList = pieceList;
        List<int[][]> pieceShapes = pieceBasicShape.getPieces();
        for (int[][] pieceShape : pieceShapes) {
            pieceList.add(new JigsawPiece(pieceShape));
        }
    }

    public List<JigsawPiece> getPieceList() {
        return pieceList;
    }
}
