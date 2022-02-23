package Main.ui;

import Main.ui.utility.Piece;
import Main.ui.utility.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellButton extends JButton {

    private final int rank, file;
    private final BoardPanel boardPanel;
    private boolean marked;
    public int size;

    // MODIFIES : this
    // EFFECT   : construct a cell button with a piece
    public CellButton(BoardPanel boardPanel, int x, int y, int size) {
        this.rank = x;
        this.file = y;
        this.boardPanel = boardPanel;
        this.size = size;

        if((x+y)%2 == 0)  {
            setBackground(Color.decode("#9e3021"));
        }
        else {
            setBackground(Color.decode("#cfa23d"));
        }
        updateIcon();
        setBorder(null);
        addActionListener();
    }

    private void addActionListener() {
        ActionListener actionListener = e -> {
            if(this.marked) {
                CellButton selectedButton = boardPanel.getSelectedButton();
                boardPanel.clearSelectedButton();
                boardPanel.board.move(selectedButton.rank, selectedButton.file, rank, file);
                boardPanel.updateIcons();
                boardPanel.getBoard().updateTurn();
                boardPanel.getBoard().get(rank, file).updateHasMoved();
            }
            else {
                SelectThisButton();
            }
        };
        addActionListener(actionListener);
    }

    private void SelectThisButton() {
        boardPanel.clearSelectedButton();
        Piece piece = boardPanel.getBoard().get(rank, file);
        if(piece == null || piece.getColor() != boardPanel.getBoard().getTurn()) {
            idle();
        }
        else {
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            boardPanel.setSelectedButton(this);
            piece.createLegalMoves();
            for(Position pos : piece.getLegalMoves()) {
                boardPanel.getCellButtons().get(pos.rank).get(pos.file).setMarked(true);
            }
        }
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
        if(marked) {
            this.setBorder(BorderFactory.createLineBorder(Color.green, 3));
        }
        else  {
            this.setBorder(null);
        }
    }
    public void updateIcon() {
        Piece piece = boardPanel.getBoard().get(rank, file);
        if(piece == null) {
            setIcon(null);
        }
        else {
            ImageIcon imageIcon = new ImageIcon(piece.getImagePath());
            Image image = imageIcon.getImage();
            imageIcon = new ImageIcon(image.getScaledInstance(size,size,  Image.SCALE_SMOOTH));
            setIcon(imageIcon);
        }
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }


    private void idle() {
        // do nothing
    }
}
