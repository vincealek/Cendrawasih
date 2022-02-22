package ui;

import ui.Piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    public int turn;

    public CellButton selectedButton;
    public final ArrayList<ArrayList<CellButton>> board;
    private final ArrayList<Piece> pieces;

    // EFFECTS : create a board and a panel for the board
    public BoardPanel () {
        this.turn = Piece.WHITE;
        this.selectedButton = null;
        setLayout(new GridLayout(10,10));
        board = new ArrayList<>();
        pieces = new ArrayList<>();
        createBoard();
        createPanel();
        createPieces();
    }

    public boolean isLegal() {

        King king = null;
        for(Piece piece : pieces) {
            if(piece.getClass() == King.class && piece.getColor() != turn) {
                king = (King)piece;
            }
        }

        for(Piece piece : pieces) {
            if(piece.isCaptured()) continue;
            piece.createObstructedMove();
            for(Position position : piece.getObstructedMove()) {
                int rank = position.rank;
                int file = position.file;
                assert king != null;
                if(king.getRank() == rank && king.getFile() == file) {
                    return false;
                }
            }
        }
        return true;
    }

    // MODIFIES : this.board
    // EFFECTS  : create the chessboard
    private void createBoard() {
        for(int i = 0; i < 8; i++)
            board.add(new ArrayList<>());

       createFirstRank(0, Piece.BLACK);
       createFirstRank(7, Piece.WHITE);
        for(int i = 0; i < 8; i++) {
            board.get(1).add(new CellButton(this, 1, i , Cendrawasih.SIZE/10, new Pawn(board, 1, i, Piece.BLACK)));
            board.get(6).add(new CellButton(this, 6, i , Cendrawasih.SIZE/10, new Pawn(board, 6, i, Piece.WHITE)));
        }
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                    board.get(i).add(new CellButton(this, i, j, Cendrawasih.SIZE/10));
            }
        }
    }

    // MODIFIES : this.board
    // EFFECTS  : helper function to create the first/last rank of the chessboard
    private void createFirstRank(int x, int color) {
        board.get(x).add(new CellButton(this, x, 0, Cendrawasih.SIZE/10, new Rook(board, x, 0, color)));
        board.get(x).add(new CellButton(this, x, 1, Cendrawasih.SIZE/10, new Knight(board, x, 1, color)));
        board.get(x).add(new CellButton(this, x, 2, Cendrawasih.SIZE/10, new Bishop(board, x, 2, color)));
        board.get(x).add(new CellButton(this, x, 3, Cendrawasih.SIZE/10, new Queen(board, x, 3, color)));
        board.get(x).add(new CellButton(this, x, 4, Cendrawasih.SIZE/10, new King(board, x, 4, color)));
        board.get(x).add(new CellButton(this, x, 5, Cendrawasih.SIZE/10, new Bishop(board, x, 5, color)));
        board.get(x).add(new CellButton(this, x, 6, Cendrawasih.SIZE/10, new Knight(board, x, 6, color)));
        board.get(x).add(new CellButton(this,x, 7, Cendrawasih.SIZE/10, new Rook(board, x, 7, color)));
    }

    // MODIFIES : this
    // EFFECTS  : create the panel for the board.
    private void createPanel() {
        for (int i = 0; i < 10; i++)
            add(new JLabel());

        for(int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Integer.toString(i+1), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 21));
            add(label);
            for(int j = 0; j < 8; j++) {
                add(board.get(i).get(j));
            }
            add(new JLabel());
        }

        add(new JLabel());
        for(char c = 'a'; c <= 'h'; c++) {
            JLabel label = new JLabel(Character.toString(c), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 21));
            add(label);
        }
        add(new JLabel());
    }
    private void createPieces() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                Piece piece = board.get(i).get(j).getPiece();
                if(piece != null) pieces.add(piece);
            }
        }
    }

    public void setSelectedButton(CellButton selectedButton) {
        this.selectedButton = selectedButton;
    }

    public void updateTurn() {
        if(turn == Piece.WHITE) turn = Piece.BLACK;
        else turn = Piece.WHITE;
    }

    public int getTurn() {
        return turn;
    }

    public CellButton getSelectedButton() {
        return selectedButton;
    }

    public ArrayList<ArrayList<CellButton>> getBoard() {
        return board;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
