package ui.Piece;

import ui.CellButton;
import ui.Cendrawasih;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
        name = "Pawn";
    }

    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-pawn.png";
        }
        else
            imagePath = resourcePath+"/b-pawn.png";
    }

    @Override
    protected void createUnobstructedMove() {
        unobstructedMove = new ArrayList<>();
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());

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
    public void createObstructedMove() {
        ArrayList<ArrayList<CellButton>> board = Cendrawasih.PANEL.getBoard();
        obstructedMove = new ArrayList<>();
        for (Position position : unobstructedMove.get(0)) {
            int rank = position.rank;
            int file = position.file;
            Piece piece = board.get(rank).get(file).getPiece();
            if (piece != null) {
                break;
            }
            obstructedMove.add(new Position(rank, file));
        }
        for (Position position : unobstructedMove.get(1)) {
            int rank = position.rank;
            int file = position.file;
            Piece piece = board.get(rank).get(file).getPiece();
            if (piece != null) {
                if (this.color != piece.getColor()) {
                    obstructedMove.add(new Position(rank, file));
                }
            }
        }
    }
}
