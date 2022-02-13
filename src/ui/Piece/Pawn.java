package ui.Piece;

import ui.BoardPanel;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(BoardPanel boardPanel, int rank, int file, int color) {
        super(boardPanel, rank, file, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-pawn.png";
        }
        else
            imagePath = resourcePath+"/b-pawn.png";
    }

    @Override
    protected void createNextPositions() {
        nextPositions = new ArrayList<>();
        nextPositions.add(new ArrayList<>());

        if (color == BLACK) {
            if (!hasMoved) {
                addPosition(0, rank+2, file);
            }
            if (rank+1 < 8) {
                addPosition(0, rank+1, file);
            }
        }
        else {
            if (!hasMoved) {
                addPosition(0, rank-2, file);
            }
            if (rank-1 >= 0) {
                addPosition(0, rank-1, file);
            }
        }
    }
}
