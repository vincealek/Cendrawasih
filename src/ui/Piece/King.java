package ui.Piece;

import ui.CellButton;
import ui.Cendrawasih;

import java.util.ArrayList;

public class King extends Piece {
    public King(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        super(board, rank, file, color);
        name = "King";
    }

    @Override
    protected void setImagePath() {
        if(color == WHITE) {
            imagePath = resourcePath+"/w-king.png";
        }
        else
            imagePath = resourcePath+"/b-king.png";
    }

    @Override
    protected void createUnobstructedMove() {
        unobstructedMove = new ArrayList<>();
        for (int i = 0; i < 8; i++) unobstructedMove.add(new ArrayList<>());

        int[] mox = {1, 0, -1, 0, 1, -1, -1, 1};
        int[] moy = {0, 1, 0, -1, 1, 1, -1, -1};
        for(int i = 0; i < 8; i++)
            addPosition(i, rank+mox[i], file+moy[i]);
    }

    @Override
    public void createObstructedMove() {
        super.createObstructedMove();

        ArrayList<ArrayList<CellButton>> board = Cendrawasih.PANEL.getBoard();
        if (!this.hasMoved) {
            Piece kRook = board.get(this.rank).get(7).getPiece();
            boolean castleKingside = (kRook != null && !kRook.hasMoved);
            if (castleKingside) {
                for (int i = this.file+1; i < 7; i++) {
                    if (board.get(this.rank).get(i).getPiece() != null) {
                        castleKingside = false;
                        break;
                    }
                }
            }
            Piece qRook = board.get(this.rank).get(0).getPiece();
            boolean castleQueenside = (qRook != null && !qRook.hasMoved);
            if (castleQueenside) {
                for (int i = this.file-1; i >= 1; i--) {
                    if (board.get(this.rank).get(i).getPiece() != null) {
                        castleQueenside = false;
                        break;
                    }
                }
            }
            if (castleKingside) {
                obstructedMove.add(new Position(this.rank, this.file+2));
            }
            if (castleQueenside) {
                obstructedMove.add(new Position(this.rank, this.file-2));
            }
        }
    }
}
