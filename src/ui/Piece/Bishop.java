package ui.Piece;

public class Bishop extends Piece {
    public Bishop(int x, int y, int color) {
        super(x, y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-bishop.png";
        }
        else
            imagePath = resourcePath+"/b-bishop.png";
    }
}
