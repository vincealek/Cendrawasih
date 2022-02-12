package ui.Piece;

public abstract class Piece {

    public static final int WHITE = 0, BLACK = 1;
    protected static final String resourcePath = "./resource/piecesplaceholder";
    protected String imagePath;
    protected int x, y, color;

    public Piece(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public String getImagePath() {
        return imagePath;
    }
}
