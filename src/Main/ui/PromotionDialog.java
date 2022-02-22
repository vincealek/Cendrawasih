package Main.ui;

import Main.ui.utility.*;

import javax.swing.*;
import java.awt.*;

public class PromotionDialog extends JDialog {

    private JPanel panel;
    private final int rank, file;
    private final Board board;
    public PromotionDialog(Board board, int rank, int file) {
        this.rank = rank;
        this.file = file;
        this.board = board;
        setModal(true);
        setSize(Cendrawasih.SIZE/10*4, Cendrawasih.SIZE/10);
        setLocationRelativeTo(null);
        setUndecorated(true);
        addPanel();
        setVisible(true);
    }

    public void addPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,4));
        addButton(new Queen(board.get(rank, file).getColor()));
        addButton(new Knight(board.get(rank, file).getColor()));
        addButton(new Bishop(board.get(rank, file).getColor()));
        addButton(new Rook(board.get(rank, file).getColor()));
        add(panel);
    }
    public void addButton(Piece piece) {
        System.out.println(piece.getImagePath());
        ImageIcon imageIcon = new ImageIcon(piece.getImagePath());
        Image image = imageIcon.getImage();
        imageIcon = new ImageIcon(image.getScaledInstance(Cendrawasih.SIZE/10,
                Cendrawasih.SIZE/10, Image.SCALE_SMOOTH));

        JButton button = new JButton(imageIcon);
        button.setBackground(Color.decode("#9e3021"));
        button.addActionListener(e -> {
            piece.setBoard(Cendrawasih.PANEL.getBoard());
            piece.setPosition(new Position(rank, file));
            setVisible(false);
            board.set(rank, file, piece);
        });
        panel.add(button);

    }

}
