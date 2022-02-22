package Test;

import Main.ui.utility.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    void runBefore() {
        board = new Board();
    }

    @Test
    public void testConstructor() {
        assertEquals(Rook.class, board.get(0,0).getClass());
        assertEquals(Piece.BLACK, board.get(0,0).getColor());
        assertEquals(Pawn.class, board.get(1,0).getClass());
        assertNull(board.get(2, 3));
        assertNull(board.get(3, 4));
        assertEquals(King.class, board.get(7,4).getClass());
        assertEquals(Bishop.class, board.get(7,5).getClass());
        assertEquals(Piece.WHITE, board.get(7,5).getColor());
        assertNotNull(board.get(0,0).getLegalNextPositions());
    }

    @Test
    public void move() {
        board.move(6, 4, 4, 4);
        board.move(6, 5, 5, 5);
        board.move(7, 5, 4, 2);
        board.move(7,6,5,7);
        board.move(7,4,7,6);
        assertEquals(Rook.class, board.get(7,5).getClass());
        assertEquals(7, board.get(7,5).getRank());
        assertEquals(5, board.get(7,5).getFile());

    }


}
