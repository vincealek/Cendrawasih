package ui.Piece;

import ui.CellButton;

import java.util.ArrayList;

public abstract class Piece {
    public static final int WHITE = 0, BLACK = 1;
    public static final String resourcePath = "./resource/piecesplaceholder";
    protected String name = "PIECE";
    public String imagePath;
    protected int rank, file, color;
    public boolean hasMoved;
    protected boolean captured;
    protected ArrayList<ArrayList<CellButton>> board;
    protected ArrayList<ArrayList<Position>> unobstructedMove;
    protected ArrayList<Position> obstructedMove, legalMove;

    public Piece(int color) {
        this.color = color;
        this.hasMoved = false;
        this.captured = false;
        setImagePath();
    }

    public Piece(ArrayList<ArrayList<CellButton>> board, int rank, int file, int color) {
        this.board = board;
        this.rank = rank;
        this.file = file;
        this.color = color;
        this.hasMoved = false;
        this.captured = false;
        setImagePath();
        createUnobstructedMove();
    }

    protected void addPosition(int idx, int rank, int file) {
        if (rank >= 0 && rank < 8 && file >= 0 && file < 8) {
            unobstructedMove.get(idx).add(new Position(rank,file));
        }
    }

    protected abstract void createUnobstructedMove();

    public void createObstructedMove() {
        ArrayList<ArrayList<CellButton>> board = this.board;
        obstructedMove = new ArrayList<>();
        for (ArrayList<Position> nextPosition : unobstructedMove) {
            for (Position position : nextPosition) {
                int rank = position.rank;
                int file = position.file;
                Piece piece = board.get(rank).get(file).getPiece();
                if (piece != null) {
                    if (this.color != piece.getColor()) {
                        obstructedMove.add(new Position(rank, file));
                    }
                    break;
                }
                obstructedMove.add(new Position(rank, file));
            }
        }
    }

    public void createLegalMove() {
        ArrayList<ArrayList<CellButton>> board = this.board;
        legalMove = new ArrayList<>();
        createObstructedMove();
        for (Position pos : obstructedMove) {
            int tRank = rank;
            int tFile = file;
            pretendMove(pos.rank, pos.file);
        }
    }

    private void pretendMove(int rank, int file) {
        Piece piece = board.get(rank).get(file).getPiece();
        if(piece != null) piece.setCaptured(true);
        this.rank = rank;
        this.file = file;
    }

    public void setPosition(Position position) {
        this.rank = position.rank;
        this.file = position.file;
        this.hasMoved = true;
        createUnobstructedMove();
    }

    protected abstract void setImagePath();

    public void setBoard(ArrayList<ArrayList<CellButton>> board) {
        this.board = board;
    }

    public void setCaptured(Boolean bool) {
        captured = bool;
    }

    public ArrayList<ArrayList<Position>> getUnobstructedMove() {
        return unobstructedMove;
    }
    public ArrayList<Position> getObstructedMove() {
        return obstructedMove;
    }
    public int getColor() {
        return color;
    }
    public String getImagePath() {
        return imagePath;
    }
    public int getFile() {
        return file;
    }
    public int getRank() {
        return rank;
    }
    public boolean isCaptured() {return captured; }

    @Override
    public String toString() {
        return name +
//                "imagePath='" + imagePath + '\'' +
                ", rank=" + rank +
                ", file=" + file +
                ", color=" + color +
//                ", hasMoved=" + hasMoved +
//                ", board=" + board +
//                ", unobstructedMove=" + unobstructedMove +
//                ", obstructedMove=" + obstructedMove +
//                ", legalMove=" + legalMove +
                '}';
    }
}
