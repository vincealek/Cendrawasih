package Main.ui.utility;

import java.util.ArrayList;

public abstract class Piece {

    public static final int WHITE = 0, BLACK = 1;
    public static final String resourcePath = "./resource/piecesplaceholder";
    public String imagePath;
    protected int rank, file, color;
    public boolean hasMoved;
    protected Board board;
    protected ArrayList<ArrayList<Position>> unObstructedMoves;
    protected ArrayList<Position> obstructedMoves, legalMoves;

    public Piece(int color) {
        this.color = color;
        setImagePath();
    }

    public Piece(Board board, int rank, int file, int color) {
        this.board = board;
        this.rank = rank;
        this.file = file;
        this.color = color;
        this.hasMoved = false;
        setImagePath();
        createUnObstructedMoves();
    }

    protected abstract void setImagePath();

    protected void addPosition(int idx, int rank, int file) {
        if (rank >= 0 && rank < 8 && file >= 0 && file < 8) {
            unObstructedMoves.get(idx).add(new Position(rank,file));
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    protected abstract void createUnObstructedMoves();

    public void createObstructedMoves() {
        createUnObstructedMoves();
        obstructedMoves = new ArrayList<>();
        for (ArrayList<Position> nextPosition : unObstructedMoves) {
            for (Position position : nextPosition) {
                int rank = position.rank;
                int file = position.file;
                Piece piece = board.get(rank,file);
                if (piece != null) {
                    if (this.color != piece.getColor()) {
                        obstructedMoves.add(new Position(rank, file));
                    }
                    break;
                }
                obstructedMoves.add(new Position(rank, file));
            }
        }
    }

    public void createLegalMoves() {
        createObstructedMoves();
        legalMoves = new ArrayList<>();
        ArrayList<Position> arrayList = new ArrayList<>(obstructedMoves);
        int tempRank = rank, tempFile = file;
        for(Position pos : arrayList) {
            Piece piece = board.get(pos);
            board.move(rank, file, pos.rank, pos.file);
            board.updateTurn();
            if(board.isLegal()) {
                legalMoves.add(pos);
            }
            board.move(rank, file, tempRank, tempFile);
            board.updateTurn();
            board.set(pos.rank, pos.file, piece);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPosition(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public void setPosition(Position position) {
        this.rank = position.rank;
        this.file = position.file;
    }

    public void updateHasMoved() {
        hasMoved = true;
    }

    public ArrayList<Position> getObstructedMoves() {
        return obstructedMoves;
    }

    public ArrayList<Position> getLegalMoves() {
        return legalMoves;
    }

    public int getColor() {
        return color;
    }
    public int getFile() {
        return file;
    }
    public int getRank() {
        return rank;
    }
}
