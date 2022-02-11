package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    private final ArrayList<ArrayList<JButton>> board;

    public BoardPanel (int size) {
        setLayout(new GridLayout(10,10));
        board = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            add(new JLabel());
        for(int i = 0; i < 8; i++)  {
            ArrayList<JButton> arrayList = new ArrayList<>();
            JLabel label = new JLabel(Integer.toString(8-i), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 21));
            add(label);
            for(int j = 0; j < 8; j++) {
                ImageIcon icon = new ImageIcon("./resource/piecesplaceholder/w-king.png");
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(size/10,
                        size/10, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImage);
                CellButton button = new CellButton(icon, i, j);

                arrayList.add(button);
                add(button);
            }
            add(new JLabel());
            board.add(arrayList);
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
