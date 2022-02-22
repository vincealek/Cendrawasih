package Main.ui.utility;

public class Position {
    public int rank, file;
    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }
    public boolean isEqual(Position position) {
        return (this.rank == position.rank) && (this.file == position.file);
    }
}
