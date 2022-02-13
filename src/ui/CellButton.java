package ui;

import ui.Piece.Piece;
import ui.Piece.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellButton extends JButton {

    private Piece piece;
    private final Position position;
    private final BoardPanel boardPanel;
    private ActionListener actionListener;
    private boolean marked;
    public int size;

    // MODIFIES : this
    // EFFECT   : construct a cell button with a piece
    public CellButton(BoardPanel boardPanel, int x, int y, int size, Piece piece) {
        this.boardPanel = boardPanel;
        this.piece = piece;
        this.size = size;
        this.position = new Position(x,y);

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

    // MODIFIES : this
    // EFFECT   : construct a cell button wit no piece
    public CellButton(BoardPanel boardPanel, int x, int y, int size) {
        this.boardPanel = boardPanel;
        piece = null;
        this.size = size;
        this.position = new Position(x,y);

        if((x+y)%2 == 0)  {
            setBackground(Color.decode("#9e3021"));
        }
        else {
            setBackground(Color.decode("#cfa23d"));
        }
        setBorder(null);
        addActionListener();
    }

    private void addActionListener() {
        ActionListener actionListener = e -> {
            if(this.marked) {
                CellButton selectedButton = boardPanel.getSelectedButton();
                clearSelectedButton();
                setPiece(selectedButton.getPiece());
                selectedButton.setPiece(null);
            }
            else {
                updateSelectedButton();
            }
        };
        addActionListener(actionListener);
    }

    private void updateSelectedButton() {
        clearSelectedButton();
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        boardPanel.setSelectedButton(this);
        if (piece != null) {
            piece.createLegalNextPositions();
            for(Position pos : piece.getLegalNextPositions()) {
                boardPanel.board.get(pos.rank).get(pos.file).setMarked(true);
            }
        }
    }
    private void clearSelectedButton() {
        if(boardPanel.getSelectedButton() != null) {
            boardPanel.getSelectedButton().setBorder(null);
            Piece selectedPiece = boardPanel.getSelectedButton().getPiece();
            if(selectedPiece != null) {
                for (Position pos : selectedPiece.getLegalNextPositions()) {
                    boardPanel.board.get(pos.rank).get(pos.file).setMarked(false);
                }
            }
            boardPanel.setSelectedButton(null);
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
    private void updateIcon() {
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

    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        if(piece == null) {
            this.piece = null;
            updateIcon();
        }
        else {
            piece.setPosition(position);
            this.piece = piece;
            updateIcon();
        }
    }

    public Position getPosition () {
        return position;
    }
}
