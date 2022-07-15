package com.vinci.jigsaw.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinci
 * @date 2022/07/15
 */
public class JigsawPieceList {

    private JigsawPieceBasicShape pieceBasicShape;

    private List<JigsawPiece> pieceList;

    public JigsawPieceList() {
        this(new JigsawPieceBasicShape(), new ArrayList<>(), false);
    }

    public JigsawPieceList(JigsawPieceBasicShape pieceBasicShape, List<JigsawPiece> pieceList, boolean withFlip) {
        this.pieceBasicShape = pieceBasicShape;
        this.pieceList = pieceList;
        List<int[][]> pieceShapes = pieceBasicShape.getPieces();
        for (int[][] pieceShape : pieceShapes) {
            pieceList.add(new JigsawPiece(pieceShape, withFlip));
        }
    }

    public List<JigsawPiece> getPieceList() {
        return pieceList;
    }
}
