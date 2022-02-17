package ui;

import ui.Piece.King;
import ui.Piece.Pawn;
import ui.Piece.Piece;
import ui.Piece.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CellButton extends JButton {

    private Piece piece;
    private final Position position;
    private final int rank, file;
    private final BoardPanel boardPanel;
    private final ArrayList<ArrayList<CellButton>> board;
    private boolean marked;
    public int size;

    // MODIFIES : this
    // EFFECT   : construct a cell button with a piece
    public CellButton(BoardPanel boardPanel, int x, int y, int size, Piece piece) {
        this.rank = x;
        this.file = y;
        this.boardPanel = boardPanel;
        this.board = boardPanel.getBoard();
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
        this.rank = x;
        this.file = y;
        this.boardPanel = boardPanel;
        this.board = boardPanel.getBoard();
        this.piece = null;
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
                moveHere(selectedButton);
                boardPanel.updateTurn();
            }
            else {
                updateSelectedButton();
            }
        };
        addActionListener(actionListener);
    }

    private void moveHere(CellButton origin) {
        setPiece(origin.getPiece());
        origin.setPiece(null);
        handleCastling(origin);
        handlePromotion();
    }

    private void handlePromotion() {
        if (piece.getClass() == Pawn.class && (rank == 7) || (rank == 0)) {
            new PromotionDialog(this);
        }
    }

    private void handleCastling(CellButton origin) {
        if(piece.getClass() == King.class) {
            if(origin.getPosition().file-this.position.file == -2) {
                board.get(rank).get(file-1).moveHere(board.get(rank).get(file+1));
            }
            if(origin.getPosition().file-this.position.file == 2) {
                board.get(rank).get(file+1).moveHere(board.get(rank).get(file-2));
            }
        }
    }

    private void updateSelectedButton() {
        clearSelectedButton();
        if(piece == null || piece.getColor() != boardPanel.getTurn()) {
            idle();
        }
        else {
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            boardPanel.setSelectedButton(this);
            if (piece != null) {
                piece.createLegalNextPositions();
                for(Position pos : piece.getLegalNextPositions()) {
                    board.get(pos.rank).get(pos.file).setMarked(true);
                }
            }
        }
    }
    private void clearSelectedButton() {
        if(boardPanel.getSelectedButton() != null) {
            boardPanel.getSelectedButton().setBorder(null);
            Piece selectedPiece = boardPanel.getSelectedButton().getPiece();
            if(selectedPiece != null) {
                for (Position pos : selectedPiece.getLegalNextPositions()) {
                    board.get(pos.rank).get(pos.file).setMarked(false);
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

    public Piece getPiece() {
        return piece;
    }

    public Position getPosition () {
        return position;
    }

    private void idle() {
        // do nothing
    }
}
