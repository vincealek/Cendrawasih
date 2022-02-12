package ui.Piece;

public class Rook extends Piece{
    public Rook(int x, int y, int color) {
        super(x,y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-rook.png";
        }
        else
            imagePath = resourcePath+"/b-rook.png";
    }
}
