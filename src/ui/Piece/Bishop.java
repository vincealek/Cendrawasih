package ui.Piece;

import ui.CellButton;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
        name = "Bishop";
    }

    public Bishop(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
        name = "Bishop";
    }

    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-bishop.png";
        }
        else
            imagePath = resourcePath+"/b-bishop.png";
    }

    @Override
    protected void createUnobstructedMove() {
        unobstructedMove = new ArrayList<>();
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());

        int[] mox = {1, -1, -1, 1};
        int[] moy = {1, 1, -1, -1};

        // for directions
        for(int i = 0; i < 4; i++) {
            // for distance
            for (int j = 1; j < 8; j++) {
                addPosition(i, rank+j*mox[i], file+j*moy[i]);
            }
        }
    }
}
