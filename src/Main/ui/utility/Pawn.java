package Main.ui.utility;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Board board, int rank, int file, int color) {
        super(board, rank, file, color);
    }

    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-pawn.png";
        }
        else
            imagePath = resourcePath+"/b-pawn.png";
    }

    @Override
    protected void createUnObstructedMoves() {
        unObstructedMoves = new ArrayList<>();
        unObstructedMoves.add(new ArrayList<>());
        unObstructedMoves.add(new ArrayList<>());

        if (color == BLACK) {
            if (rank+1 < 8) {
                addPosition(0, rank+1, file);
            }
            if (!hasMoved) {
                addPosition(0, rank+2, file);
            }
            addPosition(1, rank+1, file+1);
            addPosition(1, rank+1, file-1);
        }
        else {
            if (rank-1 >= 0) {
                addPosition(0, rank-1, file);
            }
            if (!hasMoved) {
                addPosition(0, rank-2, file);
            }
            addPosition(1, rank-1, file+1);
            addPosition(1, rank-1, file-1);
        }
    }

    @Override
    public void createObstructedMoves() {
        createUnObstructedMoves();
        obstructedMoves = new ArrayList<>();
        for (Position position : unObstructedMoves.get(0)) {
            int rank = position.rank;
            int file = position.file;
            Piece piece = board.get(rank, file);
            if (piece != null) {
                break;
            }
            obstructedMoves.add(new Position(rank, file));
        }
        for (Position position : unObstructedMoves.get(1)) {
            int rank = position.rank;
            int file = position.file;
            Piece piece = board.get(rank, file);
            if (piece != null) {
                if (this.color != piece.getColor()) {
                    obstructedMoves.add(new Position(rank, file));
                }
            }
        }
    }
}
