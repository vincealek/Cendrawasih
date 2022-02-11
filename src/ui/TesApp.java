package ui;

import javax.swing.*;
import java.awt.*;

public class TesApp {

    private static int SIZE;
    private static JFrame frame;

    public TesApp() {
        SIZE = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*3/4;
        setFrame();
    }

    private void setFrame() {
        frame = new JFrame("Cendrawasih");
        frame.setSize(SIZE, SIZE);
        frame.setMinimumSize(new Dimension(SIZE/2, SIZE/2));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BoardPanel(SIZE));
        frame.setVisible(true);
    }

}
