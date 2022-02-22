package Main.ui.utility;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(int color) {
        super(color);
    }

    public Rook(Board board, int rank, int file, int color) {
        super(board, rank, file, color);
    }

    @Override
    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-rook.png";
        }
        else
            imagePath = resourcePath+"/b-rook.png";
    }

    @Override
    protected void createNextPositions() {
        nextPositions = new ArrayList<>();
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());

        int[] mox = {1, 0, -1, 0};
        int[] moy = {0, 1, 0, -1};

        // for directions
        for(int i = 0; i < 4; i++) {
            // for distance
            for (int j = 1; j < 8; j++) {
                addPosition(i, rank+j*mox[i], file+j*moy[i]);
            }
        }
    }

}
