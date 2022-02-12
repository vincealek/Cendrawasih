package ui;

import javafx.util.Pair;
import ui.Piece.Piece;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {

    private final Piece piece;
    private final Pair<Integer,Integer> position;
    public int size;

    // MODIFIES : this
    // EFFECT   : construct a cell button with a piece
    public CellButton(int x, int y, int size, Piece piece) {
        this.piece = piece;
        this.size = size;
        this.position = new Pair<>(x,y);

        if((x+y)%2 == 0)  {
            setBackground(Color.decode("#9e3021"));
        }
        else {
            setBackground(Color.decode("#cfa23d"));
        }
        ImageIcon icon = new ImageIcon((new ImageIcon(piece.getImagePath())).getImage().
                getScaledInstance(size, size, Image.SCALE_SMOOTH));
        setIcon(icon);

    }

    // MODIFIES : this
    // EFFECT   : construct a cell button wit no piece
    public CellButton(int x, int y, int size) {
        piece = null;
        this.size = size;
        this.position = new Pair<>(x,y);

        if((x+y)%2 == 0)  {
            setBackground(Color.decode("#9e3021"));
        }
        else {
            setBackground(Color.decode("#cfa23d"));
        }
    }
}
