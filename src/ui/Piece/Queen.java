package ui.Piece;

import ui.CellButton;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
        name = "Queen";
    }

    public Queen(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
        name = "Queen";
    }

    @Override
    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-queen.png";
        }
        else
            imagePath = resourcePath+"/b-queen.png";
    }

    @Override
    protected void createUnobstructedMove() {
        unobstructedMove = new ArrayList<>();
        for (int i = 0; i < 8; i++) unobstructedMove.add(new ArrayList<>());

        int[] mox = {1, 0, -1, 0, 1, -1, -1, 1};
        int[] moy = {0, 1, 0, -1, 1, 1, -1, -1};

        // for directions
        for(int i = 0; i < 8; i++) {
            // for distance
            for (int j = 1; j < 8; j++) {
                addPosition(i, rank+j*mox[i], file+j*moy[i]);
            }
        }
    }
}
