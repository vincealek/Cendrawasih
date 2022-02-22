package Main.ui;

import Main.ui.utility.Board;
import Main.ui.utility.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    public int turn;

    public CellButton selectedButton;
    public final Board board;
    public final ArrayList<ArrayList<CellButton>> cellButtons;

    // EFFECTS : create a board and a panel for the board
    public BoardPanel () {
        this.turn = Piece.WHITE;
        this.selectedButton = null;
        this.cellButtons = new ArrayList<>();
        this.board = new Board();
        setLayout(new GridLayout(10,10));
        createPanel();
    }

    // MODIFIES : this
    // EFFECTS  : create the panel for the board.
    private void createPanel() {
        for (int i = 0; i < 10; i++)
            add(new JLabel());

        for(int i = 0; i < 8; i++)
        cellButtons.add(new ArrayList<>());
        for(int i = 0; i < 8; i++) {
            JLabel label = new JLabel(Integer.toString(i+1), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 21));
            add(label);
            for(int j = 0; j < 8; j++) {
                cellButtons.get(i).add(new CellButton(this, i, j, Cendrawasih.SIZE/10));
                add(cellButtons.get(i).get(j));
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
    public void clearSelectedButton() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                cellButtons.get(i).get(j).setMarked(false);
        selectedButton = null;
    }

    public void updateIcons() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                cellButtons.get(i).get(j).updateIcon();
            }
        }
    }

    public void updateTurn() {
        if(turn == Piece.WHITE) {
            turn = Piece.BLACK;
        }
        else turn = Piece.WHITE;
    }

    public void setSelectedButton(CellButton cellButton) {
        selectedButton = cellButton;
    }

    public int getTurn() {
        return turn;
    }

    public ArrayList<ArrayList<CellButton>> getCellButtons() {
        return cellButtons;
    }

    public CellButton getSelectedButton() {
        return selectedButton;
    }

    public Board getBoard() {
        return board;
    }
}
