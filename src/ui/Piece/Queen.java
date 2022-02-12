package ui.Piece;

public class Queen extends Piece {
    public Queen(int x, int y, int color) {
        super(x, y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-queen.png";
        }
        else
            imagePath = resourcePath+"/b-queen.png";
    }
}
