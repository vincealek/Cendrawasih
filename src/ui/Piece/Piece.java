package ui.Piece;

import ui.BoardPanel;

import java.util.ArrayList;

public abstract class Piece {

    public static final int WHITE = 0, BLACK = 1;
    protected static final String resourcePath = "./resource/piecesplaceholder";
    protected final BoardPanel boardPanel;
    protected String imagePath;
    protected int rank, file, color;
    protected boolean hasMoved;
    protected ArrayList<ArrayList<Position>> nextPositions;
    protected ArrayList<Position> legalNextPositions;

    public Piece(BoardPanel boardPanel, int rank, int file, int color) {
        this.boardPanel = boardPanel;
        this.rank = rank;
        this.file = file;
        this.color = color;
        this.hasMoved = false;
        createNextPositions();
    }

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
            legalNextPositions.addAll(nextPosition);
        }
    }
    public ArrayList<ArrayList<Position>> getNextPositions() {
        return nextPositions;
    }
    public ArrayList<Position> getLegalNextPositions() {
        return legalNextPositions;
    }

    public void setPosition(Position position) {
        this.rank = position.rank;
        this.file = position.file;
        this.hasMoved = true;
        createNextPositions();
    }
}
