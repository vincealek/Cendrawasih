package Main.ui.utility;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    public Queen(Board board, int rank, int file, int color) {
        super(board, rank, file, color);
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
    protected void createNextPositions() {
        nextPositions = new ArrayList<>();
        for (int i = 0; i < 8; i++) nextPositions.add(new ArrayList<>());

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
