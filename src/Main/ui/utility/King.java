package Main.ui.utility;

import java.util.ArrayList;

public class King extends Piece {
    public King(Board board, int rank, int file, int color) {
        super(board, rank, file, color);
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
    protected void createUnObstructedMoves() {
        unObstructedMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) unObstructedMoves.add(new ArrayList<>());

        int[] mox = {1, 0, -1, 0, 1, -1, -1, 1};
        int[] moy = {0, 1, 0, -1, 1, 1, -1, -1};
        for(int i = 0; i < 8; i++)
            addPosition(i, rank+mox[i], file+moy[i]);
    }

    @Override
    public void createObstructedMoves() {
        super.createObstructedMoves();

        if (!this.hasMoved) {
            Piece kRook = board.get(rank, 7);
            boolean castleKingside = (kRook != null && !kRook.hasMoved);
            if (castleKingside) {
                for (int i = this.file+1; i < 7; i++) {
                    if (board.get(rank,i) != null) {
                        castleKingside = false;
                        break;
                    }
                }
            }
            Piece qRook = board.get(rank, 0);
            boolean castleQueenside = (qRook != null && !qRook.hasMoved);
            if (castleQueenside) {
                for (int i = this.file-1; i >= 1; i--) {
                    if (board.get(rank,i) != null) {
                        castleQueenside = false;
                        break;
                    }
                }
            }
            if (castleKingside) {
                obstructedMoves.add(new Position(this.rank, this.file+2));
            }
            if (castleQueenside) {
                obstructedMoves.add(new Position(this.rank, this.file-2));
            }
        }
    }
}
