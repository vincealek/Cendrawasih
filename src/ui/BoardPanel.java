package ui;

import ui.Piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    public int size;
    private final ArrayList<ArrayList<CellButton>> board;

    // EFFECTS : create a board and a panel for the board
    public BoardPanel (int size) {
        this.size = size;
        setLayout(new GridLayout(10,10));
        board = new ArrayList<>();
        createBoard();
        createPanel();
    }

    // MODIFIES : this.board
    // EFFECTS  : create the chessboard
    private void createBoard() {
        for(int i = 0; i < 8; i++)
            board.add(new ArrayList<>());

       createFirstRank(0, Piece.BLACK);
       createFirstRank(7, Piece.WHITE);
        for(int i = 0; i < 8; i++) {
            board.get(1).add(new CellButton(1, i , size/10, new Pawn(1, i, Piece.BLACK)));
            board.get(6).add(new CellButton(6, i , size/10, new Pawn(6, i, Piece.WHITE)));
        }
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                    board.get(i).add(new CellButton(i, j, size/10));
            }
        }
    }

    // MODIFIES : this.board
    // EFFECTS  : helper function to create the first/last rank of the chessboard
    private void createFirstRank(int x, int color) {
        board.get(x).add(new CellButton(x, 0, size/10, new Rook(7, 0, color)));
        board.get(x).add(new CellButton(x, 1, size/10, new Knight(7, 1, color)));
        board.get(x).add(new CellButton(x, 2, size/10, new Bishop(7, 2, color)));
        board.get(x).add(new CellButton(x, 3, size/10, new Queen(7, 3, color)));
        board.get(x).add(new CellButton(x, 4, size/10, new King(7, 4, color)));
        board.get(x).add(new CellButton(x, 5, size/10, new Bishop(7, 5, color)));
        board.get(x).add(new CellButton(x, 6, size/10, new Knight(7, 6, color)));
        board.get(x).add(new CellButton(x, 7, size/10, new Rook(7, 7, color)));
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
}
