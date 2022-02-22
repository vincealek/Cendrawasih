package ui;

import ui.Piece.*;

import javax.swing.*;
import java.awt.*;

public class PromotionDialog extends JDialog {

    public JPanel panel;
    public CellButton callerButton;
    public PromotionDialog(CellButton cellButton) {
        this.callerButton = cellButton;
        setModal(true);
        setSize(cellButton.size*4, cellButton.size);
        setLocationRelativeTo(null);
        setUndecorated(true);
        addPanel();
        setVisible(true);
    }

    public void addPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(1,4));
        addButton(new Queen(callerButton.getPiece().getColor()));
        addButton(new Rook(callerButton.getPiece().getColor()));
        addButton(new Knight(callerButton.getPiece().getColor()));
        addButton(new Bishop(callerButton.getPiece().getColor()));
        add(panel);
    }
    public void addButton(Piece piece) {
        ImageIcon imageIcon = new ImageIcon(piece.getImagePath());
        Image image = imageIcon.getImage();
        imageIcon = new ImageIcon(image.getScaledInstance(callerButton.size,callerButton.size,
                Image.SCALE_SMOOTH));

        JButton button = new JButton(imageIcon);
        button.setBackground(Color.decode("#9e3021"));
        button.addActionListener(e -> {
            piece.setPosition(callerButton.getPosition());
            piece.setBoard(Cendrawasih.PANEL.getBoard());
            callerButton.setPiece(piece);
            callerButton.getBoardPanel().getPieces().add(piece);
            setVisible(false);
        });
        panel.add(button);

    }

}
