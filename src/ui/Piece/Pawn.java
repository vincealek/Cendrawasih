package ui.Piece;

public class Pawn extends Piece {

    public Pawn(int x, int y, int color) {
        super(x,y, color);
        if(color == WHITE) {
            imagePath = resourcePath+"/w-pawn.png";
        }
        else
            imagePath = resourcePath+"/b-pawn.png";
    }
}
