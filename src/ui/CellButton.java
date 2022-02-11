package ui;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {

    private int piece; //
    private final int x, y; // position from up to down and left to right

    public CellButton(Icon icon, int x, int y) {
        setIcon(icon);
        this.x = x;
        this.y = y;
        if((x+y)%2 == 1)  {
            setBackground(Color.decode("#204040"));
        }
        else {
            setBackground(Color.decode("#FFEECC"));
        }
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(piece).append(", ").append(x)
                .append(" ").append(y).append("]");
        return new String(builder);
    }
}
