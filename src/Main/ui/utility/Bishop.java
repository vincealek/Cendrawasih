package Main.ui.utility;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
    }

    public Bishop(Board board, int rank, int file, int color) {
        super(board, rank, file, color);
    }

    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-bishop.png";
        }
        else
            imagePath = resourcePath+"/b-bishop.png";
    }

    @Override
    protected void createNextPositions() {
        nextPositions = new ArrayList<>();
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());
        nextPositions.add(new ArrayList<>());

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
