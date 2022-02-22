package ui.Piece;

import ui.CellButton;
import ui.Cendrawasih;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(int color) {
        super(color);
        name = "Rook";
    }

    public Rook(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
        name = "Rook";
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
    protected void createUnobstructedMove() {
        unobstructedMove = new ArrayList<>();
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());
        unobstructedMove.add(new ArrayList<>());

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

    @Override
    public void createObstructedMove() {
        ArrayList<ArrayList<CellButton>> board = Cendrawasih.PANEL.getBoard();
        obstructedMove = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            for(Position position : unobstructedMove.get(i)) {
                int rank = position.rank;
                int file = position.file;
                Piece piece = board.get(rank).get(file).getPiece();
                if (piece != null) {
                    if(this.color != piece.getColor()) {
                        obstructedMove.add(new Position(rank, file));
                    }
                    break;
                }
                obstructedMove.add(new Position(rank,file));
            }
        }
    }
}
