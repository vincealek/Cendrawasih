package Main.ui.utility;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
    }

    public Knight(Board board, int rank, int file, int color) {
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
    protected void createUnObstructedMoves() {
        unObstructedMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) unObstructedMoves.add(new ArrayList<>());

        int[] mox = {2,2,1,-1,-2,-2,-1,1};
        int[] moy = {1,-1,-2,-2,-1,1,2,2};
        for(int i = 0; i < 8; i++)
            addPosition(i, rank+mox[i], file+moy[i]);
    }
}
