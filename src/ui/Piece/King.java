package ui.Piece;

public class King extends Piece {
    public King(int x, int y, int color) {
        super(x, y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-king.png";
        }
        else
            imagePath = resourcePath+"/b-king.png";
    }
}
