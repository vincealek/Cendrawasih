package Main.ui.utility;

import Main.ui.PromotionDialog;

import java.util.ArrayList;

public class Board {
    int turn;
    ArrayList<ArrayList<Piece>> pieces;

    public Board() {
        this.turn = Piece.WHITE;
        pieces = new ArrayList<>();
        for(int i = 0; i < 8; i++)
            pieces.add(new ArrayList<>());

        createFirstRank(0, Piece.BLACK);
        createFirstRank(7, Piece.WHITE);
        for(int i = 0; i < 8; i++) {
            pieces.get(1).add(new Pawn(this, 1, i, Piece.BLACK));
            pieces.get(6).add(new Pawn(this, 6, i, Piece.WHITE));
        }
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                pieces.get(i).add(null);
            }
        }
    }

    // MODIFIES : this.arrayList
    // EFFECTS  : helper function to create the first/last rank of the chessboard
    private void createFirstRank(int x, int color) {
        pieces.get(x).add(new Rook(this, x, 0, color));
        pieces.get(x).add(new Knight(this, x, 1, color));
        pieces.get(x).add(new Bishop(this, x, 2, color));
        pieces.get(x).add(new Queen(this, x, 3, color));
        pieces.get(x).add(new King(this, x, 4, color));
        pieces.get(x).add(new Bishop(this, x, 5, color));
        pieces.get(x).add(new Knight(this, x, 6, color));
        pieces.get(x).add(new Rook(this, x, 7, color));
    }

    public boolean isLegal() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece piece = get(i,j);
                if(piece != null) {
                    piece.createObstructedMoves();
                    for(Position pos : piece.getObstructedMoves()) {
                        if(get(pos) != null && get(pos).getClass() == King.class &&
                                turn != get(pos).getColor()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    // REQUIRE  : get(originRank, originFile) != null
    // MODIFIES : this
    // EFFECTS  : move piece from origin rank/file to dest rank/file
    public void move(int originRank, int originFile, int destRank, int destFile) {
        Piece originPiece = get(originRank, originFile);
        originPiece.setPosition(destRank, destFile);
        set(destRank, destFile, originPiece);
        set(originRank, originFile, null);
        handleCastling(originRank, originFile, originPiece);
        handlePromotion(destRank, destFile);
    }

    private void handleCastling(int originRank, int originFile, Piece movedPiece) {
        if(movedPiece.getClass() == King.class) {
            if(originFile-movedPiece.getFile() == -2) {
                move(originRank, 7, originRank, movedPiece.getFile()-1);
            }
            if(originFile-movedPiece.getFile() == 2) {
                move(originRank, 0, originRank, movedPiece.getFile()+1);
            }
        }
    }
    private void handlePromotion(int rank, int file) {
        if(get(rank,file).getClass() == Pawn.class && (rank == 0 || rank == 7)) {
            new PromotionDialog(this, rank, file);
        }
    }

    public void updateTurn() {
        if(turn == Piece.WHITE) {
            turn = Piece.BLACK;
        }
        else turn = Piece.WHITE;
    }

    public void set(int rank, int file, Piece piece) {
        pieces.get(rank).set(file, piece);
    }

    public int getTurn() {
        return turn;
    }

    public Piece get(int x, int y) {
        return pieces.get(x).get(y);
    }

    public Piece get(Position position) {
        return pieces.get(position.rank).get(position.file);
    }
}
