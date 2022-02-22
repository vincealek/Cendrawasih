package Main.ui;

import javax.swing.*;
import java.awt.*;

public class Cendrawasih {

    public static final int SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*3/4;
    public static JFrame FRAME;
    public static BoardPanel PANEL = new BoardPanel();

    public Cendrawasih() {
        setFrame();
    }

    private void setFrame() {
        FRAME = new JFrame("Cendrawasih");
        FRAME.setSize(SIZE, SIZE);
        FRAME.setMinimumSize(new Dimension(SIZE/2, SIZE/2));
        FRAME.setResizable(false);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setLocationRelativeTo(null);
        FRAME.add(PANEL);
        FRAME.setVisible(true);
    }

}
