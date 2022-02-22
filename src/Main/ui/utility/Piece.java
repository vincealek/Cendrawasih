package Main.ui.utility;

import java.util.ArrayList;

public abstract class Piece {

    public static final int WHITE = 0, BLACK = 1;
    public static final String resourcePath = "./resource/piecesplaceholder";
    public String imagePath;
    protected int rank, file, color;
    public boolean hasMoved;
    protected Board board;
    protected ArrayList<ArrayList<Position>> nextPositions;
    protected ArrayList<Position> legalNextPositions;

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
        createNextPositions();
    }

    protected abstract void setImagePath();

    protected void addPosition(int idx, int rank, int file) {
        if (rank >= 0 && rank < 8 && file >= 0 && file < 8) {
            nextPositions.get(idx).add(new Position(rank,file));
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    protected abstract void createNextPositions();

    public void createLegalNextPositions() {
        legalNextPositions = new ArrayList<>();
        for (ArrayList<Position> nextPosition : nextPositions) {
            for (Position position : nextPosition) {
                int rank = position.rank;
                int file = position.file;
                Piece piece = board.get(rank,file);
                if (piece != null) {
                    if (this.color != piece.getColor()) {
                        legalNextPositions.add(new Position(rank, file));
                    }
                    break;
                }
                legalNextPositions.add(new Position(rank, file));
            }
        }
    }

    public void move(int rank, int file) {
        this.rank = rank;
        this.file = file;
        this.hasMoved = true;
        createNextPositions();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPosition(Position position) {
        rank = position.rank;
        file = position.file;
        createNextPositions();
    }

    public ArrayList<ArrayList<Position>> getNextPositions() {
        return nextPositions;
    }
    public ArrayList<Position> getLegalNextPositions() {
        return legalNextPositions;
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
