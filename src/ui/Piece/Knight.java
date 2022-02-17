package ui.Piece;

import ui.CellButton;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
    }

    public Knight(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
    }

    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-knight.png";
        }
        else
            imagePath = resourcePath+"/b-knight.png";
    }

    @Override
    protected void createNextPositions() {
        nextPositions = new ArrayList<>();
        for (int i = 0; i < 8; i++) nextPositions.add(new ArrayList<>());

        int[] mox = {2,2,1,-1,-2,-2,-1,1};
        int[] moy = {1,-1,-2,-2,-1,1,2,2};
        for(int i = 0; i < 8; i++)
            addPosition(i, rank+mox[i], file+moy[i]);
    }
}
