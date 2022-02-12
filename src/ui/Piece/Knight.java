package ui.Piece;

public class Knight extends Piece {
    public Knight(int x, int y, int color) {
        super(x, y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-knight.png";
        }
        else
            imagePath = resourcePath+"/b-knight.png";
    }
}
